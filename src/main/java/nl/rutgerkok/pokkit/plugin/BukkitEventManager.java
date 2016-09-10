package nl.rutgerkok.pokkit.plugin;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.AuthorNagException;
import org.bukkit.plugin.EventExecutor;
import org.bukkit.plugin.IllegalPluginAccessException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredListener;
import org.bukkit.plugin.SimplePluginManager;

/**
 * Bukkit's event code. Copied from {@link SimplePluginManager}, so copyright
 * Spigot-API.
 *
 */
final class BukkitEventManager {

	/**
	 * Calls an event with the given details.
	 * <p>
	 * This method only synchronizes when the event is not asynchronous.
	 *
	 * @param event
	 *            Event details
	 */
	public void callEvent(Event event) {
		if (event.isAsynchronous()) {
			if (Thread.holdsLock(this)) {
				throw new IllegalStateException(
						event.getEventName() + " cannot be triggered asynchronously from inside synchronized code.");
			}
			if (Bukkit.getServer().isPrimaryThread()) {
				throw new IllegalStateException(
						event.getEventName() + " cannot be triggered asynchronously from primary server thread.");
			}
			fireEvent(event);
		} else {
			synchronized (this) {
				fireEvent(event);
			}
		}
	}

	private void fireEvent(Event event) {
		HandlerList handlers = event.getHandlers();
		RegisteredListener[] listeners = handlers.getRegisteredListeners();

		for (RegisteredListener registration : listeners) {
			if (!registration.getPlugin().isEnabled()) {
				continue;
			}

			try {
				registration.callEvent(event);
			} catch (AuthorNagException ex) {
				Plugin plugin = registration.getPlugin();

				if (plugin.isNaggable()) {
					plugin.setNaggable(false);

					Bukkit.getServer().getLogger().log(Level.SEVERE,
							String.format("Nag author(s): '%s' of '%s' about the following: %s",
									plugin.getDescription().getAuthors(), plugin.getDescription().getFullName(),
									ex.getMessage()));
				}
			} catch (Throwable ex) {
				Bukkit.getServer().getLogger().log(Level.SEVERE, "Could not pass event " + event.getEventName() + " to "
						+ registration.getPlugin().getDescription().getFullName(), ex);
			}
		}
	}

	private HandlerList getEventListeners(Class<? extends Event> type) {
		try {
			Method method = getRegistrationClass(type).getDeclaredMethod("getHandlerList");
			method.setAccessible(true);
			return (HandlerList) method.invoke(null);
		} catch (Exception e) {
			throw new IllegalPluginAccessException(e.toString());
		}
	}

	private Class<? extends Event> getRegistrationClass(Class<? extends Event> clazz) {
		try {
			clazz.getDeclaredMethod("getHandlerList");
			return clazz;
		} catch (NoSuchMethodException e) {
			if (clazz.getSuperclass() != null && !clazz.getSuperclass().equals(Event.class)
					&& Event.class.isAssignableFrom(clazz.getSuperclass())) {
				return getRegistrationClass(clazz.getSuperclass().asSubclass(Event.class));
			} else {
				throw new IllegalPluginAccessException("Unable to find handler list for event " + clazz.getName()
						+ ". Static getHandlerList method required!");
			}
		}
	}

	public void registerEvent(Class<? extends Event> event, Listener listener, EventPriority priority,
			EventExecutor executor, Plugin plugin) {
		registerEvent(event, listener, priority, executor, plugin, false);
	}

	/**
	 * Registers the given event to the specified listener using a directly
	 * passed EventExecutor
	 *
	 * @param event
	 *            Event class to register
	 * @param listener
	 *            PlayerListener to register
	 * @param priority
	 *            Priority of this event
	 * @param executor
	 *            EventExecutor to register
	 * @param plugin
	 *            Plugin to register
	 * @param ignoreCancelled
	 *            Do not call executor if event was already cancelled
	 */
	public void registerEvent(Class<? extends Event> event, Listener listener, EventPriority priority,
			EventExecutor executor, Plugin plugin, boolean ignoreCancelled) {
		Validate.notNull(listener, "Listener cannot be null");
		Validate.notNull(priority, "Priority cannot be null");
		Validate.notNull(executor, "Executor cannot be null");
		Validate.notNull(plugin, "Plugin cannot be null");

		if (!plugin.isEnabled()) {
			throw new IllegalPluginAccessException("Plugin attempted to register " + event + " while not enabled");
		}

		getEventListeners(event)
				.register(new RegisteredListener(listener, executor, priority, plugin, ignoreCancelled));
	}

	public void registerEvents(Listener listener, Plugin plugin) {
		if (!plugin.isEnabled()) {
			throw new IllegalPluginAccessException("Plugin attempted to register " + listener + " while not enabled");
		}

		for (Map.Entry<Class<? extends Event>, Set<RegisteredListener>> entry : plugin.getPluginLoader()
				.createRegisteredListeners(listener, plugin).entrySet()) {
			getEventListeners(getRegistrationClass(entry.getKey())).registerAll(entry.getValue());
		}

	}
}
