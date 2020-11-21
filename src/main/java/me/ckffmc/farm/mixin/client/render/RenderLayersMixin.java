package me.ckffmc.farm.mixin.client.render;

import me.ckffmc.farm.block.MyBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderLayers.class)
public class RenderLayersMixin {
    @Inject(method = {"getBlockLayer", "getMovingBlockLayer"}, cancellable = true, at = @At(value = "HEAD"))
    private static void exceptionForMangoLeaves(BlockState state, CallbackInfoReturnable<RenderLayer> cir) {
        if (state.isOf(MyBlocks.MANGO_LEAVES)) cir.setReturnValue(RenderLayer.getCutoutMipped());
    }
}
