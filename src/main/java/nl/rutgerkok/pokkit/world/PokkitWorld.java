package nl.rutgerkok.pokkit.world;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import nl.rutgerkok.pokkit.Pokkit;
import nl.rutgerkok.pokkit.PokkitLocation;
import nl.rutgerkok.pokkit.PokkitServer;
import nl.rutgerkok.pokkit.PokkitSound;
import nl.rutgerkok.pokkit.UniqueIdConversion;
import nl.rutgerkok.pokkit.entity.PokkitEntity;
import nl.rutgerkok.pokkit.metadata.WorldMetadataStore;
import nl.rutgerkok.pokkit.particle.PokkitParticle;
import nl.rutgerkok.pokkit.player.PokkitPlayer;
import nl.rutgerkok.pokkit.world.item.PokkitItemStack;

import org.bukkit.BlockChangeDelegate;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.ChunkSnapshot;
import org.bukkit.Difficulty;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.WorldType;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Item;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import cn.nukkit.level.Level;
import cn.nukkit.level.format.generic.BaseFullChunk;
import cn.nukkit.level.particle.GenericParticle;
import cn.nukkit.math.Vector3;

public final class PokkitWorld implements World {

	private static final int WORLD_HEIGHT = 128;
	private static HashMap<Level, PokkitWorld> pokkitWorldCache = new HashMap<Level, PokkitWorld>();
	
	public static PokkitWorld toBukkit(Level level) {
		if (level == null) {
			return null;
		}
		// Some plugins uses World as a reference, if we do new PokkitWorld(level), the references aren't going to be the same
		// http://i.imgur.com/erAyiV8.png
		// This caches the PokkitWorld so this issue doesn't happen
		if (pokkitWorldCache.containsKey(level)) {
			return pokkitWorldCache.get(level);
		}
		PokkitWorld pokkitWorld = new PokkitWorld(level);
		pokkitWorldCache.put(level, pokkitWorld);
		return pokkitWorld;
	}

	public static Level toNukkit(World world) {
		if (world == null) {
			return null;
		}
		return ((PokkitWorld) world).nukkit;
	}

	private final Level nukkit;

	private PokkitWorld(Level nukkit) {
		this.nukkit = Objects.requireNonNull(nukkit);
	}

	@Override
	public boolean canGenerateStructures() {
		return false;
	}

	@Override
	public boolean createExplosion(double x, double y, double z, float power) {
		throw Pokkit.unsupported();

	}

	@Override
	public boolean createExplosion(double x, double y, double z, float power, boolean setFire) {
		throw Pokkit.unsupported();

	}

	@Override
	public boolean createExplosion(double x, double y, double z, float power, boolean setFire, boolean breakBlocks) {
		throw Pokkit.unsupported();

	}

	@Override
	public boolean createExplosion(Location loc, float power) {
		throw Pokkit.unsupported();

	}

	@Override
	public boolean createExplosion(Location loc, float power, boolean setFire) {
		throw Pokkit.unsupported();

	}

	@Override
	public Item dropItem(Location location, ItemStack item) {
		nukkit.dropItem(PokkitLocation.toNukkit(location), PokkitItemStack.toNukkitCopy(item), new Vector3(0, 0, 0));
		return null; // TODO add entity support
	}

	@Override
	public Item dropItemNaturally(Location location, ItemStack item) {
		nukkit.dropItem(PokkitLocation.toNukkit(location), PokkitItemStack.toNukkitCopy(item));
		return null; // TODO add entity support
	}

	@Override
	public boolean generateTree(Location location, TreeType type) {
		throw Pokkit.unsupported();

	}

	@Override
	public boolean generateTree(Location loc, TreeType type, BlockChangeDelegate delegate) {
		throw Pokkit.unsupported();

	}

	@Override
	public boolean getAllowAnimals() {
		throw Pokkit.unsupported();

	}

	@Override
	public boolean getAllowMonsters() {
		throw Pokkit.unsupported();

	}

	@Override
	public int getAmbientSpawnLimit() {
		throw Pokkit.unsupported();

	}

	@Override
	public int getAnimalSpawnLimit() {
		throw Pokkit.unsupported();

	}

	@Override
	public Biome getBiome(int x, int z) {
		throw Pokkit.unsupported();

	}

	@Override
	public PokkitBlock getBlockAt(int x, int y, int z) {
		return PokkitBlock.toBukkit(nukkit.getBlock(new Vector3(x, y, z)));
	}

	@Override
	public Block getBlockAt(Location location) {
		return getBlockAt(location.getBlockX(), location.getBlockY(), location.getBlockZ());
	}

	@Override
	@Deprecated
	public int getBlockTypeIdAt(int x, int y, int z) {
		return getBlockAt(x, y, z).getTypeId();
	}

	@Override
	@Deprecated
	public int getBlockTypeIdAt(Location location) {
		return getBlockAt(location).getTypeId();
	}

	@Override
	public Chunk getChunkAt(Block block) {
		return PokkitChunk.of(block);
	}

	@Override
	public Chunk getChunkAt(int x, int z) {
		return new PokkitChunk(this, x, z);
	}

	@Override
	public Chunk getChunkAt(Location location) {
		return PokkitChunk.of(location);

	}

	@Override
	public Difficulty getDifficulty() {
		throw Pokkit.unsupported();

	}

	@Override
	public ChunkSnapshot getEmptyChunkSnapshot(int x, int z, boolean includeBiome, boolean includeBiomeTempRain) {
		throw Pokkit.unsupported();

	}

	@Override
	public List<Entity> getEntities() {
		List<Entity> entitiesInChunk = new ArrayList<Entity>();
		
		for (cn.nukkit.entity.Entity entity : nukkit.getEntities()) {
			entitiesInChunk.add(PokkitEntity.toBukkit(entity));
		}
		return entitiesInChunk;
	}

	@Override
	public <T extends Entity> Collection<T> getEntitiesByClass(@SuppressWarnings("unchecked") Class<T>... classes) {
		throw Pokkit.unsupported();

	}

	@Override
	public <T extends Entity> Collection<T> getEntitiesByClass(Class<T> cls) {
		throw Pokkit.unsupported();

	}

	@Override
	public Collection<Entity> getEntitiesByClasses(Class<?>... classes) {
		throw Pokkit.unsupported();

	}

	@Override
	public Environment getEnvironment() {
		return Environment.NORMAL;
	}

	@Override
	public long getFullTime() {
		throw Pokkit.unsupported();

	}

	@Override
	public String[] getGameRules() {
		return new String[0];
	}

	@Override
	public String getGameRuleValue(String rule) {
		return null;
	}

	@Override
	public ChunkGenerator getGenerator() {
		throw Pokkit.unsupported();

	}

	@Override
	public Block getHighestBlockAt(int x, int z) {
		return PokkitBlock.toBukkit(nukkit.getBlock(new Vector3(x, getHighestBlockYAt(x, z), z)));
	}

	@Override
	public Block getHighestBlockAt(Location location) {
		return PokkitBlock.toBukkit(nukkit.getBlock(new Vector3(location.getX(), getHighestBlockYAt(location.getBlockX(), location.getBlockZ()), location.getZ())));
	}

	@Override
	public int getHighestBlockYAt(int x, int z) {
		return nukkit.getHighestBlockAt(x, z);
	}

	@Override
	public int getHighestBlockYAt(Location location) {
		return nukkit.getHighestBlockAt(location.getBlockX(), location.getBlockZ());
	}

	@Override
	public double getHumidity(int x, int z) {
		throw Pokkit.unsupported();

	}

	@Override
	public boolean getKeepSpawnInMemory() {
		throw Pokkit.unsupported();

	}

	@Override
	public Set<String> getListeningPluginChannels() {
		throw Pokkit.unsupported();

	}

	@Override
	public List<LivingEntity> getLivingEntities() {
		throw Pokkit.unsupported();

	}

	@Override
	public Chunk[] getLoadedChunks() {
		ArrayList<Chunk> loadedChunks = new ArrayList<>();
		
		for (BaseFullChunk chunk : nukkit.getChunks().values()) {
			if (chunk.isLoaded()) {
				loadedChunks.add(new PokkitChunk(PokkitWorld.toBukkit(nukkit), chunk.getX(), chunk.getZ()));
			}
		}

		Chunk[] array = loadedChunks.toArray(new Chunk[loadedChunks.size()]);

		return array;
	}

	@Override
	public int getMaxHeight() {
		return WORLD_HEIGHT;
	}

	@Override
	public List<MetadataValue> getMetadata(String metadataKey) {
		return getWorldMetadata().getMetadata(this, metadataKey);
	}

	@Override
	public int getMonsterSpawnLimit() {
		throw Pokkit.unsupported();

	}

	@Override
	public String getName() {
		return nukkit.getName();
	}

	@Override
	public Collection<Entity> getNearbyEntities(Location location, double x, double y, double z) {
		throw Pokkit.unsupported();

	}

	@Override
	public List<Player> getPlayers() {
		return nukkit.getPlayers().values().stream().map(PokkitPlayer::toBukkit).collect(Collectors.toList());
	}

	@Override
	public List<BlockPopulator> getPopulators() {
		throw Pokkit.unsupported();

	}

	@Override
	public boolean getPVP() {
		return nukkit.getServer().getPropertyBoolean("pvp");
	}

	@Override
	public int getSeaLevel() {
		return WORLD_HEIGHT / 2;
	}

	@Override
	public long getSeed() {
		return nukkit.getSeed();
	}

	@Override
	public Location getSpawnLocation() {
		return PokkitLocation.toBukkit(nukkit.getSpawnLocation());
	}

	@Override
	public double getTemperature(int x, int z) {
		throw Pokkit.unsupported();

	}

	@Override
	public int getThunderDuration() {
		throw Pokkit.unsupported();

	}

	@Override
	public long getTicksPerAnimalSpawns() {
		throw Pokkit.unsupported();

	}

	@Override
	public long getTicksPerMonsterSpawns() {
		throw Pokkit.unsupported();

	}

	@Override
	public long getTime() {
		throw Pokkit.unsupported();

	}

	@Override
	public UUID getUID() {
		return UniqueIdConversion.levelIndexToId(nukkit.getId());
	}

	@Override
	public int getWaterAnimalSpawnLimit() {
		throw Pokkit.unsupported();

	}

	@Override
	public int getWeatherDuration() {
		throw Pokkit.unsupported();

	}

	@Override
	public WorldBorder getWorldBorder() {
		throw Pokkit.unsupported();

	}

	@Override
	public File getWorldFolder() {
		return new File(nukkit.getServer().getDataPath() + "worlds" + File.separator + nukkit.getFolderName());
	}

	private WorldMetadataStore getWorldMetadata() {
		return ((PokkitServer) Bukkit.getServer()).getMetadata().getWorldMetadata();
	}

	@Override
	public WorldType getWorldType() {
		throw Pokkit.unsupported();

	}

	@Override
	public boolean hasMetadata(String metadataKey) {
		return getWorldMetadata().hasMetadata(this, metadataKey);
	}

	@Override
	public boolean hasStorm() {
		throw Pokkit.unsupported();

	}

	@Override
	public boolean isAutoSave() {
		throw Pokkit.unsupported();

	}

	@Override
	public boolean isChunkInUse(int x, int z) {
		return nukkit.isChunkInUse(x, z);
	}

	@Override
	public boolean isChunkLoaded(Chunk chunk) {
		return nukkit.isChunkLoaded(chunk.getX(), chunk.getZ());
	}

	@Override
	public boolean isChunkLoaded(int x, int z) {
		return nukkit.isChunkLoaded(x, z);
	}

	@Override
	public boolean isGameRule(String rule) {
		return false;
	}

	@Override
	public boolean isThundering() {
		throw Pokkit.unsupported();

	}

	@Override
	public void loadChunk(Chunk chunk) {
		nukkit.loadChunk(chunk.getX(), chunk.getZ());
	}

	@Override
	public void loadChunk(int x, int z) {
		nukkit.loadChunk(x, z);
	}

	@Override
	public boolean loadChunk(int x, int z, boolean generate) {
		return nukkit.loadChunk(x, z, true);
	}

	@Override
	public void playEffect(Location location, Effect effect, int data) {
		return; // TODO: Implement particles
	}

	@Override
	public void playEffect(Location location, Effect effect, int data, int radius) {
		return; // TODO: Implement particles
	}

	@Override
	public <T> void playEffect(Location location, Effect effect, T data) {
		return; // TODO: Implement particles
	}

	@Override
	public <T> void playEffect(Location location, Effect effect, T data, int radius) {
		return; // TODO: Implement particles

	}

	@Override
	public void playSound(Location location, Sound sound, float volume, float pitch) {
		if (location == null || sound == null) {
			return;
		}

		cn.nukkit.level.sound.Sound nukkitSound = PokkitSound.toNukkit(location, sound, pitch);
		if (nukkitSound == null) {
			return;
		}
		nukkit.addSound(nukkitSound);
	}

	@Override
	public void playSound(Location location, String soundString, float volume, float pitch) {
		if (location == null || soundString == null) {
			return;
		}
		try {
			Sound sound = Sound.valueOf(soundString.replace("minecraft:", "").toUpperCase());
			playSound(location, sound, volume, pitch);
		} catch (IllegalArgumentException e) {
			// Ignore non-existing sound
		}
	}

	@Override
	public boolean refreshChunk(int x, int z) {
		throw Pokkit.unsupported();

	}

	@Override
	public boolean regenerateChunk(int x, int z) {
		nukkit.regenerateChunk(x, z);
		return true;
	}

	@Override
	public void removeMetadata(String metadataKey, Plugin owningPlugin) {
		getWorldMetadata().removeMetadata(this, metadataKey, owningPlugin);
	}

	@Override
	public void save() {
		nukkit.save();
	}

	@Override
	public void sendPluginMessage(Plugin source, String channel, byte[] message) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setAmbientSpawnLimit(int limit) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setAnimalSpawnLimit(int limit) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setAutoSave(boolean value) {
		nukkit.setAutoSave(value);
	}

	@Override
	public void setBiome(int x, int z, Biome bio) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setDifficulty(Difficulty difficulty) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setFullTime(long time) {
		throw Pokkit.unsupported();

	}

	@Override
	public boolean setGameRuleValue(String rule, String value) {
		return false;
	}

	@Override
	public void setKeepSpawnInMemory(boolean keepLoaded) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setMetadata(String metadataKey, MetadataValue newMetadataValue) {
		getWorldMetadata().setMetadata(this, metadataKey, newMetadataValue);
	}

	@Override
	public void setMonsterSpawnLimit(int limit) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setPVP(boolean pvp) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setSpawnFlags(boolean allowMonsters, boolean allowAnimals) {
		throw Pokkit.unsupported();

	}

	@Override
	public boolean setSpawnLocation(int x, int y, int z) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setStorm(boolean hasStorm) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setThunderDuration(int duration) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setThundering(boolean thundering) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setTicksPerAnimalSpawns(int ticksPerAnimalSpawns) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setTicksPerMonsterSpawns(int ticksPerMonsterSpawns) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setTime(long time) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setWaterAnimalSpawnLimit(int limit) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setWeatherDuration(int duration) {
		throw Pokkit.unsupported();

	}

	@Override
	public <T extends Entity> T spawn(Location location, Class<T> clazz) throws IllegalArgumentException {
		throw Pokkit.unsupported();

	}

	@Override
	public Arrow spawnArrow(Location location, Vector direction, float speed, float spread) {
		throw Pokkit.unsupported();

	}

	@Override
	public <T extends Arrow> T spawnArrow(Location location, Vector direction, float speed, float spread,
			Class<T> clazz) {
		throw Pokkit.unsupported();

	}

	@Override
	public Entity spawnEntity(Location loc, EntityType type) {
		throw Pokkit.unsupported();

	}

	@Override
	public FallingBlock spawnFallingBlock(Location location, int blockId, byte blockData)
			throws IllegalArgumentException {
		throw Pokkit.unsupported();

	}

	@Override
	public FallingBlock spawnFallingBlock(Location location, Material material, byte data)
			throws IllegalArgumentException {
		throw Pokkit.unsupported();

	}

	@Override
	public void spawnParticle(Particle particle, double x, double y, double z, int count) {
		spawnParticle(particle, x, y, z, count);
	}

	@Override
	public void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX,
			double offsetY, double offsetZ) {
		spawnParticle(particle, x, y, z, count, offsetX, offsetY, offsetZ);
	}

	@Override
	public void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX,
			double offsetY, double offsetZ, double extra) {
		spawnParticle(particle, x, y, z, count, offsetX, offsetY, offsetZ, extra);
	}

	@Override
	public <T> void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX,
			double offsetY, double offsetZ, double extra, T data) {
		int id = 0;

		id = PokkitParticle.toNukkit(particle);
		
		GenericParticle nukkitParticle = new GenericParticle(new Vector3(x, y, z), id);
		
		nukkit.addParticle(nukkitParticle);
	}

	@Override
	public <T> void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX,
			double offsetY, double offsetZ, T data) {
		spawnParticle(particle, x, y, z, count, offsetX, offsetY, offsetZ, 0, data);
	}

	@Override
	public <T> void spawnParticle(Particle particle, double x, double y, double z, int count, T data) {
		spawnParticle(particle, x, y, z, count, 0, 0, 0, data);
	}

	@Override
	public void spawnParticle(Particle particle, Location location, int count) {
		spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, 0, 0, 0);
	}

	@Override
	public void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY,
			double offsetZ) {
		spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ, 0);
	}

	@Override
	public void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY,
			double offsetZ, double extra) {
		spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ, extra, null);
	}

	@Override
	public <T> void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY,
			double offsetZ, double extra, T data) {
		spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ, extra, data);

	}

	@Override
	public <T> void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY,
			double offsetZ, T data) {
		spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ, 0, data);
	}

	@Override
	public <T> void spawnParticle(Particle particle, Location location, int count, T data) {
		spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, 0, 0, 0, 0, data);
	}

	@Override
	public Spigot spigot() {
		throw Pokkit.unsupported();

	}

	@Override
	public LightningStrike strikeLightning(Location loc) {
		throw Pokkit.unsupported();

	}

	@Override
	public LightningStrike strikeLightningEffect(Location loc) {
		throw Pokkit.unsupported();

	}

	@Override
	public boolean unloadChunk(Chunk chunk) {
		return nukkit.unloadChunk(chunk.getX(), chunk.getZ());
	}

	@Override
	public boolean unloadChunk(int x, int z) {
		return nukkit.unloadChunk(x, z);
	}

	@Override
	public boolean unloadChunk(int x, int z, boolean save) {
		return nukkit.unloadChunk(x, z); // TODO: Save
	}

	@Override
	public boolean unloadChunk(int x, int z, boolean save, boolean safe) {
		return nukkit.unloadChunk(x, z, safe); // TODO: Save
	}

	@Override
	public boolean unloadChunkRequest(int x, int z) {
		return nukkit.unloadChunkRequest(x, z);

	}

	@Override
	public boolean unloadChunkRequest(int x, int z, boolean safe) {
		return nukkit.unloadChunkRequest(x, z, safe);

	}

}
