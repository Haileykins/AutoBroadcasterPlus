package me.haileykins.autobroadcasterplus.commands;

import me.haileykins.autobroadcasterplus.utils.BroadcastMsgUtils;
import me.haileykins.autobroadcasterplus.utils.ConfigUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;

public class ABCCommand implements CommandExecutor {

    private BroadcastMsgUtils bcmUtils;
    private ConfigUtils cfgUtils;

    public ABCCommand(BroadcastMsgUtils broadcastMsgUtils, ConfigUtils configUtils) {
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

                try {

                    ConfigurationSection msgSection = bcmUtils.getBCMs().getConfigurationSection("messages");

                    int group = Integer.parseInt(args[1]);

                    String message = msgSection.getString(String.valueOf(group) + ".Message");
                    String JSONCommand = msgSection.getString(String.valueOf(group) + ".JSONCommand");
                    String JSONLink = msgSection.getString(String.valueOf(group) + ".JSONLink");
                    String displayText = msgSection.getString(String.valueOf(group) + ".Display-Text");

                    bcmUtils.handleCastCommand(JSONCommand, JSONLink, displayText, message);

                } catch (NumberFormatException e) {

                    sender.sendMessage("You Must Use Numbers");

                }

            }

        }

        return true;

    }

}
