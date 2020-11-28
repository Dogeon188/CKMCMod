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
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;

public class MyBlocks {
    public static final Block CORN = new TallCropBlock("corn_seeds", newCropSettings());
    public static final Block GARLIC = new Age4CropBlock("garlic",  newCropSettings());
    public static final Block GINGER = new Age4CropBlock("ginger", newCropSettings());
    public static final Block SOYBEAN = new Age4CropBlock("soybean", newCropSettings());
    public static final Block SPRING_ONION = new Age4CropBlock("spring_onion_seeds", newCropSettings());
    public static final Block SWEET_POTATO = new Age4CropBlock("sweet_potato", newCropSettings());
    public static final Block HANJI = new Age4CropBlock("hanji", newCropSettings());
    public static final Block LETTUCE = new Age8CropBlock("lettuce_seeds", newCropSettings());
    public static final Block RICE = new Age8CropBlock("rice_seeds", newCropSettings());

    public static final Block TEA_SAPLING = new TeaSaplingBlock(FabricBlockSettings
            .of(Material.PLANT, MaterialColor.FOLIAGE).ticksRandomly().sounds(BlockSoundGroup.WOOD));

    public static final Block MANGO_SAPLING = new MangoSaplingBlock(new MangoSaplingGenerator(),
            FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS));
    public static final Block MANGO_LOG = new PillarBlock(FabricBlockSettings.of(Material.WOOD,
            (state) -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? MaterialColor.ORANGE_TERRACOTTA : MaterialColor.WOOD)
            .strength(2.0F).sounds(BlockSoundGroup.WOOD));
    public static final Block STRIPPED_MANGO_LOG = new PillarBlock(FabricBlockSettings.of(Material.WOOD,
            (state) -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? MaterialColor.ORANGE_TERRACOTTA :
                    MaterialColor.ORANGE_TERRACOTTA)
            .strength(2.0F).sounds(BlockSoundGroup.WOOD));
    public static final Block MANGO_WOOD = new PillarBlock(FabricBlockSettings.of(Material.WOOD,
            MaterialColor.WOOD).strength(2.0F).sounds(BlockSoundGroup.WOOD));
    public static final Block STRIPPED_MANGO_WOOD = new PillarBlock(FabricBlockSettings.of(Material.WOOD,
            MaterialColor.ORANGE_TERRACOTTA).strength(2.0F).sounds(BlockSoundGroup.WOOD));

    public static final Block MANGO_PLANKS = new Block(FabricBlockSettings.of(Material.WOOD,
            MaterialColor.ORANGE_TERRACOTTA).strength(2.0F, 3.0F)
            .sounds(BlockSoundGroup.WOOD));
    public static final Block MANGO_STAIRS = new MyStairsBlock(MANGO_PLANKS.getDefaultState(),
            FabricBlockSettings.copy(MANGO_PLANKS));
    public static final Block MANGO_SLAB = new SlabBlock(FabricBlockSettings.copy(MANGO_PLANKS));

    public static final Block MANGO_BUTTON = new MyWoodenButtonBlock(FabricBlockSettings.of(Material.SUPPORTED)
            .noCollision().strength(0.5F).sounds(BlockSoundGroup.WOOD));
    public static final Block MANGO_PRESSURE_PLATE = new MyPressurePlateBlock(
            PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.of(Material.WOOD,
            MANGO_PLANKS.getDefaultMaterialColor()).noCollision().strength(0.5F).sounds(BlockSoundGroup.WOOD));
    public static final Block MANGO_TRAPDOOR = new MyTrapdoorBlock(FabricBlockSettings.copy(MANGO_PLANKS)
            .strength(3.0F).nonOpaque().allowsSpawning(Criteria::never));
    public static final Block MANGO_FENCE_GATE = new FenceGateBlock(FabricBlockSettings.copy(MANGO_PLANKS));
    public static final Block MANGO_FENCE = new FenceBlock(FabricBlockSettings.copy(MANGO_PLANKS));
    public static final Block MANGO_DOOR = new MyDoorBlock(FabricBlockSettings.copy(MANGO_PLANKS)
            .strength(3.0F).nonOpaque());

    public static final Block MANGO_LEAVES = new FruitLeavesBlock("mango", 1,
            FabricBlockSettings.of(Material.LEAVES).strength(0.2F).ticksRandomly().nonOpaque()
                    .sounds(BlockSoundGroup.GRASS).allowsSpawning(Criteria::canSpawnOnLeaves)
                    .suffocates(Criteria::never).blockVision(Criteria::never));

    public static final Block SALT_BLOCK = new Block(FabricBlockSettings.of(Material.GLASS, MaterialColor.WHITE)
            .strength(0.3F).sounds(BlockSoundGroup.GLASS));
    public static final Block MILLSTONE = new MillstoneBlock(FabricBlockSettings.of(Material.STONE)
            .requiresTool().strength(3.5F));
    public static final Block COOKING_TABLE = new CookingTableBlock(FabricBlockSettings.of(Material.STONE)
            .requiresTool().strength(3.5F));
    public static final Block TUN = new TunBlock(FabricBlockSettings.of(Material.WOOD)
            .strength(2.5F).sounds(BlockSoundGroup.WOOD));

    public static final Block POTTED_TEA_SAPLING = new FlowerPotBlock(TEA_SAPLING,
            FabricBlockSettings.of(Material.SUPPORTED).breakInstantly().nonOpaque());
    public static final Block POTTED_MANGO_SAPLING = new FlowerPotBlock(MANGO_SAPLING,
            FabricBlockSettings.of(Material.SUPPORTED).breakInstantly().nonOpaque());

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
        register("stripped_mango_log", STRIPPED_MANGO_LOG);
        register("mango_wood", MANGO_WOOD);
        register("stripped_mango_wood", STRIPPED_MANGO_WOOD);
        register("mango_planks", MANGO_PLANKS);
        register("mango_stairs", MANGO_STAIRS);
        register("mango_slab", MANGO_SLAB);
        register("mango_button", MANGO_BUTTON);
        register("mango_pressure_plate", MANGO_PRESSURE_PLATE);
        register("mango_trapdoor", MANGO_TRAPDOOR);
        register("mango_fence_gate", MANGO_FENCE_GATE);
        register("mango_fence", MANGO_FENCE);
        register("mango_door", MANGO_DOOR);
        register("mango_leaves", MANGO_LEAVES);
        register("salt_block", SALT_BLOCK);
        register("millstone", MILLSTONE);
        register("cooking_table", COOKING_TABLE);
        register("tun", TUN);
        register("potted_tea_sapling", POTTED_TEA_SAPLING);
        register("potted_mango_sapling", POTTED_MANGO_SAPLING);
    }

    private static FabricBlockSettings newCropSettings() {
        return FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP);
    }

    static class Criteria {
        private static Boolean canSpawnOnLeaves(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
            return type == EntityType.OCELOT || type == EntityType.PARROT;
        }

        private static boolean never(BlockState state, BlockView world, BlockPos pos) { return false; }

        private static Boolean never(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
            return false;
        }
    }
}
