package nl.rutgerkok.pokkit.pluginservice;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;

import nl.rutgerkok.pokkit.command.PokkitCommandSender;
import nl.rutgerkok.pokkit.player.PokkitPlayer;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.google.common.collect.Iterators;

import cn.nukkit.event.EventHandler;

public final class PlayerChatEvents extends EventTranslator {

	/**
	 * Bukkit Set&lt;Player&gt; view of Nukkit Set&lt;CommandSender&gt; view.
	 * All Nukkit CommandSenders that are not players are hidden for the Bukkit
	 * plugins.
	 *
	 * <p>
	 * This set preservers the mutability and laziness of the original Nukkit
	 * collection.
	 */
	private class RecipientsSet extends AbstractSet<Player> {
		private final Set<cn.nukkit.command.CommandSender> original;

		RecipientsSet(Set<cn.nukkit.command.CommandSender> original) {
			this.original = original;
		}

		@Override
		public Iterator<Player> iterator() {
			Iterator<CommandSender> bukkitIterator = Iterators.transform(original.iterator(),
					PokkitCommandSender::toBukkit);
			return Iterators.filter(bukkitIterator, Player.class);
		}

		@Override
		public int size() {
			return (int) original.stream().filter(commandSender -> commandSender instanceof cn.nukkit.Player).count();
		}

	}

	/**
	 * Chat messages usually have a defined format, like "&lt;Name&gt; message".
	 * Nukkit represents this format as "&lt;{%0}&gt; {%1}", Bukkit as
	 * "&lt;%s$1&gt; %s$2". This message converts from the Bukkit to the Nukkit
	 * format.
	 *
	 * @param bukkit
	 *            The Bukkit format.
	 * @return The Mukkit format.
	 */
	static String bukkitToNukkitFormat(String bukkit) {
		return String.format(bukkit, "{%0}", "{%1}");
	}

	/**
	 * Chat messages usually have a defined format, like "&lt;Name&gt; message".
	 * Nukkit represents this format as "&lt;{%0}&gt; {%1}", Bukkit as
	 * "&lt;%s$1&gt; %s$2". This message converts from the Nukkit to the Bukkit
	 * format. Note that Nukkit is zero-indexed, while Bukkit is one-indexed.
	 *
	 * @param nukkit
	 *            The Nukkit format.
	 * @return The Bukkit format.
	 */
	static String nukkitToBukkitFormat(String nukkit) {
		return nukkit.replace("%", "%%") // escape for String.format
				.replace("{%%0}", "%1$s") // replace first parameter (player
											// name)
				.replace("{%%1}", "%2$s"); // replace second parameter (message)
	}

	@EventHandler(ignoreCancelled = false)
	public void onPlayerChat(cn.nukkit.event.player.PlayerChatEvent event) {
		if (canIgnore(AsyncPlayerChatEvent.getHandlerList())) {
			return;
		}
		AsyncPlayerChatEvent bukkitEvent = new AsyncPlayerChatEvent(false, PokkitPlayer.toBukkit(event.getPlayer()),
				event.getMessage(), new RecipientsSet(event.getRecipients()));
		bukkitEvent.setFormat(nukkitToBukkitFormat(event.getFormat()));

		callCancellable(event, bukkitEvent);

		event.setMessage(bukkitEvent.getMessage());
		event.setFormat(bukkitToNukkitFormat(bukkitEvent.getFormat()));
	}
}
