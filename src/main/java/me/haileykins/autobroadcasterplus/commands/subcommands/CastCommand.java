package me.haileykins.autobroadcasterplus.commands.subcommands;

import me.haileykins.autobroadcasterplus.commands.CommandBase;
import me.haileykins.autobroadcasterplus.utils.Autobroadcaster;
import me.haileykins.autobroadcasterplus.utils.BroadcastMsgUtils;
import me.haileykins.autobroadcasterplus.utils.ConfigUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

public class CastCommand extends CommandBase {

    private BroadcastMsgUtils bcmUtils;
    private Autobroadcaster abc;
    private ConfigUtils cfgUtils;

    public CastCommand(BroadcastMsgUtils broadcastMsgUtils, Autobroadcaster autobroadcaster, ConfigUtils configUtils) {
        bcmUtils = broadcastMsgUtils;
        abc = autobroadcaster;
        cfgUtils = configUtils;
    }

    @Override
    public void onCommand(CommandSender sender, List<String> args) {
        if (args.size() != 2) {

            sender.sendMessage(bcmUtils.colorMe(cfgUtils.prefix + " " + cfgUtils.specifyBC));
            return;

        }

        ConfigurationSection msgSection = bcmUtils.getBCMs().getConfigurationSection("messages");

        String group = args.get(1);

        if (abc.randomSelector.containsValue(group)) {

            String message = msgSection.getString(String.valueOf(group) + ".Message");
            String JSONCommand = msgSection.getString(String.valueOf(group) + ".JSONCommand");
            String JSONLink = msgSection.getString(String.valueOf(group) + ".JSONLink");
            String displayText = msgSection.getString(String.valueOf(group) + ".Display-Text");

            bcmUtils.handleCastCommand(JSONCommand, JSONLink, displayText, message);

            return;
        }

        sender.sendMessage(bcmUtils.colorMe(cfgUtils.prefix + " " + cfgUtils.msgDoesntExist
                .replace("{msg}", args.get(1))));;

    }

    @Override
    public String getCommand() {
        return "cast";
    }
}
