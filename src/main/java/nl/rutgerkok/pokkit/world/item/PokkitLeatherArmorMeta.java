package nl.rutgerkok.pokkit.world.item;

import org.bukkit.Color;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import cn.nukkit.nbt.tag.CompoundTag;
import nl.rutgerkok.pokkit.Pokkit;

public class PokkitLeatherArmorMeta extends PokkitItemMeta implements LeatherArmorMeta {

	PokkitLeatherArmorMeta(CompoundTag tag) {
		super(tag);
	}

	@Override
	public Color getColor() {
		throw Pokkit.unsupported();
	}

	@Override
	public void setColor(Color color) {
		//Not supported
	}

	@Override
	public LeatherArmorMeta clone() {
		return (LeatherArmorMeta) super.clone();
	}

	

}
