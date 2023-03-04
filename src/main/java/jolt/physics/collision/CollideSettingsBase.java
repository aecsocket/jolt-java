package jolt.physics.collision;

import jolt.SegmentedJoltNative;
import jolt.math.FVec3;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;
import java.lang.foreign.SegmentAllocator;

import static jolt.headers.JPC_CollideSettingsBase.*;

public sealed class CollideSettingsBase extends SegmentedJoltNative
        permits CollideShapeSettings {
    //region Jolt-Value-Protected
    protected CollideSettingsBase(MemorySegment handle) {
        super(handle);
    }

    public static CollideSettingsBase at(MemorySegment segment) {
        return new CollideSettingsBase(segment);
    }

    public static CollideSettingsBase at(MemorySession alloc, MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new CollideSettingsBase(ofAddress(addr, alloc));
    }

    public static CollideSettingsBase of(SegmentAllocator alloc) {
        return new CollideSettingsBase(allocate(alloc));
    }
    //endregion Jolt-Value-Protected

    public ActiveEdgeMode getActiveEdgeMode() {
        return ActiveEdgeMode.values()[active_edge_mode$get(handle)];
    }

    public void setActiveEdgeMode(ActiveEdgeMode activeEdgeMode) {
        active_edge_mode$set(handle, activeEdgeMode.ordinal());
    }

    public CollectFacesMode getCollectFacesMode() {
        return CollectFacesMode.values()[collect_faces_mode$get(handle)];
    }

    public void setCollectFacesMode(CollectFacesMode collectFacesMode) {
        collect_faces_mode$set(handle, collectFacesMode.ordinal());
    }

    public float getCollisionTolerance() {
        return collision_tolerance$get(handle);
    }

    public void setCollisionTolerance(float collisionTolerance) {
        collision_tolerance$set(handle, collisionTolerance);
    }

    public float getPenetrationTolerance() {
        return penetration_tolerance$get(handle);
    }

    public void setPenetrationTolerance(float penetrationTolerance) {
        penetration_tolerance$set(handle, penetrationTolerance);
    }

    public FVec3 getActiveEdgeMovementDirection() {
        return FVec3.at(active_edge_movement_direction$slice(handle));
    }

    public void setActiveEdgeMovementDirection(FVec3 activeEdgeMovementDirection) {
        activeEdgeMovementDirection.write(active_edge_movement_direction$slice(handle));
    }
}
