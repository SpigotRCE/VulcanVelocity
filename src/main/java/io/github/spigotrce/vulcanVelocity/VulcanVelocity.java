package io.github.spigotrce.vulcanVelocity;

import com.google.inject.Inject;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.plugin.Plugin;
import org.slf4j.Logger;

@Plugin(id = "vulcanvelocity",
        name = "VulcanVelocity",
        version = "1.0-SNAPSHOT",
        description = "Vulcan Bungee implementation in velocity",
        authors = {
        "SpigotRCE"
        }
)
public class VulcanVelocity {
    private final AlertManager alertManager;

    public VulcanVelocity() {
        this.alertManager = new AlertManager();
    }

    @Inject
    private Logger logger;

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
    }

    public AlertManager getAlertManager() {
        return alertManager;
    }
}
