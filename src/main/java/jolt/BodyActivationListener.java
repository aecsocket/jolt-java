package jolt;

public class BodyActivationListener extends JoltNative {
    protected BodyActivationListener(long address) { super(address); }
    public static BodyActivationListener ofPointer(long address) { return new BodyActivationListener(address); }

    public BodyActivationListener() {
        super(_create());
    }
    private static native long _create();

    public void onBodyActivated(BodyID bodyID, long bodyUserData) {}
    public void onBodyDeactivated(BodyID bodyID, long bodyUserData) {}
}
