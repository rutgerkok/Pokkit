package nl.rutgerkok.pokkit.world.item;

import nl.rutgerkok.pokkit.material.PokkitMaterialData;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

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
	 * Creates a Bukkit copy of the item stack.
	 *
	 * @param nukkit
	 *            The Nukkit stack.
	 * @return A {@link ItemStack}, or null if Nukkit has an air or null stack.
	 */
	public static ItemStack toBukkitCopy(cn.nukkit.item.Item nukkit) {
		if (nukkit == null) {
			return null;
		}
		PokkitMaterialData materialData = PokkitMaterialData.fromNukkit(nukkit.getId(), nukkit.getDamage());
		Material material = materialData.getBukkitMaterial();
		if (material == null) {
			return null;
		}
		ItemStack bukkit = new ItemStack(material, nukkit.getCount(),
				materialData.getBukkitDamage());

		// Convert item meta
		CompoundTag extra = nukkit.getNamedTag();
		if (extra != null) {
			bukkit.setItemMeta(getItemFactory().getItemMeta(material, extra));
		}

		return bukkit;
	}

	public static final cn.nukkit.item.Item toNukkitCopy(ItemStack bukkit) {
		if (bukkit == null) {
			return null;
		}
		PokkitMaterialData materialData = PokkitMaterialData.fromBukkit(bukkit.getType(), bukkit.getDurability());
		cn.nukkit.item.Item nukkit = Item.get(materialData.getNukkitId(), materialData.getNukkitDamage(),
				bukkit.getAmount());

		if (bukkit.hasItemMeta()) {
			PokkitItemMeta meta = (PokkitItemMeta) bukkit.getItemMeta();
			nukkit.setNamedTag(meta.getTag());
		}

		// For the future, we'll want to support item meta, like names,
		// enchantments, etc.
		return nukkit;
	}
}
