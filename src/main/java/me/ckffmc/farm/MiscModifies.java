package me.ckffmc.farm;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import me.ckffmc.farm.block.MyBlocks;
import me.ckffmc.farm.item.MyItems;
import me.ckffmc.farm.mixin.entity.ai.brain.task.FarmerWorkTaskAccessor;
import me.ckffmc.farm.mixin.entity.passive.VillagerEntityAccessor;
import me.ckffmc.farm.mixin.item.AxeItemAccessor;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.Item;
import net.minecraft.loot.ConstantLootTableRange;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.LootTableEntry;
import net.minecraft.util.Identifier;

public class MiscModifies {
    private static final String[] GRASS_IDENTIFIERS = {"grass", "fern", "large_fern", "tall_grass"};

    public static void modify() {
        modifyLoot();
        modifyVillager();
        addStrippedBlocks();
    }

    public static void modifyLoot() {
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
            for (String i : GRASS_IDENTIFIERS) {
                if (id.equals(new Identifier("blocks/" + i))) {
                    LootPool pool = FabricLootPoolBuilder.builder()
                            .rolls(ConstantLootTableRange.create(1))
                            .with(LootTableEntry.builder(
                                    new Identifier(MainMod.MOD_ID, "blocks/minecraft/" + i)))
                            .build();
                    supplier.withPool(pool);
                    break;
                }
            }
        });
    }

    private static void modifyVillager() {
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

    public static void addStrippedBlocks() {
        AxeItemAccessor.setStrippedBlocks(ImmutableMap.<Block, Block>builder()
                .putAll(AxeItemAccessor.getStrippedBlocks())
                .put(MyBlocks.MANGO_LOG, MyBlocks.STRIPPED_MANGO_LOG)
                .put(MyBlocks.MANGO_WOOD, MyBlocks.STRIPPED_MANGO_WOOD)
                .build());
    }
}
