package org.Hollow_Realms.hollow_realms.client;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

public class ConfigScreen extends Screen {
    private static final KeyBinding[] abilityKeys = new KeyBinding[5];
    private boolean firstSpellOnLeftClickEnabled = false;

    public ConfigScreen(Text title) {
        super(title);
        // Initialize default key bindings if not already set
        for (int i = 0; i < abilityKeys.length; i++) {
            if (abilityKeys[i] == null) {
                abilityKeys[i] = new KeyBinding(
                    "key.hollow_realms.ability" + (i + 1),
                    -1, // Default to unbound
                    "category.hollow_realms.abilities"
                );
                KeyBindingHelper.registerKeyBinding(abilityKeys[i]);
            }
        }
    }

    @Override
    protected void init() {
        super.init();
        // Create buttons for configuring each key binding
        for (int i = 0; i < abilityKeys.length; i++) {
            int index = i; // For use in lambda
            addDrawableChild(new ButtonWidget(width / 2 - 100, height / 6 + i * 24, 200, 20,
                Text.translatable(abilityKeys[i].getTranslationKey()),
                button -> client.setScreen(new KeyBindingScreen(this, abilityKeys[index]))
            ));
        }
        // Add sliders for enabling/disabling features
        addDrawableChild(new ButtonWidget(width / 2 - 100, height / 6 + 24 * abilityKeys.length, 200, 20,
            Text.translatable(firstSpellOnLeftClickEnabled ? "Disable First Spell on Left Click" : "Enable First Spell on Left Click"),
            button -> {
                firstSpellOnLeftClickEnabled = !firstSpellOnLeftClickEnabled;
                button.setMessage(Text.translatable(firstSpellOnLeftClickEnabled ? "Disable First Spell on Left Click" : "Enable First Spell on Left Click"));
            }
        ));
    }

    private void toggleFirstSpellOnLeftClick() {
        firstSpellOnLeftClickEnabled = !firstSpellOnLeftClickEnabled;
        // Additional logic to handle the actual activation of the first spell on left click
    }
}
