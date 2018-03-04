package nl.rutgerkok.pokkit.pluginservice;

import cn.nukkit.event.EventHandler;
import nl.rutgerkok.pokkit.item.PokkitItemStack;
import nl.rutgerkok.pokkit.player.PokkitPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerItemConsumeEvents extends EventTranslator {

    @EventHandler(ignoreCancelled = false)
    public void onPlayerItemConsume(cn.nukkit.event.player.PlayerItemConsumeEvent event) {
        if (canIgnore(PlayerItemConsumeEvent.getHandlerList())) {
            return;
        }

        Player bukkitPlayer = PokkitPlayer.toBukkit(event.getPlayer());
        ItemStack item = PokkitItemStack.toBukkitCopy(event.getItem());
        PlayerItemConsumeEvent bukkitEvent = new PlayerItemConsumeEvent(bukkitPlayer, item);
        callCancellable(event, bukkitEvent);
    }
}
