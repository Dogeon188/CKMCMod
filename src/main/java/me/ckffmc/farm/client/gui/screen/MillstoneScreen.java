package me.ckffmc.farm.client.gui.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import me.ckffmc.farm.screen.MillstoneScreenHandler;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.AbstractFurnaceScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class MillstoneScreen extends HandledScreen<ScreenHandler> {
    private static final Identifier TEXTURE = new Identifier("ckfarm", "textures/gui/mill.png");

    public MillstoneScreen(ScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        assert client != null;
        client.getTextureManager().bindTexture(TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);
        int l = ((MillstoneScreenHandler)this.handler).getCraftProgress();
        this.drawTexture(matrices, x + 74, y + 45, 176, 2, l + 1, 16);

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
