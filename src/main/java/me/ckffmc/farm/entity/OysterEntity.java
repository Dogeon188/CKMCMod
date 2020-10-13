package me.ckffmc.farm.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.world.World;

public class OysterEntity extends WaterCreatureEntity {

    public OysterEntity(EntityType<? extends WaterCreatureEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 5;
    }

    public static DefaultAttributeContainer.Builder createOysterAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 5.0f).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.03f);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(8, new LookAroundGoal(this));
    }
}
