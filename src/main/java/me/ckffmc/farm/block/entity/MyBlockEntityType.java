package me.ckffmc.farm.block.entity;

import me.ckffmc.farm.MainMod;
import me.ckffmc.farm.block.MyBlocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MyBlockEntityType {

    public static final BlockEntityType<MillstoneBlockEntity> MILLSTONE_BLOCK_ENTITY = register("millstone",
            BlockEntityType.Builder.create(MillstoneBlockEntity::new, MyBlocks.MILLSTONE).build(null));

    private static <T extends BlockEntity> BlockEntityType<T> register(String id, BlockEntityType<T> builder) {
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(MainMod.MOD_ID, id), builder);
    }
}
