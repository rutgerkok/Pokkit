package nl.rutgerkok.pokkit;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.UUID;

/**
 * Conversion between {@link UUID} and original Nukkit data.
 *
 */
public final class UniqueIdConversion {

    /**
     * Converts the unique id to the level index.
     *
     * @param levelId
     *            The unique id.
     * @return The level index.
     */
    public static int levelIdToIndex(UUID levelId) {
        return (int) levelId.getLeastSignificantBits();
    }

    /**
     * Converst a level index to an unique id.
     *
     * @param levelIndex
     *            The level index.
     * @return The unique id.
     */
    public static UUID levelIndexToId(int levelIndex) {
        return new UUID(0, levelIndex);
    }

    /**
     * Converts an UUID back to the playername.
     *
     * @param uuid
     *            The UUID.
     * @return The name, always lowercase.
     */
    static String playerIdToName(UUID uuid) {
        long leastSigBits = uuid.getLeastSignificantBits();
        long mostSigBits = uuid.getMostSignificantBits();

        byte[] bytes = toArray(mostSigBits, leastSigBits);
        bytes = removeZeroesAtEnd(bytes);

        return new String(bytes, StandardCharsets.UTF_8);
    }

    /**
     * Converts a name to an UUID.
     * 
     * @param name
     *            The name, case insensitive.
     * @return The UUID.
     */
    public static UUID playerNameToId(String name) {
        byte[] bytes = name.toLowerCase().getBytes(StandardCharsets.UTF_8);
        byte[] trimmedTo16 = Arrays.copyOf(bytes, 16);

        long leastSigBits = 0;
        long mostSigBits = 0;
        for (int i = 0; i < 8; i++)
            mostSigBits = (mostSigBits << 8) | (trimmedTo16[i] & 0xff);
        for (int i = 8; i < 16; i++)
            leastSigBits = (leastSigBits << 8) | (trimmedTo16[i] & 0xff);
        return new UUID(mostSigBits, leastSigBits);
    }

    private static byte[] removeZeroesAtEnd(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] == 0) {
                return Arrays.copyOf(bytes, i);
            }
        }
        return bytes;
    }

    private static byte[] toArray(long mostSigBits, long leastSigBits) {
        byte[] bytes = new byte[16];

        bytes[0] = (byte) (mostSigBits >>> 56);
        bytes[1] = (byte) (mostSigBits >>> 48);
        bytes[2] = (byte) (mostSigBits >>> 40);
        bytes[3] = (byte) (mostSigBits >>> 32);
        bytes[4] = (byte) (mostSigBits >>> 24);
        bytes[5] = (byte) (mostSigBits >>> 16);
        bytes[6] = (byte) (mostSigBits >>> 8);
        bytes[7] = (byte) (mostSigBits >>> 0);

        bytes[8] = (byte) (leastSigBits >>> 56);
        bytes[9] = (byte) (leastSigBits >>> 48);
        bytes[10] = (byte) (leastSigBits >>> 40);
        bytes[11] = (byte) (leastSigBits >>> 32);
        bytes[12] = (byte) (leastSigBits >>> 24);
        bytes[13] = (byte) (leastSigBits >>> 16);
        bytes[14] = (byte) (leastSigBits >>> 8);
        bytes[15] = (byte) (leastSigBits >>> 0);
        
        return bytes;
    }
}
