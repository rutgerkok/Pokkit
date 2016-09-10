package nl.rutgerkok.pokkit.scoreboard;

import java.util.Map;
import java.util.WeakHashMap;

import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public final class PokkitScoreboardManager implements ScoreboardManager {

	private final Map<Scoreboard, Boolean> activeBoards = new WeakHashMap<>();

	private final Scoreboard mainBoard;

	public PokkitScoreboardManager() {
		this.mainBoard = getNewScoreboard();
	}

	@Override
	public Scoreboard getMainScoreboard() {
		return mainBoard;
	}

	@Override
	public Scoreboard getNewScoreboard() {
		Scoreboard scoreboard = new PokkitScoreboard();
		activeBoards.put(scoreboard, Boolean.TRUE);
		return scoreboard;
	}

}
