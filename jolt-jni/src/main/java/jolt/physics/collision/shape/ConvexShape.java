package jolt.physics.collision.shape;

public sealed interface ConvexShape extends Shape permits SphereShape, BoxShape, CapsuleShape, ConvexShapeImpl {
    static ConvexShape ref(long address) { return address == 0 ? null : new ConvexShapeImpl(address); }

    void setDensity(float density);

    float getDensity();
}
