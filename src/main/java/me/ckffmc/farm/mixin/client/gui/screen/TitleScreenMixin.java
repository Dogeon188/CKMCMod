package me.ckffmc.farm.mixin.client.gui.screen;

import net.minecraft.client.gui.screen.TitleScreen;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {
	private static final Random random = new Random();

	@Shadow private @Nullable String splashText;

	@Inject(at = @At("HEAD"), method = "init")
	private void splash(CallbackInfo info) {
		if (random.nextFloat() < 0.001F) splashText = "You have been hacked by Dogeon! (jk)";
		System.out.println("Now I know what this mixin stuff does ouo.");
	}
}