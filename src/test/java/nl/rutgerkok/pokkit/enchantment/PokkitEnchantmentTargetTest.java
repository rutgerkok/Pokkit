package nl.rutgerkok.pokkit.enchantment;

import static org.junit.Assert.assertNotNull;

import org.bukkit.enchantments.EnchantmentTarget;
import org.junit.Test;

import cn.nukkit.item.enchantment.EnchantmentType;

public class PokkitEnchantmentTargetTest {

	@Test
	public void testBukkitToNukkit() {
		for (EnchantmentTarget target : EnchantmentTarget.values()) {
			assertNotNull("Type " + target + " must have a mapping", PokkitEnchantmentTarget.toNukkit(target));
		}
	}

	@Test
	public void testNukkitToBukkit() {
		for (EnchantmentType type : EnchantmentType.values()) {
			assertNotNull("Type " + type + " must have a mapping", PokkitEnchantmentTarget.toBukkit(type));
		}
	}
}
