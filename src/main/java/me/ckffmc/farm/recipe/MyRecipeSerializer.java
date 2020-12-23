package me.ckffmc.farm.recipe;

import me.ckffmc.farm.MainMod;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MyRecipeSerializer {
    public static final RecipeSerializer<MillingRecipe> MILLING = new MillingRecipe.Serializer();
    public static final RecipeSerializer<CookingRecipe> COOKING = new CookingRecipe.Serializer();
    public static final SpecialRecipeSerializer<BitternRecipe> BITTERN = new SpecialRecipeSerializer<>(BitternRecipe::new);
    public static final SpecialRecipeSerializer<TsuabingRecipe> TSUABING = new SpecialRecipeSerializer<>(TsuabingRecipe::new);

    static <S extends RecipeSerializer<T>, T extends Recipe<?>> void register(String id, S serializer) {
        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(MainMod.MOD_ID, id), serializer);
    }

    public static void registerRecipeSerializers() {
        register("milling", MILLING);
        register("cooking", COOKING);
        register("bittern", BITTERN);
        register("tsuabing", TSUABING);
    }
}
