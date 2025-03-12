package org.Hollow_Realms.hollow_realms.client;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {
    public static KeyBinding[] spellKeys = new KeyBinding[9];

    public static void register() {
        for (int i = 0; i < spellKeys.length; i++) {
            spellKeys[i] = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.hollow_realms.spell" + (i + 1),
                GLFW.GLFW_KEY_1 + i,
                "category.hollow_realms.spells"
            ));
        }
    }
}