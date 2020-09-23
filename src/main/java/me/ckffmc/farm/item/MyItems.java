package me.ckffmc.farm.item;

import me.ckffmc.farm.MainMod;
import me.ckffmc.farm.block.MyBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MyItems {
    public static final Item LETTUCE_SEEDS;
    public static final Item LETTUCE;
    public static final Item SWEET_POTATO;
    public static final Item RICE;

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

    static {
        LETTUCE_SEEDS = register("lettuce_seeds", new AliasedBlockItem(MyBlocks.LETTUCE, new Item.Settings().group(MyItemGroups.GENERAL)));
        LETTUCE = register("lettuce", new Item(new Item.Settings().group(MyItemGroups.GENERAL)));
        SWEET_POTATO = register("sweet_potato", new AliasedBlockItem(MyBlocks.SWEET_POTATO,
                new Item.Settings().group(MyItemGroups.GENERAL)));
        RICE = register("rice", new AliasedBlockItem(MyBlocks.RICE, new Item.Settings().group(MyItemGroups.GENERAL)));
//        DEBUG_ITEM = register("debug_item", new Item(new Item.Settings().group(MyItemGroups.GENERAL)));
//        DEBUG_BLOCK = register(MyBlocks.DEBUG_BLOCK, MyItemGroups.GENERAL);
    }
}
