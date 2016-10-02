package nl.rutgerkok.pokkit.pluginservice;

import java.net.InetAddress;
import java.net.UnknownHostException;

import nl.rutgerkok.pokkit.PokkitServer;
import nl.rutgerkok.pokkit.player.PokkitPlayer;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerQuitEvent;

import cn.nukkit.event.EventHandler;
import cn.nukkit.lang.TextContainer;

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
	public void onPlayerLogin(cn.nukkit.event.player.PlayerLoginEvent event) {
		if (canIgnore(PlayerLoginEvent.getHandlerList())) {
			return;
		}

		Player bukkitPlayer = PokkitPlayer.toBukkit(event.getPlayer());
		InetAddress address = bukkitPlayer.getAddress().getAddress();
		try {
			// Due to some random... "bug", for some reason address is null.
			// This tries to fix the issue by creating a InetAddress from the host string.
			// However this has a problem, it uses the network IP if it is a local server.
			// But again, it is better having a NPE error on plugins or having an almost perfect fix?
			//
			// Workaround for MaxBans' LoginListener error
			InetAddress addr = InetAddress.getByName(bukkitPlayer.getAddress().getHostString());
			address = addr;
		} catch (UnknownHostException e) {
			// If an exception is thrown, don't do anything, just keep the old address.
			e.printStackTrace();
		}
		PlayerLoginEvent bukkitEvent = new PlayerLoginEvent(bukkitPlayer, bukkitPlayer.getAddress().getHostName(),
				address, event.isCancelled() ? Result.KICK_OTHER : Result.ALLOWED, event.getKickMessage(), address);
		Bukkit.getPluginManager().callEvent(bukkitEvent);
		event.setCancelled(bukkitEvent.getResult() != Result.ALLOWED);

		event.setKickMessage(bukkitEvent.getKickMessage());
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
