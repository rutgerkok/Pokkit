package nl.rutgerkok.pokkit.inventory;

import org.bukkit.inventory.Inventory;

public final class PokkitInventory {
	/**
	 * Attempts to convert the Bukkit inventory to the underlying Nukkit
	 * inventory.
	 *
	 * @param inventory
	 *            The Bukkit inventory.
	 * @return The Nukkit inventory, or null if the Bukkit inventory is null.
	 * @throws IllegalArgumentException
	 *             If the Bukkit inventory is not backed by a Nukkit inventory.
	 */
	public static cn.nukkit.inventory.Inventory toNukkit(Inventory inventory) {
		if (inventory == null) {
			return null;
		}
		if (inventory instanceof PokkitLiveInventory) {
			return ((PokkitLiveInventory) inventory).nukkit;
		}
		throw new IllegalArgumentException(
				"Cannot convert inventory " + inventory.getClass() + " to a Nukkit inventory");
	}
}
