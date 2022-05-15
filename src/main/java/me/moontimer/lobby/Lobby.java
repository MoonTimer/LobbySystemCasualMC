package me.moontimer.lobby;

import lombok.Getter;
import me.moontimer.casual.api.database.MySQL;
import me.moontimer.casual.api.database.MySQLFile;
import me.moontimer.casual.api.system.CasualAPI;
import me.moontimer.casual.api.system.coins.CoinAPI;
import me.moontimer.lobby.commands.GadgetCommand;
import me.moontimer.lobby.commands.LocationCommand;
import me.moontimer.lobby.commands.SetShopCommand;
import me.moontimer.lobby.commands.SettingsCommand;
import me.moontimer.lobby.listener.BlockedEvents;
import me.moontimer.lobby.listener.JoinQuitListener;
import me.moontimer.lobby.listener.ShopListener;
import me.moontimer.lobby.listener.gadget.EnterhackenGadget;
import me.moontimer.lobby.listener.gadget.FlyGadget;
import me.moontimer.lobby.listener.gadget.TrampolineGadget;
import me.moontimer.lobby.particle.Particle;
import me.moontimer.lobby.utils.GadgetManager;
import me.moontimer.lobby.utils.LocationManager;
import me.moontimer.lobby.utils.LocationTask;
import me.moontimer.lobby.utils.PlayerManager;
import me.moontimer.lobby.utils.items.HiderItem;
import me.moontimer.lobby.utils.items.TeleportItem;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class Lobby extends JavaPlugin {

    @Getter
    public static Lobby instance;

    @Getter
    public static String prefix = "§6§lCasualMC§7§l.§6§lNET §8× §7";

    @Getter
    public static String noPerm = "§cDu hast darauf keine Rechte.";

    @Getter
    static LocationManager locationManager = null;

    @Getter
    static MySQLFile mySQLFile = new MySQLFile("plugins/casual/lobby-mysql.yml");;

    @Getter
    static MySQL mySQL = null;

    @Getter
    static CoinAPI coinAPI = null;

    @Getter
    static HashMap<Player, PlayerManager> playerPlayerManagerHashMap = new HashMap<>();

    @Getter
    public static CasualAPI casualAPI = new CasualAPI();

    @Getter
    static GadgetManager gadgetManager = null;

    static LocationTask locationTask = new LocationTask();

    @Getter
    static Particle particle = new Particle();

    @Override
    public void onEnable() {
        // Plugin startup logic

        instance = this;

        mySQLFile.create();
        mySQLFile.loadDatas();
        mySQL = new MySQL(mySQLFile);

        mySQL.execute("CREATE TABLE IF NOT EXISTS locations(name TEXT, location TEXT);");
        mySQL.execute("CREATE TABLE IF NOT EXISTS gadget(name TEXT, uuid TEXT, gadget TEXT);");

        locationManager = new LocationManager(mySQL);
        gadgetManager = new GadgetManager(mySQL);

        locationManager.loadLocations();

        coinAPI = new CoinAPI(casualAPI.getMysql());
        coinAPI.createTable();

        for(Player x : Bukkit.getOnlinePlayers()) {
            coinAPI.loadCoins(x);
            PlayerManager playerManager = new PlayerManager(x);
            playerManager.setItems();
        }

        getCommand("setLocation").setExecutor(new LocationCommand());
        getCommand("gadget").setExecutor(new GadgetCommand());
        getCommand("setshop").setExecutor(new SetShopCommand());
        getCommand("settings").setExecutor(new SettingsCommand());
        Bukkit.getPluginManager().registerEvents(new TeleportItem(), this);
        Bukkit.getPluginManager().registerEvents(new BlockedEvents(), this);
        Bukkit.getPluginManager().registerEvents(new JoinQuitListener(), this);
        Bukkit.getPluginManager().registerEvents(new FlyGadget(), this);
        Bukkit.getPluginManager().registerEvents(new EnterhackenGadget(), this);
        Bukkit.getPluginManager().registerEvents(new TrampolineGadget(), this);
        Bukkit.getPluginManager().registerEvents(new HiderItem(), this);
        Bukkit.getPluginManager().registerEvents(new ShopListener(), this);

        for(Player x : Bukkit.getOnlinePlayers()) {
            coinAPI.loadCoins(x);
            PlayerManager playerManager = new PlayerManager(x);
            playerManager.setItems();
        }


        MinecraftServer.getServer().setAllowFlight(true);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static LocationTask getLocationTask() {
        return locationTask;
    }
}
