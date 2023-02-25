package jolt.physics.collision.shape;

import io.github.aecsocket.jniglue.JniTypeMapping;
import jolt.JoltNativeImpl;
import io.github.aecsocket.jniglue.JniBindDelete;
import io.github.aecsocket.jniglue.JniBindSelf;
import io.github.aecsocket.jniglue.JniInclude;
import jolt.geometry.AABox;
import jolt.math.JtVec3f;
import jolt.physics.body.MassProperties;

@JniInclude("<Jolt/Physics/Collision/Shape/Shape.h>")
@JniTypeMapping("Shape")
class ShapeImpl extends JoltNativeImpl implements MutableShape {
    ShapeImpl(long address) { super(address); }

    @Override
    public void delete() {
        if (address == 0L) throw new IllegalStateException(NATIVE_OBJECT_DELETED);
        _delete(address);
        address = 0;
    }
    @JniBindDelete
    private static native void _delete(long _a);

    protected Shape() {}

    public ShapeType getType() { return ShapeType.values()[_getType(address)]; }
    @JniBindSelf("return (jint) self->GetType();")
    private static native int _getType(long _a);

    public ShapeSubType getSubType() { return ShapeSubType.values()[_getSubType(address)]; }
    @JniBindSelf("return (jint) self->GetSubType();")
    private static native int _getSubType(long _a);

    public long getUserData() { return _getUserData(address); }
    @JniBindSelf("return self->GetUserData();")
    private static native long _getUserData(long _a);

    public void setUserData(long value) { _setUserData(address, value); }
    @JniBindSelf("self->SetUserData(value);")
    private static native void _setUserData(long _a, long value);

    public boolean mustBeStatic() { return _mustBeStatic(address); }
    @JniBindSelf("return self->MustBeStatic();")
    private static native boolean _mustBeStatic(long _a);

    public JtVec3f getCenterOfMass(JtVec3f out) {
        _getCenterOfMass(address, out);
        return out;
    }
    public JtVec3f getCenterOfMass() { return getCenterOfMass(new JtVec3f()); }
    @JniBindSelf("ToJavaSp(env, self->GetCenterOfMass(), out);")
    private static native void _getCenterOfMass(long _a, JtVec3f out);

    public AABox getLocalBounds(AABox out) {
        _getLocalBounds(address, out);
        return out;
    }
    public AABox getLocalBounds() { return getLocalBounds(new AABox()); }
    @JniBindSelf("ToJava(env, self->GetLocalBounds(), out);")
    private static native void _getLocalBounds(long _a, AABox out);

    public int getSubShapeIdBitsRecursive() { return _getSubShapeIdBitsRecursive(address); }
    @JniBindSelf("return self->GetSubShapeIDBitsRecursive();")
    private static native int _getSubShapeIdBitsRecursive(long _a);

    public float getInnerRadius() { return _getInnerRadius(address); }
    @JniBindSelf("return self->GetInnerRadius();")
    private static native float _getInnerRadius(long _a);

    public MassProperties getMassProperties(MassProperties out) {
        _getMassProperties(address, out);
        return out;
    }
    public MassProperties getMassProperties() { return getMassProperties(new MassProperties()); }
    @JniBindSelf("ToJava(env, self->GetMassProperties(), out);")
    private static native void _getMassProperties(long _a, MassProperties out);

    public JtVec3f getSurfaceNormal(int subShapeId, JtVec3f localSurfacePosition, JtVec3f out) {
        _getSurfaceNormal(
                address, subShapeId,
                localSurfacePosition.x, localSurfacePosition.y, localSurfacePosition.z,
                out);
        return out;
    }
    public JtVec3f getSurfaceNormal(int subShapeId, JtVec3f localSurfacePosition) { return getSurfaceNormal(subShapeId, localSurfacePosition, new JtVec3f()); }
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

    public float getVolume() { return _getVolume(address); }
    @JniBindSelf("return self->GetVolume();")
    private static native float _getVolume(long _a);

    public boolean isValidScale(JtVec3f scale) { return _isValidScale(address, scale.x, scale.y, scale.z); }
    @JniBindSelf("return self->IsValidScale(Vec3(scaleX, scaleY, scaleZ));")
    private static native boolean _isValidScale(long _a, float scaleX, float scaleY, float scaleZ);
}
