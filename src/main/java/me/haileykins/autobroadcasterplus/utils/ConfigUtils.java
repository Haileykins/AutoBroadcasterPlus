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
    String cantHaveBoth;
    public String prefix;
    public boolean updaterEnabled;
    public String filesReloaded;
    public String specifyBC;
    public String unknownCommand;
    public String msgDoesntExist;
    public String kill;
    public String reboot;
    public String pluginOutOfDate;
    public String pluginUpToDate;

    public void loadConfig() {
        plugin.saveDefaultConfig();
        FileConfiguration config = plugin.getConfig();
        prefix = config.getString("Prefix");
        broadcastInterval = config.getLong("Broadcast-Interval");
        chatInterveral = config.getInt("Chat-Interval");
        filesReloaded = config.getString("Files-Reloaded-Msg");
        updaterEnabled = config.getBoolean("Enable-Update-Notifications");
        specifyBC = config.getString("Specify-Broadcast-Msg");
        unknownCommand = config.getString("Unknown-Command");
        msgDoesntExist = config.getString("Message-Does-Not-Exist");
        cantHaveBoth = config.getString("No-JSONCommand-And-JSONLink");
        kill = config.getString("Kill-Message");
        reboot = config.getString("Reboot-Message");
        pluginOutOfDate = config.getString("Plugin-Out-Of-Date");
        pluginUpToDate = config.getString("Plugin-Up-To-Date");

        plugin.saveConfig();
    }

    public void reloadConfig() {
        plugin.reloadConfig();
        loadConfig();
        plugin.getConfig();
        plugin.saveConfig();
    }
}
