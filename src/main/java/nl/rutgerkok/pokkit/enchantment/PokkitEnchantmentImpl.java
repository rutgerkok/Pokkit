package nl.rutgerkok.pokkit.enchantment;

import java.util.Objects;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

import nl.rutgerkok.pokkit.Pokkit;
import nl.rutgerkok.pokkit.item.PokkitItemStack;

final class PokkitEnchantmentImpl extends Enchantment {

	private final cn.nukkit.item.enchantment.Enchantment nukkit;

	public PokkitEnchantmentImpl(cn.nukkit.item.enchantment.Enchantment nukkit, NamespacedKey bukkitId) {
		super(bukkitId);
		this.nukkit = Objects.requireNonNull(nukkit, "nukkit");
	}

	@Override
	public boolean canEnchantItem(ItemStack item) {
		return nukkit.canEnchant(PokkitItemStack.toNukkitCopy(item));
	}

	@Override
	public boolean conflictsWith(Enchantment other) {
		cn.nukkit.item.enchantment.Enchantment nukkitOther = cn.nukkit.item.enchantment.Enchantment
				.get(PokkitEnchantment.toNukkit(other));
		return !nukkit.isCompatibleWith(nukkitOther);
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		return PokkitEnchantmentTarget.toBukkit(nukkit.type);
	}

	@Override
	public int getMaxLevel() {
		return nukkit.getMaxLevel();
	}

	@Override
	public String getName() {
		return nukkit.getName();
	}

	@Override
	public int getStartLevel() {
		return nukkit.getMinLevel();
	}

	@Override
	public boolean isCursed() {
		throw Pokkit.unsupported();
	}

	@Override
	public boolean isTreasure() {
		throw Pokkit.unsupported();
	}

}
