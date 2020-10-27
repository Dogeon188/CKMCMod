package me.ckffmc.farm;

import me.ckffmc.farm.block.MyBlocks;
import me.ckffmc.farm.entity.MyEntityType;
import me.ckffmc.farm.item.MyItems;
import me.ckffmc.farm.recipe.MyRecipeSerializer;
import me.ckffmc.farm.world.gen.MyFeatures;
import net.fabricmc.api.ModInitializer;

public class MainMod implements ModInitializer {
    public static final String MOD_ID = "ckfarm";

    public void onInitialize() {
        try {
            MyItems.registerItems();
            MyBlocks.registerBlocks();
            MyFeatures.registerFeatures();
            MyEntityType.class.getDeclaredConstructor().newInstance();
            MyEntityType.registerEntityAttributes();
            MyRecipeSerializer.registerRecipeSerializers();
        } catch (Exception e) { e.printStackTrace(); }
    }
}
