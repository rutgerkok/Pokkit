package nl.rutgerkok.pokkit.inventory.custom;

import java.util.Objects;

import com.google.common.base.Preconditions;

import cn.nukkit.inventory.Inventory;

/**
 * Simple inventory holder that returns the inventory that created it.
 *
 */
class NukkitCustomInventoryHolder implements cn.nukkit.inventory.InventoryHolder {

	private Inventory inventory = null;

	@Override
	public Inventory getInventory() {
		Preconditions.checkState(inventory != null, "setInventory not yet called");
		return inventory;
	}

	/**
	 * Sets the inventory. Calls this method as soon as possible after construction, so that {@link #getInventory()} doesn't throw.
	 * @param inventory The inventory, may not be null.
	 */
	void setInventory(Inventory inventory) {
		Preconditions.checkState(this.inventory == null, "InventoryHolder must not yet be initialized");
		this.inventory = Objects.requireNonNull(inventory, "inventory");
	}

}
