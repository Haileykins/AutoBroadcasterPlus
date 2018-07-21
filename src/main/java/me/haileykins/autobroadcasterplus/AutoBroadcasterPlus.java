package me.haileykins.autobroadcasterplus;

import me.haileykins.autobroadcasterplus.commands.ABCCommand;
import me.haileykins.autobroadcasterplus.listeners.ActivityListener;
import me.haileykins.autobroadcasterplus.utils.Autobroadcaster;
import me.haileykins.autobroadcasterplus.utils.BroadcastMsgUtils;
import me.haileykins.autobroadcasterplus.utils.ConfigUtils;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.ThreadLocalRandom;

public class AutoBroadcasterPlus extends JavaPlugin {

    @Override
    public void onEnable() {
        // Create Instances
        ConfigUtils cfgUtils = new ConfigUtils(this);
        BroadcastMsgUtils bcmUtils = new BroadcastMsgUtils(this);
        Autobroadcaster abc = new Autobroadcaster(this, bcmUtils, cfgUtils);

        // Load Files
        cfgUtils.loadConfig();
        bcmUtils.loadConfig();

        // Register Listeners
        getServer().getPluginManager().registerEvents(new ActivityListener(abc), this);

        // Register Commands
        getCommand("autobroadcast").setExecutor(new ABCCommand(bcmUtils, cfgUtils));

        // Start Runnable Task
        abc.broadcast();
    }

    @Override
    public void onDisable() {
    }
}
