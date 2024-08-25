package io.github.spigotrce.vulcanVelocity;

import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.ProxyServer;

public class VulcanVelocityCommand implements SimpleCommand {
    private final ProxyServer proxyServer;
    private final VulcanVelocity plugin;

    public VulcanVelocityCommand(ProxyServer proxyServer, VulcanVelocity plugin) {
        this.proxyServer = proxyServer;
        this.plugin = plugin;
    }

    @Override
    public void execute(Invocation invocation) {
    }
}
