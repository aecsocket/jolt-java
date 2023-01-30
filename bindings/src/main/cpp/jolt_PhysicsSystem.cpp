#include "JoltJNI.cpp"
#include <Jolt/Physics/PhysicsSystem.h>
#include <Jolt/Physics/Collision/BroadPhase/BroadPhaseLayer.h>
#include <Jolt/Physics/Collision/ObjectLayer.h>
#include <iostream>

extern "C" {
/*
 * Class:     jolt_PhysicsSystem
 * Method:    _create
 * Signature: (IIIIJJJ)J
 */
JNIEXPORT jlong JNICALL Java_jolt_PhysicsSystem__1create
  (JNIEnv *, jclass, jint maxBodies, jint numBodyMutexes, jint maxBodyPairs, jint maxContactContraints, jlong broadPhaseLayerInterface, jlong objectVsBroadPhaseLayerFilter, jlong objectLayerPairFilter) {
    std::cout << "step 1" << std::endl;
    PhysicsSystem *self = new PhysicsSystem();
    std::cout << "step 2" << std::endl;
    self->Init(maxBodies, numBodyMutexes, maxBodyPairs, maxContactContraints, *((BroadPhaseLayerInterface*) broadPhaseLayerInterface), *((ObjectVsBroadPhaseLayerFilter*) objectVsBroadPhaseLayerFilter), *((ObjectLayerPairFilter*) objectLayerPairFilter));
    std::cout << "step 3" << std::endl;
    return (jlong) self;
}

/*
 * Class:     jolt_PhysicsSystem
 * Method:    _getBodyActivationListener
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_jolt_PhysicsSystem__1getBodyActivationListener
  (JNIEnv *, jclass, jlong address) {
    PhysicsSystem *self = (PhysicsSystem*) address;
    return (jlong) self->GetBodyActivationListener();
}

/*
 * Class:     jolt_PhysicsSystem
 * Method:    _setBodyActivationListener
 * Signature: (JJ)V
 */
JNIEXPORT void JNICALL Java_jolt_PhysicsSystem__1setBodyActivationListener
  (JNIEnv *, jclass, jlong address, jlong listener) {
    PhysicsSystem *self = (PhysicsSystem*) address;
    self->SetBodyActivationListener((BodyActivationListener*) listener);
}
}
