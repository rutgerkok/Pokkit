package nl.rutgerkok.pokkit.inventory.custom;

import java.util.Objects;

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

	public static Inventory create(InventoryHolder holder, InventoryType type, String title, int size) {
		if (type != InventoryType.CHEST && type != InventoryType.ENDER_CHEST) {
			throw new UnsupportedOperationException(
					Pokkit.NAME + " doesn't support custom inventories of type " + type);
		}
		if (size < 1) {
			throw new IllegalArgumentException("Invalid inventory size: " + size);
		}

		NukkitCustomInventory nukkit = new NukkitCustomInventory(title);
		nukkit.setSize(size);

		return new PokkitCustomInventory(nukkit, holder);
	}

	private final InventoryHolder originalHolder;

	private PokkitCustomInventory(cn.nukkit.inventory.Inventory inventory, InventoryHolder holder) {
		super(inventory);
		this.originalHolder = Objects.requireNonNull(holder, "holder");
	}

	@Override
	public InventoryHolder getHolder() {
		return originalHolder;
	}

}
