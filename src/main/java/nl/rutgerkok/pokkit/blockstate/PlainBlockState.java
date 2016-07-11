package nl.rutgerkok.pokkit.blockstate;

import org.bukkit.Location;
import org.bukkit.material.MaterialData;

final class PlainBlockState extends PokkitBlockState {

    PlainBlockState(Location locationOrNull, MaterialData materialData) {
        super(locationOrNull, materialData);
    }

    @Override
    protected void onUpdate() {
        // Empty!
    }

}
