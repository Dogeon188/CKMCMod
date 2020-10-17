package me.ckffmc.farm;

import me.ckffmc.farm.entity.MyEntityType;
import me.ckffmc.farm.item.MyItems;
import me.ckffmc.farm.recipe.MillingRecipe;
import me.ckffmc.farm.recipe.MyRecipeSerializer;
import me.ckffmc.farm.recipe.MyRecipeType;
import net.fabricmc.api.ModInitializer;


public class MainMod implements ModInitializer {

    public static final String MOD_ID = "ckfarm";

    @Override
    public void onInitialize() {
        try {
            MyItems.class.getDeclaredConstructor().newInstance();
            MyRecipeType.class.getDeclaredConstructor().newInstance();
            MyRecipeSerializer.class.getDeclaredConstructor().newInstance();
        } catch (Exception e) { e.printStackTrace(); }
        MyEntityType.load();
//        System.out.println("CKFFMC farming mod is ready!");
    }
}
