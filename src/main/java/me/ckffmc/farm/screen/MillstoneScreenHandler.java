package me.ckffmc.farm.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class MillstoneScreenHandler extends ScreenHandler {
    protected final CraftingResultInventory output = new CraftingResultInventory();
    private final PropertyDelegate propertyDelegate;
    private final Inventory inventory;
    final Slot inputSlot;
    final Slot outputSlot;

    public MillstoneScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(2), new ArrayPropertyDelegate(2));
    }

    public MillstoneScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(MyScreenHandlerType.MILLSTONE_SCREEN_HANDLER, syncId);
        checkSize(inventory, 2);
        checkDataCount(propertyDelegate, 2);
        this.propertyDelegate = propertyDelegate;
        this.inventory = inventory;
        this.addProperties(propertyDelegate);

        inputSlot = this.addSlot(new Slot(inventory, 0, 44, 47));
        outputSlot = this.addSlot(new Slot(inventory, 1, 116, 47) {
            public boolean canInsert(ItemStack stack) { return false; }

            public ItemStack onTakeItem(PlayerEntity player, ItemStack stack) {
                stack.onCraft(player.world, player, stack.getCount());
                MillstoneScreenHandler.this.output.unlockLastRecipe(player);
                return super.onTakeItem(player, stack);
            }
        });

        int m, l;
        for (m = 0; m < 3; ++m) {
            for (l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 84 + m * 18));
            }
        }
        for (m = 0; m < 9; ++m) this.addSlot(new Slot(playerInventory, m, 8 + m * 18, 142));
    }

    public boolean canUse(PlayerEntity player) { return this.inventory.canPlayerUse(player); }

    @Environment(EnvType.CLIENT)
    public int getCraftProgress() {
        int i = this.propertyDelegate.get(0);
        int j = this.propertyDelegate.get(1);
        return j != 0 && i != 0 ? i * 24 / j : 0;
    }

    public ItemStack transferSlot(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack oldStack = slot.getStack();
            newStack = oldStack.copy();
            if (invSlot == 1) {
                if (!this.insertItem(oldStack, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onStackChanged(oldStack, newStack);
            } else if (invSlot >= 2 && invSlot < 38) {
                if (!this.insertItem(oldStack, 0, 1, false)) {
                    if (invSlot < 29) {
                        if (!this.insertItem(oldStack, 29, 38, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (!this.insertItem(oldStack, 2, 29, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else if (!this.insertItem(oldStack, 2, 38, false)) {
                return ItemStack.EMPTY;
            }

            if (oldStack.isEmpty()) slot.setStack(ItemStack.EMPTY);
            else slot.markDirty();

            if (oldStack.getCount() == newStack.getCount()) return ItemStack.EMPTY;

            if (invSlot == 1) player.dropItem(slot.onTakeItem(player, oldStack), false);
        }
        return newStack;
    }

    public void close(PlayerEntity player) {
        super.close(player);
        this.inventory.onClose(player);
    }
}
