package me.ckffmc.farm.item;

import com.google.common.collect.ImmutableMap;
import me.ckffmc.farm.block.MyBlocks;
import me.ckffmc.farm.mixin.item.AxeItemAccessor;
import net.minecraft.block.Block;

public class AxeModify {
    public static void addStrippedBlocks() {
        AxeItemAccessor.setCompostables(ImmutableMap.<Block, Block>builder()
                .putAll(AxeItemAccessor.getCompostables())
                .put(MyBlocks.MANGO_LOG, MyBlocks.STRIPPED_MANGO_LOG)
                .put(MyBlocks.MANGO_WOOD, MyBlocks.STRIPPED_MANGO_WOOD)
                .build());
    }
}
