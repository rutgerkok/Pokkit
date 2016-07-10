package nl.rutgerkok.pokkit;

/**
 * From
 * https://github.com/mikvor/hashmapTest/blob/f77e6dc13a9fecef4b9e30a8d71844b80fd4f4d5/src/main/java/map/intint/IntIntMap4a.java
 * .
 */
public class IntIntMap {
    private static final int FREE_KEY = 0;

    public static final int NO_VALUE = 0;

    // taken from FastUtil
    private static final int INT_PHI = 0x9E3779B9;

    /**
     * Returns the least power of two smaller than or equal to 2<sup>30</sup>
     * and larger than or equal to <code>Math.ceil( expected / f )</code>.
     *
     * @param expected
     *            the expected number of elements in a hash table.
     * @param f
     *            the load factor.
     * @return the minimum possible size for a backing array.
     * @throws IllegalArgumentException
     *             if the necessary size is larger than 2<sup>30</sup>.
     */
    public static int arraySize(final int expected, final float f) {
        final long s = Math.max(2, nextPowerOfTwo((long) Math.ceil(expected / f)));
        if (s > (1 << 30))
            throw new IllegalArgumentException(
                    "Too large (" + expected + " expected elements with load factor " + f + ")");
        return (int) s;
    }

    /**
     * Return the least power of two greater than or equal to the specified
     * value.
     *
     * <p>
     * Note that this function will return 1 when the argument is 0.
     *
     * @param x
     *            a long integer smaller than or equal to 2<sup>62</sup>.
     * @return the least power of two greater than or equal to the specified
     *         value.
     */
    public static long nextPowerOfTwo(long x) {
        if (x == 0)
            return 1;
        x--;
        x |= x >> 1;
        x |= x >> 2;
        x |= x >> 4;
        x |= x >> 8;
        x |= x >> 16;
        return (x | x >> 32) + 1;
    }

    public static int phiMix(final int x) {
        final int h = x * INT_PHI;
        return h ^ (h >> 16);
    }

    /** Keys and values */
    private int[] m_data;
    /** Do we have 'free' key in the map? */
    private boolean m_hasFreeKey;

    /** Value of 'free' key */
    private int m_freeValue;
    /** Fill factor, must be between (0 and 1) */
    private final float m_fillFactor;

    /** We will resize a map once it reaches this size */
    private int m_threshold;

    /** Current map size */
    private int m_size;

    /** Mask to calculate the original position */
    private int m_mask;

    private int m_mask2;

    public IntIntMap(final int size, final float fillFactor) {
        if (fillFactor <= 0 || fillFactor >= 1)
            throw new IllegalArgumentException("FillFactor must be in (0, 1)");
        if (size <= 0)
            throw new IllegalArgumentException("Size must be positive!");
        final int capacity = arraySize(size, fillFactor);
        m_mask = capacity - 1;
        m_mask2 = capacity * 2 - 1;
        m_fillFactor = fillFactor;

        m_data = new int[capacity * 2];
        m_threshold = (int) (capacity * fillFactor);
    }

    public int get(final int key) {
        int ptr = (phiMix(key) & m_mask) << 1;

        if (key == FREE_KEY)
            return m_hasFreeKey ? m_freeValue : NO_VALUE;

        int k = m_data[ptr];

        if (k == FREE_KEY)
            return NO_VALUE; // end of chain already
        if (k == key) // we check FREE prior to this call
            return m_data[ptr + 1];

        while (true) {
            ptr = (ptr + 2) & m_mask2; // that's next index
            k = m_data[ptr];
            if (k == FREE_KEY)
                return NO_VALUE;
            if (k == key)
                return m_data[ptr + 1];
        }
    }

    public int put(final int key, final int value) {
        if (key == FREE_KEY) {
            final int ret = m_freeValue;
            if (!m_hasFreeKey)
                ++m_size;
            m_hasFreeKey = true;
            m_freeValue = value;
            return ret;
        }

        int ptr = (phiMix(key) & m_mask) << 1;
        int k = m_data[ptr];
        if (k == FREE_KEY) // end of chain already
        {
            m_data[ptr] = key;
            m_data[ptr + 1] = value;
            if (m_size >= m_threshold)
                rehash(m_data.length * 2); // size is set inside
            else
                ++m_size;
            return NO_VALUE;
        } else if (k == key) // we check FREE prior to this call
        {
            final int ret = m_data[ptr + 1];
            m_data[ptr + 1] = value;
            return ret;
        }

        while (true) {
            ptr = (ptr + 2) & m_mask2; // that's next index calculation
            k = m_data[ptr];
            if (k == FREE_KEY) {
                m_data[ptr] = key;
                m_data[ptr + 1] = value;
                if (m_size >= m_threshold)
                    rehash(m_data.length * 2); // size is set inside
                else
                    ++m_size;
                return NO_VALUE;
            } else if (k == key) {
                final int ret = m_data[ptr + 1];
                m_data[ptr + 1] = value;
                return ret;
            }
        }
    }

    /**
     * Taken from FastUtil implementation
     * 
     * @param newCapacity
     *            New capacity for the map.
     */
    private void rehash(final int newCapacity) {
        m_threshold = (int) (newCapacity / 2 * m_fillFactor);
        m_mask = newCapacity / 2 - 1;
        m_mask2 = newCapacity - 1;

        final int oldCapacity = m_data.length;
        final int[] oldData = m_data;

        m_data = new int[newCapacity];
        m_size = m_hasFreeKey ? 1 : 0;

        for (int i = 0; i < oldCapacity; i += 2) {
            final int oldKey = oldData[i];
            if (oldKey != FREE_KEY)
                put(oldKey, oldData[i + 1]);
        }
    }

    public int remove(final int key) {
        if (key == FREE_KEY) {
            if (!m_hasFreeKey)
                return NO_VALUE;
            m_hasFreeKey = false;
            --m_size;
            return m_freeValue; // value is not cleaned
        }

        int ptr = (phiMix(key) & m_mask) << 1;
        int k = m_data[ptr];
        if (k == key) // we check FREE prior to this call
        {
            final int res = m_data[ptr + 1];
            shiftKeys(ptr);
            --m_size;
            return res;
        } else if (k == FREE_KEY)
            return NO_VALUE; // end of chain already
        while (true) {
            ptr = (ptr + 2) & m_mask2; // that's next index calculation
            k = m_data[ptr];
            if (k == key) {
                final int res = m_data[ptr + 1];
                shiftKeys(ptr);
                --m_size;
                return res;
            } else if (k == FREE_KEY)
                return NO_VALUE;
        }
    }

    private int shiftKeys(int pos) {
        // Shift entries with the same hash.
        int last, slot;
        int k;
        final int[] data = this.m_data;
        while (true) {
            pos = ((last = pos) + 2) & m_mask2;
            while (true) {
                if ((k = data[pos]) == FREE_KEY) {
                    data[last] = FREE_KEY;
                    return last;
                }
                slot = (phiMix(k) & m_mask) << 1; // calculate the starting slot
                                                  // for the current key
                if (last <= pos ? last >= slot || slot > pos : last >= slot && slot > pos)
                    break;
                pos = (pos + 2) & m_mask2; // go to the next entry
            }
            data[last] = k;
            data[last + 1] = data[pos + 1];
        }
    }

    public int size() {
        return m_size;
    }

}
