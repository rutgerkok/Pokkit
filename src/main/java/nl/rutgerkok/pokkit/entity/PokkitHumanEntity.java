package nl.rutgerkok.pokkit.entity;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.InventoryView.Property;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MainHand;
import org.bukkit.inventory.PlayerInventory;

import nl.rutgerkok.pokkit.Pokkit;
import nl.rutgerkok.pokkit.inventory.PokkitPlayerInventory;
import nl.rutgerkok.pokkit.player.PokkitPlayer;
import nl.rutgerkok.pokkit.world.item.PokkitItemStack;

public class PokkitHumanEntity extends PokkitLivingEntity implements HumanEntity {

	private final cn.nukkit.entity.EntityHumanType nukkit;
	
	public PokkitHumanEntity(cn.nukkit.entity.EntityHumanType nukkitEntity) {
		super(nukkitEntity);
		
		this.nukkit = nukkitEntity;
	}

	public static HumanEntity toBukkit(cn.nukkit.entity.EntityHumanType human) {
		if (human == null) {
			return null;
		}
		if (human instanceof cn.nukkit.Player) {
			return PokkitPlayer.toBukkit((cn.nukkit.Player) human);
		}
		throw Pokkit.unsupported();
	}

	@Override
	public PlayerInventory getInventory() {
		return new PokkitPlayerInventory(nukkit.getInventory());
	}
	
	@Override
	public Inventory getEnderChest() {
		throw Pokkit.unsupported();
	}

	@Override
	public MainHand getMainHand() {
		return MainHand.LEFT;
	}

	@Override
	public boolean setWindowProperty(Property prop, int value) {
		throw Pokkit.unsupported();
	}

	@Override
	public InventoryView getOpenInventory() {
		throw Pokkit.unsupported();
	}

	@Override
	public InventoryView openInventory(Inventory inventory) {
		throw Pokkit.unsupported();
	}

	@Override
	public InventoryView openWorkbench(Location location, boolean force) {
		throw Pokkit.unsupported();
	}

	@Override
	public InventoryView openEnchanting(Location location, boolean force) {
		throw Pokkit.unsupported();
	}

	@Override
	public void openInventory(InventoryView inventory) {
		throw Pokkit.unsupported();
	}

	@Override
	public InventoryView openMerchant(Villager trader, boolean force) {
		throw Pokkit.unsupported();
	}

	@Override
	public void closeInventory() {
		throw Pokkit.unsupported();
	}

	@Override
	public ItemStack getItemInHand() {
		return PokkitItemStack.toBukkitCopy(nukkit.getInventory().getItemInHand());
	}

	@Override
	public void setItemInHand(ItemStack arg0) {
		nukkit.getInventory().setItemInHand(PokkitItemStack.toNukkitCopy(arg0));
	}

	@Override
	public ItemStack getItemOnCursor() {
		throw Pokkit.unsupported();
	}

	@Override
	public void setItemOnCursor(ItemStack item) {
		throw Pokkit.unsupported();
	}

	@Override
	public boolean isSleeping() {
		throw Pokkit.unsupported();
	}

	@Override
	public int getSleepTicks() {
		throw Pokkit.unsupported();
	}

	@Override
	public GameMode getGameMode() {
		throw Pokkit.unsupported();
	}

	@Override
	public void setGameMode(GameMode mode) {
		throw Pokkit.unsupported();
	}

	@Override
	public boolean isBlocking() {
		return false;
	}

	@Override
	public boolean isHandRaised() {
		return false;
	}

	@Override
	public int getExpToLevel() {
		throw Pokkit.unsupported();
	}
}
