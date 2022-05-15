package me.moontimer.lobby.listener;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import de.dytanic.cloudnet.lib.player.permission.GroupEntityData;
import de.dytanic.cloudnet.lib.player.permission.PermissionPool;
import me.moontimer.casual.api.game.visual.ItemBuilder;
import me.moontimer.lobby.Lobby;
import me.moontimer.lobby.utils.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class ShopListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityClick(PlayerInteractEntityEvent event) {
        if (event.getRightClicked() instanceof Villager) {
            openShopInv(event.getPlayer());
        }
    }

    private static void openShopInv(Player player) {

        Inventory inventory = Bukkit.createInventory (player, 9, "§8× §aShop §8×");
        ItemStack placeholder = new ItemBuilder (Material.STAINED_GLASS_PANE).setDisplayName (" ").build ();

        for (int i = 0; i < 9; i++) {
            inventory.setItem (i, placeholder);
        }

        inventory.setItem(4, new ItemBuilder(Material.WOOL).setDisplayName("§eRänge").build());
        inventory.setItem(2, new ItemBuilder(Material.FISHING_ROD).setDisplayName("§eGadgets").build());
        inventory.setItem(6, new ItemBuilder(Material.BARRIER).setDisplayName("§eKommt noch").build());

        player.openInventory (inventory);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();
        CloudPlayer cloudPlayer = CloudAPI.getInstance().getOnlinePlayer(player.getUniqueId());
        //IPermissionPlayer permissionPlayer = PermissionPool.getInstance().getPermissionPlayerManager().getCachedPermissionPlayer(player.getUniqueId());
        PlayerManager playerManager = new PlayerManager(player);

        if(event.getClickedInventory() == null) return;
        if(event.getClickedInventory().getName() == null) return;

        if(event.getClickedInventory().getName().equalsIgnoreCase("§8× §aShop §8×")) {
            switch (event.getCurrentItem().getType()) {
                case FISHING_ROD:
                    player.closeInventory();
                    openGadgetInv(player);
                    break;
                case WOOL:
                    player.closeInventory();
                    openRankInv(player);
                    break;
                default:
                    break;
            }
        }

        if (event.getClickedInventory().getName().equalsIgnoreCase("§8× §aGadget §8×")) {
            switch (event.getCurrentItem().getType()) {
                case FISHING_ROD:
                    if (Lobby.getCoinAPI().getCoins(player.getUniqueId()) < 5000) {
                        player.sendMessage(Lobby.getPrefix() + "§cDu hast zu wenig Coins");
                    } else {
                        Lobby.getCoinAPI().removeCoins(player.getUniqueId(), 5000);

                        //assert permissionPlayer != null;
                        //permissionPlayer.addPermission(new Permission("lobby.gadget.rod", -1, true));
                        cloudPlayer.getPermissionEntity().getPermissions().put("lobby.gadget.rod", true);

                        player.sendMessage(Lobby.getPrefix() + "Du hast erfolgreich die Angel gekauft");

                        playerManager.scoreboard(player.getUniqueId(), player);

                        player.closeInventory();
                    }
                    break;
                case FEATHER:
                    if (Lobby.getCoinAPI().getCoins(player.getUniqueId()) < 5000) {
                        player.sendMessage(Lobby.getPrefix() + "§cDu hast zu wenig Coins");
                    } else {
                        Lobby.getCoinAPI().removeCoins(player.getUniqueId(), 5000);

                        //assert permissionPlayer != null;
                        //permissionPlayer.addPermission(new Permission("lobby.gadget.feather", -1, true));
                        cloudPlayer.getPermissionEntity().getPermissions().put("lobby.gadget.feather", true);

                        player.sendMessage(Lobby.getPrefix() + "Du hast erfolgreich die Feder gekauft");

                        playerManager.scoreboard(player.getUniqueId(), player);

                        player.closeInventory();
                    }
                    break;
                default:
                    break;
            }
        }
        if (event.getClickedInventory().getName().equalsIgnoreCase("§8× §aRänge §8×")) {
            switch (event.getCurrentItem().getType()) {
                case IRON_INGOT:
                    if (Lobby.getCoinAPI().getCoins(player.getUniqueId()) < 25000) {
                        player.sendMessage(Lobby.getPrefix() + "§cDu hast zu wenig Coins");
                    } else {

                        Lobby.getCoinAPI().removeCoins(player.getUniqueId(), 25000);

                        long time = System.currentTimeMillis() + TimeUnit.DAYS.toMillis(30);

                        //assert permissionPlayer != null;
                        //permissionPlayer.addPermissionGroup(new PlayerPermissionGroupInfo("Premium", time));
                        //permissionPlayer.update();

                        cloudPlayer.getPermissionEntity().getGroups().add(new GroupEntityData("Premium", time));



                        player.sendMessage(Lobby.getPrefix() + "Du hast dir erfolgreich den Premium Rang gekauft");

                        playerManager.scoreboard(player.getUniqueId(), player);

                        player.closeInventory();

                        player.sendTitle("§aDu hast nun", "§e§lPremium");
                    }
                    break;
                case DIAMOND:
                    if (Lobby.getCoinAPI().getCoins(player.getUniqueId()) < 50000) {
                        player.sendMessage(Lobby.getPrefix() + "§cDu hast zu wenig Coins");
                    } else {

                        Lobby.getCoinAPI().removeCoins(player.getUniqueId(), 50000);

                        long time = System.currentTimeMillis() + TimeUnit.DAYS.toMillis(30);

                        //assert permissionPlayer != null;
                        //permissionPlayer.addPermissionGroup(new PlayerPermissionGroupInfo("Prime", time));
                        //permissionPlayer.update();

                        cloudPlayer.getPermissionEntity().getGroups().add(new GroupEntityData("Prime", time));

                        player.sendMessage(Lobby.getPrefix() + "Du hast dir erfolgreich den Prime Rang gekauft");

                        playerManager.scoreboard(player.getUniqueId(), player);

                        player.closeInventory();

                        player.sendTitle("§aDu hast nun", "§5Prime");
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void openGadgetInv(Player player) {
        Inventory inventory = Bukkit.createInventory (player, 9, "§8× §aGadget §8×");

        ItemStack placeholder = new ItemBuilder (Material.STAINED_GLASS_PANE).setDisplayName (" ").build ();

        for (int i = 0; i < 9; i++) {
            inventory.setItem (i, placeholder);
        }

        inventory.setItem(2, new ItemBuilder(Material.FISHING_ROD).setDisplayName("§eAngel").setLore("§8§l» §65000 Coins").build());
        inventory.setItem(4, new ItemBuilder(Material.FEATHER).setDisplayName("§eFeder").setLore("§8§l» §65000 Coins").build());
        inventory.setItem(6, new ItemBuilder(Material.BARRIER).setDisplayName("§eMehr").build());

        player.openInventory(inventory);
    }

    private void openRankInv(Player player) {
        Inventory inventory = Bukkit.createInventory (player, 9, "§8× §aRänge §8×");

        ItemStack placeholder = new ItemBuilder (Material.STAINED_GLASS_PANE).setDisplayName (" ").build ();

        for (int i = 0; i < 9; i++) {
            inventory.setItem (i, placeholder);
        }

        inventory.setItem(3, new ItemBuilder(Material.IRON_INGOT).setDisplayName("§ePremium").setLore("§8§l» §630 Tage", "§8§l» §625000 Coins").build());
        inventory.setItem(5, new ItemBuilder(Material.DIAMOND).setDisplayName("§ePrime").setLore("§8§l» §630 Tage", "§8§l» §650000 Coins").build());

        player.openInventory(inventory);
    }
}
