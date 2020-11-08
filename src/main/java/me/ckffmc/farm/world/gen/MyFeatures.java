package me.ckffmc.farm.world.gen;

import com.google.common.collect.ImmutableSet;
import me.ckffmc.farm.MainMod;
import me.ckffmc.farm.block.MyBlocks;
import me.ckffmc.farm.block.crop.TeaSaplingBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placer.SimpleBlockPlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;

public class MyFeatures {
    public static final ConfiguredFeature<?, ?> ORE_SALT = Feature.ORE
            .configure(new OreFeatureConfig(
                    OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
                    States.SALT, 9))
            .decorate(Decorator.RANGE.configure(
                    new RangeDecoratorConfig(0, 0, 64)))
            .spreadHorizontally().repeat(20);
    public static final ConfiguredFeature<?, ?> PATCH_TEA_SAPLING = Feature.RANDOM_PATCH
            .configure(Configs.TEA_SAPLING_CONFIG)
            .decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE);
    public static final ConfiguredFeature<?, ?> PATCH_WEED = Feature.RANDOM_PATCH
            .configure(Configs.WEED_CONFIG)
            .decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE);

    private static void register(String id, ConfiguredFeature<?, ?> feature) {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(MainMod.MOD_ID, id), feature);
    }

    public static void registerFeatures() {
        register("ore_salt", ORE_SALT);
        register("patch_tea_sapling", PATCH_TEA_SAPLING);
        register("patch_seedgrass", PATCH_WEED);
    }

    public static final class States {
        protected static final BlockState SALT = MyBlocks.SALT_BLOCK.getDefaultState();
        protected static final BlockState TEA_SAPLING = MyBlocks.TEA_SAPLING.getDefaultState()
                .with(TeaSaplingBlock.AGE, 3);
        protected static final BlockState GRASS_BLOCK = Blocks.GRASS_BLOCK.getDefaultState();
        protected static final BlockState SEEDGRASS = MyBlocks.WEED.getDefaultState();
    }

    public static final class Configs {
        public static final RandomPatchFeatureConfig TEA_SAPLING_CONFIG = new RandomPatchFeatureConfig.Builder(
                new SimpleBlockStateProvider(States.TEA_SAPLING), SimpleBlockPlacer.INSTANCE)
                .tries(32).whitelist(ImmutableSet.of(States.GRASS_BLOCK.getBlock()))
                .cannotProject().build();
        public static final RandomPatchFeatureConfig WEED_CONFIG = new RandomPatchFeatureConfig.Builder(
                new SimpleBlockStateProvider(States.SEEDGRASS), SimpleBlockPlacer.INSTANCE)
                .tries(48).whitelist(ImmutableSet.of(States.GRASS_BLOCK.getBlock()))
                .cannotProject().build();
    }
}
