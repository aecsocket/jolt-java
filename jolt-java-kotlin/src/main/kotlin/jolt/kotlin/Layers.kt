package jolt.kotlin

import jolt.math.Quat
import jolt.math.FVec3
import jolt.physics.body.BodyCreationSettings
import jolt.physics.body.MotionType
import jolt.physics.collision.shape.Shape
import java.lang.foreign.MemorySession

@JvmInline
value class BroadPhaseLayer(val layer: Byte)

@JvmInline
value class ObjectLayer(val layer: Short)

fun BodyCreationSettings(
    session: MemorySession,
    shape: Shape,
    position: FVec3,
    rotation: Quat,
    motionType: MotionType,
    layer: ObjectLayer
) = BodyCreationSettings.create(session, shape, position, rotation, motionType, layer.layer)
