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
public class JoltNative {
    protected static final String NATIVE_OBJECT_DELETED = "Native object is already deleted";
    protected static final String DELETING_GLOBAL = "Cannot delete global object reference";
    protected static final String NOT_LOADED = "Native libraries are not loaded";

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

    /**
     * Converts an array of native objects to an array of each object's address.
     * @param natives The native objects.
     * @return The addresses.
     */
    public static long[] addressesOf(JoltNative[] natives) {
        long[] result = new long[natives.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = natives[i].address;
        }
        return result;
    }

    /**
     * Converts a collection of native objects to an array of each object's address.
     * @param natives The native objects.
     * @return The addresses.
     */
    public static long[] addressesOf(Collection<? extends JoltNative> natives) {
        long[] result = new long[natives.size()];
        var iter = natives.iterator();
        for (int i = 0; i < result.length; i++) {
            result[i] = iter.next().address;
        }
        return result;
    }

    public JoltNative() {}

    public final long getAddress() { return address; }

    /**
     * Frees this object on the native side. The object <i>must not</i> be accessed after this is called.
     */
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
