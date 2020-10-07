package me.ckffmc.farm.client;

import me.ckffmc.farm.MainMod;
import me.ckffmc.farm.client.render.RenderLayerSetups;
import me.ckffmc.farm.client.render.entity.OysterEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class MainClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        RenderLayerSetups.renderLayerSetups();
        EntityRendererRegistry.INSTANCE.register(MainMod.OYSTER, (dispatcher, context) -> new OysterEntityRenderer(dispatcher));
    }
}
