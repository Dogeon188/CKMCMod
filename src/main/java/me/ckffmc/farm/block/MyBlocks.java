package me.ckffmc.farm.block;

import me.ckffmc.farm.MainMod;
import me.ckffmc.farm.block.crop.CropHelper;
import me.ckffmc.farm.block.crop.TeaSaplingBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MyBlocks {
    public static final Block CORN = CropHelper.newTall("corn_seeds", CropHelper.newCropSettings());
    public static final Block GARLIC = CropHelper.newAge4("garlic",  CropHelper.newCropSettings());
    public static final Block GINGER = CropHelper.newAge4("ginger", CropHelper.newCropSettings());
    public static final Block SOYBEAN = CropHelper.newAge4("soybean", CropHelper.newCropSettings());
    public static final Block SPRING_ONION = CropHelper.newAge4("spring_onion_seeds",
            CropHelper.newCropSettings());
    public static final Block SWEET_POTATO = CropHelper.newAge4("sweet_potato", CropHelper.newCropSettings());
    public static final Block LETTUCE = CropHelper.newAge8("lettuce_seeds", CropHelper.newCropSettings());
    public static final Block RICE = CropHelper.newAge8("rice_seeds", CropHelper.newCropSettings());

    public static final Block TEA_SAPLING = new TeaSaplingBlock(FabricBlockSettings
            .of(Material.WOOD, MaterialColor.FOLIAGE).ticksRandomly().sounds(BlockSoundGroup.GRASS));

    public static final Block SALT_BLOCK = new Block(FabricBlockSettings.of(Material.GLASS, MaterialColor.WHITE)
            .strength(0.3F).sounds(BlockSoundGroup.GLASS));
    public static final Block MILLSTONE = new MillstoneBlock(FabricBlockSettings.of(Material.STONE)
            .requiresTool().strength(3.5F));
    public static final Block COOKING_TABLE = new CookingTableBlock(FabricBlockSettings.of(Material.STONE)
            .requiresTool().strength(3.5F));

    private static void register(String id, Block block) {
        Registry.register(Registry.BLOCK, new Identifier(MainMod.MOD_ID, id), block);
    }

    public static void registerBlocks() {
        register("corn", CORN);
        register("garlic", GARLIC);
        register("ginger", GINGER);
        register("soybean", SOYBEAN);
        register("spring_onion", SPRING_ONION);
        register("sweet_potato", SWEET_POTATO);
        register("lettuce", LETTUCE);
        register("rice", RICE);
        register("tea_sapling", TEA_SAPLING);
        register("salt_block", SALT_BLOCK);
        register("millstone", MILLSTONE);
        register("cooking_table", COOKING_TABLE);
    }
}
