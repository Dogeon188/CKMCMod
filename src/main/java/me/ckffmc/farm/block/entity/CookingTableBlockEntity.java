package me.ckffmc.farm.block.entity;

import me.ckffmc.farm.MainMod;
import me.ckffmc.farm.screen.CookingTableScreenHandler;
import me.ckffmc.farm.screen.MillstoneScreenHandler;
import me.ckffmc.farm.util.ImplementedInventory;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
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

public class CookingTableBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory,
        Tickable, SidedInventory, BlockEntityClientSerializable {
    private static final int invsize = 5;
    private DefaultedList<ItemStack> inventory;
    private int cookTime;
    private int totalCookTime;
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
                    case 0: CookingTableBlockEntity.this.cookTime = value;      break;
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
        this.inventory = DefaultedList.ofSize(invsize, ItemStack.EMPTY);
        Inventories.fromTag(tag, this.inventory);
    }

    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        tag.putShort("CookTime", (short)this.cookTime);
        tag.putShort("TotalCookTime", (short)this.totalCookTime);
        Inventories.toTag(tag, this.inventory);
        return tag;
    }

    public void fromClientTag(CompoundTag tag) {
        assert this.world != null;
        fromTag(this.world.getBlockState(this.pos), tag);
    }

    public CompoundTag toClientTag(CompoundTag tag) { return toTag(tag); }

    public int[] getAvailableSlots(Direction side) {
        return (side == Direction.DOWN) ? new int[]{4} : new int[]{0, 1, 2, 3};
    }

    public boolean isValid(int slot, ItemStack stack) { return slot >= 0 && slot < 4; }

    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        return this.isValid(slot, stack);
    }

    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return (dir == Direction.DOWN && slot == 4);
    }

    public Text getDisplayName() {
        return new TranslatableText("container." + MainMod.MOD_ID + ".cooking_table");
    }

    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new CookingTableScreenHandler(syncId, inv, this, this.propertyDelegate);
    }

    public void tick() {

    }
}
