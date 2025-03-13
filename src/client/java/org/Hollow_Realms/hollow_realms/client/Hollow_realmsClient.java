package org.Hollow_Realms.hollow_realms.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import org.lwjgl.glfw.GLFW;
import net.minecraft.text.Text;

public class Hollow_realmsClient implements ClientModInitializer {

    private boolean spellCastingMode = false;
    private static final KeyBinding toggleSpellModeKey = new KeyBinding(
        "key.hollow_realms.toggleSpellMode",
        GLFW.GLFW_KEY_F,
        "category.hollow_realms.spells"
    );

    private static final KeyBinding configMenuKey = new KeyBinding(
        "key.hollow_realms.configMenu",
        GLFW.GLFW_KEY_G,
        "category.hollow_realms.config"
    );

    @Override
    public void onInitializeClient() {
        KeyBindings.register();
        KeyBindingHelper.registerKeyBinding(toggleSpellModeKey);
        KeyBindingHelper.registerKeyBinding(configMenuKey);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            ClientPlayerEntity player = MinecraftClient.getInstance().player;
            if (player != null) {
                handleSpellCastingMode(player);
                handleAbilityActivation();
            }
        });
    }

    private void handleSpellCastingMode(ClientPlayerEntity player) {
        KeyBinding swapHandsKey = MinecraftClient.getInstance().options.keySwapHands;
        if (InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), swapHandsKey.getDefaultKey().getCode())) {
            spellCastingMode = !spellCastingMode;
            if (spellCastingMode) {
                initializeSpellKeys(player.getInventory().selectedSlot);
            }
        }
    }

    private void initializeSpellKeys(int startingSlot) {
        int numSpells = 6; // Assuming up to 6 spells
        int inventorySize = 9; // Standard Minecraft hotbar size
        KeyBindings.spellKeys = new KeyBinding[numSpells];
    
        for (int i = 0, j = 0; i < numSpells && j < inventorySize; j++) {
            if (j != startingSlot - 1) { // Skip the starting slot
                int keyCode = GLFW.GLFW_KEY_1 + j; // Corresponding key code for slots
                KeyBindings.spellKeys[i] = new KeyBinding(
                    "key.hollow_realms.spell" + (i + 1),
                    keyCode,
                    "category.hollow_realms.spells"
                );
                KeyBindingHelper.registerKeyBinding(KeyBindings.spellKeys[i]);
                i++;
            }
        }
    }

    private void handleAbilityActivation() {
        // Placeholder for ability activation logic
    }

    private void castSpell(int spellNumber) {
        System.out.println("Casting spell number: " + spellNumber);
    }

    private long lastScrollTime = 0;
    private static final long SCROLL_DELAY = 250; // Delay in milliseconds

    private void handleScrolling(ClientPlayerEntity player) {
        if (System.currentTimeMillis() - lastScrollTime < SCROLL_DELAY) {
            if (spellCastingMode) {
                spellCastingMode = false; // Temporarily disable
            }
        } else {
            if (!spellCastingMode) {
                spellCastingMode = true; // Re-enable after delay
                initializeSpellKeys(player.getInventory().selectedSlot);
            }
        }
        lastScrollTime = System.currentTimeMillis();
    }
}