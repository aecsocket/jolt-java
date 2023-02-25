package jolt.physics.collision.shape;

import jolt.core.TempAllocator;
import jolt.math.JtVec3f;
import jolt.physics.PhysicsSettings;
import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;

public sealed interface StaticCompoundShapeSettings extends CompoundShapeSettings permits StaticCompoundShapeSettingsImpl {
    static StaticCompoundShapeSettings ref(long address) { return address == 0 ? null : new StaticCompoundShapeSettingsImpl(address); }

    static StaticCompoundShapeSettings create() {
        return new StaticCompoundShapeSettingsImpl();
    }

    // TODO shape result refs
    Shape createShape(TempAllocator tempAllocator);
}
