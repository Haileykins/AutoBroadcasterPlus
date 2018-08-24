package me.haileykins.autobroadcasterplus;

import me.haileykins.autobroadcasterplus.commands.CommandManager;
import me.haileykins.autobroadcasterplus.listeners.ActivityListener;
import me.haileykins.autobroadcasterplus.listeners.UpdateListener;
import me.haileykins.autobroadcasterplus.utils.Autobroadcaster;
import me.haileykins.autobroadcasterplus.utils.BroadcastMsgUtils;
import me.haileykins.autobroadcasterplus.utils.ConfigUtils;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public class AutoBroadcasterPlus extends JavaPlugin {

    @SuppressWarnings("unused")
    @Override
    public void onEnable() {

        Metrics metrics = new Metrics(this);

        // Create Instances
        BroadcastMsgUtils bcmUtils = new BroadcastMsgUtils(this);
        ConfigUtils cfgUtils = new ConfigUtils(this);
        Autobroadcaster abc = new Autobroadcaster(this, bcmUtils, cfgUtils);

        // Load Files
        cfgUtils.loadConfig();
        bcmUtils.loadConfig();

        // Register Listeners
        getServer().getPluginManager().registerEvents(new ActivityListener(abc), this);
        getServer().getPluginManager().registerEvents(new UpdateListener(this, cfgUtils, bcmUtils), this);

        // Register Commands
        getCommand("autobroadcast").setExecutor(new CommandManager(cfgUtils, bcmUtils, this, abc));

        // Start Runnable Task
        abc.broadcast();
    }
}
