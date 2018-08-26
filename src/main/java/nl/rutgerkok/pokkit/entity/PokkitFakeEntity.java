package nl.rutgerkok.pokkit.entity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import nl.rutgerkok.pokkit.Pokkit;
import nl.rutgerkok.pokkit.PokkitLocation;
import nl.rutgerkok.pokkit.world.PokkitWorld;

import cn.nukkit.math.SimpleAxisAlignedBB;

abstract class PokkitFakeEntity implements Entity {

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
	public boolean addPassenger(Entity passenger) {
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
		return null;
	}

	@Override
	public Set<PermissionAttachmentInfo> getEffectivePermissions() {
		return Collections.emptySet();
	}

	@Override
	public int getEntityId() {
		throw Pokkit.unsupported();

	}

	@Override
	public float getFallDistance() {
		return 0;
	}

	@Override
	public int getFireTicks() {
		return 0;
	}

	@Override
	public double getHeight() {
		throw Pokkit.unsupported();
	}

	@Override
	public EntityDamageEvent getLastDamageCause() {
		throw Pokkit.unsupported();

	}

	@Override
	public Location getLocation() {
		return PokkitLocation.toBukkit(getNukkitLocation());
	}

	@Override
	public Location getLocation(Location loc) {
		return PokkitLocation.toBukkit(getNukkitLocation(), loc);
	}

	@Override
	public int getMaxFireTicks() {
		throw Pokkit.unsupported();

	}

	@Override
	public List<MetadataValue> getMetadata(String metadataKey) {
		return Collections.emptyList();
	}

	@Override
	public String getName() {
		String customName = this.getCustomName();
		if (customName != null) {
			return customName;
		}
		return this.getType().toString();
	}

	@Override
	public List<Entity> getNearbyEntities(double x, double y, double z) {
		cn.nukkit.level.Location location = this.getNukkitLocation();
		cn.nukkit.entity.Entity[] found = location.level.getNearbyEntities(new SimpleAxisAlignedBB(
				location.x - x, location.y - y, location.z - z,
				location.x + x, location.y + y, location.z + z));

		return Arrays.stream(found).map(PokkitEntity::toBukkit).collect(Collectors.toList());
	}

	abstract cn.nukkit.level.Location getNukkitLocation();

	@Override
	public Entity getPassenger() {
		return null;
	}

	@Override
	public List<Entity> getPassengers() {
		return Collections.emptyList();
	}

	@Override
	public PistonMoveReaction getPistonMoveReaction() {
		return PistonMoveReaction.MOVE;
	}

	@Override
	public int getPortalCooldown() {
		return 0;
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
		return 0;
	}

	@Override
	public UUID getUniqueId() {
		throw Pokkit.unsupported();

	}

	@Override
	public Entity getVehicle() {
		return null;
	}

	@Override
	public Vector getVelocity() {
		return new Vector(0, 0, 0);
	}

	@Override
	public double getWidth() {
		throw Pokkit.unsupported();
	}

	@Override
	public World getWorld() {
		return PokkitWorld.toBukkit(getNukkitLocation().level);
	}

	@Override
	public boolean hasGravity() {
		return false;
	}

	@Override
	public boolean hasMetadata(String metadataKey) {
		throw Pokkit.unsupported();

	}

	@Override
	public boolean hasPermission(Permission perm) {
		throw Pokkit.unsupported();

	}

	@Override
	public boolean hasPermission(String name) {
		throw Pokkit.unsupported();

	}

	@Override
	public boolean isCustomNameVisible() {
		return false;
	}

	@Override
	public boolean isDead() {
		return false;
	}

	@Override
	public boolean isEmpty() {
		return true;
	}

	@Override
	public boolean isGlowing() {
		return false;
	}

	@Override
	public boolean isInsideVehicle() {
		return false;
	}

	@Override
	public boolean isInvulnerable() {
		return false;
	}

	@Override
	public boolean isOnGround() {
		return false;
	}

	@Override
	public boolean isOp() {
		return false;
	}

	@Override
	public boolean isPermissionSet(Permission perm) {
		return false;
	}

	@Override
	public boolean isPermissionSet(String name) {
		return false;
	}

	@Override
	public boolean isPersistent() {
		return false;
	}

	@Override
	public boolean isSilent() {
		return false;
	}

	@Override
	public boolean isValid() {
		return false; // Fake entities are not valid for most purposes
	}

	@Override
	public boolean leaveVehicle() {
		throw Pokkit.unsupported();

	}

	@Override
	public void playEffect(EntityEffect type) {
		throw Pokkit.unsupported();

	}

	@Override
	public void recalculatePermissions() {
		throw Pokkit.unsupported();

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
	public boolean removePassenger(Entity passenger) {
		throw Pokkit.unsupported();

	}

	@Override
	public boolean removeScoreboardTag(String tag) {
		throw Pokkit.unsupported();

	}

	@Override
	public void sendMessage(String message) {
		throw Pokkit.unsupported();

	}

	@Override
	public void sendMessage(String[] messages) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setCustomName(String name) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setCustomNameVisible(boolean flag) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setFallDistance(float distance) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setFireTicks(int ticks) {
		throw Pokkit.unsupported();

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
		throw Pokkit.unsupported();

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
	public void setPersistent(boolean persistent) {
		throw Pokkit.unsupported();
	}

	@Override
	public void setPortalCooldown(int cooldown) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setSilent(boolean flag) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setTicksLived(int value) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setVelocity(Vector velocity) {
		throw Pokkit.unsupported();

	}

	@Override
	public Spigot spigot() {
		throw Pokkit.unsupported();
	}

	@Override
	public boolean teleport(Entity destination) {
		throw Pokkit.unsupported();

	}

	@Override
	public boolean teleport(Entity destination, TeleportCause cause) {
		throw Pokkit.unsupported();

	}

	@Override
	public boolean teleport(Location location) {
		throw Pokkit.unsupported();

	}

	@Override
	public boolean teleport(Location location, TeleportCause cause) {
		throw Pokkit.unsupported();

	}
}
