package jolt.physics.collision;

import io.github.aecsocket.jniglue.JniBindSelf;
import io.github.aecsocket.jniglue.JniInclude;
import io.github.aecsocket.jniglue.JniTypeMapping;
import jolt.JoltNativeImpl;

@JniInclude("<Jolt/Physics/Collision/CollisionGroup.h>")
@JniTypeMapping("CollisionGroup")
final class CollisionGroupImpl extends JoltNativeImpl implements MutableCollisionGroup {
    CollisionGroupImpl(long address) { super(address); }

    // TODO
    @Override
    public void setGroupFilter(GroupFilter value) {}

    @Override
    public GroupFilter getGroupFilter() { return new GroupFilter() {}; }

    @Override
    public void setGroupId(int value) { _setGroupId(address, value); }
    @JniBindSelf("self->SetGroupID(value);")
    private static native void _setGroupId(long _a, int value);

    @Override
    public int getGroupId() { return _getGroupId(address); }
    @JniBindSelf("return self->GetGroupID();")
    private static native int _getGroupId(long _a);

    @Override
    public void setSubGroupId(int value) { _setSubGroupId(address, value); }
    @JniBindSelf("self->SetSubGroupID(value);")
    private static native void _setSubGroupId(long _a, int value);

    @Override
    public int getSubGroupId() { return _getSubGroupId(address); }
    @JniBindSelf("return self->GetSubGroupID();")
    private static native int _getSubGroupId(long _a);

    @Override
    public boolean canCollide(CollisionGroup other) { return _canCollide(address, other.getAddress()); }
    @JniBindSelf("return self->CanCollide(*((CollisionGroup*) other));")
    private static native boolean _canCollide(long _a, long other);
}
