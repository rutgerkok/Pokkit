package nl.rutgerkok.pokkit;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.google.common.collect.ImmutableMap;

import cn.nukkit.IPlayer;

final class PokkitOfflinePlayer implements OfflinePlayer {

    public static OfflinePlayer deserialize(Map<String, Object> args) {
        String name = (String) args.get("name");
        @SuppressWarnings("deprecation")
        OfflinePlayer player = Bukkit.getServer().getOfflinePlayer(name);
        return player;
    }

    static OfflinePlayer toBukkit(IPlayer offlinePlayer) {
        if (offlinePlayer instanceof cn.nukkit.Player) {
            // More specialized type available
            return PokkitPlayer.toBukkit((cn.nukkit.Player) offlinePlayer);
        }
        return new PokkitOfflinePlayer(offlinePlayer);
    }

    private final cn.nukkit.IPlayer nukkit;

    public PokkitOfflinePlayer(IPlayer nukkit) {
        this.nukkit = Objects.requireNonNull(nukkit, "nukkit");
    }

    @Override
    public Location getBedSpawnLocation() {
        return null;
    }

    @Override
    public long getFirstPlayed() {
        Long firstPlayed = nukkit.getFirstPlayed();
        if (firstPlayed == null) {
            return 0;
        }
        return firstPlayed.longValue();
    }

    @Override
    public long getLastPlayed() {
        Long lastPlayed = nukkit.getFirstPlayed();
        if (lastPlayed == null) {
            return 0;
        }
        return lastPlayed.longValue();
    }

    @Override
    public String getName() {
        return nukkit.getName();
    }

    @Override
    public Player getPlayer() {
        return PokkitPlayer.toBukkit(nukkit.getPlayer());
    }

    @Override
    public UUID getUniqueId() {
        return PlayerUniqueId.nameToId(getName());
    }

    @Override
    public boolean hasPlayedBefore() {
        return nukkit.hasPlayedBefore();
    }

    @Override
    public boolean isBanned() {
        return nukkit.isBanned();
    }

    @Override
    public boolean isOnline() {
        return nukkit.isOnline();
    }

    @Override
    public boolean isOp() {
        return nukkit.isOp();
    }

    @Override
    public boolean isWhitelisted() {
        return nukkit.isWhitelisted();
    }

    @Override
    public Map<String, Object> serialize() {
        return ImmutableMap.of("name", getName());
    }
    @Override
    public void setBanned(boolean banned) {
        nukkit.setBanned(banned);
    }

    @Override
    public void setOp(boolean value) {
        nukkit.setOp(value);
    }

    @Override
    public void setWhitelisted(boolean value) {
        nukkit.setWhitelisted(value);
    }

}
