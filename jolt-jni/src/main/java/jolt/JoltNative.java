package jolt;

import io.github.aecsocket.jniglue.*;

import javax.annotation.Nullable;
import java.util.Objects;

@JniNative(JoltEnvironment.JNI_MODEL)
@JniPriority(NativePriority.EARLY)
@JniHeader("""
        class JNINative {
        public:
            JNINative(JNIEnv* env, jobject obj) : obj(env->NewGlobalRef(obj)) {}
            ~JNINative() {
                jniThread.getEnv()->DeleteGlobalRef(obj);
            }
        
        protected:
            jobject obj;
        };""")

public class JoltNative {
    protected static final String NATIVE_OBJECT_DELETED = "Native object is already deleted";

    protected long address;

    protected JoltNative(long address) {
        if (address == 0)
            throw new IllegalArgumentException("Creating object with null address");
        this.address = address;
    }
    // although this *can* return null, we leave the nullability ambiguous
    public static JoltNative ref(long address) { return address == 0 ? null : new JoltNative(address); }
    public static long ptr(@Nullable JoltNative obj) { return obj == null ? 0 : obj.address; }

    protected static RuntimeException unimplemented() {
        return new UnimplementedMethodException();
    }

    public JoltNative() {}

    public long getAddress() { return address; }

    public void delete() { throw unimplemented(); }

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
}
