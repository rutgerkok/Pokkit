package nl.rutgerkok.pokkit.material;

import org.bukkit.Material;
import org.bukkit.material.MaterialData;

/**
 * Class for converting material data between Nukkit and Bukkit. Lots of raw ids
 * here, so lots of {@code @SuppressWarnings("deprecation")}.
 *
 * <p>
 * Currently, no conversion happens at all between Bukkit and Nukkit ids (it is
 * assumed that they are equal). However, the design of the API permits to add
 * conversion in the future.
 */
public final class PokkitMaterialData {

	/**
	 * Creates a universal material object from a Bukkit material.
	 *
	 * @param material
	 *            The Bukkit material.
	 * @param bukkitDamage
	 *            The Bukkit damage/block data.
	 * @return The universal material object.
	 */
	public static PokkitMaterialData fromBukkit(Material material, int bukkitDamage) {
		@SuppressWarnings("deprecation")
		int bukkitId = material.getId();
		return new PokkitMaterialData(bukkitId, bukkitDamage);
	}

	/**
	 * Creates a universal material object from a Bukkit material.
	 *
	 * @param materialData
	 *            The Bukkit material and data.
	 * @return The universal material object.
	 */
	@SuppressWarnings("deprecation")
	public static PokkitMaterialData fromBukkit(MaterialData materialData) {
		return fromBukkit(materialData.getItemType(), materialData.getData());
	}

	/**
	 * Creates a universal material object from a Nukkit material.
	 *
	 * @param nukkitId
	 *            The Nukkit material.
	 * @param nukkitDamage
	 *            The Nukkit damage/block data.
	 * @return The universal material object.
	 */
	@Deprecated
	public static PokkitMaterialData fromNukkit(int nukkitId, int nukkitDamage) {
		return new PokkitMaterialData(nukkitId, nukkitDamage);
	}
	
	/**
	 * Creates a universal material object from a Nukkit item.
	 *
	 * @param nukkitId
	 *            The Nukkit material.
	 * @param nukkitDamage
	 *            The Nukkit damage/block data.
	 * @return The universal material object.
	 */
	public static PokkitMaterialData fromNukkit(cn.nukkit.item.Item item) {
		return new PokkitMaterialData(item.getId(), item.getDamage());
	}

	private final int id;
	private final int data;

	private PokkitMaterialData(int id, int data) {
		this.id = id;
		this.data = data;
	}

	/**
	 * Gets the damage value (block data) for use in Bukkit.
	 * @return The Bukkit damage value.
	 */
	public short getBukkitDamage() {
		return (short) data;
	}

	/**
	 * Gets the material for use in Bukkit.
	 * @return The material.
	 */
	@SuppressWarnings("deprecation")
	public Material getBukkitMaterial() {
		return Material.getMaterial(id);
	}

	/**
	 * Gets the damage value (block data) for use in Nukkit.
	 * @return The Nukkit damage value.
	 */
	public int getNukkitDamage() {
		return data;
	}

	/**
	 * Gets the material id for use in Nukkit.
	 * @return The material id.
	 */
	public int getNukkitId() {
		return id;
	}

	/**
	 * Converts this instance to a Bukkit {@link MaterialData} instance.
	 * @return The Bukkit {@link MaterialData} instance.
	 */
	@SuppressWarnings("deprecation")
	public MaterialData toBukkit() {
		return getBukkitMaterial().getNewData((byte) getBukkitDamage());
	}

}
