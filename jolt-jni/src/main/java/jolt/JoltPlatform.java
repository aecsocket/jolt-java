package jolt;

import java.util.Locale;

public enum JoltPlatform {
    LINUX        ("jolt.NativeMetaLinux"),
    WINDOWS      ("jolt.NativeMetaWindows"),
    MACOS("jolt.NativeMetaMacos"),
    MACOS_ARM64("jolt.NativeMetaMacosArm64");

    private final String metaClassName;

    JoltPlatform(String metaClassName) {
        this.metaClassName = metaClassName;
    }

    public JoltNativeMeta loadMeta() throws ReflectiveOperationException {
        Class<?> metaClass = Class.forName(metaClassName);
        return (JoltNativeMeta) metaClass.getConstructor().newInstance();
    }

    public static JoltPlatform platform() {
        String osName = System.getProperty("os.name").toLowerCase(Locale.ROOT);
        String osArch = System.getProperty("os.arch");

        if (osName.contains("windows")) {
            return WINDOWS;
        } else if (osName.contains("linux")) {
            return LINUX;
        } else if (osName.contains("mac os x") || osName.contains("darwin")) {
            if (osArch != null && osArch.equals("aarch64")) {
                return MACOS_ARM64;
            } else {
                return MACOS;
            }
        } else {
            throw new IllegalStateException("Unsupported OS " + osName);
        }
    }
}
