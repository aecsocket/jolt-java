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
import jolt.physics.collision.shape.BoxShapeSettings;
import jolt.physics.collision.shape.Shape;
import jolt.physics.collision.shape.SphereShape;
import jolt.physics.collision.shape.SubShapeIdPair;
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

        try (var session = MemorySession.openConfined()) {
            var tempAllocator = new TempAllocator(10 * 1024 * 1024);
            var jobSystem = new JobSystem(
                    JobSystem.MAX_PHYSICS_JOBS,
                    JobSystem.MAX_PHYSICS_BARRIERS,
                    Math.min(16, Math.max(1, Runtime.getRuntime().availableProcessors() - 1))
            );

            var bpLayerInterface = BroadPhaseLayerInterface.of(session, new BroadPhaseLayerInterfaceFunctions() {
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

            var objBpLayerFilter = ObjectVsBroadPhaseLayerFilter.of(session, new ObjectVsBroadPhaseLayerFilterFunctions() {
                @Override
                public boolean shouldCollide(short layer1, byte layer2) {
                    return switch (layer1) {
                        case OBJ_LAYER_NON_MOVING -> layer2 == BP_LAYER_MOVING;
                        case OBJ_LAYER_MOVING -> true;
                        default -> false;
                    };
                }
            });

            var objLayerPairFilter = ObjectLayerPairFilter.of(session, new ObjectLayerPairFilterFunctions() {
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

            var bodyActivationListener = BodyActivationListener.of(session, new BodyActivationListenerFunctions() {
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
                    ? ContactListener.of(session, new ContactListenerFunctions.D() {
                        @Override
                        public ValidateResult onContactValidate(int body1, int body2, DVec3 baseOffset, CollideShapeResult collisionResult) {
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
                        public void onContactRemoved(SubShapeIdPair subShapeIdPair) {
                            System.out.println("A contact was removed");
                        }
                    })
                    : ContactListener.of(session, new ContactListenerFunctions.F() {
                        @Override
                        public ValidateResult onContactValidate(int body1, int body2, FVec3 baseOffset, CollideShapeResult collisionResult) {
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
                        public void onContactRemoved(SubShapeIdPair subShapeIdPair) {
                            System.out.println("A contact was removed");
                        }
                    });
            physicsSystem.setContactListener(contactListener);

            BodyInterface bodyInterface = physicsSystem.getBodyInterface();

            // Destroyable classes do not implement AutoCloseable due to excessive "try-with-resources" warnings
            // use Jolt.use instead
            Shape floorShape = Jolt.use(BoxShapeSettings.create(new FVec3(100.0f, 1.0f, 100.0f)), floorShapeSettings -> {
                return floorShapeSettings.create();
            });

            var floorSettings = doublePrecision
                    ? BodyCreationSettings.create(session, floorShape, new DVec3(0.0, -1.0, 0.0), Quat.IDENTITY, MotionType.STATIC, OBJ_LAYER_NON_MOVING)
                    : BodyCreationSettings.create(session, floorShape, new FVec3(0.0f, -1.0f, 0.0f), Quat.IDENTITY, MotionType.STATIC, OBJ_LAYER_NON_MOVING);
            Body floor = bodyInterface.createBody(floorSettings);
            bodyInterface.addBody(floor.getID(), Activation.DONT_ACTIVATE);

            var sphereSettings = doublePrecision
                    ? BodyCreationSettings.create(session, SphereShape.create(0.5f), new DVec3(0.0, 2.0, 0.0), Quat.IDENTITY, MotionType.DYNAMIC, OBJ_LAYER_MOVING)
                    : BodyCreationSettings.create(session, SphereShape.create(0.5f), new FVec3(0.0f, 2.0f, 0.0f), Quat.IDENTITY, MotionType.DYNAMIC, OBJ_LAYER_MOVING);

            int sphereId = bodyInterface.createAndAddBody(sphereSettings, Activation.ACTIVATE);

            bodyInterface.setLinearVelocity(sphereId, new FVec3(0.0f, -5.0f, 0.0f));

            var deltaTime = 1 / 60.0f;

            physicsSystem.optimizeBroadPhase();

            BroadPhaseQuery q = physicsSystem.getBroadPhaseQuery();

            int step = 0;
            while (bodyInterface.isActive(sphereId)) {
                ++step;

                Object position = doublePrecision
                        ? bodyInterface.getCenterOfMassPositionD(sphereId)
                        : bodyInterface.getCenterOfMassPositionF(sphereId);
                FVec3 velocity = bodyInterface.getLinearVelocity(sphereId);

                System.out.println("Step " + step + ": Position = " + position + ", Velocity = " + velocity);

                q.castRayF(
                        new FRayCast(new FVec3(0.0f, 0.0f, 0.0f), new FVec3(1.0f, 0.0f, 0.0f)),
                        RayCastBodyCollector.of(session, new RayCastBodyCollectorFunctions() {
                            @Override
                            public void onBody(Body body) {
                                System.out.println("q: body");
                            }

                            @Override
                            public void addHit(BroadPhaseCastResult result) {
                                System.out.println("q: result");
                            }
                        }),
                        BroadPhaseLayerFilter.passthrough(),
                        ObjectLayerFilter.passthrough()
                );

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

            bodyInterface.removeBody(floor.getID());
            bodyInterface.destroyBody(floor.getID());

            physicsSystem.destroy();

            jobSystem.destroy();
            tempAllocator.destroy();
            Jolt.destroyFactory();
        }
    }
}
