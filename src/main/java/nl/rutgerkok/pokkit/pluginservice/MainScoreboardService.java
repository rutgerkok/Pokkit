package nl.rutgerkok.pokkit.pluginservice;

import java.io.File;
import java.io.IOException;

import nl.rutgerkok.pokkit.Pokkit;
import nl.rutgerkok.pokkit.scoreboard.ScoreboardPersister;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * Saves and loads the main scoreboard.
 *
 */
public final class MainScoreboardService implements PokkitService {

    private final ScoreboardPersister persister = new ScoreboardPersister();

    private File getScoreboardFile(Pokkit pokkit) {
        return new File(pokkit.getDataFolder(), "scoreboard.yml");
    }

    @Override
    public void onDisable(Pokkit pokkit) {
        YamlConfiguration scoreboardConfig = new YamlConfiguration();
        persister.saveScoreboard(scoreboardConfig, Bukkit.getScoreboardManager().getMainScoreboard());
        try {
            scoreboardConfig.save(getScoreboardFile(pokkit));
        } catch (IOException e) {
            pokkit.getLogger().error("Error saving " + getScoreboardFile(pokkit).getName(), e);
        }
    }

    @Override
    public void onLoad(Pokkit pokkit) {
        YamlConfiguration scoreboardConfig = YamlConfiguration.loadConfiguration(getScoreboardFile(pokkit));
        persister.loadScoreboard(scoreboardConfig, Bukkit.getScoreboardManager().getMainScoreboard());
    }

}
