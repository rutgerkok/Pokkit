package nl.rutgerkok.pokkit.world;

import java.lang.reflect.Constructor;

import org.bukkit.GameRule;

public class PokkitGameRule {

	/**
	 * Gets the corresponding Bukkit GameRule
	 *
	 * @param nukkit
	 *            The Nukkit GameRule.
	 * @return The Bukkit GameRule. It is created if it does not exist yet.
	 */
	public static GameRule<?> toBukkit(cn.nukkit.level.GameRule nukkit) {
		GameRule<?> bukkit = GameRule.getByName(nukkit.getName());
		if (bukkit == null) {
			try {
				@SuppressWarnings("rawtypes")
				Constructor<GameRule> constructor = GameRule.class.getDeclaredConstructor(String.class, Class.class);
				constructor.setAccessible(true);
				bukkit = constructor.newInstance(nukkit.getName(), Boolean.class);
			} catch (ReflectiveOperationException e) {
				throw new RuntimeException("Failed to create new game rule", e);
			}
		}
		return bukkit;
	}

	/**
	 * Gets the corresponding Nukkit GameRule.
	 * 
	 * @param rule
	 *            The Bukkit GameRule.
	 * @return The Nukkit GameRule, or null if not found.
	 */
	public static cn.nukkit.level.GameRule toNukkit(GameRule<?> rule) {
		return cn.nukkit.level.GameRule.parseString(rule.getName()).orElse(null);
	}

}
