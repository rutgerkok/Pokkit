package nl.rutgerkok.pokkit.scoreboard;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public final class PokkitScoreboard implements Scoreboard {

	/**
	 * Map of player names to player score maps.
	 */
	private final Map<String, Map<Objective, Score>> scoresByPlayer = new HashMap<>();
	/**
	 * Map of lowercase player names to teams.
	 */
	private final Map<String, Team> teamsByPlayer = new HashMap<>();
	/**
	 * Map of lowercase team names to teams.
	 */
	private final Map<String, Team> teamsByName = new HashMap<>();
	/**
	 * Map of display slots to objectives.
	 */
	private final Map<DisplaySlot, Objective> objectivesByDisplaySlot = new EnumMap<>(DisplaySlot.class);
	/**
	 * Map of lowercase names to objectives.
	 */
	private final Map<String, Objective> objectivesByName = new HashMap<>();

	@Override
	public void clearSlot(DisplaySlot slot) throws IllegalArgumentException {
		Validate.notNull(slot, "slot");
		this.objectivesByDisplaySlot.remove(slot);
	}

	DisplaySlot getDisplaySlot(PokkitObjective objective) {
		for (Entry<DisplaySlot, Objective> entry : this.objectivesByDisplaySlot.entrySet()) {
			if (entry.getValue() == objective) {
				return entry.getKey();
			}
		}
		return null;
	}

	@Override
	public Set<String> getEntries() {
		return Collections.unmodifiableSet(scoresByPlayer.keySet());
	}

	@Override
	public Team getEntryTeam(String entry) throws IllegalArgumentException {
		Validate.notNull(entry, "entry");
		return teamsByPlayer.get(entry.toLowerCase());
	}

	@Override
	public Objective getObjective(DisplaySlot slot) throws IllegalArgumentException {
		Validate.notNull(slot, "slot");
		return objectivesByDisplaySlot.get(slot);
	}

	@Override
	public Objective getObjective(String name) throws IllegalArgumentException {
		Validate.notNull(name, "name");
		return objectivesByName.get(name.toLowerCase());
	}

	@Override
	public Set<Objective> getObjectives() {
		return Collections.unmodifiableSet(objectivesByName.values().stream().collect(Collectors.toSet()));
	}

	@Override
	public Set<Objective> getObjectivesByCriteria(String criteria) throws IllegalArgumentException {
		Validate.notNull(criteria, "criteria");
		return Collections.unmodifiableSet(objectivesByName.values().stream()
				.filter(objective -> criteria.equalsIgnoreCase(objective.getCriteria())).collect(Collectors.toSet()));
	}

	Score getOrCreateScore(PokkitObjective objective, String entry) {
		return this.scoresByPlayer.computeIfAbsent(entry.toLowerCase(), k -> new HashMap<>()).computeIfAbsent(objective,
				k -> new PokkitScore(objective, entry));
	}

	@SuppressWarnings("deprecation")
	@Override
	public Set<OfflinePlayer> getPlayers() {
		return Collections.unmodifiableSet(
				scoresByPlayer.keySet().stream().map(Bukkit::getOfflinePlayer).collect(Collectors.toSet()));
	}

	@Override
	public Team getPlayerTeam(OfflinePlayer player) throws IllegalArgumentException {
		Validate.notNull(player, "player");
		return getEntryTeam(player.getName());
	}

	@Override
	public Set<Score> getScores(OfflinePlayer player) throws IllegalArgumentException {
		Validate.notNull(player, "player");
		return getScores(player.getName());
	}

	@Override
	public Set<Score> getScores(String entry) throws IllegalArgumentException {
		Validate.notNull(entry, "entry");
		return Collections.unmodifiableSet(scoresByPlayer.getOrDefault(entry.toLowerCase(), Collections.emptyMap())
				.values().stream().collect(Collectors.toSet()));
	}

	@Override
	public Team getTeam(String name) throws IllegalArgumentException {
		Validate.notNull(name, "name");
		return teamsByName.get(name.toLowerCase());
	}

	@Override
	public Set<Team> getTeams() {
		return Collections.unmodifiableSet(teamsByName.values().stream().collect(Collectors.toSet()));
	}

	void markObjectiveUnregistered(Objective objective) {
		this.objectivesByName.remove(objective.getName().toLowerCase());
		removeFromDisplaySlots(objective);
	}

	void markPlayerInTeam(String player, Team team) {
		teamsByPlayer.put(player.toLowerCase(), Objects.requireNonNull(team));
	}

	void markTeamUnregistered(PokkitTeam pokkitTeam) {
		this.teamsByName.remove(pokkitTeam.getName().toLowerCase());
		pokkitTeam.getEntries().forEach(playerName -> teamsByPlayer.remove(playerName.toLowerCase(), pokkitTeam));
	}

	@Override
	public Objective registerNewObjective(String name, String criteria) throws IllegalArgumentException {
		Validate.notNull(name, "name");
		Validate.notNull(criteria, "criteria");
		Objective objective = new PokkitObjective(name, criteria, this);
		this.objectivesByName.put(objective.getName().toLowerCase(), objective);
		return objective;
	}

	@Override
	public Objective registerNewObjective(String name, String criteria, String displayName)
			throws IllegalArgumentException {
		Objective objective = registerNewObjective(name, criteria);
		objective.setDisplayName(displayName);
		return objective;
	}

	@Override
	public Team registerNewTeam(String name) throws IllegalArgumentException {
		Validate.notNull(name, "name");
		PokkitTeam team = new PokkitTeam(name, this);
		if (teamsByName.putIfAbsent(name.toLowerCase(), team) != null) {
			throw new IllegalArgumentException(name + " is already registered");
		}
		return team;
	}

	/**
	 * Removes the objective from all display slots. Does nothing if the
	 * objective is not displayed.
	 *
	 * @param objective
	 *            The objective.
	 */
	void removeFromDisplaySlots(Objective objective) {
		for (Iterator<Entry<DisplaySlot, Objective>> it = objectivesByDisplaySlot.entrySet().iterator(); it
				.hasNext();) {
			Entry<DisplaySlot, Objective> entry = it.next();
			if (entry.getValue() == objective) {
				it.remove();
			}
		}
	}

	@Override
	public void resetScores(OfflinePlayer player) throws IllegalArgumentException {
		Validate.notNull(player, "player");
		this.scoresByPlayer.remove(player.getName().toLowerCase());
	}

	@Override
	public void resetScores(String entry) throws IllegalArgumentException {
		Validate.notNull(entry, "entry");
		this.scoresByPlayer.remove(entry.toLowerCase());
	}

	/**
	 * Sets the objective to be displayed at the given slot. The objective is
	 * removed from all other slots. The existing objective at the display slot
	 * is no longer displayed.
	 *
	 * @param slot
	 *            The new slot.
	 * @param objective
	 *            The objective.
	 */
	void setObjectiveAtSlot(DisplaySlot slot, Objective objective) {
		Objects.requireNonNull(slot);
		Objects.requireNonNull(objective);
		removeFromDisplaySlots(objective);
		this.objectivesByDisplaySlot.put(slot, objective);
	}

}
