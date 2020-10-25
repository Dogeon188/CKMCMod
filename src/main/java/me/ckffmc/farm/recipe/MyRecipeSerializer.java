package me.ckffmc.farm.recipe;

import me.ckffmc.farm.MainMod;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MyRecipeSerializer {
    public static final RecipeSerializer<MillingRecipe> MILLING = register("milling", new MillingRecipe.Serializer());
    public static final RecipeSerializer<CookingRecipe> COOKING = register("cooking", new CookingRecipe.Serializer());

    static <S extends RecipeSerializer<T>, T extends Recipe<?>> S register(String id, S serializer) {
        return Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(MainMod.MOD_ID, id), serializer);
    }
}
