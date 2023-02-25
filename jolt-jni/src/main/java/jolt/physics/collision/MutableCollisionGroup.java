package jolt.physics.collision;

public sealed interface MutableCollisionGroup extends CollisionGroup permits CollisionGroupImpl {
    static MutableCollisionGroup ref(long address) { return address == 0 ? null : new CollisionGroupImpl(address); }

    void setGroupFilter(GroupFilter value);

    void setGroupId(int value);

    void setSubGroupId(int value);
}
