package nl.rutgerkok.pokkit.world;


import java.util.Objects;

import org.bukkit.Chunk;
import org.bukkit.ChunkSnapshot;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Entity;

import nl.rutgerkok.pokkit.Pokkit;

public final class PokkitChunk implements Chunk {

    private static final int CHUNK_SIZE = 16;

    public static Chunk of(Block block) {
        return new PokkitChunk(block.getWorld(), block.getX() >> 4, block.getZ() >> 4);
    }

    public static Chunk of(Location location) {
        return new PokkitChunk(location.getWorld(), location.getBlockX() >> 4, location.getBlockZ() >> 4);
    }

    private final World world;
    private final int chunkX;
    private final int chunkZ;

    PokkitChunk(World world, int chunkX, int chunkZ) {
        this.world = Objects.requireNonNull(world);
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
    }

    @Override
    public Block getBlock(int x, int y, int z) {
        return world.getBlockAt((x & 0xf) + chunkX * CHUNK_SIZE, y, (z & 0xf) + chunkZ * CHUNK_SIZE);
    }

    @Override
    public ChunkSnapshot getChunkSnapshot() {
        return getChunkSnapshot(true, true, true);
    }

    @Override
    public ChunkSnapshot getChunkSnapshot(boolean includeMaxblocky, boolean includeBiome,
            boolean includeBiomeTempRain) {
        throw Pokkit.unsupported();

    }

    @Override
    public Entity[] getEntities() {
        throw Pokkit.unsupported();

    }

    @Override
    public BlockState[] getTileEntities() {
        throw Pokkit.unsupported();

    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public int getX() {
        return chunkX;
    }

    @Override
    public int getZ() {
        return chunkZ;
    }

    @Override
    public boolean isLoaded() {
        return world.isChunkLoaded(chunkX, chunkZ);
    }

    @Override
    public boolean load() {
        return world.loadChunk(chunkX, chunkZ, true);
    }

    @Override
    public boolean load(boolean generate) {
        return world.loadChunk(chunkX, chunkZ, generate);
    }

    @Override
    public boolean unload() {
        return world.unloadChunk(this);
    }

    @Override
    public boolean unload(boolean save) {
        return world.unloadChunk(chunkX, chunkZ, save);
    }

    @Override
    @Deprecated
    public boolean unload(boolean save, boolean safe) {
        return world.unloadChunk(chunkX, chunkZ, save, safe);
    }

}
