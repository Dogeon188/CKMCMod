package me.ckffmc.farm.recipe;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.Lists;
import me.ckffmc.farm.item.MyItems;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.ByteArrayTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.List;

public class TsuabingRecipe extends SpecialCraftingRecipe {
    private static final Ingredient SHAVED_ICE;
    private static final Ingredient SYRUP;
    private static final Ingredient TOPPINGS;
    public static final BiMap<Item, Byte> INGREDIENTS;
    public static final BiMap<Byte, Item> INV_INGREDIENTS;

    public TsuabingRecipe(Identifier id) { super(id); }

    public boolean matches(CraftingInventory inv, World world) {
        boolean shavedIce = false;
        short syrup = 0;
        short toppings = 0;

        for (int i = 0; i < inv.size(); i++) {
            ItemStack stack = inv.getStack(i);
            if (!stack.isEmpty()) {
                if (SHAVED_ICE.test(stack)) {
                    if (shavedIce) return false;
                    shavedIce = true;
                } else if (SYRUP.test(stack)) {
                    if (syrup++ > 2) return false;
                } else if (TOPPINGS.test(stack)) {
                    if (toppings++ > 4) return false;
                } else return false;
            }
        }
        return shavedIce && (syrup > 0);
    }

    public ItemStack craft(CraftingInventory inv) {
        ItemStack stack = new ItemStack(MyItems.TSUABING);
        CompoundTag tag = stack.getOrCreateSubTag("Tsuabing");
        List<Byte> syrup = Lists.newArrayList();
        List<Byte> toppings = Lists.newArrayList();

        for (int i = 0; i < inv.size(); i++) {
            ItemStack ingredient = inv.getStack(i);
            if (!ingredient.isEmpty()) {
                if (SYRUP.test(ingredient)) {
                    syrup.add(INGREDIENTS.get(ingredient.getItem()));
                } else if (TOPPINGS.test(ingredient)) {
                    toppings.add(INGREDIENTS.get(ingredient.getItem()));
                }
            }
        }
        if (!syrup.isEmpty()) tag.put("Syrup", new ByteArrayTag(syrup));
        if (!toppings.isEmpty()) tag.put("Toppings", new ByteArrayTag(toppings));
        return stack;
    }

    public boolean fits(int width, int height) { return width >= 2 && height >= 2; }

    public RecipeSerializer<?> getSerializer() { return MyRecipeSerializer.TSUABING; }

    static {
        Item shaved_ice = MyItems.SHAVED_ICE;
        Item[] syrup = new Item[]{Items.MILK_BUCKET, Items.HONEY_BOTTLE, Items.SUGAR, Items.EGG};
        Item[] toppings = new Item[]{Items.APPLE, Items.SWEET_BERRIES, MyItems.MANGO,
                MyItems.TOFU_PUDDING, MyItems.TAPIOCA_BALLS};
        SHAVED_ICE = Ingredient.ofItems(shaved_ice);
        SYRUP = Ingredient.ofItems(syrup);
        TOPPINGS = Ingredient.ofItems(toppings);
        ImmutableBiMap.Builder<Item, Byte> builder = new ImmutableBiMap.Builder<>();
        byte i = 0;
        builder.put(MyItems.SHAVED_ICE, i++);
        for (Item item : syrup) { builder.put(item, i++); }
        for (Item item : toppings) { builder.put(item, i++); }
        INGREDIENTS = builder.build();
        INV_INGREDIENTS = INGREDIENTS.inverse();
    }
}
