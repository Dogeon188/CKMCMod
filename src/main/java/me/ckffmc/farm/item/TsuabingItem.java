package me.ckffmc.farm.item;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
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

    public TsuabingItem(Settings settings) { super(settings); }

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
        registerSyrup((byte) 0, Items.MILK_BUCKET, 0xe5e189);
        registerSyrup((byte) 1, Items.HONEY_BOTTLE, 0xd99821);
        registerTopping((byte) 0, Items.APPLE, 0xa94b03);
        registerTopping((byte) 1, MyItems.MANGO, 0xd17c15);
        registerTopping((byte) 2, Items.SWEET_BERRIES, 0xb01b04);
        registerTopping((byte) 3, MyItems.TOFU_PUDDING, 0xeddd64);
        registerTopping((byte) 4, MyItems.TAPIOCA_BALLS, 0x3b301c);
    }

    private static void registerSyrup(byte id, Item item, int color) {
        SYRUPS.put(item, id);
        SYRUP_COLOR.put(id, color);
    }

    private static void registerTopping(byte id, Item item, int color) {
        TOPPINGS.put(item, id);
        TOPPINGS_COLOR.put(id, color);
    }
}
