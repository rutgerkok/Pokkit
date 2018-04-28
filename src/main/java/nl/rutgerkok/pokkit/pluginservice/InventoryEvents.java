package nl.rutgerkok.pokkit.pluginservice;

import nl.rutgerkok.pokkit.entity.PokkitItemEntity;
import nl.rutgerkok.pokkit.inventory.PokkitInventory;
import nl.rutgerkok.pokkit.inventory.PokkitInventoryView;
import nl.rutgerkok.pokkit.item.PokkitItemStack;
import nl.rutgerkok.pokkit.player.PokkitPlayer;
import nl.rutgerkok.pokkit.world.PokkitBlock;

import org.bukkit.block.Block;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import cn.nukkit.event.EventHandler;

/**
 * Events of players modifying an inventory.
 *
 */
public class InventoryEvents extends EventTranslator {

	@EventHandler(ignoreCancelled = false)
	public void onDrop(cn.nukkit.event.player.PlayerDropItemEvent event) {
		if (canIgnore(PlayerItemHeldEvent.getHandlerList())) {
			return;
		}

		PokkitItemEntity pokkit = new PokkitItemEntity(event.getItem(), event.getPlayer().getLocation());
		PlayerDropItemEvent bukkitEvent = new PlayerDropItemEvent(PokkitPlayer.toBukkit(event.getPlayer()), pokkit);
		callCancellable(event, bukkitEvent);
	}

	@EventHandler(ignoreCancelled = false)
	public void onFurnaceBurn(cn.nukkit.event.inventory.FurnaceBurnEvent event) {
		if (canIgnore(FurnaceBurnEvent.getHandlerList())) {
			return;
		}

		Block furnace = PokkitBlock.toBukkit(event.getBlock());
		ItemStack fuel = PokkitItemStack.toBukkitCopy(event.getFuel());
		FurnaceBurnEvent bukkitEvent = new FurnaceBurnEvent(furnace, fuel, event.getBurnTime());
		bukkitEvent.setBurning(event.isBurning());

		callCancellable(event, bukkitEvent);

		event.setBurning(bukkitEvent.isBurning());
		event.setBurnTime((short) bukkitEvent.getBurnTime());
		PokkitItemStack.overwriteNukkit(bukkitEvent.getFuel(), event.getFuel());
	}

	@EventHandler(ignoreCancelled = false)
	public void onFurnaceSmelt(cn.nukkit.event.inventory.FurnaceSmeltEvent event) {
		if (canIgnore(FurnaceSmeltEvent.getHandlerList())) {
			return;
		}

		Block furnace = PokkitBlock.toBukkit(event.getBlock());
		ItemStack source = PokkitItemStack.toBukkitCopy(event.getSource());
		ItemStack result = PokkitItemStack.toBukkitCopy(event.getResult());
		FurnaceSmeltEvent bukkitEvent = new FurnaceSmeltEvent(furnace, source, result);

		callCancellable(event, bukkitEvent);

		PokkitItemStack.overwriteNukkit(bukkitEvent.getSource(), event.getSource());
		PokkitItemStack.overwriteNukkit(bukkitEvent.getResult(), event.getResult());
	}

	@EventHandler(ignoreCancelled = false)
	public void onInventoryClose(cn.nukkit.event.inventory.InventoryCloseEvent event) {
		if (canIgnore(InventoryCloseEvent.getHandlerList())) {
			return;
		}

		PokkitPlayer player = PokkitPlayer.toBukkit(event.getPlayer());
		Inventory inventory = PokkitInventory.toBukkit(event.getInventory());
		InventoryView inventoryView = new PokkitInventoryView(inventory, player);
		InventoryCloseEvent bukkitEvent = new InventoryCloseEvent(inventoryView);

		callUncancellable(bukkitEvent);
	}

	@EventHandler(ignoreCancelled = false)
	public void onInventoryOpen(cn.nukkit.event.inventory.InventoryOpenEvent event) {
		if (canIgnore(InventoryOpenEvent.getHandlerList())) {
			return;
		}

		PokkitPlayer player = PokkitPlayer.toBukkit(event.getPlayer());
		Inventory inventory = PokkitInventory.toBukkit(event.getInventory());
		InventoryView inventoryView = new PokkitInventoryView(inventory, player);
		InventoryOpenEvent bukkitEvent = new InventoryOpenEvent(inventoryView);

		callCancellable(event, bukkitEvent);
	}

	@EventHandler(ignoreCancelled = false)
	public void onInventoryTransaction(cn.nukkit.event.inventory.InventoryTransactionEvent event) {
		// We'll need to translate these to events that Bukkit plugins can
		// understand them
		/*
		Bukkit.getLogger().warning("Starting transaction");
		for (Transaction transaction : event.getTransaction().getTransactions()) {
			Bukkit.getLogger().info("InventoryTransaction: now in first inventory " + transaction.getSourceItem()
					+ " and in second inventory " + transaction.getTargetItem() + ", moved to slot "
					+ transaction.getSlot());
		}
		Bukkit.getLogger().warning("Ending transaction");
		*/
	}

	@EventHandler(ignoreCancelled = false)
	public void onItemHeld(cn.nukkit.event.player.PlayerItemHeldEvent event) {
		if (canIgnore(PlayerItemHeldEvent.getHandlerList())) {
			return;
		}

		PokkitPlayer player = PokkitPlayer.toBukkit(event.getPlayer());
		int previousSlot = player.lastItemSlot;
		if (previousSlot == PokkitPlayer.ITEM_SLOT_NOT_INITIALIZED) {
			previousSlot = event.getSlot();
		}

		player.lastItemSlot = event.getSlot();

		if (previousSlot != event.getSlot()) {
			PlayerItemHeldEvent bukkitEvent = new PlayerItemHeldEvent(player, previousSlot, event.getSlot());
			callCancellable(event, bukkitEvent);
		}
	}
}
