package me.ckffmc.farm.block.entity;

import me.ckffmc.farm.MainMod;
import me.ckffmc.farm.recipe.CookingRecipe;
import me.ckffmc.farm.recipe.MyRecipeType;
import me.ckffmc.farm.screen.CookingTableScreenHandler;
import me.ckffmc.farm.util.ImplementedInventory;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.recipe.Recipe;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Tickable;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class CookingTableBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory,
        Tickable, SidedInventory, BlockEntityClientSerializable {
    private static final int invsize = 5;
    private DefaultedList<ItemStack> inventory;
    private int cookTime;
    private int totalCookTime;
    private float experience;
    protected final PropertyDelegate propertyDelegate;

    public CookingTableBlockEntity() {
        super(MyBlockEntityType.COOKING_TABLE_BLOCK_ENTITY);
        this.inventory = DefaultedList.ofSize(invsize, ItemStack.EMPTY);
        this.propertyDelegate = new PropertyDelegate() {
            public int get(int index) {
                switch (index) {
                    case 0:  return CookingTableBlockEntity.this.cookTime;
                    case 1:  return CookingTableBlockEntity.this.totalCookTime;
                    default: return 0;
                }
            }

            public void set(int index, int value) {
                switch (index) {
                    case 0: CookingTableBlockEntity.this.cookTime = value; break;
                    case 1: CookingTableBlockEntity.this.totalCookTime = value;
                }
            }

            public int size() { return 2; }
        };
    }

    public DefaultedList<ItemStack> getItems() { return inventory; }

    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        this.cookTime = tag.getShort("CookTime");
        this.totalCookTime = tag.getShort("TotalCookTime");
        this.experience = tag.getFloat("Experience");
        this.inventory = DefaultedList.ofSize(invsize, ItemStack.EMPTY);
        Inventories.fromTag(tag, this.inventory);
    }

    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        tag.putShort("CookTime", (short)this.cookTime);
        tag.putShort("TotalCookTime", (short)this.totalCookTime);
        tag.putFloat("Experience", this.experience);
        Inventories.toTag(tag, this.inventory);
        return tag;
    }

    public void fromClientTag(CompoundTag tag) { fromTag(this.getCachedState(), tag); }

    public CompoundTag toClientTag(CompoundTag tag) { return toTag(tag); }

    public int[] getAvailableSlots(Direction side) {
        return (side == Direction.DOWN) ? new int[]{4} : new int[]{0, 1, 2, 3};
    }

    public boolean isValid(int slot, ItemStack stack) { return slot >= 0 && slot < 4; }

    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) { return this.isValid(slot, stack); }

    public boolean canExtract(int slot, ItemStack stack, Direction dir) { return (dir == Direction.DOWN && slot == 4); }

    public Text getDisplayName() { return new TranslatableText("container." + MainMod.MOD_ID + ".cooking_table"); }

    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new CookingTableScreenHandler(syncId, inv, this, this.propertyDelegate);
    }

    public boolean isCooking() { return this.cookTime > 0; }

    protected int getCookTime() {
        if (this.world != null)
            return this.world.getRecipeManager().getFirstMatch(MyRecipeType.COOKING, this, this.world).map(CookingRecipe::getCookTime).orElse(CookingRecipe.DEFAULT_COOK_TIME);
        return 0;
    }

    public void tick() {
        boolean isDirty = false;
        boolean oldIsCooking = this.isCooking();
        if (this.world != null && !this.world.isClient) {
            if (!this.inventory.subList(0, 4).isEmpty()) {
                Recipe<?> r = this.world.getRecipeManager().getFirstMatch(MyRecipeType.COOKING, this, this.world).orElse(null);
                if (this.canAcceptRecipeOutput(r)) {
                    ++this.cookTime;
                    if (this.cookTime == this.totalCookTime) {
                        this.totalCookTime = this.getCookTime();
                        this.cookTime = 0;
                        this.craftRecipe(r);
                        isDirty = true;
                    }
                } else this.cookTime = 0;
            }
            if (oldIsCooking != this.isCooking()) isDirty = true;
        }
        if (isDirty) this.markDirty();
    }

    protected boolean canAcceptRecipeOutput(@Nullable Recipe<?> recipe) {
        if (!this.inventory.subList(0, 4).isEmpty() && recipe != null) {
            ItemStack recipeOutput = recipe.getOutput();
            if (recipeOutput.isEmpty()) return false;
            ItemStack outputSlot = this.inventory.get(4);
            if (outputSlot.isEmpty()) return true;
            if (!outputSlot.isItemEqualIgnoreDamage(recipeOutput)) return false;
            if (outputSlot.getCount() < this.getMaxCountPerStack() && outputSlot.getCount() < outputSlot.getMaxCount()) return true;
            return outputSlot.getCount() < recipeOutput.getMaxCount();
        } return false;
    }

    private void craftRecipe(@Nullable Recipe<?> recipe) {
        if (recipe != null && this.canAcceptRecipeOutput(recipe)) {
            ItemStack recipeOutput = recipe.getOutput();
            ItemStack outputStack = this.inventory.get(4);
            if (outputStack.isEmpty()) this.inventory.set(4, recipeOutput.copy());
            else if (outputStack.getItem() == recipeOutput.getItem()) {
                outputStack.increment(recipeOutput.getCount());
            }
            this.inventory.subList(0, 4).forEach((stack) -> stack.decrement(1));
            this.experience += ((CookingRecipe)recipe).getExperience();
        }
    }

    public void setStack(int slot, ItemStack stack) {
        ItemStack originalStack = this.inventory.get(slot);
        boolean b = !stack.isEmpty() && stack.isItemEqualIgnoreDamage(originalStack) && ItemStack.areTagsEqual(stack, originalStack);
        this.inventory.set(slot, stack);
        if (stack.getCount() > this.getMaxCountPerStack()) stack.setCount(this.getMaxCountPerStack());

        if ((slot >= 0 && slot < 4) && !b) {
            this.totalCookTime = this.getCookTime();
            this.markDirty();
        }
    }

    public void dropExperience(PlayerEntity player) {
        if (world != null) {
            int j = MathHelper.floor(this.experience);
            float g = MathHelper.fractionalPart(experience);
            if (g != 0.0F && Math.random() < (double) g) ++j;
            while (j > 0) {
                int k = ExperienceOrbEntity.roundToOrbSize(j);
                j -= k;
                world.spawnEntity(new ExperienceOrbEntity(world, player.getX(), player.getY(), player.getZ(), k));
            }
            experience = 0;
        }
    }

    public void markDirty() {
        super.markDirty();
        Objects.requireNonNull(getWorld()).updateListeners(getPos(), getCachedState(), getCachedState(), 0b11);
    }
}
