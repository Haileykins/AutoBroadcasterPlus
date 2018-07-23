package me.haileykins.autobroadcasterplus.utils;

import me.haileykins.autobroadcasterplus.AutoBroadcasterPlus;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigUtils {

    private AutoBroadcasterPlus plugin;

    public ConfigUtils(AutoBroadcasterPlus pl) {
        plugin = pl;
    }

    long broadcastInterval;
    int chatInterveral;
    public String prefix;
    public String filesReloaded;

    public void loadConfig() {
        plugin.saveDefaultConfig();
        FileConfiguration config = plugin.getConfig();
        prefix = config.getString("Prefix");
        broadcastInterval = config.getLong("Broadcast-Interval");
        chatInterveral = config.getInt("Chat-Interval");
        filesReloaded = config.getString("Files-Reloaded-Msg");
        plugin.saveConfig();
    }

    public void reloadConfig() {
        plugin.reloadConfig();
        loadConfig();
        plugin.getConfig();
        plugin.saveConfig();
    }
}
