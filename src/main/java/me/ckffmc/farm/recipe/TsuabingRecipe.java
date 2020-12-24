package me.ckffmc.farm.recipe;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableMap;
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
import java.util.Map;

public class TsuabingRecipe extends SpecialCraftingRecipe {
    private static final Ingredient INGREDIENT_SHAVED_ICE;
    private static final Ingredient INGREDIENT_SYRUP;
    private static final Ingredient INGREDIENT_TOPPINGS;
    public static final BiMap<Item, Byte> SYRUPS;
    public static final BiMap<Item, Byte> TOPPINGS;
    public static final Map<Byte, Integer> SYRUP_COLOR;
    public static final Map<Byte, Integer> TOPPINGS_COLOR;

    public TsuabingRecipe(Identifier id) { super(id); }

    public boolean matches(CraftingInventory inv, World world) {
        boolean shavedIce = false;
        short syrup = 0;
        short toppings = 0;

        for (int i = 0; i < inv.size(); i++) {
            ItemStack stack = inv.getStack(i);
            if (!stack.isEmpty()) {
                if (INGREDIENT_SHAVED_ICE.test(stack)) {
                    if (shavedIce) return false;
                    shavedIce = true;
                } else if (INGREDIENT_SYRUP.test(stack)) {
                    if (syrup++ > 2) return false;
                } else if (INGREDIENT_TOPPINGS.test(stack)) {
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
                if (INGREDIENT_SYRUP.test(ingredient)) {
                    syrup.add(SYRUPS.get(ingredient.getItem()));
                } else if (INGREDIENT_TOPPINGS.test(ingredient)) {
                    toppings.add(TOPPINGS.get(ingredient.getItem()));
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
        Item[] syrup = new Item[]{Items.MILK_BUCKET, Items.HONEY_BOTTLE};
        Item[] toppings = new Item[]{Items.APPLE, Items.SWEET_BERRIES, MyItems.MANGO,
                MyItems.TOFU_PUDDING, MyItems.TAPIOCA_BALLS};
        INGREDIENT_SHAVED_ICE = Ingredient.ofItems(MyItems.SHAVED_ICE);
        INGREDIENT_SYRUP = Ingredient.ofItems(syrup);
        INGREDIENT_TOPPINGS = Ingredient.ofItems(toppings);
        ImmutableBiMap.Builder<Item, Byte> builder = new ImmutableBiMap.Builder<>();
        byte i = 0;
        for (Item item : syrup) { builder.put(item, i++); }
        SYRUPS = builder.build();
        i = 0;
        ImmutableBiMap.Builder<Item, Byte> builder1 = new ImmutableBiMap.Builder<>();
        for (Item item : toppings) { builder1.put(item, i++); }
        TOPPINGS = builder1.build();
        SYRUP_COLOR = new ImmutableMap.Builder<Byte,Integer>()
                .put(SYRUPS.get(Items.MILK_BUCKET),  0xe5e189)
                .put(SYRUPS.get(Items.HONEY_BOTTLE), 0xd99821).build();
        TOPPINGS_COLOR = new ImmutableMap.Builder<Byte,Integer>()
                .put(TOPPINGS.get(Items.APPLE),   0xa94b03)
                .put(TOPPINGS.get(MyItems.MANGO), 0xd17c15)
                .put(TOPPINGS.get(Items.SWEET_BERRIES),   0xb01b04)
                .put(TOPPINGS.get(MyItems.TOFU_PUDDING),  0xeddd64)
                .put(TOPPINGS.get(MyItems.TAPIOCA_BALLS), 0x3b301c)
                .build();
    }
}
