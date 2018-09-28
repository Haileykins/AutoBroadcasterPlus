package me.haileykins.autobroadcasterplus.commands.subcommands;

import me.haileykins.autobroadcasterplus.commands.CommandBase;
import me.haileykins.autobroadcasterplus.utils.BroadcastMsgUtils;
import me.haileykins.autobroadcasterplus.utils.ConfigUtils;
import org.bukkit.command.CommandSender;

import java.util.List;

public class ReloadCommand extends CommandBase {

    private ConfigUtils cfgUtils;
    private BroadcastMsgUtils bcmUtils;

    public ReloadCommand(ConfigUtils configUtils, BroadcastMsgUtils broadcastMsgUtils) {
        cfgUtils = configUtils;
        bcmUtils = broadcastMsgUtils;
    }

    @Override
    public void onCommand(CommandSender sender, List<String> args) {
        cfgUtils.reloadConfig();
        sender.sendMessage(bcmUtils.colorMe(cfgUtils.prefix + " " + cfgUtils.filesReloaded));
    }

    @Override
    public String getCommand() {
        return "reload";
    }
}
