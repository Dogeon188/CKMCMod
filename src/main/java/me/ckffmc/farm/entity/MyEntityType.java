package me.ckffmc.farm.entity;

import me.ckffmc.farm.MainMod;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MyEntityType {
    public static final EntityType<OysterEntity> OYSTER = FabricEntityTypeBuilder
            .create(SpawnGroup.WATER_AMBIENT, OysterEntity::new)
            .dimensions(EntityDimensions.fixed(0.5f, 0.125f)).build();

    private static <T extends Entity> EntityType<T> register(String id, EntityType<T> type) {
        return Registry.register(Registry.ENTITY_TYPE, new Identifier(MainMod.MOD_ID, id), type);
    }

    public static void registerEntityAttributes() {
        register("oyster", OYSTER);
        FabricDefaultAttributeRegistry.register(OYSTER, OysterEntity.createOysterAttributes());
    }
}
