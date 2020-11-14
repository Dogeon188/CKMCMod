package me.ckffmc.farm.loot;

import me.ckffmc.farm.MainMod;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.loot.ConstantLootTableRange;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.LootTableEntry;
import net.minecraft.util.Identifier;

public class LootModify {
    private static final String[] GRASS_IDENTIFIERS = {"grass", "fern", "large_fern", "tall_grass"};
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
}
