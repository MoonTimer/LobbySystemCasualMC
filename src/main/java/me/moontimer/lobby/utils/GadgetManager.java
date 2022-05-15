package me.moontimer.lobby.utils;

import me.moontimer.casual.api.database.MySQL;
import me.moontimer.lobby.Lobby;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class GadgetManager {
    MySQL mysql;

    public GadgetManager(MySQL mysql) {
        this.mysql = mysql;
    }

    /**public void setGadget(UUID uuid, String gadget) {
        mysql.execute("INSERT INTO gadget(uuid, gadget) VALUES ('" + uuid + "','" + gadget + "')");
    }*/

    public void createPlayer(Player player) {
        mysql.execute("INSERT IGNORE INTO gadget (gadget, uuid) VALUES ('" + player.getName() + "', '" + player.getUniqueId().toString() + "')");
    }

    public void setGadget(UUID uuid, String gadget) {
        mysql.execute("UPDATE gadget SET gadget='" + gadget + "' WHERE uuid='" + uuid + "'");
    }

    public String getGadget(UUID uuid) {
        ResultSet rs = Lobby.getMySQL().executeQuery("SELECT gadget FROM gadget WHERE uuid='" + uuid + "'");
        try {
            if (rs.next()) {
                return rs.getString("gadget");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
