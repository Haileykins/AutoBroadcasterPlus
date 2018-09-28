package me.haileykins.autobroadcasterplus.commands;

import me.haileykins.autobroadcasterplus.AutoBroadcasterPlus;
import me.haileykins.autobroadcasterplus.commands.subcommands.*;
import me.haileykins.autobroadcasterplus.utils.Autobroadcaster;
import me.haileykins.autobroadcasterplus.utils.BroadcastMsgUtils;
import me.haileykins.autobroadcasterplus.utils.ConfigUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CommandManager implements CommandExecutor {

    private ConfigUtils cfgUtils;
    private BroadcastMsgUtils bcmUtils;

    private Set<CommandBase> commands;

    public CommandManager(ConfigUtils configUtils, BroadcastMsgUtils broadcastMsgUtils, AutoBroadcasterPlus pl, Autobroadcaster abc) {

        cfgUtils = configUtils;
        bcmUtils = broadcastMsgUtils;


        commands = new HashSet<>();
        commands.add(new ReloadCommand(configUtils, bcmUtils));
        commands.add(new KillCommand(pl, abc, bcmUtils, cfgUtils));
        commands.add(new RebootCommand(pl, abc, bcmUtils, cfgUtils));
        commands.add(new CastCommand(bcmUtils, abc, cfgUtils));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length == 0) {

            sender.sendMessage(bcmUtils.colorMe("&7---- &6AutoBroadcasterPlus by &dHaileykins &7----"));
            sender.sendMessage(bcmUtils.colorMe("&6/abc reload - &7Reloads Config"));
            sender.sendMessage(bcmUtils.colorMe("&6/abc kill - &7Ends The Broadcast Task Immediatly"));
            sender.sendMessage(bcmUtils.colorMe("&6/abc reboot - &7Restarts Broadcasting"));
            sender.sendMessage(bcmUtils.colorMe("&6/abc cast (broadcast section) - &7Broadcast Defined Message"));
            return true;

        }

        CommandBase command = getCommand(args[0]);

        if (command == null) {
            sender.sendMessage(bcmUtils.colorMe(cfgUtils.prefix + " " + cfgUtils.unknownCommand));
            return true;
        }

        List<String> arguments = Arrays.asList(args);

        command.onCommand(sender, arguments);

        return true;
    }


    private CommandBase getCommand(String command) {
        for (CommandBase commandBase : commands) {
            if (commandBase.getCommand().equalsIgnoreCase(command)) {
                return commandBase;
            }
        }
        return null;
    }
}