package jolt.physics.collision.shape;

import jolt.JoltNative;
import jolt.physics.body.BodyImpl;
import jolt.physics.body.MutableBody;

public sealed interface Shape extends JoltNative permits MutableShape {
    static Shape ref(long address) { return address == 0 ? null : new ShapeImpl(address); }

}
