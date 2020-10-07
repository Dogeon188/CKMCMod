package me.ckffmc.farm.item;

import net.minecraft.item.FoodComponent;

public class MyFoodComponents {
    public static final FoodComponent SWEET_POTATO;
    public static final FoodComponent BAKED_SWEET_POTATO;
    public static final FoodComponent LETTUCE;
    public static final FoodComponent CORN;

    static {
        SWEET_POTATO = new FoodComponent.Builder().hunger(1).saturationModifier(0.5f).build();
        BAKED_SWEET_POTATO = new FoodComponent.Builder().hunger(4).saturationModifier(2.4f).build();
        LETTUCE = new FoodComponent.Builder().hunger(1).saturationModifier(0.5f).build();
        CORN = new FoodComponent.Builder().hunger(1).saturationModifier(0.5f).build();
    }
}
