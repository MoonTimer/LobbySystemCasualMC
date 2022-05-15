package me.moontimer.lobby.listener.gadget;

import me.moontimer.lobby.Lobby;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class FlyGadget implements Listener {

    @EventHandler
    public void onAction(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
            if (e.getItem() == null) return;

            if (e.getItem().getType() == Material.FEATHER) {

                Vector v = e.getPlayer().getLocation().getDirection().multiply(3D).setY(2);
                e.getPlayer().setVelocity(v);
                Lobby.getCasualAPI().getVisualAPI().sendSound(e.getPlayer(), Sound.WOOD_CLICK);

            }
        }
    }
}
