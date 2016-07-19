package nl.rutgerkok.pokkit.player;

import org.bukkit.entity.HumanEntity;

import nl.rutgerkok.pokkit.Pokkit;

public final class PokkitHumanEntity {

    public static HumanEntity toBukkit(cn.nukkit.entity.EntityHuman human) {
        if (human == null) {
            return null;
        }
        if (human instanceof cn.nukkit.Player) {
            return PokkitPlayer.toBukkit((cn.nukkit.Player) human);
        }
        throw Pokkit.unsupported();
    }
}
