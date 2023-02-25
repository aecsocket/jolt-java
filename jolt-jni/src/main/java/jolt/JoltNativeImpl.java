package jolt;

import io.github.aecsocket.jniglue.*;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Objects;

/**
 * The base class for all objects which back a pointer to a native object.
 * <p>
 * Note that this class must be explicitly deleted after use with {@link #delete()}.
 */
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
public abstract class JoltNativeImpl implements JoltNative {
    protected static final String NATIVE_OBJECT_DELETED = "Native object is already deleted";
    protected static final String DELETING_GLOBAL = "Cannot delete global object reference";
    protected static final String NOT_LOADED = "Native libraries are not loaded";

    protected long address;

    protected JoltNativeImpl(long address) {
        if (address == 0) throw new IllegalArgumentException("Creating object with null address");
        this.address = address;
    }
    public static long ptr(@Nullable JoltNativeImpl obj) { return obj == null ? 0 : obj.address; }

    protected static RuntimeException unimplemented() {
        return new UnimplementedMethodException();
    }

    public JoltNativeImpl() {}

    @Override
    public final long getAddress() { return address; }

    protected void deleteInternal() { throw unimplemented(); }

    @Override
    public void delete() {
        if (address == 0L) throw new IllegalStateException(NATIVE_OBJECT_DELETED);
        deleteInternal();
        address = 0;
    }

    @Override
    public String toString() { return String.format("%s*%xd", getClass().getName(), address); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JoltNativeImpl that = (JoltNativeImpl) o;
        return address == that.address;
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }
}
