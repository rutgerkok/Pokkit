package nl.rutgerkok.pokkit.pluginservice;

import org.bukkit.block.Block;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;

import cn.nukkit.event.EventHandler;
import cn.nukkit.math.Vector3;
import nl.rutgerkok.pokkit.player.PokkitPlayer;
import nl.rutgerkok.pokkit.world.PokkitBlock;
import nl.rutgerkok.pokkit.world.PokkitBlockFace;
import nl.rutgerkok.pokkit.world.PokkitWorld;
import nl.rutgerkok.pokkit.world.item.PokkitItemStack;

public final class PlayerInteractEvents extends EventTranslator {

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

	@EventHandler(ignoreCancelled = false)
	public void onItemHeld(cn.nukkit.event.player.PlayerItemHeldEvent event) {
		if (canIgnore(PlayerItemHeldEvent.getHandlerList())) {
			return;
		}

		PlayerItemHeldEvent bukkitEvent = new PlayerItemHeldEvent(PokkitPlayer.toBukkit(event.getPlayer()), event.getInventorySlot(), event.getInventorySlot()); // TODO: Create a item cache so we can get the items properly, because Bukkit uses the previous and the on hand item, and Nukkit only has the on hand item
		callCancellable(event, bukkitEvent);
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
