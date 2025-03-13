package org.Hollow_Realms.hollow_realms.client;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

public class KeyBindingScreen extends Screen {
    private final Screen parent;
    private final KeyBinding keyBinding;

    protected KeyBindingScreen(Screen parent, KeyBinding keyBinding) {
        super(Text.translatable("key.hollow_realms.configureKey", keyBinding.getBoundKeyLocalizedText()));
        this.parent = parent;
        this.keyBinding = keyBinding;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        keyBinding.setBoundKey(InputUtil.Type.KEYSYM.createFromCode(keyCode));
        client.setScreen(parent);
        return true;
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        drawCenteredText(matrices, textRenderer, "Press a key for " + keyBinding.getTranslationKey(), width / 2, height / 2 - 10, 0xFFFFFF);
        super.render(matrices, mouseX, mouseY, delta);
    }
} 