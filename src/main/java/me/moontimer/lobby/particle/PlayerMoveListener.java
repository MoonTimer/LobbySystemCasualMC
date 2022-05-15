package me.moontimer.lobby.particle;

import me.moontimer.lobby.Lobby;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (Lobby.getParticle().particleHashMap.containsKey(player)) {
            ParticleEnum particle = Lobby.getParticle().particleHashMap.get(player);
            switch (particle) {
                case FEUERREGEN:

            }
        }
    }
}
