package jolt.kotlin

import jolt.math.JtQuat
import jolt.math.JtVec3d
import jolt.math.JtVec3f
import jolt.physics.Activation
import jolt.physics.body.Body
import jolt.physics.body.BodyIDs
import jolt.physics.body.BodyInterface
import jolt.physics.body.BodyLockInterface
import jolt.physics.body.BodyLockRead
import jolt.physics.body.BodyLockWrite
import jolt.physics.body.MutableBodyInterface

@JvmInline
value class BodyID(val id: Int) {
    val index get() = BodyIDs.getIndex(id)
    val sequenceNumber get() = BodyIDs.getSequenceNumber(id)

    fun isValid() = BodyIDs.isValid(id)
}

val Body.bodyId get() = BodyID(id)

fun BodyLockRead(bodyLockInterface: BodyLockInterface, id: BodyID) =
    BodyLockRead(bodyLockInterface, id.id)

fun BodyLockWrite(bodyLockInterface: BodyLockInterface, id: BodyID) =
    BodyLockWrite(bodyLockInterface, id.id)

fun MutableBodyInterface.destroyBody(id: BodyID) =
    destroyBody(id.id)

fun MutableBodyInterface.addBody(id: BodyID, activationMode: Activation) =
    addBody(id.id, activationMode)
fun MutableBodyInterface.removeBody(id: BodyID) =
    removeBody(id.id)
fun BodyInterface.isAdded(id: BodyID) =
    isAdded(id.id)

fun BodyInterface.isActive(id: BodyID) =
    isActive(id.id)
fun MutableBodyInterface.activateBody(id: BodyID) =
    activateBody(id.id)
fun MutableBodyInterface.deactivateBody(id: BodyID) =
    deactivateBody(id.id)

fun BodyInterface.getPositionAndRotationSp(id: BodyID, outPosition: JtVec3f, outRotation: JtQuat) =
    getPositionAndRotationSp(id.id, outPosition, outRotation)
fun BodyInterface.getPositionAndRotationDp(id: BodyID, outPosition: JtVec3d, outRotation: JtQuat) =
    getPositionAndRotationDp(id.id, outPosition, outRotation)
fun MutableBodyInterface.setPositionAndRotationSp(id: BodyID, position: JtVec3f, rotation: JtQuat, activationMode: Activation) =
    setPositionAndRotationSp(id.id, position, rotation, activationMode)
fun MutableBodyInterface.setPositionAndRotationDp(id: BodyID, position: JtVec3d, rotation: JtQuat, activationMode: Activation) =
    setPositionAndRotationDp(id.id, position, rotation, activationMode)

fun BodyInterface.getPositionSp(id: BodyID, out: JtVec3f) =
    getPositionSp(id.id, out)
fun BodyInterface.getPositionSp(id: BodyID) =
    getPositionSp(id.id)
fun BodyInterface.getPositionDp(id: BodyID, out: JtVec3d) =
    getPositionDp(id.id, out)
fun BodyInterface.getPositionDp(id: BodyID) =
    getPositionDp(id.id)
fun MutableBodyInterface.setPositionSp(id: BodyID, value: JtVec3f, activationMode: Activation) =
    setPositionSp(id.id, value, activationMode)
fun MutableBodyInterface.setPositionDp(id: BodyID, value: JtVec3d, activationMode: Activation) =
    setPositionDp(id.id, value, activationMode)

fun BodyInterface.getCenterOfMassPositionSp(id: BodyID, out: JtVec3f) =
    getCenterOfMassPositionSp(id.id, out)
fun BodyInterface.getCenterOfMassPositionSp(id: BodyID) =
    getCenterOfMassPositionSp(id.id)

fun BodyInterface.getRotation(id: BodyID, out: JtQuat) =
    getRotation(id.id, out)
fun BodyInterface.getRotation(id: BodyID) =
    getRotation(id.id)
