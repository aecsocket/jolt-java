package jolt;

public final class NativeMetaLinux implements JoltNativeMeta {
    private static final String LIBRARY_PATH = "libjolt-jni.so";

    @Override
    public String getLibraryPath() { return LIBRARY_PATH; }
}
