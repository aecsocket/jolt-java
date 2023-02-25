package jolt.physics.body;

import jolt.JoltEnvironment;
import jolt.JoltNativeImpl;
import io.github.aecsocket.jniglue.JniNative;

import java.util.Collection;

@JniNative(JoltEnvironment.JNI_MODEL)
public final class BodyIDs extends JoltNativeImpl {
    private BodyIDs() {}

    public static final int INVALID = 0xffffffff;
    public static final int MAX_BODY_INDEX = 0x7fffff;


    public static boolean isValid(int id) { return id != INVALID; }

    public static int getIndex(int id) { return id & MAX_BODY_INDEX; }
    public static byte getSequenceNumber(int id) { return (byte) (id >> 24); }

    public static int[] ints(Collection<Integer> ids) {
        var result = new int[ids.size()];
        var i = 0;
        for (int id : ids) {
            result[i] = id;
            i++;
        }
        return result;
    }
}
