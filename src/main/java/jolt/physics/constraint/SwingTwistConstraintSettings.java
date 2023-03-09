package jolt.physics.constraint;

import jolt.DestroyableJoltNative;
import jolt.Jolt;
import jolt.geometry.AABox;
import jolt.math.FMat44;
import jolt.math.FVec3;
import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;
import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public abstract sealed class SwingTwistConstraintSettings extends TwoBodyConstraintSettings
        permits SwingTwistConstraintSettings.F, SwingTwistConstraintSettings.D {
    //region Jolt-Pointer
    private SwingTwistConstraintSettings(MemoryAddress handle) {
        super(handle);
    }

    public static SwingTwistConstraintSettings at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : Jolt.tryingDoublePrecision()
                ? new D(addr)
                : new F(addr);
    }
    //endregion Jolt-Pointer

    public ConstraintSpace getSpace() {
        return ConstraintSpace.values()[JPC_SwingTwistConstraintSettings_GetSpace(handle)];
    }

    public void setSpace(ConstraintSpace space) {
        JPC_SwingTwistConstraintSettings_SetSpace(handle, space.ordinal());
    }

    public abstract void getPosition1(FVec3 out);

    public abstract void getPosition1(DVec3 out);

    public abstract void setPosition1(FVec3 position1);

    public abstract void setPosition1(DVec3 position1);

    public void getTwistAxis1(FVec3 out) {
        JPC_SwingTwistConstraintSettings_GetTwistAxis1(handle, out.address());
    }

    public void setTwistAxis1(FVec3 twistAxis1) {
        JPC_SwingTwistConstraintSettings_SetTwistAxis1(handle, twistAxis1.address());
    }
    
    public void getPlaneAxis1(FVec3 out) {
        JPC_SwingTwistConstraintSettings_GetPlaneAxis1(handle, out.address());
    }

    public void setPlaneAxis1(FVec3 planeAxis1) {
        JPC_SwingTwistConstraintSettings_SetPlaneAxis1(handle, planeAxis1.address());
    }

    public abstract void getPosition2(FVec3 out);

    public abstract void getPosition2(DVec3 out);

    public abstract void setPosition2(FVec3 position2);

    public abstract void setPosition2(DVec3 position2);

    public void getTwistAxis2(FVec3 out) {
        JPC_SwingTwistConstraintSettings_GetTwistAxis2(handle, out.address());
    }

    public void setTwistAxis2(FVec3 twistAxis2) {
        JPC_SwingTwistConstraintSettings_SetTwistAxis2(handle, twistAxis2.address());
    }

    public void getPlaneAxis2(FVec3 out) {
        JPC_SwingTwistConstraintSettings_GetPlaneAxis2(handle, out.address());
    }

    public void setPlaneAxis2(FVec3 planeAxis2) {
        JPC_SwingTwistConstraintSettings_SetPlaneAxis2(handle, out.address());
    }

    public float getNormalHalfConeAngle() {
        return JPC_SwingTwistConstraintSettings_GetNormalHalfConeAngle(handle);
    }

    public void setNormalHalfConeAngle(float normalHalfConeAngle) {
        JPC_SwingTwistConstraintSettings_SetNormalHalfConeAngle(handle, normalHalfConeAngle);
    }

    public float getPlaneHalfConeAngle() {
        return JPC_SwingTwistConstraintSettings_GetPlaneHalfConeAngle(handle);
    }

    public void setPlaneHalfConeAngle(float planeHalfConeAngle) {
        JPC_SwingTwistConstraintSettings_SetPlaneHalfConeAngle(handle, planeHalfConeAngle);
    }

    public float getTwistMinAngle() {
        return JPC_SwingTwistConstraintSettings_GetTwistMinAngle(handle);
    }

    public void setTwistMinAngle(float twistMinAngle) {
        JPC_SwingTwistConstraintSettings_SetTwistMinAngle(handle, twistMinAngle);
    }

    public float getTwistMaxAngle() {
        return JPC_SwingTwistConstraintSettings_GetTwistMaxAngle(handle);
    }

    public void setTwistMaxAngle(float twistMaxAngle) {
        JPC_SwingTwistConstraintSettings_SetTwistMaxAngle(handle, twistMaxAngle);
    }

    public float getMaxFrictionTorque() {
        return JPC_SwingTwistConstraintSettings_GetMaxFrictionTorque(handle);
    }

    public void setMaxFrictionTorque(float maxFrictionTorque) {
        JPC_SwingTwistConstraintSettings_SetMaxFrictionTorque(handle, maxFrictionTorque);
    }

    static final class F extends DistanceConstraintSettings {
        private F(MemoryAddress handle) {
            super(handle);
        }
        
        @Override
        public void getPoint1(FVec3 out) {
            jolt.headers_f.JoltPhysicsC.JPC_DistanceConstraintSettings_GetPoint1(handle, out.address());
        }

        @Override
        public void getPoint1(DVec3 out) {
            throw Jolt.tryingDoublePrecision();
        }
    
        @Override
        public void setPoint1(FVec3 point1) {
            jolt.headers_f.JoltPhysicsC.JPC_DistanceConstraintSettings_SetPoint1(handle, point1.address());
        }
    
        @Override
        public void setPoint1(DVec3 point1) {
            throw Jolt.tryingDoublePrecision();
        }
    
        @Override
        public void getPoint2(FVec3 out) {
            jolt.headers_f.JoltPhysicsC.JPC_DistanceConstraintSettings_GetPoint2(handle, out.address());
        }
    
        @Override
        public void getPoint2(DVec3 out) {
            throw Jolt.tryingDoublePrecision();
        }
    
        @Override
        public void setPoint2(FVec3 point2) {
            jolt.headers_f.JoltPhysicsC.JPC_DistanceConstraintSettings_SetPoint2(handle, point2.address());
        }
    
        @Override
        public void setPoint2(DVec3 point2) {
            throw Jolt.tryingDoublePrecision();
        }
    }

    static final class D extends FixedConstraintSettings {
        private D(MemoryAddress handle) {
            super(handle);
        }

        @Override
        public void getPoint1(FVec3 out) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public void getPoint1(DVec3 out) {
            jolt.headers_d.JoltPhysicsC.JPC_DistanceConstraintSettings_GetPoint1(handle, out.address());
        }
    
        @Override
        public void setPoint1(FVec3 point1) {
            throw Jolt.tryingSinglePrecision();
        }
    
        @Override
        public void setPoint1(DVec3 point1) {
            jolt.headers_d.JoltPhysicsC.JPC_DistanceConstraintSettings_SetPoint1(handle, point1.address());
        }
    
        @Override
        public void getPoint2(FVec3 out) {
            throw Jolt.tryingSinglePrecision();
        }
    
        @Override
        public void getPoint2(DVec3 out) {
            jolt.headers_d.JoltPhysicsC.JPC_DistanceConstraintSettings_GetPoint2(handle, out.address());
        }
    
        @Override
        public void setPoint2(FVec3 point2) {
            throw Jolt.tryingSinglePrecision();
        }
    
        @Override
        public void setPoint2(DVec3 point2) {
            jolt.headers_d.JoltPhysicsC.JPC_DistanceConstraintSettings_SetPoint2(handle, point2.address());
        }
    }
}
