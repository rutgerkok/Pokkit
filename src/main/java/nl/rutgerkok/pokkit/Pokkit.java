package nl.rutgerkok.pokkit;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;

import cn.nukkit.plugin.PluginBase;
import nl.rutgerkok.pokkit.pluginservice.MainScoreboardService;
import nl.rutgerkok.pokkit.pluginservice.PermissionsYml;
import nl.rutgerkok.pokkit.pluginservice.PlayerBlockEvents;
import nl.rutgerkok.pokkit.pluginservice.PlayerChatEvents;
import nl.rutgerkok.pokkit.pluginservice.PlayerConnectEvents;
import nl.rutgerkok.pokkit.pluginservice.PlayerInteractEvents;
import nl.rutgerkok.pokkit.pluginservice.PluginService;
import nl.rutgerkok.pokkit.pluginservice.PokkitService;

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

    private final List<PokkitService> services = Arrays.asList(
            new MainScoreboardService(),
            new PermissionsYml(),
            new PluginService(),
            new PlayerBlockEvents(),
            new PlayerConnectEvents(),
            new PlayerChatEvents(),
            new PlayerInteractEvents());

    @Override
    public void onDisable() {
        services.forEach(service -> service.onDisable(this));
    }

    @Override
    public void onEnable() {
        services.forEach(service -> service.onEnable(this));
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

        services.forEach(service -> service.onLoad(this));
    }
}
