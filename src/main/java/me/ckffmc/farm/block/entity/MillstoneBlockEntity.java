package me.ckffmc.farm.block.entity;

import me.ckffmc.farm.MainMod;
import me.ckffmc.farm.block.MillstoneBlock;
import me.ckffmc.farm.recipe.MillingRecipe;
import me.ckffmc.farm.recipe.MyRecipeType;
import me.ckffmc.farm.screen.MillstoneScreenHandler;
import me.ckffmc.farm.util.ImplementedInventory;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.Recipe;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Tickable;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

public class MillstoneBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory,
        Tickable, SidedInventory, BlockEntityClientSerializable {
    private static final int invsize = 2;
    public float grind_rotation = 0;
    private DefaultedList<ItemStack> inventory;
    private int craftTime;
    private int totalCraftTime;
    protected final PropertyDelegate propertyDelegate;

    public MillstoneBlockEntity() {
        super(MyBlockEntityType.MILLSTONE_BLOCK_ENTITY);
        this.inventory = DefaultedList.ofSize(invsize, ItemStack.EMPTY);
        this.propertyDelegate = new PropertyDelegate() {
            public int get(int index) {
                switch (index) {
                    case 0:  return MillstoneBlockEntity.this.craftTime;
                    case 1:  return MillstoneBlockEntity.this.totalCraftTime;
                    default: return 0;
                }
            }

            public void set(int index, int value) {
                switch (index) {
                    case 0: MillstoneBlockEntity.this.craftTime = value;      break;
                    case 1: MillstoneBlockEntity.this.totalCraftTime = value;
                }
            }

            public int size() { return 2; }
        };
    }

    public DefaultedList<ItemStack> getItems() { return inventory; }

    public ParticleEffect getParticle() {
        ItemStack item = this.inventory.get(0);
        return new ItemStackParticleEffect(ParticleTypes.ITEM, item);
    }

    public boolean isCrafting() { return this.craftTime > 0; }

    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        this.craftTime = tag.getShort("CraftTime");
        this.totalCraftTime = tag.getShort("TotalCraftTime");
        this.inventory = DefaultedList.ofSize(invsize, ItemStack.EMPTY);
        Inventories.fromTag(tag, this.inventory);
    }

    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        tag.putShort("CraftTime", (short)this.craftTime);
        tag.putShort("TotalCraftTime", (short)this.totalCraftTime);
        Inventories.toTag(tag, this.inventory);
        return tag;
    }

    public void fromClientTag(CompoundTag tag) {
        this.craftTime = tag.getShort("CraftTime");
        this.totalCraftTime = tag.getShort("TotalCraftTime");
        this.inventory = DefaultedList.ofSize(invsize, ItemStack.EMPTY);
        Inventories.fromTag(tag, this.inventory);
    }

    public CompoundTag toClientTag(CompoundTag tag) {
        return toTag(tag);
    }

    public void tick() {
        boolean isDirty = false;
        boolean oldIsCrafting = this.isCrafting();
        if (this.world != null && !this.world.isClient) {
            if (this.isCrafting() && this.inventory.get(0).isEmpty()) {
                this.craftTime = Math.max(this.craftTime - 2, 0);
            } else {
                Recipe<?> r = this.world.getRecipeManager().getFirstMatch(MyRecipeType.MILLING,
                        this, this.world).orElse(null);
                if (this.canAcceptRecipeOutput(r)) {
                    ++this.craftTime;
                    if (this.craftTime == this.totalCraftTime) {
                        this.totalCraftTime = this.getCraftTime();
                        this.craftTime = 0;
                        this.craftRecipe(r);
                        isDirty = true;
                    }
                } else this.craftTime = 0;
            }
            if (oldIsCrafting != this.isCrafting()) {
                isDirty = true;
                this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(MillstoneBlock.MILLING,
                        this.isCrafting()), 3);
            }
            if (!this.isCrafting()) this.world.setBlockState(this.pos,
                    this.world.getBlockState(this.pos).with(MillstoneBlock.MILLING, false), 3);
        }
        if (isDirty) this.markDirty();
    }

    protected boolean canAcceptRecipeOutput(@Nullable Recipe<?> recipe) {
        if (!this.inventory.get(0).isEmpty() && recipe != null) {
            ItemStack recipeOutput = recipe.getOutput();
            if (recipeOutput.isEmpty()) {
                return false;
            } else {
                ItemStack outputSlot = this.inventory.get(1);
                if (outputSlot.isEmpty()) return true;
                else if (!outputSlot.isItemEqualIgnoreDamage(recipeOutput)) return false;
                else if (outputSlot.getCount() < this.getMaxCountPerStack() && outputSlot.getCount() < outputSlot.getMaxCount()) return true;
                else return outputSlot.getCount() < recipeOutput.getMaxCount();
            }
        } else return false;
    }

    protected int getCraftTime() {
        assert this.world != null;
        return this.world.getRecipeManager().getFirstMatch(MyRecipeType.MILLING, this, this.world).map(MillingRecipe::getCraftTime).orElse(200); }

    private void craftRecipe(@Nullable Recipe<?> recipe) {
        if (recipe != null && this.canAcceptRecipeOutput(recipe)) {
            ItemStack recipeOutput = recipe.getOutput();
            ItemStack outputStack = this.inventory.get(1);
            if (outputStack.isEmpty()) {
                this.inventory.set(1, recipeOutput.copy());
            } else if (outputStack.getItem() == recipeOutput.getItem()) {
                outputStack.increment(recipeOutput.getCount());
            }
            this.inventory.get(0).decrement(1);
        }
    }

    public void setStack(int slot, ItemStack stack) {
        ItemStack originalStack = this.inventory.get(slot);
        boolean flag = !stack.isEmpty() && stack.isItemEqualIgnoreDamage(originalStack) && ItemStack.areTagsEqual(stack
                , originalStack);
        this.inventory.set(slot, stack);
        if (stack.getCount() > this.getMaxCountPerStack()) {
            stack.setCount(this.getMaxCountPerStack());
        }

        if (slot == 0 && !flag) {
            this.totalCraftTime = this.getCraftTime();
            this.craftTime = 0;
            this.markDirty();
        }
    }

    public boolean isValid(int slot, ItemStack stack) {
        return slot == 0;
    }

    public Text getDisplayName() {
        return new TranslatableText("container." + MainMod.MOD_ID + ".millstone");
    }

    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new MillstoneScreenHandler(syncId, inv, this, this.propertyDelegate);
    }

    public int[] getAvailableSlots(Direction side) {
        return (side == Direction.DOWN) ? new int[]{1} : new int[]{0};
    }

    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        return this.isValid(slot, stack);
    }

    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return (dir == Direction.DOWN && slot == 1);
    }
}

