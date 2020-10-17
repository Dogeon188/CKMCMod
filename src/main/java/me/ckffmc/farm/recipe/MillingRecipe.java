package me.ckffmc.farm.recipe;

import com.google.gson.JsonObject;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public class MillingRecipe implements Recipe<Inventory> {
    protected final Identifier id;
    protected final Ingredient ingredient;
    protected final ItemStack result;

    public MillingRecipe(Identifier id, Ingredient ingredient, ItemStack result) {
        this.id = id;
        this.ingredient = ingredient;
        this.result = result;
    }

    public boolean matches(Inventory inv, World world) {
        return this.ingredient.test(inv.getStack(0));
    }

    public ItemStack craft(Inventory inv) {
        return this.result.copy();
    }

    @Environment(EnvType.CLIENT)
    public boolean fits(int width, int height) { return true; }

    public DefaultedList<Ingredient> getPreviewInputs() {
        DefaultedList<Ingredient> defaultedList = DefaultedList.of();
        defaultedList.add(this.ingredient);
        return defaultedList;
    }

    public ItemStack getOutput() { return this.result; }

    public Identifier getId() { return this.id; }

    @Override
    public RecipeSerializer<?> getSerializer() { return MyRecipeSerializer.MILLING; }

    public RecipeType<?> getType() { return MyRecipeType.MILLING; }

    public static class Serializer implements RecipeSerializer<MillingRecipe> {
        public MillingRecipe read(Identifier identifier, JsonObject jsonObject) {
            Ingredient ingredient = Ingredient.fromJson(JsonHelper.getObject(jsonObject, "ingredient"));
            ItemStack itemStack = ShapedRecipe.getItemStack(JsonHelper.getObject(jsonObject, "result"));
            return new MillingRecipe(identifier, ingredient, itemStack);
        }

        public MillingRecipe read(Identifier identifier, PacketByteBuf packetByteBuf) {
            Ingredient ingredient = Ingredient.fromPacket(packetByteBuf);
            ItemStack itemStack = packetByteBuf.readItemStack();
            return new MillingRecipe(identifier, ingredient, itemStack);
        }

        public void write(PacketByteBuf packetByteBuf, MillingRecipe millingRecipe) {
            millingRecipe.ingredient.write(packetByteBuf);
            packetByteBuf.writeItemStack(millingRecipe.result);
        }
    }
}
