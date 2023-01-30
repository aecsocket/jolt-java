package jolt;

public class BodyActivationListener extends JoltNative {
    protected BodyActivationListener(long address) { super(address); }
    public static BodyActivationListener ofPointer(long address) { return new BodyActivationListener(address); }

    public BodyActivationListener() {
        super(_create());
    }
    private static native long _create();

    public void onBodyActivated(BodyID bodyID, long bodyUserData) {}
    private void _onBodyActivated(long bodyId, long bodyUserData) { onBodyActivated(BodyID.ptr(bodyId), bodyUserData); }

    private void _onBodyDeactivated(long bodyId, long bodyUserData) { onBodyDeactivated(BodyID.ptr(bodyId), bodyUserData); }
    public void onBodyDeactivated(BodyID bodyID, long bodyUserData) {}
}
