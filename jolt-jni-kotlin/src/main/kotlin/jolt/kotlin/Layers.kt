package jolt.kotlin

import jolt.math.JtQuat
import jolt.math.JtVec3d
import jolt.math.JtVec3f
import jolt.physics.body.BodyCreationSettings
import jolt.physics.body.MotionType
import jolt.physics.collision.shape.Shape
import jolt.physics.collision.shape.ShapeSettings

@JvmInline
value class ObjectLayer(val id: Short)

fun BodyCreationSettingsSp(
    shape: ShapeSettings,
    position: JtVec3f,
    rotation: JtQuat,
    motionType: MotionType,
    objectLayer: ObjectLayer,
) = BodyCreationSettings.sp(shape, position, rotation, motionType, objectLayer.id)

fun BodyCreationSettingsSp(
    shape: Shape,
    position: JtVec3f,
    rotation: JtQuat,
    motionType: MotionType,
    objectLayer: ObjectLayer,
) = BodyCreationSettings.sp(shape, position, rotation, motionType, objectLayer.id)

fun BodyCreationSettingsDp(
    shape: ShapeSettings,
    position: JtVec3d,
    rotation: JtQuat,
    motionType: MotionType,
    objectLayer: ObjectLayer,
) = BodyCreationSettings.dp(shape, position, rotation, motionType, objectLayer.id)

fun BodyCreationSettingsDp(
    shape: Shape,
    position: JtVec3d,
    rotation: JtQuat,
    motionType: MotionType,
    objectLayer: ObjectLayer,
) = BodyCreationSettings.dp(shape, position, rotation, motionType, objectLayer.id)

@JvmInline
value class BroadPhaseLayer(val id: Byte)
