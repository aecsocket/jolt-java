package jolt.physics.collision;

import jolt.JoltNative;

public final class ContactSettings extends JoltNative {
    private ContactSettings(long address) { super(address); }
    public static ContactSettings ofPointer(long address) { return new ContactSettings(address); }

    public float getCombinedFriction() { return _getCombinedFriction(address); }
    private static native float _getCombinedFriction(long address);

    public void setCombinedFriction(float value) { _setCombinedFriction(address, value); }
    private static native void _setCombinedFriction(long address, float combinedFriction);

    public float getCombinedRestitution() { return _getCombinedRestitution(address); }
    private static native float _getCombinedRestitution(long address);

    public void setCombinedRestitution(float value) { _setCombinedRestitution(address, value); }
    private static native void _setCombinedRestitution(long address, float value);

    public boolean getIsSensor() { return _getIsSensor(address); }
    private static native boolean _getIsSensor(long address);

    public void setIsSensor(boolean value) { _setIsSensor(address, value); }
    private static native void _setIsSensor(long address, boolean value);
}
