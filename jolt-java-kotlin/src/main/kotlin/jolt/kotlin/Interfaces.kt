package jolt.kotlin

import jolt.physics.body.BodyActivationListener
import jolt.physics.body.BodyActivationListenerFunctions
import jolt.physics.collision.ObjectLayerPairFilter
import jolt.physics.collision.ObjectLayerPairFilterFn
import jolt.physics.collision.broadphase.BroadPhaseLayerInterface
import jolt.physics.collision.broadphase.BroadPhaseLayerInterfaceFn
import jolt.physics.collision.broadphase.ObjectVsBroadPhaseLayerFilter
import jolt.physics.collision.broadphase.ObjectVsBroadPhaseLayerFilterFn
import java.lang.foreign.MemorySession

fun BroadPhaseLayerInterface(
    memory: MemorySession,
    getNumBroadPhaseLayers: () -> Int,
    getBroadPhaseLayer: (layer: ObjectLayer) -> BroadPhaseLayer,
) = BroadPhaseLayerInterface.of(memory, object :
    BroadPhaseLayerInterfaceFn {
    override fun getNumBroadPhaseLayers() = getNumBroadPhaseLayers()
    override fun getBroadPhaseLayer(layer: Short) = getBroadPhaseLayer(ObjectLayer(layer)).layer
})

fun ObjectVsBroadPhaseLayerFilter(
    memory: MemorySession,
    shouldCollide: (layer1: ObjectLayer, layer2: BroadPhaseLayer) -> Boolean,
) = ObjectVsBroadPhaseLayerFilter.of(memory, object :
    ObjectVsBroadPhaseLayerFilterFn {
    override fun shouldCollide(layer1: Short, layer2: Byte) = shouldCollide(ObjectLayer(layer1), BroadPhaseLayer(layer2))
})

fun ObjectLayerPairFilter(
    memory: MemorySession,
    shouldCollide: (layer1: ObjectLayer, layer2: ObjectLayer) -> Boolean,
) = ObjectLayerPairFilter.of(memory, object : ObjectLayerPairFilterFn {
    override fun shouldCollide(layer1: Short, layer2: Short) = shouldCollide(ObjectLayer(layer1), ObjectLayer(layer2))
})

fun BodyActivationListener(
    memory: MemorySession,
    onBodyActivated: (bodyId: BodyID, bodyUserData: Long) -> Unit,
    onBodyDeactivated: (bodyId: BodyID, bodyUserData: Long) -> Unit,
) = BodyActivationListener.of(memory, object : BodyActivationListenerFunctions {
    override fun onBodyActivated(bodyId: Int, bodyUserData: Long) = onBodyActivated(BodyID(bodyId), bodyUserData)
    override fun onBodyDeactivated(bodyId: Int, bodyUserData: Long) = onBodyDeactivated(BodyID(bodyId), bodyUserData)
})
