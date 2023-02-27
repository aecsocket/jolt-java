package jolt;

import jolt.core.JobSystem;
import jolt.core.TempAllocator;
import jolt.physics.PhysicsSystem;
import jolt.physics.collision.ObjectLayerPairFilter;
import jolt.physics.collision.ObjectLayerPairFilterFunctions;
import jolt.physics.collision.broadphase.BroadPhaseLayerInterface;
import jolt.physics.collision.broadphase.BroadPhaseLayerInterfaceFunctions;
import jolt.physics.collision.broadphase.ObjectVsBroadPhaseLayerFilter;
import jolt.physics.collision.broadphase.ObjectVsBroadPhaseLayerFilterFunctions;
import org.junit.jupiter.api.Test;

import java.lang.foreign.MemorySession;

public final class HelloJolt {
    public static final byte BP_LAYER_NON_MOVING = 0;
    public static final byte BP_LAYER_MOVING = 1;

    public static final short OBJ_LAYER_NON_MOVING = 0;
    public static final short OBJ_LAYER_MOVING = 1;

    @Test
    public void helloJolt() {
        Jolt.load();

        Jolt.registerDefaultAllocator();
        Jolt.createFactory();
        Jolt.registerTypes();

        try (var memory = MemorySession.openConfined()) {
            var tempAllocator = new TempAllocator(10 * 1024 * 1024);
            var jobSystem = new JobSystem(
                    JobSystem.MAX_PHYSICS_JOBS,
                    JobSystem.MAX_PHYSICS_BARRIERS,
                    Math.min(16, Math.max(1, Runtime.getRuntime().availableProcessors() - 1))
            );

            var bpLayerInterface = BroadPhaseLayerInterface.of(memory, new BroadPhaseLayerInterfaceFunctions() {
                @Override
                public int getNumBroadPhaseLayers() {
                    return 2;
                }

                @Override
                public byte getBroadPhaseLayer(short layer) {
                    return switch (layer) {
                        case OBJ_LAYER_NON_MOVING -> BP_LAYER_NON_MOVING;
                        case OBJ_LAYER_MOVING -> BP_LAYER_MOVING;
                        default -> throw new IllegalArgumentException("Invalid object layer " + layer);
                    };
                }
            });

            var objBpLayerFilter = ObjectVsBroadPhaseLayerFilter.of(memory, new ObjectVsBroadPhaseLayerFilterFunctions() {
                @Override
                public boolean shouldCollide(short layer1, byte layer2) {
                    return switch (layer1) {
                        case OBJ_LAYER_NON_MOVING -> layer2 == BP_LAYER_MOVING;
                        case OBJ_LAYER_MOVING -> true;
                        default -> false;
                    };
                }
            });

            var objLayerPairFilter = ObjectLayerPairFilter.of(memory, new ObjectLayerPairFilterFunctions() {
                @Override
                public boolean shouldCollide(short layer1, short layer2) {
                    return switch (layer1) {
                        case OBJ_LAYER_NON_MOVING -> layer2 == OBJ_LAYER_MOVING;
                        case OBJ_LAYER_MOVING -> true;
                        default -> false;
                    };
                }
            });

            var physicsSystem = new PhysicsSystem(
                    1024,
                    0,
                    1024,
                    1024,
                    bpLayerInterface,
                    objBpLayerFilter,
                    objLayerPairFilter
            );

            // Clean up memory
            physicsSystem.delete();

            objLayerPairFilter.delete();
            objBpLayerFilter.delete();
            bpLayerInterface.delete();

            jobSystem.delete();
            tempAllocator.delete();
            Jolt.destroyFactory();
        }
    }
}
