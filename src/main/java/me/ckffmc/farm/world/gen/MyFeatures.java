package me.ckffmc.farm.world.gen;

import me.ckffmc.farm.MainMod;
import me.ckffmc.farm.block.MyBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class MyFeatures {
    public static final ConfiguredFeature<?, ?> ORE_SALT = Feature.ORE
            .configure(new OreFeatureConfig(
                    OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
                    States.SALT, 9))
            .decorate(Decorator.RANGE.configure(
                    new RangeDecoratorConfig(0, 0, 64)))
            .spreadHorizontally().repeat(20);

    private static void register(String id, ConfiguredFeature<?, ?> feature) {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(MainMod.MOD_ID, id), feature);
    }

    public static void registerFeatures() {
        register("ore_salt", ORE_SALT);
    }

    public static final class States {
        protected static final BlockState SALT = MyBlocks.SALT_BLOCK.getDefaultState();
    }
}
