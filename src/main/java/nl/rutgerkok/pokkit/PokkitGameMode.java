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
}
