package me.ckffmc.farm.block;

import me.ckffmc.farm.MainMod;
import me.ckffmc.farm.block.crop.*;
import me.ckffmc.farm.item.MyItems;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MyBlocks {
    public static final Block LETTUCE = register("lettuce",
            new SoilEightBlock(() -> MyItems.LETTUCE_SEEDS, FabricBlockSettings.of(Material.PLANT)));
    public static final Block SWEET_POTATO = register("sweet_potato",
            new SoilFourBlock(() -> MyItems.SWEET_POTATO, FabricBlockSettings.of(Material.PLANT)));
    public static final Block GARLIC = register("garlic",
            new SoilFourBlock(() -> MyItems.GARLIC, FabricBlockSettings.of(Material.PLANT)));
    public static final Block SPRING_ONION = register("spring_onion",
            new SoilFourBlock(() -> MyItems.SPRING_ONION, FabricBlockSettings.of(Material.PLANT)));
    public static final Block RICE = register("rice",
            new SoilEightBlock(() -> MyItems.RICE, FabricBlockSettings.of(Material.PLANT)));
    public static final Block CORN = register("corn"
            , new TallCropBlock(() -> MyItems.CORN_SEEDS, FabricBlockSettings.of(Material.PLANT)));
    public static final Block GINGER = register("ginger", new SoilFourBlock(() -> MyItems.GINGER,
            FabricBlockSettings.of(Material.PLANT)));
    public static final Block SALT_BLOCK = register("salt_block", new Block(FabricBlockSettings.copyOf(Blocks.GLOWSTONE)));

    private static Block register(String id, Block block) {
        return Registry.register(Registry.BLOCK, new Identifier(MainMod.MOD_ID, id), block);
    }
}
