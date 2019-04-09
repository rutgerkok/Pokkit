package nl.rutgerkok.pokkit.entity;

import java.util.Objects;

import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

import nl.rutgerkok.pokkit.Pokkit;
import nl.rutgerkok.pokkit.item.PokkitItemStack;

import cn.nukkit.level.Location;

/**
 * In Bukkit, dropped items are always entities. This is not the case in Nukkit,
 * so here we have a "fake entity" implementation.
 *
 */
public class PokkitItemEntity extends PokkitFakeEntity implements Item {

	public final cn.nukkit.item.Item item;
	public final cn.nukkit.level.Location location;

	public PokkitItemEntity(cn.nukkit.item.Item item, Location location) {
		this.item = Objects.requireNonNull(item, "item");
		this.location = Objects.requireNonNull(location, "location");
	}

	@Override
	public ItemStack getItemStack() {
		return PokkitItemStack.toBukkitCopy(item);
	}

	@Override
	cn.nukkit.level.Location getNukkitLocation() {
		return this.location;
	}

	@Override
	public int getPickupDelay() {
		return 0;
	}

	@Override
	public EntityType getType() {
		return EntityType.DROPPED_ITEM;
	}

	@Override
	public void setItemStack(ItemStack stack) {
		throw Pokkit.unsupported();
	}

	@Override
	public void setPickupDelay(int delay) {
		throw Pokkit.unsupported();
	}

	@Override
	public BlockFace getFacing() {
		throw Pokkit.unsupported();
	}
}
