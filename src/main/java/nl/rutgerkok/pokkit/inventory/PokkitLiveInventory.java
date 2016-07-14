package nl.rutgerkok.pokkit.inventory;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import nl.rutgerkok.pokkit.Pokkit;
import nl.rutgerkok.pokkit.material.PokkitMaterialData;
import nl.rutgerkok.pokkit.player.PokkitPlayer;
import nl.rutgerkok.pokkit.world.item.PokkitItemStack;

import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import cn.nukkit.item.Item;

/**
 * Inventory implementation that forwards changes directly to a Nukkit
 * inventory.
 *
 * <p>
 * Note: this implementation is not to spec. inventory.getItem(i).setAmount(j)
 * doesn't update the inventory yet.
 */
public class PokkitLiveInventory extends PokkitAbstractInventory {

    private cn.nukkit.inventory.Inventory nukkit;

    public PokkitLiveInventory(cn.nukkit.inventory.Inventory inventory) {
        this.nukkit = Objects.requireNonNull(inventory, "inventory");
    }

    @Override
    protected int addSingleStack(ItemStack item) {
        Item nukkitItem = PokkitItemStack.toNukkitCopy(item);
        int remaining = nukkitItem.count;

        // Try to disperse over the existing stacks first
        int size = getSize();
        for (int i = 0; i < size; i++) {
            Item atPosition = nukkit.getItem(i);
            if (!atPosition.equals(nukkitItem)) {
                // Another stack type
                continue;
            }

            int maxStackSize = Math.min(atPosition.getMaxStackSize(), this.getMaxStackSize());
            if (atPosition.count >= maxStackSize) {
                // Already full
                continue;
            }

            int transferAmount = Math.min(atPosition.count - maxStackSize, remaining);
            remaining -= transferAmount;
            atPosition.count += transferAmount;

            if (remaining == 0) {
                return 0;
            }
        }

        // Try to disperse over empty slots
        for (int i = 0; i < size; i++) {
            Item atPosition = nukkit.getItem(i);
            if (atPosition.getId() != Item.AIR) {
                continue;
            }

            int transferAmount = Math.min(getMaxStackSize(), remaining);
            remaining -= transferAmount;
            Item newItem = nukkitItem.clone();
            newItem.count = transferAmount;
            nukkit.setItem(i, newItem);

            if (remaining == 0) {
                return 0;
            }
        }

        return remaining;
    }

    @Override
    public HashMap<Integer, ? extends ItemStack> all(ItemStack item) {
        throw Pokkit.unsupported();
    }

    @Override
    public HashMap<Integer, ? extends ItemStack> all(Material material) throws IllegalArgumentException {
        throw Pokkit.unsupported();
    }

    @Override
    public void clear() {
        nukkit.clearAll();
    }

    @Override
    public void clear(int index) {
        nukkit.clear(index);
    }

    @Override
    public boolean contains(ItemStack item) {
        if (item == null) {
            return false;
        }

        Item nukkitItem = PokkitItemStack.toNukkitCopy(item);

        int size = getSize();
        for (int i = 0; i < size; i++) {
            Item atPosition = nukkit.getItem(i);
            if (exactMatch(nukkitItem, atPosition)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(ItemStack item, int amount) {
        if (item == null) {
            return false;
        }
        if (amount < 1) {
            return true;
        }
        int remaining = amount;

        Item nukkitItem = PokkitItemStack.toNukkitCopy(item);

        int size = getSize();
        for (int i = 0; i < size; i++) {
            Item atPosition = nukkit.getItem(i);
            if (exactMatch(nukkitItem, atPosition)) {
                remaining--;
                if (remaining <= 0) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean contains(Material material, int amount) throws IllegalArgumentException {
        Validate.notNull(material, "material");
        int nukkitTypeId = PokkitMaterialData.getNukkitBlockId(PokkitMaterialData.bukkitToNukkit(material, 0));

        int remaining = amount;

        int size = getSize();
        for (int i = 0; i < size; i++) {
            Item atPosition = nukkit.getItem(i);
            if (atPosition.getId() == nukkitTypeId) {
                remaining -= atPosition.getCount();
                if (remaining <= 0) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean containsAtLeast(ItemStack item, int amount) {
        if (item == null || amount < 1) {
            return false;
        }
        Item nukkitItem = PokkitItemStack.toNukkitCopy(item);
        nukkitItem.count = amount;
        return nukkit.contains(nukkitItem);
    }

    private boolean exactMatch(Item first, Item second) {
        return first.count == second.count && first.equals(second);
    }

    @Override
    public int first(ItemStack item) {
        if (item == null) {
            return -1;
        }
        return nukkit.first(PokkitItemStack.toNukkitCopy(item));
    }

    @Override
    public int first(Material material) throws IllegalArgumentException {
        Validate.notNull(material, "material");
        int nukkitTypeId = PokkitMaterialData.getNukkitBlockId(PokkitMaterialData.bukkitToNukkit(material, 0));

        int size = getSize();
        for (int i = 0; i < size; i++) {
            Item atPosition = nukkit.getItem(i);
            if (atPosition.getId() == nukkitTypeId) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int firstEmpty() {
        return nukkit.firstEmpty(Item.get(Item.AIR));
    }

    @Override
    public ItemStack[] getContents() {
        ItemStack[] contents = new ItemStack[getSize()];
        nukkit.getContents().forEach((slot, item) -> contents[slot] = PokkitItemStack.toBukkitCopy(item));
        return contents;
    }

    @Override
    public InventoryHolder getHolder() {
        throw Pokkit.unsupported();
    }

    @Override
    public ItemStack getItem(int index) {
        return PokkitItemStack.toBukkitCopy(nukkit.getItem(index));
    }

    @Override
    public int getMaxStackSize() {
        return nukkit.getMaxStackSize();
    }

    @Override
    public String getName() {
        return nukkit.getName();
    }

    @Override
    public int getSize() {
        return nukkit.getSize();
    }

    @Override
    public String getTitle() {
        return nukkit.getTitle();
    }

    @Override
    public InventoryType getType() {
        return PokkitInventoryType.toBukkit(nukkit.getType());
    }

    @Override
    public List<HumanEntity> getViewers() {
        return nukkit.getViewers()
                .stream()
                .map(PokkitPlayer::toBukkit)
                .collect(Collectors.toList());
    }

    @Override
    public void remove(ItemStack item) {
        throw Pokkit.unsupported();
    }

    @Override
    public void remove(Material material) throws IllegalArgumentException {
        throw Pokkit.unsupported();
    }

    @Override
    public HashMap<Integer, ItemStack> removeItem(ItemStack... items) throws IllegalArgumentException {
        throw Pokkit.unsupported();
    }

    @Override
    public void setItem(int index, ItemStack item) {
        nukkit.setItem(index, PokkitItemStack.toNukkitCopy(item));
    }

    @Override
    public void setMaxStackSize(int size) {
        nukkit.setMaxStackSize(size);
    }
}
