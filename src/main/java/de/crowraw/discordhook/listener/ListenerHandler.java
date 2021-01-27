package de.crowraw.discordhook.listener;

import de.crowraw.discordhook.DiscordHook;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;


public class ListenerHandler implements Listener {
    private DiscordHook discordHook = DiscordHook.getInstance();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        discordHook.sendMessage("`" + event.getPlayer().getName() + "`" + " joined the server!");
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        discordHook.sendMessage("`" + event.getPlayer().getName() + "`" + " left the server!");
    }

    @EventHandler
    public void onMessage(AsyncPlayerChatEvent event) {
        discordHook.sendMessage("`" + event.getPlayer().getName() + "`" + " just wrote: " + event.getMessage());
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (event.getEntity().getKiller() != null) {
            Player killer = player.getKiller();
            discordHook.sendMessage("`" + player.getName() + "`" + " was killed by " + "`" + killer.getName() + "`");
            return;
        }
        discordHook.sendMessage("`" + player.getName() + "`" + " was killed!");
    }
}
