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

public class TeleportItem implements Listener {

    @EventHandler
    public void onAction(PlayerInteractEvent e) {
        try {

            if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8× §aTeleporter §8×")) {
                Inventory inv = Bukkit.createInventory(null, 5*9, e.getItem().getItemMeta().getDisplayName());

                for(int i = 0; i < inv.getSize(); i++) {
                    inv.setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE, 15).setDisplayName("§9").build());
                }

                inv.setItem(13, new ItemBuilder(Material.MAGMA_CREAM).setDisplayName("§8× §eSpawn §8×").build());

                inv.setItem(33, new ItemBuilder(Material.IRON_PICKAXE).setDisplayName("§8× §eCitybuild §8×").build());
                inv.setItem(29, new ItemBuilder(Material.BED).setDisplayName("§8× §eBedWars §8×").build());
                inv.setItem(31, new ItemBuilder(Material.BLAZE_ROD).setDisplayName("§8× §eMLGRush §8×").build());

                e.getPlayer().openInventory(inv);
            }

        } catch (NullPointerException ignored) {
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if(e.getClickedInventory() == null)return;
        if(e.getClickedInventory().getName() == null)return;
        if(e.getClickedInventory().getName().equalsIgnoreCase("§8× §aTeleporter §8×")) {

            if(e.getCurrentItem() == null)return;
            if(e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE)return;

            Player p = (Player) e.getWhoClicked();

            if(e.getCurrentItem().getType() == Material.BARRIER) {
                Lobby.getCasualAPI().getVisualAPI().sendSound(p, Sound.NOTE_BASS);
                return;
            }

            String locname = e.getCurrentItem().getItemMeta().getDisplayName().replace(" §8×", "").replace("§8× §e", "").replace("§8× §c", "").replace(" ", "");
            Location loc = Lobby.getLocationManager().getLocation(locname);

            if(loc == null) {
                Lobby.getCasualAPI().getVisualAPI().sendSound(p, Sound.NOTE_BASS);
                return;
            }

            p.teleport(loc);
            Lobby.getCasualAPI().getVisualAPI().sendSound(p, Sound.NOTE_PLING);
        }
    }

}

