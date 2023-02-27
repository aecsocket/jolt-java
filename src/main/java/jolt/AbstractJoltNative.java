package jolt;

import jolt.math.Vec3;

import javax.annotation.Nullable;
import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

public abstract class AbstractJoltNative implements JoltNative {
    protected final MemoryAddress address;
    private boolean deleted;

    protected AbstractJoltNative(MemoryAddress address) {
        this.address = address;
    }

    @Override
    public MemoryAddress address() {
        return address;
    }

    protected abstract void deleteInternal();

    @Override
    public void delete() {
        if (deleted) throw new RuntimeException("Native object is already deleted");
        deleteInternal();
        deleted = true;
    }

    protected static RuntimeException cannotDelete() {
        throw new UnsupportedOperationException("Cannot delete this object");
    }

    protected static MemoryAddress addr(@Nullable JoltNative obj) { return JoltNative.addressOf(obj); }
    protected static int readBodyId(MemoryAddress address) { return JoltNative.readBodyId(address); }
    protected static int readSubShapeIdPair(MemoryAddress address) { return JoltNative.readSubShapeIdPair(address); }
    protected static Vec3 readVec3(MemoryAddress address) { return JoltNative.readVec3(address); }
    protected static void writeVec3(Vec3 obj, MemorySegment segment) { JoltNative.writeVec3(obj, segment); }
    protected static MemorySegment allocateVec3(MemorySession session, Vec3 obj) { return JoltNative.allocateVec3(session, obj); }
}
