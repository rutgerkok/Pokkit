package nl.rutgerkok.pokkit.world;

import org.bukkit.ChunkSnapshot;
import org.bukkit.block.Biome;

import cn.nukkit.level.format.generic.BaseFullChunk;
import nl.rutgerkok.pokkit.Pokkit;

public class PokkitChunkSnapshot implements ChunkSnapshot {
    private final int x;
    private final int z;
    private final String worldname;
    // X, Y, Z
    private int[][][] blocksIds = new int[16][128][16];
    private int[][][] blocksData = new int[16][128][16];
    
	public PokkitChunkSnapshot(final int x, final int z, final String wname, BaseFullChunk baseFullChunk) {
		this.nukkit = baseFullChunk;
		this.x = x;
		this.z = z;
		this.worldname = wname;

		// Ouch, this isn't the best way to implement this... right? TODO: Improve this
		// Yeah, Chunk Snapshot is a chunk... "snapshot", so we need to clone the chunk
		for (int blockX = 0; blockX < 16; blockX++) {
			for (int blockY = 0; blockY < 127; blockY++) {
				for (int blockZ = 0; blockZ < 16; blockZ++) {
					blocksIds[blockX][blockY][blockZ] = baseFullChunk.getBlockId(blockX, blockY, blockZ);
					blocksData[blockX][blockY][blockZ] = baseFullChunk.getBlockData(blockX, blockY, blockZ);
				}
			}
		}
	}
	
	BaseFullChunk nukkit;
	
	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getZ() {
		return z;
	}

	@Override
	public String getWorldName() {
		return worldname;
	}

	@Override
	public int getBlockTypeId(int x, int y, int z) {
		return blocksIds[x][y][z];
	}

	@Override
	public int getBlockData(int x, int y, int z) {
		return blocksData[x][y][z];
	}

	@Override
	public int getBlockSkyLight(int x, int y, int z) {
		return nukkit.getBlockSkyLight(x, y, z);
	}

	@Override
	public int getBlockEmittedLight(int x, int y, int z) {
		throw Pokkit.unsupported();
	}

	@Override
	public int getHighestBlockYAt(int x, int z) {
		return nukkit.getHighestBlockAt(x, z);
	}

	@Override
	public Biome getBiome(int x, int z) {
		throw Pokkit.unsupported();
		
	}

	@Override
	public double getRawBiomeTemperature(int x, int z) {
		throw Pokkit.unsupported();
		
	}

	@Override
	public double getRawBiomeRainfall(int x, int z) {
		throw Pokkit.unsupported();
		
	}

	@Override
	public long getCaptureFullTime() {
		throw Pokkit.unsupported();
		
	}

	@Override
	public boolean isSectionEmpty(int sy) {
		return true; // Always true due to reasons
	}
}
