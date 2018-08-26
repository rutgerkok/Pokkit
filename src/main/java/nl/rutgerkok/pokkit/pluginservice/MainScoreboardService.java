package nl.rutgerkok.pokkit.pluginservice;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import nl.rutgerkok.pokkit.scoreboard.PokkitScoreboard;
import nl.rutgerkok.pokkit.scoreboard.ScoreboardPersister;

import cn.nukkit.plugin.PluginBase;

/**
 * Saves and loads the main scoreboard.
 *
 */
public final class MainScoreboardService implements PokkitService {

	private final ScoreboardPersister persister = new ScoreboardPersister();

	private File getScoreboardFile(PluginBase pokkit) {
		return new File(pokkit.getDataFolder(), "scoreboard.yml");
	}

	@Override
	public void onDisable(PluginBase pokkit) {
		YamlConfiguration scoreboardConfig = new YamlConfiguration();
		persister.saveScoreboard(scoreboardConfig, Bukkit.getScoreboardManager().getMainScoreboard());
		scoreboardConfig.options().header("This is the scoreboard data of the main scoreboard.");
		try {
			scoreboardConfig.save(getScoreboardFile(pokkit));
		} catch (IOException e) {
			Bukkit.getLogger().log(Level.SEVERE, "Error saving " + getScoreboardFile(pokkit).getName(), e);
		}
	}

	@Override
	public void onLoad(PluginBase pokkit) {
		YamlConfiguration scoreboardConfig = YamlConfiguration.loadConfiguration(getScoreboardFile(pokkit));
		persister.loadScoreboard(scoreboardConfig,
				(PokkitScoreboard) Bukkit.getScoreboardManager().getMainScoreboard());
	}

}
