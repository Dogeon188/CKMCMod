package me.ckffmc.farm.recipe;

import com.google.common.collect.Lists;
import me.ckffmc.farm.item.MyItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;

import java.util.List;

public class FermentingRecipeRegistry {
    private static final List<Recipe> RECIPES = Lists.newArrayList();

    public static boolean isValidIngredient(ItemStack stack) {
        for (Recipe recipe : RECIPES) { if (recipe.input.test(stack)) return true; }
        return false;
   }

   public static boolean hasRecipe(ItemStack input) {
        for (Recipe recipe : RECIPES) { if (recipe.input.test(input)) return true; }
        return false;
   }

   public static int getFermentTime() { return 400; }

   public static ItemStack craft(ItemStack input) {
        if (!input.isEmpty()) {
            for (Recipe recipe : RECIPES) { if (recipe.input.test(input)) return new ItemStack(recipe.output); }
        }
        return input;
   }

    public static void registerFermentingRecipes() {
        register(MyItems.TEA_LEAVES, MyItems.BLACK_TEA_LEAVES, 200);
        register(MyItems.SOYBEAN, Items.STONE, 200);
        register(MyItems.TOFU, Items.STONE, 200);
        register(Items.EGG, Items.ACACIA_LOG, 400);
    }

    private static void register(Item input, Item output, int fermentTime) {
        RECIPES.add(new Recipe(Ingredient.ofItems(input), output, fermentTime));
    }

    static class Recipe {
        private final Ingredient input;
        private final Item output;
        private final int fermentTime;

        public Recipe(Ingredient input, Item output, int fermentTime) {
            this.input = input;
            this.output = output;
            this.fermentTime = fermentTime;
        }
    }
}
