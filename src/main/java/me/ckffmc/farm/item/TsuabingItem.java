package me.ckffmc.farm.item;

import me.ckffmc.farm.recipe.TsuabingRecipe;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
    public TsuabingItem(Settings settings) { super(settings); }

    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext ctx) {
        buildTooltip(tooltip, stack);
    }

    private static void buildTooltip(List<Text> tooltip, ItemStack stack) {
        CompoundTag tag = stack.getSubTag("Tsuabing");
        if (tag != null) {
            if (tag.contains("Syrup", 7)) {
                byte[] bytes = tag.getByteArray("Syrup");
                if (bytes.length != 0) {
                    Map<Byte, Integer> map = new HashMap<>();
                    for (byte syrup : bytes) {
                        if (!map.containsKey(syrup)) map.put(syrup, 1);
                        else map.replace(syrup, map.get(syrup) + 1);
                    }
                    map.forEach((k, v) -> tooltip.add(buildTooltipText(k, v)));
                }
            }
            if (tag.contains("Toppings", 7)) {
                byte[] bytes = tag.getByteArray("Toppings");
                if (bytes.length != 0) {
                    Map<Byte, Integer> map = new HashMap<>();
                    for (byte topping : bytes) {
                        if (!map.containsKey(topping)) map.put(topping, 1);
                        else map.replace(topping, map.get(topping) + 1);
                    }
                    map.forEach((k, v) -> tooltip.add(buildTooltipText(k, v)));
                }
            }
        }
    }

    private static Text buildTooltipText(byte k, int v) {
        Identifier id = Registry.ITEM.getId(TsuabingRecipe.INV_INGREDIENTS.get(k));
        MutableText text = new TranslatableText("item." + id.getNamespace() + "." + id.getPath());
        return ((v <= 1) ? text : new TranslatableText("text.dogeon.multiply", text, v))
                .formatted(Formatting.GRAY);
    }
}
