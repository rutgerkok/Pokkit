package nl.rutgerkok.pokkit.blockstate;

import org.bukkit.Location;

import nl.rutgerkok.pokkit.blockdata.PokkitBlockData;

import cn.nukkit.nbt.tag.CompoundTag;

final class PlainBlockState extends PokkitBlockState {

	PlainBlockState(Location locationOrNull, PokkitBlockData materialData) {
		super(locationOrNull, materialData);
	}

	@Override
	public void saveToTag(CompoundTag tag) {
		// Empty!
	}

}
