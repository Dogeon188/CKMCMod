package me.ckffmc.farm.item;

import me.ckffmc.farm.MainMod;
import me.ckffmc.farm.block.MyBlocks;
import me.ckffmc.farm.entity.MyEntityType;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MyItems {
    public static final Item LETTUCE = new Item(new Item.Settings().group(MyItemGroups.GENERAL)
            .food(MyFoodComponents.LETTUCE));
    public static final Item CORN = new Item(new Item.Settings().group(MyItemGroups.GENERAL)
            .food(MyFoodComponents.CORN));
    public static final Item SPRING_ONION = new Item(new Item.Settings().group(MyItemGroups.GENERAL)
            .food(MyFoodComponents.SPRING_ONION));
    public static final Item RICE = new Item(new Item.Settings().group(MyItemGroups.GENERAL));

    public static final Item SWEET_POTATO = new AliasedBlockItem(MyBlocks.SWEET_POTATO,
            new Item.Settings().group(MyItemGroups.GENERAL).food(MyFoodComponents.SWEET_POTATO));
    public static final Item BAKED_SWEET_POTATO = new Item(new Item.Settings().group(MyItemGroups.GENERAL)
            .food(MyFoodComponents.BAKED_SWEET_POTATO));
    public static final Item ROASTED_CORN = new Item(new Item.Settings().group(MyItemGroups.GENERAL)
            .food(MyFoodComponents.ROASTED_CORN));
    public static final Item GINGER = new AliasedBlockItem(MyBlocks.GINGER,
            new Item.Settings().group(MyItemGroups.GENERAL).food(MyFoodComponents.GINGER));
    public static final Item GARLIC = new AliasedBlockItem(MyBlocks.GARLIC,
            new Item.Settings().group(MyItemGroups.GENERAL).food(MyFoodComponents.GARLIC));
    public static final Item SOYBEAN = new AliasedBlockItem(MyBlocks.SOYBEAN,
            new Item.Settings().group(MyItemGroups.GENERAL));

    public static final Item LETTUCE_SEEDS = new AliasedBlockItem(MyBlocks.LETTUCE,
            new Item.Settings().group(MyItemGroups.GENERAL));
    public static final Item CORN_SEEDS = new AliasedBlockItem(MyBlocks.CORN,
            new Item.Settings().group(MyItemGroups.GENERAL));
    public static final Item SPRING_ONION_SEEDS = new AliasedBlockItem(MyBlocks.SPRING_ONION,
            new Item.Settings().group(MyItemGroups.GENERAL));
    public static final Item RICE_SEEDS = new AliasedBlockItem(MyBlocks.RICE,
            new Item.Settings().group(MyItemGroups.GENERAL));

    public static final Item RAW_OYSTER = new Item(new Item.Settings().group(MyItemGroups.GENERAL));
    public static final Item COOKED_OYSTER = new Item(new Item.Settings().group(MyItemGroups.GENERAL)
            .food(MyFoodComponents.COOKED_OYSTER));
    public static final Item WHEAT_FLOUR = new Item(new Item.Settings().group(MyItemGroups.GENERAL));
    public static final Item NOODLES = new Item(new Item.Settings().group(MyItemGroups.GENERAL));
    public static final Item SOY_MILK = new DrinkItem(new Item.Settings().group(MyItemGroups.GENERAL)
            .food(MyFoodComponents.SOY_MILK).maxCount(16));
    public static final Item SALT = new Item(new Item.Settings().group(MyItemGroups.GENERAL));
    public static final Item SALT_BLOCK = new BlockItem(MyBlocks.SALT_BLOCK,
            new Item.Settings().group(MyItemGroups.GENERAL));
    public static final Item BITTERN = new Item(new Item.Settings().group(MyItemGroups.GENERAL)
            .recipeRemainder(Items.GLASS_BOTTLE).maxCount(16));
    public static final Item TOFU = new Item(new Item.Settings().group(MyItemGroups.GENERAL)
            .food(MyFoodComponents.TOFU));
    public static final Item TOFU_PUDDING = new Item(new Item.Settings().group(MyItemGroups.GENERAL)
            .food(MyFoodComponents.TOFU_PUDDING));
    public static final Item SWEET_POTATO_STARCH = new Item(new Item.Settings().group(MyItemGroups.GENERAL));
    public static final Item SHAVED_ICE = new Item(new Item.Settings().group(MyItemGroups.GENERAL)
            .food(MyFoodComponents.SHAVED_ICE));

    public static final Item MILLSTONE = new BlockItem(MyBlocks.MILLSTONE,
            new Item.Settings().group(MyItemGroups.GENERAL));
    public static final Item COOKING_TABLE = new BlockItem(MyBlocks.COOKING_TABLE,
            new Item.Settings().group(MyItemGroups.GENERAL));

    public static final Item OYSTER_SPAWN_EGG = new SpawnEggItem(MyEntityType.OYSTER, 0x091E07, 0xC9BCCA, new Item.Settings().group(MyItemGroups.GENERAL));

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
        register("sweet_potato_starch", SWEET_POTATO_STARCH);
        register("shaved_ice", SHAVED_ICE);
        register("millstone", MILLSTONE);
        register("cooking_table", COOKING_TABLE);
        register("oyster_spawn_egg", OYSTER_SPAWN_EGG);
    }
}
