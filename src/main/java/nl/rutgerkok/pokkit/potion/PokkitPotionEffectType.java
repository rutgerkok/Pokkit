package nl.rutgerkok.pokkit.potion;

import org.bukkit.potion.PotionEffectType;

import cn.nukkit.potion.Effect;
import cn.nukkit.utils.ServerException;

/**
 * Converts potion effect types.
 *
 */
public class PokkitPotionEffectType {

	@SuppressWarnings("deprecation")
	public static Effect toNukkit(PotionEffectType type) {
		try {
			return Effect.getEffect(type.getId());
		} catch (ServerException e) {
			return new Effect(type.getId(), "bukkit_" + type.getId(), 9, 0, 0);
		}
	}

}
