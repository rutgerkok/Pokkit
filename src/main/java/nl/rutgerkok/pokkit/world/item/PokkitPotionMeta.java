package nl.rutgerkok.pokkit.world.item;

import java.util.List;

import nl.rutgerkok.pokkit.Pokkit;

import org.bukkit.Material;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import cn.nukkit.nbt.tag.CompoundTag;

final class PokkitPotionMeta extends PokkitItemMeta implements PotionMeta {

	PokkitPotionMeta(CompoundTag tag) {
		super(tag);
	}

	@Override
	public boolean addCustomEffect(PotionEffect effect, boolean overwrite) {
		throw Pokkit.unsupported();
	}

	@Override
	public boolean clearCustomEffects() {
		throw Pokkit.unsupported();
	}

	@Override
	public PotionMeta clone() {
		throw Pokkit.unsupported();
	}

	@Override
	public PotionData getBasePotionData() {
		throw Pokkit.unsupported();
	}

	@Override
	public List<PotionEffect> getCustomEffects() {
		throw Pokkit.unsupported();
	}

	@Override
	public boolean hasCustomEffect(PotionEffectType type) {
		throw Pokkit.unsupported();
	}

	@Override
	public boolean hasCustomEffects() {
		throw Pokkit.unsupported();
	}

	@Override
	boolean isApplicable(Material material) {
		return material == Material.POTION || material == Material.SPLASH_POTION
				|| material == Material.LINGERING_POTION || material == Material.TIPPED_ARROW;
	}

	@Override
	public boolean removeCustomEffect(PotionEffectType type) {
		throw Pokkit.unsupported();
	}

	@Override
	public void setBasePotionData(PotionData data) {
		throw Pokkit.unsupported();
	}

	@Override
	public boolean setMainEffect(PotionEffectType type) {
		throw Pokkit.unsupported();
	}

}
