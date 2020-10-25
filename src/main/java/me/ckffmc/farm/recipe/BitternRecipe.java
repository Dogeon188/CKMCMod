package me.ckffmc.farm.recipe;

import me.ckffmc.farm.item.MyItems;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class BitternRecipe extends SpecialCraftingRecipe {
    public BitternRecipe(Identifier id) { super(id); }

    public boolean matches(CraftingInventory inv, World world) {
        int water_count = 0, salt_count = 0;
        for(int k = 0; k < inv.size(); ++k) {
            ItemStack itemStack = inv.getStack(k);
            if (!itemStack.isEmpty()) {
                if (itemStack.getItem() instanceof PotionItem && PotionUtil.getPotion(itemStack) == Potions.WATER) ++water_count;
                else {
                    if (!(itemStack.getItem().equals(MyItems.SALT))) return false;
                    ++salt_count;
                }
                if (salt_count > 1 || water_count > 1) return false;
            }
        }
        return water_count == 1 && salt_count == 1;
    }

    public ItemStack craft(CraftingInventory inv) { return new ItemStack(MyItems.BITTERN, 1); }

    public boolean fits(int width, int height) { return width >= 2 && height >= 2; }

    public RecipeSerializer<?> getSerializer() { return MyRecipeSerializer.BITTERN; }
}
