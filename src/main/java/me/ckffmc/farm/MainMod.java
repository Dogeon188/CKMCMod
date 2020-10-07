package me.ckffmc.farm;

import me.ckffmc.farm.entity.OysterEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class MainMod implements ModInitializer {

    public static final String MOD_ID = "ckfarm";
    private static final String[] toLoadClasses = {
            "me.ckffmc.farm.item.MyItems"
    };
    public static final EntityType<OysterEntity> OYSTER = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "oyster"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, OysterEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
    );

    @Override
    public void onInitialize() {
        try {
            for (String c : toLoadClasses) Class.forName(c).getDeclaredConstructor().newInstance();
        } catch (Exception e) { e.printStackTrace(); }
        FabricDefaultAttributeRegistry.register(OYSTER, OysterEntity.createMobAttributes());
//        System.out.println("CKFFMC farming mod is ready!");
    }
}
