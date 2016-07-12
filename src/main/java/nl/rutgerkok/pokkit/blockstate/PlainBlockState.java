package nl.rutgerkok.pokkit.blockstate;

import org.bukkit.Location;
import org.bukkit.material.MaterialData;

import cn.nukkit.nbt.tag.CompoundTag;

final class PlainBlockState extends PokkitBlockState {

    PlainBlockState(Location locationOrNull, MaterialData materialData) {
        super(locationOrNull, materialData);
    }

    @Override
    public void saveToTag(CompoundTag tag) {
        // Empty!
    }

}
