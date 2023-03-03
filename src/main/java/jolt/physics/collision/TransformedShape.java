package jolt.physics.collision;

import jolt.Jolt;
import jolt.SegmentedJoltNative;
import jolt.math.DVec3;
import jolt.math.FVec3;
import jolt.math.Quat;
import jolt.physics.collision.shape.Shape;

import java.lang.foreign.Addressable;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

import static jolt.headers.JPC_TransformedShape.*;

public abstract sealed class TransformedShape extends SegmentedJoltNative
        permits TransformedShape.F, TransformedShape.D {
    public static TransformedShape at(MemorySegment segment) {
        return Jolt.doublePrecision() ? new D(segment) : new F(segment);
    }

    public static TransformedShape at(MemorySession session, Addressable ptr) {
        return ptr.address() == null ? null : at(Jolt.doublePrecision()
                ? jolt.headers_d.JPC_TransformedShape.ofAddress(ptr.address(), session)
                : jolt.headers_f.JPC_TransformedShape.ofAddress(ptr.address(), session));
    }

    public static TransformedShape create(MemorySession session) {
        return Jolt.doublePrecision()
                ? new D(jolt.headers_d.JPC_TransformedShape.allocate(session))
                : new F(jolt.headers_f.JPC_TransformedShape.allocate(session));
    }

    private TransformedShape(MemorySegment segment) {
        super(segment);
    }

    // TODO public abstract boolean castRayF(FRayCast ray, RayCastResult outHit);

    // TODO castRay
    // TODO collidePoint
    // TODO collideShape
    // TODO castShape
    // todo collectTransformedShapes
    // TODO getTrianglesStart
    // TODO getTrianglesNext

    public abstract FVec3 getShapePositionCOMF();

    public abstract DVec3 getShapePositionCOMD();

    public abstract Quat getShapeRotation();

    public abstract Shape getShape();

    public abstract void setShape(Shape shape);

    public abstract FVec3 getShapeScale();

    public abstract int getBodyId();

    public abstract void setBodyId(int bodyId);

    // TODO get/setSubShapeIdCreator

    protected static final class F extends TransformedShape {
        private F(MemorySegment segment) {
            super(segment);
        }
        
        @Override
        public FVec3 getShapePositionCOMF() {
            return FVec3.at(jolt.headers_f.JPC_TransformedShape.shape_position_com$slice(segment));
        }
        
        @Override
        public DVec3 getShapePositionCOMD() {
            throw Jolt.tryingDoublePrecision();
        }
        
        @Override
        public Quat getShapeRotation() {
            return Quat.at(jolt.headers_f.JPC_TransformedShape.shape_rotation$slice(segment));
        }
        
        @Override
        public Shape getShape() {
            return Shape.at(jolt.headers_f.JPC_TransformedShape.shape$get(segment));
        }
        
        @Override
        public void setShape(Shape shape) {
            jolt.headers_f.JPC_TransformedShape.shape$set(segment, shape.address());
        }
        
        @Override
        public FVec3 getShapeScale() {
            return FVec3.at(jolt.headers_f.JPC_TransformedShape.shape_scale$slice(segment));
        }
        
        @Override
        public int getBodyId() {
            return jolt.headers_f.JPC_TransformedShape.body_id$get(segment);
        }
        
        @Override
        public void setBodyId(int bodyId) {
            jolt.headers_f.JPC_TransformedShape.body_id$set(segment, bodyId);
        }
    }

    protected static final class D extends TransformedShape {
        private D(MemorySegment segment) {
            super(segment);
        }

        @Override
        public FVec3 getShapePositionCOMF() {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public DVec3 getShapePositionCOMD() {
            return DVec3.at(jolt.headers_d.JPC_TransformedShape.shape_position_com$slice(segment));
        }

        @Override
        public Quat getShapeRotation() {
            return Quat.at(jolt.headers_d.JPC_TransformedShape.shape_rotation$slice(segment));
        }

        @Override
        public Shape getShape() {
            return Shape.at(jolt.headers_d.JPC_TransformedShape.shape$get(segment));
        }

        @Override
        public void setShape(Shape shape) {
            jolt.headers_d.JPC_TransformedShape.shape$set(segment, shape.address());
        }

        @Override
        public FVec3 getShapeScale() {
            return FVec3.at(jolt.headers_d.JPC_TransformedShape.shape_scale$slice(segment));
        }

        @Override
        public int getBodyId() {
            return jolt.headers_d.JPC_TransformedShape.body_id$get(segment);
        }

        @Override
        public void setBodyId(int bodyId) {
            jolt.headers_d.JPC_TransformedShape.body_id$set(segment, bodyId);
        }
    }
}
