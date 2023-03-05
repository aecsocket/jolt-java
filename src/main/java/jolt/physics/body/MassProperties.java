package jolt.physics.body;

import jolt.SegmentedJoltNative;
import jolt.math.FMat44;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;
import java.lang.foreign.SegmentAllocator;

import static jolt.headers.JPC_MassProperties.*;

public final class MassProperties extends SegmentedJoltNative {
    //region Jolt-Value
    private MassProperties(MemorySegment handle) {
        super(handle);
    }

    public static MassProperties at(MemorySegment segment) {
        return new MassProperties(segment);
    }

    public static MassProperties at(MemorySession alloc, MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new MassProperties(ofAddress(addr, alloc));
    }

    public static MassProperties of(SegmentAllocator alloc) {
        return new MassProperties(allocate(alloc));
    }
    //endregion Jolt-Value

    public static MassProperties of(SegmentAllocator alloc, float mass, FMat44 inertia) {
        var segment = allocate(alloc);
        mass$set(segment, mass);
        inertia.write(inertia$slice(segment));
        return new MassProperties(segment);
    }

    public float getMass() {
        return mass$get(handle);
    }

    public void setMass(float mass) {
        mass$set(handle, mass);
    }

    public FMat44 getInertia() {
        return FMat44.at(inertia$slice(handle));
    }

    public void setInertia(FMat44 inertia) {
        inertia.write(inertia$slice(handle));
    }

    public void read(MemorySegment segment) {
        setMass(mass$get(segment));
        getInertia().read(inertia$slice(segment).address());
    }

    public void write(MemorySegment segment) {
        mass$set(segment, getMass());
        getInertia().write(inertia$slice(segment));
    }
}
