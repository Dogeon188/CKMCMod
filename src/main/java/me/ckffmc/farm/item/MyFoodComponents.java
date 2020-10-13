package me.ckffmc.farm.item;

import net.minecraft.item.FoodComponent;

public class MyFoodComponents {
    public static final FoodComponent SWEET_POTATO;
    public static final FoodComponent BAKED_SWEET_POTATO;
    public static final FoodComponent LETTUCE;
    public static final FoodComponent CORN;
    public static final FoodComponent GARLIC;
    public static final FoodComponent SPRING_ONION;
    public static final FoodComponent GINGER;
    public static final FoodComponent COOKED_OYSTER;

    static {
        SWEET_POTATO = new FoodComponent.Builder().hunger(1).saturationModifier(0.5f).build();
        BAKED_SWEET_POTATO = new FoodComponent.Builder().hunger(4).saturationModifier(2.4f).build();
        LETTUCE = new FoodComponent.Builder().hunger(1).saturationModifier(0.5f).build();
        CORN = new FoodComponent.Builder().hunger(1).saturationModifier(0.5f).build();
        GARLIC = new FoodComponent.Builder().hunger(1).saturationModifier(0.5f).build();
        SPRING_ONION = new FoodComponent.Builder().hunger(1).saturationModifier(0.5f).build();
        GINGER = new FoodComponent.Builder().hunger(1).saturationModifier(0.5f).build();
        COOKED_OYSTER = new FoodComponent.Builder().hunger(2).saturationModifier(1.6f).meat().build();
    }
}
