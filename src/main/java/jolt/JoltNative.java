package jolt;

import jolt.math.Vec3;

import javax.annotation.Nullable;
import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

import static jolt.headers.JoltPhysicsC.*;

public interface JoltNative {
    MemoryAddress address();

    void delete();

    static MemoryAddress addressOf(@Nullable JoltNative obj) {
        return obj == null ? MemoryAddress.NULL : obj.address();
    }

    static int readBodyId(MemoryAddress address) {
        return address.get(C_INT, 0);
    }

    static int readSubShapeIdPair(MemoryAddress address) {
        return address.get(C_INT, 0);
    }

    static Vec3 readVec3(MemoryAddress address) {
        return new Vec3(
                address.getAtIndex(C_FLOAT, 0),
                address.getAtIndex(C_FLOAT, 1),
                address.getAtIndex(C_FLOAT, 2)
        );
    }

    static void writeVec3(Vec3 obj, MemorySegment segment) {
        segment.setAtIndex(C_FLOAT, 0, obj.x());
        segment.setAtIndex(C_FLOAT, 1, obj.y());
        segment.setAtIndex(C_FLOAT, 2, obj.z());
    }

    static MemorySegment allocateVec3(MemorySession session, Vec3 obj) {
        return session.allocateArray(C_FLOAT,
                obj.x(),
                obj.y(),
                obj.z()
        );
    }
}
