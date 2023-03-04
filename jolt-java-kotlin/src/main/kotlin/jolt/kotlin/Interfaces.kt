package jolt.kotlin

import jolt.math.DVec3
import jolt.math.FVec3
import jolt.physics.body.Body
import jolt.physics.body.BodyActivationListener
import jolt.physics.body.BodyActivationListenerFn
import jolt.physics.collision.*
import jolt.physics.collision.broadphase.BroadPhaseLayerFilter
import jolt.physics.collision.broadphase.BroadPhaseLayerFilterFn
import jolt.physics.collision.broadphase.BroadPhaseLayerInterface
import jolt.physics.collision.broadphase.BroadPhaseLayerInterfaceFn
import jolt.physics.collision.broadphase.ObjectVsBroadPhaseLayerFilter
import jolt.physics.collision.broadphase.ObjectVsBroadPhaseLayerFilterFn
import jolt.physics.collision.shape.SubShapeIdPair
import java.lang.foreign.MemorySession

fun BroadPhaseLayerInterface(
    arena: MemorySession,
    getNumBroadPhaseLayers: () -> Int,
    getBroadPhaseLayer: (layer: ObjectLayer) -> BroadPhaseLayer,
) = BroadPhaseLayerInterface.of(arena, object :
    BroadPhaseLayerInterfaceFn {
    override fun getNumBroadPhaseLayers() = getNumBroadPhaseLayers()
    override fun getBroadPhaseLayer(layer: Short) = getBroadPhaseLayer(ObjectLayer(layer)).layer
})

fun ObjectVsBroadPhaseLayerFilter(
    arena: MemorySession,
    shouldCollide: (layer1: ObjectLayer, layer2: BroadPhaseLayer) -> Boolean,
) = ObjectVsBroadPhaseLayerFilter.of(arena, object : ObjectVsBroadPhaseLayerFilterFn {
    override fun shouldCollide(layer1: Short, layer2: Byte) = shouldCollide(ObjectLayer(layer1), BroadPhaseLayer(layer2))
})

fun BroadPhaseLayerFilter(
    arena: MemorySession,
    shouldCollide: (layer: BroadPhaseLayer) -> Boolean,
) = BroadPhaseLayerFilter.of(arena, object : BroadPhaseLayerFilterFn {
    override fun shouldCollide(layer: Byte) = shouldCollide(BroadPhaseLayer(layer))
})

fun ObjectLayerPairFilter(
    arena: MemorySession,
    shouldCollide: (layer1: ObjectLayer, layer2: ObjectLayer) -> Boolean,
) = ObjectLayerPairFilter.of(arena, object : ObjectLayerPairFilterFn {
    override fun shouldCollide(layer1: Short, layer2: Short) = shouldCollide(ObjectLayer(layer1), ObjectLayer(layer2))
})

fun ObjectLayerFilter(
    arena: MemorySession,
    shouldCollide: (layer: ObjectLayer) -> Boolean,
) = ObjectLayerFilter.of(arena, object : ObjectLayerFilterFn {
    override fun shouldCollide(layer: Short) = shouldCollide(ObjectLayer(layer))
})

fun BodyActivationListener(
    arena: MemorySession,
    onBodyActivated: (bodyId: BodyId, bodyUserData: Long) -> Unit,
    onBodyDeactivated: (bodyId: BodyId, bodyUserData: Long) -> Unit,
) = BodyActivationListener.of(arena, object : BodyActivationListenerFn {
    override fun onBodyActivated(bodyId: Int, bodyUserData: Long) = onBodyActivated(BodyId(bodyId), bodyUserData)
    override fun onBodyDeactivated(bodyId: Int, bodyUserData: Long) = onBodyDeactivated(BodyId(bodyId), bodyUserData)
})

fun BodyFilter(
    arena: MemorySession,
    shouldCollide: (bodyId: BodyId) -> Boolean,
    shouldCollideLocked: (body: Body) -> Boolean,
) = BodyFilter.of(arena, object : BodyFilterFn {
    override fun shouldCollide(bodyId: Int) = shouldCollide(BodyId(bodyId))
    override fun shouldCollideLocked(body: Body) = shouldCollideLocked(body)
})

fun ShapeFilter(
    arena: MemorySession,
    shouldCollide: (subShapeId: SubShapeId) -> Boolean,
    shouldPairCollide: (subShapeId1: SubShapeId, subShapeId2: SubShapeId) -> Boolean,
) = ShapeFilter.of(arena, object : ShapeFilterFn {
    override fun shouldPairCollide(subShapeId: Int) = shouldCollide(SubShapeId(subShapeId))
    override fun shouldPairCollide(subShapeId1: Int, subShapeId2: Int) = shouldPairCollide(SubShapeId(subShapeId1), SubShapeId(subShapeId2))
})

@JvmName("ContactListenerF")
fun ContactListener(
    arena: MemorySession,
    onContactValidate: (body1: BodyId, body2: BodyId, baseOffset: FVec3, collisionResult: CollideShapeResult) -> ValidateResult,
    onContactAdded: (body1: BodyId, body2: BodyId, manifold: ContactManifold, settings: ContactSettings) -> Unit,
    onContactPersisted: (body1: BodyId, body2: BodyId, manifold: ContactManifold, settings: ContactSettings) -> Unit,
    onContactRemoved: (subShapeIdPair: SubShapeIdPair) -> Unit,
) = ContactListener.of(arena, object : ContactListenerFn.F {
    override fun onContactValidate(
        body1: Int,
        body2: Int,
        baseOffset: FVec3,
        collisionResult: CollideShapeResult
    ) = onContactValidate(BodyId(body1), BodyId(body2), baseOffset, collisionResult)
    override fun onContactAdded(body1: Int, body2: Int, manifold: ContactManifold, settings: ContactSettings) =
        onContactAdded(BodyId(body1), BodyId(body2), manifold, settings)
    override fun onContactPersisted(body1: Int, body2: Int, manifold: ContactManifold, settings: ContactSettings) =
        onContactPersisted(BodyId(body1), BodyId(body2), manifold, settings)
    override fun onContactRemoved(subShapeIdPair: SubShapeIdPair) =
        onContactRemoved(subShapeIdPair)
})

@JvmName("ContactListenerD")
fun ContactListener(
    arena: MemorySession,
    onContactValidate: (body1: BodyId, body2: BodyId, baseOffset: DVec3, collisionResult: CollideShapeResult) -> ValidateResult,
    onContactAdded: (body1: BodyId, body2: BodyId, manifold: ContactManifold, settings: ContactSettings) -> Unit,
    onContactPersisted: (body1: BodyId, body2: BodyId, manifold: ContactManifold, settings: ContactSettings) -> Unit,
    onContactRemoved: (subShapeIdPair: SubShapeIdPair) -> Unit,
) = ContactListener.of(arena, object : ContactListenerFn.D {
    override fun onContactValidate(
        body1: Int,
        body2: Int,
        baseOffset: DVec3,
        collisionResult: CollideShapeResult
    ) = onContactValidate(BodyId(body1), BodyId(body2), baseOffset, collisionResult)
    override fun onContactAdded(body1: Int, body2: Int, manifold: ContactManifold, settings: ContactSettings) =
        onContactAdded(BodyId(body1), BodyId(body2), manifold, settings)
    override fun onContactPersisted(body1: Int, body2: Int, manifold: ContactManifold, settings: ContactSettings) =
        onContactPersisted(BodyId(body1), BodyId(body2), manifold, settings)
    override fun onContactRemoved(subShapeIdPair: SubShapeIdPair) =
        onContactRemoved(subShapeIdPair)
})
