package jolt.physics.collision.shape;

import io.github.aecsocket.jniglue.JniTypeMapping;
import jolt.JoltNativeImpl;
import io.github.aecsocket.jniglue.JniBindSelf;
import io.github.aecsocket.jniglue.JniInclude;
import jolt.UnimplementedMethodException;
import jolt.geometry.AABox;
import jolt.math.JtMat44f;
import jolt.math.JtQuat;
import jolt.math.JtVec3f;
import jolt.physics.body.MassProperties;
import jolt.physics.collision.PhysicsMaterial;
import jolt.physics.collision.TransformedShape;

@JniInclude("<Jolt/Physics/Collision/Shape/Shape.h>")
@JniTypeMapping("Shape")
public sealed class Shape extends JoltNativeImpl permits ConvexShape, CompoundShape {
    protected Shape(long address) { super(address); }
    public static Shape ref(long address) { return address == 0 ? null : new Shape(address); }

    protected Shape() {}

    public final ShapeType getType() { return ShapeType.values()[_getType(address)]; }
    @JniBindSelf("return (jint) self->GetType();")
    private static native int _getType(long _a);

    public final ShapeSubType getSubType() { return ShapeSubType.values()[_getSubType(address)]; }
    @JniBindSelf("return (jint) self->GetSubType();")
    private static native int _getSubType(long _a);

    public final long getUserData() { return _getUserData(address); }
    @JniBindSelf("return self->GetUserData();")
    private static native long _getUserData(long _a);

    public final void setUserData(long value) { _setUserData(address, value); }
    @JniBindSelf("self->SetUserData(value);")
    private static native void _setUserData(long _a, long value);

    public final boolean mustBeStatic() { return _mustBeStatic(address); }
    @JniBindSelf("return self->MustBeStatic();")
    private static native boolean _mustBeStatic(long _a);

    public final JtVec3f getCenterOfMass(JtVec3f out) {
        _getCenterOfMass(address, out);
        return out;
    }
    public final JtVec3f getCenterOfMass() { return getCenterOfMass(new JtVec3f()); }
    @JniBindSelf("ToJavaSp(env, self->GetCenterOfMass(), out);")
    private static native void _getCenterOfMass(long _a, JtVec3f out);

    public final AABox getLocalBounds(AABox out) {
        _getLocalBounds(address, out);
        return out;
    }
    public final AABox getLocaleBounds() { return getLocalBounds(new AABox()); }
    @JniBindSelf("ToJava(env, self->GetLocalBounds(), out);")
    private static native void _getLocalBounds(long _a, AABox out);

    public final int getSubShapeIdBitsRecursive() { return _getSubShapeIdBitsRecursive(address); }
    @JniBindSelf("return self->GetSubShapeIDBitsRecursive();")
    private static native int _getSubShapeIdBitsRecursive(long _a);

    public final AABox getWorldSpaceBounds(JtMat44f centerOfMassTransform, JtVec3f scale, AABox out) {
        throw new UnimplementedMethodException(); // TODO
    }
    public final AABox getWorldSpaceBounds(JtMat44f centerOfMassTransform, JtVec3f scale) { return getWorldSpaceBounds(centerOfMassTransform, scale, new AABox()); }

    public final float getInnerRadius() { return _getInnerRadius(address); }
    @JniBindSelf("return self->GetInnerRadius();")
    private static native float _getInnerRadius(long _a);

    public final MassProperties getMassProperties(MassProperties out) {
        _getMassProperties(address, out);
        return out;
    }
    public final MassProperties getMassProperties() { return getMassProperties(new MassProperties()); }
    @JniBindSelf("ToJava(env, self->GetMassProperties(), out);")
    private static native void _getMassProperties(long _a, MassProperties out);

    public final PhysicsMaterial getMaterial(int subShapeId) {
        throw new UnimplementedMethodException(); // TODO
    }

    public final JtVec3f getSurfaceNormal(int subShapeId, JtVec3f localSurfacePosition, JtVec3f out) {
        _getSurfaceNormal(
                address, subShapeId,
                localSurfacePosition.x, localSurfacePosition.y, localSurfacePosition.z,
                out
        );
        return out;
    }
    public final JtVec3f getSurfaceNormal(int subShapeId, JtVec3f localSurfacePosition) { return getSurfaceNormal(subShapeId, localSurfacePosition, new JtVec3f()); }
    @JniBindSelf("""
            ToJavaSp(env,
                self->GetSurfaceNormal(
                    SubShapeIDOf(subShapeId),
                    Vec3(localSurfacePositionX, localSurfacePositionY, localSurfacePositionZ)
                ),
                out
            );""")
    private static native void _getSurfaceNormal(
            long _a, int subShapeId,
            float localSurfacePositionX, float localSurfacePositionY, float localSurfacePositionZ,
            JtVec3f out
    );

    public final long getSubShapeUserData(int subShapeId) {
        throw new UnimplementedMethodException(); // TODO
    }

    public final TransformedShape getSubShapeTransformedShape(int subShapeId, JtVec3f positionCOM, JtQuat rotation, JtVec3f scale) {
        throw new UnimplementedMethodException(); // TODO
    }

    public final float getVolume() { return _getVolume(address); }
    @JniBindSelf("return self->GetVolume();")
    private static native float _getVolume(long _a);

    public final boolean isValidScale(JtVec3f scale) { return _isValidScale(address, scale.x, scale.y, scale.z); }
    @JniBindSelf("return self->IsValidScale(Vec3(scaleX, scaleY, scaleZ));")
    private static native boolean _isValidScale(long _a, float scaleX, float scaleY, float scaleZ);
}
