package nl.rutgerkok.pokkit.material;

import java.util.EnumMap;
import java.util.Map;

import nl.rutgerkok.pokkit.IntIntMap;

import org.bukkit.Material;
import org.bukkit.material.MaterialData;

import cn.nukkit.block.Block;
import cn.nukkit.item.Item;

/**
 * Class for converting material data between Nukkit and Bukkit. Lots of raw ids
 * here, so lots of {@code @SuppressWarnings("deprecation")}.
 *
 * <p>
 * Technical notes: for Minecraft PC, we use {@code Material.ordinal()} instead
 * of {@link Material#getId()} as the block id, as the ordinals are not
 * deprecated and the material ids are.
 */
public final class PokkitMaterialData {

    private static final int BLOCK_DATA_BITS = 16;
    private static final int BLOCK_DATA_MASK = 0xffff;
    private static final Material[] MATERIAL_VALUES = Material.values();

    private static final Map<Material, Integer> typeBukkitToNukkit;
    private static final Material[] typeNukkitToBukkit;
    private static final IntIntMap combinedIdBukkitToNukkit;
    private static final IntIntMap combinedIdNukkitToBukkit;

    static {
        int highestNukkitMaterialId = Item.GOLDEN_APPLE_ENCHANTED;

        typeBukkitToNukkit = new EnumMap<>(Material.class);
        typeNukkitToBukkit = new Material[highestNukkitMaterialId + 1];

        combinedIdBukkitToNukkit = new IntIntMap(50, 0.5f);
        combinedIdNukkitToBukkit = new IntIntMap(50, 0.5f);

        init();
    }

    private static void addConverter(Material bukkit, int nukkit) {
        if (typeBukkitToNukkit.put(bukkit, nukkit) != null) {
            throw new IllegalArgumentException(bukkit + " was already mapped");
        }
        typeNukkitToBukkit[nukkit] = bukkit;
    }

    private static void addConverter(MaterialData bukkit, Block nukkit) {
        combinedIdBukkitToNukkit.put(toCombinedId(bukkit), toCombinedId(nukkit));
        combinedIdNukkitToBukkit.put(toCombinedId(nukkit), toCombinedId(bukkit));
    }

    @SuppressWarnings("deprecation")
    public static int bukkitToNukkit(Material bukkitType, int bukkitData) {

        // Check combined id map, only if data is in block range
        if (bukkitData >= 0 && bukkitData < 16) {
            int combinedBukkitId = toCombinedId(bukkitType.ordinal(), bukkitData);
            int combinedNukkitId = combinedIdBukkitToNukkit.get(combinedBukkitId);
            if (combinedNukkitId > 0) {
                return combinedNukkitId;
            }
        }

        // Check type map
        Integer nukkitTypeId = typeBukkitToNukkit.get(bukkitType);
        if (nukkitTypeId != null) {
            return toCombinedId(nukkitTypeId, bukkitData);
        }

        // Direct conversion
        return toCombinedId(bukkitType.getId(), bukkitData);
    }

    /**
     * Gets the block data or item damage from the combined block id (from
     * Bukkit or Nukkit, that doesn't matter).
     *
     * @param combinedId
     *            The combined id.
     * @return The block data or item damage.
     */
    public static int getBlockData(int combinedId) {
        return combinedId & BLOCK_DATA_MASK;
    }

    private static int getBlockId(int combinedId) {
        return combinedId >> BLOCK_DATA_BITS;
    }

    /**
     * Gets the material from a combined Bukkit id.
     * 
     * @param combinedBukkitId
     *            The combined Bukkit id, retrieved using for example
     *            {@link #nukkitToBukkit(Item)}.
     * @return The material.
     */
    public static Material getMaterial(int combinedBukkitId) {
        return MATERIAL_VALUES[getBlockId(combinedBukkitId)];
    }

    /**
     * Gets the Nukkit block id from the given combined Nukkit id.
     *
     * @param combinedNukkitId
     *            The Nukkit combined id.
     * @return The Nukkit block id.
     */
    public static int getNukkitBlockId(int combinedNukkitId) {
        // a getBukkitBlockId method with this implementation would return
        // Material.ordinal() instead of Material.getId(), which is probably not
        // the block id that the user expects.
        return getBlockId(combinedNukkitId);
    }

    @SuppressWarnings("deprecation")
    private static void init() {
        addConverter(Material.ACTIVATOR_RAIL, Block.ACTIVATOR_RAIL);
        addConverter(Material.WOOD_STEP, Block.WOOD_SLAB);
        addConverter(Material.WOOD_DOUBLE_STEP, Block.DOUBLE_WOOD_SLAB);
        addConverter(new MaterialData(Material.DIRT, (byte) 2), Block.get(Block.PODZOL));
    }

    /**
     * Gets the combined id for Bukkit.
     *
     * @param nukkit
     *            The Nukkit block.
     * @return The combined id for Bukkit.
     */
    public static int nukkitToBukkit(Block nukkit) {
        return nukkitToBukkit(nukkit.getId(), nukkit.getDamage());
    }

    /**
     * Gets the combined id for Bukkit.
     *
     * @param nukkitId
     *            The Nukkit block/item type.
     * @param nukkitData
     *            The Nukkit block data/item damage.
     * @return The combined id for Bukkit.
     */
    public static int nukkitToBukkit(int nukkitId, int nukkitData) {
        if (nukkitId <= 0 || nukkitId > typeNukkitToBukkit.length) {
            return 0;
        }

        // Check combined id map, only if data is in block range
        if (nukkitData >= 0 && nukkitData < 16) {
            int nukkitCombinedId = toCombinedId(nukkitId, nukkitData);
            int bukkitCombinedId = combinedIdNukkitToBukkit.get(nukkitCombinedId);
            if (bukkitCombinedId > 0) {
                return bukkitCombinedId;
            }
        }

        // Check type map
        Material mapped = typeNukkitToBukkit[nukkitId];
        if (mapped != null) {
            int resultingId = mapped.ordinal();
            return toCombinedId(resultingId, nukkitData);
        }

        // No mapping, return original
        return toCombinedId(nukkitId, nukkitData);
    }

    /**
     * Gets the combined id for Bukkit.
     * 
     * @param nukkit
     *            The nukkit item.
     * @return The combined id for Bukkit.
     */
    public static int nukkitToBukkit(Item nukkit) {
        return nukkitToBukkit(nukkit.getId(), nukkit.getDamage());
    }

    private static int toCombinedId(Block nukkit) {
        return toCombinedId(nukkit.getId(), nukkit.getDamage());
    }

    /**
     * Gets the combined id.
     *
     * @param id
     *            The block id.
     * @param data
     *            The block data, must be between 0 and 15, inclusive.
     * @return The combined id.
     */
    private static int toCombinedId(int id, int data) {
        return (id << BLOCK_DATA_BITS) | data;
    }

    @SuppressWarnings("deprecation")
    private static int toCombinedId(MaterialData materialData) {
        return toCombinedId(materialData.getItemType().ordinal(), materialData.getData());
    }

}
