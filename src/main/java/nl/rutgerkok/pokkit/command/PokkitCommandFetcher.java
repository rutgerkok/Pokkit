package nl.rutgerkok.pokkit.command;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import nl.rutgerkok.pokkit.plugin.PokkitPlugin;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;

import cn.nukkit.command.PluginIdentifiableCommand;

/**
 * Retrieves commands of Bukkit plugins. Nukkit directly reads the plugin.yml of
 * Bukkit plugins, so the commands of Bukkit plugins end up in the Nukkit
 * command map. This class provides programmatic access to them for Bukkit
 * plugins, for example to support
 * {@link PluginCommand#setExecutor(CommandExecutor) changing the command
 * executor}.
 * <p>
 * It is important that only one instance of each {@link PluginCommand Bukkit
 * command} exists, so that changes to
 * {@link PluginCommand#setExecutor(CommandExecutor) the command executor} are
 * still present when calling {@link #getBukkitPluginCommand(String)} for a
 * second time.
 */
public final class PokkitCommandFetcher {

	private final Function<String, PluginIdentifiableCommand> nukkitCommandMap;
	private final Map<PluginIdentifiableCommand, PluginCommand> toBukkitCommand = new IdentityHashMap<>();

	public PokkitCommandFetcher(Function<String, PluginIdentifiableCommand> nukkitCommandMap) {
		this.nukkitCommandMap = Objects.requireNonNull(nukkitCommandMap, "nukkitCommandMap");
	}

	/**
	 * Creates a new Bukkit command.
	 *
	 * @param nukkitCommand
	 *            The Nukkit command.
	 * @return The plugin command.
	 * @throws ClassCastException
	 *             If the nukkitCommand is not provided by a Bukkit plugin.
	 */
	private PluginCommand createNewBukkitCommand(cn.nukkit.command.PluginCommand<?> nukkitCommand) {
		Plugin bukkitPlugin = PokkitPlugin.toBukkit(nukkitCommand.getPlugin());
		try {
			Constructor<PluginCommand> constructor = PluginCommand.class.getDeclaredConstructor(String.class,
					Plugin.class);
			constructor.setAccessible(true);
			PluginCommand bukkitCommand = constructor.newInstance(nukkitCommand.getName(), bukkitPlugin);

			bukkitCommand.setAliases(Arrays.asList(nukkitCommand.getAliases()));
			bukkitCommand.setDescription(nukkitCommand.getDescription());
			bukkitCommand.setLabel(nukkitCommand.getLabel());
			bukkitCommand.setPermission(nukkitCommand.getPermission());
			bukkitCommand.setPermissionMessage(nukkitCommand.getPermissionMessage());
			bukkitCommand.setUsage(nukkitCommand.getUsage());

			return bukkitCommand;
		} catch (ReflectiveOperationException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Gets a command of a Bukkit plugin.
	 * <p>
	 * Multple invocations of this method with the same name will return the
	 * same command instance.
	 *
	 * @param name
	 *            The command name.
	 * @return The command.
	 */
	public PluginCommand getBukkitPluginCommand(String name) {
		cn.nukkit.command.PluginCommand<?> nukkitCommand = (cn.nukkit.command.PluginCommand<?>) nukkitCommandMap
				.apply(name);

		if (nukkitCommand == null) {
			// No command exists with that name
			return null;
		}
		if (!(nukkitCommand.getPlugin() instanceof PokkitPlugin)) {
			// Command not provided by a Bukkit plugin
			return null;
		}

		PluginCommand bukkitCommand = toBukkitCommand.get(nukkitCommand);
		if (bukkitCommand == null) {
			bukkitCommand = createNewBukkitCommand(nukkitCommand);
			toBukkitCommand.put(nukkitCommand, bukkitCommand);
		}
		return bukkitCommand;
	}
}
