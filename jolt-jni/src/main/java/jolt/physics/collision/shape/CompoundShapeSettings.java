package jolt.physics.collision.shape;

import io.github.aecsocket.jniglue.*;
import jolt.math.JtQuat;
import jolt.math.JtVec3f;

@JniInclude("<Jolt/Physics/Collision/Shape/CompoundShape.h>")
@JniTypeMapping("CompoundShapeSettings")
public sealed class CompoundShapeSettings extends ShapeSettings permits StaticCompoundShapeSettings {
    protected CompoundShapeSettings(long address) { super(address); }
    public static CompoundShapeSettings ref(long address) { return address == 0 ? null : new CompoundShapeSettings(address); }

    protected CompoundShapeSettings() {}

    public void addShape(JtVec3f position, JtQuat rotation, ShapeSettings shape, int userData) {
        _addShape0(
                address,
                position.x, position.y, position.z,
                rotation.x, rotation.y, rotation.z, rotation.w,
                shape.getAddress(),
                userData
        );
    }
    public void addShape(JtVec3f position, JtQuat rotation, ShapeSettings shape) { addShape(position, rotation, shape, 0); }
    @JniBindSelf("""
            self->AddShape(
                Vec3(positionX, positionY, positionZ),
                Quat(rotationX, rotationY, rotationZ, rotationW),
                (ShapeSettings*) shape,
                userData
            );""")
    private static native void _addShape0(
            long _a,
            float positionX, float positionY, float positionZ,
            float rotationX, float rotationY, float rotationZ, float rotationW,
            long shape,
            int userData
    );

    public void addShape(JtVec3f position, JtQuat rotation, Shape shape, int userData) {
        _addShape1(
                address,
                position.x, position.y, position.z,
                rotation.x, rotation.y, rotation.z, rotation.w,
                shape.getAddress(),
                userData
        );
    }
    public void addShape(JtVec3f position, JtQuat rotation, Shape shape) { addShape(position, rotation, shape, 0); }
    @JniBindSelf("""
            self->AddShape(
                Vec3(positionX, positionY, positionZ),
                Quat(rotationX, rotationY, rotationZ, rotationW),
                (Shape*) shape,
                userData
            );""")
    private static native void _addShape1(
            long _a,
            float positionX, float positionY, float positionZ,
            float rotationX, float rotationY, float rotationZ, float rotationW,
            long shape,
            int userData
    );
}
