package jolt;

public final class NativeMetaWindows implements JoltNativeMeta {
    private static final String LIBRARY_PATH = "jolt-jni-bindings.dll";

    @Override
    public String getLibraryPath() { return LIBRARY_PATH; }
}
