package me.ckffmc.farm.block;

import me.ckffmc.farm.MainMod;
import me.ckffmc.farm.block.crop.*;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MyBlocks {
    public static final Block LETTUCE;
    public static final Block SWEET_POTATO;
    public static final Block RICE;
    public static final Block CORN;

    private static Block register(String id, Block block) {
        return Registry.register(Registry.BLOCK, new Identifier(MainMod.MOD_ID, id), block);
    }

    static {
        LETTUCE = register("lettuce", new LettuceBlock(FabricBlockSettings.of(Material.PLANT)));
        SWEET_POTATO = register("sweet_potato", new SweetPotatoBlock(FabricBlockSettings.of(Material.PLANT)));
        RICE = register("rice", new RiceBlock(FabricBlockSettings.of(Material.PLANT)));
        CORN = register("corn", new CornBlock(FabricBlockSettings.of(Material.PLANT)));
//        DEBUG_BLOCK = register("debug_block", new Block(FabricBlockSettings.copyOf(Blocks.STONE)));
    }
}
