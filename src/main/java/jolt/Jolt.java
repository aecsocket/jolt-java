package jolt;

import cpufeatures.CpuArchitecture;
import cpufeatures.CpuFeatures;
import cpufeatures.CpuPlatform;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import static jolt.headers.JoltPhysicsC.*;

public final class Jolt {
    private Jolt() {}

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
                    (bits & 0x1) != 0
            );
            doublePrecision = features.doublePrecision();
        }
        return features;
    }

    public static Set<JoltFeature> featureSet() {
        JoltFeatures features = features();
        var result = new HashSet<JoltFeature>();
        if (features.doublePrecision()) result.add(JoltFeature.DOUBLE_PRECISION);
        return result;
    }

    public static boolean doublePrecision() { return doublePrecision; }

    public static RuntimeException requireSinglePrecision() {
        return new RuntimeException("Attempting to use single-precision method with double-precision library");
    }

    public static void assertSinglePrecision() {
        if (doublePrecision) throw requireSinglePrecision();
    }

    public static RuntimeException requireDoublePrecision() {
        return new RuntimeException("Attempting to use double-precision method with single-precision library");
    }

    public static void assertDoublePrecision() {
        if (!doublePrecision) throw requireDoublePrecision();
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
