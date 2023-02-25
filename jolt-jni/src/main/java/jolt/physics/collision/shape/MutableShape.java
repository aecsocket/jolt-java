package jolt.physics.collision.shape;

import jolt.JoltNative;

public sealed interface MutableShape extends Shape permits ShapeImpl {
    static MutableShape ref(long address) { return address == 0 ? null : new ShapeImpl(address); }

}
