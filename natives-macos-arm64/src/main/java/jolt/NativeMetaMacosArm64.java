package jolt;

public final class NativeMetaMacosArm64 implements JoltNativeMeta {
    private static final String LIBRARY_PATH = "libjolt-jni-bindings.dylib";

    @Override
    public String getLibraryPath() { return LIBRARY_PATH; }
}
