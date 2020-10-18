package me.ckffmc.farm.item;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

import java.util.Objects;

public class DrinkItem extends Item {

    public DrinkItem(Settings settings) { super(settings); }

    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
//        ItemStack itemStack = super.finishUsing(stack, world, user);
//        PlayerEntity playerEntity = user instanceof PlayerEntity ? (PlayerEntity)user : null;
//        if (playerEntity instanceof ServerPlayerEntity) {
//            Criteria.CONSUME_ITEM.trigger((ServerPlayerEntity)playerEntity, stack);
//        }
//
//        if (playerEntity != null) {
//            playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
//            if (!playerEntity.abilities.creativeMode) stack.decrement(1);
//        }
//
//        return stack;
        return super.finishUsing(stack, world, user);
    }

    public int getMaxUseTime(ItemStack stack) {
        return 32;
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    public SoundEvent getEatSound() {
        return SoundEvents.ENTITY_GENERIC_DRINK;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (this.isFood()) {
            ItemStack itemStack = user.getStackInHand(hand);
            if (user.canConsume(Objects.requireNonNull(this.getFoodComponent()).isAlwaysEdible())) {
                user.setCurrentHand(hand);
                return TypedActionResult.consume(itemStack);
            } else {
                return TypedActionResult.fail(itemStack);
            }
        } else {
            return TypedActionResult.pass(user.getStackInHand(hand));
        }
    }
}
