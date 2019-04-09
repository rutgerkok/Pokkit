package nl.rutgerkok.pokkit.entity;

import nl.rutgerkok.pokkit.Pokkit;
import nl.rutgerkok.pokkit.item.PokkitItemStack;

import org.bukkit.Rotation;
import org.bukkit.block.BlockFace;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.inventory.ItemStack;

import cn.nukkit.blockentity.BlockEntityItemFrame;

public class PokkitItemFrameEntity extends PokkitFakeEntity implements ItemFrame {
	BlockEntityItemFrame nukkit;

	public PokkitItemFrameEntity(BlockEntityItemFrame nukkit) {
		this.nukkit = nukkit;
	}

	@Override
	public BlockFace getAttachedFace() {
		throw Pokkit.unsupported();
	}

	@Override
	public BlockFace getFacing() {
		throw Pokkit.unsupported();
	}

	@Override
	public ItemStack getItem() {
		return PokkitItemStack.toBukkitCopy(nukkit.getItem());
	}

	@Override
	cn.nukkit.level.Location getNukkitLocation() {
		return nukkit.getLocation();
	}

	@Override
	public PistonMoveReaction getPistonMoveReaction() {
		return PistonMoveReaction.BREAK;
	}

	@Override
	public Rotation getRotation() {
		// TODO: Implement
		return Rotation.CLOCKWISE;
	}

	@Override
	public EntityType getType() {
		return EntityType.ITEM_FRAME;
	}

	@Override
	public boolean isValid() {
		return nukkit.isBlockEntityValid();
	}

	@Override
	public void remove() {
		nukkit.close();
	}

	@Override
	public void setFacingDirection(BlockFace face) {
		throw Pokkit.unsupported();
	}

	@Override
	public boolean setFacingDirection(BlockFace face, boolean force) {
		throw Pokkit.unsupported();
	}

	@Override
	public void setItem(ItemStack item) {
		nukkit.setItem(PokkitItemStack.toNukkitCopy(item));
	}

	@Override
	public void setItem(ItemStack item, boolean b) {
		nukkit.setItem(PokkitItemStack.toNukkitCopy(item));
	}

	@Override
	public void setRotation(Rotation rotation) throws IllegalArgumentException {
		throw Pokkit.unsupported();
	}

}
