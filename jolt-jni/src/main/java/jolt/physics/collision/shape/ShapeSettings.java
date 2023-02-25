package jolt.physics.collision.shape;

import jolt.JoltNative;

public sealed interface ShapeSettings extends JoltNative permits ConvexShapeSettings, CompoundShapeSettings, ShapeSettingsImpl {
    static ShapeSettings ref(long address) { return address == 0 ? null : new ShapeSettingsImpl(address); }

    // TODO shape result, refs
    Shape createShape();

    long getUserData();

    void setUserData(long userData);
}
