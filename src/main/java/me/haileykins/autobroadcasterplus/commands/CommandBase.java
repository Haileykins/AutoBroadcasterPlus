package me.haileykins.autobroadcasterplus.commands;

import org.bukkit.command.CommandSender;

import java.util.List;

public abstract class CommandBase {
    public abstract void onCommand(CommandSender sender, List<String> args);
    public abstract String getCommand();

}
