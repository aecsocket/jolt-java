package jolt.physics.collision.shape;

import jolt.geometry.AABox;
import jolt.geometry.OrientedBox;

public sealed interface CompoundShape extends Shape permits StaticCompoundShape, CompoundShapeImpl {
    static CompoundShape ref(long address) { return address == 0 ? null : new CompoundShapeImpl(address); }

    int[] getIntersectingSubShapes(AABox box);

    int[] getIntersectingSubShapes(OrientedBox box);

    // todo getsubshapes

    int getNumSubShapes();

}
