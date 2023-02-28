package jolt;

import jolt.math.DVec3;
import jolt.math.FVec3;
import jolt.math.Quat;

import javax.annotation.Nullable;
import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

import static jolt.headers.JoltPhysicsC.*;

public interface JoltNative {
    MemoryAddress address();

    void destroy();

    static MemoryAddress addressOf(@Nullable JoltNative obj) {
        return obj == null ? MemoryAddress.NULL : obj.address();
    }

    static int readBodyId(MemoryAddress address) {
        return address.get(C_INT, 0);
    }

    static int readSubShapeIdPair(MemoryAddress address) {
        return address.get(C_INT, 0);
    }

    static FVec3 readFVec3(MemoryAddress address) {
        return new FVec3(
                address.getAtIndex(C_FLOAT, 0),
                address.getAtIndex(C_FLOAT, 1),
                address.getAtIndex(C_FLOAT, 2)
        );
    }

    static void writeFVec3(FVec3 obj, MemorySegment segment) {
        segment.setAtIndex(C_FLOAT, 0, obj.x());
        segment.setAtIndex(C_FLOAT, 1, obj.y());
        segment.setAtIndex(C_FLOAT, 2, obj.z());
    }

    static MemorySegment allocateFVec3(MemorySession session, FVec3 obj) {
        return session.allocateArray(C_FLOAT,
                obj.x(),
                obj.y(),
                obj.z()
        );
    }

    static DVec3 readDVec3(MemoryAddress address) {
        return new DVec3(
                address.getAtIndex(C_DOUBLE, 0),
                address.getAtIndex(C_DOUBLE, 1),
                address.getAtIndex(C_DOUBLE, 2)
        );
    }

    static void writeDVec3(DVec3 obj, MemorySegment segment) {
        segment.setAtIndex(C_DOUBLE, 0, obj.x());
        segment.setAtIndex(C_DOUBLE, 1, obj.y());
        segment.setAtIndex(C_DOUBLE, 2, obj.z());
    }

    static MemorySegment allocateDVec3(MemorySession session, DVec3 obj) {
        return session.allocateArray(C_DOUBLE,
                obj.x(),
                obj.y(),
                obj.z()
        );
    }

    static Quat readQuat(MemoryAddress address) {
        return new Quat(
                address.getAtIndex(C_FLOAT, 0),
                address.getAtIndex(C_FLOAT, 1),
                address.getAtIndex(C_FLOAT, 2),
                address.getAtIndex(C_FLOAT, 3)
        );
    }

    static void writeQuat(Quat obj, MemorySegment segment) {
        segment.setAtIndex(C_FLOAT, 0, obj.x());
        segment.setAtIndex(C_FLOAT, 1, obj.y());
        segment.setAtIndex(C_FLOAT, 2, obj.y());
        segment.setAtIndex(C_FLOAT, 3, obj.y());
    }

    static MemorySegment allocateQuat(MemorySession session, Quat obj) {
        return session.allocateArray(C_FLOAT,
                obj.x(),
                obj.y(),
                obj.z(),
                obj.w()
        );
    }
}
