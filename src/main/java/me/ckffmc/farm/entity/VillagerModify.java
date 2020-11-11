package me.ckffmc.farm.entity;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import me.ckffmc.farm.item.MyItems;
import me.ckffmc.farm.mixin.entity.FarmerWorkTaskAccessor;
import me.ckffmc.farm.mixin.entity.VillagerEntityAccessor;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.Item;

public class VillagerModify {
    public static void modify() {
        // compost seeds task
        FarmerWorkTaskAccessor.setCompostables(ImmutableList.<Item>builder()
                .addAll(FarmerWorkTaskAccessor.getCompostables())
                .add(MyItems.CORN_SEEDS, MyItems.RICE_SEEDS, MyItems.SPRING_ONION_SEEDS, MyItems.LETTUCE_SEEDS)
                .build());

        // villager entity fields
        VillagerEntityAccessor.setItemFoodValues(ImmutableMap.<Item, Integer>builder()
                .putAll(VillagerEntity.ITEM_FOOD_VALUES)
                .put(MyItems.SWEET_POTATO, 1)
                .put(MyItems.SOYBEAN, 1)
                .put(MyItems.CORN, 1)
                .put(MyItems.RICE, 1)
                .put(MyItems.GARLIC, 1)
                .put(MyItems.GINGER, 1)
                .put(MyItems.SPRING_ONION, 1)
                .put(MyItems.LETTUCE, 1)
                .build());

        VillagerEntityAccessor.setGatherableItems(ImmutableSet.<Item>builder()
                .addAll(VillagerEntityAccessor.getGatherableItems())
                .add(MyItems.SWEET_POTATO, MyItems.SOYBEAN, MyItems.CORN, MyItems.CORN_SEEDS,
                        MyItems.RICE, MyItems.RICE_SEEDS, MyItems.LETTUCE_SEEDS, MyItems.LETTUCE,
                        MyItems.SPRING_ONION_SEEDS, MyItems.SPRING_ONION, MyItems.GINGER, MyItems.GARLIC)
                .build());
    }
}
