package me.ckffmc.farm.client.render.entity;

import com.google.common.collect.ImmutableList;
import me.ckffmc.farm.entity.OysterEntity;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelUtil;
import net.minecraft.client.render.entity.model.CompositeEntityModel;
import net.minecraft.util.math.MathHelper;

public class OysterEntityModel extends CompositeEntityModel<OysterEntity> {
    private final ModelPart torso;
    private final ModelPart topShell;
    private final ModelPart bottomShell;

    public OysterEntityModel() {
        this.textureWidth = 32;
        this.textureHeight = 32;

        this.torso = new ModelPart(this, 0, 14)
                .addCuboid(-2.0F, 0.5F, -1.0F, 4.0F, 1.0F, 2.0F);
        this.torso.setPivot(0.0F, 22.0F, 0.0F);
        this.bottomShell = new ModelPart(this, 0, 7)
                .addCuboid(-4.0F, 1.0F, -3.0F, 8.0F, 1.0F, 6.0F);
        this.bottomShell.setPivot(0.0F, 22.0F, 0.0F);
        this.topShell = new ModelPart(this, 0, 0)
                .addCuboid(0.0F, 0.0F, -3.0F, 8.0F, 1.0F, 6.0F);
        this.topShell.setPivot(4.0F, 23.0F, 0.0F);
    }

    public Iterable<ModelPart> getParts() { return ImmutableList.of(this.torso, this.bottomShell, this.topShell); }

    public void setAngles(OysterEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.topShell.roll = ModelUtil.interpolateAngle(3.1415926F, 3.18F,
                MathHelper.sin(animationProgress / 16) + 1);
    }
}
