package me.ckffmc.farm.mixin.world;

import me.ckffmc.farm.entity.MyEntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.DefaultBiomeCreator;
import net.minecraft.world.biome.SpawnSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(DefaultBiomeCreator.class)
public class DefaultBiomeCreatorMixin {
    @ModifyVariable(at = @At("STORE"), method = "createRiver", ordinal = 0)
    private static SpawnSettings.Builder addOysterToRiver(SpawnSettings.Builder builder) {
        return builder.spawn(SpawnGroup.WATER_AMBIENT, new SpawnSettings.SpawnEntry(MyEntityType.OYSTER, 5, 1, 5));
    }
}
