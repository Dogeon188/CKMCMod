package me.ckffmc.farm.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class MyFoodComponents {
    public static final FoodComponent BAKED_SWEET_POTATO = simpleFood(4, 2.4f);
    public static final FoodComponent BUBBLE_TEA = new FoodComponent.Builder().hunger(4).saturationModifier(0.4f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1800, 1), 1.0F)
            .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 1800, 1), 1.0F)
            .build();
    public static final FoodComponent COOKED_OYSTER = simpleFood(2, 0.6f);
    public static final FoodComponent CORN = simpleFood(1, 0.8f);
    public static final FoodComponent ROASTED_CORN = simpleFood(4, 1.8f);
    public static final FoodComponent GARLIC = simpleFood(1, 0.3f);
    public static final FoodComponent GINGER = simpleFood(1, 0.3f);
    public static final FoodComponent LETTUCE = simpleFood(1, 0.4f);
    public static final FoodComponent SOY_MILK = simpleFood(3, 0.8f);
    public static final FoodComponent SPRING_ONION = simpleFood(1, 0.3f);
    public static final FoodComponent SHAVED_ICE = simpleFood(2, 0.1f);
    public static final FoodComponent SWEET_POTATO = simpleFood(1, 0.8f);
    public static final FoodComponent TOFU = simpleFood(2, 1.0f);
    public static final FoodComponent TOFU_PUDDING = simpleFood(2, 1.2f);

    private static FoodComponent simpleFood(int h, float s) {
        return new FoodComponent.Builder().hunger(h).saturationModifier(s).build();
    }
}
