package me.moontimer.lobby.listener;

import me.moontimer.lobby.Lobby;
import me.moontimer.lobby.utils.PlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import javax.persistence.Lob;

public class JoinQuitListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onJoin(PlayerJoinEvent event) {

        Lobby.getGadgetManager().createPlayer(event.getPlayer());

        event.setJoinMessage(null);
        event.getPlayer().teleport(Lobby.getLocationManager().getLocation("Spawn"));

        PlayerManager playerManager = new PlayerManager(event.getPlayer());
        playerManager.setItems();

        playerManager.scoreboard(event.getPlayer().getUniqueId(), event.getPlayer());

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
    }
}
