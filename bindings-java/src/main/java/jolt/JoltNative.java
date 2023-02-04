package jolt;

import jolt.jni.*;

import java.util.Objects;

@JniNative(JoltNative.MODEL)
@JniInclude(priority = IncludePriority.EARLY, value = """
        <cstdint>
        <Jolt/Jolt.h>""")
@JniHeader("""
        using uint = unsigned int;
        using uint8 = uint8_t;
        using uint16 = uint16_t;
        using uint32 = uint32_t;
        using uint64 = uint64_t;
        using namespace JPH;
        
        class JNINative {
        public:
            JNINative(JNIEnv* env, jobject obj) : env(env), obj(env->NewGlobalRef(obj)) {}
            ~JNINative() {
                env->DeleteGlobalRef(obj);
            }
        
        protected:
            JNIEnv* env;
            jobject obj;
        };""")
public class JoltNative implements AutoCloseable {
    public static final String MODEL = "jolt/JoltJNIBindings";

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
    @JniBind("delete (void*) address;")
    private static native void _delete(long address);
    @Override public void close() { delete(); }

    @Override
    public String toString() {
        return String.format("%s*%xd", getClass().getName(), address);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JoltNative that = (JoltNative) o;
        return address == that.address;
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }

    public static RuntimeException unimplemented() {
        return new UnimplementedMethodException();
    }
}
