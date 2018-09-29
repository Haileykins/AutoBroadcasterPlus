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
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class BroadcastMsgUtils {

    private AutoBroadcasterPlus plugin;
    private ConfigUtils cfgUtils;

    private FileConfiguration messages;

    private int descending = 0;
    private int ascending = 0;

    public BroadcastMsgUtils(AutoBroadcasterPlus pl, ConfigUtils configUtils) {
        plugin = pl;
        cfgUtils = configUtils;
    }

    public String colorMe(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public void loadConfig() {
        File file = new File(plugin.getDataFolder(), "messages.yml");
        messages = new YamlConfiguration();

        if (!file.exists()) {
            plugin.saveResource("messages.yml", false);
        }

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
            Bukkit.broadcast("You can not have JSONCommand and JSONLink in the same broadcast!", "abc.admin");
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

    String broadcastType(Map<Integer, String> randomSelector, int i) {
        String path = null;
        int type = cfgUtils.broadcastType;

        if (type == 0) {
            path = randomSelector.get(ThreadLocalRandom.current().nextInt(0, randomSelector.size()));
        }

        if (type == 1) {
            if (descending == i - 1) {
                descending = 0;
            } else {
                descending += 1;
            }

            path = randomSelector.get(descending);

        }

        if (type == 2) {
            if (ascending == 0) {
                ascending = i - 1;
            } else {
                ascending -= 1;
            }

            path = randomSelector.get(ascending);


        }

        return path;
    }
}
