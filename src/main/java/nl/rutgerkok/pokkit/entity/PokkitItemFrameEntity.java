package nl.rutgerkok.pokkit.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Rotation;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import cn.nukkit.blockentity.BlockEntityItemFrame;
import nl.rutgerkok.pokkit.PokkitLocation;
import nl.rutgerkok.pokkit.world.item.PokkitItemStack;

public class PokkitItemFrameEntity implements ItemFrame {
    BlockEntityItemFrame nukkit;
    
    public PokkitItemFrameEntity(BlockEntityItemFrame nukkit) {
        this.nukkit = nukkit;
    }
    
    @Override
    public void setMetadata(String metadataKey, MetadataValue newMetadataValue) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<MetadataValue> getMetadata(String metadataKey) {
        return new ArrayList<MetadataValue>(); // Nukkit can't store metadata
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
        return new HashSet<PermissionAttachmentInfo>();
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
    public String getCustomName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setCustomName(String name) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Location getLocation() {
        return PokkitLocation.toBukkit(nukkit.getLocation());
    }

    @Override
    public Location getLocation(Location loc) {
        return PokkitLocation.toBukkit(nukkit.getLocation(), loc);
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
        nukkit.close();
    }

    @Override
    public boolean isDead() {
        // TODO Auto-generated method stub
        return nukkit.isBlockEntityValid();
    }

    @Override
    public boolean isValid() {
        // TODO Auto-generated method stub
        return nukkit.isBlockEntityValid();
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
        return EntityType.ITEM_FRAME;
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
        return 0;
    }

    @Override
    public void setPortalCooldown(int cooldown) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Set<String> getScoreboardTags() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean addScoreboardTag(String tag) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean removeScoreboardTag(String tag) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Spigot spigot() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean setFacingDirection(BlockFace face, boolean force) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public BlockFace getAttachedFace() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setFacingDirection(BlockFace face) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public BlockFace getFacing() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ItemStack getItem() {
        return PokkitItemStack.toBukkitCopy(nukkit.getItem());
    }

    @Override
    public void setItem(ItemStack item) {
        nukkit.setItem(PokkitItemStack.toNukkitCopy(item));
    }

    @Override
    public Rotation getRotation() {
        // TODO: Implement
        return Rotation.CLOCKWISE;
    }

    @Override
    public void setRotation(Rotation rotation) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        
    }

}
