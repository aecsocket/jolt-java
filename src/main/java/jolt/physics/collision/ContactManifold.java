package jolt.physics.collision;

import jolt.Jolt;
import jolt.SegmentedJoltNative;
import jolt.math.DVec3;
import jolt.math.FVec3;

import java.lang.foreign.Addressable;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

import static jolt.headers.JPC_ContactManifold.*;

public abstract sealed class ContactManifold extends SegmentedJoltNative
        permits ContactManifold.F, ContactManifold.D {
    public static ContactManifold at(MemorySegment segment) {
        return Jolt.doublePrecision() ? new D(segment) : new F(segment);
    }

    public static ContactManifold at(MemorySession session, Addressable ptr) {
        return ptr.address() == null ? null : at(Jolt.doublePrecision()
                ? jolt.headers_d.JPC_ContactManifold.ofAddress(ptr.address(), session)
                : jolt.headers_f.JPC_ContactManifold.ofAddress(ptr.address(), session));
    }

    private ContactManifold(MemorySegment segment) {
        super(segment);
    }

    public abstract FVec3 getBaseOffsetF();

    public abstract DVec3 getBaseOffsetD();

    public abstract FVec3 getWorldSpaceNormal();

    public abstract float getPenetrationDepth();

    public abstract void setPenetrationDepth(float penetrationDepth);

    public abstract int getSubShapeId1();

    public abstract void setSubShapeId1(int subShapeId1);

    public abstract int getSubShapeId2();

    public abstract void setSubShapeId2(int subShapeId2);

    // TODO getRelativeContactPointsOn1
    // TODO getRelativeContactPointsOn2

    protected static final class F extends ContactManifold {
        private F(MemorySegment segment) {
            super(segment);
        }

        @Override
        public FVec3 getBaseOffsetF() {
            return FVec3.at(jolt.headers_f.JPC_ContactManifold.base_offset$slice(segment));
        }

        @Override
        public DVec3 getBaseOffsetD() {
            throw Jolt.tryingDoublePrecision();
        }

        @Override
        public FVec3 getWorldSpaceNormal() {
            return FVec3.at(jolt.headers_f.JPC_ContactManifold.normal$slice(segment));
        }

        @Override
        public float getPenetrationDepth() {
            return jolt.headers_f.JPC_ContactManifold.penetration_depth$get(segment);
        }

        @Override
        public void setPenetrationDepth(float penetrationDepth) {
            jolt.headers_f.JPC_ContactManifold.penetration_depth$set(segment, penetrationDepth);
        }

        @Override
        public int getSubShapeId1() {
            return jolt.headers_f.JPC_ContactManifold.shape1_sub_shape_id$get(segment);
        }

        @Override
        public void setSubShapeId1(int subShapeId1) {
            jolt.headers_f.JPC_ContactManifold.shape1_sub_shape_id$set(segment, subShapeId1);
        }

        @Override
        public int getSubShapeId2() {
            return jolt.headers_f.JPC_ContactManifold.shape2_sub_shape_id$get(segment);
        }

        @Override
        public void setSubShapeId2(int subShapeId2) {
            jolt.headers_f.JPC_ContactManifold.shape2_sub_shape_id$set(segment, subShapeId2);
        }
    }

    protected static final class D extends ContactManifold {
        private D(MemorySegment segment) {
            super(segment);
        }

        @Override
        public FVec3 getBaseOffsetF() {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public DVec3 getBaseOffsetD() {
            return DVec3.at(jolt.headers_d.JPC_ContactManifold.base_offset$slice(segment));
        }

        @Override
        public FVec3 getWorldSpaceNormal() {
            return FVec3.at(jolt.headers_d.JPC_ContactManifold.normal$slice(segment));
        }

        @Override
        public float getPenetrationDepth() {
            return jolt.headers_d.JPC_ContactManifold.penetration_depth$get(segment);
        }

        @Override
        public void setPenetrationDepth(float penetrationDepth) {
            jolt.headers_d.JPC_ContactManifold.penetration_depth$set(segment, penetrationDepth);
        }

        @Override
        public int getSubShapeId1() {
            return jolt.headers_d.JPC_ContactManifold.shape1_sub_shape_id$get(segment);
        }

        @Override
        public void setSubShapeId1(int subShapeId1) {
            jolt.headers_d.JPC_ContactManifold.shape1_sub_shape_id$set(segment, subShapeId1);
        }

        @Override
        public int getSubShapeId2() {
            return jolt.headers_d.JPC_ContactManifold.shape2_sub_shape_id$get(segment);
        }

        @Override
        public void setSubShapeId2(int subShapeId2) {
            jolt.headers_d.JPC_ContactManifold.shape2_sub_shape_id$set(segment, subShapeId2);
        }
    }
}
