package nl.rutgerkok.pokkit.scoreboard;

import java.util.Objects;

import org.apache.commons.lang.Validate;
import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

final class PokkitObjective implements Objective {

    private final String name;
    private String displayName;
    private PokkitScoreboard scoreboardOrNull;
    private final String criteria;

    PokkitObjective(String name, String criteria, PokkitScoreboard scoreboard) {
        this.name = Objects.requireNonNull(name);
        this.displayName = name;
        this.scoreboardOrNull = Objects.requireNonNull(scoreboard);
        this.criteria = Objects.requireNonNull(criteria);
    }

    private PokkitScoreboard checkScoreboard() throws IllegalStateException {
        PokkitScoreboard scoreboard = this.scoreboardOrNull;
        if (scoreboard == null) {
            throw new IllegalStateException("team has been unregistered");
        }
        return scoreboard;
    }

    @Override
    public String getCriteria() throws IllegalStateException {
        checkScoreboard();
        return this.criteria;
    }

    @Override
    public String getDisplayName() throws IllegalStateException {
        checkScoreboard();
        return displayName;
    }

    @Override
    public DisplaySlot getDisplaySlot() throws IllegalStateException {
        return checkScoreboard().getDisplaySlot(this);
    }

    @Override
    public String getName() throws IllegalStateException {
        checkScoreboard();
        return name;
    }

    @Override
    public Score getScore(OfflinePlayer player) throws IllegalArgumentException, IllegalStateException {
        Validate.notNull(player, "player");
        return getScore(player.getName());
    }

    @Override
    public Score getScore(String entry) throws IllegalArgumentException, IllegalStateException {
        Validate.notNull(entry, "entry");
        PokkitScoreboard scoreboard = checkScoreboard();
        return scoreboard.getOrCreateScore(this, entry);
    }

    @Override
    public Scoreboard getScoreboard() {
        return scoreboardOrNull;
    }

    @Override
    public boolean isModifiable() throws IllegalStateException {
        checkScoreboard();
        return true;
    }

    @Override
    public void setDisplayName(String displayName) throws IllegalStateException, IllegalArgumentException {
        Validate.notNull(displayName, "displayName");
        checkScoreboard();
        this.displayName = displayName;
    }

    @Override
    public void setDisplaySlot(DisplaySlot slot) throws IllegalStateException {
        checkScoreboard().setObjectiveAtSlot(slot, this);
    }

    @Override
    public void unregister() throws IllegalStateException {
        PokkitScoreboard scoreboard = checkScoreboard();
        scoreboard.markObjectiveUnregistered(this);
    }

}
