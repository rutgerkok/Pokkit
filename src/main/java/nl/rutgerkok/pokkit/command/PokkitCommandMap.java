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
 * Class that transforms Nukkit commands into Bukkit commands.
 * <p>
 * It is important that only one instance of each {@link PluginCommand Bukkit
 * command} exists, so that changes to
 * {@link PluginCommand#setExecutor(CommandExecutor) the command executor} are
 * still present when calling {@link #getPluginCommand(String)} for a second
 * time.
 */
public final class PokkitCommandMap {

	private final Function<String, PluginIdentifiableCommand> nukkitCommandMap;
	private final Map<PluginIdentifiableCommand, PluginCommand> toBukkitCommand = new IdentityHashMap<>();

	public PokkitCommandMap(Function<String, PluginIdentifiableCommand> nukkitCommandMap) {
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
		Plugin bukkitPlugin = PokkitPlugin.toBukkit((PokkitPlugin) nukkitCommand.getPlugin());
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

	public PluginCommand getPluginCommand(String name) {
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
