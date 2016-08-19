package nl.rutgerkok.pokkit.pluginservice;

import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import cn.nukkit.event.EventHandler;
import cn.nukkit.lang.TextContainer;
import nl.rutgerkok.pokkit.PokkitServer;
import nl.rutgerkok.pokkit.player.PokkitPlayer;

public final class PlayerConnectEvents extends EventTranslator {

    @EventHandler(ignoreCancelled = false)
    public void onPlayerJoin(cn.nukkit.event.player.PlayerJoinEvent event) {
        if (canIgnore(PlayerJoinEvent.getHandlerList())) {
            return;
        }

        PlayerJoinEvent bukkitEvent = new PlayerJoinEvent(PokkitPlayer.toBukkit(event.getPlayer()),
                event.getJoinMessage().getText());
        callUncancellable(bukkitEvent);
        event.setJoinMessage(toNukkit(event.getJoinMessage(), bukkitEvent.getJoinMessage(), event));
    }

    @EventHandler(ignoreCancelled = false)
    public void onPlayerKick(cn.nukkit.event.player.PlayerKickEvent event) {
        if (!canIgnore(PlayerKickEvent.getHandlerList())) {
            PlayerKickEvent bukkitEvent = new PlayerKickEvent(PokkitPlayer.toBukkit(event.getPlayer()),
                    event.getReason(), event.getQuitMessage().getText());
            callCancellable(event, bukkitEvent);
            event.setQuitMessage(toNukkit(event.getQuitMessage(), bukkitEvent.getLeaveMessage(), event));
        }

        removeFromOnlinePlayers(event.getPlayer());
    }

    @EventHandler(ignoreCancelled = false)
    public void onPlayerQuit(cn.nukkit.event.player.PlayerQuitEvent event) {
        if (!canIgnore(PlayerQuitEvent.getHandlerList())) {
            PlayerQuitEvent bukkitEvent = new PlayerQuitEvent(PokkitPlayer.toBukkit(event.getPlayer()),
                    event.getQuitMessage().getText());
            callUncancellable(bukkitEvent);
            event.setQuitMessage(toNukkit(event.getQuitMessage(), bukkitEvent.getQuitMessage(), event));
        }

        removeFromOnlinePlayers(event.getPlayer());
    }

    private void removeFromOnlinePlayers(cn.nukkit.Player player) {
        ((PokkitServer) Bukkit.getServer()).getOnlinePlayerData().logsOut(player);
    }

    private TextContainer toNukkit(TextContainer originalMessage, String message,
            cn.nukkit.event.player.PlayerEvent event) {
        if (originalMessage.getText().equals(message)) {
            return originalMessage;
        }
        return new TextContainer(message);
    }

}
