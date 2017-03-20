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

	@EventHandler(ignoreCancelled = false)
	public void onPlayerChat(cn.nukkit.event.player.PlayerChatEvent event) {
		if (canIgnore(AsyncPlayerChatEvent.getHandlerList())) {
		}
		AsyncPlayerChatEvent bukkitEvent = new AsyncPlayerChatEvent(false, PokkitPlayer.toBukkit(event.getPlayer()),
				event.getMessage(), new RecipientsSet(event.getRecipients()));
		bukkitEvent.setFormat(event.getFormat());

		callCancellable(event, bukkitEvent);

		event.setMessage(bukkitEvent.getMessage());
		event.setFormat(bukkitEvent.getFormat());
	}
}
