package jolt;

import jolt.jni.JniBind;
import jolt.jni.JniBindInit;
import jolt.jni.JniNative;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.atomic.AtomicBoolean;

@JniNative(JoltNative.MODEL)
public final class JoltNativeLoader {
    private JoltNativeLoader() {}

    private static final AtomicBoolean loaded = new AtomicBoolean(false);

    public static void load() {
        if (loaded.getAndSet(true)) return;
        JoltPlatform platform = JoltPlatform.platform();
        try {
            JoltNativeMeta meta = platform.loadMeta();
            loadFromMeta(meta);
            _init();
        } catch (Throwable ex) {
            throw new IllegalStateException("Could not load Jolt JNI for platform " + platform, ex);
        }
    }

    private static void loadFromMeta(JoltNativeMeta meta) {
        String libPath = meta.getLibraryPath();
        try (var libIn = JoltNativeLoader.class.getClassLoader().getResourceAsStream("jolt/" + libPath)) {
            if (libIn == null)
                throw new IllegalStateException("No Jolt JNI library at " + libPath);
            var libFile = Files.createTempFile(libPath, null);
            Files.copy(libIn, libFile, StandardCopyOption.REPLACE_EXISTING);
            System.load(libFile.toAbsolutePath().toString());
        } catch (IOException e) {
            throw new IllegalStateException("Could not load Jolt JNI from " + libPath, e);
        }
    }

    @JniBindInit
    private static native void _init();
}
