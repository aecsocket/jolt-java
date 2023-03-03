package jolt.geometry;

import jolt.SegmentedJoltNative;
import jolt.math.FVec3;

import java.lang.foreign.*;

import static jolt.headers.JPC_AABoxCast.*;

public final class AABoxCast extends SegmentedJoltNative {
    // START Jolt-Value
    private AABoxCast(MemorySegment handle) {
        super(handle);
    }

    public static AABoxCast at(MemorySegment segment) {
        return new AABoxCast(segment);
    }

    public static AABoxCast at(MemorySession alloc, MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new AABoxCast(ofAddress(addr, alloc));
    }

    public static AABoxCast of(SegmentAllocator alloc) {
        return new AABoxCast(allocate(alloc));
    }
    // END Jolt-Value

    public static AABoxCast of(SegmentAllocator alloc, AABox box, FVec3 direction) {
        var segment = allocate(alloc);
        box.write(box$slice(segment));
        direction.write(direction$slice(segment));
        return new AABoxCast(segment);
    }

    public AABox getBox() {
        return AABox.at(box$slice(handle));
    }

    public void setBox(AABox box) {
        box.write(box$slice(handle));
    }

    public FVec3 getDirection() {
        return FVec3.at(direction$slice(handle));
    }

    public void setDirection(FVec3 direction) {
        direction.write(direction$slice(handle));
    }

    public void read(MemorySegment segment) {
        setBox(AABox.at(box$slice(segment)));
        setDirection(FVec3.at(direction$slice(segment)));
    }

    public void read(AABoxCast b) {
        read(b.handle);
    }

    public void write(MemorySegment segment) {
        getBox().write(box$slice(segment));
        getDirection().write(direction$slice(segment));
    }

    @Override
    public String toString() {
        return "AABoxCast(box=%s, direction=%s)".formatted(getBox(), getDirection());
    }
}
