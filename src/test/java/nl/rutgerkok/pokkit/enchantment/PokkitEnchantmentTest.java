package nl.rutgerkok.pokkit.enchantment;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.bukkit.enchantments.Enchantment;
import org.junit.BeforeClass;
import org.junit.Test;

public class PokkitEnchantmentTest {

	@BeforeClass
	public static void setup() {
		cn.nukkit.item.enchantment.Enchantment.init();
		PokkitEnchantment.registerNukkitEnchantmentsInBukkit();
	}

	@Test
	public void testBukkitToNukkit() {
		int tested = 0;

		for (Enchantment enchantment : Enchantment.values()) {
			assertTrue("Type " + enchantment + " must have a mapping", PokkitEnchantment.toNukkit(enchantment) != -1);
			tested++;
		}

		assertTrue("Didn't test any enchantments", tested > 0);
	}

	@Test
	public void testNukkitToBukkit() {
		for (cn.nukkit.item.enchantment.Enchantment enchantment : cn.nukkit.item.enchantment.Enchantment
				.getEnchantments()) {
			assertNotNull("Type " + enchantment + " must have a mapping", PokkitEnchantment.toBukkit(enchantment.id));
		}
	}
}
