package nl.rutgerkok.pokkit.player;

import java.util.Map;
import java.util.WeakHashMap;

public class OnlinePlayerData {

	private Map<cn.nukkit.Player, PokkitPlayer> nukkitToBukkit = new WeakHashMap<>();

	/**
	 * Gets the Pokkit player instance beloning to the Nukkit player instance.
	 * 
	 * @param player
	 *            The Nukkit player instance.
	 * @return The Pokkit player instance.
	 */
	public PokkitPlayer getPlayer(cn.nukkit.Player player) {
		return nukkitToBukkit.computeIfAbsent(player, PokkitPlayer::new);
	}

	/**
	 * This method should be called when a player logs out. Although this class
	 * using a {@link WeakHashMap}, entries should still be removed as quickly
	 * as possible.
	 *
	 * @param player
	 *            The player.
	 */
	public void logsOut(cn.nukkit.Player player) {
		nukkitToBukkit.remove(player);
	}
}
