package jolt;

public final class JoltEnvironment {
    private JoltEnvironment() {}

    static {
        JoltNativeLoader.load();
    }

    public static native void registerDefaultAllocator();
}
