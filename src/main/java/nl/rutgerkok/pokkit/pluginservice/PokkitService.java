package nl.rutgerkok.pokkit.pluginservice;

import nl.rutgerkok.pokkit.Pokkit;

import org.bukkit.Bukkit;

/**
 * Interface for holding code that makes Pokkit work properly.
 *
 */
public interface PokkitService {

    /**
     * Called when Pokkit is disabled.
     *
     * @param pokkit
     *            The Pokkit instance.
     */
    default void onDisable(Pokkit pokkit) {
        // Empty!
    }

    /**
     * Called after the worlds are initialized.
     *
     * @param pokkit
     *            The Pokkit instance.
     */
    default void onEnable(Pokkit pokkit) {
        // Empty!
    }

    /**
     * Called before world initialization. {@link Bukkit#getServer()} is already
     * available.
     *
     * @param pokkit
     *            The Pokkit instance.
     */
    default void onLoad(Pokkit pokkit) {
        // Empty!
    }
}
