package nl.rutgerkok.pokkit.material;

import org.bukkit.inventory.ItemStack;

import cn.nukkit.item.Item;

/**
 * Class for converting Bukkit and Nukkit item stacks.
 *
 */
public final class PokkitItemStack {

    public static ItemStack toBukkitCopy(cn.nukkit.item.Item nukkit) {
        if (nukkit == null) {
            return null;
        }
        int combinedBukkitId = PokkitMaterialData.nukkitToBukkit(nukkit);
        ItemStack bukkit = new ItemStack(PokkitMaterialData.getMaterial(combinedBukkitId),
                nukkit.getCount(), (short) PokkitMaterialData.getBlockData(combinedBukkitId));

        return bukkit;
    }

    public static final cn.nukkit.item.Item toNukkitCopy(ItemStack bukkit) {
        if (bukkit == null) {
            return null;
        }
        int combinedNukkitId = PokkitMaterialData.bukkitToNukkit(bukkit.getType(), bukkit.getDurability());
        cn.nukkit.item.Item nukkit = Item.get(PokkitMaterialData.getNukkitBlockId(combinedNukkitId),
                PokkitMaterialData.getBlockData(combinedNukkitId), bukkit.getAmount());

        // For the future, we'll want to support item meta, like names,
        // enchantments, etc.
        return nukkit;
    }
}
