package nl.rutgerkok.pokkit.inventory;

import static org.junit.Assert.assertNotNull;

import org.bukkit.event.inventory.InventoryType;
import org.junit.Test;

public class InventoryTypeTest {

	@Test
	public void testBukkitToNukkit() {
		for (InventoryType type : InventoryType.values()) {
			assertNotNull("Type " + type + " must have a mapping", PokkitInventoryType.toNukkit(type));
		}
	}

	@Test
	public void testNukkitToBukkit() {
		for (cn.nukkit.inventory.InventoryType type : cn.nukkit.inventory.InventoryType.values()) {
			assertNotNull("Type " + type + " must have a mapping", PokkitInventoryType.toBukkit(type));
		}
	}
}
