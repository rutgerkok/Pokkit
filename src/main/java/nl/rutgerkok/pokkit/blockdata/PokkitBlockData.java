package nl.rutgerkok.pokkit.blockdata;

import java.util.Objects;

import org.bukkit.Material;
import org.bukkit.block.data.BlockData;

import nl.rutgerkok.pokkit.Pokkit;

import cn.nukkit.block.Block;

/**
 * Nukkit does not have block states yet, so implementing this class is a bit
 * difficult.
 *
 */
public class PokkitBlockData implements BlockData {

	public static PokkitBlockData createBlockData(Material material, int blockData) {
		return new PokkitBlockData(BlockMap.getNukkitBlock(material, blockData));
	}

	/**
	 * Uses a simple "blockId:blockData" format.
	 *
	 * @param inputString
	 *            The input string.
	 * @return The parsed block data.
	 * @throws IllegalArgumentException
	 *             If no such material exists, or the string is in an invalid
	 *             format.
	 */
	public static BlockData createBlockData(String inputString) {
		String[] parts = inputString.split(":", 2);
		try {
			int blockId = Integer.parseUnsignedInt(parts[0]);
			int blockData = Integer.parseUnsignedInt(parts[1]);
			if (blockData >= 16) {
				throw new IllegalArgumentException("Invalid block data: " + blockData);
			}
			return new PokkitBlockData(Block.get(blockId, blockData));
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(Pokkit.NAME + " expects \"blockId:blockData\", got \"" + inputString + "\".");
		}
	}

	public static PokkitBlockData toBukkit(cn.nukkit.block.Block block) {
		return new PokkitBlockData(block);
	}

	public static cn.nukkit.block.Block toNukkit(BlockData blockData) {
		return ((PokkitBlockData) blockData).block;
	}

	private final cn.nukkit.block.Block block;

	protected PokkitBlockData(Block block) {
		this.block = Objects.requireNonNull(block);
	}

	@Override
	public BlockData clone() {
		return new PokkitBlockData(block.clone());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		PokkitBlockData other = (PokkitBlockData) obj;
		if (!block.equals(other.block)) {
			return false;
		}
		return true;
	}

	@Override
	public String getAsString() {
		return block.getId() + ":" + block.getDamage();
	}

	@Override
	public Material getMaterial() {
		return BlockMap.getMaterial(block.getId());
	}

	public byte getNukkitData() {
		return (byte) block.getDamage();
	}

	/**
	 * Gets the block id according to the MCPE system.
	 * @return The block id.
	 */
	public int getNukkitId() {
		return block.getId();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((block == null) ? 0 : block.hashCode());
		return result;
	}

	@Override
	public boolean matches(BlockData data) {
		return block.getFullId() == ((PokkitBlockData) data).block.getFullId();
	}

	@Override
	public BlockData merge(BlockData data) {
		// Merging not supported
		return data.clone();
	}

	/**
	 * Bridge to old API.
	 *
	 * @return Equivalent object in the old API.
	 */
	@Deprecated
	public org.bukkit.material.MaterialData toMaterialData() {
		Material material = BlockMap.getMaterial(block.getId());
		return material.getNewData((byte) block.getDamage());
	}
}
