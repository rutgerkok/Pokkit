package nl.rutgerkok.pokkit.pluginservice;


import nl.rutgerkok.pokkit.PokkitLocation;
import nl.rutgerkok.pokkit.item.PokkitItemStack;
import nl.rutgerkok.pokkit.player.PokkitPlayer;
import nl.rutgerkok.pokkit.world.PokkitBlock;
import nl.rutgerkok.pokkit.world.PokkitBlockFace;
import nl.rutgerkok.pokkit.world.PokkitWorld;

import org.bukkit.block.Block;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import cn.nukkit.event.EventHandler;
import cn.nukkit.math.Vector3;

/**
 * Events triggered by the player interacting with the world, like walking
 * around and clicking on something. Block place and break events can be found
 * in {@link PlayerBlockEvents}, however.
 */
public final class PlayerInteractEvents extends EventTranslator {

	@EventHandler(ignoreCancelled = false)
	public void onMove(cn.nukkit.event.player.PlayerMoveEvent event) {
		if (canIgnore(PlayerMoveEvent.getHandlerList())) {
			return;
		}

		PlayerMoveEvent bukkitEvent = new PlayerMoveEvent(PokkitPlayer.toBukkit(event.getPlayer()), PokkitLocation.toBukkit(event.getFrom()), PokkitLocation.toBukkit(event.getTo()));
		callCancellable(event, bukkitEvent);

		// Apply setFrom(...) and setTo(...)
		event.setFrom(PokkitLocation.toNukkit(bukkitEvent.getFrom()));
		event.setTo(PokkitLocation.toNukkit(bukkitEvent.getTo()));
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

	private Action toBukkit(cn.nukkit.event.player.PlayerInteractEvent.Action nukkit) {
		// TODO Direct mapping via Action.valueOf?
		switch (nukkit) {
		case LEFT_CLICK_AIR:
			return Action.LEFT_CLICK_AIR;
		case RIGHT_CLICK_AIR:
			return Action.RIGHT_CLICK_AIR;
		case LEFT_CLICK_BLOCK:
			return Action.LEFT_CLICK_BLOCK;
		case RIGHT_CLICK_BLOCK:
			return Action.RIGHT_CLICK_BLOCK;
		case PHYSICAL:
			return Action.PHYSICAL;
		}
		throw new RuntimeException("Unknown action: " + nukkit);
	}
}
