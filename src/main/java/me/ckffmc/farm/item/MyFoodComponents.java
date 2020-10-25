package me.ckffmc.farm.item;

import net.minecraft.item.FoodComponent;

public class MyFoodComponents {

    public static final FoodComponent BAKED_SWEET_POTATO = new FoodComponent.Builder().hunger(4).saturationModifier(2.4f).build();
    public static final FoodComponent COOKED_OYSTER = new FoodComponent.Builder().hunger(2).saturationModifier(1.6f).meat().build();
    public static final FoodComponent CORN = new FoodComponent.Builder().hunger(1).saturationModifier(0.8f).build();
    public static final FoodComponent ROASTED_CORN = new FoodComponent.Builder().hunger(4).saturationModifier(1.8f).build();
    public static final FoodComponent GARLIC = new FoodComponent.Builder().hunger(1).saturationModifier(0.3f).build();
    public static final FoodComponent GINGER = new FoodComponent.Builder().hunger(1).saturationModifier(0.3f).build();
    public static final FoodComponent LETTUCE = new FoodComponent.Builder().hunger(1).saturationModifier(0.4f).build();
    public static final FoodComponent SOY_MILK = new FoodComponent.Builder().hunger(3).saturationModifier(0.8f).build();
    public static final FoodComponent SPRING_ONION = new FoodComponent.Builder().hunger(1).saturationModifier(0.3f).build();
    public static final FoodComponent SHAVED_ICE = new FoodComponent.Builder().hunger(2).saturationModifier(0.1f).build();
    public static final FoodComponent SWEET_POTATO = new FoodComponent.Builder().hunger(1).saturationModifier(0.8f).build();
    public static final FoodComponent TOFU = new FoodComponent.Builder().hunger(2).saturationModifier(1.0f).build();
    public static final FoodComponent TOFU_PUDDING = new FoodComponent.Builder().hunger(2).saturationModifier(1.2f).build();
}
