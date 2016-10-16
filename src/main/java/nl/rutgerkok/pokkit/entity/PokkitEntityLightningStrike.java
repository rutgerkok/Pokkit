package nl.rutgerkok.pokkit.entity;

import org.bukkit.entity.LightningStrike;

import cn.nukkit.entity.Entity;
import cn.nukkit.entity.weather.EntityLightningStrike;

public class PokkitEntityLightningStrike extends PokkitEntity implements LightningStrike{

	private final cn.nukkit.entity.Entity nukkit;
	
	PokkitEntityLightningStrike(Entity nukkitEntity) {
		super(nukkitEntity);
		this.nukkit = nukkitEntity;
	}
	
	public static LightningStrike toBukkit(cn.nukkit.entity.Entity nukkit) {
		if (nukkit == null) {
			return null;
		}
		if (!(nukkit instanceof EntityLightningStrike)) {
			return null;
		}
		
		return new PokkitEntityLightningStrike(nukkit);
	}

	public static cn.nukkit.entity.weather.EntityLightningStrike toNukkit(LightningStrike entity) {
		if (entity == null) {
			return null;
		}
		return (EntityLightningStrike) ((PokkitEntityLightningStrike) entity).nukkit;
	}


	@Override
	public boolean isEffect() {
		return ((EntityLightningStrike)nukkit).isEffect();
	}

	@Override
	public org.bukkit.entity.LightningStrike.Spigot spigot() {
		return new org.bukkit.entity.LightningStrike.Spigot();
	}

}
