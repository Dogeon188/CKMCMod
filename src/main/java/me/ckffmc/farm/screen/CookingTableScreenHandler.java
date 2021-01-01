package me.ckffmc.farm.screen;

import me.ckffmc.farm.block.entity.CookingTableBlockEntity;
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

public class CookingTableScreenHandler extends ScreenHandler {
    private final PropertyDelegate propertyDelegate;
    private final Inventory inventory;
    protected final CraftingResultInventory output = new CraftingResultInventory();

    public CookingTableScreenHandler(int syncId,  PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(5), new ArrayPropertyDelegate(2));
    }

    public CookingTableScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(MyScreenHandlerType.COOKING_TABLE_SCREEN_HANDLER, syncId);
        checkSize(inventory, 5);
        checkDataCount(propertyDelegate, 2);
        this.inventory = inventory;
        this.propertyDelegate = propertyDelegate;
        this.addProperties(propertyDelegate);

        this.addSlot(new Slot(inventory, 0, 35, 27));
        this.addSlot(new Slot(inventory, 1, 53, 27));
        this.addSlot(new Slot(inventory, 2, 35, 45));
        this.addSlot(new Slot(inventory, 3, 53, 45));
        this.addSlot(new Slot(inventory, 4, 122, 36) {
            public boolean canInsert(ItemStack stack) { return false; }

            public ItemStack onTakeItem(PlayerEntity player, ItemStack stack) {
                stack.onCraft(player.world, player, stack.getCount());
                CookingTableScreenHandler.this.output.unlockLastRecipe(player);
                if (!player.world.isClient && this.inventory instanceof CookingTableBlockEntity)
                    ((CookingTableBlockEntity)this.inventory).dropExperience(player);
                return super.onTakeItem(player, stack);
            }
        });

        int m, l;
        for (m = 0; m < 3; ++m)
            for (l = 0; l < 9; ++l)
                this.addSlot(new Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 84 + m * 18));
        for (m = 0; m < 9; ++m) this.addSlot(new Slot(playerInventory, m, 8 + m * 18, 142));
    }

    public ItemStack transferSlot(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack oldStack = slot.getStack();
            newStack = oldStack.copy();
            if (invSlot == 4) {
                if (!this.insertItem(oldStack, 5, 41, true))
                    return ItemStack.EMPTY;
                slot.onStackChanged(oldStack, newStack);
            } else if (invSlot >= 5 && invSlot < 41) {
                if (!this.insertItem(oldStack, 0, 4, false)) {
                    if (invSlot < 32) {
                        if (!this.insertItem(oldStack, 32, 41, false))
                            return ItemStack.EMPTY;
                    } else if (!this.insertItem(oldStack, 5, 32, false))
                        return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(oldStack, 5, 41, false))
                return ItemStack.EMPTY;

            if (oldStack.isEmpty()) slot.setStack(ItemStack.EMPTY);
            else slot.markDirty();

            if (oldStack.getCount() == newStack.getCount()) return ItemStack.EMPTY;

            if (invSlot == 4) player.dropItem(slot.onTakeItem(player, oldStack), false);
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
