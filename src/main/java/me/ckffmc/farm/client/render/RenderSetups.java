package me.ckffmc.farm.client.render;

import me.ckffmc.farm.block.MyBlocks;
import me.ckffmc.farm.block.entity.MyBlockEntityType;
import me.ckffmc.farm.client.gui.screen.CookingTableScreen;
import me.ckffmc.farm.client.gui.screen.TunScreen;
import me.ckffmc.farm.client.gui.screen.MillstoneScreen;
import me.ckffmc.farm.client.render.block.entity.MillstoneBlockEntityRenderer;
import me.ckffmc.farm.client.render.entity.OysterEntityRenderer;
import me.ckffmc.farm.entity.MyEntityType;
import me.ckffmc.farm.screen.MyScreenHandlerType;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;

public class RenderSetups {
    public static void renderSetups() {
        renderLayerSetups();

        // entity render setups
        EntityRendererRegistry.INSTANCE.register(MyEntityType.OYSTER, (dsp, ctx) -> new OysterEntityRenderer(dsp));

        // ui render setups
        ScreenRegistry.register(MyScreenHandlerType.MILLSTONE_SCREEN_HANDLER, MillstoneScreen::new);
        ScreenRegistry.register(MyScreenHandlerType.COOKING_TABLE_SCREEN_HANDLER, CookingTableScreen::new);
        ScreenRegistry.register(MyScreenHandlerType.TUN_SCREEN_HANDLER, TunScreen::new);

        // block entity render setups
        BlockEntityRendererRegistry.INSTANCE.register(MyBlockEntityType.MILLSTONE_BLOCK_ENTITY, MillstoneBlockEntityRenderer::new);
    }

    private static void renderLayerSetups() {
        // mipped with transparent pixel(s)
        RenderLayer cutoutMipped = RenderLayer.getCutoutMipped();
        putBlock(MyBlocks.TEA_SAPLING, cutoutMipped);
        putBlock(MyBlocks.POTTED_TEA_SAPLING, cutoutMipped);
        // with transparent pixel(s)
        RenderLayer cutout = RenderLayer.getCutout();
        putBlock(MyBlocks.MANGO_LEAVES, cutout);
        putBlock(MyBlocks.RICE, cutout);
        putBlock(MyBlocks.CORN, cutout);
        putBlock(MyBlocks.GARLIC, cutout);
        putBlock(MyBlocks.SPRING_ONION, cutout);
        putBlock(MyBlocks.GINGER, cutout);
        putBlock(MyBlocks.LETTUCE, cutout);
        putBlock(MyBlocks.SWEET_POTATO, cutout);
        putBlock(MyBlocks.HANJI, cutout);
        putBlock(MyBlocks.SOYBEAN, cutout);
        putBlock(MyBlocks.MANGO_SAPLING, cutout);
        putBlock(MyBlocks.MANGO_TRAPDOOR, cutout);
        putBlock(MyBlocks.MANGO_DOOR, cutout);
        putBlock(MyBlocks.POTTED_MANGO_SAPLING, cutout);
        // half-transparent
        RenderLayer translucent = RenderLayer.getTranslucent();
    }

    private static void putBlock(Block b, RenderLayer r) { BlockRenderLayerMap.INSTANCE.putBlock(b, r); }
}
