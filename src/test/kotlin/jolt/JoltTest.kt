package jolt

import jolt.core.JobSystemThreadPool
import jolt.core.RTTIFactory
import jolt.core.TempAllocatorImpl
import jolt.math.JtQuat
import jolt.math.JtVec3f
import jolt.physics.Activation
import jolt.physics.PhysicsSystem
import jolt.physics.body.*
import jolt.physics.collision.*
import jolt.physics.collision.broadphase.BroadPhaseLayer
import jolt.physics.collision.broadphase.BroadPhaseLayerInterface
import jolt.physics.collision.broadphase.ObjectVsBroadPhaseLayerFilter
import jolt.physics.collision.shape.BoxShapeSettings
import jolt.physics.collision.shape.SphereShape
import jolt.physics.collision.shape.SubShapeIdPair
import kotlin.test.Test

const val LAYER_NON_MOVING = 0
const val LAYER_MOVING = 1

class JoltTest {
    @Test
    fun testJolt() {
        JoltNativeLoader.load()

        JoltEnvironment.registerDefaultAllocator()
        RTTIFactory.setInstance(RTTIFactory())
        JoltEnvironment.registerTypes()
        val tempAllocator = TempAllocatorImpl.ofSize(10 * 1024 * 1024)
        val jobSystem = JobSystemThreadPool(2048, 8, Runtime.getRuntime().availableProcessors() - 1)

        val bpLayerNonMoving = BroadPhaseLayer.ofValue((0).toByte())
        val bpLayerMoving = BroadPhaseLayer.ofValue((1).toByte())
        val bpLayers = object : BroadPhaseLayerInterface() {
            override fun getNumBroadPhaseLayers() = 2
            override fun getBroadPhaseLayer(layer: Int) = when (layer) {
                LAYER_NON_MOVING -> bpLayerNonMoving
                LAYER_MOVING -> bpLayerMoving
                else -> throw IllegalArgumentException("Invalid layer $layer")
            }
            override fun getBroadPhaseLayerName(layer: BroadPhaseLayer) = when (layer) {
                bpLayerNonMoving -> "NON_MOVING"
                bpLayerMoving -> "MOVING"
                else -> throw IllegalArgumentException("Invalid layer $layer")
            }
        }

        val objBpLayerFilter = object : ObjectVsBroadPhaseLayerFilter() {
            override fun shouldCollide(layer1: Int, layer2: BroadPhaseLayer) = when (layer1) {
                LAYER_NON_MOVING -> layer2.value == LAYER_MOVING
                LAYER_MOVING -> true
                else -> false
            }
        }

        val objObjLayerFilter = object : ObjectLayerPairFilter() {
            override fun shouldCollide(layer1: Int, layer2: Int) = when (layer1) {
                LAYER_NON_MOVING -> layer2 == LAYER_MOVING
                LAYER_MOVING -> true
                else -> false
            }
        }

        val physSystem = PhysicsSystem()
        physSystem.init(
            1024, 0, 1024, 1024,
            bpLayers, objBpLayerFilter, objObjLayerFilter
        )

        val bodyActivationListener = object : BodyActivationListener() {
            override fun onBodyActivated(bodyID: BodyId, bodyUserData: Long) {
                println("A body got activated")
            }

            override fun onBodyDeactivated(bodyID: BodyId, bodyUserData: Long) {
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

        val floorSettings = BodyCreationSettings(floorShape, JtVec3f(0.0f, -1.0f, 0.0f), JtQuat.IDENTITY, MotionType.STATIC, LAYER_NON_MOVING)
        val floor = bodyInterface.createBody(floorSettings)

        bodyInterface.addBody(floor.id, Activation.DONT_ACTIVATE)

        val sphereSettings = BodyCreationSettings(SphereShape(0.5f), JtVec3f(0.0f, 2.0f, 0.0f), JtQuat.IDENTITY, MotionType.DYNAMIC, LAYER_MOVING)
        val sphereId = bodyInterface.createAndAddBody(sphereSettings, Activation.ACTIVATE)

        bodyInterface.setLinearVelocity(sphereId, JtVec3f(0.0f, -5.0f, 0.0f))

        val deltaTime = 1 / 60f
        physSystem.optimizeBroadPhase()

        var step = 0
        while (bodyInterface.isActive(sphereId)) {
            ++step

            val position = bodyInterface.getCenterOfMassPosition(sphereId)
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
        bpLayers.delete()
        jobSystem.delete()
        tempAllocator.delete()
        RTTIFactory.getInstance()?.delete()
        RTTIFactory.setInstance(null)
    }
}
