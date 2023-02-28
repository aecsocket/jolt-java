package jolt;

import jolt.math.DVec3;
import jolt.math.FVec3;
import jolt.math.Quat;

import javax.annotation.Nullable;
import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

public abstract class AbstractJoltNative implements JoltNative {
    protected final MemoryAddress address;
    private boolean destroyed;

    protected AbstractJoltNative(MemoryAddress address) {
        this.address = address;
    }

    @Override
    public MemoryAddress address() {
        return address;
    }

    protected abstract void destroyInternal();

    @Override
    public void destroy() {
        if (destroyed) throw new RuntimeException("Native object is already destroyed");
        destroyInternal();
        destroyed = true;
    }

    protected static RuntimeException cannotDestroy() {
        throw new UnsupportedOperationException("Cannot destroy this object");
    }

    protected static MemoryAddress addr(@Nullable JoltNative obj) { return JoltNative.addressOf(obj); }
    protected static int readBodyId(MemoryAddress address) { return JoltNative.readBodyId(address); }
    protected static int readSubShapeIdPair(MemoryAddress address) { return JoltNative.readSubShapeIdPair(address); }
    protected static FVec3 readFVec3(MemoryAddress address) { return JoltNative.readFVec3(address); }
    protected static void writeFVec3(FVec3 obj, MemorySegment segment) { JoltNative.writeFVec3(obj, segment); }
    protected static MemorySegment allocateFVec3(MemorySession session, FVec3 obj) { return JoltNative.allocateFVec3(session, obj); }
    protected static DVec3 readDVec3(MemoryAddress address) { return JoltNative.readDVec3(address); }
    protected static void writeDVec3(DVec3 obj, MemorySegment segment) { JoltNative.writeDVec3(obj, segment); }
    protected static MemorySegment allocateDVec3(MemorySession session, DVec3 obj) { return JoltNative.allocateDVec3(session, obj); }
    protected static Quat readQuat(MemoryAddress address) { return JoltNative.readQuat(address); }
    protected static void writeQuat(Quat obj, MemorySegment segment) { JoltNative.writeQuat(obj, segment); }
    protected static MemorySegment allocateQuat(MemorySession session, Quat obj) { return JoltNative.allocateQuat(session, obj); }
}
