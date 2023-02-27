package jolt;

import cpufeatures.CpuArchitecture;
import cpufeatures.CpuFeatures;
import cpufeatures.CpuPlatform;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.atomic.AtomicBoolean;

import static jolt.headers.JoltPhysicsC.*;

public final class Jolt {
    private Jolt() {}

    private static final AtomicBoolean loaded = new AtomicBoolean(false);

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
