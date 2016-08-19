package nl.rutgerkok.pokkit.pluginservice;

import org.bukkit.Bukkit;

import cn.nukkit.plugin.PluginBase;

/**
 * Interface for holding code that makes Pokkit work properly.
 *
 */
public interface PokkitService {

    /**
     * Called when Pokkit is disabled.
     *
     * @param pokkit
     *            The NukkitHook instance.
     */
    default void onDisable(PluginBase pokkit) {
        // Empty!
    }

    /**
     * Called after the worlds are initialized.
     *
     * @param pokkit
     *            The NukkitHook instance.
     */
    default void onEnable(PluginBase pokkit) {
        // Empty!
    }

    /**
     * Called before world initialization. {@link Bukkit#getServer()} is already
     * available.
     *
     * @param pokkit
     *            The NukkitHook instance.
     */
    default void onLoad(PluginBase pokkit) {
        // Empty!
    }
}
