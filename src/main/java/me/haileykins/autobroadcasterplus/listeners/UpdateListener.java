package me.haileykins.autobroadcasterplus.listeners;

import me.haileykins.autobroadcasterplus.AutoBroadcasterPlus;
import me.haileykins.autobroadcasterplus.utils.BroadcastMsgUtils;
import me.haileykins.autobroadcasterplus.utils.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class UpdateListener implements Listener {

    private AutoBroadcasterPlus plugin;
    private ConfigUtils cfgUtils;
    private BroadcastMsgUtils bcmUtils;

    private final String resourceURL = "https://api.spigotmc.org/legacy/update.php?resource=59045";

    public UpdateListener(AutoBroadcasterPlus pl, ConfigUtils configUtils, BroadcastMsgUtils broadcastMsgUtils) {
        plugin = pl;
        cfgUtils = configUtils;
        bcmUtils = broadcastMsgUtils;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!cfgUtils.updaterEnabled) {
            return;
        }

        Player player = event.getPlayer();

        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                HttpsURLConnection connection = (HttpsURLConnection) new URL(resourceURL).openConnection();
                String version = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();

                if (!plugin.getDescription().getVersion().equalsIgnoreCase(version)) {
                   player.sendMessage(bcmUtils.colorMe(cfgUtils.prefix + " " + cfgUtils.pluginOutOfDate));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
