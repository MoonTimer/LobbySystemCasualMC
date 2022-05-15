package me.moontimer.lobby.commands;

import me.moontimer.lobby.Lobby;
import me.moontimer.lobby.utils.LocationManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftVillager;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SetShopCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (!player.hasPermission("lobby.setshop")) {
            player.sendMessage(Lobby.getPrefix() + Lobby.getNoPerm());
            return true;
        }
        if (!(args.length == 0)) {
            player.sendMessage(Lobby.getPrefix() + "Nur /setshop");
            return true;
        }

        Lobby.getLocationManager().setLocation("Shop", player.getLocation());
        player.sendMessage(Lobby.getPrefix() + "Der Shop wurde erfolgreich gesetzt");

        Villager villager = (Villager)player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.VILLAGER);
        ((CraftVillager)villager).getHandle().setProfession(5);
        villager.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100000, 100000));
        villager.setCustomNameVisible(true);
        villager.setCustomName("§eShop");

        return false;
    }
}
