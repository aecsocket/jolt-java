package jolt.physics.collision;

public interface ObjectLayerPairFilterFn {
    boolean shouldCollide(short layer1, short layer2);
}
