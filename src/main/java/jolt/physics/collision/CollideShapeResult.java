package jolt.physics.collision;

import jolt.JoltNative;

public class CollideShapeResult extends JoltNative {
    protected CollideShapeResult(long address) { super(address); }
    public static CollideShapeResult ofPointer(long address) { return new CollideShapeResult(address); }


}

