package me.moontimer.lobby.listener;

import me.moontimer.casual.api.game.visual.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Locale;

public class SettingsManager implements Listener {

    public static void openMainMenu(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 9, "§8× §aEinstellungen §8×");

        ItemStack placeholder = new ItemBuilder (Material.STAINED_GLASS_PANE).setDisplayName (" ").build ();

        for (int i = 0; i < 9; i++) {
            inventory.setItem (i, placeholder);
        }

        inventory.setItem( 2, new ItemBuilder(Material.BLAZE_POWDER).setDisplayName("§eParticle Settings").build());
        inventory.setItem( 4, new ItemBuilder(Material.FISHING_ROD).setDisplayName("§eGadget Settings").build());
        inventory.setItem( 6, new ItemBuilder(Material.GOLD_RECORD).setDisplayName("§eMusik Settings").build());

        player.openInventory(inventory);
    }

    @EventHandler
    public void onMainClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8× §aEinstellungen §8×")) {
            switch (event.getCurrentItem().getType()) {
                case BLAZE_POWDER:
                    player.closeInventory();
                    makeParticleInventory(player);
                    break;
            }

        }
    }

    private static void makeParticleInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 9, "§eParticle Settings");

        inventory.setItem(0, new ItemBuilder(Material.BLAZE_POWDER).setDisplayName("§eFeuer Ring").build());

        player.openInventory(inventory);
    }

}
