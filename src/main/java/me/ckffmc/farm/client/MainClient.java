package me.ckffmc.farm.client;

import me.ckffmc.farm.MyScreenHandlerType;
import me.ckffmc.farm.client.gui.screen.MillstoneScreen;
import me.ckffmc.farm.client.render.RenderSetups;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

@Environment(EnvType.CLIENT)
public class MainClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        RenderSetups.renderSetups();
        ScreenRegistry.register(MyScreenHandlerType.MILL_SCREEN_HANDLER, MillstoneScreen::new);
    }
}
