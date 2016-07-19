package nl.rutgerkok.pokkit.pluginservice;

import org.bukkit.block.BlockState;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.EquipmentSlot;

import cn.nukkit.event.EventHandler;
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

        BlockBreakEvent bukkitEvent = new BlockBreakEvent(PokkitBlock.toBukkit(event.getBlock()), PokkitPlayer.toBukkit(event.getPlayer()));
        callCancellable(event, bukkitEvent);
    }

    @EventHandler(ignoreCancelled = false)
    public void onBlockPlace(cn.nukkit.event.block.BlockPlaceEvent event) {
        if (canIgnore(BlockPlaceEvent.getHandlerList())) {
            return;
        }

        cn.nukkit.block.Block placed = event.getBlock();
        BlockState replacedBlockState = PokkitWorld.toBukkit(placed.level).getBlockAt((int)placed.x, (int) placed.y, (int) placed.z).getState();
        BlockPlaceEvent bukkitEvent = new BlockPlaceEvent(PokkitBlock.toBukkit(event.getBlockReplace()),
                replacedBlockState, PokkitBlock.toBukkit(event.getBlockAgainst()),
                PokkitItemStack.toBukkitCopy(event.getItem()), PokkitPlayer.toBukkit(event.getPlayer()),
 true,
                EquipmentSlot.HAND);

        callCancellable(event, bukkitEvent);
    }
}
