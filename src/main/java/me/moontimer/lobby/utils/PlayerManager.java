package me.moontimer.lobby.utils;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.bridge.CloudServer;
import de.dytanic.cloudnet.lib.cloudserver.CloudServerMeta;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import fr.mrmicky.fastboard.FastBoard;
import me.moontimer.casual.api.game.visual.ItemBuilder;
import me.moontimer.casual.api.system.coins.CoinAPI;
import me.moontimer.lobby.Lobby;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;
import java.util.UUID;

public class PlayerManager {

    Player p;;
    HashMap<String, ItemStack> heads = new HashMap<>();

    public PlayerManager(Player p) {
        this.p = p;
        Lobby.getPlayerPlayerManagerHashMap().put(p, this);

        heads.put("community", ItemBuilder.SkullBuilder("§8× §eCommunity §8×", "MHF_Question"));
        heads.put("case-opening", ItemBuilder.SkullBuilder("§8× §eCase-Opening §8×", "MHF_Chest"));

    }

    public void clearItems() {
        p.getInventory().clear();
        p.setFoodLevel(20);
        p.setHealth(20);
        p.spigot().setCollidesWithEntities(false);
    }

    public void setItems() {

        p.getInventory().clear();
        p.setFoodLevel(20);
        p.setHealth(20);
        p.spigot().setCollidesWithEntities(false);
        p.getInventory().setItem(4, new ItemBuilder(Material.COMPASS).setDisplayName("§8× §aTeleporter §8×").build());
        p.getInventory().setItem(2, new ItemBuilder(Material.BLAZE_POWDER).setDisplayName("§8× §aSpieler Verstecker §8×").build());
        if (Lobby.getGadgetManager().getGadget(getPlayer().getUniqueId()) == null) {
            p.getInventory().setItem(6, new ItemBuilder(Material.BARRIER).setDisplayName("§8× §aKein Gadget ausgewählt §8×").build());
        } else if (Lobby.getGadgetManager().getGadget(getPlayer().getUniqueId()).equals("Angel")) {
            p.getInventory().setItem(6, new ItemBuilder(Material.FISHING_ROD).setDisplayName("§8× §aAngel §8×").build());
        } else if (Lobby.getGadgetManager().getGadget(getPlayer().getUniqueId()).equals("Feder")) {
            p.getInventory().setItem(6, new ItemBuilder(Material.FEATHER).setDisplayName("§8× §aFeder §8×").build());
        } else if (Lobby.getGadgetManager().getGadget(getPlayer().getUniqueId()).equals("Tramp")) {
            p.getInventory().setItem(6, new ItemBuilder(Material.SLIME_BLOCK).setDisplayName("§8× §aTrampoline §8×").build());
        }

    }

    public void scoreboard(UUID uuid, Player player) {
        FastBoard board = new FastBoard(player);

        CloudPlayer cloudPlayer = CloudAPI.getInstance().getOnlinePlayer(player.getUniqueId());

        board.updateTitle("§7§o× §6§oLobby");

        board.updateLines(
                "", // Empty line
                "§7» §eCoins§7: §e",
                "  §7" + Lobby.getCoinAPI().getCoins(uuid),
                "",
                "§7» §eOnlinezeit§7: §e",
                "  §7Gibtesnicht",
                "",
                "§7» §eServer§7: §e",
                "  §7" + cloudPlayer.getServer());
    }


    public HashMap<String, ItemStack> getHeads() {
        return heads;
    }

    public void checkSkins() {

        for (Entity x : Bukkit.getWorld("world").getEntities()) {
            if (x.getCustomName() == null) return;

            if (x.getCustomName().startsWith("§e") && x.getCustomName().startsWith("&e")) {


            }

        }

    }

    public Player getPlayer() {
        return p;
    }
}
