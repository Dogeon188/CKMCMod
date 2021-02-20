package me.ckffmc.farm.item;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TsuabingItem extends Item {
    public static final BiMap<Item, Byte> SYRUPS = HashBiMap.create();
    public static final BiMap<Item, Byte> TOPPINGS = HashBiMap.create();
    public static final Map<Byte, Integer> SYRUP_COLOR = new HashMap<>();
    public static final Map<Byte, Integer> TOPPINGS_COLOR = new HashMap<>();
    private static final Map<Byte, Pair<Integer, Float>> SYRUP_FOOD = new HashMap<>();
    private static final Map<Byte, Pair<Integer, Float>> TOPPINGS_FOOD = new HashMap<>();

    public TsuabingItem(Settings settings) { super(settings); }

    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) user;
            player.getHungerManager().add(getFoodAmount(stack), getSaturation(stack));
        }
        return user.eatFood(world, stack);
    }

    private static int getFoodAmount(ItemStack stack) {
        if (stack.getItem() == MyItems.TSUABING) {
            CompoundTag tag = stack.getSubTag("Tsuabing");
            if (tag != null) {
                int food = 0;
                if (tag.contains("Syrup", 7))
                    for (byte b : tag.getByteArray("Syrup")) food += SYRUP_FOOD.get(b).getLeft();
                if (tag.contains("Toppings", 7))
                    for (byte b : tag.getByteArray("Toppings")) food += TOPPINGS_FOOD.get(b).getLeft();
                return food;
            }
        }
        return 0;
    }

    private static float getSaturation(ItemStack stack) {
        if (stack.getItem() == MyItems.TSUABING) {
            CompoundTag tag = stack.getSubTag("Tsuabing");
            if (tag != null) {
                float saturation = 0;
                if (tag.contains("Syrup", 7))
                    for (byte b : tag.getByteArray("Syrup")) saturation += SYRUP_FOOD.get(b).getRight();
                if (tag.contains("Toppings", 7))
                    for (byte b : tag.getByteArray("Toppings")) saturation += TOPPINGS_FOOD.get(b).getRight();
                return saturation;
            }
        }
        return 0;
    }

    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext ctx) {
        buildTooltip(tooltip, stack);
    }

    private static void buildTooltip(List<Text> tooltip, ItemStack stack) {
        CompoundTag tag = stack.getSubTag("Tsuabing");
        if (tag != null) {
            buildTooltip(tooltip, tag, "Syrup");
            buildTooltip(tooltip, tag, "Toppings");
        }
    }

    private static void buildTooltip(List<Text> tooltip, CompoundTag tag, String key) {
        if (tag.contains(key, 7)) {
            byte[] bytes = tag.getByteArray(key);
            if (bytes.length != 0) {
                Map<Byte, Integer> map = new HashMap<>();
                for (byte b : bytes) {
                    if (!map.containsKey(b)) map.put(b, 1);
                    else map.replace(b, map.get(b) + 1);
                }
                map.forEach((k, v) -> {
                    Identifier id = Registry.ITEM.getId((key.equals("Syrup") ? SYRUPS : TOPPINGS).inverse().get(k));
                    MutableText text = new TranslatableText("item." + id.getNamespace() + "." + id.getPath());
                    tooltip.add(((v <= 1) ? text : new TranslatableText("text.dogeon.multiply", text, v))
                            .formatted(Formatting.GRAY));
                });
            }
        }
    }

    static {
        registerSyrup((byte) 0, Items.MILK_BUCKET, 0xe5e189, 1, 0.2f);
        registerSyrup((byte) 1, Items.HONEY_BOTTLE, 0xd99821, 4, 0.3f);
        registerTopping((byte) 0, Items.APPLE, 0xa94b03, 2, 0.2f);
        registerTopping((byte) 1, MyItems.MANGO, 0xd17c15, 1, 0.2f);
        registerTopping((byte) 2, Items.SWEET_BERRIES, 0xb01b04, 2, 0.2f);
        registerTopping((byte) 3, MyItems.TOFU_PUDDING, 0xeddd64, 2, 0.3f);
        registerTopping((byte) 4, MyItems.TAPIOCA_BALLS, 0x3b301c, 3, 0.2f);
    }

    private static void registerSyrup(byte id, Item item, int color, int food, float saturationModifier) {
        SYRUPS.put(item, id);
        SYRUP_COLOR.put(id, color);
        SYRUP_FOOD.put(id, new Pair<>(food, food * saturationModifier * 2.0f));
    }

    private static void registerTopping(byte id, Item item, int color, int food, float saturationModifier) {
        TOPPINGS.put(item, id);
        TOPPINGS_COLOR.put(id, color);
        TOPPINGS_FOOD.put(id, new Pair<>(food, food * saturationModifier * 2.0f));
    }
}
