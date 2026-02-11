package com.zzh.spear_origin.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.zzh.spear_origin.SpearOrigin; // 你的主类包名
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class SpearReforgingScreen extends HandledScreen<SpearReforgingScreenHandler> {
    // 指向你的 GUI 贴图路径
    private static final Identifier TEXTURE = new Identifier("minecraft", "textures/gui/container/dispenser.png");

    public SpearReforgingScreen(SpearReforgingScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        // 这里可以调整标题的位置
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);

        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        // 1. 画背景图
        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

        // 2. 画进度条 (如果有进度)
        renderProgressArrow(context, x, y);
    }

    private void renderProgressArrow(DrawContext context, int x, int y) {
        if(handler.isCrafting()) {
            // 参数：贴图, 屏幕X, 屏幕Y, 贴图上的U(X), 贴图上的V(Y), 宽度, 高度
            // 这里的 176, 0 是指你的 GUI 贴图中，进度条“充满状态”的起始位置
            // handler.getScaledProgress() 计算出当前应该画多宽
            context.drawTexture(TEXTURE, x + 85, y + 30, 176, 0, handler.getScaledProgress(), 8);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}