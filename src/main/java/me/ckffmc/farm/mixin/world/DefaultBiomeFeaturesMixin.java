package me.ckffmc.farm.mixin.world;

import me.ckffmc.farm.entity.MyEntityType;
import me.ckffmc.farm.world.gen.MyFeatures;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DefaultBiomeFeatures.class)
public class DefaultBiomeFeaturesMixin {
    @Inject(at = @At("HEAD"), method = "addMineables")
    private static void addOres(GenerationSettings.Builder builder, CallbackInfo ci) {
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, MyFeatures.ORE_SALT);
    }

    @ModifyVariable(at = @At("HEAD"), method = "addOceanMobs", ordinal = 0)
    private static SpawnSettings.Builder addOyster(SpawnSettings.Builder builder) {
        return builder.spawn(SpawnGroup.WATER_AMBIENT, new SpawnSettings.SpawnEntry(MyEntityType.OYSTER, 5, 1, 5));
    }
}