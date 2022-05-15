package me.moontimer.lobby.utils;

import me.moontimer.casual.api.database.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class LocationManager {
    HashMap<String, Location> locations;
    MySQL mysql;

    public LocationManager(MySQL mysql) {
        this.mysql = mysql;
        locations = new HashMap<>();
    }

    public void loadLocations() {
        ResultSet rs = mysql.executeQuery("SELECT * FROM locations");
        try {
            while (rs.next()) {
                locations.put(rs.getString("name"), fetchLocation(rs.getString("location")));
            }
        } catch(SQLException ex) {}

    }

    public void setLocation(String name, Location location) {
        locations.remove(name);
        locations.put(name, location);
        mysql.execute("INSERT INTO locations(name, location) VALUES ('" + name + "','" + fetchLocation(location) + "')");
    }

    public Location getLocation(String name) {
        return locations.get(name);
    }

    public String fetchLocation(Location raw) {
        return String.valueOf(raw.getWorld().getName() + "#" + raw.getX()+ "#" + raw.getY()+ "#" + raw.getZ()+ "#" + raw.getYaw()+ "#" + raw.getPitch());
    }

    public Location fetchLocation(String raw) {
        Location location = new Location(null, 0, 0, 0, 0, 0);
        String[] comp = raw.split("#");
        location.setWorld(Bukkit.getWorld(comp[0]));
        location.setX(Double.valueOf(comp[1]));
        location.setY(Double.valueOf(comp[2]));
        location.setZ(Double.valueOf(comp[3]));
        location.setYaw(Float.valueOf(comp[4]));
        location.setPitch(Float.valueOf(comp[5]));
        return location;
    }

    public HashMap<String, Location> getLocations() {
        return locations;
    }
}
