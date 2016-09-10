package nl.rutgerkok.pokkit.plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import nl.rutgerkok.pokkit.Pokkit;
import nl.rutgerkok.pokkit.permission.PokkitPermissible;
import nl.rutgerkok.pokkit.permission.PokkitPermission;

import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.permissions.Permissible;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.EventExecutor;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.UnknownDependencyException;

/**
 * A plugin manager for all Bukkit plugins. Nukkit plugins can view Bukkit
 * plugins, but not the other way round.
 *
 */
public final class PokkitPluginManager implements PluginManager {

	private final cn.nukkit.plugin.PluginManager nukkit;
	private final PokkitPluginLoader pluginLoader;
	private final BukkitEventManager eventManager;

	public PokkitPluginManager(cn.nukkit.plugin.PluginManager nukkit) {
		this.nukkit = Objects.requireNonNull(nukkit);
		this.eventManager = new BukkitEventManager();

		// Register plugin loader, then retrieve it back
		if (!this.nukkit.registerInterface(PokkitPluginLoader.class)) {
			throw new RuntimeException("Loader not registered");
		}
		this.pluginLoader = PokkitPluginLoader.getInstanceBack();
	}

	@Override
	public void addPermission(Permission perm) {
		nukkit.addPermission(PokkitPermission.toNukkit(perm));
	}

	@Override
	public void callEvent(Event event) throws IllegalStateException {
		eventManager.callEvent(event);
	}

	@Override
	public void clearPlugins() {
		nukkit.clearPlugins();
	}

	@Override
	public void disablePlugin(Plugin plugin) {
		cn.nukkit.plugin.Plugin nukkitPlugin = toNukkitPlugin(plugin);
		if (nukkitPlugin == null) {
			return;
		}
		nukkit.disablePlugin(nukkitPlugin);
	}

	@Override
	public void disablePlugins() {
		nukkit.disablePlugins();
	}

	@Override
	public void enablePlugin(Plugin plugin) {
		cn.nukkit.plugin.Plugin nukkitPlugin = toNukkitPlugin(plugin);
		if (nukkitPlugin == null) {
			return;
		}
		nukkit.enablePlugin(nukkitPlugin);
	}

	@Override
	public Set<Permission> getDefaultPermissions(boolean op) {
		return nukkit.getDefaultPermissions(op).values().stream().map(PokkitPermission::toBukkit)
				.collect(Collectors.toSet());
	}

	@Override
	public Set<Permissible> getDefaultPermSubscriptions(boolean op) {
		return nukkit.getDefaultPermSubscriptions(op).stream().map(PokkitPermissible::toBukkit)
				.collect(Collectors.toSet());
	}

	@Override
	public Permission getPermission(String name) {
		cn.nukkit.permission.Permission nukkitPermission = nukkit.getPermission(name);
		if (nukkitPermission == null) {
			return null;
		}
		return PokkitPermission.toBukkit(nukkitPermission);
	}

	@Override
	public Set<Permission> getPermissions() {
		return nukkit.getPermissions().values().stream().map(PokkitPermission::toBukkit).collect(Collectors.toSet());
	}

	@Override
	public Set<Permissible> getPermissionSubscriptions(String permission) {
		return nukkit.getPermissionSubscriptions(permission).stream().map(PokkitPermissible::toBukkit)
				.collect(Collectors.toSet());
	}

	@Override
	public Plugin getPlugin(String name) {
		return PokkitPlugin.toBukkit(nukkit.getPlugin(name));
	}

	@Override
	public Plugin[] getPlugins() {
		return nukkit.getPlugins().values().stream().map(nukkitPlugin -> PokkitPlugin.toBukkit(nukkitPlugin))
				.toArray(Plugin[]::new);
	}

	@Override
	public boolean isPluginEnabled(Plugin plugin) {
		cn.nukkit.plugin.Plugin nukkitPlugin = toNukkitPlugin(plugin);
		if (nukkitPlugin == null) {
			return false;
		}
		return nukkit.isPluginEnabled(nukkitPlugin);
	}

	@Override
	public boolean isPluginEnabled(String name) {
		// We cannot use nukkit.isPluginEnabled(name), as that would return true
		// of Nukkit plugins, instead of only for Bukkit plugins
		Plugin plugin = this.getPlugin(name);
		if (plugin == null) {
			return false;
		}
		return isPluginEnabled(plugin);
	}

	@Override
	public Plugin loadPlugin(File file)
			throws InvalidPluginException, InvalidDescriptionException, UnknownDependencyException {

		// Load the plugin
		Map<String, cn.nukkit.plugin.PluginLoader> pluginLoaders = Collections
				.singletonMap(PokkitPluginLoader.class.getName(), this.pluginLoader);
		cn.nukkit.plugin.Plugin plugin = nukkit.loadPlugin(file, pluginLoaders);

		// Check the plugin
		if (plugin == null) {
			// Some error during plugin loading
			throw new InvalidPluginException();
		}
		if (!(plugin instanceof PokkitPlugin)) {
			// This should be impossible with our loader
			throw new RuntimeException("Unexpected plugin type: " + plugin + "(" + plugin.getClass() + ")");
		}

		// Return the plugin
		return PokkitPlugin.toBukkit(plugin);
	}

	@Override
	public Plugin[] loadPlugins(File directory) {
		// Load the plugins
		List<String> pluginLoader = Collections.singletonList(PokkitPluginLoader.class.getName());
		Collection<cn.nukkit.plugin.Plugin> nukkitPlugins = nukkit.loadPlugins(directory, pluginLoader).values();

		// Check and convert the plugins
		List<Plugin> bukkitPlugins = new ArrayList<>();
		for (cn.nukkit.plugin.Plugin nukkitPlugin : nukkitPlugins) {
			if (!(nukkitPlugin instanceof PokkitPlugin)) {
				// This should be impossible with our loader
				throw new RuntimeException(
						"Unexpected plugin type: " + nukkitPlugin + "(" + nukkitPlugin.getClass() + ")");
			}
			bukkitPlugins.add(PokkitPlugin.toBukkit(nukkitPlugin));
		}
		return bukkitPlugins.toArray(new Plugin[0]);
	}

	@Override
	public void recalculatePermissionDefaults(Permission perm) {
		nukkit.recalculatePermissionDefaults(PokkitPermission.toNukkit(perm));
	}

	@Override
	public void registerEvent(Class<? extends Event> event, Listener listener, EventPriority priority,
			EventExecutor executor, Plugin plugin) {
		eventManager.registerEvent(event, listener, priority, executor, plugin);
	}

	@Override
	public void registerEvent(Class<? extends Event> event, Listener listener, EventPriority priority,
			EventExecutor executor, Plugin plugin, boolean ignoreCancelled) {
		eventManager.registerEvent(event, listener, priority, executor, plugin, ignoreCancelled);
	}

	@Override
	public void registerEvents(Listener listener, Plugin plugin) {
		eventManager.registerEvents(listener, plugin);
	}

	@Override
	public void registerInterface(Class<? extends PluginLoader> loader) throws IllegalArgumentException {
		throw Pokkit.unsupported();

	}

	@Override
	public void removePermission(Permission perm) {
		nukkit.removePermission(PokkitPermission.toNukkit(perm));
	}

	@Override
	public void removePermission(String name) {
		nukkit.removePermission(name);
	}

	@Override
	public void subscribeToDefaultPerms(boolean op, Permissible permissible) {
		nukkit.subscribeToDefaultPerms(op, PokkitPermissible.toNukkit(permissible));
	}

	@Override
	public void subscribeToPermission(String permission, Permissible permissible) {
		nukkit.subscribeToPermission(permission, PokkitPermissible.toNukkit(permissible));
	}

	private cn.nukkit.plugin.Plugin toNukkitPlugin(Plugin plugin) {
		return nukkit.getPlugin(plugin.getName());
	}

	@Override
	public void unsubscribeFromDefaultPerms(boolean op, Permissible permissible) {
		nukkit.unsubscribeFromDefaultPerms(op, PokkitPermissible.toNukkit(permissible));
	}

	@Override
	public void unsubscribeFromPermission(String permission, Permissible permissible) {
		nukkit.unsubscribeFromPermission(permission, PokkitPermissible.toNukkit(permissible));
	}

	@Override
	public boolean useTimings() {
		return false;
	}

}
