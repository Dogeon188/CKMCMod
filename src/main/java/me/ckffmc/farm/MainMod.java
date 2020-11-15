package me.ckffmc.farm;

import me.ckffmc.farm.block.MyBlocks;
import me.ckffmc.farm.entity.MyEntityType;
import me.ckffmc.farm.entity.VillagerModify;
import me.ckffmc.farm.item.MyItems;
import me.ckffmc.farm.loot.LootModify;
import me.ckffmc.farm.recipe.FermentingRecipeRegistry;
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
            MyEntityType.registerEntities();
            MyRecipeSerializer.registerRecipeSerializers();
            FermentingRecipeRegistry.registerFermentingRecipes();
            LootModify.modifyLoot();
            VillagerModify.modify();
        } catch (Exception e) { e.printStackTrace(); }
    }
}
