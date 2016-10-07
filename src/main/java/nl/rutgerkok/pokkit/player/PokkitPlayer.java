package nl.rutgerkok.pokkit.player;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.SplittableRandom;
import java.util.UUID;
import java.util.stream.Collectors;

import nl.rutgerkok.pokkit.Pokkit;
import nl.rutgerkok.pokkit.PokkitGameMode;
import nl.rutgerkok.pokkit.PokkitLocation;
import nl.rutgerkok.pokkit.PokkitServer;
import nl.rutgerkok.pokkit.PokkitSound;
import nl.rutgerkok.pokkit.PokkitVector;
import nl.rutgerkok.pokkit.UniqueIdConversion;
import nl.rutgerkok.pokkit.entity.PokkitEntity;
import nl.rutgerkok.pokkit.entity.PokkitHumanEntity;
import nl.rutgerkok.pokkit.inventory.PokkitInventory;
import nl.rutgerkok.pokkit.inventory.PokkitInventoryView;
import nl.rutgerkok.pokkit.inventory.PokkitPlayerInventory;
import nl.rutgerkok.pokkit.material.PokkitMaterialData;
import nl.rutgerkok.pokkit.metadata.PlayerMetadataStore;
import nl.rutgerkok.pokkit.particle.PokkitParticle;
import nl.rutgerkok.pokkit.permission.PokkitPermission;
import nl.rutgerkok.pokkit.permission.PokkitPermissionAttachment;
import nl.rutgerkok.pokkit.permission.PokkitPermissionAttachmentInfo;
import nl.rutgerkok.pokkit.plugin.PokkitPlugin;
import nl.rutgerkok.pokkit.potion.PokkitPotionEffect;
import nl.rutgerkok.pokkit.world.PokkitBlock;
import nl.rutgerkok.pokkit.world.PokkitWorld;
import nl.rutgerkok.pokkit.world.item.PokkitItemStack;

import org.apache.commons.lang.Validate;
import org.bukkit.Achievement;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.EntityEffect;
import org.bukkit.GameMode;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.Particle;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.Statistic;
import org.bukkit.WeatherType;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Block;
import org.bukkit.configuration.serialization.DelegateDeserialization;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Villager;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.InventoryView.Property;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MainHand;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.map.MapView;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.Vector;

import cn.nukkit.level.particle.GenericParticle;
import cn.nukkit.math.Vector3;
import cn.nukkit.network.protocol.UpdateBlockPacket;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;

@DelegateDeserialization(PokkitOfflinePlayer.class)
public class PokkitPlayer extends PokkitHumanEntity implements Player {

	public static final int ITEM_SLOT_NOT_INITIALIZED = -999;
	public static PokkitPlayer toBukkit(cn.nukkit.Player nukkit) {
		if (nukkit == null) {
			return null;
		}
		return ((PokkitServer) Bukkit.getServer()).getOnlinePlayerData().getPlayer(nukkit);
	}
	
	public static cn.nukkit.Player toNukkit(Player player) {
		if (player == null) {
			return null;
		}
		return ((PokkitPlayer) player).nukkit;
	}

	private final Player.Spigot spigot;

	private final cn.nukkit.Player nukkit;

	private Scoreboard scoreboard;
	private InetSocketAddress address;
	public int lastItemSlot = ITEM_SLOT_NOT_INITIALIZED;

	public PokkitPlayer(cn.nukkit.Player player) {
		super(player);
		this.nukkit = Objects.requireNonNull(player);
		this.scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
		PokkitPlayer instance = this;
		this.spigot = new Player.Spigot() {
            @Override
            public boolean getCollidesWithEntities() {
            	return nukkit.canCollide();
            }
            
            @Override
            public Set<Player> getHiddenPlayers() {
            	throw Pokkit.unsupported();
            }
            
            @Override
            public String getLocale() {
            	return nukkit.getLocale().toString();
            }
            
            @Override
            public InetSocketAddress getRawAddress() {
            	return InetSocketAddress.createUnresolved(nukkit.getAddress(), nukkit.getPort());
            }
            
            @Override
            public void playEffect(final Location location, final Effect effect, final int id, final int data, final float offsetX, final float offsetY, final float offsetZ, final float speed, final int particleCount, int radius) {
            	throw Pokkit.unsupported();
            }
            
            @Override
            public void respawn() {
            	throw Pokkit.unsupported();
            }
            
            @Override
        	public void sendMessage(BaseComponent component) {
            	instance.sendMessage(component.toLegacyText());
        	}
            
            @Override
        	public void sendMessage(BaseComponent... components) {
        		StringBuilder text = new StringBuilder();
        		for (BaseComponent component : components) {
        			text.append(component.toLegacyText());
        		}
        		instance.sendMessage(text.toString());
        	}

        	@Override
            public void sendMessage(final ChatMessageType position, final BaseComponent component) {
            	throw Pokkit.unsupported();
            }
            
            @Override
            public void sendMessage(final ChatMessageType position, final BaseComponent... components) {
            	throw Pokkit.unsupported();
            }
            
            @Override
            public void setCollidesWithEntities(final boolean collides) {
            	throw Pokkit.unsupported();
            }
        };
	}

	@Override
	public void _INVALID_damage(int arg0) {
		cn.nukkit.event.entity.EntityDamageEvent e = new cn.nukkit.event.entity.EntityDamageEvent(nukkit, cn.nukkit.event.entity.EntityDamageEvent.CAUSE_CUSTOM, arg0);
		nukkit.attack(e);
	}

	@Override
	public void _INVALID_damage(int arg0, Entity arg1) {
		cn.nukkit.event.entity.EntityDamageByEntityEvent e = new cn.nukkit.event.entity.EntityDamageByEntityEvent(nukkit, PokkitEntity.toNukkit(arg1), cn.nukkit.event.entity.EntityDamageEvent.CAUSE_CUSTOM, arg0);
		nukkit.attack(e);
	}

	@Override
	public int _INVALID_getHealth() {
		return nukkit.getHealth();
	}

	@Override
	public int _INVALID_getLastDamage() {
		throw Pokkit.unsupported();

	}

	@Override
	public int _INVALID_getMaxHealth() {
		return nukkit.getMaxHealth();
	}

	@Override
	public void _INVALID_setHealth(int arg0) {
		nukkit.setHealth(arg0);
	}

	@Override
	public void _INVALID_setLastDamage(int arg0) {
		throw Pokkit.unsupported();

	}

	@Override
	public void _INVALID_setMaxHealth(int arg0) {
		nukkit.setMaxHealth(arg0);
	}

	@Override
	public void abandonConversation(Conversation arg0) {
		throw Pokkit.unsupported();
	}

	@Override
	public void abandonConversation(Conversation arg0, ConversationAbandonedEvent arg1) {
		throw Pokkit.unsupported();
	}

	@Override
	public void acceptConversationInput(String arg0) {
		throw Pokkit.unsupported();
	}

	@Override
	public PermissionAttachment addAttachment(Plugin plugin) {
		return PokkitPermissionAttachment.toBukkit(nukkit.addAttachment(PokkitPlugin.toNukkit(plugin)));
	}

	@Override
	public PermissionAttachment addAttachment(Plugin plugin, int ticks) {
		cn.nukkit.plugin.Plugin nukkitPlugin = PokkitPlugin.toNukkit(plugin);
		cn.nukkit.permission.PermissionAttachment nukkitAttachment = nukkit.addAttachment(nukkitPlugin);
		nukkit.getServer().getScheduler().scheduleDelayedTask(() -> {
			nukkit.removeAttachment(nukkitAttachment);
		}, ticks);

		return PokkitPermissionAttachment.toBukkit(nukkitAttachment);
	}

	@Override
	public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value) {
		return PokkitPermissionAttachment.toBukkit(nukkit.addAttachment(PokkitPlugin.toNukkit(plugin), name, value));
	}

	@Override
	public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value, int ticks) {
		return PokkitPermissionAttachment.toBukkit(nukkit.addAttachment(PokkitPlugin.toNukkit(plugin), name, value));
	}

	@Override
	public boolean addPotionEffect(PotionEffect bukkitEffect) {
		return addPotionEffect(bukkitEffect, false);
	}

	@Override
	public boolean addPotionEffect(PotionEffect bukkitEffect, boolean force) {
		nukkit.addEffect(PokkitPotionEffect.toNukkit(bukkitEffect));
		return true;
	}

	@Override
	public boolean addPotionEffects(Collection<PotionEffect> bukkitEffects) {
		for (PotionEffect bukkitEffect : bukkitEffects) {
			nukkit.addEffect(PokkitPotionEffect.toNukkit(bukkitEffect));
		}
		return true;
	}

	@Override
	public void awardAchievement(Achievement arg0) {
		// Silently unsupported!
	}

	@Override
	public boolean beginConversation(Conversation arg0) {
		throw Pokkit.unsupported();
	}

	@Override
	public boolean canSee(Player arg0) {
		return nukkit.canSee(PokkitPlayer.toNukkit(arg0));
	}

	@Override
	public void chat(String arg0) {
		throw Pokkit.unsupported();
	}

	@Override
	public void closeInventory() {
		throw Pokkit.unsupported();
	}

	@Override
	public void damage(double arg0) {
		cn.nukkit.event.entity.EntityDamageEvent e = new cn.nukkit.event.entity.EntityDamageEvent(nukkit, cn.nukkit.event.entity.EntityDamageEvent.CAUSE_CUSTOM, (float) arg0);
		nukkit.attack(e);
	}

	@Override
	public void damage(double arg0, Entity arg1) {
		cn.nukkit.event.entity.EntityDamageEvent e = new cn.nukkit.event.entity.EntityDamageByEntityEvent(nukkit, PokkitEntity.toNukkit(arg1), cn.nukkit.event.entity.EntityDamageEvent.CAUSE_CUSTOM, (float) arg0);
		nukkit.attack(e);
	}

	@Override
	public void decrementStatistic(Statistic arg0) throws IllegalArgumentException {
		throw Pokkit.unsupported();

	}

	@Override
	public void decrementStatistic(Statistic arg0, EntityType arg1) throws IllegalArgumentException {
		throw Pokkit.unsupported();

	}

	@Override
	public void decrementStatistic(Statistic arg0, EntityType arg1, int arg2) {
		throw Pokkit.unsupported();

	}

	@Override
	public void decrementStatistic(Statistic arg0, int arg1) throws IllegalArgumentException {
		throw Pokkit.unsupported();

	}

	@Override
	public void decrementStatistic(Statistic arg0, Material arg1) throws IllegalArgumentException {
		throw Pokkit.unsupported();

	}

	@Override
	public void decrementStatistic(Statistic arg0, Material arg1, int arg2) throws IllegalArgumentException {
		throw Pokkit.unsupported();

	}

	@Override
	public boolean eject() {
		throw Pokkit.unsupported();

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PokkitPlayer other = (PokkitPlayer) obj;
		if (!getName().equals(other.getName()))
			return false;
		return true;
	}

	@Override
	public Collection<PotionEffect> getActivePotionEffects() {
		throw Pokkit.unsupported();

	}

	@Override
	public InetSocketAddress getAddress() {
		InetSocketAddress address = this.address;
		if (address == null || address.getAddress() == null) {
			address = new InetSocketAddress(nukkit.getAddress(), nukkit.getPort());
			this.address = address;
		}
		return address;
	}

	@Override
	public boolean getAllowFlight() {
		return nukkit.getAdventureSettings().canFly();
	}

	@Override
	public AttributeInstance getAttribute(Attribute arg0) {
		throw Pokkit.unsupported();

	}

	@Override
	public Location getBedSpawnLocation() {
		return PokkitLocation.toBukkit(nukkit.getSpawn());
	}

	@Override
	public boolean getCanPickupItems() {
		throw Pokkit.unsupported();

	}

	@Override
	public Location getCompassTarget() {
		throw Pokkit.unsupported();

	}

	@Override
	public String getCustomName() {
		return nukkit.getNameTag();
	}

	@Override
	public String getDisplayName() {
		return nukkit.getDisplayName();
	}

	@Override
	public Set<PermissionAttachmentInfo> getEffectivePermissions() {
		return nukkit.getEffectivePermissions().values().stream().map(PokkitPermissionAttachmentInfo::toBukkit)
				.collect(Collectors.toSet());
	}

	@Override
	public Inventory getEnderChest() {
		throw Pokkit.unsupported();

	}

	@Override
	public int getEntityId() {
		return (int) nukkit.getId();
	}

	@Override
	public EntityEquipment getEquipment() {
		throw Pokkit.unsupported();

	}

	@Override
	public float getExhaustion() {
		throw Pokkit.unsupported();

	}

	@Override
	public float getExp() {
		return nukkit.getExperience();

	}

	@Override
	public int getExpToLevel() {
		throw Pokkit.unsupported();

	}

	@Override
	public double getEyeHeight() {
		return this.getEyeHeight(false);
	}

	@Override
	public double getEyeHeight(boolean ignoreSneaking) {
        if (ignoreSneaking) {
            return 1.62;
        }
        if (this.isSneaking()) {
            return 1.54;
        }
        return 1.62;
	}

	@Override
	public Location getEyeLocation() {
        final Location loc = this.getLocation();
        loc.setY(loc.getY() + this.getEyeHeight());
        return loc;
	}

	@Override
	public float getFallDistance() {
		return nukkit.fallDistance;

	}

	@Override
	public int getFireTicks() {
		return nukkit.fireTicks;

	}

	@Override
	public long getFirstPlayed() {
		Long firstPlayed = nukkit.getFirstPlayed();
		if (firstPlayed == null) {
			return 0;
		}
		return firstPlayed;
	}

	@Override
	public float getFlySpeed() {
		throw Pokkit.unsupported();

	}

	@Override
	public int getFoodLevel() {
		return nukkit.getFoodData().getLevel();

	}

	@Override
	public GameMode getGameMode() {
		return PokkitGameMode.toBukkit(nukkit.getGamemode());
	}

	@Override
	public double getHealth() {
		return nukkit.getHealth();
	}

	@Override
	public double getHealthScale() {
		throw Pokkit.unsupported();

	}

	@Override
	public PlayerInventory getInventory() {
		return new PokkitPlayerInventory(nukkit.getInventory());
	}

	@Override
	public ItemStack getItemInHand() {
		return PokkitItemStack.toBukkitCopy(nukkit.getInventory().getItemInHand());
	}

	@Override
	public ItemStack getItemOnCursor() {
		throw Pokkit.unsupported();

	}

	@Override
	public Player getKiller() {
		cn.nukkit.entity.Entity entity = nukkit.getKiller();
		if (entity instanceof cn.nukkit.Player) {
			return PokkitPlayer.toBukkit((cn.nukkit.Player) entity);
		}
		return null;
	}

	@Override
	public double getLastDamage() {
		throw Pokkit.unsupported();

	}

	@Override
	public EntityDamageEvent getLastDamageCause() {
		EntityDamageEvent e = new EntityDamageEvent(PokkitEntity.toBukkit(nukkit.getLastDamageCause().getEntity()), DamageCause.ENTITY_ATTACK, nukkit.getLastDamageCause().getDamage());
		return e;
	}

	@Override
	public long getLastPlayed() {
		Long lastPlayed = nukkit.getLastPlayed();
		if (lastPlayed == null) {
			return 0;
		}
		return lastPlayed;
	}

	@Override
	public List<Block> getLastTwoTargetBlocks(HashSet<Byte> arg0, int arg1) {
		throw Pokkit.unsupported();

	}

	@Override
	public List<Block> getLastTwoTargetBlocks(Set<Material> arg0, int arg1) {
		throw Pokkit.unsupported();

	}

	@Override
	public Entity getLeashHolder() throws IllegalStateException {
		throw Pokkit.unsupported();

	}

	@Override
	public int getLevel() {
		return nukkit.getExperienceLevel();
	}

	@Override
	public List<Block> getLineOfSight(HashSet<Byte> arg0, int arg1) {
		List<Block> bukkitBlocks = new ArrayList<>();
		List<Integer> transparentBlocks = new ArrayList<>();
		
		for (byte b : arg0) {
			transparentBlocks.add((int) b);
		}
		
		cn.nukkit.block.Block[] nukkitBlocks = nukkit.getLineOfSight(arg1, 0, transparentBlocks.toArray(new Integer[transparentBlocks.size()]));
		
		for (cn.nukkit.block.Block nukkitBlock : nukkitBlocks) {
			bukkitBlocks.add(PokkitBlock.toBukkit(nukkitBlock));
		}
		
		return bukkitBlocks;
	}

	@Override
	public List<Block> getLineOfSight(Set<Material> arg0, int arg1) {
		List<Block> bukkitBlocks = new ArrayList<>();
		List<Integer> transparentBlocks = new ArrayList<>();
		
		while (arg0.iterator().hasNext()) {
			Material bukkitMaterial = arg0.iterator().next();
			transparentBlocks.add(bukkitMaterial.getId());
		}
		
		cn.nukkit.block.Block[] nukkitBlocks = nukkit.getLineOfSight(arg1, 0, transparentBlocks.toArray(new Integer[transparentBlocks.size()]));
		
		for (cn.nukkit.block.Block nukkitBlock : nukkitBlocks) {
			bukkitBlocks.add(PokkitBlock.toBukkit(nukkitBlock));
		}
		
		return bukkitBlocks;
	}

	@Override
	public Set<String> getListeningPluginChannels() {
		throw Pokkit.unsupported();

	}
	
	@Override
	public Location getLocation() {
		return PokkitLocation.toBukkit(nukkit.getLocation());
	}

	@Override
	public Location getLocation(Location toOverwrite) {
		return PokkitLocation.toBukkit(nukkit.getLocation(), toOverwrite);
	}

	@Override
	public MainHand getMainHand() {
		return MainHand.LEFT;
	}

	@Override
	public int getMaxFireTicks() {
		return nukkit.maxFireTicks;
	}

	@Override
	public double getMaxHealth() {
		return nukkit.getMaxHealth();
	}

	@Override
	public int getMaximumAir() {
		throw Pokkit.unsupported();

	}

	@Override
	public int getMaximumNoDamageTicks() {
		throw Pokkit.unsupported();

	}

	@Override
	public List<MetadataValue> getMetadata(String metadataKey) {
		return getMetadataStore().getMetadata(this, metadataKey);
	}

	private PlayerMetadataStore getMetadataStore() {
		return ((PokkitServer) Bukkit.getServer()).getMetadata().getPlayerMetadata();
	}

	@Override
	public String getName() {
		return nukkit.getName();
	}

	@Override
	public List<Entity> getNearbyEntities(double arg0, double arg1, double arg2) {
		throw Pokkit.unsupported();

	}

	@Override
	public int getNoDamageTicks() {
		return nukkit.noDamageTicks;

	}

	@Override
	public InventoryView getOpenInventory() {
		throw Pokkit.unsupported();

	}

	@Override
	public Entity getPassenger() {
		return null; // TODO: When vehicles are properly implemented in Nukkit, change this to use Nukkit's API!
	}

	@Override
	public Player getPlayer() {
		return this;
	}

	@Override
	public String getPlayerListName() {
		return nukkit.getDisplayName(); // In Nukkit, if you change the player's display name, it also changes in the player list, so...

	}

	@Override
	public long getPlayerTime() {
		throw Pokkit.unsupported();

	}

	@Override
	public long getPlayerTimeOffset() {
		throw Pokkit.unsupported();

	}

	@Override
	public WeatherType getPlayerWeather() {
		throw Pokkit.unsupported();

	}

	@Override
	public int getPortalCooldown() {
		return 80;
	}

	@Override
	public PotionEffect getPotionEffect(PotionEffectType type) {
		throw Pokkit.unsupported();
	}

	@Override
	public int getRemainingAir() {
		throw Pokkit.unsupported();

	}

	@Override
	public boolean getRemoveWhenFarAway() {
		throw Pokkit.unsupported();

	}

	@Override
	public float getSaturation() {
		return nukkit.getFoodData().getFoodSaturationLevel();

	}

	@Override
	public Scoreboard getScoreboard() {
		return scoreboard;
	}

	@Override
	public Server getServer() {
		return Bukkit.getServer();
	}

	@Override
	public int getSleepTicks() {
		throw Pokkit.unsupported();

	}

	@Override
	public Entity getSpectatorTarget() {
		throw Pokkit.unsupported();

	}

	@Override
	public int getStatistic(Statistic arg0) throws IllegalArgumentException {
		throw Pokkit.unsupported();

	}

	@Override
	public int getStatistic(Statistic arg0, EntityType arg1) throws IllegalArgumentException {
		throw Pokkit.unsupported();

	}

	@Override
	public int getStatistic(Statistic arg0, Material arg1) throws IllegalArgumentException {
		throw Pokkit.unsupported();

	}

	@Override
	public Block getTargetBlock(HashSet<Byte> arg0, int arg1) {
		List<Integer> transparentBlocks = new ArrayList<>();
		
		for (byte b : arg0) {
			transparentBlocks.add((int) b);
		}
		
		cn.nukkit.block.Block nukkitBlock = nukkit.getTargetBlock(arg1, transparentBlocks.toArray(new Integer[transparentBlocks.size()]));
				
		return PokkitBlock.toBukkit(nukkitBlock);
	}

	@Override
	public Block getTargetBlock(Set<Material> arg0, int arg1) {
		List<Integer> transparentBlocks = new ArrayList<>();
		
		while (arg0.iterator().hasNext()) {
			Material bukkitMaterial = arg0.iterator().next();
			transparentBlocks.add(bukkitMaterial.getId());
		}
		
		cn.nukkit.block.Block nukkitBlock = nukkit.getTargetBlock(arg1, transparentBlocks.toArray(new Integer[transparentBlocks.size()]));
				
		return PokkitBlock.toBukkit(nukkitBlock);
	}

	@Override
	public int getTicksLived() {
		throw Pokkit.unsupported();

	}

	@Override
	public int getTotalExperience() {
		throw Pokkit.unsupported();

	}

	@Override
	public EntityType getType() {
		return EntityType.PLAYER;
	}

	@Override
	public UUID getUniqueId() {
		return UniqueIdConversion.playerNameToId(getName());
	}

	@Override
	public Entity getVehicle() {
		throw Pokkit.unsupported();

	}

	@Override
	public Vector getVelocity() {
		return PokkitVector.toBukkit(nukkit.getMotion());
	}

	@Override
	public float getWalkSpeed() {
		return nukkit.getMovementSpeed();
	}

	@Override
	public World getWorld() {
		return PokkitWorld.toBukkit(nukkit.getLevel());
	}

	@Override
	public void giveExp(int arg0) {
		throw Pokkit.unsupported();

	}

	@Override
	public void giveExpLevels(int arg0) {
		throw Pokkit.unsupported();

	}

	@Override
	public boolean hasAchievement(Achievement arg0) {
		return true; // TODO: When achievements are properly implemented in Nukkit, change this to use Nukkit's API!
	}

	@Override
	public boolean hasAI() {
		return true; // I think it is a bit difficult a player to not have AI...
	}

	@Override
	public boolean hasGravity() {
		return true; // I think it is a bit difficult a player to not have gravity...
	}

	@Override
	public int hashCode() {
		return getName().hashCode();
	}

	@Override
	public boolean hasLineOfSight(Entity arg0) {
		return nukkit.hasLineOfSight(PokkitEntity.toNukkit(arg0));
	}

	@Override
	public boolean hasMetadata(String metadataKey) {
		return getMetadataStore().hasMetadata(this, metadataKey);
	}

	@Override
	public boolean hasPermission(Permission permission) {
		return nukkit.hasPermission(PokkitPermission.toNukkit(permission));
	}

	@Override
	public boolean hasPermission(String permission) {
		return nukkit.hasPermission(permission);
	}

	@Override
	public boolean hasPlayedBefore() {
		return nukkit.hasPlayedBefore();
	}

	@Override
	public boolean hasPotionEffect(PotionEffectType potionEffect) {
		for (cn.nukkit.potion.Effect eff : nukkit.getEffects().values()) {
			if (eff.getId() == potionEffect.getId()) { // TODO: Proper implementation of this
				return true;
			}
		}
		return false;
	}

	@Override
	public void hidePlayer(Player arg0) {
		nukkit.hidePlayer(PokkitPlayer.toNukkit(arg0));
	}

	@Override
	public void incrementStatistic(Statistic arg0) throws IllegalArgumentException {
		throw Pokkit.unsupported();

	}

	@Override
	public void incrementStatistic(Statistic arg0, EntityType arg1) throws IllegalArgumentException {
		throw Pokkit.unsupported();

	}

	@Override
	public void incrementStatistic(Statistic arg0, EntityType arg1, int arg2) throws IllegalArgumentException {
		throw Pokkit.unsupported();

	}

	@Override
	public void incrementStatistic(Statistic arg0, int arg1) throws IllegalArgumentException {
		throw Pokkit.unsupported();

	}

	@Override
	public void incrementStatistic(Statistic arg0, Material arg1) throws IllegalArgumentException {
		throw Pokkit.unsupported();

	}

	@Override
	public void incrementStatistic(Statistic arg0, Material arg1, int arg2) throws IllegalArgumentException {
		throw Pokkit.unsupported();

	}

	@Override
	public boolean isBanned() {
		return nukkit.isBanned();
	}

	@Override
	public boolean isBlocking() {
		return false;
	}

	@Override
	public boolean isCollidable() {
		return true;
	}

	@Override
	public boolean isConversing() {
		throw Pokkit.unsupported();

	}

	@Override
	public boolean isCustomNameVisible() {
		throw Pokkit.unsupported();

	}

	@Override
	public boolean isDead() {
		return nukkit.getHealth() <= 0;
	}

	@Override
	public boolean isEmpty() {
		throw Pokkit.unsupported();

	}

	@Override
	public boolean isFlying() {
		return nukkit.getAdventureSettings().canFly() && !nukkit.isOnGround();
	}

	@Override
	public boolean isGliding() {
		return false;
	}

	@Override
	public boolean isGlowing() {
		return false;
	}

	@Override
	public boolean isHandRaised() {
		return false;
	}

	@Override
	public boolean isHealthScaled() {
		throw Pokkit.unsupported();

	}

	@Override
	public boolean isInsideVehicle() {
		return false; // TODO: When vehicles are properly implemented in Nukkit, change this to use Nukkit's API!
	}

	@Override
	public boolean isInvulnerable() {
		throw Pokkit.unsupported();

	}

	@Override
	public boolean isLeashed() {
		throw Pokkit.unsupported();

	}

	@Override
	public boolean isOnGround() {
		return nukkit.isOnGround();
	}

	@Override
	public boolean isOnline() {
		return nukkit.isOnline();
	}

	@Override
	public boolean isOp() {
		return nukkit.isOp();
	}

	@Override
	public boolean isPermissionSet(Permission permission) {
		return nukkit.isPermissionSet(PokkitPermission.toNukkit(permission));
	}

	@Override
	public boolean isPermissionSet(String permission) {
		return nukkit.isPermissionSet(permission);
	}

	@Override
	public boolean isPlayerTimeRelative() {
		throw Pokkit.unsupported();

	}

	@Override
	public boolean isSilent() {
		throw Pokkit.unsupported();

	}

	@Override
	public boolean isSleeping() {
		return nukkit.isSleeping();
	}

	@Override
	public boolean isSleepingIgnored() {
		throw Pokkit.unsupported();

	}

	@Override
	public boolean isSneaking() {
		return nukkit.isSneaking();
	}

	@Override
	public boolean isSprinting() {
		return nukkit.isSprinting();
	}

	@Override
	public boolean isValid() {
		return isOnline() && !isDead();
	}

	@Override
	public boolean isWhitelisted() {
		return nukkit.isWhitelisted();
	}

	@Override
	public void kickPlayer(String reason) {
		nukkit.kick(reason);
	}

	@Override
	public <T extends Projectile> T launchProjectile(Class<? extends T> arg0) {
		throw Pokkit.unsupported();

	}

	@Override
	public <T extends Projectile> T launchProjectile(Class<? extends T> arg0, Vector arg1) {
		throw Pokkit.unsupported();

	}

	@Override
	public boolean leaveVehicle() {
		throw Pokkit.unsupported();

	}

	@Override
	public void loadData() {
		throw Pokkit.unsupported();

	}

	@Override
	public InventoryView openEnchanting(Location arg0, boolean arg1) {
		throw Pokkit.unsupported();

	}

	@Override
	public InventoryView openInventory(Inventory inventory) {
		nukkit.addWindow(PokkitInventory.toNukkit(inventory));
		return new PokkitInventoryView(inventory, this.getInventory(), this, inventory.getType());
	}

	@Override
	public void openInventory(InventoryView arg0) {
		throw Pokkit.unsupported();

	}

	@Override
	public InventoryView openMerchant(Villager arg0, boolean arg1) {
		throw Pokkit.unsupported();

	}

	@Override
	public InventoryView openWorkbench(Location arg0, boolean arg1) {
		throw Pokkit.unsupported();

	}

	@Override
	public boolean performCommand(String arg0) {
		throw Pokkit.unsupported();

	}

	@Override
	public void playEffect(EntityEffect arg0) {
		throw Pokkit.unsupported();

	}

	@Override
	public void playEffect(Location arg0, Effect arg1, int arg2) {
		throw Pokkit.unsupported();

	}

	@Override
	public <T> void playEffect(Location arg0, Effect arg1, T arg2) {
		throw Pokkit.unsupported();

	}

	@Override
	public void playNote(Location arg0, byte arg1, byte arg2) {
		throw Pokkit.unsupported();

	}

	@Override
	public void playNote(Location arg0, Instrument arg1, Note arg2) {
		throw Pokkit.unsupported();

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
		nukkit.level.addSound(nukkitSound, nukkit);
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
	public void recalculatePermissions() {
		nukkit.recalculatePermissions();
	}

	@Override
	public void remove() {
		throw Pokkit.unsupported();

	}

	@Override
	public void removeAchievement(Achievement arg0) {
		throw Pokkit.unsupported();

	}

	@Override
	public void removeAttachment(PermissionAttachment attachment) {
		nukkit.removeAttachment(PokkitPermissionAttachment.toNukkit(attachment));
	}

	@Override
	public void removeMetadata(String metadataKey, Plugin owningPlugin) {
		getMetadataStore().removeMetadata(this, metadataKey, owningPlugin);
	}

	@Override
	public void removePotionEffect(PotionEffectType arg0) {
		throw Pokkit.unsupported();

	}

	@Override
	public void resetMaxHealth() {
		throw Pokkit.unsupported();

	}

	@Override
	public void resetPlayerTime() {
		throw Pokkit.unsupported();

	}

	@Override
	public void resetPlayerWeather() {
		throw Pokkit.unsupported();

	}

	@Override
	public void resetTitle() {
		throw Pokkit.unsupported();

	}

	@Override
	public void saveData() {
		throw Pokkit.unsupported();

	}

	@Override
	@Deprecated
	public void sendBlockChange(Location location, int materialId, byte blockData) {
		Material material = Material.getMaterial(materialId);
		if (material == null) {
			return;
		}
		sendBlockChange(location, material, blockData);
	}

	@Override
	public void sendBlockChange(Location location, Material material, byte blockData) {
		int x = location.getBlockX();
		int y = location.getBlockY();
		int z = location.getBlockZ();
		PokkitMaterialData materialData = PokkitMaterialData.fromBukkit(material, blockData);
		int nukkitBlockId = materialData.getNukkitId();
		int nukkitBlockData = materialData.getNukkitDamage();
		int flags = UpdateBlockPacket.FLAG_ALL_PRIORITY;

		UpdateBlockPacket packet = new UpdateBlockPacket();
		packet.records = new UpdateBlockPacket.Entry[] {
				new UpdateBlockPacket.Entry(x, z, y, nukkitBlockId, nukkitBlockData, flags) };
		nukkit.dataPacket(packet, false);
	}

	@Override
	public boolean sendChunkChange(Location arg0, int arg1, int arg2, int arg3, byte[] arg4) {
		throw Pokkit.unsupported();

	}

	@Override
	public void sendMap(MapView arg0) {
		throw Pokkit.unsupported();

	}

	@Override
	public void sendMessage(String message) {
		nukkit.sendMessage(message);
	}

	@Override
	public void sendMessage(String[] messages) {
		for (String message : messages) {
			nukkit.sendMessage(message);
		}
	}

	@Override
	public void sendPluginMessage(Plugin arg0, String arg1, byte[] arg2) {
		throw Pokkit.unsupported();

	}

	@Override
	public void sendRawMessage(String message) {
		nukkit.sendMessage(message);
	}

	@Override
	public void sendSignChange(Location arg0, String[] arg1) throws IllegalArgumentException {
		throw Pokkit.unsupported();

	}

	@Override
	public void sendTitle(String arg0, String arg1) {
		nukkit.sendPopup(arg0, arg1);
	}

	@Override
	public Map<String, Object> serialize() {
		throw Pokkit.unsupported();

	}

	@Override
	public void setAI(boolean arg0) {
		throw Pokkit.unsupported();

	}

	@SuppressWarnings("deprecation")
	@Override
	public void setAllowFlight(boolean value) {
		nukkit.setAllowFlight(value);
	}

	@Override
	public void setBanned(boolean value) {
		nukkit.setBanned(value);
	}

	@Override
	public void setBedSpawnLocation(Location arg0) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setBedSpawnLocation(Location arg0, boolean arg1) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setCanPickupItems(boolean arg0) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setCollidable(boolean arg0) {
		throw Pokkit.unsupported();

	}
	
	@Override
	public void setCompassTarget(Location arg0) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setCustomName(String arg0) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setCustomNameVisible(boolean arg0) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setDisplayName(String displayName) {
		nukkit.setDisplayName(displayName);
	}

	@Override
	public void setExhaustion(float arg0) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setExp(float arg0) {
		nukkit.setExperience((int) arg0);

	}

	@Override
	public void setFallDistance(float arg0) {
		nukkit.fallDistance = arg0;
	}

	@Override
	public void setFireTicks(int arg0) {
		nukkit.fireTicks = arg0;
	}

	@Override
	public void setFlying(boolean arg0) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setFlySpeed(float arg0) throws IllegalArgumentException {
		throw Pokkit.unsupported();

	}

	@Override
	public void setFoodLevel(int arg0) {
		nukkit.getFoodData().setLevel(arg0);
	}

	@Override
	public void setGameMode(GameMode gamemode) {
		nukkit.setGamemode(PokkitGameMode.toNukkit(gamemode));
	}

	@Override
	public void setGliding(boolean arg0) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setGlowing(boolean arg0) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setGravity(boolean arg0) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setHealth(double arg0) {
		nukkit.setHealth((float) arg0);
	}

	@Override
	public void setHealthScale(double arg0) throws IllegalArgumentException {
		throw Pokkit.unsupported();

	}

	@Override
	public void setHealthScaled(boolean arg0) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setInvulnerable(boolean arg0) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setItemInHand(ItemStack arg0) {
		nukkit.getInventory().setItemInHand(PokkitItemStack.toNukkitCopy(arg0));
	}

	@Override
	public void setItemOnCursor(ItemStack arg0) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setLastDamage(double arg0) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setLastDamageCause(EntityDamageEvent arg0) {
		throw Pokkit.unsupported();

	}

	@Override
	public boolean setLeashHolder(Entity arg0) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setLevel(int arg0) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setMaxHealth(double maxHealth) {
		nukkit.setMaxHealth((int) Math.ceil(maxHealth));
	}

	@Override
	public void setMaximumAir(int arg0) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setMaximumNoDamageTicks(int arg0) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setMetadata(String metadataKey, MetadataValue newMetadataValue) {
		getMetadataStore().setMetadata(this, metadataKey, newMetadataValue);
	}

	@Override
	public void setNoDamageTicks(int arg0) {
		nukkit.noDamageTicks = arg0;
	}

	@Override
	public void setOp(boolean value) {
		nukkit.setOp(value);
	}

	@Override
	public boolean setPassenger(Entity arg0) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setPlayerListName(String arg0) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setPlayerTime(long arg0, boolean arg1) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setPlayerWeather(WeatherType arg0) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setPortalCooldown(int arg0) {
		return; // TODO: When portals are properly implemented in Nukkit, change this to use Nukkit's API!
	}

	@Override
	public void setRemainingAir(int arg0) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setRemoveWhenFarAway(boolean arg0) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setResourcePack(String arg0) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setSaturation(float arg0) {
		nukkit.getFoodData().setFoodSaturationLevel(arg0);
	}

	@Override
	public void setScoreboard(Scoreboard scoreboard) throws IllegalArgumentException, IllegalStateException {
		Validate.notNull(scoreboard);
		this.scoreboard = scoreboard;
	}

	@Override
	public void setSilent(boolean arg0) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setSleepingIgnored(boolean arg0) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setSneaking(boolean value) {
		nukkit.setSneaking(value);
	}

	@Override
	public void setSpectatorTarget(Entity arg0) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setSprinting(boolean value) {
		nukkit.setSprinting(value);
	}

	@Override
	public void setStatistic(Statistic arg0, EntityType arg1, int arg2) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setStatistic(Statistic arg0, int arg1) throws IllegalArgumentException {
		throw Pokkit.unsupported();

	}

	@Override
	public void setStatistic(Statistic arg0, Material arg1, int arg2) throws IllegalArgumentException {
		throw Pokkit.unsupported();

	}

	@Override
	public void setTexturePack(String arg0) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setTicksLived(int arg0) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setTotalExperience(int arg0) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setVelocity(Vector arg0) {
		nukkit.setMotion(PokkitVector.toNukkit(arg0));
	}

	@Override
	public void setWalkSpeed(float arg0) throws IllegalArgumentException {
		nukkit.setMovementSpeed(arg0);
	}

	@Override
	public void setWhitelisted(boolean value) {
		nukkit.setWhitelisted(value);
	}

	@Override
	public boolean setWindowProperty(Property arg0, int arg1) {
		throw Pokkit.unsupported();

	}

	@Override
	public void showPlayer(Player arg0) {
		nukkit.showPlayer(PokkitPlayer.toNukkit(arg0));
	}

	@Override
	public void spawnParticle(Particle particle, double x, double y, double z, int count) {
		spawnParticle(particle, x, y, z, count, 0, 0 , 0);

	}

	@Override
	public void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX, double offsetY,
			double offsetZ) {
		spawnParticle(particle, x, y, z, count, offsetX, offsetY, offsetZ, 0);

	}

	@Override
	public void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX, double offsetY,
			double offsetZ, double extra) {
		spawnParticle(particle, x, y, z, count, offsetX, offsetY, offsetZ, extra, null);
	}

	@Override
	public <T> void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX,
			double offsetY, double offsetZ, double extra, T data) {
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

			nukkit.getLevel().addParticle(nukkitParticle, nukkit);
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
		spawnParticle(particle, x, y, z, count, 0, 0, 0, 0, data);
	}

	@Override
	public void spawnParticle(Particle particle, Location location, int count) {
		spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count);
	}

	@Override
	public void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ) {
		spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ);
	}

	@Override
	public void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ,
			double extra) {
		spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ, extra);
	}

	@Override
	public <T> void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ,
			double extra, T data) {
		spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ, extra, data);
	}

	@Override
	public <T> void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ,
			T data) {
		spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ, data);
	}

	@Override
	public <T> void spawnParticle(Particle particle, Location location, int count, T data) {
		spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, data);
	}

	@Override
	public Player.Spigot spigot() {
		return spigot;
	}

	@Override
	public void stopSound(Sound sound) {
		// Silently unsupported!
	}

	@Override
	public void stopSound(String sound) {
		// Silently unsupported!
	}

	@Override
	public boolean teleport(Entity entity) {
		return teleport(entity.getLocation());
	}

	@Override
	public boolean teleport(Entity entity, TeleportCause cause) {
		return teleport(entity.getLocation(), cause);
	}

	@Override
	public boolean teleport(Location location) {
		return nukkit.teleport(PokkitLocation.toNukkit(location));
	}

	@Override
	public boolean teleport(Location location, TeleportCause cause) {
		return nukkit.teleport(PokkitLocation.toNukkit(location), PokkitTeleportCause.toNukkit(cause));
	}

	@Override
	public void updateInventory() {
		nukkit.getInventory().sendContents(nukkit);
	}

}
