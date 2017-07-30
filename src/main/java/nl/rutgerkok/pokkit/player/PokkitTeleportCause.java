package nl.rutgerkok.pokkit.player;

import cn.nukkit.event.player.PlayerTeleportEvent.TeleportCause;

/**
 * For converting teleport causes.
 *
 */
public final class PokkitTeleportCause {

	/**
	 * Converts a Bukkit teleport cause to a Nukkit cause.
	 *
	 * @param cause
	 *            The Bukkit cause.
	 *
	 * @return The Nukkit cause.
	 */
	public static TeleportCause toNukkit(org.bukkit.event.player.PlayerTeleportEvent.TeleportCause cause) {
		switch (cause) {
		case COMMAND:
			return TeleportCause.COMMAND;
		case ENDER_PEARL:
		        return TeleportCause.ENDER_PEARL;
		case NETHER_PORTAL:
			return TeleportCause.NETHER_PORTAL;
		case PLUGIN:
			return TeleportCause.PLUGIN;
		default:
			return TeleportCause.UNKNOWN;
		}
	}

}
