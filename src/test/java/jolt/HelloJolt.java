package jolt;

import jolt.core.JobSystem;
import jolt.core.TempAllocator;
import jolt.math.DVec3;
import jolt.math.FVec3;
import jolt.math.Quat;
import jolt.physics.Activation;
import jolt.physics.PhysicsSystem;
import jolt.physics.body.*;
import jolt.physics.collision.*;
import jolt.physics.collision.broadphase.*;
import jolt.physics.collision.shape.*;
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

        boolean doublePrecision = Jolt.features().doublePrecision();
        System.out.println("Features: " + Jolt.featureSet());

        Jolt.registerDefaultAllocator();
        Jolt.createFactory();
        Jolt.registerTypes();

        try (var arena = MemorySession.openConfined()) {
            var tempAllocator = TempAllocator.of(10 * 1024 * 1024);
            var jobSystem = JobSystem.of(
                    JobSystem.MAX_PHYSICS_JOBS,
                    JobSystem.MAX_PHYSICS_BARRIERS,
                    Math.min(16, Math.max(1, Runtime.getRuntime().availableProcessors() - 1))
            );

            var bpLayerInterface = BroadPhaseLayerInterface.of(arena, new BroadPhaseLayerInterfaceFn() {
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

            var objBpLayerFilter = ObjectVsBroadPhaseLayerFilter.of(arena, new ObjectVsBroadPhaseLayerFilterFn() {
                @Override
                public boolean shouldCollide(short layer1, byte layer2) {
                    return switch (layer1) {
                        case OBJ_LAYER_NON_MOVING -> layer2 == BP_LAYER_MOVING;
                        case OBJ_LAYER_MOVING -> true;
                        default -> false;
                    };
                }
            });

            var objLayerPairFilter = ObjectLayerPairFilter.of(arena, new ObjectLayerPairFilterFn() {
                @Override
                public boolean shouldCollide(short layer1, short layer2) {
                    return switch (layer1) {
                        case OBJ_LAYER_NON_MOVING -> layer2 == OBJ_LAYER_MOVING;
                        case OBJ_LAYER_MOVING -> true;
                        default -> false;
                    };
                }
            });

            var physicsSystem = PhysicsSystem.of(
                    1024,
                    0,
                    1024,
                    1024,
                    bpLayerInterface,
                    objBpLayerFilter,
                    objLayerPairFilter
            );

            var bodyActivationListener = BodyActivationListener.of(arena, new BodyActivationListenerFn() {
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

            var contactListener = doublePrecision
                    ? ContactListener.of(arena, new ContactListenerFn.D() {
                        @Override
                        public ValidateResult onContactValidate(Body body1, Body body2, DVec3 baseOffset, CollideShapeResult collisionResult) {
                            System.out.println("Contact validate callback");
                            return ValidateResult.ACCEPT_ALL_CONTACTS_FOR_THIS_BODY_PAIR;
                        }

                        @Override
                        public void onContactAdded(Body body1, Body body2, ContactManifold manifold, ContactSettings settings) {
                            System.out.println("A contact was added");
                        }

                        @Override
                        public void onContactPersisted(Body body1, Body body2, ContactManifold manifold, ContactSettings settings) {
                            System.out.println("A contact was persisted");
                        }

                        @Override
                        public void onContactRemoved(SubShapeIdPair subShapeIdPair) {
                            System.out.println("A contact was removed");
                        }
                    })
                    : ContactListener.of(arena, new ContactListenerFn.F() {
                        @Override
                        public ValidateResult onContactValidate(Body body1, Body body2, FVec3 baseOffset, CollideShapeResult collisionResult) {
                            System.out.println("Contact validate callback");
                            return ValidateResult.ACCEPT_ALL_CONTACTS_FOR_THIS_BODY_PAIR;
                        }

                        @Override
                        public void onContactAdded(Body body1, Body body2, ContactManifold manifold, ContactSettings settings) {
                            System.out.println("A contact was added");
                        }

                        @Override
                        public void onContactPersisted(Body body1, Body body2, ContactManifold manifold, ContactSettings settings) {
                            System.out.println("A contact was persisted");
                        }

                        @Override
                        public void onContactRemoved(SubShapeIdPair subShapeIdPair) {
                            System.out.println("A contact was removed");
                        }
                    });
            physicsSystem.setContactListener(contactListener);

            BodyInterface bodyInterface = physicsSystem.getBodyInterface();

            //StaticCompoundShapeSettings.of().create(arena).orThrow();

            // Destroyable classes do not implement AutoCloseable due to excessive "try-with-resources" warnings
            // use Jolt.use instead
            Shape floorShape = Jolt.use(BoxShapeSettings.of(
                    FVec3.of(arena, 100.0f, 1.0f, 100.0f)
            ), floorShapeSettings -> {
                return floorShapeSettings.create(arena);
            }).orThrow();

            var floorSettings = doublePrecision
                    ? BodyCreationSettings.of(arena,
                            floorShape,
                            DVec3.of(arena, 0.0, -1.0, 0.0),
                            Quat.ofIdentity(arena),
                            MotionType.STATIC, OBJ_LAYER_NON_MOVING
                    ) : BodyCreationSettings.of(arena,
                            floorShape,
                            FVec3.of(arena, 0.0f, -1.0f, 0.0f),
                            Quat.ofIdentity(arena),
                            MotionType.STATIC, OBJ_LAYER_NON_MOVING
                    );
            Body floor = bodyInterface.createBody(floorSettings);
            bodyInterface.addBody(floor.getId(), Activation.DONT_ACTIVATE);

            var sphereSettings = doublePrecision
                    ? BodyCreationSettings.of(arena,
                            SphereShape.of(0.5f),
                            DVec3.of(arena, 0.0, 2.0, 0.0),
                            Quat.ofIdentity(arena),
                            MotionType.DYNAMIC, OBJ_LAYER_MOVING
                    ) : BodyCreationSettings.of(arena,
                            SphereShape.of(0.5f),
                            FVec3.of(arena, 0.0f, 2.0f, 0.0f),
                            Quat.ofIdentity(arena),
                            MotionType.DYNAMIC, OBJ_LAYER_MOVING
                    );

            int sphereId = bodyInterface.createAndAddBody(sphereSettings, Activation.ACTIVATE);

            bodyInterface.setLinearVelocity(sphereId, FVec3.of(arena, 0.0f, -5.0f, 0.0f));

            var deltaTime = 1 / 60.0f;

            physicsSystem.optimizeBroadPhase();

            int step = 0;
            while (bodyInterface.isActive(sphereId)) {
                ++step;

                Object position;
                if (doublePrecision) {
                    DVec3 out = DVec3.of(arena);
                    bodyInterface.getCenterOfMassPosition(sphereId, out);
                    position = out;
                } else {
                    FVec3 out = FVec3.of(arena);
                    bodyInterface.getCenterOfMassPosition(sphereId, out);
                    position = out;
                }
                FVec3 velocity = FVec3.of(arena);
                bodyInterface.getLinearVelocity(sphereId, velocity);

                System.out.println("Step " + step + ": Position = " + position + ", Velocity = " + velocity);

                physicsSystem.update(
                        deltaTime,
                        1,
                        1,
                        tempAllocator,
                        jobSystem
                );
            }

            bodyInterface.removeBody(sphereId);
            bodyInterface.destroyBody(sphereId);

            bodyInterface.removeBody(floor.getId());
            bodyInterface.destroyBody(floor.getId());

            physicsSystem.delete();

            jobSystem.delete();
            tempAllocator.delete();
            Jolt.destroyFactory();
        }
    }
}
