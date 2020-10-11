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
    public static final EntityType<OysterEntity> OYSTER;

    private static <T extends Entity> EntityType<T> register(String id, FabricEntityTypeBuilder<T> type) {
        return Registry.register(Registry.ENTITY_TYPE, new Identifier(MainMod.MOD_ID, id), type.build());
    }

    public static void loadEntities() {
        FabricDefaultAttributeRegistry.register(MyEntityType.OYSTER, OysterEntity.createOysterAttributes());
    }

    static {
        OYSTER = register("oyster",
                FabricEntityTypeBuilder.create(SpawnGroup.WATER_AMBIENT, OysterEntity::new).dimensions(EntityDimensions.fixed(0.5f, 0.125f)));
    }
}
