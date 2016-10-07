package nl.rutgerkok.pokkit.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nl.rutgerkok.pokkit.Pokkit;
import nl.rutgerkok.pokkit.player.PokkitPlayer;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class PokkitLivingEntity extends PokkitEntity implements LivingEntity {
	public static LivingEntity toBukkit(cn.nukkit.entity.EntityLiving nukkit) {
		if (nukkit == null) {
			return null;
		}

		if (nukkit instanceof cn.nukkit.Player) {
			return PokkitPlayer.toBukkit((cn.nukkit.Player) nukkit);
		}

		return new PokkitLivingEntity(nukkit);
	}

	public static cn.nukkit.entity.Entity toNukkit(LivingEntity entity) {
		if (entity == null) {
			return null;
		}
		return ((PokkitLivingEntity) entity).nukkit;
	}

	private final cn.nukkit.entity.EntityLiving nukkit;

	PokkitLivingEntity(cn.nukkit.entity.EntityLiving nukkitEntity) {
		super(nukkitEntity);
		this.nukkit = nukkitEntity;
	}

	@Override
	public void _INVALID_damage(int amount) {
		throw Pokkit.unsupported();

	}

	@Override
	public void _INVALID_damage(int amount, Entity source) {
		throw Pokkit.unsupported();

	}

	@Override
	public int _INVALID_getHealth() {
		return nukkit.getHealth();
	}

	@Override
	public int _INVALID_getLastDamage() {
		return (int) nukkit.getLastDamageCause().getFinalDamage();
	}

	@Override
	public int _INVALID_getMaxHealth() {
		return nukkit.getMaxHealth();
	}

	@Override
	public void _INVALID_setHealth(int health) {
		nukkit.setHealth(health);
	}

	@Override
	public void _INVALID_setLastDamage(int damage) {
		throw Pokkit.unsupported();

	}

	@Override
	public void _INVALID_setMaxHealth(int health) {
		nukkit.setMaxHealth(health);
	}

	@Override
	public boolean addPotionEffect(PotionEffect effect) {
		throw Pokkit.unsupported();
	}

	@Override
	public boolean addPotionEffect(PotionEffect effect, boolean force) {
		throw Pokkit.unsupported();
	}

	@Override
	public boolean addPotionEffects(Collection<PotionEffect> effects) {
		throw Pokkit.unsupported();
	}

	@Override
	public void damage(double amount) {
		throw Pokkit.unsupported();

	}

	@Override
	public void damage(double amount, Entity source) {
		throw Pokkit.unsupported();

	}

	@Override
	public Collection<PotionEffect> getActivePotionEffects() {
		throw Pokkit.unsupported();
	}

	@Override
	public AttributeInstance getAttribute(Attribute attribute) {
		throw Pokkit.unsupported();
	}

	@Override
	public boolean getCanPickupItems() {
		throw Pokkit.unsupported();
	}

	@Override
	public EntityEquipment getEquipment() {
		throw Pokkit.unsupported();
	}

	@Override
	public double getEyeHeight() {
		throw Pokkit.unsupported();
	}

	@Override
	public double getEyeHeight(boolean ignoreSneaking) {
		throw Pokkit.unsupported();
	}

	@Override
	public Location getEyeLocation() {
		return getLocation().add(0, getEyeHeight(), 0);
	}

	@Override
	public double getHealth() {
		return nukkit.getHealth();
	}

	@Override
	public Player getKiller() {
		throw Pokkit.unsupported();
	}

	@Override
	public double getLastDamage() {
		return nukkit.getLastDamageCause().getFinalDamage();
	}

	@Override
	public List<Block> getLastTwoTargetBlocks(HashSet<Byte> transparent, int maxDistance) {
		throw Pokkit.unsupported();
	}

	@Override
	public List<Block> getLastTwoTargetBlocks(Set<Material> transparent, int maxDistance) {
		throw Pokkit.unsupported();
	}

	@Override
	public Entity getLeashHolder() throws IllegalStateException {
		throw Pokkit.unsupported();
	}

	@Override
	public List<Block> getLineOfSight(HashSet<Byte> transparent, int maxDistance) {
		throw Pokkit.unsupported();
	}

	@Override
	public List<Block> getLineOfSight(Set<Material> transparent, int maxDistance) {
		throw Pokkit.unsupported();
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
		return 60; // From Nukkit source code
	}

	@Override
	public int getNoDamageTicks() {
		return nukkit.noDamageTicks;
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
	public Block getTargetBlock(HashSet<Byte> transparent, int maxDistance) {
		throw Pokkit.unsupported();
	}

	@Override
	public Block getTargetBlock(Set<Material> transparent, int maxDistance) {
		throw Pokkit.unsupported();
	}

	@Override
	public boolean hasAI() {
		throw Pokkit.unsupported();
	}

	@Override
	public boolean hasLineOfSight(Entity other) {
		return nukkit.hasLineOfSight(PokkitEntity.toNukkit(other));
	}

	@Override
	public boolean hasPotionEffect(PotionEffectType type) {
		throw Pokkit.unsupported();
	}

	@Override
	public boolean isCollidable() {
		return nukkit.canCollide();
	}

	@Override
	public boolean isGliding() {
		throw Pokkit.unsupported();
	}

	@Override
	public boolean isLeashed() {
		throw Pokkit.unsupported();
	}

	@Override
	public <T extends Projectile> T launchProjectile(Class<? extends T> projectile) {
		throw Pokkit.unsupported();
	}

	@Override
	public <T extends Projectile> T launchProjectile(Class<? extends T> projectile, Vector velocity) {
		throw Pokkit.unsupported();
	}

	@Override
	public void removePotionEffect(PotionEffectType type) {
		throw Pokkit.unsupported();

	}

	@Override
	public void resetMaxHealth() {
		throw Pokkit.unsupported();

	}

	@Override
	public void setAI(boolean ai) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setCanPickupItems(boolean pickup) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setCollidable(boolean collidable) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setGliding(boolean gliding) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setHealth(double health) {
		nukkit.setHealth((float) health);
	}

	@Override
	public void setLastDamage(double damage) {
		throw Pokkit.unsupported();

	}

	@Override
	public boolean setLeashHolder(Entity holder) {
		throw Pokkit.unsupported();
	}

	@Override
	public void setMaxHealth(double health) {
		nukkit.setMaxHealth((int) Math.ceil(health));
	}

	@Override
	public void setMaximumAir(int ticks) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setMaximumNoDamageTicks(int ticks) {
		throw Pokkit.unsupported();
	}

	@Override
	public void setNoDamageTicks(int ticks) {
		nukkit.noDamageTicks = ticks;
	}

	@Override
	public void setRemainingAir(int ticks) {
		throw Pokkit.unsupported();
	}

	@Override
	public void setRemoveWhenFarAway(boolean remove) {
		throw Pokkit.unsupported();
	}

}
