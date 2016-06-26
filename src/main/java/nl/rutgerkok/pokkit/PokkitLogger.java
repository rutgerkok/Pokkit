package nl.rutgerkok.pokkit;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import cn.nukkit.plugin.PluginLogger;
import cn.nukkit.utils.LogLevel;

/**
 * Java Logger that forwards to Nukkit's logger.
 *
 */
public class PokkitLogger extends Logger {

    private final PluginLogger nukkit;

    protected PokkitLogger(PluginLogger nukkit) {
        super(Pokkit.NAME, null);
        this.setLevel(Level.FINE);
        this.nukkit = nukkit;
    }

    @Override
    public void log(LogRecord record) {
        String message = record.getMessage();
        LogLevel level = toNukkitLevel(record.getLevel());
        if (level == null) {
            return;
        }

        Throwable thrown = record.getThrown();
        if (thrown == null) {
            nukkit.log(level, message);
        } else {
            nukkit.log(level, message, thrown);
        }
    }

    private LogLevel toNukkitLevel(Level level) {
        if (level == Level.SEVERE) {
            return LogLevel.ERROR;
        }
        if (level == Level.WARNING) {
            return LogLevel.WARNING;
        }
        if (level == Level.INFO) {
            return LogLevel.INFO;
        }
        if (level == Level.CONFIG || level == Level.FINE) {
            return LogLevel.DEBUG;
        }
        return null;
    }

}
