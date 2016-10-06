package nl.rutgerkok.pokkit.pluginservice;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.block.BlockState;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import cn.nukkit.event.EventHandler;
import cn.nukkit.item.Item;
import nl.rutgerkok.pokkit.player.PokkitPlayer;
import nl.rutgerkok.pokkit.world.PokkitBlock;
import nl.rutgerkok.pokkit.world.PokkitWorld;
import nl.rutgerkok.pokkit.world.item.PokkitItemStack;

public final class PlayerBlockEvents extends EventTranslator {

	@EventHandler(ignoreCancelled = false)
	public void onBlockBreak(cn.nukkit.event.block.BlockBreakEvent event) {
		if (canIgnore(BlockBreakEvent.getHandlerList())) {
			return;
		}

		PokkitBlock brokenBlock = PokkitBlock.toBukkit(event.getBlock());

		// Capture original drops
		List<ItemStack> originalDrops = Arrays.stream(event.getDrops()).map(PokkitItemStack::toBukkitCopy)
				.collect(Collectors.toList());

		// Inject actual drops
		brokenBlock.getDrops().clear();
		brokenBlock.getDrops().addAll(originalDrops);

		BlockBreakEvent bukkitEvent = new BlockBreakEvent(brokenBlock, PokkitPlayer.toBukkit(event.getPlayer()));
		callCancellable(event, bukkitEvent);

		// Update Nukkit drops
		if (!bukkitEvent.getBlock().getDrops().equals(originalDrops)) {
			event.setDrops(
					bukkitEvent.getBlock().getDrops().stream().map(PokkitItemStack::toNukkitCopy).toArray(Item[]::new));
		}
	}

	@EventHandler(ignoreCancelled = false)
	public void onBlockPlace(cn.nukkit.event.block.BlockPlaceEvent event) {
		if (canIgnore(BlockPlaceEvent.getHandlerList())) {
			return;
		}

		cn.nukkit.block.Block placed = event.getBlock();
		BlockState replacedBlockState = PokkitWorld.toBukkit(placed.level)
				.getBlockAt((int) placed.x, (int) placed.y, (int) placed.z).getState();
		BlockPlaceEvent bukkitEvent = new BlockPlaceEvent(PokkitBlock.toBukkit(placed), replacedBlockState,
				PokkitBlock.toBukkit(event.getBlockAgainst()), PokkitItemStack.toBukkitCopy(event.getItem()),
				PokkitPlayer.toBukkit(event.getPlayer()), true, EquipmentSlot.HAND);

		callCancellable(event, bukkitEvent);
	}
	
	@EventHandler(ignoreCancelled = false)
	public void onBlockBurn(cn.nukkit.event.block.BlockBurnEvent event) {
		if (canIgnore(BlockPlaceEvent.getHandlerList())) {
			return;
		}

		cn.nukkit.block.Block burning = event.getBlock();
		
		BlockBurnEvent bukkitEvent = new BlockBurnEvent(PokkitBlock.toBukkit(burning));
		callCancellable(event, bukkitEvent);
	}
}
