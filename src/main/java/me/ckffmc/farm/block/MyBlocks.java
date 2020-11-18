package me.ckffmc.farm.block;

import me.ckffmc.farm.MainMod;
import me.ckffmc.farm.block.crop.Age4CropBlock;
import me.ckffmc.farm.block.crop.Age8CropBlock;
import me.ckffmc.farm.block.crop.TallCropBlock;
import me.ckffmc.farm.block.crop.TeaSaplingBlock;
import me.ckffmc.farm.block.sapling.MangoSaplingBlock;
import me.ckffmc.farm.block.sapling.MangoSaplingGenerator;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemConvertible;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;

public class MyBlocks {
    public static final Block CORN = newTallCrop("corn_seeds", newCropSettings());
    public static final Block GARLIC = newAge4Crop("garlic",  newCropSettings());
    public static final Block GINGER = newAge4Crop("ginger", newCropSettings());
    public static final Block SOYBEAN = newAge4Crop("soybean", newCropSettings());
    public static final Block SPRING_ONION = newAge4Crop("spring_onion_seeds", newCropSettings());
    public static final Block SWEET_POTATO = newAge4Crop("sweet_potato", newCropSettings());
    public static final Block HANJI = newAge4Crop("hanji", newCropSettings());
    public static final Block LETTUCE = newAge8Crop("lettuce_seeds", newCropSettings());
    public static final Block RICE = newAge8Crop("rice_seeds", newCropSettings());

    public static final Block TEA_SAPLING = new TeaSaplingBlock(FabricBlockSettings
            .of(Material.PLANT, MaterialColor.FOLIAGE).ticksRandomly().sounds(BlockSoundGroup.WOOD));

    public static final Block MANGO_SAPLING = new MangoSaplingBlock(new MangoSaplingGenerator(),
            FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS));
    public static final Block MANGO_LOG = new PillarBlock(FabricBlockSettings.of(Material.WOOD,
            (state) -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? MaterialColor.ORANGE : MaterialColor.WOOD)
            .strength(2.0F).sounds(BlockSoundGroup.WOOD));
    public static final Block MANGO_PLANKS = new Block(FabricBlockSettings.of(Material.WOOD, MaterialColor.WOOD)
            .strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD));

    public static final Block MANGO_LEAVES = new FruitLeavesBlock(FabricBlockSettings.of(Material.LEAVES)
            .strength(0.2F).ticksRandomly().sounds(BlockSoundGroup.GRASS).nonOpaque()
            .allowsSpawning(Criteria::canSpawnOnLeaves).suffocates(Criteria::never).blockVision(Criteria::never));

    public static final Block SALT_BLOCK = new Block(FabricBlockSettings.of(Material.GLASS, MaterialColor.WHITE)
            .strength(0.3F).sounds(BlockSoundGroup.GLASS));
    public static final Block MILLSTONE = new MillstoneBlock(FabricBlockSettings.of(Material.STONE)
            .requiresTool().strength(3.5F));
    public static final Block COOKING_TABLE = new CookingTableBlock(FabricBlockSettings.of(Material.STONE)
            .requiresTool().strength(3.5F));
    public static final Block TUN =
            new TunBlock(FabricBlockSettings.of(Material.WOOD).strength(2.5F).sounds(BlockSoundGroup.WOOD));

    private static void register(String id, Block block) {
        Registry.register(Registry.BLOCK, new Identifier(MainMod.MOD_ID, id), block);
    }

    public static void registerBlocks() {
        register("corn", CORN);
        register("garlic", GARLIC);
        register("ginger", GINGER);
        register("soybean", SOYBEAN);
        register("spring_onion", SPRING_ONION);
        register("sweet_potato", SWEET_POTATO);
        register("hanji", HANJI);
        register("lettuce", LETTUCE);
        register("rice", RICE);
        register("tea_sapling", TEA_SAPLING);
        register("mango_sapling", MANGO_SAPLING);
        register("mango_log", MANGO_LOG);
        register("mango_planks", MANGO_PLANKS);
        register("mango_leaves", MANGO_LEAVES);
        register("salt_block", SALT_BLOCK);
        register("millstone", MILLSTONE);
        register("cooking_table", COOKING_TABLE);
        register("tun", TUN);
    }

    private static FabricBlockSettings newCropSettings() {
        return FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP);
    }

    private static Age8CropBlock newAge8Crop(String seeds_item, FabricBlockSettings settings) {
        return new Age8CropBlock(settings) {
            protected ItemConvertible getSeedsItem() {
                return Registry.ITEM.get(new Identifier(MainMod.MOD_ID, seeds_item));
            }
        };
    }

    private static Age4CropBlock newAge4Crop(String seeds_item, FabricBlockSettings settings) {
        return new Age4CropBlock(settings) {
            protected ItemConvertible getSeedsItem() {
                return Registry.ITEM.get(new Identifier(MainMod.MOD_ID, seeds_item));
            }
        };
    }

    private static TallCropBlock newTallCrop(String seeds_item, FabricBlockSettings settings) {
        return new TallCropBlock(settings) {
            protected ItemConvertible getSeedsItem() {
                return Registry.ITEM.get(new Identifier(MainMod.MOD_ID, seeds_item));
            }
        };
    }

    static class Criteria {
        private static Boolean canSpawnOnLeaves(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
            return type == EntityType.OCELOT || type == EntityType.PARROT;
        }

        private static boolean never(BlockState state, BlockView world, BlockPos pos) { return false; }
    }
}
