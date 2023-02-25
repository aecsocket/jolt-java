package jolt.physics.collision.shape;

import io.github.aecsocket.jniglue.JniHeader;
import io.github.aecsocket.jniglue.JniNative;
import jolt.JoltEnvironment;
import io.github.aecsocket.jniglue.JniInclude;

@JniNative(JoltEnvironment.JNI_MODEL)
@JniInclude("<Jolt/Physics/Collision/Shape/SubShapeID.h>")
@JniHeader("""
        SubShapeID SubShapeIDOf(const SubShapeID::Type value) {
            SubShapeID result;
            result.SetValue(value);
            return result;
        }""")
public final class SubShapeIds {
}
