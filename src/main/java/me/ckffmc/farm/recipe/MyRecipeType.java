package me.ckffmc.farm.recipe;

import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MyRecipeType {
    public static RecipeType<MillingRecipe> MILLING = register("milling");

    static <T extends Recipe<?>> RecipeType<T> register(final String string) {
        return Registry.register(Registry.RECIPE_TYPE, (Identifier)(new Identifier(string)), new RecipeType<T>() {
            public String toString() { return string; }
        });
    }
}
