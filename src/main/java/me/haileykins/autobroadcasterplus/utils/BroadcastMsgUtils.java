package me.haileykins.autobroadcasterplus.utils;

import me.haileykins.autobroadcasterplus.AutoBroadcasterPlus;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class BroadcastMsgUtils {

    private AutoBroadcasterPlus plugin;

    private FileConfiguration messages;

    public BroadcastMsgUtils(AutoBroadcasterPlus pl) {
        plugin = pl;
    }

    public String colorMe(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public void loadConfig() {
        plugin.saveResource("messages.yml", false);

        File file = new File(plugin.getDataFolder(), "messages.yml");
        messages = new YamlConfiguration();

        try {
            messages.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getBCMs() {
        return messages;
    }

    public void handleCastCommand(String JSONCommand, String JSONLink, String displayText, String message) {
        if ((!JSONCommand.equalsIgnoreCase("none")) && (!JSONLink.equalsIgnoreCase("none"))) {
            // TODO: PRIVATE ERROR MESSAGE TO THOSE WITH PERMISSIONS
            Bukkit.broadcastMessage("ERROR: You can not have both JSONCommand and JSONLink enabled!");
            return;
        }

        if (!JSONCommand.equalsIgnoreCase("none")) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                TextComponent msg = new TextComponent(colorMe(message));
                if (!displayText.equalsIgnoreCase("none")) {
                    msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(colorMe(displayText)).create()));
                }
                msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + JSONCommand));
                player.spigot().sendMessage(msg);
            }
        }


        if (!JSONLink.equalsIgnoreCase("none")) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                TextComponent msg = new TextComponent(colorMe(message));
                if (!displayText.equalsIgnoreCase("none")) {
                    msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(colorMe(displayText)).create()));
                }
                msg.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, JSONLink));
                player.spigot().sendMessage(msg);
            }
        }


        if ((JSONCommand.equalsIgnoreCase("none")) && (JSONLink.equalsIgnoreCase("none"))) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                TextComponent msg = new TextComponent(colorMe(message));
                if (!displayText.equalsIgnoreCase("none")) {
                    msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(colorMe(displayText)).create()));
                }
                player.spigot().sendMessage(msg);
            }

        }
    }
}
