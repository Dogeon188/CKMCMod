package me.ckffmc.farm.client.gui.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import me.ckffmc.farm.MainMod;
import me.ckffmc.farm.screen.TunScreenHandler;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class TunScreen extends HandledScreen<TunScreenHandler> {
    private static final Identifier BACKGROUND_TEXTURE = new Identifier(MainMod.MOD_ID, "textures/gui/tun.png");

    public TunScreen(TunScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        if (client != null) {
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            client.getTextureManager().bindTexture(BACKGROUND_TEXTURE);
            int x = (width - backgroundWidth) / 2;
            int y = (height - backgroundHeight) / 2;
            drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);
            int l = handler.getCraftProgress();
            drawTexture(matrices, x + 76, y + 18, 176, 5, 20 + 1, 6);
            drawTexture(matrices, x + 54, y + 58, 176, 0, l + 1, 5);
            drawTexture(matrices, x + 77, y + 65, 176, 0, l + 1, 5);
            drawTexture(matrices, x + 100, y + 58, 176, 0, l + 1, 5);
        }
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
