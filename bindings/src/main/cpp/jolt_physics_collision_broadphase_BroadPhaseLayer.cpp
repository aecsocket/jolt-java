#include "JoltJNI.h"
#include <Jolt/Physics/Collision/BroadPhase/BroadPhaseLayer.h>

using namespace JPH;

extern "C" {
/*
 * Class:     jolt_physics_collision_broadphase_BroadPhaseLayer
 * Method:    _create
 * Signature: (B)J
 */
JNIEXPORT jlong JNICALL Java_jolt_physics_collision_broadphase_BroadPhaseLayer__1create
  (JNIEnv *, jclass, jbyte value) {
    return (jlong) new BroadPhaseLayer(value);
}

/*
 * Class:     jolt_physics_collision_broadphase_BroadPhaseLayer
 * Method:    _getValue
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_jolt_physics_collision_broadphase_BroadPhaseLayer__1getValue
  (JNIEnv *, jclass, jlong address) {
    BroadPhaseLayer *self = (BroadPhaseLayer*) address;
    return (jint) (uint8) *self;
}
}
