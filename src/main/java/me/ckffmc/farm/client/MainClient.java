package me.ckffmc.farm.client;

import me.ckffmc.farm.client.render.RenderSetups;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class MainClient implements ClientModInitializer {
    public void onInitializeClient() {
        RenderSetups.renderSetups();
    }
}
