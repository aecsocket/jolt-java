package jolt.physics.collision;

import jolt.JoltNative;

public sealed interface CollisionGroup extends JoltNative permits MutableCollisionGroup {
    static CollisionGroup ref(long address) { return address == 0 ? null : new CollisionGroupImpl(address); }

    GroupFilter getGroupFilter();

    int getGroupId();

    int getSubGroupId();

    boolean canCollide(CollisionGroup other);
}
