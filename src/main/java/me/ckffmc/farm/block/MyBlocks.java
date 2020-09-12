package me.ckffmc.farm.block;

import me.ckffmc.farm.MainMod;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MyBlocks {
    public static final Block DEBUG_BLOCK;

    private static Block register(String id, Block block) {
        return Registry.register(Registry.BLOCK, new Identifier(MainMod.MOD_ID, id), block);
    }

    static {
        DEBUG_BLOCK = register("debug_block", new Block(FabricBlockSettings.copyOf(Blocks.STONE)));
    }
}
