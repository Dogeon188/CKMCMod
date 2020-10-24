package me.ckffmc.farm.recipe;

import me.ckffmc.farm.MainMod;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MyRecipeType {
    public static RecipeType<MillingRecipe> MILLING = register("milling");
    public static RecipeType<CookingRecipe> COOKING = register("cooking");

    static <T extends Recipe<?>> RecipeType<T> register(final String id) {
        return Registry.register(Registry.RECIPE_TYPE, new Identifier(MainMod.MOD_ID, id), new RecipeType<T>() {
            public String toString() { return id; }
        });
    }
}
