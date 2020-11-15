package me.ckffmc.farm.screen;

import me.ckffmc.farm.recipe.FermentingRecipeRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
        checkDataCount(propertyDelegate, 4);
        this.inventory = inventory;
        this.propertyDelegate = propertyDelegate;
        this.addProperties(propertyDelegate);

        this.addSlot(new FermentingSlot(inventory, 0, 102, 42));
        this.addSlot(new FermentingSlot(inventory, 1, 79, 49));
        this.addSlot(new FermentingSlot(inventory, 2, 56, 42));
        this.addSlot(new FuelSlot(inventory, 3, 40, 18)); // fuel slot

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
            } else if (invSlot >= 4 && invSlot < 40) {
                if (!this.insertItemToFermentedSlots(oldStack)) {
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

    protected boolean insertItemToFermentedSlots(ItemStack stack) {
        boolean bl = false;
        int i = 3;
        Slot targetSlot;

        while (!stack.isEmpty()) {
            if (i < 0) break;
            targetSlot = this.slots.get(i);
            if (targetSlot.getStack().isEmpty() && targetSlot.canInsert(stack)) {
                targetSlot.setStack(stack.split(Math.min(stack.getCount(), targetSlot.getMaxItemCount())));
                targetSlot.markDirty();
                bl = true;
            }
            --i;
        }

        return bl;
    }

    public boolean canUse(PlayerEntity player) { return this.inventory.canPlayerUse(player); }

    @Environment(EnvType.CLIENT)
    public int getFuel() { return this.propertyDelegate.get(3);}

    @Environment(EnvType.CLIENT)
    public int getFermentProgress(int index) {
        int total = FermentingRecipeRegistry.getFermentTime();
        int part = total - this.propertyDelegate.get(index);
        return (part != 0 && part != total && total != 0) ? part * 20 / total : 0;
    }

    public void close(PlayerEntity player) {
        super.close(player);
        this.inventory.onClose(player);
    }

    static class FuelSlot extends Slot {
        public FuelSlot(Inventory inventory, int i, int j, int k) { super(inventory, i, j, k); }

        public boolean canInsert(ItemStack stack) { return matches(stack); }

        public static boolean matches(ItemStack stack) {
            Item item = stack.getItem();
            return item == Items.BROWN_MUSHROOM || item == Items.RED_MUSHROOM;
        }

        public int getMaxItemCount() { return 64; }
    }

    static class FermentingSlot extends Slot {
        public FermentingSlot(Inventory inventory, int i, int j, int k) { super(inventory, i, j, k); }

        public boolean canInsert(ItemStack stack) { return matches(stack); }

        public static boolean matches(ItemStack stack) { return FermentingRecipeRegistry.isValidIngredient(stack); }

        public int getMaxItemCount() { return 1; }
    }
}
