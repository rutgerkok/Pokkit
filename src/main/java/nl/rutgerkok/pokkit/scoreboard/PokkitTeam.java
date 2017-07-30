package nl.rutgerkok.pokkit.scoreboard;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

@SuppressWarnings("deprecation")
final class PokkitTeam implements Team {

	private final String name;
	private String displayName;
	private PokkitScoreboard scoreboardOrNull;
	private final Set<String> playerNames = new HashSet<>();
	private final Map<Option, OptionStatus> options = new EnumMap<>(Option.class);
	private String prefix = "";
	private String suffix = "";
	private boolean allowFriendyFire;
	private boolean canSeeFriendlyInvisibles;
	private ChatColor color = ChatColor.RESET;

	PokkitTeam(String name, PokkitScoreboard scoreboard) {
		this.name = Objects.requireNonNull(name);
		this.displayName = name;
		this.scoreboardOrNull = Objects.requireNonNull(scoreboard);
	}

	@Override
	public void addEntry(String entry) throws IllegalStateException, IllegalArgumentException {
		Validate.notNull(entry, "entry");
		PokkitScoreboard scoreboard = checkScoreboard();
		this.playerNames.add(entry.toLowerCase());
		scoreboard.markPlayerInTeam(entry, this);
	}

	@Override
	public void addPlayer(OfflinePlayer player) throws IllegalStateException, IllegalArgumentException {
		addEntry(player.getName());
	}

	@Override
	public boolean allowFriendlyFire() throws IllegalStateException {
		checkScoreboard();
		return this.allowFriendyFire;
	}

	@Override
	public boolean canSeeFriendlyInvisibles() throws IllegalStateException {
		checkScoreboard();
		return this.canSeeFriendlyInvisibles;
	}

	private PokkitScoreboard checkScoreboard() throws IllegalStateException {
		PokkitScoreboard scoreboard = this.scoreboardOrNull;
		if (scoreboard == null) {
			throw new IllegalStateException("team has been unregistered");
		}
		return scoreboard;
	}

	@Override
	public ChatColor getColor() throws IllegalStateException {
		checkScoreboard();
		return color;
	}

	@Override
	public String getDisplayName() throws IllegalStateException {
		checkScoreboard();
		return displayName;
	}

	@Override
	public Set<String> getEntries() throws IllegalStateException {
		checkScoreboard();
		return Collections.unmodifiableSet(this.playerNames);
	}

	@Override
	public String getName() throws IllegalStateException {
		checkScoreboard();
		return name;
	}

	@Override
	public NameTagVisibility getNameTagVisibility() throws IllegalArgumentException {
		OptionStatus visibility = getOption(Option.NAME_TAG_VISIBILITY);
		switch (visibility) {
		case ALWAYS:
			return NameTagVisibility.ALWAYS;
		case FOR_OTHER_TEAMS:
			return NameTagVisibility.HIDE_FOR_OTHER_TEAMS;
		case FOR_OWN_TEAM:
			return NameTagVisibility.HIDE_FOR_OWN_TEAM;
		case NEVER:
			return NameTagVisibility.NEVER;
		default:
			throw new RuntimeException("Unknown visibility: " + visibility);
		}
	}

	@Override
	public OptionStatus getOption(Option option) throws IllegalStateException {
		checkScoreboard();
		Validate.notNull(option, "option");
		return options.getOrDefault(option, OptionStatus.ALWAYS);
	}

	@Override
	public Set<OfflinePlayer> getPlayers() throws IllegalStateException {
		checkScoreboard();
		return Collections
				.unmodifiableSet(playerNames.stream().map(Bukkit::getOfflinePlayer).collect(Collectors.toSet()));
	}

	@Override
	public String getPrefix() throws IllegalStateException {
		checkScoreboard();
		return prefix;
	}

	@Override
	public Scoreboard getScoreboard() {
		return scoreboardOrNull;
	}

	@Override
	public int getSize() throws IllegalStateException {
		checkScoreboard();
		return playerNames.size();
	}

	@Override
	public String getSuffix() throws IllegalStateException {
		checkScoreboard();
		return suffix;
	}

	@Override
	public boolean hasEntry(String entry) throws IllegalArgumentException, IllegalStateException {
		Validate.notNull(entry, "entry");
		checkScoreboard();
		return playerNames.contains(entry.toLowerCase());
	}

	@Override
	public boolean hasPlayer(OfflinePlayer player) throws IllegalArgumentException, IllegalStateException {
		Validate.notNull(player, "player");
		checkScoreboard();
		return playerNames.contains(player.getName().toLowerCase());
	}

	@Override
	public boolean removeEntry(String entry) throws IllegalStateException, IllegalArgumentException {
		Validate.notNull(entry, "entry");
		checkScoreboard();
		return playerNames.remove(entry.toLowerCase());
	}

	@Override
	public boolean removePlayer(OfflinePlayer player) throws IllegalStateException, IllegalArgumentException {
		Validate.notNull(player, "player");
		checkScoreboard();
		return playerNames.remove(player.getName().toLowerCase());
	}

	@Override
	public void setAllowFriendlyFire(boolean enabled) throws IllegalStateException {
		checkScoreboard();
		this.allowFriendyFire = enabled;
	}

	@Override
	public void setCanSeeFriendlyInvisibles(boolean enabled) throws IllegalStateException {
		checkScoreboard();
		this.canSeeFriendlyInvisibles = enabled;
	}

	@Override
	public void setColor(ChatColor color) {
		Validate.notNull(color, "color");
		checkScoreboard();
		this.color = color;
	}

	@Override
	public void setDisplayName(String displayName) throws IllegalStateException, IllegalArgumentException {
		Validate.notNull(displayName, "displayName");
		checkScoreboard();
		this.displayName = displayName;
	}

	@Override
	public void setNameTagVisibility(NameTagVisibility visibility) throws IllegalArgumentException {
		Validate.notNull(visibility, "visibility");
		checkScoreboard();
		switch (visibility) {
		case ALWAYS:
			this.setOption(Option.NAME_TAG_VISIBILITY, OptionStatus.ALWAYS);
			break;
		case HIDE_FOR_OTHER_TEAMS:
			this.setOption(Option.NAME_TAG_VISIBILITY, OptionStatus.FOR_OTHER_TEAMS);
			break;
		case HIDE_FOR_OWN_TEAM:
			this.setOption(Option.NAME_TAG_VISIBILITY, OptionStatus.FOR_OWN_TEAM);
			break;
		case NEVER:
			this.setOption(Option.NAME_TAG_VISIBILITY, OptionStatus.NEVER);
			break;
		default:
			throw new RuntimeException("Unknown name tag visibility: " + visibility);
		}
	}

	@Override
	public void setOption(Option option, OptionStatus status) throws IllegalStateException {
		checkScoreboard();
		Validate.notNull(option, "option");
		Validate.notNull(status, "status");
		options.put(option, status);
	}

	@Override
	public void setPrefix(String prefix) throws IllegalStateException, IllegalArgumentException {
		Validate.notNull(prefix, "prefix");
		checkScoreboard();
		this.prefix = prefix;
	}

	@Override
	public void setSuffix(String suffix) throws IllegalStateException, IllegalArgumentException {
		Validate.notNull(suffix, "suffix");
		checkScoreboard();
		this.suffix = suffix;
	}

	@Override
	public void unregister() throws IllegalStateException {
		PokkitScoreboard scoreboard = checkScoreboard();
		scoreboard.markTeamUnregistered(this);
		this.scoreboardOrNull = null;
	}

}
