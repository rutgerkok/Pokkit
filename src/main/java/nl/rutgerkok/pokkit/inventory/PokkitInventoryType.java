package nl.rutgerkok.pokkit.inventory;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import cn.nukkit.inventory.InventoryType;

public final class PokkitInventoryType {

    private static BiMap<InventoryType, org.bukkit.event.inventory.InventoryType> map = HashBiMap.create();
    static {
        map.put(InventoryType.get(InventoryType.ANVIL), org.bukkit.event.inventory.InventoryType.ANVIL);
        map.put(new InventoryType(1, "Beacon", typeOf(InventoryType.ENCHANT_TABLE)),
                org.bukkit.event.inventory.InventoryType.BEACON);
        map.put(InventoryType.get(InventoryType.BREWING_STAND), org.bukkit.event.inventory.InventoryType.BREWING);
        map.put(InventoryType.get(InventoryType.CHEST), org.bukkit.event.inventory.InventoryType.CHEST);
        map.put(InventoryType.get(InventoryType.CRAFTING), org.bukkit.event.inventory.InventoryType.CRAFTING);
        map.put(InventoryType.get(InventoryType.DOUBLE_CHEST), org.bukkit.event.inventory.InventoryType.CHEST);
        map.put(InventoryType.get(InventoryType.PLAYER), org.bukkit.event.inventory.InventoryType.CREATIVE);
        map.put(InventoryType.get(InventoryType.DISPENSER), org.bukkit.event.inventory.InventoryType.DISPENSER);
        map.put(InventoryType.get(InventoryType.DROPPER), org.bukkit.event.inventory.InventoryType.DROPPER);
        map.put(InventoryType.get(InventoryType.ENCHANT_TABLE), org.bukkit.event.inventory.InventoryType.ENCHANTING);
        map.put(new InventoryType(3, "Ender Chest", typeOf(InventoryType.CHEST)),
                org.bukkit.event.inventory.InventoryType.ENDER_CHEST);
        map.put(InventoryType.get(InventoryType.FURNACE), org.bukkit.event.inventory.InventoryType.FURNACE);
        map.put(InventoryType.get(InventoryType.HOPPER), org.bukkit.event.inventory.InventoryType.HOPPER);
        map.put(new InventoryType(3, "Villager", typeOf(InventoryType.ANVIL)),
                org.bukkit.event.inventory.InventoryType.MERCHANT);
        map.put(InventoryType.get(InventoryType.PLAYER), org.bukkit.event.inventory.InventoryType.PLAYER);
        map.put(InventoryType.get(InventoryType.WORKBENCH), org.bukkit.event.inventory.InventoryType.WORKBENCH);
    }

    /**
     * Gets the Bukkit inventory type.
     *
     * @param nukkit
     *            The Nukkit inventory type.
     * @return The Bukkit inventory type.
     */
    public static org.bukkit.event.inventory.InventoryType toBukkit(InventoryType nukkit) {
        return map.get(nukkit);
    }

    /**
     * Gets the Nukkit inventory type.
     *
     * @param inventoryType
     *            The Bukkit inventory type.
     * @return The Nukkit invenotry type.
     */
    public static InventoryType toNukkit(org.bukkit.event.inventory.InventoryType inventoryType) {
        return map.inverse().get(inventoryType);
    }

    private static int typeOf(int inventoryType) {
        return InventoryType.get(inventoryType).getNetworkType();
    }
}
