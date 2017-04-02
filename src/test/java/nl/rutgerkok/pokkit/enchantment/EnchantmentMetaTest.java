package nl.rutgerkok.pokkit.enchantment;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import nl.rutgerkok.pokkit.world.item.PokkitItemFactory;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.ItemMeta;
import org.junit.BeforeClass;
import org.junit.Test;

public class EnchantmentMetaTest {

	@BeforeClass
	public static void setup() {
		cn.nukkit.item.enchantment.Enchantment.init();
		PokkitEnchantment.registerNukkitEnchantmentsInBukkit();
	}

	private final PokkitItemFactory factory = new PokkitItemFactory();

	@Test
	public void testItemMeta() {
		ItemMeta itemMeta = factory.getItemMeta(Material.DIAMOND_PICKAXE);

		// Test initial state
		assertFalse(itemMeta.hasEnchants());
		assertFalse(itemMeta.hasEnchant(Enchantment.DURABILITY));

		// Add an enchantment, test state
		assertTrue(itemMeta.addEnchant(Enchantment.DURABILITY, 1, true));
		assertTrue(itemMeta.hasEnchants());
		assertTrue(itemMeta.hasEnchant(Enchantment.DURABILITY));
		assertFalse(itemMeta.hasEnchant(Enchantment.FIRE_ASPECT));

		// Remove it again, test state
		assertTrue(itemMeta.removeEnchant(Enchantment.DURABILITY));
		assertFalse("Removing twice", itemMeta.removeEnchant(Enchantment.DURABILITY));
		assertFalse(itemMeta.hasEnchants());
		assertFalse(itemMeta.hasEnchant(Enchantment.DURABILITY));
	}
}
