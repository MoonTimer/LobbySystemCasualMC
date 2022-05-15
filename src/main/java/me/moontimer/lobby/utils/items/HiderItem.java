package me.moontimer.lobby.utils.items;

import me.moontimer.casual.api.game.visual.ItemBuilder;
import me.moontimer.lobby.Lobby;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Dye;

import javax.swing.*;

public class HiderItem implements Listener {

    @EventHandler
    public void onAction(PlayerInteractEvent e) {
        try {
            if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8× §aSpieler Verstecker §8×")) {
                Inventory inv = Bukkit.createInventory(null, 1*9, e.getItem().getItemMeta().getDisplayName());

                for(int i = 0; i < inv.getSize(); i++) {
                    inv.setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE, 15).setDisplayName("§9").build());
                }

                inv.setItem(3, new ItemBuilder(Material.INK_SACK, 10).setDisplayName("§8× §eAnzeigen §8×").build());
                inv.setItem(5, new ItemBuilder(Material.INK_SACK, 1).setDisplayName("§8× §eVerstecken §8×").build());

                e.getPlayer().openInventory(inv);
            }

        } catch (NullPointerException ignored) {
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if(e.getClickedInventory() == null) return;
        if(e.getClickedInventory().getName() == null) return;
        if(e.getClickedInventory().getName().equalsIgnoreCase("§8× §aSpieler Verstecker §8×")) {

            if(e.getCurrentItem() == null)return;

            if(e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE)return;

            Player player = (Player) e.getWhoClicked();

            ItemStack greenDye = new ItemStack(Material.INK_SACK, 1, (short) 10);
            ItemStack redDye = new ItemStack(Material.INK_SACK, 1, (short) 1);

            if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§8× §eAnzeigen §8×")) {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    player.showPlayer(players);
                }
                player.sendMessage(Lobby.getPrefix() + "§7Alle Spieler werden nun wieder §aangezeigt");
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§8× §eVerstecken §8×")) {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    player.hidePlayer(players);
                }
                player.sendMessage(Lobby.getPrefix() + "§7Alle Spieler werden nun nicht mehr §cangezeigt");
            }
        }
    }

}
