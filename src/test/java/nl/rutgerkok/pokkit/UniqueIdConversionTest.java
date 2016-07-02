package nl.rutgerkok.pokkit;

import static nl.rutgerkok.pokkit.UniqueIdConversion.levelIdToIndex;
import static nl.rutgerkok.pokkit.UniqueIdConversion.levelIndexToId;
import static nl.rutgerkok.pokkit.UniqueIdConversion.playerIdToName;
import static nl.rutgerkok.pokkit.UniqueIdConversion.playerNameToId;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
public class UniqueIdConversionTest {

    @Test
    public void testPlayerRoundtrip() {
        assertEquals("jeb_", playerIdToName(playerNameToId("jeb_")));
    }

    @Test
    public void testPlayerToLowercase() {
        assertEquals("notch", playerIdToName(playerNameToId("Notch")));
    }

    @Test
    public void testWorldRoundtrip() {
        assertEquals(20, levelIdToIndex(levelIndexToId(20)));
    }
}
