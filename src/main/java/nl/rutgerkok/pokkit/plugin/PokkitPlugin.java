package nl.rutgerkok.pokkit.plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import nl.rutgerkok.pokkit.Pokkit;
import nl.rutgerkok.pokkit.command.PokkitCommandSender;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_99_R9.CraftServer;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.plugin.PluginDescription;
import cn.nukkit.plugin.PluginLoader;
import cn.nukkit.plugin.PluginLogger;
import cn.nukkit.utils.Config;

/**
 * A wrapper around a Bukkit plugin, for use with Nukkit.
 *
 */
public class PokkitPlugin implements cn.nukkit.plugin.Plugin {

	/**
	 * For showing Nukkit plugins to Bukkit.
	 */
	private static class NukkitPluginWrapper implements Plugin {

		private final cn.nukkit.plugin.Plugin nukkit;

		private NukkitPluginWrapper(cn.nukkit.plugin.Plugin nukkit) {
			this.nukkit = Objects.requireNonNull(nukkit);
			if (nukkit instanceof PokkitPlugin) {
				throw new IllegalArgumentException("Cannot wrap Bukkit plugin into Nukkit plugin into Bukkit plugin");
			}
		}

		@Override
		public FileConfiguration getConfig() {
			throw Pokkit.unsupported();
		}

		@Override
		public File getDataFolder() {
			return nukkit.getDataFolder();
		}

		@Override
		public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
			return null;
		}

		@Override
		public PluginDescriptionFile getDescription() {
			PluginDescription nukkitDescription = nukkit.getDescription();
			return new PluginDescriptionFile(nukkitDescription.getName(), nukkitDescription.getVersion(),
					nukkitDescription.getMain());
		}

		@Override
		public Logger getLogger() {
			return Logger.getLogger(getName());
		}

		@Override
		public String getName() {
			return nukkit.getName();
		}

		@Override
		public org.bukkit.plugin.PluginLoader getPluginLoader() {
			throw Pokkit.unsupported();
		}

		@Override
		public InputStream getResource(String filename) {
			return nukkit.getResource(filename);
		}

		@Override
		public org.bukkit.Server getServer() {
			throw Pokkit.unsupported();
		}

		@Override
		public boolean isEnabled() {
			return nukkit.isEnabled();
		}

		@Override
		public boolean isNaggable() {
			return false;
		}

		@Override
		public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command command,
				String label, String[] args) {
			throw Pokkit.unsupported();
		}

		@Override
		public void onDisable() {
			throw Pokkit.unsupported();
		}

		@Override
		public void onEnable() {
			throw Pokkit.unsupported();
		}

		@Override
		public void onLoad() {
			throw Pokkit.unsupported();
		}

		@Override
		public List<String> onTabComplete(org.bukkit.command.CommandSender sender, org.bukkit.command.Command command,
				String alias, String[] args) {
			return Collections.emptyList();
		}

		@Override
		public void reloadConfig() {
			throw Pokkit.unsupported();
		}

		@Override
		public void saveConfig() {
			throw Pokkit.unsupported();
		}

		@Override
		public void saveDefaultConfig() {
			throw Pokkit.unsupported();
		}

		@Override
		public void saveResource(String resourcePath, boolean replace) {
			throw Pokkit.unsupported();
		}

		@Override
		public void setNaggable(boolean canNag) {
			// Empty!
		}

	}

	/**
	 * Gets the plugin as a Bukkit plugin. Either this method gets the
	 * underlying Bukkit plugin of a Nukkit wrapper, or creates a Bukkit wrapper
	 * for a Nukkit plugin.
	 *
	 * @param plugin
	 *            The Nukkit wrapper.
	 * @return The Bukkit plugin.
	 */
	public static Plugin toBukkit(cn.nukkit.plugin.Plugin plugin) {
		if (plugin == null) {
			return null;
		}
		if (plugin instanceof PokkitPlugin) {
			return ((PokkitPlugin) plugin).bukkit;
		}
		return new NukkitPluginWrapper(plugin);
	}

	/**
	 * Gets the Nukkit wrapper for the given Bukkit plugin.
	 *
	 * @param plugin
	 *            The Bukkit plugin.
	 * @return The Nukkit wrapper.
	 */
	public static cn.nukkit.plugin.Plugin toNukkit(Plugin plugin) {
		return CraftServer.toNukkit(Bukkit.getServer()).getPluginManager().getPlugin(plugin.getName());
	}

	private final JavaPlugin bukkit;
	private final PluginDescription pluginDescription;
	private final PluginLoader loader;

	private final PluginLogger logger;

	PokkitPlugin(JavaPlugin bukkit, PluginDescription pluginDescription, PluginLoader pluginLoader) throws IOException {
		this.bukkit = Objects.requireNonNull(bukkit);
		this.pluginDescription = Objects.requireNonNull(pluginDescription);
		this.loader = Objects.requireNonNull(pluginLoader);

		this.logger = new PluginLogger(this);
	}

	private PluginCommand getBukkitCommand(Command command) {
		org.bukkit.command.PluginCommand bukkitCommand = Bukkit.getServer().getPluginCommand(command.getName());
		if (bukkitCommand == null || !bukkitCommand.getPlugin().equals(bukkit)) {
			// Not registered, or registered by other plugin
			bukkitCommand = Bukkit.getServer()
					.getPluginCommand(pluginDescription.getName().toLowerCase() + ":" + command.getName());
		}
		return bukkitCommand;
	}

	@Override
	public Config getConfig() {
		// Maybe we want to allow Nukkit to have a look into Bukkit plugin
		// config files?
		return new Config();
	}

	@Override
	public File getDataFolder() {
		return bukkit.getDataFolder();
	}

	@Override
	public PluginDescription getDescription() {
		return pluginDescription;
	}

	@Override
	public PluginLogger getLogger() {
		return logger;
	}

	@Override
	public String getName() {
		return bukkit.getName();
	}

	@Override
	public PluginLoader getPluginLoader() {
		return loader;
	}

	@Override
	public InputStream getResource(String filename) {
		return bukkit.getResource(filename);
	}

	@Override
	public Server getServer() {
		return CraftServer.toNukkit(Bukkit.getServer());
	}

	@Override
	public boolean isDisabled() {
		return !bukkit.isEnabled();
	}

	@Override
	public boolean isEnabled() {
		return bukkit.isEnabled();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		PluginCommand bukkitCommand = getBukkitCommand(command);
		if (bukkitCommand == null) {
			return false;
		}
		return bukkitCommand.getExecutor().onCommand(PokkitCommandSender.toBukkit(sender), bukkitCommand, label, args);
	}

	@Override
	public void onDisable() {
		bukkit.onDisable();
	}

	@Override
	public void onEnable() {
		bukkit.onEnable();
	}

	@Override
	public void onLoad() {
		bukkit.onLoad();
	}

	@Override
	public void reloadConfig() {
		bukkit.reloadConfig();
	}

	@Override
	public void saveConfig() {
		bukkit.saveConfig();
	}

	@Override
	public void saveDefaultConfig() {
		bukkit.saveDefaultConfig();
	}

	@Override
	public boolean saveResource(String resourcePath) {
		bukkit.saveResource(resourcePath, false);
		return true;
	}

	@Override
	public boolean saveResource(String resourcePath, boolean replace) {
		bukkit.saveResource(resourcePath, replace);
		return true;
	}

	@Override
	public boolean saveResource(String filename, String outputName, boolean replace) {
		throw Pokkit.unsupported();
	}

	@Override
	public String toString() {
		return bukkit.toString();
	}
}
