package me.ckffmc.farm.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class DrinkItem extends Item {

    public DrinkItem(Settings settings) { super(settings); }

    public int getMaxUseTime(ItemStack stack) { return 32; }

    public UseAction getUseAction(ItemStack stack) { return UseAction.DRINK; }

    public SoundEvent getEatSound() {
        return SoundEvents.ENTITY_GENERIC_DRINK;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (this.isFood()) {
            ItemStack itemStack = user.getStackInHand(hand);
            if (this.getFoodComponent() != null && user.canConsume(this.getFoodComponent().isAlwaysEdible())) {
                user.setCurrentHand(hand);
                return TypedActionResult.consume(itemStack);
            } else return TypedActionResult.fail(itemStack);
        } else return TypedActionResult.pass(user.getStackInHand(hand));
    }
}
