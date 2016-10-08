package nl.rutgerkok.pokkit.pluginservice;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_99_R9.CraftServer;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginLoadOrder;
import org.bukkit.plugin.PluginManager;

import cn.nukkit.plugin.PluginBase;

/**
 * Loads and enables the Bukkit plugins.
 */
public final class PluginService implements PokkitService {

	private Plugin[] plugins = null;

	private void enablePlugins(PluginLoadOrder pluginLoadOrder) {
		PluginManager pluginManager = Bukkit.getServer().getPluginManager();
		for (Plugin plugin : plugins) {
			if (plugin.getDescription().getLoad() == pluginLoadOrder) {
				pluginManager.enablePlugin(plugin);
			}
		}
	}

	@Override
	public void onEnable(PluginBase pokkit) {
		enablePlugins(PluginLoadOrder.POSTWORLD);
		plugins = null; // no longer needed, Nukkit takes care of them now
	}

	@Override
	public void onLoad(PluginBase pokkit) {
		CraftServer server = ((CraftServer) Bukkit.getServer());
		plugins = server.loadPlugins();
		enablePlugins(PluginLoadOrder.STARTUP);
	}

}
