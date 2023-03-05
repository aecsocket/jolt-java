package jolt.physics;

public interface PhysicsStepListenerFn {
    void onStep(float deltaTime, PhysicsSystem physicsSystem);
}
