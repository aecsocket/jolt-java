package jolt.kotlin

import jolt.physics.body.Body

@JvmInline
value class BodyId(val id: Int)

val Body.bodyId get() = BodyId(id)

@JvmInline
value class SubShapeId(val id: Int)
