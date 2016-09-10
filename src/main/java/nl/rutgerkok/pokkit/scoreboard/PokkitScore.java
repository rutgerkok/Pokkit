package nl.rutgerkok.pokkit.scoreboard;

import java.util.Objects;
import java.util.OptionalInt;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

final class PokkitScore implements Score {

	private final PokkitObjective objective;
	private final String playerName;
	private OptionalInt score = OptionalInt.empty();

	public PokkitScore(PokkitObjective objective, String playerName) {
		this.objective = Objects.requireNonNull(objective, "objective");
		this.playerName = Objects.requireNonNull(playerName, "playerName");
	}

	private void checkObjective() throws IllegalStateException {
		if (objective.getScoreboard() == null) {
			throw new IllegalStateException("Objective has been unregistered");
		}
	}

	@Override
	public String getEntry() {
		return playerName;
	}

	@Override
	public Objective getObjective() {
		return objective;
	}

	@SuppressWarnings("deprecation")
	@Override
	public OfflinePlayer getPlayer() {
		return Bukkit.getOfflinePlayer(playerName);
	}

	@Override
	public int getScore() throws IllegalStateException {
		checkObjective();
		return score.orElse(0);
	}

	@Override
	public Scoreboard getScoreboard() {
		return objective.getScoreboard();
	}

	@Override
	public boolean isScoreSet() throws IllegalStateException {
		return score.isPresent();
	}

	@Override
	public void setScore(int score) throws IllegalStateException {
		this.score = OptionalInt.of(score);
	}

}
