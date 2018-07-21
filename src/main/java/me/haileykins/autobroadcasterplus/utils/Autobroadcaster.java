package me.haileykins.autobroadcasterplus.utils;

import me.haileykins.autobroadcasterplus.AutoBroadcasterPlus;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.concurrent.ThreadLocalRandom;

public class Autobroadcaster {

    public int chatActivity;
    private AutoBroadcasterPlus plugin;
    private ConfigUtils cfgUtils;
    private BroadcastMsgUtils bcmUtils;

    private String message;
    private String JSONCommand;
    private String JSONLink;
    private String displayText;

    public Autobroadcaster(AutoBroadcasterPlus pl, BroadcastMsgUtils broadcastMsgUtils, ConfigUtils configUtils) {
        plugin = pl;
        bcmUtils = broadcastMsgUtils;
        cfgUtils = configUtils;
    }

    public void broadcast() {
        ConfigurationSection msgSection = bcmUtils.getBCMs().getConfigurationSection("messages");

        plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            int group = ThreadLocalRandom.current().nextInt(msgSection.getKeys(false).size()) + 1;

            message = msgSection.getString(String.valueOf(group) + ".Message");
            JSONCommand = msgSection.getString(String.valueOf(group) + ".JSONCommand");
            JSONLink = msgSection.getString(String.valueOf(group) + ".JSONLink");
            displayText = msgSection.getString(String.valueOf(group) + ".Display-Text");

            final int interval = cfgUtils.chatInterveral;

            if (chatActivity >= interval) {
                chatActivity = 0;

                if ((!JSONCommand.equalsIgnoreCase("none")) && (!JSONLink.equalsIgnoreCase("none"))) {
                    // TODO: PRIVATE ERROR MESSAGE TO THOSE WITH PERMISSIONS
                    Bukkit.broadcastMessage("ERROR: You can not have both JSONCommand and JSONLink enabled!");
                    return;
                }

                if (!JSONCommand.equalsIgnoreCase("none")) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        TextComponent msg = new TextComponent(bcmUtils.colorMe(message));
                        if (displayText.equalsIgnoreCase("none")) {
                            return;
                        }
                        msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(bcmUtils.colorMe(displayText)).create()));
                        msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + JSONCommand));
                        player.spigot().sendMessage(msg);
                    }
                }

                if (!JSONLink.equalsIgnoreCase("none")) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        TextComponent msg = new TextComponent(bcmUtils.colorMe(message));
                        if (displayText.equalsIgnoreCase("none")) {
                            return;
                        }
                        msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(bcmUtils.colorMe(displayText)).create()));
                        msg.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, JSONLink));
                        player.spigot().sendMessage(msg);
                    }
                }

                if ((JSONCommand.equalsIgnoreCase("none")) && (JSONLink.equalsIgnoreCase("none"))) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        TextComponent msg = new TextComponent(bcmUtils.colorMe(message));
                        if (displayText.equalsIgnoreCase("none")) {
                            return;
                        }
                        msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(bcmUtils.colorMe(displayText)).create()));
                        player.spigot().sendMessage(msg);
                    }

                }

            }
            
        }, 0L, cfgUtils.broadcastInterval * 20);
    }
}
