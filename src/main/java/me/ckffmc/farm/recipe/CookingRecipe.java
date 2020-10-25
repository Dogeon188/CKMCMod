package me.ckffmc.farm.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
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

public class CookingRecipe implements Recipe<Inventory> {
    public static final int DEFAULT_COOK_TIME = 200;
    public static final float DEFAULT_EXPERIENCE = 0.4F;
    protected final Identifier id;
    protected final DefaultedList<Ingredient> ingredients;
    protected final ItemStack result;
    protected final int cookTime;
    protected final float experience;

    public CookingRecipe(Identifier id, DefaultedList<Ingredient> ingredients, ItemStack result, int cookTime, float experience) {
        this.id = id;
        this.ingredients = ingredients;
        this.result = result;
        this.cookTime = cookTime;
        this.experience = experience;
    }

    public boolean matches(Inventory inv, World world) {
        RecipeFinder finder = new RecipeFinder();
        int i = 0;
        for (int j = 0; j < 4; j++) {
            ItemStack stack = inv.getStack(j);
            if (!stack.isEmpty()) {
                ++i;
                finder.method_20478(stack, 1);
            }
        }
        return i == this.ingredients.size() && finder.findRecipe(this, null);
    }

    public ItemStack craft(Inventory inv) {
        return this.result.copy();
    }

    @Environment(EnvType.CLIENT)
    public boolean fits(int width, int height) { return true; }

    public DefaultedList<Ingredient> getPreviewInputs() {
        return this.ingredients;
    }

    public ItemStack getOutput() { return this.result; }

    public int getCookTime() { return cookTime; }

    public float getExperience() { return experience; }

    public Identifier getId() { return this.id; }

    public RecipeSerializer<?> getSerializer() { return MyRecipeSerializer.COOKING; }

    public RecipeType<?> getType() { return MyRecipeType.COOKING; }

    public static class Serializer implements RecipeSerializer<CookingRecipe> {
        public CookingRecipe read(Identifier identifier, JsonObject jsonObject) {
            DefaultedList<Ingredient> ingredients = getIngredients(JsonHelper.getArray(jsonObject, "ingredients"));
            if (ingredients.isEmpty()) throw new JsonParseException("No ingredients for cooking recipe");
            if (ingredients.size() > 4) throw new JsonParseException("Too many ingredients for cooking recipe");
            ItemStack result = ShapedRecipe.getItemStack(JsonHelper.getObject(jsonObject, "result"));
            int cookTime = JsonHelper.getInt(jsonObject, "cook_time", DEFAULT_COOK_TIME);
            float experience = JsonHelper.getFloat(jsonObject, "experience", DEFAULT_EXPERIENCE);
            return new CookingRecipe(identifier, ingredients, result, cookTime, experience);
        }

        private static DefaultedList<Ingredient> getIngredients(JsonArray json) {
            DefaultedList<Ingredient> ingredients = DefaultedList.of();
            for(int i = 0; i < json.size(); ++i) {
                Ingredient ingredient = Ingredient.fromJson(json.get(i));
                if (!ingredient.isEmpty()) ingredients.add(ingredient);
            }
            return ingredients;
        }

        public CookingRecipe read(Identifier identifier, PacketByteBuf packetByteBuf) {
            int i = packetByteBuf.readVarInt();
            DefaultedList<Ingredient> ingredients = DefaultedList.ofSize(i, Ingredient.EMPTY);
            for(int j = 0; j < ingredients.size(); ++j) ingredients.set(j, Ingredient.fromPacket(packetByteBuf));
            ItemStack result = packetByteBuf.readItemStack();
            int cookTime = packetByteBuf.readVarInt();
            float experience = packetByteBuf.readFloat();
            return new CookingRecipe(identifier, ingredients, result, cookTime, experience);
        }

        public void write(PacketByteBuf packetByteBuf, CookingRecipe cookingRecipe) {
            packetByteBuf.writeVarInt(cookingRecipe.ingredients.size());
            for (Ingredient ingredient : cookingRecipe.ingredients) ingredient.write(packetByteBuf);
            packetByteBuf.writeItemStack(cookingRecipe.result);
            packetByteBuf.writeVarInt(cookingRecipe.cookTime);
            packetByteBuf.writeFloat(cookingRecipe.experience);
        }
    }
}
