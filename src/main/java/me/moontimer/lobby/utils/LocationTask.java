package me.moontimer.lobby.utils;

import me.moontimer.lobby.Lobby;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.Random;

public class LocationTask {
    Integer last = 0;
    BukkitTask task;
    Location location = null;


    public LocationTask() {
    }

    public void stop() {
        task.cancel();
        last = 0;
    }

    private int getNew() {
        return new Random().nextInt(15);
    }

}
