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
    public String specifyBC;
    public String unknownCommand;
    public String msgDoesntExist;
    public String mustBeNumber;
    public String cantHaveBoth;

    public void loadConfig() {
        plugin.saveDefaultConfig();
        FileConfiguration config = plugin.getConfig();
        prefix = config.getString("Prefix");
        broadcastInterval = config.getLong("Broadcast-Interval");
        chatInterveral = config.getInt("Chat-Interval");
        filesReloaded = config.getString("Files-Reloaded-Msg");
        specifyBC = config.getString("Specify-Broadcast-Msg");
        unknownCommand = config.getString("Unknown-Command");
        msgDoesntExist = config.getString("Message-Does-Not-Exist");
        mustBeNumber = config.getString("Must-Be-Number");
        cantHaveBoth = config.getString("No-JSONCommand-And-JSONLink");
        plugin.saveConfig();
    }

    public void reloadConfig() {
        plugin.reloadConfig();
        loadConfig();
        plugin.getConfig();
        plugin.saveConfig();
    }

    public void setTime(String time) {
        FileConfiguration config = plugin.getConfig();
        config.set("Broadcast-Interval", time);
    }
}
