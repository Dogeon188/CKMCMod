package me.ckffmc.farm.item;

import me.ckffmc.farm.MainMod;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class MyItemGroups {
    public static final ItemGroup GENERAL = FabricItemGroupBuilder.create(new Identifier(MainMod.MOD_ID, "general")).icon(() -> new ItemStack(MyItems.DEBUG_ITEM)).build();
}
