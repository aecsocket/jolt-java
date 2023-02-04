package jolt.physics.body;

import jolt.JoltNative;
import jolt.jni.*;
import jolt.math.JtQuat;
import jolt.math.JtVec3f;
import jolt.physics.collision.shape.Shape;
import jolt.physics.collision.shape.ShapeSettings;

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
    private static native void _delete(long _a);

    public BodyCreationSettings(
            ShapeSettings shape,
            JtVec3f position,
            JtQuat rotation,
            MotionType motionType,
            int objectLayer
    ) {
        address = _ctor0(
                shape.getAddress(),
                position.x, position.y, position.z,
                rotation.x, rotation.y, rotation.z, rotation.w,
                motionType.ordinal(),
                objectLayer
        );
    }
    @JniBind("""
            return (jlong) new BodyCreationSettings(
                (ShapeSettings*) shape,
                RVec3Arg(positionX, positionY, positionZ),
                QuatArg(rotationX, rotationY, rotationZ, rotationW),
                (EMotionType) motionType,
                objectLayer
            );""")
    private static native long _ctor0(
            long shape,
            float positionX, float positionY, float positionZ,
            float rotationX, float rotationY, float rotationZ, float rotationW,
            int motionType,
            int objectLayer
    );

    public BodyCreationSettings(
            Shape shape,
            JtVec3f position,
            JtQuat rotation,
            MotionType motionType,
            int objectLayer
    ) {
        address = _ctor1(
                shape.getAddress(),
                position.x, position.y, position.z,
                rotation.x, rotation.y, rotation.z, rotation.w,
                motionType.ordinal(),
                objectLayer
        );
    }
    @JniBind("""
            return (jlong) new BodyCreationSettings(
                (Shape*) shape,
                RVec3Arg(positionX, positionY, positionZ),
                QuatArg(rotationX, rotationY, rotationZ, rotationW),
                (EMotionType) motionType,
                objectLayer
            );""")
    private static native long _ctor1(
            long shape,
            float positionX, float positionY, float positionZ,
            float rotationX, float rotationY, float rotationZ, float rotationW,
            int motionType,
            int objectLayer
    );

    public ShapeSettings getShapeSettings() { return ShapeSettings.ref(_getShapeSettings(address)); }
    @JniBindSelf("return (jlong) self->GetShapeSettings();")
    private static native long _getShapeSettings(long _a);

    public void setShapeSettings(ShapeSettings shape) { _setShapeSettings(address, shape.getAddress()); }
    @JniBindSelf("self->SetShapeSettings((ShapeSettings*) shape);")
    private static native void _setShapeSettings(long _a, long shape);

    public Shape getShape() { return Shape.ref(_getShape(address)); }
    @JniBindSelf("return (jlong) self->GetShape();")
    private static native long _getShape(long _a);

    public void setShape(Shape shape) { _setShape(address, shape.getAddress()); }
    @JniBindSelf("self->SetShape((Shape*) shape);")
    private static native void _setShape(long _a, long shape);
}
