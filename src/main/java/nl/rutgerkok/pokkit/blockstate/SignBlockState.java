package nl.rutgerkok.pokkit.blockstate;

import java.util.Objects;

import com.google.common.base.Preconditions;

import org.bukkit.Location;
import org.bukkit.block.Sign;

import nl.rutgerkok.pokkit.Pokkit;
import nl.rutgerkok.pokkit.blockdata.PokkitBlockData;

import cn.nukkit.nbt.tag.CompoundTag;

public final class SignBlockState extends PokkitBlockState implements Sign {

	private final String[] lines;
	private String hiddenData;

	protected SignBlockState(Location locationOrNull, PokkitBlockData materialData, String[] lines, String hiddenData) {
		super(locationOrNull, materialData);
		this.lines = Objects.requireNonNull(lines, "lines");
		this.hiddenData = Objects.requireNonNull(hiddenData, "hiddenData");
		Preconditions.checkArgument(lines.length == 4, "there must be four lines on a sign");
	}

	/**
	 * Gets hidden data on this sign.
	 *
	 * <p>
	 * While players cannot temper with this data, other plugins can. Always
	 * check the format of the returned string.
	 *
	 * @return Hidden data.
	 */
	public String getHiddenData() {
		return hiddenData;
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
	public void saveToTag(CompoundTag tag) {
		tag.putString("Text1", lines[0]);
		tag.putString("Text2", lines[1]);
		tag.putString("Text3", lines[2]);
		tag.putString("Text4", lines[3]);
		if (hiddenData.isEmpty()) {
			tag.remove(Pokkit.NAME);
		} else {
			tag.putString(Pokkit.NAME, hiddenData);
		}
	}

	/**
	 * Sets hidden data on this sign. Set to an empty string to clear the hidden
	 * data.
	 *
	 * @param string
	 *            The hidden data.
	 */
	public void setHiddenData(String string) {
		this.hiddenData = Objects.requireNonNull(string);
	}

	@Override
	public void setLine(int index, String line) throws IndexOutOfBoundsException {
		if (line == null) {
			line = "";
		}
		lines[index] = line;
	}

	@Override
	public boolean isEditable() {
		throw Pokkit.unsupported();
	}

	@Override
	public void setEditable(boolean editable) {
		throw Pokkit.unsupported();
	}
}
