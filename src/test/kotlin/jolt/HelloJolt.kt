package jolt

import jolt.core.JobSystemThreadPool
import jolt.core.RTTIFactory
import jolt.core.TempAllocatorImpl
import jolt.kotlin.BodyCreationSettingsDp
import jolt.kotlin.BodyCreationSettingsSp
import jolt.kotlin.BroadPhaseLayer
import jolt.kotlin.ObjectLayer
import jolt.math.JtQuat
import jolt.math.JtVec3d
import jolt.math.JtVec3f
import jolt.physics.Activation
import jolt.physics.PhysicsSettings
import jolt.physics.PhysicsSystem
import jolt.physics.body.*
import jolt.physics.collision.*
import jolt.physics.collision.broadphase.BroadPhaseLayerInterface
import jolt.physics.collision.broadphase.ObjectVsBroadPhaseLayerFilter
import jolt.physics.collision.shape.BoxShapeSettings
import jolt.physics.collision.shape.SphereShape
import jolt.physics.collision.shape.SubShapeIdPair
import kotlin.test.Test

val OBJECT_LAYER_NON_MOVING = ObjectLayer(0)
val OBJECT_LAYER_MOVING = ObjectLayer(1)

val BP_LAYER_NON_MOVING = BroadPhaseLayer(0)
val BP_LAYER_MOVING = BroadPhaseLayer(1)

class HelloJolt {
    @Test
    fun testJolt() {
        JoltNativeLoader.load()

        val features = JoltEnvironment.features()
        val doublePrecision = JoltEnvironment.uses(JoltFeature.DOUBLE_PRECISION)
        println("Features: ${features.joinToString(" ")}")

        JoltEnvironment.registerDefaultAllocator()
        RTTIFactory.setInstance(RTTIFactory())
        JoltEnvironment.registerTypes()
        val tempAllocator = TempAllocatorImpl.ofBytes(10 * 1024 * 1024)
        val jobSystem = JobSystemThreadPool(
            PhysicsSettings.MAX_PHYSICS_JOBS,
            PhysicsSettings.MAX_PHYSICS_BARRIERS,
            Runtime.getRuntime().availableProcessors() - 1
        )

        val bpLayerInterface = object : BroadPhaseLayerInterface() {
            override fun getNumBroadPhaseLayers() = 2
            override fun getBroadPhaseLayer(layer: Int) = when (ObjectLayer(layer)) {
                OBJECT_LAYER_NON_MOVING -> BP_LAYER_NON_MOVING
                OBJECT_LAYER_MOVING -> BP_LAYER_MOVING
                else -> throw IllegalArgumentException("Invalid layer $layer")
            }.id
            override fun getBroadPhaseLayerName(layer: Byte) = when (BroadPhaseLayer(layer)) {
                BP_LAYER_NON_MOVING -> "NON_MOVING"
                BP_LAYER_MOVING -> "MOVING"
                else -> throw IllegalArgumentException("Invalid layer $layer")
            }
        }

        val objBpLayerFilter = object : ObjectVsBroadPhaseLayerFilter() {
            override fun shouldCollide(layer1: Int, layer2: Byte) = when (ObjectLayer(layer1)) {
                OBJECT_LAYER_NON_MOVING -> BroadPhaseLayer(layer2) == BP_LAYER_MOVING
                OBJECT_LAYER_MOVING -> true
                else -> false
            }
        }

        val objObjLayerFilter = object : ObjectLayerPairFilter() {
            override fun shouldCollide(layer1: Int, layer2: Int) = when (ObjectLayer(layer1)) {
                OBJECT_LAYER_NON_MOVING -> ObjectLayer(layer2) == OBJECT_LAYER_MOVING
                OBJECT_LAYER_MOVING -> true
                else -> false
            }
        }

        val physSystem = PhysicsSystem()
        physSystem.init(
            1024, 0, 1024, 1024,
            bpLayerInterface, objBpLayerFilter, objObjLayerFilter
        )

        val bodyActivationListener = object : BodyActivationListener() {
            override fun onBodyActivated(bodyID: BodyIds, bodyUserData: Long) {
                println("A body got activated")
            }

            override fun onBodyDeactivated(bodyID: BodyIds, bodyUserData: Long) {
                println("A body went to sleep")
            }
        }
        physSystem.bodyActivationListener = bodyActivationListener

        val contactListener = object : ContactListener() {
            override fun onContactValidate(
                body1: Body,
                body2: Body,
                baseOffset: JtVec3f,
                collisionResult: CollideShapeResult
            ): ValidateResult {
                println("Contact validate callback")
                return ValidateResult.ACCEPT_ALL_CONTACTS_FOR_THIS_BODY_PAIR
            }

            override fun onContactAdded(
                body1: Body,
                body2: Body,
                manifold: ContactManifold,
                settings: ContactSettings
            ) {
                println("A contact was added")
            }

            override fun onContactPersisted(
                body1: Body,
                body2: Body,
                manifold: ContactManifold,
                settings: ContactSettings
            ) {
                println("A contact was persisted")
            }

            override fun onContactRemoved(subShapePair: SubShapeIdPair) {
                println("A contact was removed")
            }
        }
        physSystem.contactListener = contactListener

        val bodyInterface = physSystem.bodyInterface

        val floorShapeSettings = BoxShapeSettings(JtVec3f(100.0f, 1.0f, 100.0f))
        val floorShape = floorShapeSettings.create()

        val floorSettings =
            if (doublePrecision) BodyCreationSettingsDp(floorShape, JtVec3d(0.0, -1.0, 0.0), JtQuat.IDENTITY, MotionType.STATIC, OBJECT_LAYER_NON_MOVING)
            else BodyCreationSettingsSp(floorShape, JtVec3f(0.0f, -1.0f, 0.0f), JtQuat.IDENTITY, MotionType.STATIC, OBJECT_LAYER_NON_MOVING)
        val floor = bodyInterface.createBody(floorSettings)

        bodyInterface.addBody(floor.id, Activation.DONT_ACTIVATE)

        val sphereSettings =
            if (doublePrecision) BodyCreationSettingsDp(SphereShape(0.5f), JtVec3d(0.0, 2.0, 0.0), JtQuat.IDENTITY, MotionType.DYNAMIC, OBJECT_LAYER_MOVING)
            else BodyCreationSettingsSp(SphereShape(0.5f), JtVec3f(0.0f, 2.0f, 0.0f), JtQuat.IDENTITY, MotionType.DYNAMIC, OBJECT_LAYER_MOVING)
        val sphereId = bodyInterface.createAndAddBody(sphereSettings, Activation.ACTIVATE)

        bodyInterface.setLinearVelocity(sphereId, JtVec3f(0.0f, -5.0f, 0.0f))

        val deltaTime = 1 / 60f
        physSystem.optimizeBroadPhase()

        var step = 0
        while (bodyInterface.isActive(sphereId)) {
            ++step

            val position: Any =
                if (doublePrecision) bodyInterface.getCenterOfMassPositionDp(sphereId)
                else bodyInterface.getCenterOfMassPositionSp(sphereId)
            val velocity = bodyInterface.getLinearVelocity(sphereId)
            println("Step $step: Position = $position, Velocity = $velocity")

            val collisionSteps = 1
            val integrationSubSteps = 1

            physSystem.update(deltaTime, collisionSteps, integrationSubSteps, tempAllocator, jobSystem)
        }

        bodyInterface.removeBody(sphereId)
        bodyInterface.destroyBody(sphereId)

        bodyInterface.removeBody(floor.id)
        bodyInterface.destroyBody(floor.id)

        contactListener.delete()
        bodyActivationListener.delete()
        physSystem.delete()
        objObjLayerFilter.delete()
        objBpLayerFilter.delete()
        bpLayerInterface.delete()
        jobSystem.delete()
        tempAllocator.delete()
        RTTIFactory.getInstance()?.delete()
        RTTIFactory.setInstance(null)
    }
}
