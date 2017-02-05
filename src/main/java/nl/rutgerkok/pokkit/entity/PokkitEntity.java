package nl.rutgerkok.pokkit.entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import nl.rutgerkok.pokkit.Pokkit;
import nl.rutgerkok.pokkit.PokkitLocation;
import nl.rutgerkok.pokkit.player.PokkitTeleportCause;
import nl.rutgerkok.pokkit.world.PokkitWorld;

import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import com.google.common.base.Strings;

import cn.nukkit.math.Vector3;

public class PokkitEntity implements Entity {
	public static Entity toBukkit(cn.nukkit.entity.Entity nukkit) {
		if (nukkit == null) {
			return null;
		}

		if (nukkit instanceof cn.nukkit.entity.EntityLiving) {
			// Return more specific type
			return PokkitLivingEntity.toBukkit((cn.nukkit.entity.EntityLiving) nukkit);
		}
		return new PokkitEntity(nukkit);
	}

	public static cn.nukkit.entity.Entity toNukkit(Entity entity) {
		if (entity == null) {
			return null;
		}
		return ((PokkitEntity) entity).nukkit;
	}

	private final cn.nukkit.entity.Entity nukkit;

	PokkitEntity(cn.nukkit.entity.Entity nukkitEntity) {
		this.nukkit = nukkitEntity;
	}

	@Override
	public PermissionAttachment addAttachment(Plugin plugin) {
		throw Pokkit.unsupported();
	}

	@Override
	public PermissionAttachment addAttachment(Plugin plugin, int ticks) {
		throw Pokkit.unsupported();
	}

	@Override
	public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value) {
		throw Pokkit.unsupported();
	}

	@Override
	public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value, int ticks) {
		throw Pokkit.unsupported();
	}

	@Override
	public boolean addScoreboardTag(String tag) {
		throw Pokkit.unsupported();
	}

	@Override
	public boolean eject() {
		throw Pokkit.unsupported();
	}

	@Override
	public String getCustomName() {
		return Strings.emptyToNull(nukkit.getNameTag());
	}

	@Override
	public Set<PermissionAttachmentInfo> getEffectivePermissions() {
		return new HashSet<>();
	}

	@Override
	public int getEntityId() {
		throw Pokkit.unsupported();
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
	public EntityDamageEvent getLastDamageCause() {
		cn.nukkit.event.entity.EntityDamageEvent nukkitEvent = nukkit.getLastDamageCause();
		@SuppressWarnings("deprecation")
		EntityDamageEvent bukkitEvent = new EntityDamageEvent(PokkitEntity.toBukkit(nukkitEvent.getEntity()),
				PokkitDamageCause.toBukkit(nukkitEvent.getCause()), nukkitEvent.getDamage());
		return bukkitEvent;
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
	public int getMaxFireTicks() {
		return nukkit.maxFireTicks;
	}

	@Override
	public List<MetadataValue> getMetadata(String metadataKey) {
		throw Pokkit.unsupported();
	}

	@Override
	public String getName() {
		return nukkit.getName();
	}

	@Override
	public List<Entity> getNearbyEntities(double x, double y, double z) {
		throw Pokkit.unsupported();
	}

	@Override
	public Entity getPassenger() {
		return PokkitEntity.toBukkit(nukkit.riding);
	}

	@Override
	public int getPortalCooldown() {
		return 80;
	}

	@Override
	public Set<String> getScoreboardTags() {
		return Collections.emptySet();
	}

	@Override
	public Server getServer() {
		return Bukkit.getServer();
	}

	@Override
	public int getTicksLived() {
		return nukkit.ticksLived;
	}

	@Override
	public EntityType getType() {
		throw Pokkit.unsupported();
	}

	@Override
	public UUID getUniqueId() {
		throw Pokkit.unsupported();
	}

	@Override
	public Entity getVehicle() {
		return PokkitEntity.toBukkit(nukkit.riding);
	}

	@Override
	public Vector getVelocity() {
		return new Vector(nukkit.motionX, nukkit.motionY, nukkit.motionZ);
	}

	@Override
	public World getWorld() {
		return PokkitWorld.toBukkit(nukkit.getLevel());
	}

	@Override
	public boolean hasGravity() {
		throw Pokkit.unsupported();
	}

	@Override
	public boolean hasMetadata(String metadataKey) {
		throw Pokkit.unsupported();
	}

	@Override
	public boolean hasPermission(Permission perm) {
		switch (perm.getDefault()) {
			case FALSE:
				return false;
			case NOT_OP:
				return !isOp();
			case OP:
				return isOp();
			case TRUE:
				return true;
			default:
				return false;
		}
	}

	@Override
	public boolean hasPermission(String name) {
		return false;
	}

	@Override
	public boolean isCustomNameVisible() {
		return nukkit.isNameTagVisible();
	}

	@Override
	public boolean isDead() {
		return nukkit.getHealth() <= 0 || !nukkit.isAlive();
	}

	@Override
	public boolean isEmpty() {
		return false; // No vehicle support yet
	}

	@Override
	public boolean isGlowing() {
		return false; // No glow support yet
	}

	@Override
	public boolean isInsideVehicle() {
		return false; // No vehicle support yet
	}

	@Override
	public boolean isInvulnerable() {
		return nukkit.invulnerable;
	}

	@Override
	public boolean isOnGround() {
		return nukkit.isOnGround();
	}

	@Override
	public boolean isOp() {
		return false; // Entities cannot be OP in Nukkit
	}

	@Override
	public boolean isPermissionSet(Permission perm) {
		return false; // Permissions cannot be set for entities in Nukkit
	}

	@Override
	public boolean isPermissionSet(String name) {
		return false; // Permissions cannot be set for entities in Nukkit
	}

	@Override
	public boolean isSilent() {
		return false; // No silence support yet
	}

	@Override
	public boolean isValid() {
		return nukkit.isAlive() && nukkit.isValid();
	}

	@Override
	public boolean leaveVehicle() {
		throw Pokkit.unsupported();
	}

	@Override
	public void playEffect(EntityEffect type) {
		// Not supported yet. As effects are usually unimportant, it's not worth
		// to crash a plugin over this, so let this method fail silently
	}

	@Override
	public void recalculatePermissions() {
		// No permission support for entities in Nukkit, so nothing to
		// recalculate.
	}

	@Override
	public void remove() {
		throw Pokkit.unsupported();

	}

	@Override
	public void removeAttachment(PermissionAttachment attachment) {
		throw Pokkit.unsupported();

	}

	@Override
	public void removeMetadata(String metadataKey, Plugin owningPlugin) {
		throw Pokkit.unsupported();

	}

	@Override
	public boolean removeScoreboardTag(String tag) {
		throw Pokkit.unsupported();
	}

	@Override
	public void sendMessage(String message) {
		// Ignore, entities don't record messages in Nukkit
	}

	@Override
	public void sendMessage(String[] messages) {
		// Ignore, entities don't record messages in Nukkit
	}

	@Override
	public void setCustomName(String name) {
		nukkit.setNameTag(name);
	}

	@Override
	public void setCustomNameVisible(boolean visible) {
		nukkit.setNameTagVisible(visible);
	}

	@Override
	public void setFallDistance(float distance) {
		nukkit.fallDistance = distance;
	}

	@Override
	public void setFireTicks(int ticks) {
		nukkit.fireTicks = ticks;
	}

	@Override
	public void setGlowing(boolean flag) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setGravity(boolean gravity) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setInvulnerable(boolean flag) {
		nukkit.invulnerable = flag;
	}

	@Override
	public void setLastDamageCause(EntityDamageEvent event) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setMetadata(String metadataKey, MetadataValue newMetadataValue) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setOp(boolean value) {
		throw Pokkit.unsupported();

	}

	@Override
	public boolean setPassenger(Entity passenger) {
		throw Pokkit.unsupported();
	}

	@Override
	public void setPortalCooldown(int cooldown) {
		return; // TODO: When portals are properly implemented in Nukkit, change this to use Nukkit's API!
	}

	@Override
	public void setSilent(boolean flag) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setTicksLived(int value) {
		nukkit.ticksLived = value;
	}

	@Override
	public void setVelocity(Vector velocity) {
		nukkit.setMotion(new Vector3(velocity.getX(), velocity.getY(), velocity.getZ()));
	}

	@Override
	public Entity.Spigot spigot() {
		return new Entity.Spigot() {
			@Override
			public boolean isInvulnerable() {
				return PokkitEntity.this.isInvulnerable();
			}
		};
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
}
