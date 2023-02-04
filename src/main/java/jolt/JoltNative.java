package jolt;

import jolt.jni.IncludePriority;
import jolt.jni.JniHeader;
import jolt.jni.JniInclude;
import jolt.jni.JniNative;

@JniNative("jolt/JoltJNI")
@JniInclude(priority = IncludePriority.EARLY, value = """
        <cstdint>
        <Jolt/Jolt.h>""")
@JniHeader("""
        using uint = unsigned int;
        using uint8 = uint8_t;
        using uint16 = uint16_t;
        using uint32 = uint32_t;
        using uint64 = uint64_t;
        using namespace JPH;""")
public class JoltNative implements AutoCloseable {
    protected long address;

    protected JoltNative(long address) { this.address = address; }
    // although this *can* return null, we leave the nullability ambiguous
    public static JoltNative ref(long address) { return address == 0 ? null : new JoltNative(address); }

    public JoltNative() {}

    public long getAddress() { return address; }

    public void delete() {
        if (address == 0L)
            throw new IllegalStateException("Native object is already deleted");
        _delete(address);
        address = 0;
    }
    private static native void _delete(long address);
    @Override public void close() { delete(); }

    public static RuntimeException unimplemented() {
        return new UnimplementedMethodException();
    }
}
