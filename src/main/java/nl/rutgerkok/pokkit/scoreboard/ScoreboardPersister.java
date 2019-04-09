package nl.rutgerkok.pokkit.scoreboard;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.bukkit.scoreboard.Team.Option;
import org.bukkit.scoreboard.Team.OptionStatus;

/**
 * Class for saving and loading the main scoreboard.
 *
 */
public final class ScoreboardPersister {

	private void loadObjective(ConfigurationSection config, Objective objective) {
		objective.setDisplayName(config.getString("display_name", ""));

		String displaySlot = config.getString("display_slot", "");
		try {
			objective.setDisplaySlot(DisplaySlot.valueOf(displaySlot.toUpperCase()));
		} catch (IllegalArgumentException e) {
			// Ignore, no valid display slot provided
		}
	}

	/**
	 * Loads a scoreboard from a configuration file.
	 *
	 * @param config
	 *            The configuration file.
	 * @param scoreboard
	 *            The scoreboard.
	 */
	public void loadScoreboard(Configuration config, PokkitScoreboard scoreboard) {
		// Load teams
		ConfigurationSection teamsSection = config.getConfigurationSection("teams");
		if (teamsSection == null) {
			return;
		}
		teamsSection.getKeys(false).forEach(teamName -> {
			ConfigurationSection teamSection = teamsSection.getConfigurationSection(teamName);
			if (scoreboard.getTeam(teamName) != null) {
				return;
			}
			Team team = scoreboard.registerNewTeam(teamName);
			loadTeam(teamSection, team);
		});

		// Load objectives
		ConfigurationSection objectivesSection = config.getConfigurationSection("objectives");
		if (objectivesSection == null) {
			return;
		}
		objectivesSection.getKeys(false).forEach(objectiveName -> {
			ConfigurationSection objectiveSection = objectivesSection.getConfigurationSection(objectiveName);
			if (scoreboard.getObjective(objectiveName) != null) {
				return;
			}
			Objective objective = scoreboard.registerNewObjective(objectiveName,
					objectiveSection.getString("criteria", ""));
			loadObjective(objectiveSection, objective);
		});

		// Load scores
		ConfigurationSection scoresSection = config.getConfigurationSection("scores");
		if (scoresSection == null) {
			return;
		}
		scoresSection.getKeys(false).forEach(playerName -> {
			ConfigurationSection playerScores = scoresSection.getConfigurationSection(playerName);
			playerScores.getValues(false).forEach((objectiveName, score) -> {
				Objective objective = scoreboard.getObjective(objectiveName);
				if (objective == null || !(score instanceof Integer)) {
					return;
				}
				objective.getScore(playerName).setScore((Integer) score);
			});
		});
	}

	private void loadTeam(ConfigurationSection config, Team team) {
		team.setDisplayName(config.getString("display_name", ""));
		team.setPrefix(config.getString("prefix", ""));
		team.setSuffix(config.getString("suffix", ""));
		team.setOption(Option.COLLISION_RULE, toOptionStatus(config.getString("collisions", "")));
		team.setOption(Option.DEATH_MESSAGE_VISIBILITY, toOptionStatus(config.getString("death_messages", "")));
		team.setOption(Option.NAME_TAG_VISIBILITY, toOptionStatus(config.getString("name_tags", "")));
		team.setAllowFriendlyFire(config.getBoolean("friendly_fire"));
		team.setColor(toChatColor(config.getString("color", "")));

		List<String> members = config.getStringList("members");
		if (members != null) {
			members.forEach(team::addEntry);
		}
	}

	/**
	 * Saves a scoreboard to a configuration file.
	 *
	 * @param config
	 *            The configuration file.
	 * @param scoreboard
	 *            The scoreboard.
	 */
	public void saveScoreboard(Configuration config, Scoreboard scoreboard) {
		// Save teams
		ConfigurationSection teamsSection = config.createSection("teams");
		scoreboard.getTeams().forEach(team -> {
			ConfigurationSection teamSection = teamsSection.createSection(team.getName());
			saveTeam(teamSection, team);
		});

		// Save objectives
		ConfigurationSection objectivesSection = config.createSection("objectives");
		scoreboard.getObjectives().forEach(objective -> {
			ConfigurationSection objectiveSection = objectivesSection.createSection(objective.getName());
			objectiveSection.set("criteria", objective.getCriteria());
			objectiveSection.set("display_name", objective.getDisplayName());
			objectiveSection.set("display_slot", objective.getDisplaySlot().toString().toLowerCase());
		});

		// Save scores
		ConfigurationSection scoresSection = config.createSection("scores");
		scoreboard.getEntries().forEach(playerName -> {
			ConfigurationSection playerSection = scoresSection.createSection(playerName);
			scoreboard.getScores(playerName)
					.forEach(score -> playerSection.set(score.getObjective().getName(), score.getScore()));
		});
	}

	private void saveTeam(ConfigurationSection config, Team team) {
		config.set("display_name", team.getDisplayName());
		config.set("prefix", team.getPrefix());
		config.set("suffix", team.getSuffix());
		config.set("collisions", team.getOption(Option.COLLISION_RULE).name().toLowerCase());
		config.set("death_messages", team.getOption(Option.DEATH_MESSAGE_VISIBILITY).name().toLowerCase());
		config.set("name_tags", team.getOption(Option.NAME_TAG_VISIBILITY).name().toLowerCase());
		config.set("friendly_fire", team.allowFriendlyFire());
		config.set("members", new ArrayList<>(team.getEntries()));
		config.set("color", team.getColor().name());
	}

	private ChatColor toChatColor(String string) {
		try {
			return ChatColor.valueOf(string.toUpperCase());
		} catch (IllegalArgumentException e) {
			return ChatColor.RESET;
		}
	}

	private OptionStatus toOptionStatus(String string) {
		try {
			return OptionStatus.valueOf(string.toUpperCase());
		} catch (IllegalArgumentException e) {
			return OptionStatus.ALWAYS;
		}
	}
}
