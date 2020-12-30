package me.ckffmc.farm.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class MyFoodComponents {
    public static final FoodComponent BAKED_SWEET_POTATO = newFood(4, 2.4f);
    public static final FoodComponent BUBBLE_TEA = new FoodComponent.Builder().hunger(4).saturationModifier(0.4f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1800, 1), 1.0F)
            .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 1800, 1), 1.0F)
            .build();
    public static final FoodComponent COOKED_OYSTER = newFood(2, 0.6f);
    public static final FoodComponent COOKED_RICE = newFood(2, 1.2f);
    public static final FoodComponent CORN = newFood(1, 0.8f);
    public static final FoodComponent GARLIC = newFood(1, 0.3f);
    public static final FoodComponent GINGER = newFood(1, 0.3f);
    public static final FoodComponent LETTUCE = newFood(1, 0.4f);
    public static final FoodComponent MANGO = newFood(1, 0.4f);
    public static final FoodComponent OYSTER_VERMICELLI = newFood(7, 2.4f);
    public static final FoodComponent PRESERVED_EGG = newFood(2, 0.6f);
    public static final FoodComponent RAW_OYSTER = newFood(1, 0.2f);
    public static final FoodComponent ROASTED_CORN = newFood(4, 1.8f);
    public static final FoodComponent TSUABING = newFood(5, 1.2f);
    public static final FoodComponent SOY_MILK = newFood(3, 0.8f);
    public static final FoodComponent SPRING_ONION = newFood(1, 0.3f);
    public static final FoodComponent STINKY_TOFU = newFood(3, 1.0f);
    public static final FoodComponent SWEET_POTATO = newFood(1, 0.8f);
    public static final FoodComponent TOFU = newFood(2, 1.0f);
    public static final FoodComponent TOFU_PUDDING = newFood(2, 1.2f);
    public static final FoodComponent BEEF_NOODLES = newFood(12, 14f);
    public static final FoodComponent BRAISED_PORK_RICE = newFood(8,7.2f);

    private static FoodComponent newFood(int h, float s) {
        return new FoodComponent.Builder().hunger(h).saturationModifier(s).build();
    }
}
