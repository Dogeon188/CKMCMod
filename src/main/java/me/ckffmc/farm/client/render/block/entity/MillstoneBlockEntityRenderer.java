package me.ckffmc.farm.client.render.block.entity;

import me.ckffmc.farm.MainMod;
import me.ckffmc.farm.block.entity.MillstoneBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.Identifier;

public class MillstoneBlockEntityRenderer extends BlockEntityRenderer<MillstoneBlockEntity> {
    public static final SpriteIdentifier GRIND_TEXTURE = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEX,
            new Identifier(MainMod.MOD_ID, "block/millstone/grind"));
    private final ModelPart grind;

    public MillstoneBlockEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
        super(dispatcher);
        this.grind = new ModelPart(64, 16, 0, 0).addCuboid(-5.0F, 5.0F, -5.0F, 10.0F, 6.0F, 10.0F);
    }

    public void render(MillstoneBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {matrices.push();
        matrices.translate(0.5, 0, 0.5);
        if (entity.isCrafting()) { entity.grind_rotation += tickDelta; }
        matrices.multiply(Vector3f.NEGATIVE_Y.getDegreesQuaternion((entity.grind_rotation) * 2));
        this.grind.render(matrices, GRIND_TEXTURE.getVertexConsumer(vertexConsumers, RenderLayer::getEntitySolid), light, overlay);
        matrices.pop();

        // render milling item
        matrices.push();
        double offset = Math.sin((entity.getWorld().getTime() + tickDelta) / 8.0) / 16.0;
        matrices.translate(0.5, 0.6875 + offset, 0.5);
        matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion((entity.getWorld().getTime() + tickDelta) * 4));
        MinecraftClient.getInstance().getItemRenderer().renderItem(entity.getStack(0), ModelTransformation.Mode.GROUND, light, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers);
        matrices.pop();
    }
}
