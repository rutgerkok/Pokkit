package nl.rutgerkok.pokkit;

import java.util.List;

import nl.rutgerkok.pokkit.material.PokkitMaterialData;
import nl.rutgerkok.pokkit.world.item.PokkitItemMeta;

import org.bukkit.Achievement;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.UnsafeValues;
import org.bukkit.inventory.ItemStack;

import cn.nukkit.item.Item;

@SuppressWarnings("deprecation")
public class PokkitUnsafe implements UnsafeValues {

	@Override
	public Achievement getAchievementFromInternalName(String name) {
		try {
			return Achievement.valueOf(name.toUpperCase().replace("MINECRAFT:", ""));
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	@Override
	public Material getMaterialFromInternalName(String name) {
		Item item = Item.fromString(name);

		if (item.getId() == Item.AIR && !name.toLowerCase().endsWith("air")) {
			// Nukkit returns air in case of failure, correct that
			return null;
		}

		return PokkitMaterialData.getMaterial(PokkitMaterialData.nukkitToBukkit(item));
	}

	@Override
	public Statistic getStatisticFromInternalName(String name) {
		try {
			return Statistic.valueOf(name.toUpperCase().replace("MINECRAFT:", ""));
		} catch (IllegalArgumentException e) {
			return null;
		}
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
	public List<String> tabCompleteInternalMaterialName(String token, List<String> completions) {
		return completions;
	}

	@Override
	public List<String> tabCompleteInternalStatisticOrAchievementName(String token, List<String> completions) {
		return completions;
	}

}
