package jolt.physics.collision.shape;

import io.github.aecsocket.jniglue.JniBindDelete;
import io.github.aecsocket.jniglue.JniInclude;
import io.github.aecsocket.jniglue.JniBindSelf;
import io.github.aecsocket.jniglue.JniTypeMapping;
import jolt.JoltNativeImpl;

@JniInclude("<Jolt/Physics/Collision/Shape/Shape.h>")
@JniTypeMapping("ShapeSettings")
sealed class ShapeSettingsImpl extends JoltNativeImpl implements ShapeSettings permits ConvexShapeSettingsImpl, CompoundShapeSettingsImpl {
    ShapeSettingsImpl(long address) { super(address); }

    @Override protected void deleteInternal() { _delete(address); }
    @JniBindDelete private static native void _delete(long _a);

    protected ShapeSettingsImpl() {}

    @Override
    public Shape createShape() { return Shape.ref(_createShape(address)); }
    @JniBindSelf("""
            ShapeSettings::ShapeResult result = self->Create();
            if (result.HasError()) {
                JniThrow(env, result.GetError().c_str());
                return (jlong) nullptr;
            }
            return (jlong) result.Get().GetPtr();""")
    private static native long _createShape(long _a);

    @Override
    public long getUserData() { return _getUserData(address); }
    @JniBindSelf("return self->mUserData;")
    private static native long _getUserData(long _a);

    @Override
    public void setUserData(long userData) { _setUserData(address, userData); }
    @JniBindSelf("self->mUserData = value;")
    private static native void _setUserData(long _a, long value);
}
