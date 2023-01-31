#include "JoltJNI.h"
#include <iostream>

void JoltJNI::Init(JNIEnv* env) {
    if (vm != nullptr) return; // already initialized
    env->GetJavaVM(&vm);

    jclass cBodyActivationListener = env->FindClass("jolt/physics/body/BodyActivationListener");
    if (env->ExceptionCheck()) return;
    BodyActivationListener_onBodyActivated = env->GetMethodID(cBodyActivationListener, "_onBodyActivated", "(JJ)V");
    BodyActivationListener_onBodyDeactivated = env->GetMethodID(cBodyActivationListener, "_onBodyDeactivated", "(JJ)V");;

    jclass cBroadPhaseLayerInterface = env->FindClass("jolt/physics/collision/broadphase/BroadPhaseLayerInterface");
    if (env->ExceptionCheck()) return;
    BroadPhaseLayerInterface_getNumBroadPhaseLayers = env->GetMethodID(cBroadPhaseLayerInterface, "_getNumBroadPhaseLayers", "()I");
    BroadPhaseLayerInterface_getBroadPhaseLayer = env->GetMethodID(cBroadPhaseLayerInterface, "_getBroadPhaseLayer", "(I)J");
    BroadPhaseLayerInterface_getBroadPhaseLayerName = env->GetMethodID(cBroadPhaseLayerInterface, "_getBroadPhaseLayerName", "(J)Ljava/lang/String;");

    jclass cObjectVsBroadPhaseLayerFilter = env->FindClass("jolt/physics/collision/broadphase/ObjectVsBroadPhaseLayerFilter");
    if (env->ExceptionCheck()) return;
    ObjectVsBroadPhaseLayerFilter_shouldCollide = env->GetMethodID(cObjectVsBroadPhaseLayerFilter, "_shouldCollide", "(IJ)Z");

    jclass cContactListener = env->FindClass("jolt/physics/collision/ContactListener");
    if (env->ExceptionCheck()) return;
    ContactListener_onContactValidate = env->GetMethodID(cContactListener, "_onContactValidate", "(JJFFFJ)I");
    ContactListener_onContactAdded = env->GetMethodID(cContactListener, "_onContactAdded", "(JJJJ)V");
    ContactListener_onContactPersisted = env->GetMethodID(cContactListener, "_onContactPersisted", "(JJJJ)V");
    ContactListener_onContactRemoved = env->GetMethodID(cContactListener, "_onContactRemoved", "(J)V");

    jclass cObjectLayerPairFilter = env->FindClass("jolt/physics/collision/ObjectLayerPairFilter");
    if (env->ExceptionCheck()) return;
    ObjectLayerPairFilter_shouldCollide = env->GetMethodID(cObjectLayerPairFilter, "_shouldCollide", "(II)Z");
}
