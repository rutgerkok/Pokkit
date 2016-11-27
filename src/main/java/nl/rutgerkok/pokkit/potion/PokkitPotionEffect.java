package nl.rutgerkok.pokkit.potion;

import org.bukkit.potion.PotionEffect;

import cn.nukkit.potion.Effect;

public class PokkitPotionEffect {

	public static Effect toNukkit(PotionEffect bukkitEffect) {
		cn.nukkit.potion.Effect nukkitEffect = PokkitPotionEffectType.toNukkit(bukkitEffect.getType());
		nukkitEffect.setAmbient(bukkitEffect.isAmbient());
		nukkitEffect.setAmplifier(bukkitEffect.getAmplifier());
		nukkitEffect.setDuration(bukkitEffect.getDuration());
		nukkitEffect.setColor(bukkitEffect.getColor().getRed(), bukkitEffect.getColor().getGreen(), bukkitEffect.getColor().getBlue());
		nukkitEffect.setVisible(bukkitEffect.hasParticles());
		return nukkitEffect;
	}

}
