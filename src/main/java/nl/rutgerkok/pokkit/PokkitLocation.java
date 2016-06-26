package nl.rutgerkok.pokkit;

import nl.rutgerkok.pokkit.world.PokkitWorld;

import org.bukkit.Location;

import cn.nukkit.level.Position;

public class PokkitLocation {

    public static Location toBukkit(cn.nukkit.level.Location location) {
        return new Location(PokkitWorld.toBukkit(location.getLevel()), location.getX(), location.getY(),
                location.getZ(), (float) location.getYaw(), (float) location.getPitch());
    }

    public static Location toBukkit(cn.nukkit.level.Location location, Location toOverwrite) {
        toOverwrite.setWorld(PokkitWorld.toBukkit(location.getLevel()));
        toOverwrite.setX(location.getX());
        toOverwrite.setY(location.getY());
        toOverwrite.setZ(location.getZ());
        toOverwrite.setYaw((float) location.getYaw());
        toOverwrite.setPitch((float) location.getPitch());
        return toOverwrite;
    }

    public static Location toBukkit(Position position) {
        return new Location(PokkitWorld.toBukkit(position.getLevel()), position.getX(), position.getY(),
                position.getZ());
    }

    public static cn.nukkit.level.Location toNukkit(Location location) {
        return new cn.nukkit.level.Location(location.getX(), location.getY(),
                location.getZ(), location.getYaw(), location.getPitch(), PokkitWorld.toNukkit(location.getWorld()));
    }
}
