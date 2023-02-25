package jolt.kotlin

import jolt.math.*
import jolt.physics.Activation
import jolt.physics.body.Body
import jolt.physics.body.BodyIDs
import jolt.physics.body.BodyInterface
import jolt.physics.body.BodyLockInterface
import jolt.physics.body.BodyLockRead
import jolt.physics.body.BodyLockWrite
import jolt.physics.collision.shape.Shape

@JvmInline
value class BodyID(val id: Int) {
    val index get() = BodyIDs.getIndex(id)
    val sequenceNumber get() = BodyIDs.getSequenceNumber(id)

    fun isValid() = BodyIDs.isValid(id)
}

val Body.bodyId get() = BodyID(id)

fun BodyLockRead(bodyLockInterface: BodyLockInterface, bodyId: BodyID) =
    BodyLockRead(bodyLockInterface, bodyId.id)

fun BodyLockWrite(bodyLockInterface: BodyLockInterface, bodyId: BodyID) =
    BodyLockWrite(bodyLockInterface, bodyId.id)

fun BodyInterface.unassignBodyId(bodyId: BodyID) =
    unassignBodyId(bodyId.id)
fun BodyInterface.unassignBodyIds(bodyIds: Collection<BodyID>) =
    unassignBodyIds(bodyIds.map { it.id })
fun BodyInterface.destroyBody(bodyId: BodyID) =
    destroyBody(bodyId.id)
fun BodyInterface.destroyBodies(bodyIds: Collection<BodyID>) =
    destroyBodies(bodyIds.map { it.id })
fun BodyInterface.addBody(bodyId: BodyID, activationMode: Activation) =
    addBody(bodyId.id, activationMode)
fun BodyInterface.removeBody(bodyId: BodyID) =
    removeBody(bodyId.id)
fun BodyInterface.isAdded(bodyId: BodyID) =
    isAdded(bodyId.id)
fun BodyInterface.activateBody(bodyId: BodyID) =
    activateBody(bodyId.id)
fun BodyInterface.activateBodies(bodyIds: Collection<BodyID>) =
    activateBodies(bodyIds.map { it.id })
fun BodyInterface.deactivateBody(bodyId: BodyID) =
    deactivateBody(bodyId.id)
fun BodyInterface.deactivateBodies(bodyIds: Collection<BodyID>) =
    deactivateBodies(bodyIds.map { it.id })
fun BodyInterface.isActive(bodyId: BodyID) =
    isActive(bodyId.id)
fun BodyInterface.setShape(bodyId: BodyID, shape: Shape, updateMassProperties: Boolean, activationMode: Activation) =
    setShape(bodyId.id, shape, updateMassProperties, activationMode)
fun BodyInterface.notifyShapeChanged(bodyId: BodyID, previousCenterOfMass: JtVec3f, updateMassProperties: Boolean, activationMode: Activation) =
    notifyShapeChanged(bodyId.id, previousCenterOfMass, updateMassProperties, activationMode)
fun BodyInterface.getObjectLayer(bodyId: BodyID) =
    ObjectLayer(getObjectLayer(bodyId.id))
fun BodyInterface.setObjectLayer(bodyId: BodyID, layer: ObjectLayer) =
    setObjectLayer(bodyId.id, layer.id)
fun BodyInterface.setPositionAndRotationSp(bodyId: BodyID, position: JtVec3f, rotation: JtQuat, activationMode: Activation) =
    setPositionAndRotationSp(bodyId.id, position, rotation, activationMode)
fun BodyInterface.setPositionAndRotationDp(bodyId: BodyID, position: JtVec3d, rotation: JtQuat, activationMode: Activation) =
    setPositionAndRotationDp(bodyId.id, position, rotation, activationMode)
fun BodyInterface.setPositionAndRotationWhenChangedSp(bodyId: BodyID, position: JtVec3f, rotation: JtQuat, activationMode: Activation) =
    setPositionAndRotationWhenChangedSp(bodyId.id, position, rotation, activationMode)
fun BodyInterface.setPositionAndRotationWhenChangedDp(bodyId: BodyID, position: JtVec3d, rotation: JtQuat, activationMode: Activation) =
    setPositionAndRotationWhenChangedDp(bodyId.id, position, rotation, activationMode)
fun BodyInterface.getPositionAndRotationSp(bodyId: BodyID, outPosition: JtVec3f, outRotation: JtQuat) =
    getPositionAndRotationSp(bodyId.id, outPosition, outRotation)
fun BodyInterface.getPositionAndRotationDp(bodyId: BodyID, outPosition: JtVec3d, outRotation: JtQuat) =
    getPositionAndRotationDp(bodyId.id, outPosition, outRotation)
fun BodyInterface.setPositionSp(bodyId: BodyID, value: JtVec3f, activationMode: Activation) =
    setPositionSp(bodyId.id, value, activationMode)
fun BodyInterface.setPositionDp(bodyId: BodyID, value: JtVec3d, activationMode: Activation) =
    setPositionDp(bodyId.id, value, activationMode)
fun BodyInterface.getPositionSp(bodyId: BodyID, out: JtVec3f) =
    getPositionSp(bodyId.id, out)
fun BodyInterface.getPositionSp(bodyId: BodyID) =
    getPositionSp(bodyId.id)
fun BodyInterface.getPositionDp(bodyId: BodyID, out: JtVec3d) =
    getPositionDp(bodyId.id, out)
fun BodyInterface.getPositionDp(bodyId: BodyID) =
    getPositionDp(bodyId.id)
fun BodyInterface.getCenterOfMassPositionSp(bodyId: BodyID, out: JtVec3f) =
    getCenterOfMassPositionSp(bodyId.id, out)
fun BodyInterface.getCenterOfMassPositionSp(bodyId: BodyID) =
    getCenterOfMassPositionSp(bodyId.id)
fun BodyInterface.getCenterOfMassPositionDp(bodyId: BodyID, out: JtVec3d) =
    getCenterOfMassPositionDp(bodyId.id, out)
fun BodyInterface.getCenterOfMassPositionDp(bodyId: BodyID) =
    getCenterOfMassPositionDp(bodyId.id)
fun BodyInterface.setRotation(bodyId: BodyID, rotation: JtQuat, activationMode: Activation) =
    setRotation(bodyId.id, rotation, activationMode)
fun BodyInterface.getRotation(bodyId: BodyID, out: JtQuat) =
    getRotation(bodyId.id, out)
fun BodyInterface.getRotation(bodyId: BodyID) =
    getRotation(bodyId.id)
fun BodyInterface.getWorldTransformSp(bodyId: BodyID, out: JtMat44f) =
    getWorldTransformSp(bodyId.id, out)
fun BodyInterface.getWorldTransformSp(bodyId: BodyID) =
    getWorldTransformSp(bodyId.id)
fun BodyInterface.getWorldTransformDp(bodyId: BodyID, out: JtMat44d) =
    getWorldTransformDp(bodyId.id, out)
fun BodyInterface.getWorldTransformDp(bodyId: BodyID) =
    getWorldTransformDp(bodyId.id)
fun BodyInterface.getCenterOfMassTransformSp(bodyId: BodyID, out: JtMat44f) =
    getCenterOfMassTransformSp(bodyId.id, out)
fun BodyInterface.getCenterOfMassTransformSp(bodyId: BodyID) =
    getCenterOfMassTransformSp(bodyId.id)
fun BodyInterface.getCenterOfMassTransformDp(bodyId: BodyID, out: JtMat44d) =
    getCenterOfMassTransformDp(bodyId.id, out)
fun BodyInterface.getCenterOfMassTransformDp(bodyId: BodyID) =
    getCenterOfMassTransformDp(bodyId.id)
// TODO the rest
