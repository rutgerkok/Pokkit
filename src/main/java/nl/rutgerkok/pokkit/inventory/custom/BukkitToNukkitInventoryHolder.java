package nl.rutgerkok.pokkit.inventory.custom;

import java.util.Objects;

import org.bukkit.inventory.InventoryHolder;

import com.google.common.base.Preconditions;

import cn.nukkit.inventory.Inventory;

/**
 * Simple inventory holder that wraps a Bukkit {@link InventoryHolder}.
 * {@link #getInventory()} simply returns the inventory specified by
 * {@link #setInventory(Inventory)}.
 *
 */
public final class BukkitToNukkitInventoryHolder implements cn.nukkit.inventory.InventoryHolder {

	private cn.nukkit.inventory.Inventory inventory = null;
	public final InventoryHolder bukkitHolderOrNull;

	public BukkitToNukkitInventoryHolder(InventoryHolder bukkitHolder) {
		this.bukkitHolderOrNull = bukkitHolder;
	}

	@Override
	public cn.nukkit.inventory.Inventory getInventory() {
		Preconditions.checkState(inventory != null, "setInventory not yet called");
		return inventory;
	}

	/**
	 * Sets the inventory. Calls this method as soon as possible after construction, so that {@link #getInventory()} doesn't throw.
	 * @param inventory The inventory, may not be null.
	 */
	public void setInventory(Inventory inventory) {
		Preconditions.checkState(this.inventory == null, "setInventory was called earlier");
		this.inventory = Objects.requireNonNull(inventory, "inventory");
	}

}
