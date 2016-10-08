package org.bukkit.craftbukkit.v1_99_R9;

import nl.rutgerkok.pokkit.command.PokkitCommandFetcher;
import nl.rutgerkok.pokkit.command.PokkitCommandSender;

import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.SimpleCommandMap;

/**
 * In Bukkit, commands cannot be registered dynamically: registration is only
 * allowed through the plugin.yml. Some Bukkit plugins need to hack around this,
 * so they access the SimpleCommandMap in CraftServer, and call its
 * {@link #register(String, String, Command)} method. To support this pattern,
 * we create a class extending SimpleCommandMap (this class) that forwards all
 * registrations to Nukkit.
 * <p>
 * Note that this class extends SimpleCommandMap only because of compatibility
 * reasons with the aforementioned plugins. This class is not a complete
 * implementation; all getters in this class will return no results. This is
 * because the {@code knownCommands} field in SimpleCommandMap stays empty; we
 * have overridden the register method to ignore that field, and register the
 * command in Nukkit instead.
 * <p>
 * See {@link PokkitCommandFetcher} for a description on how Bukkit plugin
 * commands normally end up in the Nukkit command map.
 */
final class NotSoSimpleCommandMap extends SimpleCommandMap {

	private class WrappedCommand extends cn.nukkit.command.Command {

		private final Command bukkit;

		public WrappedCommand(Command bukkit) {
			super(bukkit.getName(), bukkit.getDescription(), bukkit.getUsage(), bukkit.getAliases().toArray(new String[0]));
			this.bukkit = bukkit;
		}

		@Override
		public boolean execute(cn.nukkit.command.CommandSender sender, String commandLabel, String[] args) {
			return bukkit.execute(PokkitCommandSender.toBukkit(sender), commandLabel, args);
		}

	}

	public NotSoSimpleCommandMap(Server server) {
		super(server);
	}

	@Override
	public boolean register(String label, String fallbackPrefix, Command command) {
		if (fallbackPrefix.equals("bukkit")) {
			// SimpleCommandMap automatically registers some Bukkit commands
			// ("/version", "/plugins", etc.) in the constructor. We ignore
			// those commands, as Nukkit has better implementations of those.
			return true;
		}
		// Forward to Nukkit
		return cn.nukkit.Server.getInstance()
				.getCommandMap()
				.register(fallbackPrefix, new WrappedCommand(command), label);
	}

}
