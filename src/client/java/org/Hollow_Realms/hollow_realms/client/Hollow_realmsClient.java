package org.Hollow_Realms.hollow_realms.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
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
                if (toggleSpellModeKey.wasPressed()) {
                    spellCastingMode = !spellCastingMode;
                }

                if (spellCastingMode) {
                    for (int i = 0; i < KeyBindings.spellKeys.length; i++) {
                        if (KeyBindings.spellKeys[i].wasPressed()) {
                            castSpell(player.getInventory().selectedSlot, i);
                        }
                    }
                }
            }
        });
    }

    private void castSpell(int currentSlot, int spellOffset) {
        int spellSlot = currentSlot + spellOffset - (KeyBindings.spellKeys.length / 2);
        // Ensure the spell slot is within the valid range
        if (spellSlot >= 0 && spellSlot < player.getInventory().size()) {
            System.out.println("Casting spell from slot: " + spellSlot);
        } else {
            System.out.println("Invalid spell slot: " + spellSlot);
        }
    }
}