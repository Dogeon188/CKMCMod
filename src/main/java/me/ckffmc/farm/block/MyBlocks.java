package me.ckffmc.farm.block;

import me.ckffmc.farm.MainMod;
import me.ckffmc.farm.block.crop.*;
import me.ckffmc.farm.item.MyItems;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MyBlocks {
    public static final Block LETTUCE;
    public static final Block SWEET_POTATO;
    public static final Block GARLIC;
    public static final Block SPRING_ONION;
    public static final Block RICE;
    public static final Block CORN;
    public static final Block GINGER;
    public static final Block SALT_BLOCK;
    public static final Block MILLSTONE;

    private static Block register(String id, Block block) {
        return Registry.register(Registry.BLOCK, new Identifier(MainMod.MOD_ID, id), block);
    }

    static {
        LETTUCE = register("lettuce", new SoilEightBlock(() -> MyItems.LETTUCE_SEEDS,
                FabricBlockSettings.of(Material.PLANT)));
        SWEET_POTATO = register("sweet_potato", new SoilFourBlock(() -> MyItems.SWEET_POTATO,
                FabricBlockSettings.of(Material.PLANT)));
        GARLIC = register("garlic", new SoilFourBlock(() -> MyItems.GARLIC,
                FabricBlockSettings.of(Material.PLANT)));
        SPRING_ONION = register("spring_onion", new SoilFourBlock(() -> MyItems.SPRING_ONION,
                FabricBlockSettings.of(Material.PLANT)));
        RICE = register("rice", new SoilEightBlock(() -> MyItems.RICE,
                FabricBlockSettings.of(Material.PLANT)));
        CORN = register("corn", new TallCropBlock(() -> MyItems.CORN_SEEDS,
                FabricBlockSettings.of(Material.PLANT)));
        GINGER = register("ginger", new SoilFourBlock(() -> MyItems.GINGER,
                FabricBlockSettings.of(Material.PLANT)));
        SALT_BLOCK = register("salt_block", new Block(FabricBlockSettings.copyOf(Blocks.GLOWSTONE)));
        MILLSTONE = register("millstone", new MillstoneBlock(FabricBlockSettings.copyOf(Blocks.STONE)));
    }
}
