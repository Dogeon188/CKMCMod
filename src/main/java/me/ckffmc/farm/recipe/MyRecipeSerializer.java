package me.ckffmc.farm.recipe;

import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.registry.Registry;

public class MyRecipeSerializer {
    public static RecipeSerializer<MillingRecipe> MILLING = register("milling", new MillingRecipe.Serializer());

    static <S extends RecipeSerializer<T>, T extends Recipe<?>> S register(String id, S serializer) {
        return Registry.register(Registry.RECIPE_SERIALIZER, id, serializer);
    }
}
