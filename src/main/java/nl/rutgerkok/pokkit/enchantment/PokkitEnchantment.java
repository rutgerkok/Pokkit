package nl.rutgerkok.pokkit.enchantment;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;

public class PokkitEnchantment {

	private static Enchantment[] nukkitToBukkit = new Enchantment[33];
	private static Map<NamespacedKey, Integer> bukkitToNukkit = new HashMap<>();

	static {
		twoWay(cn.nukkit.item.enchantment.Enchantment.ID_PROTECTION_ALL, Enchantment.PROTECTION_ENVIRONMENTAL);
		twoWay(cn.nukkit.item.enchantment.Enchantment.ID_PROTECTION_FIRE, Enchantment.PROTECTION_FIRE);
		twoWay(cn.nukkit.item.enchantment.Enchantment.ID_PROTECTION_FALL, Enchantment.PROTECTION_FALL);
		twoWay(cn.nukkit.item.enchantment.Enchantment.ID_PROTECTION_EXPLOSION, Enchantment.PROTECTION_EXPLOSIONS);
		twoWay(cn.nukkit.item.enchantment.Enchantment.ID_PROTECTION_PROJECTILE, Enchantment.PROTECTION_PROJECTILE);
		twoWay(cn.nukkit.item.enchantment.Enchantment.ID_THORNS, Enchantment.THORNS);
		twoWay(cn.nukkit.item.enchantment.Enchantment.ID_WATER_BREATHING, Enchantment.OXYGEN);
		twoWay(cn.nukkit.item.enchantment.Enchantment.ID_WATER_WORKER, Enchantment.WATER_WORKER);
		twoWay(cn.nukkit.item.enchantment.Enchantment.ID_WATER_WALKER, Enchantment.DEPTH_STRIDER);
		twoWay(cn.nukkit.item.enchantment.Enchantment.ID_DAMAGE_ALL, Enchantment.DAMAGE_ALL);
		twoWay(cn.nukkit.item.enchantment.Enchantment.ID_DAMAGE_SMITE, Enchantment.DAMAGE_UNDEAD);
		twoWay(cn.nukkit.item.enchantment.Enchantment.ID_DAMAGE_ARTHROPODS, Enchantment.DAMAGE_ARTHROPODS);
		twoWay(cn.nukkit.item.enchantment.Enchantment.ID_KNOCKBACK, Enchantment.KNOCKBACK);
		twoWay(cn.nukkit.item.enchantment.Enchantment.ID_FIRE_ASPECT, Enchantment.FIRE_ASPECT);
		twoWay(cn.nukkit.item.enchantment.Enchantment.ID_LOOTING, Enchantment.LOOT_BONUS_MOBS);
		twoWay(cn.nukkit.item.enchantment.Enchantment.ID_EFFICIENCY, Enchantment.DIG_SPEED);
		twoWay(cn.nukkit.item.enchantment.Enchantment.ID_SILK_TOUCH, Enchantment.SILK_TOUCH);
		twoWay(cn.nukkit.item.enchantment.Enchantment.ID_DURABILITY, Enchantment.DURABILITY);
		twoWay(cn.nukkit.item.enchantment.Enchantment.ID_FORTUNE_DIGGING, Enchantment.LOOT_BONUS_BLOCKS);
		twoWay(cn.nukkit.item.enchantment.Enchantment.ID_BOW_POWER, Enchantment.ARROW_DAMAGE);
		twoWay(cn.nukkit.item.enchantment.Enchantment.ID_BOW_KNOCKBACK, Enchantment.ARROW_KNOCKBACK);
		twoWay(cn.nukkit.item.enchantment.Enchantment.ID_BOW_FLAME, Enchantment.ARROW_FIRE);
		twoWay(cn.nukkit.item.enchantment.Enchantment.ID_BOW_INFINITY, Enchantment.ARROW_INFINITE);
		twoWay(cn.nukkit.item.enchantment.Enchantment.ID_FORTUNE_FISHING, Enchantment.LUCK);
		twoWay(cn.nukkit.item.enchantment.Enchantment.ID_LURE, Enchantment.LURE);
		twoWay(cn.nukkit.item.enchantment.Enchantment.ID_FROST_WALKER, Enchantment.FROST_WALKER);
		twoWay(cn.nukkit.item.enchantment.Enchantment.ID_MENDING, Enchantment.MENDING);
		twoWay(cn.nukkit.item.enchantment.Enchantment.ID_BINDING_CURSE, Enchantment.BINDING_CURSE);
		twoWay(cn.nukkit.item.enchantment.Enchantment.ID_VANISHING_CURSE, Enchantment.VANISHING_CURSE);
		twoWay(cn.nukkit.item.enchantment.Enchantment.ID_TRIDENT_IMPALING, Enchantment.IMPALING);
		twoWay(cn.nukkit.item.enchantment.Enchantment.ID_TRIDENT_LOYALTY, Enchantment.LOYALTY);
		twoWay(cn.nukkit.item.enchantment.Enchantment.ID_TRIDENT_RIPTIDE, Enchantment.RIPTIDE);
		twoWay(cn.nukkit.item.enchantment.Enchantment.ID_TRIDENT_CHANNELING, Enchantment.CHANNELING);
	}

	/**
	 * Called when Pokkit initialises.
	 */
	public static void registerNukkitEnchantmentsInBukkit() {
		if (!Enchantment.isAcceptingRegistrations()) {
			// Already initialized
			return;
		}
		for (cn.nukkit.item.enchantment.Enchantment nukkitEnchantment : cn.nukkit.item.enchantment.Enchantment.getEnchantments()) {
			Enchantment bukkitWrapper = PokkitEnchantment.toBukkit(nukkitEnchantment.getId());
			Enchantment bukkitImpl = new PokkitEnchantmentImpl(nukkitEnchantment, bukkitWrapper.getKey());

			Enchantment.registerEnchantment(bukkitImpl);
		}
		Enchantment.stopAcceptingRegistrations();
	}

	/**
	 * Gets a Bukkit enchantment.
	 *
	 * @param nukkit
	 *            The Nukkit enchantment id.
	 * @return The Bukkit enchantment, or null if not found. There exists a test
	 *         that should prevent null from being returned for all known
	 *         enchantments, but it is possible that this test has been
	 *         disabled. It is also possible that this method is called with a
	 *         bogus enchantment id.
	 */
	public static Enchantment toBukkit(int nukkit) {
		if (nukkit < 0 || nukkit >= nukkitToBukkit.length) {
			return null;
		}
		return nukkitToBukkit[nukkit];
	}

	/**
	 * Gets a Nukkit enchantment.
	 *
	 * @param enchantment
	 *            The Bukkit enchantment.
	 * @return The Nukkit enchantment id, or -1 if not found. There exists a
	 *         test that should prevent -1 from being returned for all known
	 *         enchantments, but it is possible that this test has been
	 *         disabled.
	 */
	public static int toNukkit(Enchantment enchantment) {
		NamespacedKey bukkitId = enchantment.getKey();
		return bukkitToNukkit.getOrDefault(bukkitId, -1).intValue();
	}

	private static void twoWay(int nukkit, Enchantment bukkit) {
		NamespacedKey bukkitId = bukkit.getKey();

		nukkitToBukkit[nukkit] = bukkit;
		bukkitToNukkit.put(bukkitId, nukkit);
	}

}
