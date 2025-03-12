package org.Hollow_Realms.hollow_realms.client;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

public class ConfigScreen extends Screen {

    protected ConfigScreen(Text title) {
        super(title);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices); // Render the default background
        drawCenteredText(matrices, textRenderer, "Hollrealms Configuration", width / 2, height / 2 - 50, 0xFFFFFF);
        super.render(matrices, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }
} 