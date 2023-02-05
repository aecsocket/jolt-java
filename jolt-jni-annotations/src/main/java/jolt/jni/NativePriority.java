package jolt.jni;

public final class NativePriority {
    private NativePriority() {}

    public static final int NORMAL = 0;
    public static final int EARLY = -100;
    public static final int EARLIEST = -1000;
    public static final int LATE = 100;
    public static final int LATEST = 1000;
}
