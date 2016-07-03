package nl.rutgerkok.pokkit;

import org.bukkit.GameMode;

import cn.nukkit.Player;

public final class PokkitGameMode {

    public static GameMode toBukkit(int nukkit) {
        switch (nukkit) {
            case Player.CREATIVE:
                return GameMode.CREATIVE;
            case Player.SURVIVAL:
                return GameMode.SURVIVAL;
            case Player.SPECTATOR:
                return GameMode.SPECTATOR;
            case Player.ADVENTURE:
                return GameMode.ADVENTURE;
            default:
                throw new UnsupportedOperationException("Unknown gamemode: " + nukkit);
        }
    }

    public static int toNukkit(GameMode gamemode) {
        switch (gamemode) {
            case ADVENTURE:
                return Player.ADVENTURE;
            case CREATIVE:
                return Player.CREATIVE;
            case SPECTATOR:
                return Player.SPECTATOR;
            case SURVIVAL:
                return Player.SURVIVAL;
            default:
                throw new UnsupportedOperationException("Unknown gamemode: " + gamemode);
        }
    }
}
