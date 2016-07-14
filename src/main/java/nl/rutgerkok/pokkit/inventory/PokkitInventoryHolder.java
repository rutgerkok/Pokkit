package nl.rutgerkok.pokkit.inventory;

import nl.rutgerkok.pokkit.Pokkit;

import org.bukkit.inventory.InventoryHolder;

public class PokkitInventoryHolder {

    public static InventoryHolder toBukkit(cn.nukkit.inventory.InventoryHolder holder) {
        throw Pokkit.unsupported();
    }

}
