package de.crowraw.discordhook.api;

import org.apache.commons.lang.NotImplementedException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigUtil {

    private File file;
    private YamlConfiguration yamlConfiguration;

    /**
     * @param whereToSave -> for example "plugins//PLUGINNAME//config.yml
     */
    public ConfigUtil(String whereToSave) {
        file = new File(whereToSave);
        yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        saveConfig();
    }

    /**
     * @param message Message
     * @param key     KeyForMessage
     * @return
     */
    public String getStringMessage(String message, String key) {
        message = message.replace('§', '&');
        if (yamlConfiguration.get("messages." + key) == null) {
            yamlConfiguration.set("messages." + key, message);
            saveConfig();
        }
        return ChatColor.translateAlternateColorCodes('&', yamlConfiguration.getString("messages." + key));
    }

    /**
     * Useless as hell!
     */
    public File getFile() {
        return file;
    }


    /**
     * Saves the config!
     */
    public void saveConfig() {
        try {
            this.yamlConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Don't forget to save config before doing that!
     */
    public void reloadConfig() {
        yamlConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public String getHost() {
        if (yamlConfiguration.get("mysql.host") == null) {
            yamlConfiguration.set("mysql.host", "host");
            saveConfig();
        }
        return yamlConfiguration.getString("mysql.host");
    }

    public String getUser() {
        if (yamlConfiguration.get("mysql.host") == null) {
            yamlConfiguration.set("mysql.host", "host");
            saveConfig();
        }
        return yamlConfiguration.getString("mysql.host");

    }

    public String getPassword() {
        if (yamlConfiguration.get("mysql.password") == null) {
            yamlConfiguration.set("mysql.password", "password");
            saveConfig();
        }
        return yamlConfiguration.getString("mysql.password");

    }

    public String getDatabase() {
        if (yamlConfiguration.get("mysql.databse") == null) {
            yamlConfiguration.set("mysql.database", "database");
            saveConfig();
        }
        return yamlConfiguration.getString("mysql.database");

    }

    public int getPort() {
        if (yamlConfiguration.get("mysql.port") == null) {
            yamlConfiguration.set("mysql.port", 3306);
            saveConfig();
        }
        return yamlConfiguration.getInt("mysql.port");

    }


    /**
     * @param id       id to look for
     * @param location location to save
     */
    public void loadLocation(int id, Location location) {
        yamlConfiguration.set("location." + id + ".X", location.getX());
        yamlConfiguration.set("location." + id + ".Y", location.getY());
        yamlConfiguration.set("location." + id + ".Z", location.getZ());
        yamlConfiguration.set("location." + id + ".World", location.getWorld().getName());
        saveConfig();

    }

    /**
     * @param id Id to look for
     * @return Location
     */
    public Location getLocationFromId(int id) {
        if (yamlConfiguration.get("location." + id) == null) {
            throw new NotImplementedException("There is not such a location!");
        }
        World world = Bukkit.getWorld(yamlConfiguration.getString("location." + id + ".World"));
        int x = yamlConfiguration.getInt("location." + id + ".X");
        int y = yamlConfiguration.getInt("location." + id + ".Y");
        int z = yamlConfiguration.getInt("location." + id + ".Z");
        return new Location(world, x, y, z);
    }

    public YamlConfiguration getYamlConfiguration() {
        return yamlConfiguration;
    }
}
