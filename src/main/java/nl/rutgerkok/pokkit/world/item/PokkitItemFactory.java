package nl.rutgerkok.pokkit.world.item;

import java.util.Objects;

import nl.rutgerkok.pokkit.Pokkit;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import cn.nukkit.nbt.tag.CompoundTag;

public final class PokkitItemFactory implements ItemFactory {

	@Override
	public ItemMeta asMetaFor(ItemMeta meta, ItemStack stack) throws IllegalArgumentException {
		throw Pokkit.unsupported();
	}

	@Override
	public ItemMeta asMetaFor(ItemMeta meta, Material material) throws IllegalArgumentException {
		return getItemMeta(material, ((PokkitItemMeta) meta).getTag());
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * Unlike what you would expect, this method is not a wrapper for
	 * {@link Objects#equals(Object, Object)}. Bukkit has a strange
	 * implementation: a meta equals null if it is empty. We have to copy this
	 * behaviour, for example because {@link ItemStack#hasItemMeta()} depends on
	 * it.
	 */
	@Override
	public boolean equals(ItemMeta meta1, ItemMeta meta2) throws IllegalArgumentException {

		if (meta1 == null && meta2 != null) {
			return isEqualToNull(meta2);
		}
		if (meta1 != null && meta2 == null) {
			return isEqualToNull(meta1);
		}
		return Objects.equals(meta1, meta2);
	}

	@Override
	public Color getDefaultLeatherColor() {
		throw Pokkit.unsupported();
	}

	@Override
	public ItemMeta getItemMeta(Material material) {
		return getItemMeta(material, new CompoundTag());
	}

	public ItemMeta getItemMeta(Material material, CompoundTag tag) {
		switch (material) {
		case POTION:
		case SPLASH_POTION:
		case LINGERING_POTION:
		case TIPPED_ARROW:
			return new PokkitPotionMeta(tag);
		case SIGN:
		case BANNER:
		case CHEST:
		case TRAPPED_CHEST:
		case DISPENSER:
		case FURNACE:
		case BREWING_STAND:
		case HOPPER:
		case DROPPER:
		case BEACON:
		case MOB_SPAWNER:
		case NOTE_BLOCK:
		case JUKEBOX:
		case ENCHANTMENT_TABLE:
		case SKULL_ITEM:
		case COMMAND:
		case COMMAND_REPEATING:
		case COMMAND_CHAIN:
		case END_GATEWAY:
		case STRUCTURE_BLOCK:
		case FLOWER_POT:
		case REDSTONE_COMPARATOR:
			return new PokkitBlockStateMeta(tag, material);
		case LEATHER_BOOTS:
		case LEATHER_CHESTPLATE:
		case LEATHER_HELMET:
		case LEATHER_LEGGINGS:
			return new PokkitLeatherArmorMeta(tag);
		default:
			return new PokkitItemMeta(tag);
		}
	}

	@Override
	public boolean isApplicable(ItemMeta meta, ItemStack stack) throws IllegalArgumentException {
		return isApplicable(meta, stack.getType());
	}

	@Override
	public boolean isApplicable(ItemMeta meta, Material material) throws IllegalArgumentException {
		return ((PokkitItemMeta) meta).isApplicable(material);
	}

	private boolean isEqualToNull(ItemMeta meta) {
		return ((PokkitItemMeta) meta).getTag().isEmpty();
	}

}
