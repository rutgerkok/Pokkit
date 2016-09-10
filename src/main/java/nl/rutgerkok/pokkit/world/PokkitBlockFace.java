package nl.rutgerkok.pokkit.world;

import org.bukkit.block.BlockFace;

public final class PokkitBlockFace {

	public static BlockFace toBukkit(int nukkit) {
		switch (nukkit) {
		case cn.nukkit.math.Vector3.SIDE_DOWN:
			return BlockFace.DOWN;
		case cn.nukkit.math.Vector3.SIDE_UP:
			return BlockFace.UP;
		case cn.nukkit.math.Vector3.SIDE_WEST:
			return BlockFace.WEST;
		case cn.nukkit.math.Vector3.SIDE_EAST:
			return BlockFace.EAST;
		case cn.nukkit.math.Vector3.SIDE_NORTH:
			return BlockFace.NORTH;
		case cn.nukkit.math.Vector3.SIDE_SOUTH:
			return BlockFace.SOUTH;
		case 255:
			return BlockFace.SELF;
		}
		throw new IllegalArgumentException("Unknown Vector3.SIDE: " + nukkit);
	}
}
