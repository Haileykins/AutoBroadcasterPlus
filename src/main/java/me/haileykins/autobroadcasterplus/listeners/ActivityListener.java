package me.haileykins.autobroadcasterplus.listeners;

import me.haileykins.autobroadcasterplus.utils.Autobroadcaster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ActivityListener implements Listener {

    private Autobroadcaster abc;

    public ActivityListener(Autobroadcaster autoBroadcaster) {
        abc = autoBroadcaster;
    }


    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        abc.chatActivity += 1;
    }
}
