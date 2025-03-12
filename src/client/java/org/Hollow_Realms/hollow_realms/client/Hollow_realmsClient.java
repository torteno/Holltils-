package org.Hollow_Realms.hollow_realms.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

public class Hollow_realmsClient implements ClientModInitializer {

    private int spellModeSlot = -1;

    @Override
    public void onInitializeClient() {
        KeyBindings.register();


        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            ClientPlayerEntity player = MinecraftClient.getInstance().player;
            if (player != null) {
                for (int i = 0; i < KeyBindings.spellKeys.length; i++) {
                    if (KeyBindings.spellKeys[i].wasPressed()) {
                        castSpell(i + 1);
                    }
                }

                if (spellModeSlot != -1) {
                    int currentSlot = player.getInventory().selectedSlot;
                    if (currentSlot != spellModeSlot) {
                        spellModeSlot = currentSlot;
                    }
                }
            }
        });


    }

    private void castSpell(int spellSlot) {
        // Implement spell casting logic here
        System.out.println("Casting spell from slot: " + spellSlot);
    }
}