package nl.rutgerkok.pokkit.pluginservice;


import nl.rutgerkok.pokkit.PokkitLocation;
import nl.rutgerkok.pokkit.player.PokkitPlayer;
import nl.rutgerkok.pokkit.world.PokkitBlock;
import nl.rutgerkok.pokkit.world.PokkitBlockFace;
import nl.rutgerkok.pokkit.world.PokkitWorld;
import nl.rutgerkok.pokkit.world.item.PokkitItemStack;

import org.bukkit.block.Block;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import cn.nukkit.event.EventHandler;
import cn.nukkit.math.Vector3;

public final class PlayerInteractEvents extends EventTranslator {

	@EventHandler(ignoreCancelled = false)
	public void onDrop(cn.nukkit.event.player.PlayerDropItemEvent event) {
		if (canIgnore(PlayerItemHeldEvent.getHandlerList())) {
			return;
		}

		// TODO: Implement PokkitItemEntity
		PlayerDropItemEvent bukkitEvent = new PlayerDropItemEvent(PokkitPlayer.toBukkit(event.getPlayer()), null);
		callCancellable(event, bukkitEvent);
	}

	@EventHandler(ignoreCancelled = false)
	public void onItemHeld(cn.nukkit.event.player.PlayerItemHeldEvent event) {
		if (canIgnore(PlayerItemHeldEvent.getHandlerList())) {
			return;
		}

		PokkitPlayer player = PokkitPlayer.toBukkit(event.getPlayer());
		int previousSlot = player.lastItemSlot;
		if (previousSlot == PokkitPlayer.ITEM_SLOT_NOT_INITIALIZED) {
			previousSlot = event.getInventorySlot();
		}

		player.lastItemSlot = event.getInventorySlot();

		if (previousSlot != event.getInventorySlot()) {
			PlayerItemHeldEvent bukkitEvent = new PlayerItemHeldEvent(player, previousSlot, event.getInventorySlot());
			callCancellable(event, bukkitEvent);
		}
	}

	@EventHandler(ignoreCancelled = false)
	public void onMove(cn.nukkit.event.player.PlayerMoveEvent event) {
		if (canIgnore(PlayerItemHeldEvent.getHandlerList())) {
			return;
		}

		PlayerMoveEvent bukkitEvent = new PlayerMoveEvent(PokkitPlayer.toBukkit(event.getPlayer()), PokkitLocation.toBukkit(event.getFrom()), PokkitLocation.toBukkit(event.getTo()));
		callCancellable(event, bukkitEvent);
	}

	@EventHandler(ignoreCancelled = false)
	public void onPlayerInteract(cn.nukkit.event.player.PlayerInteractEvent event) {
		if (canIgnore(PlayerInteractEvent.getHandlerList())) {
			return;
		}

		Vector3 touchVector = event.getTouchVector();
		Block bukkitBlock;
		if (touchVector.x == 0 && touchVector.y == 0 && touchVector.z == 0) {
			bukkitBlock = PokkitBlock.toBukkit(event.getBlock());
		} else {
			bukkitBlock = PokkitWorld.toBukkit(event.getPlayer().getLevel()).getBlockAt((int) touchVector.x,
					(int) touchVector.y, (int) touchVector.z);
		}
		PlayerInteractEvent bukkitEvent = new PlayerInteractEvent(PokkitPlayer.toBukkit(event.getPlayer()),
				toBukkit(event.getAction()), PokkitItemStack.toBukkitCopy(event.getItem()), bukkitBlock,
				PokkitBlockFace.toBukkit(event.getFace()));

		callCancellable(event, bukkitEvent);
	}

	@EventHandler(ignoreCancelled = false)
	public void onSignChange(cn.nukkit.event.block.SignChangeEvent event) {
		if (canIgnore(SignChangeEvent.getHandlerList())) {
			return;
		}

		SignChangeEvent bukkitEvent = new SignChangeEvent(PokkitBlock.toBukkit(event.getBlock()),
				PokkitPlayer.toBukkit(event.getPlayer()), event.getLines());
		callCancellable(event, bukkitEvent);

		// The lines[] share a reference, so
		// event.setLines(bukkitEvent.getLines()) is unnecessary
	}

	private Action toBukkit(int nukkit) {
		switch (nukkit) {
		case cn.nukkit.event.player.PlayerInteractEvent.LEFT_CLICK_AIR:
			return Action.LEFT_CLICK_AIR;
		case cn.nukkit.event.player.PlayerInteractEvent.RIGHT_CLICK_AIR:
			return Action.RIGHT_CLICK_AIR;
		case cn.nukkit.event.player.PlayerInteractEvent.LEFT_CLICK_BLOCK:
			return Action.LEFT_CLICK_BLOCK;
		case cn.nukkit.event.player.PlayerInteractEvent.RIGHT_CLICK_BLOCK:
			return Action.RIGHT_CLICK_BLOCK;
		case cn.nukkit.event.player.PlayerInteractEvent.PHYSICAL:
			return Action.PHYSICAL;
		}
		throw new RuntimeException("Unknown action: " + nukkit);
	}
}
