package me.ckffmc.farm.client.render;

import me.ckffmc.farm.block.MyBlocks;
import me.ckffmc.farm.client.render.entity.renderer.OysterEntityRenderer;
import me.ckffmc.farm.entity.MyEntityType;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.EntityType;

public class RenderSetups {
    public static void renderSetups() {
        renderLayerSetups();
        entityRenderSetups();
    }

    private static void entityRenderSetups() {
        putEntity(MyEntityType.OYSTER, (dsp, ctx) -> new OysterEntityRenderer(dsp));
    }

    private static void putEntity(EntityType<?> e, EntityRendererRegistry.Factory f) {
        EntityRendererRegistry.INSTANCE.register(e, f);
    }

    private static void renderLayerSetups() {
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
