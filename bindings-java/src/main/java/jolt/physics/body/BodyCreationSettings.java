package jolt.physics.body;

import jolt.JoltNative;
import jolt.jni.JniBind;
import jolt.jni.JniBindDelete;
import jolt.jni.JniInclude;
import jolt.jni.JniType;
import jolt.math.JtQuat;
import jolt.math.JtVec3f;
import jolt.physics.collision.shape.Shape;

@JniInclude("<Jolt/Physics/Body/BodyCreationSettings.h>")
@JniType("BodyCreationSettings")
public final class BodyCreationSettings extends JoltNative {
    private BodyCreationSettings(long address) { super(address); }
    public static BodyCreationSettings ref(long address) { return address == 0 ? null : new BodyCreationSettings(address); }

    @Override
    public void delete() {
        if (address == 0L) throw new IllegalStateException(NATIVE_OBJECT_DELETED);
        _delete(address);
        address = 0;
    }
    @JniBindDelete
    private static native void _delete(long address);

    public BodyCreationSettings(
            Shape shape,
            JtVec3f position,
            JtQuat rotation,
            MotionType motionType,
            int objectLayer
    ) {
        address = _ctor(
                shape.getAddress(),
                position.x(), position.y(), position.z(),
                rotation.x(), rotation.y(), rotation.z(), rotation.w(),
                motionType.ordinal(),
                objectLayer
        );
    }
    @JniBind("""
            return (long) new BodyCreationSettings(
                (Shape*) shape,
                RVec3Arg(positionX, positionY, positionZ),
                QuatArg(rotationX, rotationY, rotationZ, rotationW),
                (EMotionType) motionType,
                objectLayer
            );""")
    private static native long _ctor(
            long shape,
            float positionX, float positionY, float positionZ,
            float rotationX, float rotationY, float rotationZ, float rotationW,
            int motionType,
            int objectLayer
    );
}
