package jolt.physics.collision;

import jolt.jni.JniInclude;
import jolt.jni.JniSelfBind;
import jolt.JoltNative;
import jolt.jni.JniType;

@JniInclude("<Jolt/Physics/Collision/ContactListener.h>")
@JniType("ContactSettings")
public final class ContactSettings extends JoltNative {
    private ContactSettings(long address) { super(address); }
    public static ContactSettings ref(long address) { return new ContactSettings(address); }

    public float getCombinedFriction() { return _getCombinedFriction(address); }
    @JniSelfBind("return self->mCombinedFriction;")
    private static native float _getCombinedFriction(long address);

    public void setCombinedFriction(float value) { _setCombinedFriction(address, value); }
    @JniSelfBind("self->mCombinedFriction = value;")
    private static native void _setCombinedFriction(long address, float value);

    public float getCombinedRestitution() { return _getCombinedRestitution(address); }
    @JniSelfBind("return self->mCombinedRestitution;")
    private static native float _getCombinedRestitution(long address);

    public void setCombinedRestitution(float value) { _setCombinedRestitution(address, value); }
    @JniSelfBind("self->mCombinedRestitution = value;")
    private static native void _setCombinedRestitution(long address, float value);

    public boolean getIsSensor() { return _getIsSensor(address); }
    @JniSelfBind("return self->mIsSensor;")
    private static native boolean _getIsSensor(long address);

    public void setIsSensor(boolean value) { _setIsSensor(address, value); }
    @JniSelfBind("self->mIsSensor = value;")
    private static native void _setIsSensor(long address, boolean value);
}
