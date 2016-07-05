package nl.rutgerkok.pokkit.pluginservice;

import nl.rutgerkok.pokkit.Pokkit;

import org.bukkit.Bukkit;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import cn.nukkit.event.Listener;

/**
 * Abstract base class for Nukkit --> Pokkit event transformers.
 *
 */
abstract class EventTranslator implements PokkitService, Listener {

    protected void callCancellable(cn.nukkit.event.Cancellable nukkit, Cancellable bukkit) {
        bukkit.setCancelled(nukkit.isCancelled());
        Bukkit.getPluginManager().callEvent((Event) bukkit);
        nukkit.setCancelled(bukkit.isCancelled());
    }

    protected void callUncancellable(Event bukkit) {
        Bukkit.getPluginManager().callEvent(bukkit);
    }

    protected boolean canIgnore(HandlerList handlerList) {
        return handlerList.getRegisteredListeners().length == 0;
    }

    @Override
    public void onEnable(Pokkit pokkit) {
        pokkit.getServer().getPluginManager().registerEvents(this, pokkit);
    }
}
