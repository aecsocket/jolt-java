package jolt;

import jolt.core.*;
import jolt.math.JtQuat;
import jolt.math.JtVec3d;
import jolt.math.JtVec3f;
import jolt.physics.Activation;
import jolt.physics.PhysicsSettings;
import jolt.physics.PhysicsSystem;
import jolt.physics.body.*;
import jolt.physics.collision.*;
import jolt.physics.collision.broadphase.BroadPhaseLayerInterface;
import jolt.physics.collision.broadphase.ObjectVsBroadPhaseLayerFilter;
import jolt.physics.collision.shape.*;
import org.junit.jupiter.api.Test;

import java.util.List;

public final class HelloJolt {
    // Kotlin: ObjectLayer(0)
    public static final int OBJECT_LAYER_NON_MOVING = 0;
    // Kotlin: ObjectLayer(1)
    public static final int OBJECT_LAYER_MOVING = 1;

    // Kotlin: BroadPhaseLayer(0)
    public static final int BP_LAYER_NON_MOVING = 0;
    // Kotlin: BroadPhaseLayer(1)
    public static final int BP_LAYER_MOVING = 1;

    @Test
    public void testJolt() {
        // handle SIGSEGV nostop noprint

        // Finds the platform-specific `.dll` or `.so` in the JAR, located in `jolt/`, and loads it
        JoltEnvironment.load();

        // Gets the features that the native library was compiled with
        List<JoltFeature> features = JoltEnvironment.featureList();
        boolean doublePrecision = JoltEnvironment.uses(JoltFeature.DOUBLE_PRECISION);
        System.out.println("Features: " + features);

        // Some Jolt setup - this must be performed in this exact order
        JoltEnvironment.registerDefaultAllocator();
        var rttiFactory = new RTTIFactory();
        RTTIFactory.setInstance(rttiFactory);
        JoltEnvironment.registerTypes();

        // Start setting up our physics system

        // The TempAllocator dedicates a block of memory to the physics simulation,
        // so that it does not have to be reallocated every time.
        // We allocate 10MB here.
        var tempAllocator = TempAllocatorImpl.ofBytes(10 * 1024 * 1024);
        // The JobSystem determines how the physics simulation will be run.
        // You should use your own implementation to run jobs on your own worker threads,
        // but a ThreadPool implementation is provided.
        var jobSystem = new JobSystemThreadPool(
                PhysicsSettings.MAX_PHYSICS_JOBS,
                PhysicsSettings.MAX_PHYSICS_BARRIERS,
                Math.min(16, Math.max(1, Runtime.getRuntime().availableProcessors() - 1))
        );

        // This maps broadphase layers to object layers.
        var bpLayerInterface = new BroadPhaseLayerInterface() {
            @Override
            public int getNumBroadPhaseLayers() {
                return 2;
            }

            // Kotlin: ObjectLayer(layer)
            @Override
            public byte getBroadPhaseLayer(int layer) {
                return switch (layer) {
                    case 0 -> BP_LAYER_NON_MOVING;
                    case 1 -> BP_LAYER_MOVING;
                    default -> throw new IllegalArgumentException("Invalid object layer " + layer);
                };
            }

            // This is only used in debug builds
            @Override
            public String getBroadPhaseLayerName(byte layer) {
                return switch (layer) {
                    case 0 -> "NON_MOVING";
                    case 1 -> "MOVING";
                    default -> "INVALID";
                };
            }
        };

        // Determines whether objects in an object layer collide with objects in a broadphase layer.
        var objBpLayerFilter = new ObjectVsBroadPhaseLayerFilter() {
            // Kotlin: ObjectLayer(layer1), BroadPhaseLayer(layer2)
            @Override
            public boolean shouldCollide(int layer1, byte layer2) {
                return switch (layer1) {
                    case OBJECT_LAYER_NON_MOVING -> layer2 == BP_LAYER_MOVING;
                    case OBJECT_LAYER_MOVING -> true;
                    default -> false;
                };
            }
        };

        // Determines whether objects in one object layer collide with objects in another object layer.
        var objLayerPairFilter = new ObjectLayerPairFilter() {
            // Kotlin: ObjectLayer(layer1), ObjectLayer(layer2)
            @Override
            public boolean shouldCollide(int layer1, int layer2) {
                return switch (layer1) {
                    case OBJECT_LAYER_NON_MOVING -> layer2 == OBJECT_LAYER_MOVING;
                    case OBJECT_LAYER_MOVING -> true;
                    default -> false;
                };
            }
        };

        // Allocates and creates our PhysicsSystem in which bodies will be simulated
        var physicsSystem = new PhysicsSystem();
        physicsSystem.init(
                // maxBodies: maximum number of bodies in the system - adding more will throw an exception
                // for a real project use ~65536
                1024,
                // numBodyMutexes: how many mutexes are allocated to protect rigid bodies from concurrent access
                // use 0 for default settings
                0,
                // maxBodyPairs: maximum amount of body pairs that can be queued at any time
                // if this is too small, broad phase jobs will do narrow phase work
                // for a real project use ~65536
                1024,
                // maxContactConstraints: maximum size of the contact constraint buffer
                // if this is too small, bodies will interpenetrate or fall through the world
                // for a real project use ~10240
                // NOTE: if this value is too large for the TempAllocator to contain,
                // the JVM will crash
                1024,
                bpLayerInterface, objBpLayerFilter, objLayerPairFilter
        );

        // Optional: called when bodies activate and go to sleep
        // The callbacks here must be thread safe
        var bodyActivationListener = new BodyActivationListener() {
            // Kotlin: BodyId(bodyId)
            @Override
            public void onBodyActivated(int bodyId, long bodyUserData) {
                System.out.println("A body got activated");
            }

            // Kotlin: BodyId(bodyId)
            @Override
            public void onBodyDeactivated(int bodyId, long bodyUserData) {
                System.out.println("A body went to sleep");
            }
        };
        physicsSystem.setBodyActivationListener(bodyActivationListener);

        // Optional: called when bodies (are about to) collide and separate
        // The callbacks here must be thread safe
        var contactListener = new ContactListener() {
            @Override
            public ValidateResult onContactValidate(Body body1, Body body2, JtVec3f baseOffset, CollideShapeResult collisionResult) {
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
            public void onContactRemoved(SubShapeIdPair subShapePair) {
                System.out.println("A contact was removed");
            }
        };
        physicsSystem.setContactListener(contactListener);

        // The BodyInterface is the main way to interact with bodies
        // Here we use the locking variant, but if you are an advanced user you can use the -NoLock version
        BodyInterface bodyInterface = physicsSystem.getBodyInterface();

        // Create the rigid body for the floor

        // Create the settings for the box shape we are about to make
        // NOTE: The math classes provided by JoltJNI (Jt-) are purely data wrappers around Jolt types.
        // They do not provide linear algebra functionality, and you are expected to convert to/from your own types.
        // In matrices, the 4th row 4th col element is always 1.0.
        var floorShapeSettings = new BoxShapeSettings(new JtVec3f(100.0f, 1.0f, 100.0f));
        // And create the shape itself - this may throw an exception
        Shape floorShape = floorShapeSettings.create();

        // Create the settings for the rigid body
        // NOTE: This is precision-dependent. Use `sp` for single precision, or `dp` for double precision.
        // If you know for certain which precision level you are dealing with, you don't have to have a conditional call here.
        // However this is conditional since this is a test case.
        // Kotlin: BodyCreationSettingsDp/Sp(..., ObjectLayer)
        var floorSettings = doublePrecision
                ? BodyCreationSettings.dp(floorShape, new JtVec3d(0.0, -1.0, 0.0), JtQuat.identity(), MotionType.STATIC, OBJECT_LAYER_NON_MOVING)
                : BodyCreationSettings.sp(floorShape, new JtVec3f(0.0f, -1.0f, 0.0f), JtQuat.identity(), MotionType.STATIC, OBJECT_LAYER_NON_MOVING);
        // Create the rigid body itself - this may throw an exception if there are no bodies left
        // (as opposed to Jolt, which will return null)
        Body floor = bodyInterface.createBody(floorSettings);
        // Add the body to the world via its ID, and it is not active by default
        bodyInterface.addBody(floor.getId(), Activation.DONT_ACTIVATE);

        // Shorthand version of the above, creating a dynamic sphere
        var sphereSettings = doublePrecision
                ? BodyCreationSettings.dp(new SphereShape(0.5f), new JtVec3d(0.0, 2.0, 0.0), JtQuat.identity(), MotionType.DYNAMIC, OBJECT_LAYER_MOVING)
                : BodyCreationSettings.sp(new SphereShape(0.5f), new JtVec3f(0.0f, 2.0f, 0.0f), JtQuat.identity(), MotionType.DYNAMIC, OBJECT_LAYER_MOVING);
        int sphereId = bodyInterface.createAndAddBody(sphereSettings, Activation.ACTIVATE);

        bodyInterface.setLinearVelocity(sphereId, new JtVec3f(0.0f, -5.0f, 0.0f));

        var deltaTime = 1 / 60.0f;

        // Optional: improve collision detection performance after adding a batch of objects
        physicsSystem.optimizeBroadPhase();

        int step = 0;
        while (bodyInterface.isActive(sphereId)) {
            ++step;

            // Use Object here just so we don't tie ourselves to either JtVec3f ot JtVec3d
            Object position = doublePrecision
                    ? bodyInterface.getCenterOfMassPositionDp(sphereId)
                    : bodyInterface.getCenterOfMassPositionSp(sphereId);
            // But most other values, apart from position, will be single-precision only
            JtVec3f velocity = bodyInterface.getLinearVelocity(sphereId);

            System.out.println("Step " + step + ": Position = " + position + ", Velocity = " + velocity);

            physicsSystem.update(
                    // deltaTime: time step in seconds to update
                    deltaTime,
                    // collisionSteps: if you take larger steps than 1/60th of a second,
                    // you need to do multiple collision steps
                    1,
                    // integrationSubSteps: if you want more accurate step results, increase this
                    1,
                    tempAllocator,
                    jobSystem
            );
        }

        // Remove the sphere, but it is not destroyed yet! It can be re-added at any time.
        bodyInterface.removeBody(sphereId);
        // Destroy the body, so its ID is now invalid.
        bodyInterface.destroyBody(sphereId);

        bodyInterface.removeBody(floor.getId());
        bodyInterface.destroyBody(floor.getId());

        // Clean up memory
        contactListener.delete();
        bodyActivationListener.delete();
        physicsSystem.delete();
        objLayerPairFilter.delete();
        objBpLayerFilter.delete();
        bpLayerInterface.delete();
        jobSystem.delete();
        tempAllocator.delete();
        rttiFactory.delete();
        RTTIFactory.setInstance(null);
    }
}