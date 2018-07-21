package me.haileykins.autobroadcasterplus.commands;

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

    public ABCCommand(Autobroadcaster autobroadcaster, BroadcastMsgUtils broadcastMsgUtils, ConfigUtils configUtils) {
        abc = autobroadcaster;
        bcmUtils = broadcastMsgUtils;
        cfgUtils = configUtils;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {

            // TODO: HELP MESSAGE
            return true;

        }

        if (args[0].equalsIgnoreCase("reload")) {

            if (args.length == 1) {

                cfgUtils.reloadConfig();
                sender.sendMessage(bcmUtils.colorMe(cfgUtils.prefix + " " + cfgUtils.filesReloaded));
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

                    sender.sendMessage(args[1] + " Doesn't Exist In Broadcast List");
                    return true;
            }

            sender.sendMessage("You must specify what message to broadcast!");

        }

        sender.sendMessage("Unknown Command");
        return true;

    }

}
