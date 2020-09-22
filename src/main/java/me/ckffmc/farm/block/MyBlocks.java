package me.ckffmc.farm.block;

import me.ckffmc.farm.MainMod;
import me.ckffmc.farm.block.crop.EarthCropBlock;
import me.ckffmc.farm.block.crop.EarthCropSmallBlock;
import me.ckffmc.farm.item.MyItems;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MyBlocks {
    public static final Block LETTUCE;
    public static final Block SWEET_POTATO;


    private static Block register(String id, Block block) {
        return Registry.register(Registry.BLOCK, new Identifier(MainMod.MOD_ID, id), block);
    }

    static {
        LETTUCE = register("lettuce", new EarthCropBlock(MyItems.LETTUCE_SEEDS, AbstractBlock.Settings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP)));
        SWEET_POTATO = register("sweet_potato", new EarthCropSmallBlock(MyItems.SWEET_POTATO, AbstractBlock.Settings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP)));
//        DEBUG_BLOCK = register("debug_block", new Block(FabricBlockSettings.copyOf(Blocks.STONE)));
    }
}
