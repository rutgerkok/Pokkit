package nl.rutgerkok.pokkit;

import java.util.List;

import nl.rutgerkok.pokkit.item.PokkitItemMeta;
import nl.rutgerkok.pokkit.material.PokkitMaterialData;

import org.bukkit.Achievement;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Statistic;
import org.bukkit.UnsafeValues;
import org.bukkit.advancement.Advancement;
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

		return PokkitMaterialData.fromNukkit(item.getId(), item.getDamage()).getBukkitMaterial();
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
	public boolean removeAdvancement(NamespacedKey key) {
		throw Pokkit.unsupported();
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
