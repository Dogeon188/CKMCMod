package me.ckffmc.farm.client.render.entity.model;

import com.google.common.collect.ImmutableList;
import me.ckffmc.farm.entity.OysterEntity;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelUtil;
import net.minecraft.client.render.entity.model.CompositeEntityModel;

public class OysterEntityModel extends CompositeEntityModel<OysterEntity> {
    private final ModelPart torso;
    private final ModelPart topShell;
    private final ModelPart bottomShell;
    private float openAnimation;

    public OysterEntityModel() {
        this.textureWidth = 32;
        this.textureHeight = 32;

        this.torso = new ModelPart(this, 0, 14);
        this.torso.addCuboid(-2.0F, 0.5F, -1.0F, 4.0F, 1.0F, 2.0F, 0.0F);
        this.torso.setPivot(0.0F, 22.0F, 0.0F);
        this.bottomShell = new ModelPart(this, 0, 7);
        this.bottomShell.addCuboid(-4.0F, 1.0F, -3.0F, 8.0F, 1.0F, 6.0F, 0.0F);
        this.bottomShell.setPivot(0.0F, 22.0F, 0.0F);
        this.bottomShell.yaw = 3.1415926F;
        this.topShell = new ModelPart(this, 0, 0);
        this.topShell.addCuboid(-4.0F, 0.0F, 0.0F, 8.0F, 1.0F, 6.0F, 0.0F);
        this.topShell.setPivot(0.0F, 23.0F, 3.0F);
    }

    @Override
    public Iterable<ModelPart> getParts() { return ImmutableList.of(this.torso, this.bottomShell, this.topShell); }

    @Override
    public void animateModel(OysterEntity entity, float f, float g, float tickDelta) {
        this.openAnimation = entity.getOpenProgress(tickDelta);
        super.animateModel(entity, f, g, tickDelta);
    }

    @Override
    public void setAngles(OysterEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.topShell.pitch = ModelUtil.interpolateAngle(2.1415F, 3.1415F, this.openAnimation);
    }
}
