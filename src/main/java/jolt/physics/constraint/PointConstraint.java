package jolt.physics.constraint;

import jolt.DestroyableJoltNative;
import jolt.Jolt;
import jolt.geometry.AABox;
import jolt.math.DVec3;
import jolt.math.FMat44;
import jolt.math.FVec3;
import jolt.physics.body.MutableBody;
import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;
import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public abstract sealed class PointConstraint extends TwoBodyConstraint
        permits PointConstraint.F, PointConstraint.D {
    //region Jolt-Pointer
    private PointConstraint(MemoryAddress handle) {
        super(handle);
    }

    public static PointConstraint at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : Jolt.doublePrecision()
                ? new D(addr)
                : new F(addr);
    }
    //endregion Jolt-Pointer

    public abstract void setPoint1(ConstraintSpace space, FVec3 point1);

    public abstract void setPoint1(ConstraintSpace space, DVec3 point1);

    public abstract void setPoint2(ConstraintSpace space, FVec3 point2);

    public abstract void setPoint2(ConstraintSpace space, DVec3 point2);

    public void getLocalSpacePoint1(FVec3 out) {
        JPC_PointConstraint_GetLocalSpacePoint1(handle, out.address());
    }

    public void getLocalSpacePoint2(FVec3 out) {
        JPC_PointConstraint_GetLocalSpacePoint2(handle, out.address());
    }

    public void getTotalLambdaPosition(FVec3 out) {
        JPC_PointConstraint_GetTotalLambdaPosition(handle, out.address());
    }

    static final class F extends PointConstraint {
        private F(MemoryAddress handle) {
            super(handle);
        }

        @Override
        public void setPoint1(ConstraintSpace space, FVec3 point1) {
            jolt.headers_f.JoltPhysicsC.JPC_PointConstraint_SetPoint1(handle, space.ordinal(), point1.address());
        }

        @Override
        public void setPoint1(ConstraintSpace space, DVec3 point1) {
            throw Jolt.tryingDoublePrecision();
        }
    
        @Override
        public void setPoint2(ConstraintSpace space, FVec3 point2) {
            jolt.headers_f.JoltPhysicsC.JPC_PointConstraint_SetPoint2(handle, space.ordinal(), point2.address());
        }
    
        @Override
        public void setPoint2(ConstraintSpace space, DVec3 point2) {
            throw Jolt.tryingDoublePrecision();
        }
    }

    static final class D extends PointConstraint {
        private D(MemoryAddress handle) {
            super(handle);
        }

        @Override
        public void setPoint1(ConstraintSpace space, FVec3 point1) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public void setPoint1(ConstraintSpace space, DVec3 point1) {
            jolt.headers_f.JoltPhysicsC.JPC_PointConstraint_SetPoint1(handle, space.ordinal(), point1.address());
        }
    
        @Override
        public void setPoint2(ConstraintSpace space, FVec3 point2) {
            throw Jolt.tryingSinglePrecision();
        }
    
        @Override
        public void setPoint2(ConstraintSpace space, DVec3 point2) {
            jolt.headers_f.JoltPhysicsC.JPC_PointConstraint_SetPoint2(handle, space.ordinal(), point2.address());
        }
    }
}
