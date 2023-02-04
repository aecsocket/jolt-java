package jolt

import jolt.core.JobSystemThreadPool
import jolt.core.RTTIFactory
import jolt.core.TempAllocatorImpl
import jolt.math.JtVec3f
import jolt.physics.PhysicsSystem
import jolt.physics.body.Body
import jolt.physics.body.BodyActivationListener
import jolt.physics.body.BodyID
import jolt.physics.collision.*
import jolt.physics.collision.broadphase.BroadPhaseLayer
import jolt.physics.collision.broadphase.BroadPhaseLayerInterface
import jolt.physics.collision.broadphase.ObjectVsBroadPhaseLayerFilter
import jolt.physics.collision.shape.SubShapeIDPair
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
                else -> throw IllegalArgumentException("Invalid layer $layer / $bpLayerMoving")
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

        val physSystem = PhysicsSystem(
            1024, 0, 1024, 1024,
            bpLayers, objBpLayerFilter, objObjLayerFilter
        )

        val bodyActivationListener = object : BodyActivationListener() {
            override fun onBodyActivated(bodyID: BodyID, bodyUserData: Long) {
                println("A body got activated")
            }

            override fun onBodyDeactivated(bodyID: BodyID, bodyUserData: Long) {
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

            override fun onContactRemoved(subShapePair: SubShapeIDPair) {
                println("A contact was removed")
            }
        }
        physSystem.contactListener = contactListener

        val bodyInterface = physSystem.bodyInterface

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
