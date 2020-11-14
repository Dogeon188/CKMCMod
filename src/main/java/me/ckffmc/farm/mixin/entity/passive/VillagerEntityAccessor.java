package me.ckffmc.farm.mixin.entity.passive;

import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;
import java.util.Set;

@Mixin(VillagerEntity.class)
public interface VillagerEntityAccessor {
    @Accessor("ITEM_FOOD_VALUES")
    static void setItemFoodValues(Map<Item, Integer> map) { throw new AssertionError(); }

    @Accessor("GATHERABLE_ITEMS")
    static Set<Item> getGatherableItems() { throw new AssertionError(); }

    @Accessor("GATHERABLE_ITEMS")
    static void setGatherableItems(Set<Item> set) { throw new AssertionError(); }
}
