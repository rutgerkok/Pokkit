package nl.rutgerkok.pokkit.world;

import org.bukkit.block.BlockFace;

public final class PokkitBlockFace {

	public static BlockFace toBukkit(cn.nukkit.math.BlockFace nukkit) {
		// TODO Direct mapping via BlockFace.valueOf?
		switch (nukkit) {
		case DOWN:
			return BlockFace.DOWN;
		case UP:
			return BlockFace.UP;
		case WEST:
			return BlockFace.WEST;
		case EAST:
			return BlockFace.EAST;
		case NORTH:
			return BlockFace.NORTH;
		case SOUTH:
			return BlockFace.SOUTH;
		}
		throw new IllegalArgumentException("Unknown BlockFace.SIDE: " + nukkit);
	}
}
