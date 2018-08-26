package nl.rutgerkok.pokkit.inventory;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import nl.rutgerkok.pokkit.Pokkit;
import nl.rutgerkok.pokkit.entity.PokkitHumanEntity;
import nl.rutgerkok.pokkit.item.PokkitItemStack;

import cn.nukkit.item.Item;

public final class PokkitPlayerInventory extends PokkitLiveInventory implements PlayerInventory {

	public PokkitPlayerInventory(cn.nukkit.inventory.PlayerInventory inventory) {
		super(inventory);
	}

	@Override
	public ItemStack[] getArmorContents() {
		Item[] nukkitArmor = nukkit().getArmorContents();
		ItemStack[] bukkitArmor = new ItemStack[nukkitArmor.length];

		Arrays.setAll(bukkitArmor, i -> PokkitItemStack.toBukkitCopy(nukkitArmor[i]));

		return bukkitArmor;
	}

	@Override
	public ItemStack getBoots() {
		return PokkitItemStack.toBukkitCopy(nukkit().getBoots());
	}

	@Override
	public ItemStack getChestplate() {
		return PokkitItemStack.toBukkitCopy(nukkit().getChestplate());
	}

	@Override
	public ItemStack[] getExtraContents() {
		throw Pokkit.unsupported();

	}

	@Override
	public int getHeldItemSlot() {
		return nukkit().getHeldItemIndex();
	}

	@Override
	public ItemStack getHelmet() {
		return PokkitItemStack.toBukkitCopy(nukkit().getHelmet());
	}

	@Override
	public HumanEntity getHolder() {
		return PokkitHumanEntity.toBukkit(nukkit().getHolder());
	}

	@Override
	public ItemStack getItemInHand() {
		return PokkitItemStack.toBukkitCopy(nukkit().getItemInHand());
	}

	@Override
	public ItemStack getItemInMainHand() {
		return PokkitItemStack.toBukkitCopy(nukkit().getItemInHand());
	}

	@Override
	public ItemStack getItemInOffHand() {
		return new ItemStack(Material.AIR);
	}

	@Override
	public ItemStack getLeggings() {
		return PokkitItemStack.toBukkitCopy(nukkit().getLeggings());
	}

	private cn.nukkit.inventory.PlayerInventory nukkit() {
		return (cn.nukkit.inventory.PlayerInventory) super.nukkit;
	}

	@Override
	public void setArmorContents(ItemStack[] bukkitArmor) {
		Item[] nukkitArmor = nukkit().getArmorContents();

		Arrays.setAll(nukkitArmor, i -> PokkitItemStack.toNukkitCopy(bukkitArmor[i]));

		nukkit().setArmorContents(nukkitArmor);
	}

	@Override
	public void setBoots(ItemStack boots) {
		nukkit().setBoots(PokkitItemStack.toNukkitCopy(boots));
	}

	@Override
	public void setChestplate(ItemStack chestplate) {
		nukkit().setChestplate(PokkitItemStack.toNukkitCopy(chestplate));
	}

	@Override
	public void setExtraContents(ItemStack[] items) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setHeldItemSlot(int slot) {
		nukkit().setHeldItemSlot(slot);
	}

	@Override
	public void setHelmet(ItemStack helmet) {
		nukkit().setHelmet(PokkitItemStack.toNukkitCopy(helmet));
	}

	@Override
	public void setItemInHand(ItemStack stack) {
		nukkit().setItemInHand(PokkitItemStack.toNukkitCopy(stack));
	}

	@Override
	public void setItemInMainHand(ItemStack item) {
		nukkit().setItemInHand(PokkitItemStack.toNukkitCopy(item));
	}

	@Override
	public void setItemInOffHand(ItemStack item) {
		throw Pokkit.unsupported();

	}

	@Override
	public void setLeggings(ItemStack leggings) {
		nukkit().setLeggings(PokkitItemStack.toNukkitCopy(leggings));
	}

}
