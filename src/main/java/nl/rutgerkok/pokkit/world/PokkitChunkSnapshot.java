package nl.rutgerkok.pokkit.world;

import org.bukkit.ChunkSnapshot;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.block.data.BlockData;

import nl.rutgerkok.pokkit.Pokkit;
import nl.rutgerkok.pokkit.blockdata.PokkitBlockData;

import cn.nukkit.block.Block;
import cn.nukkit.level.format.generic.BaseFullChunk;

public class PokkitChunkSnapshot implements ChunkSnapshot {
    private final int x;
    private final int z;
    private final String worldname;
    // X, Y, Z
	private PokkitBlockData[][][] blocksIds = new PokkitBlockData[16][256][16];

	BaseFullChunk nukkit;

	public PokkitChunkSnapshot(final int x, final int z, final String wname, BaseFullChunk baseFullChunk) {
		this.nukkit = baseFullChunk;
		this.x = x;
		this.z = z;
		this.worldname = wname;

		// Ouch, this isn't the best way to implement this... right? TODO: Improve this
		// Yeah, Chunk Snapshot is a chunk... "snapshot", so we need to clone the chunk
		for (int blockX = 0; blockX < 16; blockX++) {
			for (int blockY = 0; blockY < 255; blockY++) {
				for (int blockZ = 0; blockZ < 16; blockZ++) {
					PokkitBlockData data = PokkitBlockData
							.toBukkit(Block.get(baseFullChunk.getBlockId(blockX, blockY, blockZ),
									baseFullChunk.getBlockData(blockX, blockY, blockZ)));
					blocksIds[blockX][blockY][blockZ] = data;
				}
			}
		}
	}

	@Override
	public Biome getBiome(int x, int z) {
		return Biome.PLAINS;
	}

	@Override
	public BlockData getBlockData(int x, int y, int z) {
		return blocksIds[x][y][z];
	}

	@Override
	public int getBlockEmittedLight(int x, int y, int z) {
		throw Pokkit.unsupported();
	}

	@Override
	public int getBlockSkyLight(int x, int y, int z) {
		return nukkit.getBlockSkyLight(x, y, z);
	}

	@Override
	public Material getBlockType(int x, int y, int z) {
		return blocksIds[x][y][z].getMaterial();
	}

	@Override
	public long getCaptureFullTime() {
		throw Pokkit.unsupported();

	}

	@Override
	public int getData(int x, int y, int z) {
		return blocksIds[x][y][z].getNukkitData();
	}

	@Override
	public int getHighestBlockYAt(int x, int z) {
		return nukkit.getHighestBlockAt(x, z);
	}

	@Override
	public double getRawBiomeTemperature(int x, int z) {
		throw Pokkit.unsupported();

	}

	@Override
	public String getWorldName() {
		return worldname;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getZ() {
		return z;
	}

	@Override
	public boolean isSectionEmpty(int sy) {
		return true; // Always true due to reasons
	}
}
