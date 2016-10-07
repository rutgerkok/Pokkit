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
import nl.rutgerkok.pokkit.player.PokkitPlayer;

public class PokkitHumanEntity extends PokkitLivingEntity implements HumanEntity {

	private final cn.nukkit.entity.EntityLiving nukkit;
	
	public PokkitHumanEntity(cn.nukkit.entity.EntityLiving nukkitEntity) {
		super(nukkitEntity);
		
		this.nukkit = nukkitEntity;
	}

	public static HumanEntity toBukkit(cn.nukkit.entity.EntityHuman human) {
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Inventory getEnderChest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MainHand getMainHand() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setWindowProperty(Property prop, int value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public InventoryView getOpenInventory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InventoryView openInventory(Inventory inventory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InventoryView openWorkbench(Location location, boolean force) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InventoryView openEnchanting(Location location, boolean force) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void openInventory(InventoryView inventory) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public InventoryView openMerchant(Villager trader, boolean force) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ItemStack getItemInHand() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setItemInHand(ItemStack item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ItemStack getItemOnCursor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setItemOnCursor(ItemStack item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isSleeping() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getSleepTicks() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public GameMode getGameMode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setGameMode(GameMode mode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isBlocking() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isHandRaised() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getExpToLevel() {
		// TODO Auto-generated method stub
		return 0;
	}
}
