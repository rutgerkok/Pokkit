package nl.rutgerkok.pokkit.pluginservice;

import org.bukkit.Bukkit;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import cn.nukkit.event.Listener;
import cn.nukkit.plugin.PluginBase;

/**
 * Abstract base class for Nukkit --> Pokkit event transformers.
 *
 */
abstract class EventTranslator implements PokkitService, Listener {

	/**
	 * Fires the event using {@code Bukkit.getPluginManager().callEvent(event)}.
	 * Propagates whether the event is cancelled before and after calling the
	 * event.
	 *
	 * @param nukkit
	 *            The original Nukkit event.
	 * @param bukkit
	 *            The translated Bukkit event. This argument must extend
	 *            {@link Event} <i>and</i> implement {@link Cancellable}.
	 */
	protected void callCancellable(cn.nukkit.event.Cancellable nukkit, Cancellable bukkit) {
		bukkit.setCancelled(nukkit.isCancelled());
		Bukkit.getPluginManager().callEvent((Event) bukkit);
		nukkit.setCancelled(bukkit.isCancelled());
	}

	/**
	 * Shortcut for {@code Bukkit.getPluginManager().callEvent(event)}
	 *
	 * @param bukkit
	 *            The Bukkit event.
	 */
	protected void callUncancellable(Event bukkit) {
		Bukkit.getPluginManager().callEvent(bukkit);
	}

	/**
	 * This method checks if a handler list is empty. The handler list is the
	 * list of all Bukkit plugins that are "handling" (responding to) an event.
	 * If the list is emtpy, then no plugins are listening to that event, and it
	 * can be safely ignored.
	 *
	 * <p>
	 * This method is intended to be used as follows:
	 *
	 * <pre>
	 * &commat;EventHandler(ignoreCancelled = false)
	 * public void onSpawn(cn.nukkit.event.entity.EntitySpawnEvent event) {
	 *     if (canIgnore(EntitySpawnEvent.getHandlerList())) {
	 *         // No Bukkit events are listening to EntitySpawnEvent, so
	 *         // don't waste time firing it.
	 *         return;
	 *     }
	 *
	 *     // Spend time to convert and fire the event, so that Bukkit
	 *     // plugins can react
	 *     EntitySpawnEvent bukkitEvent = ...
	 * }
	 * </pre>
	 *
	 * In the above example, we ignore {@code EntitySpawnEvent} if
	 * {@code EntitySpawnEvent} has no handlers.
	 *
	 * @param handlerList
	 *            The plugins listening to a Bukkit event.
	 * @return True if the handler list is empty, false otherwise.
	 */
	protected boolean canIgnore(HandlerList handlerList) {
		return handlerList.getRegisteredListeners().length == 0;
	}

	@Override
	public void onEnable(PluginBase pokkit) {
		pokkit.getServer().getPluginManager().registerEvents(this, pokkit);
	}
}
