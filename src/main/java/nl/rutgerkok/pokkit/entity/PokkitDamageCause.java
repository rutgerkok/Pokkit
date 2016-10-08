package nl.rutgerkok.pokkit.entity;

import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import cn.nukkit.event.entity.EntityDamageEvent;

public final class PokkitDamageCause {

	public static DamageCause toBukkit(int nukkit) {
		switch (nukkit) {
			case EntityDamageEvent.CAUSE_CONTACT:
				return DamageCause.CONTACT;
			case EntityDamageEvent.CAUSE_ENTITY_ATTACK:
				return DamageCause.ENTITY_ATTACK;
			case EntityDamageEvent.CAUSE_PROJECTILE:
				return DamageCause.PROJECTILE;
			case EntityDamageEvent.CAUSE_SUFFOCATION:
				return DamageCause.SUFFOCATION;
			case EntityDamageEvent.CAUSE_FALL:
				return DamageCause.FALL;
			case EntityDamageEvent.CAUSE_FIRE:
				return DamageCause.FIRE;
			case EntityDamageEvent.CAUSE_FIRE_TICK:
				return DamageCause.FIRE_TICK;
			case EntityDamageEvent.CAUSE_LAVA:
				return DamageCause.LAVA;
			case EntityDamageEvent.CAUSE_DROWNING:
				return DamageCause.DROWNING;
			case EntityDamageEvent.CAUSE_BLOCK_EXPLOSION:
				return DamageCause.BLOCK_EXPLOSION;
			case EntityDamageEvent.CAUSE_ENTITY_EXPLOSION:
				return DamageCause.ENTITY_EXPLOSION;
			case EntityDamageEvent.CAUSE_VOID:
				return DamageCause.VOID;
			case EntityDamageEvent.CAUSE_SUICIDE:
				return DamageCause.SUICIDE;
			case EntityDamageEvent.CAUSE_MAGIC:
				return DamageCause.MAGIC;
			case EntityDamageEvent.CAUSE_LIGHTNING:
				return DamageCause.LIGHTNING;
			case EntityDamageEvent.CAUSE_CUSTOM:
			default:
				return DamageCause.CUSTOM;
		}
	}
}
