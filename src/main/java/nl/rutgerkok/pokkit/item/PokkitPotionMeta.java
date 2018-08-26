package nl.rutgerkok.pokkit.item;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import nl.rutgerkok.pokkit.Pokkit;

import cn.nukkit.nbt.tag.CompoundTag;

final class PokkitPotionMeta extends PokkitItemMeta implements PotionMeta {

	PokkitPotionMeta(CompoundTag tag, int damage) {
		super(tag, damage);
	}

	@Override
	public boolean addCustomEffect(PotionEffect effect, boolean overwrite) {
		return true;
	}

	@Override
	public boolean clearCustomEffects() {
		return true;
	}

	@Override
	public PokkitPotionMeta clone() {
		return (PokkitPotionMeta) super.clone();
	}

	@Override
	public PotionData getBasePotionData() {
		return new PotionData(PotionType.AWKWARD);
	}

	@Override
	public Color getColor() {
		throw Pokkit.unsupported();
	}

	@Override
	public List<PotionEffect> getCustomEffects() {
		return new ArrayList<PotionEffect>();
	}

	@Override
	public boolean hasColor() {
		throw Pokkit.unsupported();
	}

	@Override
	public boolean hasCustomEffect(PotionEffectType type) {
		return true;
	}

	@Override
	public boolean hasCustomEffects() {
		return true;
	}

	@Override
	boolean isApplicable(Material material) {
		return material == Material.POTION || material == Material.SPLASH_POTION
				|| material == Material.LINGERING_POTION || material == Material.TIPPED_ARROW;
	}

	@Override
	public boolean removeCustomEffect(PotionEffectType type) {
		return true;
	}

	@Override
	public void setBasePotionData(PotionData data) {
		return;
	}

	@Override
	public void setColor(Color arg0) {
		throw Pokkit.unsupported();
	}

	@Override
	public boolean setMainEffect(PotionEffectType type) {
		return true;
	}

}
