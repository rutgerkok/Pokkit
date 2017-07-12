package nl.rutgerkok.pokkit.entity;

import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public final class PokkitDamageCause {

	public static DamageCause toBukkit(cn.nukkit.event.entity.EntityDamageEvent.DamageCause nukkit) {
		// TODO Direct mapping via DamageCause.valueOf?
		switch (nukkit) {
			case CONTACT:
				return DamageCause.CONTACT;
			case ENTITY_ATTACK:
				return DamageCause.ENTITY_ATTACK;
			case PROJECTILE:
				return DamageCause.PROJECTILE;
			case SUFFOCATION:
				return DamageCause.SUFFOCATION;
			case FALL:
				return DamageCause.FALL;
			case FIRE:
				return DamageCause.FIRE;
			case FIRE_TICK:
				return DamageCause.FIRE_TICK;
			case LAVA:
				return DamageCause.LAVA;
			case DROWNING:
				return DamageCause.DROWNING;
			case BLOCK_EXPLOSION:
				return DamageCause.BLOCK_EXPLOSION;
			case ENTITY_EXPLOSION:
				return DamageCause.ENTITY_EXPLOSION;
			case VOID:
				return DamageCause.VOID;
			case SUICIDE:
				return DamageCause.SUICIDE;
			case MAGIC:
				return DamageCause.MAGIC;
			case LIGHTNING:
				return DamageCause.LIGHTNING;
			case CUSTOM:
			default:
				return DamageCause.CUSTOM;
		}
	}
}
