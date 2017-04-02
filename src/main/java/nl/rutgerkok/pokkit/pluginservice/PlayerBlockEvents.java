package nl.rutgerkok.pokkit.pluginservice;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import nl.rutgerkok.pokkit.blockstate.PokkitBlockState;
import nl.rutgerkok.pokkit.entity.PokkitEntity;
import nl.rutgerkok.pokkit.player.PokkitPlayer;
import nl.rutgerkok.pokkit.world.PokkitBlock;
import nl.rutgerkok.pokkit.world.PokkitWorld;
import nl.rutgerkok.pokkit.world.item.PokkitItemStack;

import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockIgniteEvent.IgniteCause;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import cn.nukkit.event.EventHandler;
import cn.nukkit.item.Item;

public final class PlayerBlockEvents extends EventTranslator {

	@EventHandler(ignoreCancelled = false)
	public void onBlockBreak(cn.nukkit.event.block.BlockBreakEvent event) {
		PokkitBlock brokenBlock = PokkitBlock.toBukkit(event.getBlock());

		// In Bukkit, if you break a block in creative, a PlayerInteractEvent is sent before the block breaks
		// So we need to call a PlayerInteractEvent if the player is in creative
		if (event.getPlayer().isCreative()) {
		    // Yes, the player is in creative!
		    // TODO: That BlockFace.SELF is wrong
			PlayerInteractEvent playerInteractEvent = new PlayerInteractEvent(
					PokkitPlayer.toBukkit(event.getPlayer()),
					Action.LEFT_CLICK_BLOCK,
					PokkitItemStack.toBukkitCopy(event.getItem()),
					brokenBlock, BlockFace.SELF);
		    callCancellable(event, playerInteractEvent);
		    if (playerInteractEvent.isCancelled()) {
		        return;
		    }
		}

		if (canIgnore(BlockBreakEvent.getHandlerList())) {
			return;
		}

		// Capture original drops
		List<ItemStack> originalDrops = Arrays.stream(event.getDrops())
				.map(PokkitItemStack::toBukkitCopy)
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
	public void onBlockBurn(cn.nukkit.event.block.BlockBurnEvent event) {
		if (canIgnore(BlockBurnEvent.getHandlerList())) {
			return;
		}

		cn.nukkit.block.Block burning = event.getBlock();

		// Nukkit does not provide ignitingBlock, so we have to use this
		// deprecated method
		@SuppressWarnings("deprecation")
		BlockBurnEvent bukkitEvent = new BlockBurnEvent(PokkitBlock.toBukkit(burning));
		callCancellable(event, bukkitEvent);
	}

	@EventHandler(ignoreCancelled = false)
	public void onBlockForm(cn.nukkit.event.block.BlockFormEvent event) {
		if (canIgnore(BlockFormEvent.getHandlerList())) {
			return;
		}

		cn.nukkit.block.Block forming = event.getBlock();

		BlockFormEvent bukkitEvent = new BlockFormEvent(PokkitBlock.toBukkit(forming),
				PokkitBlockState.getBlockState(PokkitBlock.toBukkit(event.getNewState())));
		callCancellable(event, bukkitEvent);
	}

	@EventHandler(ignoreCancelled = false)
	public void onBlockGrowing(cn.nukkit.event.block.BlockGrowEvent event) {
		if (canIgnore(BlockGrowEvent.getHandlerList())) {
			return;
		}

		cn.nukkit.block.Block growing = event.getBlock();

		BlockGrowEvent bukkitEvent = new BlockGrowEvent(PokkitBlock.toBukkit(growing),
				PokkitBlockState.getBlockState(PokkitBlock.toBukkit(event.getNewState())));
		callCancellable(event, bukkitEvent);
	}

	@EventHandler(ignoreCancelled = false)
	public void onBlockIgnite(cn.nukkit.event.block.BlockIgniteEvent event) {
		if (canIgnore(BlockIgniteEvent.getHandlerList())) {
			return;
		}

		cn.nukkit.block.Block ignited = event.getBlock();
		IgniteCause cause = IgniteCause.FLINT_AND_STEEL;

		switch (event.getCause()) {
		case EXPLOSION:
			cause = IgniteCause.EXPLOSION;
			break;
		case FIREBALL:
			cause = IgniteCause.FIREBALL;
			break;
		case FLINT_AND_STEEL:
			cause = IgniteCause.FLINT_AND_STEEL;
			break;
		case LAVA:
			cause = IgniteCause.LAVA;
			break;
		case LIGHTNING:
			cause = IgniteCause.LIGHTNING;
			break;
		case SPREAD:
			cause = IgniteCause.SPREAD;
			break;
		default:
			cause = IgniteCause.FLINT_AND_STEEL; // Default to Flint and Steel
			break;
		}

		BlockIgniteEvent bukkitEvent = new BlockIgniteEvent(PokkitBlock.toBukkit(ignited), cause, PokkitEntity.toBukkit(event.getEntity()));
		callCancellable(event, bukkitEvent);
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
	public void onBlockSpread(cn.nukkit.event.block.BlockSpreadEvent event) {
		if (canIgnore(BlockSpreadEvent.getHandlerList())) {
			return;
		}

		cn.nukkit.block.Block spreading = event.getBlock();

		BlockSpreadEvent bukkitEvent = new BlockSpreadEvent(PokkitBlock.toBukkit(spreading),
				PokkitBlock.toBukkit(event.getNewState()),
				PokkitBlockState.getBlockState(PokkitBlock.toBukkit(event.getNewState())));
		callCancellable(event, bukkitEvent);
	}

	@EventHandler(ignoreCancelled = false)
	public void onLeavesDecay(cn.nukkit.event.block.LeavesDecayEvent event) {
		if (canIgnore(LeavesDecayEvent.getHandlerList())) {
			return;
		}

		cn.nukkit.block.Block leaves = event.getBlock();

		LeavesDecayEvent bukkitEvent = new LeavesDecayEvent(PokkitBlock.toBukkit(leaves));
		callCancellable(event, bukkitEvent);
	}
}
