package nl.rutgerkok.pokkit.inventory;

import nl.rutgerkok.pokkit.inventory.custom.NukkitCustomInventory;
import nl.rutgerkok.pokkit.inventory.custom.PokkitCustomInventory;
import nl.rutgerkok.pokkit.player.PokkitPlayer;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public final class PokkitInventory {
	public static Inventory toBukkit(cn.nukkit.inventory.Inventory inventory) {
		// Find/retrieve the correct wrapper
		if (inventory == null) {
			return null;
		}

		// Retrieve the player wrapper
		if (inventory instanceof cn.nukkit.inventory.PlayerInventory) {
			cn.nukkit.Player player = (cn.nukkit.Player) inventory.getHolder();
			Player bukkitPlayer = PokkitPlayer.toBukkit(player);
			return bukkitPlayer.getInventory();
		}

		// Create a wrapper that preserves the original InventoryHolder
		if (inventory instanceof NukkitCustomInventory) {
			return new PokkitCustomInventory((NukkitCustomInventory) inventory);
		}

		// Create a generic wrapper
		return new PokkitLiveInventory(inventory);
	}

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
