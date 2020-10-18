package me.ckffmc.farm.client.render.block.entity;

import me.ckffmc.farm.block.entity.MillstoneBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.item.ItemStack;

import java.util.Objects;

public class MillstoneBlockEntityRenderer extends BlockEntityRenderer<MillstoneBlockEntity> {
//    public static final SpriteIdentifier GRIND_TEXTURE = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEX, new Identifier(MainMod.MOD_ID, "textures/block/millstone/grind.png"));
//    private final ModelPart grind;

    public MillstoneBlockEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
        super(dispatcher);
//        this.grind = new ModelPart(16, 16, 0, 0).addCuboid(-5.0F, -3.0F, -5.0F, 10.0F, 6.0F, 10.0F);
//        this.grind.setPivot(8.0f, 8.0f, 8.0f);
    }

    public void render(MillstoneBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        int lightAbove = WorldRenderer.getLightmapCoordinates(Objects.requireNonNull(entity.getWorld()), entity.getPos().up());

        /*
        VertexConsumer vertexConsumer = GRIND_TEXTURE.getVertexConsumer(vertexConsumers, RenderLayer::getEntitySolid);
        if (entity.getWorld().getBlockState(entity.getPos()).get(MillstoneBlock.MILLING)) {
            System.out.println(entity.getCraftProgress());
            this.grind.yaw = entity.getCraftProgress() * 6.2831852f;
            grind.yaw = ((entity.getWorld().getTime() + tickDelta) / 16.0f) % 6.28f;
        }
        this.grind.render(matrices, vertexConsumer, light, overlay);
         */

        ItemStack stack = entity.getStack(0);
        matrices.push();
        double offset = Math.sin((entity.getWorld().getTime() + tickDelta) / 8.0) / 16.0;
        matrices.translate(0.5, 0.6875 + offset, 0.5);
        matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion((entity.getWorld().getTime() + tickDelta) * 4));
        MinecraftClient.getInstance().getItemRenderer().renderItem(stack, ModelTransformation.Mode.GROUND, lightAbove, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers);
        matrices.pop();
    }
}
