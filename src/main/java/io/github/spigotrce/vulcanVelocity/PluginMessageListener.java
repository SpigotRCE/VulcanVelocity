package io.github.spigotrce.vulcanVelocity;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
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
    public void onPluginMessage(PluginMessageEvent event) {
        if (!event.getIdentifier().getId().equals("vulcan:bungee") || !(event.getSource() instanceof ServerConnection)) return;

        event.setResult(PluginMessageEvent.ForwardResult.handled());
        ByteArrayDataInput in = ByteStreams.newDataInput(event.getData());
        switch (in.readUTF()) {
            case "alert": {
                vulcanVelocity.getAlertManager().handleAlert(event.getSource(), in);
                break;
            }
            case "punishment": {
                vulcanVelocity.getAlertManager().handlePunishment((ServerConnection)((Object)event.getSource()), in);
            }
        }
    }
}
