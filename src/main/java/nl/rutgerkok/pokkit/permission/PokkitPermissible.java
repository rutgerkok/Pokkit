package nl.rutgerkok.pokkit.permission;

import nl.rutgerkok.pokkit.command.PokkitCommandSender;
import nl.rutgerkok.pokkit.player.PokkitPlayer;

import org.bukkit.entity.Player;
import org.bukkit.permissions.Permissible;

/**
 * Converts beteen a Bukkit {@link Permissible} and a Nukkit
 * {@link cn.nukkit.permission.Permissible}.
 *
 */
public final class PokkitPermissible {

	public static Permissible toBukkit(cn.nukkit.permission.Permissible nukkit) {
		if (nukkit instanceof cn.nukkit.command.CommandSender) {
			return PokkitCommandSender.toBukkit((cn.nukkit.command.CommandSender) nukkit);
		}
		throw new UnsupportedOperationException("Don't know how to convert " + nukkit.getClass());
	}

	public static cn.nukkit.permission.Permissible toNukkit(Permissible pokkit) {
		if (pokkit instanceof PokkitPlayer) {
			return PokkitPlayer.toNukkit((Player) pokkit);
		}
		throw new UnsupportedOperationException("Don't know how to convert " + pokkit.getClass());
	}

}
