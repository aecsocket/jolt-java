package jolt.physics.collision.shape;

import io.github.aecsocket.jniglue.JniBindDelete;
import io.github.aecsocket.jniglue.JniInclude;
import io.github.aecsocket.jniglue.JniBindSelf;
import io.github.aecsocket.jniglue.JniTypeMapping;
import jolt.JoltNativeImpl;

@JniInclude("<Jolt/Physics/Collision/Shape/Shape.h>")
@JniTypeMapping("ShapeSettings")
public sealed class ShapeSettings extends JoltNativeImpl permits ConvexShapeSettings, CompoundShapeSettings {
    protected ShapeSettings(long address) { super(address); }
    public static ShapeSettings ref(long address) { return address == 0 ? null : new ShapeSettings(address); }

    @Override protected void deleteInternal() { _delete(address); }
    @JniBindDelete private static native void _delete(long _a);

    protected ShapeSettings() {}

    public final Shape create() { return Shape.ref(_create(address)); }
    @JniBindSelf("""
            ShapeSettings::ShapeResult result = self->Create();
            if (result.HasError()) {
                JniThrow(env, result.GetError().c_str());
                return (jlong) nullptr;
            }
            return (jlong) result.Get().GetPtr();""")
    private static native long _create(long _a);

    public final long getUserData() { return _getUserData(address); }
    @JniBindSelf("return self->mUserData;")
    private static native long _getUserData(long _a);

    public final void setUserData(long userData) { _setUserData(address, userData); }
    @JniBindSelf("self->mUserData = value;")
    private static native void _setUserData(long _a, long value);
}
