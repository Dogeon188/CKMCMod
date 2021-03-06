package me.ckffmc.farm.mixin.client.recipebook;

import me.ckffmc.farm.recipe.MyRecipeType;
import net.minecraft.client.recipebook.ClientRecipeBook;
import net.minecraft.client.recipebook.RecipeBookGroup;
import net.minecraft.recipe.Recipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientRecipeBook.class)
public class ClientRecipeBookMixin {
    @Inject(method = "getGroupForRecipe", cancellable = true, at = @At(value = "INVOKE",
            target = "Lorg/apache/logging/log4j/Logger;warn(Ljava/lang/String;[Lorg/apache/logging/log4j/util/Supplier;)V"))
    private static void suppressRecipeWarn(Recipe<?> recipe, CallbackInfoReturnable<RecipeBookGroup> cir) {
        if (recipe.getType() == MyRecipeType.COOKING || recipe.getType() == MyRecipeType.MILLING) {
            cir.setReturnValue(RecipeBookGroup.UNKNOWN);
        }
    }
}
