package me.ckffmc.farm.world.gen;

import com.google.common.collect.ImmutableSet;
import me.ckffmc.farm.MainMod;
import me.ckffmc.farm.block.FruitLeavesBlock;
import me.ckffmc.farm.block.MyBlocks;
import me.ckffmc.farm.block.crop.TeaSaplingBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.UniformIntDistribution;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.placer.SimpleBlockPlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

public class MyFeatures {
    public static final ConfiguredFeature<?, ?> ORE_SALT = Feature.ORE
            .configure(Configs.SALT_CONFIG)
            .decorate(Decorator.RANGE.configure(
                    new RangeDecoratorConfig(0, 0, 64)))
            .spreadHorizontally().repeat(20);
    public static final ConfiguredFeature<?, ?> PATCH_TEA_SAPLING = Feature.RANDOM_PATCH
            .configure(Configs.TEA_SAPLING_CONFIG)
            .decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE);
    public static final ConfiguredFeature<TreeFeatureConfig, ?> MANGO = Feature.TREE
            .configure(Configs.MANGO_CONFIG);

    private static void register(String id, ConfiguredFeature<?, ?> feature) {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(MainMod.MOD_ID, id), feature);
    }

    public static void registerFeatures() {
        register("ore_salt", ORE_SALT);
        register("patch_tea_sapling", PATCH_TEA_SAPLING);
        register("mango", MANGO);
    }

    public static final class States {
        protected static final BlockState SALT = MyBlocks.SALT_BLOCK.getDefaultState();
        protected static final BlockState TEA_SAPLING = MyBlocks.TEA_SAPLING.getDefaultState()
                .with(TeaSaplingBlock.AGE, 3);
        protected static final BlockState GRASS_BLOCK = Blocks.GRASS_BLOCK.getDefaultState();
        protected static final BlockState MANGO_LOG = MyBlocks.MANGO_LOG.getDefaultState();
        protected static final BlockStateProvider MANGO_STATE_PROVIDER = new WeightedBlockStateProvider()
                .addState(MyBlocks.MANGO_LEAVES.getDefaultState(), 8)
                .addState(MyBlocks.MANGO_LEAVES.getDefaultState().with(FruitLeavesBlock.HAS_FRUIT, true), 1);
    }

    public static final class Configs {
        public static final OreFeatureConfig SALT_CONFIG = new OreFeatureConfig(
                OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
                States.SALT, 9);
        public static final RandomPatchFeatureConfig TEA_SAPLING_CONFIG = new RandomPatchFeatureConfig.Builder(
                new SimpleBlockStateProvider(States.TEA_SAPLING), SimpleBlockPlacer.INSTANCE)
                .tries(32).whitelist(ImmutableSet.of(States.GRASS_BLOCK.getBlock()))
                .cannotProject().build();
        public static final TreeFeatureConfig MANGO_CONFIG = new TreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(States.MANGO_LOG),
                States.MANGO_STATE_PROVIDER,
                new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                new StraightTrunkPlacer(4, 2, 0),
                new TwoLayersFeatureSize(1, 0, 1))
                .ignoreVines().build();
    }
}
