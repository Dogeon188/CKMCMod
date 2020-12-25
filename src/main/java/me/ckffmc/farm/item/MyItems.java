package me.ckffmc.farm.item;

import me.ckffmc.farm.MainMod;
import me.ckffmc.farm.block.MyBlocks;
import me.ckffmc.farm.entity.MyEntityType;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MyItems {
    public static final Item LETTUCE = new Item(grouped().food(MyFoodComponents.LETTUCE));
    public static final Item CORN = new Item(grouped().food(MyFoodComponents.CORN));
    public static final Item SPRING_ONION = new Item(grouped().food(MyFoodComponents.SPRING_ONION));
    public static final Item RICE = new Item(grouped());

    public static final Item SWEET_POTATO = new AliasedBlockItem(MyBlocks.SWEET_POTATO,
            grouped().food(MyFoodComponents.SWEET_POTATO));
    public static final Item BAKED_SWEET_POTATO = new Item(grouped().food(MyFoodComponents.BAKED_SWEET_POTATO));
    public static final Item ROASTED_CORN = new Item(grouped().food(MyFoodComponents.ROASTED_CORN));
    public static final Item GINGER = new AliasedBlockItem(MyBlocks.GINGER, grouped().food(MyFoodComponents.GINGER));
    public static final Item GARLIC = new AliasedBlockItem(MyBlocks.GARLIC, grouped().food(MyFoodComponents.GARLIC));
    public static final Item SOYBEAN = new AliasedBlockItem(MyBlocks.SOYBEAN, grouped());

    public static final Item LETTUCE_SEEDS = new AliasedBlockItem(MyBlocks.LETTUCE, grouped());
    public static final Item CORN_SEEDS = new AliasedBlockItem(MyBlocks.CORN, grouped());
    public static final Item SPRING_ONION_SEEDS = new AliasedBlockItem(MyBlocks.SPRING_ONION, grouped());
    public static final Item RICE_SEEDS = new AliasedBlockItem(MyBlocks.RICE, grouped());

    public static final Item TEA_SAPLING = new BlockItem(MyBlocks.TEA_SAPLING, grouped());
    public static final Item TEA_LEAVES = new Item(grouped());
    public static final Item BLACK_TEA_LEAVES = new Item(grouped());

    public static final Item MANGO = new Item(grouped().food(MyFoodComponents.MANGO));
    public static final Item MANGO_SAPLING = new BlockItem(MyBlocks.MANGO_SAPLING, grouped());
    public static final Item MANGO_LOG = new BlockItem(MyBlocks.MANGO_LOG, grouped());
    public static final Item STRIPPED_MANGO_LOG = new BlockItem(MyBlocks.STRIPPED_MANGO_LOG, grouped());
    public static final Item MANGO_WOOD = new BlockItem(MyBlocks.MANGO_WOOD, grouped());
    public static final Item STRIPPED_MANGO_WOOD = new BlockItem(MyBlocks.STRIPPED_MANGO_WOOD, grouped());
    public static final Item MANGO_PLANKS = new BlockItem(MyBlocks.MANGO_PLANKS, grouped());
    public static final Item MANGO_STAIRS = new BlockItem(MyBlocks.MANGO_STAIRS, grouped());
    public static final Item MANGO_SLAB = new BlockItem(MyBlocks.MANGO_SLAB, grouped());
    public static final Item MANGO_BUTTON = new BlockItem(MyBlocks.MANGO_BUTTON, grouped());
    public static final Item MANGO_PRESSURE_PLATE = new BlockItem(MyBlocks.MANGO_PRESSURE_PLATE, grouped());
    public static final Item MANGO_TRAPDOOR = new BlockItem(MyBlocks.MANGO_TRAPDOOR, grouped());
    public static final Item MANGO_FENCE_GATE = new BlockItem(MyBlocks.MANGO_FENCE_GATE, grouped());
    public static final Item MANGO_FENCE = new BlockItem(MyBlocks.MANGO_FENCE, grouped());
    public static final Item MANGO_DOOR = new BlockItem(MyBlocks.MANGO_DOOR, grouped());
    public static final Item MANGO_LEAVES = new BlockItem(MyBlocks.MANGO_LEAVES, grouped());

    public static final Item RAW_OYSTER = new Item(grouped().food(MyFoodComponents.RAW_OYSTER));
    public static final Item COOKED_OYSTER = new Item(grouped().food(MyFoodComponents.COOKED_OYSTER));
    public static final Item WHEAT_FLOUR = new Item(grouped());
    public static final Item NOODLES = new Item(grouped());
    public static final Item SOY_MILK = new DrinkItem(grouped().food(MyFoodComponents.SOY_MILK).maxCount(16));
    public static final Item SALT = new Item(grouped());
    public static final Item SALT_BLOCK = new BlockItem(MyBlocks.SALT_BLOCK, grouped());
    public static final Item BITTERN = new Item(grouped().recipeRemainder(Items.GLASS_BOTTLE).maxCount(16));
    public static final Item TOFU = new Item(grouped().food(MyFoodComponents.TOFU));
    public static final Item TOFU_PUDDING = new Item(grouped().food(MyFoodComponents.TOFU_PUDDING));
    public static final Item STINKY_TOFU = new Item(grouped().food(MyFoodComponents.STINKY_TOFU));
    public static final Item PRESERVED_EGG = new Item(grouped().food(MyFoodComponents.PRESERVED_EGG));
    public static final Item SWEET_POTATO_STARCH = new Item(grouped());
    public static final Item SHAVED_ICE = new Item(grouped());
    public static final Item TAPIOCA_BALLS = new Item(grouped());
    public static final Item BUBBLE_TEA = new DrinkItem(grouped().food(MyFoodComponents.BUBBLE_TEA).maxCount(16));
    public static final Item SOY_SAUCE = new Item(grouped());

    public static final Item TSUABING = new TsuabingItem(grouped().food(MyFoodComponents.TSUABING).maxCount(1));

    public static final Item MILLSTONE = new BlockItem(MyBlocks.MILLSTONE, grouped());
    public static final Item COOKING_TABLE = new BlockItem(MyBlocks.COOKING_TABLE, grouped());
    public static final Item TUN = new BlockItem(MyBlocks.TUN, grouped());

    public static final Item OYSTER_SPAWN_EGG = new SpawnEggItem(MyEntityType.OYSTER,
            0x091E07, 0xC9BCCA, grouped());

    private static void register(String id, Item item) {
        if (item instanceof BlockItem) ((BlockItem) item).appendBlocks(Item.BLOCK_ITEMS, item);
        Registry.register(Registry.ITEM, new Identifier(MainMod.MOD_ID, id), item);
    }

    public static void registerItems() {
        register("lettuce", LETTUCE);
        register("corn", CORN);
        register("spring_onion", SPRING_ONION);
        register("rice", RICE);
        register("sweet_potato", SWEET_POTATO);
        register("baked_sweet_potato", BAKED_SWEET_POTATO);
        register("roasted_corn", ROASTED_CORN);
        register("ginger", GINGER);
        register("garlic", GARLIC);
        register("soybean", SOYBEAN);
        register("lettuce_seeds", LETTUCE_SEEDS);
        register("corn_seeds", CORN_SEEDS);
        register("spring_onion_seeds", SPRING_ONION_SEEDS);
        register("rice_seeds", RICE_SEEDS);
        register("tea_sapling", TEA_SAPLING);
        register("tea_leaves", TEA_LEAVES);
        register("black_tea_leaves", BLACK_TEA_LEAVES);
        register("mango", MANGO);
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
        register("raw_oyster", RAW_OYSTER);
        register("cooked_oyster", COOKED_OYSTER);
        register("wheat_flour", WHEAT_FLOUR);
        register("noodles", NOODLES);
        register("soy_milk", SOY_MILK);
        register("salt", SALT);
        register("salt_block", SALT_BLOCK);
        register("bittern", BITTERN);
        register("tofu", TOFU);
        register("tofu_pudding", TOFU_PUDDING);
        register("stinky_tofu", STINKY_TOFU);
        register("preserved_egg", PRESERVED_EGG);
        register("sweet_potato_starch", SWEET_POTATO_STARCH);
        register("shaved_ice", SHAVED_ICE);
        register("tapioca_balls", TAPIOCA_BALLS);
        register("bubble_tea", BUBBLE_TEA);
        register("soy_sauce", SOY_SAUCE);
        register("tsuabing", TSUABING);
        register("millstone", MILLSTONE);
        register("cooking_table", COOKING_TABLE);
        register("tun", TUN);
        register("oyster_spawn_egg", OYSTER_SPAWN_EGG);
    }

    private static Item.Settings grouped() { return new Item.Settings().group(MyItemGroups.GENERAL); }
}
