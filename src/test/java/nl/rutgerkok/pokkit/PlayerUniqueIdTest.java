package nl.rutgerkok.pokkit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PlayerUniqueIdTest {

    @Test
    public void testRoundtrip() {
        assertEquals("jeb_", PlayerUniqueId.idToName(PlayerUniqueId.nameToId("jeb_")));
    }

    @Test
    public void testToLowercase() {
        assertEquals("notch", PlayerUniqueId.idToName(PlayerUniqueId.nameToId("Notch")));
    }
}
