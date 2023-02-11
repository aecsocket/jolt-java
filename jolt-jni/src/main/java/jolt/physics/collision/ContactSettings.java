package jolt.physics.collision;

import io.github.aecsocket.jniglue.JniInclude;
import io.github.aecsocket.jniglue.JniBindSelf;
import io.github.aecsocket.jniglue.JniTypeMapping;
import jolt.JoltNative;

@JniInclude("<Jolt/Physics/Collision/ContactListener.h>")
@JniTypeMapping("ContactSettings")
public final class ContactSettings extends JoltNative {
    private ContactSettings(long address) { super(address); }
    public static ContactSettings ref(long address) { return address == 0 ? null : new ContactSettings(address); }

    public float getCombinedFriction() { return _getCombinedFriction(address); }
    @JniBindSelf("return self->mCombinedFriction;")
    private static native float _getCombinedFriction(long _a);

    public void setCombinedFriction(float value) { _setCombinedFriction(address, value); }
    @JniBindSelf("self->mCombinedFriction = value;")
    private static native void _setCombinedFriction(long _a, float value);

    public float getCombinedRestitution() { return _getCombinedRestitution(address); }
    @JniBindSelf("return self->mCombinedRestitution;")
    private static native float _getCombinedRestitution(long _a);

    public void setCombinedRestitution(float value) { _setCombinedRestitution(address, value); }
    @JniBindSelf("self->mCombinedRestitution = value;")
    private static native void _setCombinedRestitution(long _a, float value);

    public boolean getIsSensor() { return _getIsSensor(address); }
    @JniBindSelf("return self->mIsSensor;")
    private static native boolean _getIsSensor(long _a);

    public void setIsSensor(boolean value) { _setIsSensor(address, value); }
    @JniBindSelf("self->mIsSensor = value;")
    private static native void _setIsSensor(long _a, boolean value);
}
