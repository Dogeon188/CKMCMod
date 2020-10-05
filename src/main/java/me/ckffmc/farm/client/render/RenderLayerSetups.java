package me.ckffmc.farm.client.render;

import me.ckffmc.farm.block.MyBlocks;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;

public class RenderLayerSetups {
    public static void renderLayerSetups() {
        // mipped with transparent pixel(s)
        RenderLayer cutoutMipped = RenderLayer.getCutoutMipped();
        // with transparent pixel(s)
        RenderLayer cutout = RenderLayer.getCutout();
        putBlock(MyBlocks.RICE, cutout);
        putBlock(MyBlocks.CORN, cutout);
        putBlock(MyBlocks.GARLIC, cutout);
        putBlock(MyBlocks.SPRING_ONION, cutout);
        putBlock(MyBlocks.GINGER, cutout);
        putBlock(MyBlocks.LETTUCE, cutout);
        putBlock(MyBlocks.SWEET_POTATO, cutout);
        // half-transparent
        RenderLayer translucent = RenderLayer.getTranslucent();
    }

    private static void putBlock(Block b, RenderLayer r) { BlockRenderLayerMap.INSTANCE.putBlock(b, r); }
}
