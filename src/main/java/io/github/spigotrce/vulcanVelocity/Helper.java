package io.github.spigotrce.vulcanVelocity;

import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.adventure.text.Component;

public class Helper {
    public void broadcast(ProxyServer proxyServer, VulcanVelocity plugin, Component component) {
        proxyServer.getAllPlayers().forEach(player -> {
            if (player.hasPermission("vulcanbungee.alerts"))
                player.sendMessage(component);
        });
    }
}
