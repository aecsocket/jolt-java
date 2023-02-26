package jolt.physics.collision;

public interface ObjectLayerPairFilterFunctions {
    boolean shouldCollide(short layer1, short layer2);
}
