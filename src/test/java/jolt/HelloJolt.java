package jolt;

import jolt.core.JobSystem;
import jolt.core.TempAllocator;
import jolt.math.RVec3;
import jolt.math.Vec3;
import jolt.physics.PhysicsSystem;
import jolt.physics.body.BodyActivationListener;
import jolt.physics.body.BodyActivationListenerFunctions;
import jolt.physics.body.BodyInterface;
import jolt.physics.collision.*;
import jolt.physics.collision.broadphase.BroadPhaseLayerInterface;
import jolt.physics.collision.broadphase.BroadPhaseLayerInterfaceFunctions;
import jolt.physics.collision.broadphase.ObjectVsBroadPhaseLayerFilter;
import jolt.physics.collision.broadphase.ObjectVsBroadPhaseLayerFilterFunctions;
import jolt.physics.collision.shape.BoxShapeSettings;
import jolt.physics.collision.shape.Shape;
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

            var physicsSystem = PhysicsSystem.create(
                    1024,
                    0,
                    1024,
                    1024,
                    bpLayerInterface,
                    objBpLayerFilter,
                    objLayerPairFilter
            );

            var bodyActivationListener = BodyActivationListener.of(memory, new BodyActivationListenerFunctions() {
                @Override
                public void onBodyActivated(int bodyId, long bodyUserData) {
                    System.out.println("A body got activated");
                }

                @Override
                public void onBodyDeactivated(int bodyId, long bodyUserData) {
                    System.out.println("A body went to sleep");
                }
            });
            physicsSystem.setBodyActivationListener(bodyActivationListener);

            var contactListener = ContactListener.of(memory, new ContactListenerFunctions() {
                @Override
                public ValidateResult onContactValidate(int body1, int body2, RVec3 baseOffset, CollideShapeResult collisionResult) {
                    System.out.println("Contact validate callback");
                    return ValidateResult.ACCEPT_ALL_CONTACTS_FOR_THIS_BODY_PAIR;
                }

                @Override
                public void onContactAdded(int body1, int body2, ContactManifold manifold, ContactSettings settings) {
                    System.out.println("A contact was added");
                }

                @Override
                public void onContactPersisted(int body1, int body2, ContactManifold manifold, ContactSettings settings) {
                    System.out.println("A contact was persisted");
                }

                @Override
                public void onContactRemoved(int subShapeIdPair) {
                    System.out.println("A contact was removed");
                }
            });
            physicsSystem.setContactListener(contactListener);

            BodyInterface bodyInterface = physicsSystem.getBodyInterface();

            var floorShapeSettings = BoxShapeSettings.create(new Vec3(100.0f, 1.0f, 100.0f));
            Shape floorShape = floorShapeSettings.create();

            // TODO

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
