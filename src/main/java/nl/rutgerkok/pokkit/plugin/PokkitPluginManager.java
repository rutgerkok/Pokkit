package nl.rutgerkok.pokkit.plugin;

import java.io.File;

import org.bukkit.Server;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPluginLoader;

public final class PokkitPluginManager {

    /**
     * Creates a plugin manager.
     * 
     * @param server
     *            The server.
     * @param commandMap
     *            The command map of the server.
     * @param pluginFolder
     *            The folder the plugins are stored in.
     * @return The plugin manager.
     */
    public static SimplePluginManager create(Server server, SimpleCommandMap commandMap, File pluginFolder) {
        SimplePluginManager manager = new SimplePluginManager(server, commandMap);
        manager.registerInterface(JavaPluginLoader.class);
        return manager;
    }

    /**
     * Loads the Bukkit plugins.
     * 
     * @param pluginManager
     *            The plugin manager.
     * @param pluginFolder
     *            The plugin folder.
     */
    public static void loadPlugins(SimplePluginManager pluginManager, File pluginFolder) {
        Plugin[] plugins = pluginManager.loadPlugins(pluginFolder);
        for (Plugin plugin : plugins) {
            pluginManager.enablePlugin(plugin);
        }
    }
}
