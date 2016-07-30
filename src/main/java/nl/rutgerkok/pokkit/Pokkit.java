package nl.rutgerkok.pokkit;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import nl.rutgerkok.pokkit.pluginservice.MainScoreboardService;
import nl.rutgerkok.pokkit.pluginservice.PermissionsYml;
import nl.rutgerkok.pokkit.pluginservice.PlayerBlockEvents;
import nl.rutgerkok.pokkit.pluginservice.PlayerChatEvents;
import nl.rutgerkok.pokkit.pluginservice.PlayerConnectEvents;
import nl.rutgerkok.pokkit.pluginservice.PlayerInteractEvents;
import nl.rutgerkok.pokkit.pluginservice.PluginService;
import nl.rutgerkok.pokkit.pluginservice.PokkitService;

import org.bukkit.Bukkit;

import com.google.common.collect.Maps;

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

    private boolean errored = false;

    private final List<PokkitService> services = Arrays.asList(
            new MainScoreboardService(),
            new PermissionsYml(),
            new PluginService(),
            new PlayerBlockEvents(),
            new PlayerConnectEvents(),
            new PlayerChatEvents(),
            new PlayerInteractEvents());

    private void error(String error) {
        Bukkit.getServer().getLogger().severe(error);
    }

    @Override
    public void onDisable() {
        if (errored) {
            return;
        }
        services.forEach(service -> service.onDisable(this));
    }

    @Override
    public void onEnable() {
        if (errored) {
            return;
        }
        services.forEach(service -> service.onEnable(this));
    }

    @Override
    public void onLoad() {
        File pluginFolder = new File(this.getDataFolder(), "bukkitPlugins");
        if (!pluginFolder.exists()) {
            pluginFolder.mkdirs();
        }

        Logger logger = new PokkitLogger(this.getLogger());
        LogManager.getLogManager().addLogger(logger);

        PokkitServer server = new PokkitServer(this.getServer(), logger, pluginFolder);
        Bukkit.setServer(server);

        try {
            Maps.newHashMap(); // Test whether Google Guava is present

            // Load the services
            services.forEach(service -> service.onLoad(this));
        } catch (NoSuchMethodError e) {
            if (!e.getMessage().contains("com.google.common.collect.")) {
                // Some unrelated error
                throw e;
            }
            // Google Collections (provided by Nukkit) is loaded instead of
            // Guava. This is problematic, as we cannot load any Bukkit plugins
            // this way.
            error("Thanks for installing " + NAME + "! To complete the installation,");
            error("open up the Nukkit JAR file with a ZIP editor (like 7-zip) and delete");
            error("the com/google/common folder. Do NOT delete any other folder.");
            error("Unfortunately, this is a hard requirement for " + NAME + " to load at");
            error("all. Nukkit should still work fine after deleting the directory as long");
            error("as " + NAME + " is installed: " + NAME + " ships with a Bukkit-compatible");
            error("replacement of the contents of that folder.");
            error("If you ever decide to uninstall " + NAME + ", you'll need an untouched");
            error("copy of the Nukkit JAR file again, so please keep a backup of the Nukkit");
            error("JAR file before you modify it.");
            setEnabled(false);
            errored = true;
        }

    }
}
