package me.moontimer.lobby.commands;

import jdk.jfr.internal.tool.Main;
import me.moontimer.lobby.Lobby;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LocationCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (!player.hasPermission("lobby.setLocation")) {
            player.sendMessage(Lobby.getPrefix() + Lobby.getNoPerm());
            return true;
        }
        if (args.length == 0) {
            player.sendMessage(Lobby.getPrefix() + "§cSyntax: /location <Name>");
            return true;
        }

        String name = args[0];
        if(Lobby.getLocationManager().getLocation(name) != null) {
            player.sendMessage(Lobby.getPrefix() + "§cDiese Location gibt es schon.");
            return true;
        }

        Lobby.getLocationManager().setLocation(name, player.getLocation());
        player.sendMessage(Lobby.getPrefix() + "§cDie Position §a" + name + " §c wurde gesetzt.");
        return false;
    }
}
