package me.ckffmc.farm.mixin.entity;

import me.ckffmc.farm.block.MyBlocks;
import me.ckffmc.farm.item.MyItems;
import net.minecraft.entity.ai.brain.task.FarmerVillagerTask;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(FarmerVillagerTask.class)
public class FarmerVillagerTaskMixin {
    @Shadow private @Nullable BlockPos currentTarget;

    @ModifyVariable(method = "keepRunning", at = @At("STORE"), ordinal = 0)
    private SimpleInventory addPlantableSeeds(SimpleInventory inventory, ServerWorld serverWorld,
                                              VillagerEntity villagerEntity, long l) {
        for(int i = 0; i < inventory.size(); ++i) {
            ItemStack itemStack = inventory.getStack(i);
            boolean bl = false;
            if (!itemStack.isEmpty()) {
                if (itemStack.getItem() == MyItems.RICE_SEEDS) {
                    serverWorld.setBlockState(this.currentTarget, MyBlocks.RICE.getDefaultState(), 3);
                    bl = true;
                } else if (itemStack.getItem() == MyItems.SWEET_POTATO) {
                    serverWorld.setBlockState(this.currentTarget, MyBlocks.SWEET_POTATO.getDefaultState(), 3);
                    bl = true;
                } else if (itemStack.getItem() == MyItems.SOYBEAN) {
                    serverWorld.setBlockState(this.currentTarget, MyBlocks.SOYBEAN.getDefaultState(), 3);
                    bl = true;
                } else if (itemStack.getItem() == MyItems.CORN_SEEDS) {
                    serverWorld.setBlockState(this.currentTarget, MyBlocks.CORN.getDefaultState(), 3);
                    bl = true;
                } else if (itemStack.getItem() == MyItems.GARLIC) {
                    serverWorld.setBlockState(this.currentTarget, MyBlocks.GARLIC.getDefaultState(), 3);
                    bl = true;
                } else if (itemStack.getItem() == MyItems.GINGER) {
                    serverWorld.setBlockState(this.currentTarget, MyBlocks.GINGER.getDefaultState(), 3);
                    bl = true;
                } else if (itemStack.getItem() == MyItems.LETTUCE_SEEDS) {
                    serverWorld.setBlockState(this.currentTarget, MyBlocks.LETTUCE.getDefaultState(), 3);
                    bl = true;
                } else if (itemStack.getItem() == MyItems.SPRING_ONION_SEEDS) {
                    serverWorld.setBlockState(this.currentTarget, MyBlocks.SPRING_ONION.getDefaultState(), 3);
                    bl = true;
                }
            }

            if (bl) {
                if (this.currentTarget != null) serverWorld.playSound(null,
                        this.currentTarget.getX(), this.currentTarget.getY(), this.currentTarget.getZ(),
                        SoundEvents.ITEM_CROP_PLANT, SoundCategory.BLOCKS, 1.0F, 1.0F);
                itemStack.decrement(1);
                if (itemStack.isEmpty()) inventory.setStack(i, ItemStack.EMPTY);
                break;
            }
        }
        return inventory;
    }
}
