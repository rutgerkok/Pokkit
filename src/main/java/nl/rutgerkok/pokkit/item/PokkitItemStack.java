package nl.rutgerkok.pokkit.item;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

import cn.nukkit.item.Item;
import cn.nukkit.nbt.tag.CompoundTag;

/**
 * Class for converting Bukkit and Nukkit item stacks.
 *
 */
public final class PokkitItemStack {

	private static final PokkitItemFactory getItemFactory() {
		return (PokkitItemFactory) Bukkit.getItemFactory();
	}

	/**
	 * Overwrites a Nukkit item with the data of a Bukkit item. Useful in case
	 * you want the changes to a Bukkit item show up in a Nukkit item.
	 *
	 * <p>
	 * If the stacks are of a different material, this method print a warning
	 * and do nothing to the item stack.
	 *
	 * @param bukkit
	 *            The Bukkit item.
	 * @param nukkit
	 *            The Nukkit item.
	 */
	public static void overwriteNukkit(ItemStack bukkit, cn.nukkit.item.Item nukkit) {
		cn.nukkit.item.Item nukkitType = ItemMap.fromBukkit(bukkit.getType());

		if (nukkitType.getId() != nukkit.getId()) {
			Bukkit.getLogger().info("Cannot change item material from " + bukkit.getType() + " to "
					+ nukkit);
			return;
		}

		nukkit.setCount(bukkit.getAmount());

		if (bukkit.hasItemMeta()) {
			PokkitItemMeta meta = (PokkitItemMeta) bukkit.getItemMeta();
			if (meta instanceof Damageable) {
				nukkit.setDamage(((Damageable) meta).getDamage());
			}
			nukkit.setNamedTag(meta.getTag());
		} else {
			nukkit.clearNamedTag();
		}
	}

	/**
	 * Creates a Bukkit copy of the item stack. Changes to the returned item stack are not mirrored in the original Nukkit stack.
	 *
	 * @param nukkit
	 *            The Nukkit stack.
	 * @return A {@link ItemStack}, or null if Nukkit has an air or null stack.
	 */
	public static ItemStack toBukkitCopy(cn.nukkit.item.Item nukkit) {
		Material material = ItemMap.fromNukkitOrNull(nukkit);
		if (material == null) {
			return null;
		}
		ItemStack bukkit = new ItemStack(material, nukkit.getCount());

		// Convert item meta
		CompoundTag extra = nukkit.getNamedTag();
		if (extra != null) {
			bukkit.setItemMeta(getItemFactory().getItemMeta(material, extra, nukkit.getDamage()));
		}

		return bukkit;
	}

	public static final cn.nukkit.item.Item toNukkitCopy(ItemStack bukkit) {
		if (bukkit == null) {
			return Item.get(Item.AIR);
		}

		cn.nukkit.item.Item nukkit = ItemMap.fromBukkit(bukkit.getType());
		nukkit.setCount(bukkit.getAmount());

		// Convert item meta
		if (bukkit.hasItemMeta()) {
			PokkitItemMeta meta = (PokkitItemMeta) bukkit.getItemMeta();
			if (meta instanceof Damageable) {
				nukkit.setDamage(((Damageable) meta).getDamage());
			}
			nukkit.setNamedTag(meta.getTag());
		}

		return nukkit;
	}
}
