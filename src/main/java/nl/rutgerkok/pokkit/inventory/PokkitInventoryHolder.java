package nl.rutgerkok.pokkit.inventory;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class PokkitInventoryHolder implements InventoryHolder {
    cn.nukkit.inventory.InventoryHolder nukkit;
    
    public PokkitInventoryHolder(cn.nukkit.inventory.InventoryHolder nukkit) {
        this.nukkit = nukkit;
    }
    
	public static InventoryHolder toBukkit(cn.nukkit.inventory.InventoryHolder holder) {
		return new PokkitInventoryHolder(holder);
	}

    @Override
    public Inventory getInventory() {
        return PokkitInventory.toBukkit(nukkit.getInventory());
    }

}
