package nl.rutgerkok.pokkit;

import nl.rutgerkok.pokkit.world.PokkitWorld;

import org.bukkit.Location;

import cn.nukkit.level.Position;

public class PokkitLocation {

	public static Location toBukkit(cn.nukkit.level.Position position, Location toOverwrite) {
		toOverwrite.setWorld(PokkitWorld.toBukkit(position.getLevel()));
		toOverwrite.setX(position.getX());
		toOverwrite.setY(position.getY());
		toOverwrite.setZ(position.getZ());
		if (position instanceof cn.nukkit.level.Location) {
			toOverwrite.setYaw((float) ((cn.nukkit.level.Location) position).getYaw());
			toOverwrite.setPitch((float) ((cn.nukkit.level.Location) position).getPitch());
		}
		return toOverwrite;
	}

	public static Location toBukkit(Position position) {
		if (position instanceof cn.nukkit.level.Location) {
			cn.nukkit.level.Location location = (cn.nukkit.level.Location) position;
			return new Location(PokkitWorld.toBukkit(location.getLevel()),
					location.getX(),
					location.getY(),
					location.getZ(),
					(float) location.getYaw(),
					(float) location.getPitch());
		}

		return new Location(PokkitWorld.toBukkit(position.getLevel()),
				position.getX(),
				position.getY(),
				position.getZ());
	}

	public static cn.nukkit.level.Location toNukkit(Location location) {
		return new cn.nukkit.level.Location(location.getX(), location.getY(), location.getZ(), location.getYaw(),
				location.getPitch(), PokkitWorld.toNukkit(location.getWorld()));
	}
}
