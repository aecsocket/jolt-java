package jolt.physics.collision.shape;

import io.github.aecsocket.jniglue.*;
import jolt.core.TempAllocator;

@JniInclude("<Jolt/Physics/Collision/Shape/StaticCompoundShape.h>")
@JniTypeMapping("StaticCompoundShapeSettings")
final class StaticCompoundShapeSettingsImpl extends CompoundShapeSettingsImpl implements StaticCompoundShapeSettings {
    StaticCompoundShapeSettingsImpl(long address) { super(address); }

    @Override protected void deleteInternal() { _delete(address); }
    @JniBindDelete private static native void _delete(long _a);

    public StaticCompoundShapeSettingsImpl() { address = _ctor(); }
    @JniBind("return (jlong) new StaticCompoundShapeSettings();")
    private static native long _ctor();

    public Shape createShape(TempAllocator tempAllocator) { return Shape.ref(_createShape(address, tempAllocator.getAddress())); }
    @JniBindSelf("""
            ShapeSettings::ShapeResult result = self->Create(*((TempAllocator*) tempAllocator));
            if (result.HasError()) {
                JniThrow(env, result.GetError().c_str());
                return (jlong) nullptr;
            }
            return (jlong) result.Get().GetPtr();""")
    private static native long _createShape(long _a, long tempAllocator);
}
