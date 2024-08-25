package io.github.spigotrce.vulcanVelocity;

import com.google.common.io.ByteArrayDataInput;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.ServerConnection;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class AlertManager {
    private final ArrayList<Player> toggledPlayers;
    private final VulcanVelocity plugin;
    private final ProxyServer server;

    public AlertManager(VulcanVelocity plugin, ProxyServer server) {
        this.toggledPlayers = new ArrayList<>();
        this.plugin = plugin;
        this.server = server;
    }

    public void handleAlert(ServerConnection connection, ByteArrayDataInput input) {
        String[] components = input.readUTF().split("#VULCAN#");
        String checkName = components[0];
        String checkType = components[1];
        String vl = components[2];
        String player = components[3];
        String maxVl = components[4];
        String clientVersion = components[5];
        String tps = components[6];
        String ping = components[7];
        String description = components[8];
        String info = components[9];
        String dev = components[10];
        String severity = components[11];
        String serverName = connection.getServerInfo().getName();
        TextComponent alertMessage = LegacyComponentSerializer.legacyAmpersand().deserialize(Config.ALERTS_FORMAT.replace("%check%", checkName).replace("%type%", checkType).replace("%vl%", vl).replace("%player%", player).replace("%max-vl%", maxVl).replace("%client-version%", clientVersion).replace("%tps%", tps).replace("%ping%", ping).replace("%info%", info).replace("%description%", description).replace("%server%", serverName).replace("%prefix", Config.PREFIX)).clickEvent(ClickEvent.runCommand("/" + Config.ALERTS_CLICK_COMMAND.replace("%player%", player).replace("%server%", serverName)));
        TextComponent.Builder hoverMessage = Component.text();
        int size = Config.ALERTS_HOVER_FORMAT.size();
        int i = 1;
        for (String line : Config.ALERTS_HOVER_FORMAT) {
            line = line.replace("%check%", checkName).replace("%type%", checkType).replace("%vl%", vl).replace("%player%", player).replace("%max-vl%", maxVl).replace("%client-version%", clientVersion).replace("%tps%", tps).replace("%ping%", ping).replace("%info%", info).replace("%description%", description).replace("%server%", serverName).replace("%dev%", dev).replace("%severity%", severity).replace("%prefix", Config.PREFIX);
            hoverMessage.append(LegacyComponentSerializer.legacyAmpersand().deserialize(line));
            if (i != size) {
                hoverMessage.append(Component.newline());
            }
            ++i;
        }
        alertMessage = alertMessage.hoverEvent(hoverMessage.build().asHoverEvent());
        for (Player staff : toggledPlayers) {
            if (staff.getCurrentServer().isPresent() && staff.getCurrentServer().get().getServerInfo().getName().equals(serverName))
                continue;
            staff.sendMessage(alertMessage);
        }
    }

    public void handlePunishment(ServerConnection connection, ByteArrayDataInput input) {
        String[] components = input.readUTF().split("#VULCAN#");
        String command = components[0];
        server.getCommandManager().executeAsync(server.getConsoleCommandSource(), command);
        String checkName = components[1];
        String checkType = components[2];
        String vl = components[3];
        String player = components[4];
        String maxVl = components[5];
        String serverName = connection.getServerInfo().getName();
        TextComponent punishmentMessage = LegacyComponentSerializer.legacyAmpersand().deserialize(Config.PUNISHMENT_FORMAT.replace("%check%", checkName).replace("%type%", checkType).replace("%vl%", vl).replace("%player%", player).replace("%max-vl%", maxVl).replace("%server%", serverName).replace("%prefix%", Config.PREFIX));
        for (Player staff : toggledPlayers) {
            if (staff.getCurrentServer().isPresent() && ((ServerConnection)staff.getCurrentServer().get()).getServerInfo().getName().equals(serverName)) continue;
            staff.sendMessage(punishmentMessage);
        }
    }
}
