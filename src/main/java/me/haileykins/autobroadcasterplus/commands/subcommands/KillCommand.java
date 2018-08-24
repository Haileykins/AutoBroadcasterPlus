package me.haileykins.autobroadcasterplus.commands.subcommands;

import me.haileykins.autobroadcasterplus.AutoBroadcasterPlus;
import me.haileykins.autobroadcasterplus.commands.CommandBase;
import me.haileykins.autobroadcasterplus.utils.Autobroadcaster;
import me.haileykins.autobroadcasterplus.utils.BroadcastMsgUtils;
import me.haileykins.autobroadcasterplus.utils.ConfigUtils;
import org.bukkit.command.CommandSender;

import java.util.List;

public class KillCommand extends CommandBase {

    private AutoBroadcasterPlus plugin;
    private Autobroadcaster abc;
    private BroadcastMsgUtils bcmUtils;
    private ConfigUtils cfgUtils;

    public KillCommand(AutoBroadcasterPlus pl, Autobroadcaster autobroadcaster, BroadcastMsgUtils broadcastMsgUtils, ConfigUtils configUtils) {
        plugin = pl;
        abc = autobroadcaster;
        bcmUtils = broadcastMsgUtils;
        cfgUtils = configUtils;
    }

    @Override
    public void onCommand(CommandSender sender, List<String> args) {
        if (plugin.getServer().getScheduler().isQueued(abc.taskID) || plugin.getServer().getScheduler().isCurrentlyRunning(abc.taskID)) {
            plugin.getServer().getScheduler().cancelTasks(plugin);
            sender.sendMessage(bcmUtils.colorMe(cfgUtils.prefix + " " + cfgUtils.kill));
        }
    }

    @Override
    public String getCommand() {
        return "kill";
    }
}
