package me.haileykins.autobroadcasterplus.commands;

import me.haileykins.autobroadcasterplus.AutoBroadcasterPlus;
import me.haileykins.autobroadcasterplus.utils.Autobroadcaster;
import me.haileykins.autobroadcasterplus.utils.BroadcastMsgUtils;
import me.haileykins.autobroadcasterplus.utils.ConfigUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;

public class ABCCommand implements CommandExecutor {

    private BroadcastMsgUtils bcmUtils;
    private ConfigUtils cfgUtils;
    private Autobroadcaster abc;
    private AutoBroadcasterPlus plugin;

    public ABCCommand(Autobroadcaster autobroadcaster, BroadcastMsgUtils broadcastMsgUtils, ConfigUtils configUtils, AutoBroadcasterPlus pl) {
        abc = autobroadcaster;
        bcmUtils = broadcastMsgUtils;
        cfgUtils = configUtils;
        plugin = pl;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(bcmUtils.colorMe("&7---- &6AutoBroadcasterPlus by &dHaileykins &7----"));
            sender.sendMessage(bcmUtils.colorMe("&6/abc reload - &7Reloads Config"));
            sender.sendMessage(bcmUtils.colorMe("&6/abc kill - &7Ends The Broadcast Task Immediatly"));
            sender.sendMessage(bcmUtils.colorMe("&6/abc reboot - &7Restarts Broadcasting"));
            sender.sendMessage(bcmUtils.colorMe("&6/abc cast (broadcast section) - &7Broadcast Defined Message"));
            return true;

        }

        if (args[0].equalsIgnoreCase("reload")) {

            if (args.length == 1) {

                cfgUtils.reloadConfig();
                sender.sendMessage(bcmUtils.colorMe(cfgUtils.prefix + " " + cfgUtils.filesReloaded));
                return true;

            }

        }

        if (args[0].equalsIgnoreCase("kill")) {
            if (plugin.getServer().getScheduler().isQueued(abc.taskID) || plugin.getServer().getScheduler().isCurrentlyRunning(abc.taskID)) {
                plugin.getServer().getScheduler().cancelTasks(plugin);
                sender.sendMessage(bcmUtils.colorMe(cfgUtils.prefix + " " + cfgUtils.kill));
                return true;
            }
        }

        if (args[0].equalsIgnoreCase("reboot")) {
            if (!plugin.getServer().getScheduler().isQueued(abc.taskID) || !plugin.getServer().getScheduler().isCurrentlyRunning(abc.taskID)) {
                abc.broadcast();
                sender.sendMessage(bcmUtils.colorMe(cfgUtils.prefix + " " + cfgUtils.reboot));
                return true;
            }
        }

        if (args[0].equalsIgnoreCase("cast")) {

            if (args.length == 2) {
                ConfigurationSection msgSection = bcmUtils.getBCMs().getConfigurationSection("messages");

                String group = args[1];

                if (abc.randomSelector.containsValue(group)) {

                    String message = msgSection.getString(String.valueOf(group) + ".Message");
                    String JSONCommand = msgSection.getString(String.valueOf(group) + ".JSONCommand");
                    String JSONLink = msgSection.getString(String.valueOf(group) + ".JSONLink");
                    String displayText = msgSection.getString(String.valueOf(group) + ".Display-Text");

                    bcmUtils.handleCastCommand(JSONCommand, JSONLink, displayText, message);

                    return true;
                }

                sender.sendMessage(bcmUtils.colorMe(cfgUtils.prefix + " " + cfgUtils.msgDoesntExist
                        .replace("{msg}", args[1])));
                return true;
            }

            sender.sendMessage(bcmUtils.colorMe(cfgUtils.prefix + " " + cfgUtils.specifyBC));
            return true;

        }

        sender.sendMessage(bcmUtils.colorMe(cfgUtils.prefix + " " + cfgUtils.unknownCommand));
        return true;

    }

}
