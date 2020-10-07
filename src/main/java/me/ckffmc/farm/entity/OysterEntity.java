package me.ckffmc.farm.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.world.World;

public class OysterEntity extends WaterCreatureEntity {
    public OysterEntity(EntityType<? extends WaterCreatureEntity> entityType, World world) {
        super(entityType, world);
    }
}
