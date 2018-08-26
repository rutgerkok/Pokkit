package nl.rutgerkok.pokkit.item;

import java.util.Objects;

import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.inventory.meta.BlockStateMeta;

import nl.rutgerkok.pokkit.blockstate.PokkitBlockState;

import cn.nukkit.nbt.tag.CompoundTag;

final class PokkitBlockStateMeta extends PokkitItemMeta implements BlockStateMeta {

	private final Material material;

	PokkitBlockStateMeta(CompoundTag tag, Material material, int damage) {
		super(tag, damage);
		this.material = Objects.requireNonNull(material, "material");
	}

	@Override
	public BlockState getBlockState() {
		CompoundTag blockState = tag.getCompound("BlockEntityTag");
		return PokkitBlockState.getVirtual(this.material, blockState);
	}

	@Override
	public boolean hasBlockState() {
		return tag.contains("BlockEntityTag");
	}

	@Override
	boolean isApplicable(Material material) {
		return material == this.material;
	}

	@Override
	public void setBlockState(BlockState blockState) {
		if (blockState == null) {
			tag.remove("BlockEntityTag");
		} else {
			CompoundTag blockStateTag = tag.getCompound("BlockEntityTag");
			((PokkitBlockState) blockState).saveToTag(blockStateTag);
			setOrRemoveChildTag(tag, "BlockEntityTag", blockStateTag);
		}
	}

}
