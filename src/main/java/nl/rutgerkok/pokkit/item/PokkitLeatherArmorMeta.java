package nl.rutgerkok.pokkit.item;

import org.bukkit.Color;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import cn.nukkit.nbt.tag.CompoundTag;

public class PokkitLeatherArmorMeta extends PokkitItemMeta implements LeatherArmorMeta {

	PokkitLeatherArmorMeta(CompoundTag tag, int damage) {
		super(tag, damage);
	}


	@Override
	public PokkitLeatherArmorMeta clone() {
		return (PokkitLeatherArmorMeta) super.clone();
	}


	//If you find a better way to do this, PLEASE tell me.
	@Override
	public Color getColor() {
		 if (!tag.exist("red")) return null;
		 if (!tag.exist("green")) return null;
		 if (!tag.exist("blue")) return null;
		 int r = tag.getInt("red");
		 int g = tag.getInt("green");
		 int b = tag.getInt("blue");
		 return Color.fromRGB(r, g, b);
	}


	@Override
	public void setColor(Color color) {
		int rgb = color.getRed() << 16 | color.getGreen() << 8 | color.getBlue();
        this.getTag().putInt("customColor", rgb);//Set the color
        this.getTag().putInt("red", color.getRed());//If you find a better way to do this, PLEASE tell me.
        this.getTag().putInt("green", color.getGreen());//If you find a better way to do this, PLEASE tell me.
        this.getTag().putInt("blue", color.getBlue());//If you find a better way to do this, PLEASE tell me.
	}



}
