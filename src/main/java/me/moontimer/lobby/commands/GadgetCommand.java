package me.moontimer.lobby.commands;

import me.moontimer.casual.api.database.MySQL;
import me.moontimer.casual.api.game.visual.ItemBuilder;
import me.moontimer.lobby.Lobby;
import me.moontimer.lobby.utils.GadgetManager;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Locale;
import java.util.UUID;

public class GadgetCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(Lobby.getPrefix() + "§cSyntax: /gadget <[Angel, Feder, mehr kommt noch]>");
            return true;
        }

        UUID uuid = player.getUniqueId();
        if (args.length == 1) {
            switch (args[0].toLowerCase(Locale.ROOT)) {
                case "angel":
                    if (!player.hasPermission("lobby.gadget.rod")) {
                        player.sendMessage(Lobby.getPrefix() + "§cDu besitzt dieses Gadget nicht!");
                        return true;
                    }
                    Lobby.getGadgetManager().setGadget(uuid, "Angel");
                    player.sendMessage(Lobby.getPrefix() + "§7Dein Gadget wurde gesetzt");
                    Lobby.getGadgetManager().setGadget(player.getUniqueId(), "Angel");
                    player.getInventory().setItem(6, new ItemBuilder(Material.FISHING_ROD).setDisplayName("§8× §aAngel §8×").build());
                    break;
                case "feder":
                    if (!player.hasPermission("lobby.gadget.feather")) {
                        player.sendMessage(Lobby.getPrefix() + "§cDu besitzt dieses Gadget nicht!");
                        return true;
                    }
                    Lobby.getGadgetManager().setGadget(uuid, "Feder");
                    player.sendMessage(Lobby.getPrefix() + "§7Dein Gadget wurde gesetzt");
                    Lobby.getGadgetManager().setGadget(player.getUniqueId(), "Feder");
                    player.getInventory().setItem(6, new ItemBuilder(Material.FEATHER).setDisplayName("§8× §aFeder §8×").build());
                    break;
                case "gibtsnicht":
                    Lobby.getGadgetManager().setGadget(uuid, "Tramp");
                    player.sendMessage(Lobby.getPrefix() + "§7Dein Gadget wurde gesetzt");
                    Lobby.getGadgetManager().setGadget(player.getUniqueId(), "Tramp");
                    player.getInventory().setItem(6, new ItemBuilder(Material.SLIME_BLOCK).setDisplayName("§8× §aTrampoline §8×").build());
                    break;
                default:
                    player.sendMessage(Lobby.getPrefix() + "§cDieses Gadget gibt es nicht");
            }
        }

        return false;
    }
}
