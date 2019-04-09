package nl.rutgerkok.pokkit;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.UnsafeValues;
import org.bukkit.advancement.Advancement;
import org.bukkit.block.data.BlockData;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.PluginDescriptionFile;

import nl.rutgerkok.pokkit.blockdata.PokkitBlockData;
import nl.rutgerkok.pokkit.item.PokkitItemMeta;

@SuppressWarnings("deprecation")
public class PokkitUnsafe implements UnsafeValues {

	@Override
	public void checkSupported(PluginDescriptionFile pdf) {
		// Empty!
	}

	@Override
	public Material fromLegacy(Material material) {
		return material;
	}

	@Override
	public BlockData fromLegacy(Material material, byte data) {
		return PokkitBlockData.createBlockData(material, data);
	}

	@Override
	public Material fromLegacy(MaterialData material) {
		return material.getItemType();
	}

	@Override
	public Material fromLegacy(MaterialData material, boolean itemPriority) {
		return material.getItemType();
	}

	@Override
	public int getDataVersion() {
		return 1;
	}

	@Override
	public Advancement loadAdvancement(NamespacedKey key, String advancementJson) {
		throw Pokkit.unsupported();
	}

	@Override
	public ItemStack modifyItemStack(ItemStack stack, String arguments) {
		// NBT not yet supported
		PokkitItemMeta itemMeta = (PokkitItemMeta) stack.getItemMeta();
		itemMeta.getTag().putString("Unknown", arguments);
		stack.setItemMeta(itemMeta);
		return stack;
	}

	@Override
	public byte[] processClass(PluginDescriptionFile pdf, String path, byte[] clazz) {
		return clazz; // Not implemented yet
	}

	@Override
	public boolean removeAdvancement(NamespacedKey key) {
		throw Pokkit.unsupported();
	}

	@Override
	public Material toLegacy(Material material) {
		return material;
	}

}
