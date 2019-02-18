package nl.rutgerkok.pokkit.world;

import org.bukkit.StructureType;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.SplittableRandom;
import java.util.UUID;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.ChunkSnapshot;
import org.bukkit.Difficulty;
import org.bukkit.Effect;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.WorldType;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.craftbukkit.v1_99_R9.CraftServer;
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
import org.bukkit.util.Consumer;
import org.bukkit.util.Vector;

import nl.rutgerkok.pokkit.Pokkit;
import nl.rutgerkok.pokkit.PokkitLocation;
import nl.rutgerkok.pokkit.PokkitSound;
import nl.rutgerkok.pokkit.PokkitVector;
import nl.rutgerkok.pokkit.UniqueIdConversion;
import nl.rutgerkok.pokkit.entity.PokkitEntity;
import nl.rutgerkok.pokkit.entity.PokkitEntityLightningStrike;
import nl.rutgerkok.pokkit.entity.PokkitEntityTranslator;
import nl.rutgerkok.pokkit.item.PokkitItemStack;
import nl.rutgerkok.pokkit.metadata.WorldMetadataStore;
import nl.rutgerkok.pokkit.particle.PokkitParticle;
import nl.rutgerkok.pokkit.player.PokkitPlayer;

import cn.nukkit.entity.weather.EntityLightning;
import cn.nukkit.level.Explosion;
import cn.nukkit.level.GameRules;
import cn.nukkit.level.Level;
import cn.nukkit.level.Position;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.level.format.generic.BaseFullChunk;
import cn.nukkit.level.particle.GenericParticle;
import cn.nukkit.math.Vector3;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.DoubleTag;
import cn.nukkit.nbt.tag.FloatTag;
import cn.nukkit.nbt.tag.ListTag;

public final class PokkitWorld implements World {

	private static final int WORLD_HEIGHT = 256;

	/**
	 * World cache. If Nukkit ever adds world unload support, we'll need to
	 * remove the corresponding PokkitWorld instance from here too.
	 */
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
		return this.createExplosion(null, x, y, z, power, false, true);
	}

	@Override
	public boolean createExplosion(double x, double y, double z, float power, boolean setFire) {
		return this.createExplosion(null, x, y, z, power, setFire, true);
	}

	@Override
	public boolean createExplosion(double x, double y, double z, float power, boolean setFire, boolean breakBlocks) {
		return this.createExplosion(null, x, y, z, power, setFire, breakBlocks);
	}

	private boolean createExplosion(Level level, double x, double y, double z, float power, boolean setFire, boolean breakBlocks) {
		// Base function called by all other createExplosion functions
		// Nukkit does not yet support setFire
		if (level == null) {
			level = nukkit;
		}
		Explosion explosion = new Explosion(
			new Position(x, y, z, level),
			power,
			null);
		if (breakBlocks) {
			explosion.explodeA();
		}
		explosion.explodeB();
		return true;
	}

	@Override
	public boolean createExplosion(Location loc, float power) {
		cn.nukkit.level.Location l = PokkitLocation.toNukkit(loc);
		return this.createExplosion(l.level, l.x, l.y, l.z, power, false, true);
	}

	@Override
	public boolean createExplosion(Location loc, float power, boolean setFire) {
		cn.nukkit.level.Location l = PokkitLocation.toNukkit(loc);
		return this.createExplosion(l.level, l.x, l.y, l.z, power, setFire, true);
	}

	@Override
	public Item dropItem(Location location, ItemStack item) {
		nukkit.dropItem(PokkitLocation.toNukkit(location), PokkitItemStack.toNukkitCopy(item), new Vector3(0, 0, 0));
		return null; // Add entity support
	}

	@Override
	public Item dropItemNaturally(Location location, ItemStack item) {
		nukkit.dropItem(PokkitLocation.toNukkit(location), PokkitItemStack.toNukkitCopy(item));
		return null; // Add entity support
	}

	@Override
	public boolean generateTree(Location location, TreeType type) {
		throw Pokkit.unsupported();

	}

	@Override
	@Deprecated
	public boolean generateTree(Location loc, TreeType type, org.bukkit.BlockChangeDelegate delegate) {
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
		return nukkit.getServer().getConfig().getInt("spawn-limits.ambient");
	}

	@Override
	public int getAnimalSpawnLimit() {
	    return nukkit.getServer().getConfig().getInt("spawn-limits.animals");
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
		return nukkit.getTime();
	}

	@Override
	public <T> T getGameRuleDefault(GameRule<T> rule) {
		return getGameRuleValue0(rule, GameRules.getDefault());
	}

	@Override
	public String[] getGameRules() {
		cn.nukkit.level.GameRule[] rules = this.nukkit.getGameRules().getRules();
		String[] ruleStrings = new String[rules.length];
		for (int i = 0; i < ruleStrings.length; i++) {
			ruleStrings[i] = rules[i].getName();
		}
		return ruleStrings;
	}

	@Override
	public <T> T getGameRuleValue(GameRule<T> rule) {
		return getGameRuleValue0(rule, this.nukkit.getGameRules());
	}

	@Override
	public String getGameRuleValue(String stringRule) {
		if (stringRule == null) {
			return null;
		}
		GameRule<?> rule = GameRule.getByName(stringRule);
		if (rule == null) {
			return null;
		}
		return Objects.toString(getGameRuleValue(rule));
	}

	@SuppressWarnings("unchecked")
	private <T> T getGameRuleValue0(GameRule<T> rule, GameRules source) {
		cn.nukkit.level.GameRule nukkitRule = PokkitGameRule.toNukkit(rule);
		if (nukkitRule == null) {
			if (rule.getType().equals(Integer.class)) {
				return (T) Integer.valueOf(0);
			}
			if (rule.getType().equals(Boolean.class)) {
				return (T) Boolean.FALSE;
			}
			throw new IllegalArgumentException("Unknown GameRule with unknown type: " + rule);
		}

		Class<?> type = rule.getType();
		if (type.equals(Boolean.class)) {
			return (T) Boolean.valueOf(source.getBoolean(nukkitRule));
		}
		if (type.equals(Integer.class)) {
			return (T) Integer.valueOf(source.getInteger(nukkitRule));
		}
		throw new IllegalArgumentException("GameRule with unknown type: " + rule + " of type " + type);
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
		List<LivingEntity> livingEntities = new ArrayList<>();

		for (cn.nukkit.entity.Entity entity : nukkit.getEntities()) {
			if (entity instanceof cn.nukkit.entity.EntityLiving) {
		 		livingEntities.add((LivingEntity) PokkitEntity.toBukkit(entity));
			}
		}

		return livingEntities;
	}

	@Override
	public Chunk[] getLoadedChunks() {
		ArrayList<Chunk> loadedChunks = new ArrayList<>();

		for (FullChunk chunk : nukkit.getChunks().values()) {
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
		return nukkit.getThunderTime();
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
		return this.nukkit.getTime();
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
		return nukkit.getRainTime();
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
		return ((CraftServer) Bukkit.getServer()).getMetadata().getWorldMetadata();
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
		return nukkit.isThundering();

	}

	@Override
	public boolean isAutoSave() {
		return nukkit.getAutoSave();
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
		return nukkit.isThundering();
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
		return; // Implement particles
	}

	@Override
	public void playEffect(Location location, Effect effect, int data, int radius) {
		return; // Implement particles
	}

	@Override
	public <T> void playEffect(Location location, Effect effect, T data) {
		return; // Implement particles
	}

	@Override
	public <T> void playEffect(Location location, Effect effect, T data, int radius) {
		return; // Implement particles

	}

	@Override
	public void playSound(Location location, Sound sound, float volume, float pitch) {
		if (location == null || sound == null) {
			return;
		}

		cn.nukkit.level.Sound nukkitSound = PokkitSound.toNukkit(location, sound, pitch);
		if (nukkitSound == null) {
			return;
		}
		Vector3 pos = PokkitVector.toNukkit(location.toVector());
		nukkit.addSound(pos, nukkitSound, volume, pitch);
	}

	@Override
	public void playSound(Location location, Sound sound, SoundCategory category, float volume, float pitch) {
		// Ignore the category
		playSound(location, sound, volume, pitch);
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
	public void playSound(Location location, String sound, SoundCategory category, float volume, float pitch) {
		// Ignore the category
		playSound(location, sound, volume, pitch);
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
		nukkit.setTime((int) time);
	}

	@Override
	public <T> boolean setGameRule(GameRule<T> rule, T newValue) {
		cn.nukkit.level.GameRule nukkitRule = PokkitGameRule.toNukkit(rule);
		if (nukkitRule == null) {
			return false;
		}
		if (newValue instanceof Boolean) {
			nukkit.getGameRules().setGameRule(nukkitRule, ((Boolean)newValue).booleanValue());
			return true;
		}
		if (newValue instanceof Integer) {
			nukkit.getGameRules().setGameRule(nukkitRule, ((Integer)newValue).intValue());
			return true;
		}
		return false;
	}

	@Override
	public boolean setGameRuleValue(String rule, String value) {
		cn.nukkit.level.GameRule gameRule = cn.nukkit.level.GameRule.parseString(rule).orElse(null);
		if (gameRule == null) {
			return false;
		}
		try {
			nukkit.getGameRules().setGameRules(gameRule, value);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
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
		nukkit.setSpawnLocation(new Vector3(x, y, z));
		return true;
	}

	@Override
	public boolean setSpawnLocation(Location location) {
		if (!location.getWorld().equals(this)) {
			return false;
		}
		nukkit.setSpawnLocation(PokkitLocation.toNukkit(location));
		return true;
	}

	@Override
	public void setStorm(boolean hasStorm) {
		nukkit.setThundering(hasStorm);
	}

	@Override
	public void setThunderDuration(int duration) {
		nukkit.setThunderTime(duration);
	}

	@Override
	public void setThundering(boolean thundering) {
		nukkit.setThundering(thundering);
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
		nukkit.setTime((int)time);
	}

	@Override
	public void setWaterAnimalSpawnLimit(int limit) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setWeatherDuration(int duration) {
		nukkit.setRainTime(duration);
	}

	@Override
	public <T extends Entity> T spawn(Location location, Class<T> clazz) throws IllegalArgumentException {
		throw Pokkit.unsupported();

	}

	@Override
	public <T extends Entity> T spawn(Location location, Class<T> clazz, Consumer<T> consumer) throws IllegalArgumentException {
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
		try {
			BaseFullChunk chunk = nukkit.getChunk(loc.getChunk().getX(), loc.getChunk().getZ());
			CompoundTag nbt = new CompoundTag();
			nbt.putList(new ListTag<DoubleTag>("Pos").add(new DoubleTag("", loc.getX() + 0.5))
                    .add(new DoubleTag("", loc.getY())).add(new DoubleTag("", loc.getZ() + 0.5)))
            .putList(new ListTag<DoubleTag>("Motion").add(new DoubleTag("", 0))
                    .add(new DoubleTag("", 0)).add(new DoubleTag("", 0)))
            .putList(new ListTag<FloatTag>("Rotation").add(new FloatTag("", 0))
                    .add(new FloatTag("", 0)));
			cn.nukkit.entity.Entity ent = cn.nukkit.entity.Entity.createEntity(PokkitEntityTranslator.getEntity(type), chunk, nbt);
			ent.spawnToAll();
			return PokkitEntity.toBukkit(ent);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public FallingBlock spawnFallingBlock(Location location, BlockData data) throws IllegalArgumentException {
		throw Pokkit.unsupported();
	}

	@Override
	public FallingBlock spawnFallingBlock(Location location, Material material, byte data)
			throws IllegalArgumentException {
		throw Pokkit.unsupported();
	}

	@Override
	public FallingBlock spawnFallingBlock(Location location,
			@SuppressWarnings("deprecation") org.bukkit.material.MaterialData materialData)
			throws IllegalArgumentException {
		throw Pokkit.unsupported();
	}

	@Override
	public void spawnParticle(Particle particle, double x, double y, double z, int count) {
		spawnParticle(particle, x, y, z, count, 0, 0, 0);
	}

	@Override
	public void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX,
			double offsetY, double offsetZ) {
		spawnParticle(particle, x, y, z, count, offsetX, offsetY, offsetZ, 0);
	}

	@Override
	public void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX,
			double offsetY, double offsetZ, double extra) {
		spawnParticle(particle, x, y, z, count, offsetX, offsetY, offsetZ, extra, null);
	}

	@Override
	public <T> void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX,
			double offsetY, double offsetZ, double extra, T data) {
		spawnParticle(particle, x, y, z, count, offsetX, offsetY, offsetZ, extra, data, false);
	}

	@Override
	public <T> void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX,
			double offsetY, double offsetZ, double extra, T data, boolean force) {
		int id = 0;

		id = PokkitParticle.toNukkit(particle);

		SplittableRandom random = new SplittableRandom();

		int index = 0;
		while (count > index) {
			double realOffsetX = 0;
			double realOffsetY = 0;
			double realOffsetZ = 0;
			if (offsetX != 0) {
				realOffsetX = offsetX / 2;
				x = x + random.nextDouble(-realOffsetX, realOffsetX);
			}
			if (offsetX != 0) {
				realOffsetY = offsetY / 2;
				y = y + random.nextDouble(-realOffsetY, realOffsetY);
			}
			if (offsetX != 0) {
				realOffsetZ = offsetZ / 2;
				z = z + random.nextDouble(-realOffsetZ, realOffsetZ);
			}

			GenericParticle nukkitParticle = new GenericParticle(new Vector3(x, y, z), id);

			nukkit.addParticle(nukkitParticle);
			index++;
		}
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
		spawnParticle(particle, location, count, 0, 0, 0);
	}

	@Override
	public void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY,
			double offsetZ) {
		spawnParticle(particle, location, count, offsetX, offsetY, offsetZ, 0);
	}

	@Override
	public void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY,
			double offsetZ, double extra) {
		spawnParticle(particle, location, count, offsetX, offsetY, offsetZ, extra, null);
	}

	@Override
	public <T> void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY,
			double offsetZ, double extra, T data) {
		spawnParticle(particle, location, count, offsetX, offsetY, offsetZ, extra, data, false);

	}

	@Override
	public <T> void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY,
			double offsetZ, double extra, T data, boolean force) {
		spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ,
				extra, data, force);

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

	public LightningStrike strike(Location loc, boolean effect) {
		BaseFullChunk chunk = nukkit.getChunk(loc.getChunk().getX(), loc.getChunk().getZ());
        int x = loc.getBlockX();
        int z = loc.getBlockZ();
        int y = loc.getBlockY();
        int bId = nukkit.getBlockIdAt(x, y, z);
        if (bId != cn.nukkit.block.Block.TALL_GRASS && bId != cn.nukkit.block.Block.WATER)
            y += 1;
        CompoundTag nbt = new CompoundTag()
                .putList(new ListTag<DoubleTag>("Pos").add(new DoubleTag("", x))
                        .add(new DoubleTag("", y)).add(new DoubleTag("", z)))
                .putList(new ListTag<DoubleTag>("Motion").add(new DoubleTag("", 0))
                        .add(new DoubleTag("", 0)).add(new DoubleTag("", 0)))
                .putList(new ListTag<FloatTag>("Rotation").add(new FloatTag("", 0))
                        .add(new FloatTag("", 0)));

        EntityLightning bolt = new EntityLightning(chunk, nbt);
        bolt.setEffect(effect);
        bolt.spawnToAll();
        return PokkitEntityLightningStrike.toBukkit(bolt);
	}

	@Override
	public LightningStrike strikeLightning(Location loc) {
		return strike(loc, true);
	}

	@Override
	public LightningStrike strikeLightningEffect(Location loc) {
		return strike(loc, false);
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
		return nukkit.unloadChunk(x, z, true, save);
	}

	@Override
	public boolean unloadChunk(int x, int z, boolean save, boolean safe) {
		return nukkit.unloadChunk(x, z, safe, save);
	}

	@Override
	public boolean unloadChunkRequest(int x, int z) {
		return nukkit.unloadChunkRequest(x, z);

	}

	@Override
	public boolean unloadChunkRequest(int x, int z, boolean safe) {
		return nukkit.unloadChunkRequest(x, z, safe);

	}

	@Override
	public Location locateNearestStructure(Location location, StructureType type, int i, boolean b) {
		throw Pokkit.unsupported();
	}

	@Override
	public boolean isChunkGenerated(int x, int z) {
		return nukkit.isChunkGenerated(x, z);
	}
}
