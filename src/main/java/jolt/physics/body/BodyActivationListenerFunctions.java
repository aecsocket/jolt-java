package jolt.physics.body;

public interface BodyActivationListenerFunctions {
    void onBodyActivated(int bodyId, long bodyUserData);

    void onBodyDeactivated(int bodyId, long bodyUserData);
}
