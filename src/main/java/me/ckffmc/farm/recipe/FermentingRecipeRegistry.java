package me.ckffmc.farm.recipe;

import me.ckffmc.farm.item.MyItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class FermentingRecipeRegistry {
    private static final List<Recipe> RECIPES = new ArrayList<>();

    public static boolean isValidIngredient(ItemStack stack) {
        for (Recipe recipe : RECIPES) { if (recipe.input.test(stack)) return true; }
        return false;
   }

    public static int getFermentTime() { return 400; }

   public static ItemStack craft(ItemStack input) {
        if (!input.isEmpty())
            for (Recipe recipe : RECIPES)
                if (recipe.input.test(input)) return new ItemStack(recipe.output);
        return input;
   }

    public static void registerFermentingRecipes() {
        register(MyItems.SOYBEAN, MyItems.SOY_SAUCE);
        register(MyItems.TOFU, MyItems.STINKY_TOFU);
        register(Items.EGG, MyItems.PRESERVED_EGG);
    }

    private static void register(Item input, Item output) {
        RECIPES.add(new Recipe(Ingredient.ofItems(input), output));
    }

    static class Recipe {
        private final Ingredient input;
        private final Item output;

        public Recipe(Ingredient input, Item output) {
            this.input = input;
            this.output = output;
        }
    }
}
