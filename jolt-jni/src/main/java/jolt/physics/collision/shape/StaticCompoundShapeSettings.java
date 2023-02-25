package jolt.physics.collision.shape;

import io.github.aecsocket.jniglue.*;
import jolt.core.TempAllocator;

@JniInclude("<Jolt/Physics/Collision/Shape/StaticCompoundShape.h>")
@JniTypeMapping("StaticCompoundShapeSettings")
public final class StaticCompoundShapeSettings extends CompoundShapeSettings {
    private StaticCompoundShapeSettings(long address) { super(address); }
    public static StaticCompoundShapeSettings ref(long address) { return address == 0 ? null : new StaticCompoundShapeSettings(address); }

    @Override
    public void delete() {
        if (address == 0L) throw new IllegalStateException(NATIVE_OBJECT_DELETED);
        _delete(address);
        address = 0;
    }
    @JniBindDelete
    private static native void _delete(long _a);

    public StaticCompoundShapeSettings() { address = _ctor(); }
    @JniBind("return (jlong) new StaticCompoundShapeSettings();")
    private static native long _ctor();

    public Shape create(TempAllocator tempAllocator) { return Shape.ref(_create(address, tempAllocator.getAddress())); }
    @JniBindSelf("""
            ShapeSettings::ShapeResult result = self->Create(*((TempAllocator*) tempAllocator));
            if (result.HasError()) {
                JniThrow(env, result.GetError().c_str());
                return (jlong) nullptr;
            }
            return (jlong) result.Get().GetPtr();""")
    private static native long _create(long _a, long tempAllocator);
}
