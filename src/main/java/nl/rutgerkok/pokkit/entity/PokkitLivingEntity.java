package nl.rutgerkok.pokkit.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class PokkitLivingEntity implements LivingEntity {
	public static PokkitLivingEntity toBukkit(cn.nukkit.entity.EntityLiving nukkit) {
		if (nukkit == null) {
			return null;
		}
		
		return new PokkitLivingEntity(nukkit);
	}

	public static cn.nukkit.entity.Entity toNukkit(Entity entity) {
		if (entity == null) {
			return null;
		}
		return ((PokkitLivingEntity) entity).nukkit;
	}

	public PokkitLivingEntity(cn.nukkit.entity.EntityLiving nukkitEntity) {
		this.nukkit = nukkitEntity;
	}
	
	private final cn.nukkit.entity.EntityLiving nukkit;
	
	@Override
	public void setMetadata(String metadataKey, MetadataValue newMetadataValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<MetadataValue> getMetadata(String metadataKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasMetadata(String metadataKey) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeMetadata(String metadataKey, Plugin owningPlugin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendMessage(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendMessage(String[] messages) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPermissionSet(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPermissionSet(Permission perm) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasPermission(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasPermission(Permission perm) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PermissionAttachment addAttachment(Plugin plugin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value, int ticks) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PermissionAttachment addAttachment(Plugin plugin, int ticks) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeAttachment(PermissionAttachment attachment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void recalculatePermissions() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<PermissionAttachmentInfo> getEffectivePermissions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isOp() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setOp(boolean value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Location getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Location getLocation(Location loc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setVelocity(Vector velocity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vector getVelocity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isOnGround() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public World getWorld() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean teleport(Location location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean teleport(Location location, TeleportCause cause) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean teleport(Entity destination) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean teleport(Entity destination, TeleportCause cause) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Entity> getNearbyEntities(double x, double y, double z) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getEntityId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getFireTicks() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxFireTicks() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setFireTicks(int ticks) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isDead() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Server getServer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entity getPassenger() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setPassenger(Entity passenger) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eject() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public float getFallDistance() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setFallDistance(float distance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLastDamageCause(EntityDamageEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EntityDamageEvent getLastDamageCause() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UUID getUniqueId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTicksLived() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setTicksLived(int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playEffect(EntityEffect type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EntityType getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isInsideVehicle() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean leaveVehicle() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Entity getVehicle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCustomName(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getCustomName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCustomNameVisible(boolean flag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isCustomNameVisible() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setGlowing(boolean flag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isGlowing() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setInvulnerable(boolean flag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isInvulnerable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSilent() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setSilent(boolean flag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasGravity() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setGravity(boolean gravity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getPortalCooldown() {
		// TODO Auto-generated method stub
		return 80;
	}

	@Override
	public void setPortalCooldown(int cooldown) {
		// TODO Auto-generated method stub
	}

	@Override
	public Spigot spigot() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AttributeInstance getAttribute(Attribute attribute) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void damage(double amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void _INVALID_damage(int amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void damage(double amount, Entity source) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void _INVALID_damage(int amount, Entity source) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getHealth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int _INVALID_getHealth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setHealth(double health) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void _INVALID_setHealth(int health) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getMaxHealth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int _INVALID_getMaxHealth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMaxHealth(double health) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void _INVALID_setMaxHealth(int health) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetMaxHealth() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T extends Projectile> T launchProjectile(Class<? extends T> projectile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Projectile> T launchProjectile(Class<? extends T> projectile, Vector velocity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getEyeHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getEyeHeight(boolean ignoreSneaking) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Location getEyeLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Block> getLineOfSight(HashSet<Byte> transparent, int maxDistance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Block> getLineOfSight(Set<Material> transparent, int maxDistance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Block getTargetBlock(HashSet<Byte> transparent, int maxDistance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Block getTargetBlock(Set<Material> transparent, int maxDistance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Block> getLastTwoTargetBlocks(HashSet<Byte> transparent, int maxDistance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Block> getLastTwoTargetBlocks(Set<Material> transparent, int maxDistance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getRemainingAir() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setRemainingAir(int ticks) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getMaximumAir() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMaximumAir(int ticks) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getMaximumNoDamageTicks() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMaximumNoDamageTicks(int ticks) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getLastDamage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int _INVALID_getLastDamage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLastDamage(double damage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void _INVALID_setLastDamage(int damage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getNoDamageTicks() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setNoDamageTicks(int ticks) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Player getKiller() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addPotionEffect(PotionEffect effect) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addPotionEffect(PotionEffect effect, boolean force) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addPotionEffects(Collection<PotionEffect> effects) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasPotionEffect(PotionEffectType type) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PotionEffect getPotionEffect(PotionEffectType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removePotionEffect(PotionEffectType type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<PotionEffect> getActivePotionEffects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasLineOfSight(Entity other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getRemoveWhenFarAway() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setRemoveWhenFarAway(boolean remove) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EntityEquipment getEquipment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCanPickupItems(boolean pickup) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getCanPickupItems() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isLeashed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Entity getLeashHolder() throws IllegalStateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setLeashHolder(Entity holder) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isGliding() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setGliding(boolean gliding) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAI(boolean ai) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasAI() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setCollidable(boolean collidable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isCollidable() {
		// TODO Auto-generated method stub
		return false;
	}
}
