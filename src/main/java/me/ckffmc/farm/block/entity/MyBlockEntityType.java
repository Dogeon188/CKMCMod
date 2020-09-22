package me.ckffmc.farm.block.entity;

import me.ckffmc.farm.MainMod;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MyBlockEntityType {

    private static <T extends BlockEntity> BlockEntityType<T> register(String id, BlockEntityType builder) {
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(MainMod.MOD_ID, id), builder);
    }

    static {}
}
