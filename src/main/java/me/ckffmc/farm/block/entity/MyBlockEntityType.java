package me.ckffmc.farm.block.entity;

import me.ckffmc.farm.MainMod;
import me.ckffmc.farm.block.MyBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;

public class MyBlockEntityType {
    public static final BlockEntityType<MillstoneBlockEntity> MILLSTONE_BLOCK_ENTITY = register("millstone",
            MillstoneBlockEntity::new, MyBlocks.MILLSTONE);
    public static final BlockEntityType<CookingTableBlockEntity> COOKING_TABLE_BLOCK_ENTITY = register("cooking_table",
            CookingTableBlockEntity::new, MyBlocks.COOKING_TABLE);
    public static final BlockEntityType<TunBlockEntity> TUN_BLOCK_ENTITY = register("tun",
            TunBlockEntity::new, MyBlocks.TUN);

    private static <T extends BlockEntity> BlockEntityType<T> register(String id, Supplier<T> builder, Block... blocks) {
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(MainMod.MOD_ID, id), BlockEntityType.Builder.create(builder, blocks).build(null));
    }
}
