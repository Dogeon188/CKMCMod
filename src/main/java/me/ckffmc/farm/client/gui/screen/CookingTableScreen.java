package me.ckffmc.farm.client.gui.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import me.ckffmc.farm.MainMod;
import me.ckffmc.farm.screen.CookingTableScreenHandler;
import me.ckffmc.farm.screen.MillstoneScreenHandler;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class CookingTableScreen extends HandledScreen<CookingTableScreenHandler> {
    private static final Identifier BACKGROUND_TEXTURE = new Identifier(MainMod.MOD_ID, "textures/gui/cooking.png");

    public CookingTableScreen(CookingTableScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        assert client != null;
        client.getTextureManager().bindTexture(BACKGROUND_TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);
        int l = handler.getCraftProgress();
        drawTexture(matrices, x + 84, y + 36, 178, 2, l + 1, 16);
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }

    protected void init() {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }
}
