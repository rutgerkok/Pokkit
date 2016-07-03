package nl.rutgerkok.pokkit.pluginservice;

import nl.rutgerkok.pokkit.Pokkit;
import nl.rutgerkok.pokkit.PokkitServer;
import nl.rutgerkok.pokkit.player.PokkitPlayer;

import org.bukkit.Bukkit;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;

public final class PlayerEvents implements PokkitService, Listener {

    private void callCancellable(cn.nukkit.event.Cancellable nukkit, Cancellable bukkit) {
        bukkit.setCancelled(nukkit.isCancelled());
        Bukkit.getPluginManager().callEvent((Event) bukkit);
        nukkit.setCancelled(bukkit.isCancelled());
    }

    private void callUncancellable(Event bukkit) {
        Bukkit.getPluginManager().callEvent(bukkit);
    }

    private boolean canIgnore(HandlerList handlerList) {
        return handlerList.getRegisteredListeners().length == 0;
    }

    @Override
    public void onEnable(Pokkit pokkit) {
        pokkit.getServer().getPluginManager().registerEvents(this, pokkit);
    }

    @EventHandler(ignoreCancelled = false)
    public void onPlayerJoin(cn.nukkit.event.player.PlayerJoinEvent event) {
        if (canIgnore(PlayerJoinEvent.getHandlerList())) {
            return;
        }

        PlayerJoinEvent bukkitEvent = new PlayerJoinEvent(
                PokkitPlayer.toBukkit(event.getPlayer()), event.getJoinMessage().getText());
        callUncancellable(bukkitEvent);
        event.setJoinMessage(bukkitEvent.getJoinMessage());
    }

    @EventHandler(ignoreCancelled = false)
    public void onPlayerKick(cn.nukkit.event.player.PlayerKickEvent event) {
        if (!canIgnore(PlayerKickEvent.getHandlerList())) {
            PlayerKickEvent bukkitEvent = new PlayerKickEvent(
                    PokkitPlayer.toBukkit(event.getPlayer()), event.getReason(), event.getQuitMessage().getText());
            callCancellable(event, bukkitEvent);
            event.setQuitMessage(bukkitEvent.getLeaveMessage());
        }

        removeFromOnlinePlayers(event.getPlayer());
    }

    @EventHandler(ignoreCancelled = false)
    public void onPlayerQuit(cn.nukkit.event.player.PlayerQuitEvent event) {
        if (!canIgnore(PlayerQuitEvent.getHandlerList())) {
            PlayerQuitEvent bukkitEvent = new PlayerQuitEvent(PokkitPlayer.toBukkit(event.getPlayer()), event.getQuitMessage().getText());
            callUncancellable(bukkitEvent);
            event.setQuitMessage(bukkitEvent.getQuitMessage());
        }

        removeFromOnlinePlayers(event.getPlayer());
    }

    private void removeFromOnlinePlayers(cn.nukkit.Player player) {
        ((PokkitServer) Bukkit.getServer()).getOnlinePlayerData().logsOut(player);
    }

}
