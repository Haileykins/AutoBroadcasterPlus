package me.haileykins.autobroadcasterplus.commands;

import org.bukkit.command.CommandSender;

import java.util.List;

<<<<<<< HEAD
public abstract class CommandBase {
=======
public abstract class ABCCommands {
>>>>>>> 1af6c3b8185acbead4bf890d8901f58c8153f361

    /**
     * The Method called when the sub-oommand is ran
     * @param sender The CommandSender that ran the command
     * @param args The arguments passed to the command
     */
    public abstract void onCommand(CommandSender sender, List<String> args);

    /**
     * The method to return the String value of the sub-command
     * @return The string value of the sub-command
     */
    public abstract String getCommand();

}
