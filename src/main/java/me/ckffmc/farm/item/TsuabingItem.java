package me.ckffmc.farm.item;

import me.ckffmc.farm.recipe.TsuabingRecipe;
import me.ckffmc.farm.util.TextUtil;
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

import java.util.ArrayList;
import java.util.List;

public class TsuabingItem extends Item {
    public TsuabingItem(Settings settings) { super(settings); }

    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext ctx) {
        CompoundTag tag = stack.getSubTag("Tsuabing");
        if (tag != null) {
            if (tag.contains("Syrup", 7)) {
                byte[] bytes = tag.getByteArray("Syrup");
                if (bytes.length != 0) {
                    for (byte syrup : bytes) {
                        Identifier id = Registry.ITEM.getId(TsuabingRecipe.INV_INGREDIENTS.get(syrup));
                        tooltip.add(new TranslatableText("item." + id.getNamespace() + "." + id.getPath())
                                .formatted(Formatting.GRAY));
                    }
                }
            }
            if (tag.contains("Toppings", 7)) {
                byte[] bytes = tag.getByteArray("Toppings");
                if (bytes.length != 0) {
                    for (byte topping : bytes) {
                        Identifier id = Registry.ITEM.getId(TsuabingRecipe.INV_INGREDIENTS.get(topping));
                        tooltip.add(new TranslatableText("item." + id.getNamespace() + "." + id.getPath())
                                .formatted(Formatting.GRAY));
                    }
                }
            }
        }
    }
}
