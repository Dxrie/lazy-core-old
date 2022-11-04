package lazycore.lazycore.listener;

import lazycore.lazycore.FileConfig;
import lazycore.lazycore.Lazycore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ServerHandler implements Listener {

    public ServerHandler(Lazycore plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Lazycore.waitingList.add(event.getPlayer());

        for (int i = 0; i < Lazycore.vanishList.size(); i++) {
            if (!event.getPlayer().isOp()) {
                event.getPlayer().hidePlayer(Lazycore.plugin, Lazycore.vanishList.get(i));
            }
        }

        if (FileConfig.getConfig().contains(event.getPlayer().getUniqueId().toString())) {
            event.getPlayer().sendMessage(ChatColor.ITALIC + "/login {password}");
        } else {
            event.getPlayer().sendMessage(ChatColor.ITALIC + "/register {password}");
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Lazycore.waitingList.remove(event.getPlayer());

        for (int i = 0; i < Lazycore.vanishList.size(); i++) {
            event.getPlayer().showPlayer(Lazycore.plugin, Lazycore.vanishList.get(i));
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (Lazycore.waitingList.contains(event.getPlayer())) {
            event.setCancelled(true);
        }
    }
}
