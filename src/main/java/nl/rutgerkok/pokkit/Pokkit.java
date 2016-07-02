package nl.rutgerkok.pokkit;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginLoadOrder;

import cn.nukkit.plugin.PluginBase;

/**
 * Startup class.
 *
 */
public final class Pokkit extends PluginBase {

    public static final String NAME = "Pokkit";
    public static final String VERSION = "1.10-R0.1-SNAPSHOT";

    /**
     * Use {@code throw Pokkit.unsupported()} to indicate that a Bukkit API
     * method has not been implemented yet.
     *
     * @return Never returns normally. The only reason that this type is present
     *         is so that you can use {@code throw Pokkit.unsupported()}, which
     *         prevents compile errors.
     * @throws UnsupportedOperationException
     *             Always.
     */
    public static final RuntimeException unsupported() {
        throw new UnsupportedOperationException("This method is not supported yet by " + NAME + " " + VERSION);
    }

    private Plugin[] plugins = null;

    private void enablePlugins(PluginLoadOrder pluginLoadOrder) {
        for (Plugin plugin : plugins) {
            if (plugin.getDescription().getLoad() == pluginLoadOrder) {
                Bukkit.getServer().getPluginManager().enablePlugin(plugin);
            }
        }
    }

    @Override
    public void onEnable() {
        enablePlugins(PluginLoadOrder.POSTWORLD);
        plugins = null; // field is no longer needed
    }

    @Override
    public void onLoad() {
        File pluginFolder = new File(this.getDataFolder(), "bukkitPlugins");
        if (!pluginFolder.exists()) {
            pluginFolder.mkdirs();
        }

        Logger logger = new PokkitLogger(this.getLogger());
        PokkitServer server = new PokkitServer(this.getServer(), logger, pluginFolder);
        Bukkit.setServer(server);

        plugins = server.loadPlugins();
        enablePlugins(PluginLoadOrder.STARTUP);
    }
}
