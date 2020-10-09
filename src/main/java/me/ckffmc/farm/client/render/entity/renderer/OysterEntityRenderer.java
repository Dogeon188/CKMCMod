package me.ckffmc.farm.client.render.entity.renderer;

import me.ckffmc.farm.client.render.entity.model.OysterEntityModel;
import me.ckffmc.farm.entity.OysterEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class OysterEntityRenderer extends MobEntityRenderer<OysterEntity, OysterEntityModel> {
    public OysterEntityRenderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher, new OysterEntityModel(), 0.25f);
    }

    @Override
    public Identifier getTexture(OysterEntity entity) {
        return new Identifier("ckfarm", "textures/entity/oyster.png");
    }
}
