package jolt.physics.body;

public interface BodyActivationListenerFn {
    void onBodyActivated(int bodyId, long bodyUserData);

    void onBodyDeactivated(int bodyId, long bodyUserData);
}
