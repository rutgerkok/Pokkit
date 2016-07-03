package nl.rutgerkok.pokkit.pluginservice;

import nl.rutgerkok.pokkit.Pokkit;
import nl.rutgerkok.pokkit.PokkitServer;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginLoadOrder;
import org.bukkit.plugin.PluginManager;

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
    public void onEnable(Pokkit pokkit) {
        enablePlugins(PluginLoadOrder.POSTWORLD);
        plugins = null; // no longer needed, Nukkit takes care of them now
    }

    @Override
    public void onLoad(Pokkit pokkit) {
        PokkitServer server = ((PokkitServer) Bukkit.getServer());
        plugins  = server.loadPlugins();
        enablePlugins(PluginLoadOrder.STARTUP);
    }


}
