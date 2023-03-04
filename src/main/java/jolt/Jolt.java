package jolt;

import cpufeatures.CpuArchitecture;
import cpufeatures.CpuFeatures;
import cpufeatures.CpuPlatform;

import javax.annotation.Nullable;
import java.io.IOException;
import java.lang.foreign.MemoryAddress;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Function;

import static jolt.headers.JoltPhysicsC.*;

public final class Jolt {
    private Jolt() {}

    public static final String JOLT_VERSION = "2.0.1-906ddc0";

    private static final AtomicBoolean loaded = new AtomicBoolean(false);
    private static JoltFeatures features;
    private static boolean doublePrecision;

    /**
     * Loads the native libraries from the JAR.
     */
    public static void load() {
        if (loaded.getAndSet(true)) return;
        CpuFeatures.load();

        CpuPlatform platform = CpuPlatform.get();
        CpuArchitecture arch = CpuArchitecture.get();
        String resourcePath = "jolt/" + switch (platform) {
            case LINUX -> switch (arch) {
                case X86 -> "linux_x86/libJoltC.so";
                default -> throw new RuntimeException("Invalid architecture " + arch + " for Linux");
            };
            case WINDOWS -> switch (arch) {
                case X86 -> "windows_x86/libJoltC.dll";
                default -> throw new RuntimeException("Invalid architecture " + arch + " for Windows");
            };
            case MACOS -> switch (arch) {
                case X86 -> "macos_x86/libJoltC.dylib";
                default -> throw new RuntimeException("Invalid architecture " + arch + " for MacOS");
            };
        };

        try (var resourceIn = CpuFeatures.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (resourceIn == null)
                throw new RuntimeException("No native library in JAR at " + resourcePath);
            var libFile = Files.createTempFile("Jolt", null);
            Files.copy(resourceIn, libFile, StandardCopyOption.REPLACE_EXISTING);
            System.load(libFile.toAbsolutePath().toString());
        } catch (IOException ex) {
            throw new RuntimeException("Could not load native library", ex);
        }
    }

    public static JoltFeatures features() {
        if (features == null) {
            int bits = JPJ_GetFeatures();
            features = new JoltFeatures(
                    (bits & 0x1) != 0,
                    (bits & 0x2) != 0,
                    (bits & 0x4) != 0,
                    (bits & 0x8) != 0,
                    (bits & 0x10) != 0,
                    (bits & 0x20) != 0,
                    (bits & 0x40) != 0,
                    (bits & 0x80) != 0,
                    (bits & 0x100) != 0,
                    (bits & 0x200) != 0
            );
            doublePrecision = features.doublePrecision();
        }
        return features;
    }

    public static Set<JoltFeature> featureSet() {
        JoltFeatures features = features();
        var result = new HashSet<JoltFeature>();
        for (var feature : JoltFeature.values()) {
            if (feature.test.test(features))
                result.add(feature);
        }
        return result;
    }

    public static boolean doublePrecision() { return doublePrecision; }

    public static RuntimeException tryingDoublePrecision() {
        return new RuntimeException("Attempting to use double-precision method with single-precision library");
    }

    public static void assertSinglePrecision() {
        if (doublePrecision) throw tryingDoublePrecision();
    }

    public static RuntimeException tryingSinglePrecision() {
        return new RuntimeException("Attempting to use single-precision method with double-precision library");
    }

    public static MemoryAddress ptr(@Nullable JoltNative obj) {
        return obj == null ? MemoryAddress.NULL : obj.address();
    }

    public static <T extends Destroyable> void use(T obj, Consumer<T> block) {
        block.accept(obj);
        obj.destroy();
    }

    public static <T extends Destroyable, R> R use(T obj, Function<T, R> block) {
        var result = block.apply(obj);
        obj.destroy();
        return result;
    }

    public static void assertDoublePrecision() {
        if (!doublePrecision) throw tryingSinglePrecision();
    }

    public static void registerDefaultAllocator() {
        JPC_RegisterDefaultAllocator();
    }

    public static void createFactory() {
        JPC_CreateFactory();
    }

    public static void destroyFactory() {
        JPC_DestroyFactory();
    }

    public static void registerTypes() {
        JPC_RegisterTypes();
    }
}
