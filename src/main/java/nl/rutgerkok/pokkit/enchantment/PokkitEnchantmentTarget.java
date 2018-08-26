package nl.rutgerkok.pokkit.enchantment;

import java.util.EnumMap;
import java.util.Map;

import org.bukkit.enchantments.EnchantmentTarget;

import cn.nukkit.item.enchantment.EnchantmentType;

public class PokkitEnchantmentTarget {

	private static final Map<EnchantmentType, EnchantmentTarget> nukkitToBukkit = new EnumMap<>(EnchantmentType.class);
	private static final Map<EnchantmentTarget, EnchantmentType> bukkitToNukkit = new EnumMap<>(
			EnchantmentTarget.class);

	static {
		twoWay(EnchantmentType.ALL, EnchantmentTarget.ALL);
		twoWay(EnchantmentType.ARMOR, EnchantmentTarget.ARMOR);
		twoWay(EnchantmentType.ARMOR_FEET, EnchantmentTarget.ARMOR_FEET);
		twoWay(EnchantmentType.ARMOR_HEAD, EnchantmentTarget.ARMOR_HEAD);
		twoWay(EnchantmentType.ARMOR_LEGS, EnchantmentTarget.ARMOR_LEGS);
		twoWay(EnchantmentType.ARMOR_TORSO, EnchantmentTarget.ARMOR_TORSO);
		twoWay(EnchantmentType.BOW, EnchantmentTarget.BOW);
		twoWay(EnchantmentType.BREAKABLE, EnchantmentTarget.BREAKABLE);
		twoWay(EnchantmentType.DIGGER, EnchantmentTarget.TOOL);
		twoWay(EnchantmentType.FISHING_ROD, EnchantmentTarget.FISHING_ROD);
		twoWay(EnchantmentType.SWORD, EnchantmentTarget.WEAPON);

		// For WEARABLE and TRIDENT, no suitable Nukkit target exists yet
		bukkitToNukkit.put(EnchantmentTarget.WEARABLE, EnchantmentType.BREAKABLE);
		bukkitToNukkit.put(EnchantmentTarget.TRIDENT, EnchantmentType.BREAKABLE);
	}

	/**
	 * Gets the corresponding Bukkit enchantment target.
	 * @param nukkit The Nukkit enchantment type.
	 * @return The Bukkit enchantment type, or null if not found.
	 */
	public static EnchantmentTarget toBukkit(EnchantmentType nukkit) {
		return nukkitToBukkit.get(nukkit);
	}

	/**
	 * Gets the corresponding Nukkit enchantment type.
	 * @param bukkit The Bukkit enchantment target.
	 * @return The Nukkit enchantment type, or null if not found.
	 */
	public static EnchantmentType toNukkit(EnchantmentTarget bukkit) {
		return bukkitToNukkit.get(bukkit);
	}

	private static void twoWay(EnchantmentType nukkit, EnchantmentTarget bukkit) {
		nukkitToBukkit.put(nukkit, bukkit);
		bukkitToNukkit.put(bukkit, nukkit);
	}
}
