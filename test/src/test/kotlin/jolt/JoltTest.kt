package jolt

import kotlin.test.Test

const val LAYER_NON_MOVING = 0
const val LAYER_MOVING = 1

class JoltTest {
    @Test
    fun testJolt() {
        JoltNativeLoader.load()

        JoltEnvironment.registerDefaultAllocator()
        RTTIFactory.instance(RTTIFactory())
        JoltEnvironment.registerTypes()
        val tempAllocator = TempAllocatorImpl.ofSize(10 * 1024 * 1024)
        val jobSystem = JobSystemThreadPool(2048, 8, Runtime.getRuntime().availableProcessors() - 1)

        val bpLayerNonMoving = BroadPhaseLayer((0).toByte())
        val bpLayerMoving = BroadPhaseLayer(1L)
        val bpLayers = object : BroadPhaseLayerInterface() {
            override fun getNumBroadPhaseLayers() = 2
            override fun getBroadPhaseLayer(layer: Int) = when (layer) {
                LAYER_NON_MOVING -> bpLayerNonMoving
                LAYER_MOVING -> bpLayerMoving
                else -> throw RuntimeException()
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

        /*val bodyActivationListener = object : BodyActivationListener() {
            override fun onBodyActivated(bodyID: BodyID, bodyUserData: Long) {
                println("A body got activated")
            }

            override fun onBodyDeactivated(bodyID: BodyID, bodyUserData: Long) {
                println("A body went to sleep")
            }
        }
        physSystem.bodyActivationListener = bodyActivationListener

        bodyActivationListener.destroy()*/
        physSystem.destroy()
        objObjLayerFilter.destroy()
        objBpLayerFilter.destroy()
        bpLayers.destroy()
        tempAllocator.destroy()
        jobSystem.destroy()
        RTTIFactory.instance()?.destroy()
        RTTIFactory.instance(null)
    }
}
