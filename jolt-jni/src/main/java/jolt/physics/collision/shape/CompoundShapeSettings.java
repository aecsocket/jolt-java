package jolt.physics.collision.shape;

import jolt.math.JtQuat;
import jolt.math.JtVec3f;

public sealed interface CompoundShapeSettings extends ShapeSettings permits StaticCompoundShapeSettings, CompoundShapeSettingsImpl {
    static CompoundShapeSettings ref(long address) { return address == 0 ? null : new CompoundShapeSettingsImpl(address); }

    void addShape(JtVec3f position, JtQuat rotation, ShapeSettings shape, int userData);

    void addShape(JtVec3f position, JtQuat rotation, Shape shape, int userData);

    // TODO get mSubShapes
}
