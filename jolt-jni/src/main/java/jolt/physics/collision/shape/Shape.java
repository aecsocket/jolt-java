package jolt.physics.collision.shape;

import jolt.JoltNative;
import io.github.aecsocket.jniglue.JniBindDelete;
import io.github.aecsocket.jniglue.JniBindSelf;
import io.github.aecsocket.jniglue.JniInclude;
import io.github.aecsocket.jniglue.JniType;
import jolt.math.JtAABox;
import jolt.math.JtVec3f;
import jolt.physics.body.MassProperties;

@JniInclude("<Jolt/Physics/Collision/Shape/Shape.h>")
@JniType("Shape")
public class Shape extends JoltNative {
    protected Shape(long address) { super(address); }
    public static Shape ref(long address) { return address == 0 ? null : new Shape(address); }

    @Override
    public void delete() {
        if (address == 0L) throw new IllegalStateException(NATIVE_OBJECT_DELETED);
        _delete(address);
        address = 0;
    }
    @JniBindDelete
    private static native void _delete(long _a);

    protected Shape() {}

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

    public JtAABox getLocalBounds(JtAABox out) {
        _getLocalBounds(address, out);
        return out;
    }
    public JtAABox getLocalBounds() { return getLocalBounds(new JtAABox()); }
    @JniBindSelf("ToJava(env, self->GetLocalBounds(), out);")
    private static native void _getLocalBounds(long _a, JtAABox out);

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
}
