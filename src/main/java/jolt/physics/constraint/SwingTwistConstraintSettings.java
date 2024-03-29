package jolt.physics.constraint;

import jolt.Jolt;
import jolt.math.DVec3;
import jolt.math.FVec3;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySession;

import static jolt.headers.JoltPhysicsC.*;

public abstract sealed class SwingTwistConstraintSettings extends TwoBodyConstraintSettings
        permits SwingTwistConstraintSettings.F, SwingTwistConstraintSettings.D {
    //region Jolt-Pointer
    private SwingTwistConstraintSettings(MemoryAddress handle) {
        super(handle);
    }

    public static SwingTwistConstraintSettings at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : Jolt.doublePrecision()
                ? new D(addr)
                : new F(addr);
    }
    //endregion Jolt-Pointer

    public static SwingTwistConstraintSettings of() {
        return Jolt.doublePrecision() ? new D(JPC_SwingTwistConstraintSettings_Create()) : new F(JPC_SwingTwistConstraintSettings_Create());
    }

    public ConstraintSpace getSpace() {
        return ConstraintSpace.values()[JPC_SwingTwistConstraintSettings_GetSpace(handle)];
    }

    public void setSpace(ConstraintSpace space) {
        JPC_SwingTwistConstraintSettings_SetSpace(handle, (byte) space.ordinal());
    }

    public abstract void getPoint1(FVec3 out);

    public abstract void getPoint1(DVec3 out);

    public abstract void setPoint1(FVec3 position1);

    public abstract void setPoint1(DVec3 position1);

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

    public abstract void getPoint2(FVec3 out);

    public abstract void getPoint2(DVec3 out);

    public abstract void setPoint2(FVec3 position2);

    public abstract void setPoint2(DVec3 position2);

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
        JPC_SwingTwistConstraintSettings_SetPlaneAxis2(handle, planeAxis2.address());
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

    public MotorSettings getSwingMotorSettings(MemorySession alloc) {
        return MotorSettings.at(alloc, JPC_SwingTwistConstraintSettings_GetSwingMotorSettings(handle));
    }

    public MotorSettings getTwistMotorSettings(MemorySession alloc) {
        return MotorSettings.at(alloc, JPC_SwingTwistConstraintSettings_GetTwistMotorSettings(handle));
    }

    static final class F extends SwingTwistConstraintSettings {
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

    static final class D extends SwingTwistConstraintSettings {
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
