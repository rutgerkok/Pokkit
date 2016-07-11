package nl.rutgerkok.pokkit.blockstate;

import java.util.Objects;

import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.material.MaterialData;

import com.google.common.base.Preconditions;

import cn.nukkit.blockentity.BlockEntity;
import cn.nukkit.blockentity.BlockEntitySign;

final class SignBlockState extends PokkitBlockState implements Sign {

    private final String[] lines;

    protected SignBlockState(Location locationOrNull, MaterialData materialData, String[] lines) {
        super(locationOrNull, materialData);
        this.lines = Objects.requireNonNull(lines, "lines");
        Preconditions.checkArgument(lines.length == 4, "there must be four lines on a sign");
    }

    @Override
    public String getLine(int index) throws IndexOutOfBoundsException {
        return lines[index];
    }

    @Override
    public String[] getLines() {
        return lines;
    }

    @Override
    protected void onUpdate() {
        BlockEntity blockEntity = getBlockEntity();
        if (blockEntity instanceof BlockEntitySign) {
            ((BlockEntitySign) blockEntity).setText(lines[0], lines[1], lines[2], lines[3]);
        }
    }

    @Override
    public void setLine(int index, String line) throws IndexOutOfBoundsException {
        if (line == null) {
            line = "";
        }
        lines[index] = line;
    }

}
