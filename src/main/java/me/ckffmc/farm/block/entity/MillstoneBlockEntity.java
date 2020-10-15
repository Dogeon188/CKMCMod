package me.ckffmc.farm.block.entity;

import me.ckffmc.farm.screen.MillstoneScreenHandler;
import me.ckffmc.farm.util.ImplementedInventory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;

public class MillstoneBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
    private final int invsize = 2;
    private DefaultedList<ItemStack> inventory = DefaultedList.ofSize(invsize, ItemStack.EMPTY);

    public MillstoneBlockEntity() { super(MyBlockEntityType.MILLSTONE_BLOCK_ENTITY); }

    public DefaultedList<ItemStack> getItems() { return inventory; }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        Inventories.toTag(tag, this.inventory);
        return tag;
    }

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        inventory = DefaultedList.ofSize(invsize, ItemStack.EMPTY);
        Inventories.fromTag(tag, this.inventory);
    }

    public boolean isValid(int slot, ItemStack stack) {
        return slot == 0;
    }

    @Override
    public Text getDisplayName() {
        return new TranslatableText(getCachedState().getBlock().getTranslationKey());
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new MillstoneScreenHandler(syncId, inv, this);
    }
}

