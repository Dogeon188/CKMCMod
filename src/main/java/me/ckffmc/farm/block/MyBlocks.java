package me.ckffmc.farm.block;

import me.ckffmc.farm.MainMod;
import me.ckffmc.farm.block.crop.CropHelper;
import me.ckffmc.farm.item.MyItems;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MyBlocks {
    public static final Block CORN = register("corn",
            CropHelper.newTallCropBlock("corn_seeds", FabricBlockSettings.of(Material.PLANT)));
    public static final Block GARLIC = register("garlic",
            CropHelper.newAge4CropBlock("garlic", FabricBlockSettings.of(Material.PLANT)));
    public static final Block GINGER = register("ginger",
            CropHelper.newAge4CropBlock("ginger", FabricBlockSettings.of(Material.PLANT)));
    public static final Block SOYBEAN = register("soybean",
            CropHelper.newAge4CropBlock("soybean", FabricBlockSettings.of(Material.PLANT)));
    public static final Block SPRING_ONION = register("spring_onion",
            CropHelper.newAge4CropBlock("spring_onion_seeds", FabricBlockSettings.of(Material.PLANT)));
    public static final Block SWEET_POTATO = register("sweet_potato",
            CropHelper.newAge4CropBlock("sweet_potato", FabricBlockSettings.of(Material.PLANT)));
    public static final Block LETTUCE = register("lettuce",
            CropHelper.newAge8CropBlock("lettuce_seeds", FabricBlockSettings.of(Material.PLANT)));
    public static final Block RICE = register("rice",
            CropHelper.newAge8CropBlock("rice_seeds", FabricBlockSettings.of(Material.PLANT)));

    public static final Block SALT_BLOCK = register("salt_block",
            new Block(FabricBlockSettings.copyOf(Blocks.GLOWSTONE)));
    public static final Block MILLSTONE = register("millstone",
            new MillstoneBlock(FabricBlockSettings.of(Material.STONE).requiresTool().strength(3.5F)));
    public static final Block COOKING_TABLE = register("cooking_table",
            new CookingTableBlock(FabricBlockSettings.of(Material.STONE).requiresTool().strength(3.5F)));

    private static Block register(String id, Block block) {
        return Registry.register(Registry.BLOCK, new Identifier(MainMod.MOD_ID, id), block);
    }
}
