package me.ckffmc.farm.recipe;

import me.ckffmc.farm.item.MyItems;
import me.ckffmc.farm.item.TsuabingItem;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.ByteArrayTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class TsuabingRecipe extends SpecialCraftingRecipe {
    private static final Ingredient SHAVED_ICE;
    private static final Ingredient SYRUP;
    private static final Ingredient TOPPINGS;

    public TsuabingRecipe(Identifier id) { super(id); }

    public boolean matches(CraftingInventory inv, World world) {
        boolean shavedIce = false;
        short syrup = 0;
        short toppings = 0;

        for (int i = 0; i < inv.size(); i++) {
            ItemStack stack = inv.getStack(i);
            if (!stack.isEmpty()) {
                if (SHAVED_ICE.test(stack)) {
                    if (shavedIce) return false;
                    shavedIce = true;
                } else if (SYRUP.test(stack)) {
                    if (syrup++ > 2) return false;
                } else if (TOPPINGS.test(stack)) {
                    if (toppings++ > 4) return false;
                } else return false;
            }
        }
        return shavedIce && (syrup > 0);
    }

    public ItemStack craft(CraftingInventory inv) {
        ItemStack stack = new ItemStack(MyItems.TSUABING);
        CompoundTag tag = stack.getOrCreateSubTag("Tsuabing");
        List<Byte> syrup = new ArrayList<>();
        List<Byte> toppings = new ArrayList<>();

        for (int i = 0; i < inv.size(); i++) {
            ItemStack ingredient = inv.getStack(i);
            if (!ingredient.isEmpty()) {
                if (SYRUP.test(ingredient))
                    syrup.add(TsuabingItem.SYRUPS.get(ingredient.getItem()));
                else if (TOPPINGS.test(ingredient))
                    toppings.add(TsuabingItem.TOPPINGS.get(ingredient.getItem()));
            }
        }
        if (!syrup.isEmpty()) tag.put("Syrup", new ByteArrayTag(syrup));
        if (!toppings.isEmpty()) tag.put("Toppings", new ByteArrayTag(toppings));
        return stack;
    }

    public boolean fits(int width, int height) { return width >= 2 && height >= 2; }

    public RecipeSerializer<?> getSerializer() { return MyRecipeSerializer.TSUABING; }

    static {
        SHAVED_ICE = Ingredient.ofItems(MyItems.SHAVED_ICE);
        SYRUP = Ingredient.ofItems(TsuabingItem.SYRUPS.keySet().toArray(new Item[0]));
        TOPPINGS = Ingredient.ofItems(TsuabingItem.TOPPINGS.keySet().toArray(new Item[0]));
    }
}
