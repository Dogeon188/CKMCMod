package me.ckffmc.farm.client.render.entity;

import me.ckffmc.farm.entity.OysterEntity;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class OysterEntityModel extends EntityModel<OysterEntity> {
    private final ModelPart base;

    public OysterEntityModel() {
        this.textureHeight = 16;
        this.textureWidth = 16;
        base = new ModelPart(this, 0, 0);
        base.addCuboid(-6, -6, -6, 12, 12, 12);
    }

    @Override
    public void setAngles(OysterEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) { }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        matrices.translate(0, 1.125, 0);
        base.render(matrices, vertices, light, overlay);
    }
}
