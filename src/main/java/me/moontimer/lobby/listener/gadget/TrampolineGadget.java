package me.moontimer.lobby.listener.gadget;

import me.moontimer.lobby.Lobby;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Random;

public class TrampolineGadget implements Listener {

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (event.getItem() == null) return;
            if (event.getItem().getType() == Material.SLIME_BLOCK) {
                final Block block = player.getLocation().subtract(0, 1, 0).getBlock(); //get the block
                final Material type = block.getType(); //get the block's type

                Bukkit.getScheduler().runTaskLater(Lobby.getInstance(), new Runnable(){
                    public void run(){
                        block.setType(type); //set the block back to the original block
                    }
                }, 15 * 20L);
            }
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (player.getItemInHand() == null) return;

        if (player.getInventory().getItemInHand().getType() == Material.SLIME_BLOCK)
        if (player.getLocation().subtract(0.0D, 0.4D, 0.0D).getBlock().getType() == Material.SLIME_BLOCK) {
            World w = player.getWorld();
            double x = player.getLocation().getX();
            double y = player.getLocation().getY();
            double z = player.getLocation().getZ();
            Vector v = player.getLocation().getDirection().multiply(0.0D).setY(0.8D);
            player.setVelocity(v);

        }
    }
}
