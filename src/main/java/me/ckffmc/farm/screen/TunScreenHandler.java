package me.ckffmc.farm.screen;

import me.ckffmc.farm.block.entity.TunBlockEntity;
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

public class TunScreenHandler extends ScreenHandler {
    private final PropertyDelegate propertyDelegate;
    private final Inventory inventory;

    public TunScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(4), new ArrayPropertyDelegate(7));
    }

    public TunScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(MyScreenHandlerType.TUN_SCREEN_HANDLER, syncId);
        checkSize(inventory, 4);
        checkDataCount(propertyDelegate, 7);
        this.inventory = inventory;
        this.propertyDelegate = propertyDelegate;
        this.addProperties(propertyDelegate);

        this.addSlot(new Slot(inventory, 0, 56, 42));
        this.addSlot(new Slot(inventory, 1, 79, 49));
        this.addSlot(new Slot(inventory, 2, 102, 42));
        this.addSlot(new Slot(inventory, 3, 40, 18)); // fuel slot

        int m, l;
        for (m = 0; m < 3; ++m) {
            for (l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 84 + m * 18));
            }
        }
        for (m = 0; m < 9; ++m) this.addSlot(new Slot(playerInventory, m, 8 + m * 18, 142));
    }

    public ItemStack transferSlot(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack oldStack = slot.getStack();
            newStack = oldStack.copy();
            if (invSlot < 3) {
                if (!this.insertItem(oldStack, 4, 40, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onStackChanged(oldStack, newStack);
            } else if (invSlot >= 5 && invSlot < 41) {
                if (!this.insertItem(oldStack, 0, 4, false)) {
                    if (invSlot < 31) {
                        if (!this.insertItem(oldStack, 31, 40, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (!this.insertItem(oldStack, 5, 31, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else if (!this.insertItem(oldStack, 4, 40, false)) {
                return ItemStack.EMPTY;
            }

            if (oldStack.isEmpty()) slot.setStack(ItemStack.EMPTY);
            else slot.markDirty();

            if (oldStack.getCount() == newStack.getCount()) return ItemStack.EMPTY;

            if (invSlot < 3) player.dropItem(slot.onTakeItem(player, oldStack), false);
            slot.onTakeItem(player, oldStack);
        }
        return newStack;
    }

    public boolean canUse(PlayerEntity player) { return this.inventory.canPlayerUse(player); }

    @Environment(EnvType.CLIENT)
    public int getCraftProgress() {
        int i = this.propertyDelegate.get(0);
        int j = this.propertyDelegate.get(1);
        return j != 0 && i != 0 ? i * 24 / j : 0;
    }

    public void close(PlayerEntity player) {
        super.close(player);
        this.inventory.onClose(player);
    }
}
