package jolt.physics.collision.shape;

public sealed interface StaticCompoundShape extends CompoundShape permits StaticCompoundShapeImpl {
    static StaticCompoundShape ref(long address) { return address == 0 ? null : new StaticCompoundShapeImpl(address); }
}
