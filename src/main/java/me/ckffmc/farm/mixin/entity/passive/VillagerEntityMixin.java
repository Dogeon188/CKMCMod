package me.ckffmc.farm.mixin.entity.passive;

import com.google.common.collect.ImmutableSet;
import me.ckffmc.farm.item.MyItems;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.Set;

@Mixin(VillagerEntity.class)
public class VillagerEntityMixin {
    // should also modify me.ckffmc.farm.mixin.entity.ai.brain.task.FarmerVillagerTaskMixin for it to work.
    @ModifyArg(method = "hasSeedToPlant", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/inventory/SimpleInventory;containsAny(Ljava/util/Set;)Z"))
    private Set<Item> addPlantableSeeds(Set<Item> set) {
        return ImmutableSet.<Item>builder()
                .addAll(set)
                .add(MyItems.SWEET_POTATO, MyItems.RICE_SEEDS, MyItems.SOYBEAN, MyItems.CORN_SEEDS
                , MyItems.GARLIC, MyItems.GINGER, MyItems.SPRING_ONION_SEEDS, MyItems.LETTUCE_SEEDS)
                .build();
    }
}
