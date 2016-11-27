package nl.rutgerkok.pokkit.particle;

import org.bukkit.Particle;

public class PokkitParticle {
	public static int toNukkit(Particle particle) {
		int id = 0;

		switch(particle) {
		case BARRIER:
			break;
		case BLOCK_CRACK:
			break;
		case BLOCK_DUST:
			break;
		case CLOUD:
			id = cn.nukkit.level.particle.Particle.TYPE_SMOKE;
			break;
		case CRIT:
			id = cn.nukkit.level.particle.Particle.TYPE_CRITICAL;
			break;
		case CRIT_MAGIC:
			break;
		case DAMAGE_INDICATOR:
			break;
		case DRAGON_BREATH:
			break;
		case DRIP_LAVA:
			id = cn.nukkit.level.particle.Particle.TYPE_DRIP_LAVA;
			break;
		case DRIP_WATER:
			id = cn.nukkit.level.particle.Particle.TYPE_DRIP_WATER;
			break;
		case ENCHANTMENT_TABLE:
			id = cn.nukkit.level.particle.Particle.TYPE_ENCHANTMENT_TABLE;
			break;
		case END_ROD:
			break;
		case EXPLOSION_HUGE:
			id = cn.nukkit.level.particle.Particle.TYPE_HUGE_EXPLODE;
			break;
		case EXPLOSION_LARGE:
			id = cn.nukkit.level.particle.Particle.TYPE_HUGE_EXPLODE;
			break;
		case EXPLOSION_NORMAL:
			id = cn.nukkit.level.particle.Particle.TYPE_EXPLODE;
			break;
		case FALLING_DUST:
			id = cn.nukkit.level.particle.Particle.TYPE_DUST;
			break;
		case FIREWORKS_SPARK:
			break;
		case FLAME:
			id = cn.nukkit.level.particle.Particle.TYPE_FLAME;
			break;
		case FOOTSTEP:
			break;
		case HEART:
			id = cn.nukkit.level.particle.Particle.TYPE_HEART;
			break;
		case ITEM_CRACK:
			id = cn.nukkit.level.particle.Particle.TYPE_ITEM_BREAK;
			break;
		case ITEM_TAKE:
			break;
		case LAVA:
			id = cn.nukkit.level.particle.Particle.TYPE_LAVA;
			break;
		case MOB_APPEARANCE:
			break;
		case NOTE:
			break;
		case PORTAL:
			id = cn.nukkit.level.particle.Particle.TYPE_PORTAL;
			break;
		case REDSTONE:
			id = cn.nukkit.level.particle.Particle.TYPE_REDSTONE;
			break;
		case SLIME:
			id = cn.nukkit.level.particle.Particle.TYPE_SLIME;
			break;
		case SMOKE_LARGE:
			id = cn.nukkit.level.particle.Particle.TYPE_LARGE_SMOKE;
			break;
		case SMOKE_NORMAL:
			id = cn.nukkit.level.particle.Particle.TYPE_SMOKE;
			break;
		case SNOWBALL:
			break;
		case SNOW_SHOVEL:
			id = cn.nukkit.level.particle.Particle.TYPE_SNOWBALL_POOF;
			break;
		case SPELL:
			id = cn.nukkit.level.particle.Particle.TYPE_MOB_SPELL;
			break;
		case SPELL_INSTANT:
			id = cn.nukkit.level.particle.Particle.TYPE_MOB_SPELL_INSTANTANEOUS;
			break;
		case SPELL_MOB:
			id = cn.nukkit.level.particle.Particle.TYPE_MOB_SPELL;
			break;
		case SPELL_MOB_AMBIENT:
			id = cn.nukkit.level.particle.Particle.TYPE_MOB_SPELL_AMBIENT;
			break;
		case SPELL_WITCH:
			break;
		case SUSPENDED:
			break;
		case SUSPENDED_DEPTH:
			break;
		case SWEEP_ATTACK:
			break;
		case TOWN_AURA:
			id = cn.nukkit.level.particle.Particle.TYPE_TOWN_AURA;
			break;
		case VILLAGER_ANGRY:
			id = cn.nukkit.level.particle.Particle.TYPE_VILLAGER_ANGRY;
			break;
		case VILLAGER_HAPPY:
			id = cn.nukkit.level.particle.Particle.TYPE_VILLAGER_HAPPY;
			break;
		case WATER_BUBBLE:
			break;
		case WATER_DROP:
			id = cn.nukkit.level.particle.Particle.TYPE_RAIN_SPLASH;
			break;
		case WATER_SPLASH:
			id = cn.nukkit.level.particle.Particle.TYPE_WATER_SPLASH;
			break;
		case WATER_WAKE:
			id = cn.nukkit.level.particle.Particle.TYPE_WATER_WAKE;
			break;
		default:
			break;
		}

		return id;
	}
}
