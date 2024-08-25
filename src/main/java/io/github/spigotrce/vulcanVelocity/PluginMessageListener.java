package io.github.spigotrce.vulcanVelocity;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.PluginMessageEvent;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.ServerConnection;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;

public class PluginMessageListener {
    private final ProxyServer proxyServer;
    private final VulcanVelocity vulcanVelocity;

    private final MinecraftChannelIdentifier identifier;

    public PluginMessageListener(ProxyServer proxyServer, VulcanVelocity plugin) {
        this.proxyServer = proxyServer;
        this.vulcanVelocity = plugin;
        this.identifier = MinecraftChannelIdentifier.create("vulcan", "bungee");
    }

    @Subscribe
    public void onPluginMessageReceived(PluginMessageEvent event) {
        if (!(event.getSource() instanceof ServerConnection) ||
                event.getIdentifier().equals(identifier) ||
                event.getData().length == 0) {
        }
    }
}
