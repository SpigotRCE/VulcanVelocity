package io.github.spigotrce.vulcanVelocity;

import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class VulcanVelocityCommand implements SimpleCommand {
    private final ProxyServer proxyServer;
    private final VulcanVelocity plugin;

    public VulcanVelocityCommand(ProxyServer proxyServer, VulcanVelocity plugin) {
        this.proxyServer = proxyServer;
        this.plugin = plugin;
    }

    @Override
    public void execute(Invocation invocation) {
        if (invocation.source() instanceof Player player) {
            if (!player.hasPermission("vulcanbungee.alerts")) {
                player.sendMessage(Component.text("You do not have permission to execute this command."));
                return;
            }
            // TODO: add toggling
        } else {
            invocation.source().sendMessage(Component.text("Only players can execute this command!"));
        }
    }
}
