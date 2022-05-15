package me.moontimer.lobby.listener;

import me.moontimer.lobby.Lobby;
import me.moontimer.lobby.utils.PlayerManager;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.util.Vector;

public class BlockedEvents implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onFood(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onBreeak(BlockBreakEvent e) {
        if(e.getPlayer().getGameMode() != GameMode.CREATIVE) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onExpl(ExplosionPrimeEvent e) {
        e.setCancelled(true);
        e.setRadius(0);
        e.setFire(false);
    }


    @EventHandler
    public void onTree(LeavesDecayEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onWeather(WeatherChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onGamemode(PlayerGameModeChangeEvent e) {
        if(!e.getPlayer().hasPermission("lobby.gm") && e.getNewGameMode() == GameMode.CREATIVE) {
            e.setCancelled(true);
            return;
        }

        if(e.getNewGameMode() == GameMode.CREATIVE) {
            PlayerManager playerManager = Lobby.getPlayerPlayerManagerHashMap().get(e.getPlayer());
            playerManager.clearItems();
            return;
        } else {
            PlayerManager playerManager = Lobby.getPlayerPlayerManagerHashMap().get(e.getPlayer());
            playerManager.setItems();
        }

    }

    @EventHandler
    public void onForm(EntityBlockFormEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onShot(EntityShootBowEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onBurn(BlockBurnEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onCreeper(CreeperPowerEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onTarget(EntityTargetLivingEntityEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void on(EntityTeleportEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onAch(PlayerAchievementAwardedEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onPLace(BlockPlaceEvent e) {
        if(e.getPlayer().getGameMode() != GameMode.CREATIVE) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if(e.getWhoClicked().getGameMode() != GameMode.CREATIVE) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPhysik(PlayerInteractEvent e) {
        if(e.getAction() == Action.PHYSICAL) {
            e.setCancelled(true);

            if(e.getClickedBlock().getType() == Material.GOLD_PLATE) {
                Vector v = e.getPlayer().getLocation().getDirection().multiply(4D).setY(5);
                e.getPlayer().setVelocity(v);
            }

        }
    }

    @EventHandler
    public void onEntityIn(PlayerInteractEntityEvent e) {
        e.setCancelled(true);
    }



}

