package jolt.kotlin

import jolt.math.JtQuat
import jolt.math.JtVec3d
import jolt.math.JtVec3f
import jolt.physics.Activation
import jolt.physics.body.BodyInterface
import jolt.physics.body.MutableBodyInterface

@JvmInline
value class BodyId(val id: Int)

fun MutableBodyInterface.destroyBody(id: BodyId) =
    destroyBody(id.id)

fun MutableBodyInterface.addBody(id: BodyId, activationMode: Activation) =
    addBody(id.id, activationMode)
fun MutableBodyInterface.removeBody(id: BodyId) =
    removeBody(id.id)
fun BodyInterface.isAdded(id: BodyId) =
    isAdded(id.id)

fun BodyInterface.isActive(id: BodyId) =
    isActive(id.id)
fun MutableBodyInterface.activateBody(id: BodyId) =
    activateBody(id.id)
fun MutableBodyInterface.deactivateBody(id: BodyId) =
    deactivateBody(id.id)

fun BodyInterface.getPositionAndRotationSp(id: BodyId, outPosition: JtVec3f, outRotation: JtQuat) =
    getPositionAndRotationSp(id.id, outPosition, outRotation)
fun BodyInterface.getPositionAndRotationDp(id: BodyId, outPosition: JtVec3d, outRotation: JtQuat) =
    getPositionAndRotationDp(id.id, outPosition, outRotation)
fun MutableBodyInterface.setPositionAndRotationSp(id: BodyId, position: JtVec3f, rotation: JtQuat, activationMode: Activation) =
    setPositionAndRotationSp(id.id, position, rotation, activationMode)
fun MutableBodyInterface.setPositionAndRotationDp(id: BodyId, position: JtVec3d, rotation: JtQuat, activationMode: Activation) =
    setPositionAndRotationDp(id.id, position, rotation, activationMode)

fun BodyInterface.getPositionSp(id: BodyId, out: JtVec3f) =
    getPositionSp(id.id, out)
fun BodyInterface.getPositionSp(id: BodyId) =
    getPositionSp(id.id)
fun BodyInterface.getPositionDp(id: BodyId, out: JtVec3d) =
    getPositionDp(id.id, out)
fun BodyInterface.getPositionDp(id: BodyId) =
    getPositionDp(id.id)
fun MutableBodyInterface.setPositionSp(id: BodyId, value: JtVec3f, activationMode: Activation) =
    setPositionSp(id.id, value, activationMode)
fun MutableBodyInterface.setPositionDp(id: BodyId, value: JtVec3d, activationMode: Activation) =
    setPositionDp(id.id, value, activationMode)

fun BodyInterface.getCenterOfMassPositionSp(id: BodyId, out: JtVec3f) =
    getCenterOfMassPositionSp(id.id, out)
fun BodyInterface.getCenterOfMassPositionSp(id: BodyId) =
    getCenterOfMassPositionSp(id.id)

fun BodyInterface.getRotation(id: BodyId, out: JtQuat) =
    getRotation(id.id, out)
fun BodyInterface.getRotation(id: BodyId) =
    getRotation(id.id)
