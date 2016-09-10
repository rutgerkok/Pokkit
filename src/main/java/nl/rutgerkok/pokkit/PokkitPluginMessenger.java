package nl.rutgerkok.pokkit;

import java.util.Collections;
import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.plugin.messaging.PluginMessageListenerRegistration;

/**
 * Dummy class that implements {@link Messenger}.
 *
 */
final class PokkitPluginMessenger implements Messenger {

	@Override
	public void dispatchIncomingMessage(Player source, String channel, byte[] message) {
		// Do nothing
	}

	@Override
	public Set<PluginMessageListenerRegistration> getIncomingChannelRegistrations(Plugin plugin) {
		return Collections.emptySet();
	}

	@Override
	public Set<PluginMessageListenerRegistration> getIncomingChannelRegistrations(Plugin plugin, String channel) {
		return Collections.emptySet();
	}

	@Override
	public Set<PluginMessageListenerRegistration> getIncomingChannelRegistrations(String channel) {
		return Collections.emptySet();
	}

	@Override
	public Set<String> getIncomingChannels() {
		return Collections.emptySet();
	}

	@Override
	public Set<String> getIncomingChannels(Plugin plugin) {
		return Collections.emptySet();
	}

	@Override
	public Set<String> getOutgoingChannels() {
		return Collections.emptySet();
	}

	@Override
	public Set<String> getOutgoingChannels(Plugin plugin) {
		return Collections.emptySet();
	}

	@Override
	public boolean isIncomingChannelRegistered(Plugin plugin, String channel) {
		return false;
	}

	@Override
	public boolean isOutgoingChannelRegistered(Plugin plugin, String channel) {
		return false;
	}

	@Override
	public boolean isRegistrationValid(PluginMessageListenerRegistration registration) {
		return false;
	}

	@Override
	public boolean isReservedChannel(String channel) {
		return false;
	}

	@Override
	public PluginMessageListenerRegistration registerIncomingPluginChannel(Plugin plugin, String channel,
			PluginMessageListener listener) {
		return new PluginMessageListenerRegistration(this, plugin, channel, listener);
	}

	@Override
	public void registerOutgoingPluginChannel(Plugin plugin, String channel) {
		// Do nothing
	}

	@Override
	public void unregisterIncomingPluginChannel(Plugin plugin) {
		// Do nothing
	}

	@Override
	public void unregisterIncomingPluginChannel(Plugin plugin, String channel) {
		// Do nothing
	}

	@Override
	public void unregisterIncomingPluginChannel(Plugin plugin, String channel, PluginMessageListener listener) {
		// Do nothing
	}

	@Override
	public void unregisterOutgoingPluginChannel(Plugin plugin) {
		// Do nothing
	}

	@Override
	public void unregisterOutgoingPluginChannel(Plugin plugin, String channel) {
		// Do nothing
	}

}
