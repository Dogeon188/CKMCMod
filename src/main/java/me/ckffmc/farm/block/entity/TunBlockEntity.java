package me.ckffmc.farm.block.entity;

import me.ckffmc.farm.MainMod;
import me.ckffmc.farm.recipe.FermentingRecipeRegistry;
import me.ckffmc.farm.screen.TunScreenHandler;
import me.ckffmc.farm.util.ImplementedInventory;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Tickable;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class TunBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory,
        Tickable, SidedInventory, BlockEntityClientSerializable {
    private static final int invsize = 4;
    private DefaultedList<ItemStack> inventory;
    private int[] fermentTimes;
    private int fuel;
    protected final PropertyDelegate propertyDelegate;

    public TunBlockEntity() {
        super(MyBlockEntityType.TUN_BLOCK_ENTITY);
        this.inventory = DefaultedList.ofSize(invsize, ItemStack.EMPTY);
        this.fermentTimes = new int[3];
        this.propertyDelegate = new PropertyDelegate() {
            public int get(int index) {
                switch (index) {
                    case 0: case 1: case 2:
                        return TunBlockEntity.this.fermentTimes[index];
                    case 3: return TunBlockEntity.this.fuel;
                    default: return 0;
                }
            }

            public void set(int index, int value) {
                switch (index) {
                    case 0: case 1: case 2:
                        TunBlockEntity.this.fermentTimes[index] = value; break;
                    case 3: TunBlockEntity.this.fuel = value;
                }
            }

            public int size() { return 4; }
        };
    }

    public DefaultedList<ItemStack> getItems() { return inventory; }

    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        this.fermentTimes = tag.getIntArray("FermentTimes");
        if (tag.contains("FermentTimes", 11)) {
            int[] js = tag.getIntArray("FermentTimes");
            System.arraycopy(js, 0, this.fermentTimes, 0, Math.min(3, js.length));
        }
        this.fuel = tag.getShort("Fuel");
        this.inventory = DefaultedList.ofSize(invsize, ItemStack.EMPTY);
        Inventories.fromTag(tag, this.inventory);
    }

    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        tag.putIntArray("FermentTimes", this.fermentTimes);
        tag.putShort("Fuel", (short)this.fuel);
        Inventories.toTag(tag, this.inventory);
        return tag;
    }

    public void fromClientTag(CompoundTag tag) { fromTag(this.getCachedState(), tag); }

    public CompoundTag toClientTag(CompoundTag tag) { return toTag(tag); }

    public int[] getAvailableSlots(Direction side) {
        return (side == Direction.DOWN) ? new int[]{1, 2, 3} : new int[]{0};
    }

    public boolean isValid(int slot, ItemStack stack) { return slot >= 1 && slot < 4; }

    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) { return this.isValid(slot, stack); }

    public boolean canExtract(int slot, ItemStack stack, Direction dir) { return (dir == Direction.DOWN && slot == 3); }

    public Text getDisplayName() { return new TranslatableText("container." + MainMod.MOD_ID + ".tun"); }

    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new TunScreenHandler(syncId, inv, this, this.propertyDelegate);
    }

    public void tick() {
        boolean isDirty = false;
        ItemStack itemStack = this.inventory.get(3);
        if (this.fuel <= 0 && (itemStack.getItem() == Items.BROWN_MUSHROOM || itemStack.getItem() == Items.RED_MUSHROOM)) {
            this.fuel = 20;
            itemStack.decrement(1);
            isDirty = true;
        }

        for (int i = 0; i < 3; i++) {
            boolean canCraft = this.canCraft(i);
            if (this.fermentTimes[i] > 0) {
                --this.fermentTimes[i];
                if (this.fermentTimes[i] == 0 && canCraft) {
                    this.craft(i);
                    isDirty = true;
                } else if (!canCraft) {
                    this.fermentTimes[i] = 0;
                    isDirty = true;
                }
            } else if (this.fuel > 0 && canCraft) {
                --this.fuel;
                this.fermentTimes[i] = FermentingRecipeRegistry.getFermentTime();
                isDirty = true;
            }
        }
        if (isDirty) this.markDirty();
    }

    private boolean canCraft(int index) {
        ItemStack itemStack = this.inventory.get(index);
        if (itemStack.isEmpty()) return false;
        else return FermentingRecipeRegistry.isValidIngredient(itemStack);
    }

    private void craft(int index) {
        this.inventory.set(index, FermentingRecipeRegistry.craft(this.inventory.get(index)));
    }

    public void setStack(int slot, ItemStack stack) {
        if (slot >= 0 && slot < this.inventory.size()) this.inventory.set(slot, stack);
    }

    public void markDirty() {
        super.markDirty();
        Objects.requireNonNull(getWorld()).updateListeners(getPos(), getCachedState(), getCachedState(), 0b11);
    }
}
