package nl.rutgerkok.pokkit.pluginservice;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PlayerChatEventsTest {

	@Test
	public void testBukkitConversion() {
		assertEquals("<{%0}> {%1}", PlayerChatEvents.bukkitToNukkitFormat("<%s> %s"));
		assertEquals("<{%0}> {%1}", PlayerChatEvents.bukkitToNukkitFormat("<%1s> %2s"));
		assertEquals("{%0}: {%1}", PlayerChatEvents.bukkitToNukkitFormat("%1s: %2s"));
		assertEquals("%{%0}% {%1}", PlayerChatEvents.bukkitToNukkitFormat("%%%s%% %s"));
	}

	@Test
	public void testNukkitConversion() {
		assertEquals("<%1$s> %2$s", PlayerChatEvents.nukkitToBukkitFormat("<{%0}> {%1}"));
		assertEquals("%1$s: %2$s", PlayerChatEvents.nukkitToBukkitFormat("{%0}: {%1}"));
		assertEquals("%% %1$s %% %2$s", PlayerChatEvents.nukkitToBukkitFormat("% {%0} % {%1}"));
	}
}
