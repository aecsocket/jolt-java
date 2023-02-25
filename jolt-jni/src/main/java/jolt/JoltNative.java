package jolt;

import java.util.Collection;

public interface JoltNative {
    long getAddress();

    void delete();

    /**
     * Converts an array of native objects to an array of each object's address.
     * @param natives The native objects.
     * @return The addresses.
     */
    static long[] addressesOf(JoltNative[] natives) {
        long[] result = new long[natives.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = natives[i].getAddress();
        }
        return result;
    }

    /**
     * Converts a collection of native objects to an array of each object's address.
     * @param natives The native objects.
     * @return The addresses.
     */
    static long[] addressesOf(Collection<? extends JoltNative> natives) {
        long[] result = new long[natives.size()];
        var iter = natives.iterator();
        for (int i = 0; i < result.length; i++) {
            result[i] = iter.next().getAddress();
        }
        return result;
    }
}
