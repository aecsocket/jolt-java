package jolt.kotlin

import jolt.physics.collision.ObjectLayerPairFilter
import jolt.physics.collision.ObjectLayerPairFilterFunctions
import jolt.physics.collision.broadphase.BroadPhaseLayerInterface
import jolt.physics.collision.broadphase.BroadPhaseLayerInterfaceFunctions
import jolt.physics.collision.broadphase.ObjectVsBroadPhaseLayerFilter
import jolt.physics.collision.broadphase.ObjectVsBroadPhaseLayerFilterFunctions
import java.lang.foreign.MemorySession

fun BroadPhaseLayerInterface(
    memory: MemorySession,
    getNumBroadPhaseLayers: () -> Int,
    getBroadPhaseLayer: (layer: ObjectLayer) -> BroadPhaseLayer,
) = BroadPhaseLayerInterface.of(memory, object : BroadPhaseLayerInterfaceFunctions {
    override fun getNumBroadPhaseLayers() = getNumBroadPhaseLayers()
    override fun getBroadPhaseLayer(layer: Short) = getBroadPhaseLayer(ObjectLayer(layer)).layer
})

fun ObjectVsBroadPhaseLayerFilter(
    memory: MemorySession,
    shouldCollide: (layer1: ObjectLayer, layer2: BroadPhaseLayer) -> Boolean,
) = ObjectVsBroadPhaseLayerFilter.of(memory, object : ObjectVsBroadPhaseLayerFilterFunctions {
    override fun shouldCollide(layer1: Short, layer2: Byte) = shouldCollide(ObjectLayer(layer1), BroadPhaseLayer(layer2))
})

fun ObjectLayerPairFilter(
    memory: MemorySession,
    shouldCollide: (layer1: ObjectLayer, layer2: ObjectLayer) -> Boolean,
) = ObjectLayerPairFilter.of(memory, object : ObjectLayerPairFilterFunctions {
    override fun shouldCollide(layer1: Short, layer2: Short) = shouldCollide(ObjectLayer(layer1), ObjectLayer(layer2))
})
