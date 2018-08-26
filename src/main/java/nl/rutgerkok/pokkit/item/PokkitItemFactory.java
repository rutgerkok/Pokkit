package nl.rutgerkok.pokkit.item;

import java.util.Objects;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import nl.rutgerkok.pokkit.Pokkit;

import cn.nukkit.nbt.tag.CompoundTag;

public final class PokkitItemFactory implements ItemFactory {

	@Override
	public ItemMeta asMetaFor(ItemMeta meta, ItemStack stack) throws IllegalArgumentException {
		throw Pokkit.unsupported();
	}

	@Override
	public ItemMeta asMetaFor(ItemMeta meta, Material material) throws IllegalArgumentException {
		return getItemMeta(material, ((PokkitItemMeta) meta).getTag(), 0);
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
		return getItemMeta(material, new CompoundTag(), 0);
	}

	@SuppressWarnings("deprecation")
	public ItemMeta getItemMeta(Material material, CompoundTag tag, int damage) {
		switch (material) {
		case POTION:
		case LEGACY_POTION:
		case SPLASH_POTION:
		case LEGACY_SPLASH_POTION:
		case LINGERING_POTION:
		case LEGACY_LINGERING_POTION:
		case TIPPED_ARROW:
		case LEGACY_TIPPED_ARROW:
			return new PokkitPotionMeta(tag, damage);
		case SIGN:
		case LEGACY_SIGN:
		case BLACK_BANNER:
		case BLUE_BANNER:
		case BROWN_BANNER:
		case CYAN_BANNER:
		case GRAY_BANNER:
		case GREEN_BANNER:
		case LIGHT_BLUE_BANNER:
		case LIGHT_GRAY_BANNER:
		case LIME_BANNER:
		case MAGENTA_BANNER:
		case ORANGE_BANNER:
		case PINK_BANNER:
		case PURPLE_BANNER:
		case RED_BANNER:
		case WHITE_BANNER:
		case YELLOW_BANNER:
		case LEGACY_BANNER:
		case CHEST:
		case LEGACY_CHEST:
		case TRAPPED_CHEST:
		case LEGACY_TRAPPED_CHEST:
		case DISPENSER:
		case LEGACY_DISPENSER:
		case FURNACE:
		case LEGACY_FURNACE:
		case BREWING_STAND:
		case LEGACY_BREWING_STAND:
		case HOPPER:
		case LEGACY_HOPPER:
		case DROPPER:
		case LEGACY_DROPPER:
		case BEACON:
		case LEGACY_BEACON:
		case SPAWNER:
		case LEGACY_MOB_SPAWNER:
		case NOTE_BLOCK:
		case LEGACY_NOTE_BLOCK:
		case JUKEBOX:
		case LEGACY_JUKEBOX:
		case ENCHANTING_TABLE:
		case LEGACY_ENCHANTMENT_TABLE:
		case SKELETON_SKULL:
		case WITHER_SKELETON_SKULL:
		case ZOMBIE_HEAD:
		case CREEPER_HEAD:
		case PLAYER_HEAD:
		case LEGACY_SKULL_ITEM:
		case COMMAND_BLOCK:
		case LEGACY_COMMAND:
		case REPEATING_COMMAND_BLOCK:
		case LEGACY_COMMAND_REPEATING:
		case CHAIN_COMMAND_BLOCK:
		case LEGACY_COMMAND_CHAIN:
		case END_GATEWAY:
		case LEGACY_END_GATEWAY:
		case STRUCTURE_BLOCK:
		case LEGACY_STRUCTURE_BLOCK:
		case FLOWER_POT:
		case LEGACY_FLOWER_POT:
		case COMPARATOR:
		case LEGACY_REDSTONE_COMPARATOR:
			return new PokkitBlockStateMeta(tag, material, damage);
		case LEATHER_BOOTS:
		case LEGACY_LEATHER_BOOTS:
		case LEATHER_CHESTPLATE:
		case LEGACY_LEATHER_CHESTPLATE:
		case LEATHER_HELMET:
		case LEGACY_LEATHER_HELMET:
		case LEATHER_LEGGINGS:
		case LEGACY_LEATHER_LEGGINGS:
			return new PokkitLeatherArmorMeta(tag, damage);
		default:
			return new PokkitItemMeta(tag, damage);
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

	@Override
	public Material updateMaterial(ItemMeta meta, Material material) throws IllegalArgumentException {
		// Normally used to change the material, for example when the spawned
		// type of a spawn egg has changed
		return material;
	}

}
