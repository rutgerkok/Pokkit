package nl.rutgerkok.pokkit.inventory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.ListIterator;

import com.google.common.base.Preconditions;

import org.apache.commons.lang.Validate;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.DoubleChest;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

/**
 * Abstract class for inventories, implements all methods that can be
 * implemented on top of others without noticeable permformance loss. (I mean,
 * theoretically we could implement almost everything on top of
 * {@link #getContents()} and {@link #setContents(ItemStack[])}...)
 *
 */
public abstract class PokkitAbstractInventory implements Inventory {

	@Override
	public final HashMap<Integer, ItemStack> addItem(ItemStack... items) throws IllegalArgumentException {
		Validate.noNullElements(items);

		HashMap<Integer, ItemStack> result = new HashMap<>();
		for (int i = 0; i < items.length; i++) {
			ItemStack item = items[i];

			int remaining = addSingleStack(item);
			if (remaining != 0) {
				// Register the leftover
				ItemStack leftOver = item.clone();
				leftOver.setAmount(remaining);
				result.put(i, leftOver);
			}
		}

		return result;
	}

	/**
	 * Add the item stack to the inventory. The implementation must first add
	 * the items to existing stacks that are
	 * {@link ItemStack#isSimilar(ItemStack) of the same type}, then items can
	 * be added to empty slots.
	 *
	 * <p>
	 * Existing stacks will be filled up to {@link Math#min(int, int) Math.min}(
	 * {@link Material#getMaxStackSize()}, {@link Inventory#getMaxStackSize()}),
	 * while empty slots will be filled up to
	 * {@link Inventory#getMaxStackSize()}.
	 *
	 * @param item
	 *            The item.
	 * @return How many items could not be stored.
	 */
	protected abstract int addSingleStack(ItemStack item);

	@Override
	public boolean contains(Material material) throws IllegalArgumentException {
		return first(material) != -1;
	}

	@Override
	public Location getLocation() {
		InventoryHolder holder = getHolder();
		// I think this covers all possible inventory holders
		if (holder instanceof BlockState) {
			return ((BlockState) holder).getLocation();
		}
		if (holder instanceof Entity) {
			return ((Entity) holder).getLocation();
		}
		if (holder instanceof DoubleChest) {
			return ((DoubleChest) holder).getLocation();
		}
		return null;
	}

	@Override
	public ItemStack[] getStorageContents() {
		return getContents();
	}

	@Override
	public ListIterator<ItemStack> iterator() {
		return Arrays.asList(getContents()).listIterator();
	}

	@Override
	public ListIterator<ItemStack> iterator(int index) {
		return Arrays.asList(getContents()).listIterator(index);
	}

	@Override
	public void setContents(ItemStack[] items) throws IllegalArgumentException {
		Preconditions.checkArgument(items.length <= getSize());
		for (int i = 0; i < items.length; i++) {
			setItem(i, items[i]);
		}
	}

	@Override
	public void setStorageContents(ItemStack[] items) throws IllegalArgumentException {
		setContents(items);
	}

}
