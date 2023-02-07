package jolt.physics.body;

import jolt.JoltNative;
import jolt.jni.JniNative;

@JniNative(JoltNative.MODEL)
public final class BodyIds extends JoltNative {
    private BodyIds() {}

    public static final int INVALID = 0xffffffff;
    public static final int MAX_BODY_INDEX = 0x7fffff;

    public static boolean isValid(int id) { return id != INVALID; }

    public static int getIndex(int id) { return id & MAX_BODY_INDEX; }
    public static byte getSequenceNumber(int id) { return (byte) (id >> 24); }
}
