package nl.rutgerkok.pokkit.inventory.custom;

import nl.rutgerkok.pokkit.Pokkit;
import nl.rutgerkok.pokkit.inventory.PokkitLiveInventory;

import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

/**
 * Class for creating custom inventories. This class implements the Bukkit API
 * {@link Inventory} and acts as a wrapper around {@ink NukkitCustomInventory}.
 *
 */
public final class PokkitCustomInventory extends PokkitLiveInventory implements Inventory {

	/**
	 * Creates a new custom inventory.
	 *
	 * @param holderOrNull
	 *            Holder of the inventory, may be null.
	 * @param type
	 *            Type of the inventory (only {@link InventoryType#CHEST} and
	 *            {@link InventoryType#ENDER_CHEST} are permitted for now).
	 * @param title
	 *            The title, may not be null.
	 * @param size
	 *            The size of the inventory, must be positive.
	 * @return The inventory.
	 */
	public static Inventory create(InventoryHolder holderOrNull, InventoryType type, String title, int size) {
		if (type != InventoryType.CHEST && type != InventoryType.ENDER_CHEST) {
			throw new UnsupportedOperationException(
					Pokkit.NAME + " doesn't support custom inventories of type " + type);
		}
		if (size < 1) {
			throw new IllegalArgumentException("Invalid inventory size: " + size);
		}

		NukkitCustomInventory nukkit = new NukkitCustomInventory(title, holderOrNull);
		nukkit.setSize(size);

		return new PokkitCustomInventory(nukkit);
	}

	public PokkitCustomInventory(NukkitCustomInventory inventory) {
		super(inventory);
	}

	@Override
	public InventoryHolder getHolder() {
		return ((NukkitCustomInventory) nukkit).getHolder().bukkitHolderOrNull;
	}

}
