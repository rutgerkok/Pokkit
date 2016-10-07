package nl.rutgerkok.pokkit.world.item;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import cn.nukkit.nbt.tag.CompoundTag;

final class PokkitPotionMeta extends PokkitItemMeta implements PotionMeta {

	PokkitPotionMeta(CompoundTag tag) {
		super(tag);
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
	public PotionMeta clone() {
		return this;
	}

	@Override
	public PotionData getBasePotionData() {
		return new PotionData(PotionType.AWKWARD);
	}

	@Override
	public List<PotionEffect> getCustomEffects() {
		return new ArrayList<PotionEffect>();
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
	public boolean setMainEffect(PotionEffectType type) {
		return true;
	}

}
