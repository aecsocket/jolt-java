package jolt.physics.collision;

import jolt.SegmentedJoltNative;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;
import java.lang.foreign.SegmentAllocator;

import static jolt.headers.JPC_CollisionGroup.*;
import static jolt.headers.JoltPhysicsC.*;

public final class CollisionGroup extends SegmentedJoltNative {
    //region Jolt-Value
    private CollisionGroup(MemorySegment handle) {
        super(handle);
    }

    public static CollisionGroup at(MemorySegment segment) {
        return new CollisionGroup(segment);
    }

    public static CollisionGroup at(MemorySession alloc, MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new CollisionGroup(ofAddress(addr, alloc));
    }

    public static CollisionGroup of(SegmentAllocator alloc) {
        return new CollisionGroup(allocate(alloc));
    }
    //endregion Jolt-Value

    public void setGroupFilter(GroupFilter filter) {
        filter$set(handle, filter.address());
    }

    public GroupFilter geetGroupFilter() {
        return null; // TODO GroupFilter.at(filter$get(handle));
    }

    public void setGroupId(int id) {
        group_id$set(handle, id);
    }

    public int getGroupId() {
        return group_id$get(handle);
    }

    public void setSubGroupId(int id) {
        sub_group_id$set(handle, id);
    }

    public int getSubGroupId() {
        return sub_group_id$get(handle);
    }

    public boolean canCollide(CollisionGroup other) {
        return JPC_CollisionGroup_CanCollide(handle, other.address());
    }
}
