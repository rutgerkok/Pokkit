package nl.rutgerkok.pokkit.scoreboard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.junit.Test;

public class ScoreboardTest {

    @Test
    public void testScores() {
        Scoreboard scoreboard = new PokkitScoreboard();

        Objective objective = scoreboard.registerNewObjective("TestObjective", "lives");

        assertFalse(objective.getScore("TestPlayer").isScoreSet());
        assertEquals(0, objective.getScore("TestPlayer").getScore());

        objective.getScore("TestPlayer").setScore(10);

        assertTrue(objective.getScore("TestPlayer").isScoreSet());
        assertEquals(10, objective.getScore("TestPlayer").getScore());
    }

    @Test
    public void testTeamPlayers() {
        Scoreboard scoreboard = new PokkitScoreboard();

        Team team = scoreboard.registerNewTeam("MyTeam");
        assertEquals(0, team.getSize());

        team.addEntry("Foo");
        assertEquals(1, team.getSize());

        team.addEntry("foo"); // this player is already in the team
        assertEquals(1, team.getSize());
    }

    @Test
    public void testTeams() {
        Scoreboard scoreboard = new PokkitScoreboard();

        // Register team
        Team team = scoreboard.registerNewTeam("MyTeam");
        assertEquals(team, scoreboard.getTeam("MyTeam"));

        // Unregister team
        team.unregister();
        assertNull(scoreboard.getTeam("MyTeam"));
    }
}
