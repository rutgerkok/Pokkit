package nl.rutgerkok.pokkit.player;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import nl.rutgerkok.pokkit.UniqueIdConversion;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.google.common.collect.ImmutableMap;

import cn.nukkit.IPlayer;

/**
 * Offline player implementation. This class tries to avoid creating a
 * {@link IPlayer} instance, as that instance load information from disk.
 *
 */
public final class PokkitOfflinePlayer implements OfflinePlayer {

	public static OfflinePlayer deserialize(Map<String, Object> args) {
		String name = (String) args.get("name");
		return fromName(name);
	}

	public static OfflinePlayer fromName(String name) {
		return new PokkitOfflinePlayer(name, null);
	}

	public static OfflinePlayer toBukkit(IPlayer offlinePlayer) {
		if (offlinePlayer instanceof cn.nukkit.Player) {
			// More specialized type available
			return PokkitPlayer.toBukkit((cn.nukkit.Player) offlinePlayer);
		}
		return new PokkitOfflinePlayer(offlinePlayer.getName(), offlinePlayer);
	}

	private final String name;
	private IPlayer nukkitOrNull;

	private PokkitOfflinePlayer(String name, IPlayer nukkitOrNull) {
		this.name = Objects.requireNonNull(name, "name");
		this.nukkitOrNull = nukkitOrNull;
	}

	@Override
	public Location getBedSpawnLocation() {
		return null;
	}

	@Override
	public long getFirstPlayed() {
		Long firstPlayed = getNukkit().getFirstPlayed();
		if (firstPlayed == null) {
			return 0;
		}
		return firstPlayed.longValue() * 1000;
	}

	@Override
	public long getLastPlayed() {
		Long lastPlayed = getNukkit().getLastPlayed();
		if (lastPlayed == null) {
			return 0;
		}
		return lastPlayed.longValue() * 1000;
	}

	@Override
	public String getName() {
		return name;
	}

	private IPlayer getNukkit() {
		IPlayer iPlayer = this.nukkitOrNull;
		if (iPlayer == null) {
			iPlayer = cn.nukkit.Server.getInstance().getOfflinePlayer(getName());
			this.nukkitOrNull = iPlayer;
		}
		return iPlayer;
	}

	@Override
	public Player getPlayer() {
		return Bukkit.getPlayerExact(getName());
	}

	@Override
	public UUID getUniqueId() {
		return UniqueIdConversion.playerNameToId(getName());
	}

	@Override
	public boolean hasPlayedBefore() {
		return getNukkit().hasPlayedBefore();
	}

	@Override
	public boolean isBanned() {
		return getNukkit().isBanned();
	}

	@Override
	public boolean isOnline() {
		return Bukkit.getPlayerExact(getName()) != null;
	}

	@Override
	public boolean isOp() {
		return getNukkit().isOp();
	}

	@Override
	public boolean isWhitelisted() {
		return cn.nukkit.Server.getInstance().isWhitelisted(getName());
	}

	@Override
	public Map<String, Object> serialize() {
		return ImmutableMap.of("name", getName());
	}

	@Override
	public void setOp(boolean value) {
		getNukkit().setOp(value);
	}

	@Override
	public void setWhitelisted(boolean value) {
		getNukkit().setWhitelisted(value);
	}

}
