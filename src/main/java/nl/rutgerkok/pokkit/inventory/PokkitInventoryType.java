package nl.rutgerkok.pokkit.inventory;

import java.util.EnumMap;
import java.util.Map;

import org.bukkit.event.inventory.InventoryType;

public final class PokkitInventoryType {

	private static Map<cn.nukkit.inventory.InventoryType, InventoryType> nukkitToBukkit = new EnumMap<>(
			cn.nukkit.inventory.InventoryType.class);
	private static Map<InventoryType, cn.nukkit.inventory.InventoryType> bukkitToNukkit = new EnumMap<>(
			InventoryType.class);

	static {
		twoWay(cn.nukkit.inventory.InventoryType.ANVIL, InventoryType.ANVIL);
		twoWay(cn.nukkit.inventory.InventoryType.BREWING_STAND, InventoryType.BREWING);
		twoWay(cn.nukkit.inventory.InventoryType.CHEST, InventoryType.CHEST);
		twoWay(cn.nukkit.inventory.InventoryType.CRAFTING, InventoryType.CRAFTING);
		twoWay(cn.nukkit.inventory.InventoryType.DOUBLE_CHEST, InventoryType.CHEST);
		twoWay(cn.nukkit.inventory.InventoryType.DISPENSER, InventoryType.DISPENSER);
		twoWay(cn.nukkit.inventory.InventoryType.DROPPER, InventoryType.DROPPER);
		twoWay(cn.nukkit.inventory.InventoryType.ENCHANT_TABLE, InventoryType.ENCHANTING);
		twoWay(cn.nukkit.inventory.InventoryType.FURNACE, InventoryType.FURNACE);
		twoWay(cn.nukkit.inventory.InventoryType.HOPPER, InventoryType.HOPPER);
		twoWay(cn.nukkit.inventory.InventoryType.PLAYER, InventoryType.PLAYER);
		twoWay(cn.nukkit.inventory.InventoryType.WORKBENCH, InventoryType.WORKBENCH);
		twoWay(cn.nukkit.inventory.InventoryType.ENDER_CHEST, InventoryType.ENDER_CHEST);
		twoWay(cn.nukkit.inventory.InventoryType.SHULKER_BOX, InventoryType.SHULKER_BOX);
		twoWay(cn.nukkit.inventory.InventoryType.BEACON, InventoryType.BEACON);

		bukkitToNukkit.put(InventoryType.MERCHANT, cn.nukkit.inventory.InventoryType.ANVIL);
		bukkitToNukkit.put(InventoryType.CREATIVE, cn.nukkit.inventory.InventoryType.PLAYER);

		nukkitToBukkit.put(cn.nukkit.inventory.InventoryType.CURSOR, InventoryType.PLAYER); // TODO wrong
	}

	/**
	 * Gets the Bukkit inventory type.
	 *
	 * @param nukkit
	 *            The Nukkit inventory type.
	 * @return The Bukkit inventory type.
	 */
	public static InventoryType toBukkit(cn.nukkit.inventory.InventoryType nukkit) {
		return nukkitToBukkit.get(nukkit);
	}

	/**
	 * Gets the Nukkit inventory type.
	 *
	 * @param inventoryType
	 *            The Bukkit inventory type.
	 * @return The Nukkit invenotry type.
	 */
	public static cn.nukkit.inventory.InventoryType toNukkit(InventoryType inventoryType) {
		return bukkitToNukkit.get(inventoryType);
	}

	private static void twoWay(cn.nukkit.inventory.InventoryType nukkit, InventoryType bukkit) {
		nukkitToBukkit.put(nukkit, bukkit);
		bukkitToNukkit.put(bukkit, nukkit);
	}
}
