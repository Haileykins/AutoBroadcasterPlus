package me.haileykins.autobroadcasterplus.listeners;

import me.haileykins.autobroadcasterplus.AutoBroadcasterPlus;
import me.haileykins.autobroadcasterplus.utils.ConfigUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class UpdateListener implements Listener {

    private AutoBroadcasterPlus plugin;
    private ConfigUtils cfgUtils;

    public UpdateListener(AutoBroadcasterPlus pl, ConfigUtils configUtils) {
        plugin = pl;
        cfgUtils = configUtils;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!cfgUtils.updaterEnabled) {
            return;
        }

        if (event.getPlayer().hasPermission("abc.admin")) {
            Player player = event.getPlayer();
            try {
                HttpsURLConnection connection = (HttpsURLConnection) new
                        URL("https://api.spigotmc.org/legacy/update.php?resource=59045").openConnection();
                String version = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();

                if (!plugin.getDescription().getVersion().equalsIgnoreCase(version)) {
                    player.sendMessage("Plugin Out Of Date");
                } else {
                    player.sendMessage("Plugin Up To Date");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
