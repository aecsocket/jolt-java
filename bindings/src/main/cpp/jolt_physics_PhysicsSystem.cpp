#include "JoltJNI.h"
#include <Jolt/Physics/PhysicsSystem.h>
#include <Jolt/Physics/Collision/BroadPhase/BroadPhaseLayer.h>
#include <Jolt/Physics/Collision/ObjectLayer.h>

using namespace JPH;

extern "C" {
/*
 * Class:     jolt_physics_PhysicsSystem
 * Method:    _create
 * Signature: (IIIIJJJ)J
 */
JNIEXPORT jlong JNICALL Java_jolt_physics_PhysicsSystem__1create
  (JNIEnv *, jclass, jint maxBodies, jint numBodyMutexes, jint maxBodyPairs, jint maxContactConstraints, jlong broadPhaseLayerInterface, jlong objectVsBroadPhaseLayerFilter, jlong objectLayerPairFilter) {
    PhysicsSystem *self = new PhysicsSystem();
    //self->Init(maxBodies, numBodyMutexes, maxBodyPairs, maxContactConstraints, *((BroadPhaseLayerInterface*) broadPhaseLayerInterface), *((ObjectVsBroadPhaseLayerFilter*) objectVsBroadPhaseLayerFilter), *((ObjectLayerPairFilter*) objectLayerPairFilter));
    return (jlong) self;
}

/*
 * Class:     jolt_physics_PhysicsSystem
 * Method:    _getBodyInterface
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_jolt_physics_PhysicsSystem__1getBodyInterface
  (JNIEnv *, jclass, jlong address) {
    PhysicsSystem *self = (PhysicsSystem*) address;
    return (jlong) &self->GetBodyInterface();
}

/*
 * Class:     jolt_physics_PhysicsSystem
 * Method:    _getBodyInterfaceNoLock
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_jolt_physics_PhysicsSystem__1getBodyInterfaceNoLock
  (JNIEnv *, jclass, jlong address) {
    PhysicsSystem *self = (PhysicsSystem*) address;
    return (jlong) &self->GetBodyInterfaceNoLock();
}

/*
 * Class:     jolt_physics_PhysicsSystem
 * Method:    _getBodyActivationListener
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_jolt_physics_PhysicsSystem__1getBodyActivationListener
  (JNIEnv *, jclass, jlong address) {
    PhysicsSystem *self = (PhysicsSystem*) address;
    return (jlong) self->GetBodyActivationListener();
}

/*
 * Class:     jolt_PhysicsSystem
 * Method:    _setBodyActivationListener
 * Signature: (JJ)V
 */
JNIEXPORT void JNICALL Java_jolt_physics_PhysicsSystem__1setBodyActivationListener
  (JNIEnv *, jclass, jlong address, jlong value) {
    PhysicsSystem *self = (PhysicsSystem*) address;
    self->SetBodyActivationListener((BodyActivationListener*) value);
}

/*
 * Class:     jolt_physics_PhysicsSystem
 * Method:    _getContactListener
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_jolt_physics_PhysicsSystem__1getContactListener
  (JNIEnv *, jclass, jlong address) {
    PhysicsSystem *self = (PhysicsSystem*) address;
    return (jlong) self->GetContactListener();
}

/*
 * Class:     jolt_physics_PhysicsSystem
 * Method:    _setContactListener
 * Signature: (JJ)V
 */
JNIEXPORT void JNICALL Java_jolt_physics_PhysicsSystem__1setContactListener
  (JNIEnv *, jclass, jlong address, jlong value) {
    PhysicsSystem *self = (PhysicsSystem*) address;
    self->SetContactListener((ContactListener*) value);
}
}
