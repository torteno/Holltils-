package org.Hollow_Realms.hollow_realms;

import net.fabricmc.api.ModInitializer;
import org.Hollow_Realms.hollow_realms.client.KeyBindings;

public class Hollow_realms implements ModInitializer {

    @Override
    public void onInitialize() {
        KeyBindings.register();
    }
}
