package me.ckffmc.farm.item;

import me.ckffmc.farm.MainMod;
import me.ckffmc.farm.block.MyBlocks;
import me.ckffmc.farm.entity.MyEntityType;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MyItems {
    public static final Item LETTUCE_SEEDS = register("lettuce_seeds",
            new AliasedBlockItem(MyBlocks.LETTUCE, new Item.Settings().group(MyItemGroups.GENERAL)));
    public static final Item LETTUCE = register("lettuce",
            new Item(new Item.Settings().group(MyItemGroups.GENERAL).food(MyFoodComponents.LETTUCE)));
    public static final Item SWEET_POTATO = register("sweet_potato", new AliasedBlockItem(MyBlocks.SWEET_POTATO,
            new Item.Settings().group(MyItemGroups.GENERAL).food(MyFoodComponents.SWEET_POTATO)));
    public static final Item BAKED_SWEET_POTATO = register("baked_sweet_potato",
            new Item(new Item.Settings().group(MyItemGroups.GENERAL).food(MyFoodComponents.BAKED_SWEET_POTATO)));
    public static final Item GARLIC = register("garlic", new AliasedBlockItem(MyBlocks.GARLIC,
            new Item.Settings().group(MyItemGroups.GENERAL).food(MyFoodComponents.GARLIC)));
    public static final Item RICE = register("rice",
            new AliasedBlockItem(MyBlocks.RICE, new Item.Settings().group(MyItemGroups.GENERAL)));
    public static final Item CORN = register("corn",
            new Item(new Item.Settings().group(MyItemGroups.GENERAL).food(MyFoodComponents.CORN)));
    public static final Item CORN_SEEDS = register("corn_seeds",
            new AliasedBlockItem(MyBlocks.CORN, new Item.Settings().group(MyItemGroups.GENERAL)));
    public static final Item SPRING_ONION = register("spring_onion",
            new Item(new Item.Settings().group(MyItemGroups.GENERAL).food(MyFoodComponents.SPRING_ONION)));
    public static final Item SPRING_ONION_SEEDS = register("spring_onion_seeds",
            new AliasedBlockItem(MyBlocks.SPRING_ONION, new Item.Settings().group(MyItemGroups.GENERAL)));
    public static final Item GINGER = register("ginger", new AliasedBlockItem(MyBlocks.GINGER,
            new Item.Settings().group(MyItemGroups.GENERAL).food(MyFoodComponents.GINGER)));
    public static final Item SALT = register("salt", new Item(new Item.Settings().group(MyItemGroups.GENERAL)));
    public static final Item SALT_BLOCK = register(MyBlocks.SALT_BLOCK, MyItemGroups.GENERAL);
    public static final Item RAW_OYSTER = register("raw_oyster",
            new Item(new Item.Settings().group(MyItemGroups.GENERAL)));
    public static final Item COOKED_OYSTER = register("cooked_oyster",
            new Item(new Item.Settings().group(MyItemGroups.GENERAL).food(MyFoodComponents.COOKED_OYSTER)));
    public static final Item OYSTER_SPAWN_EGG = register("oyster_spawn_egg", new SpawnEggItem(MyEntityType.OYSTER, 0x091E07, 0xC9BCCA, new Item.Settings().group(MyItemGroups.GENERAL)));
    public static final Item MILLSTONE = register(MyBlocks.MILLSTONE, MyItemGroups.GENERAL);
    public static final Item SOYBEAN = register("soybean",
            new AliasedBlockItem(MyBlocks.SOYBEAN, new Item.Settings().group(MyItemGroups.GENERAL)));
    public static final Item SOY_MILK = register("soy_milk",
            new DrinkItem(new Item.Settings().group(MyItemGroups.GENERAL).maxCount(16).food(MyFoodComponents.SOY_MILK)));
    public static final Item WHEAT_FLOUR = register("wheat_flour",
            new Item(new Item.Settings().group(MyItemGroups.GENERAL)));

    private static Item register(Block block) {
        return register(new BlockItem(block, new Item.Settings()));
    }

    private static Item register(Block block, ItemGroup group) {
        return register(new BlockItem(block, (new Item.Settings()).group(group)));
    }

    private static Item register(BlockItem item) {
        return register(item.getBlock(), item);
    }

    protected static Item register(Block block, Item item) {
        return register(Registry.BLOCK.getId(block), item);
    }

    private static Item register(String id, Item item) {
        return register(new Identifier(MainMod.MOD_ID, id), item);
    }

    private static Item register(Identifier id, Item item) {
        if (item instanceof BlockItem) ((BlockItem) item).appendBlocks(Item.BLOCK_ITEMS, item);
        return Registry.register(Registry.ITEM, id, item);
    }
}
