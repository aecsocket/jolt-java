package jolt.physics.constraint;

import jolt.Jolt;
import jolt.math.DVec3;
import jolt.math.FVec3;

import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public abstract sealed class DistanceConstraintSettings extends TwoBodyConstraintSettings
        permits DistanceConstraintSettings.F, DistanceConstraintSettings.D {
    //region Jolt-Pointer
    private DistanceConstraintSettings(MemoryAddress handle) {
        super(handle);
    }

    public static DistanceConstraintSettings at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : Jolt.doublePrecision()
                ? new D(addr)
                : new F(addr);
    }
    //endregion Jolt-Pointer

    public static DistanceConstraintSettings of() {
        return Jolt.doublePrecision() ? new D(JPC_DistanceConstraintSettings_Create()) : new F(JPC_DistanceConstraintSettings_Create());
    }

    public ConstraintSpace getSpace() {
        return ConstraintSpace.values()[JPC_DistanceConstraintSettings_GetSpace(handle)];
    }

    public void setSpace(ConstraintSpace space) {
        JPC_DistanceConstraintSettings_SetSpace(handle, (byte) space.ordinal());
    }

    public abstract void getPoint1(FVec3 out);

    public abstract void getPoint1(DVec3 out);

    public abstract void setPoint1(FVec3 point1);

    public abstract void setPoint1(DVec3 point1);

    public abstract void getPoint2(FVec3 out);

    public abstract void getPoint2(DVec3 out);

    public abstract void setPoint2(FVec3 point2);

    public abstract void setPoint2(DVec3 point2);

    public float getMinDistance() {
        return JPC_DistanceConstraintSettings_GetMinDistance(handle);
    }

    public void setMinDistance(float minDistance) {
        JPC_DistanceConstraintSettings_SetMinDistance(handle, minDistance);
    }

    public float getMaxDistance() {
        return JPC_DistanceConstraintSettings_GetMaxDistance(handle);
    }

    public void setMaxDistance(float maxDistance) {
        JPC_DistanceConstraintSettings_SetMaxDistance(handle, maxDistance);
    }

    public float getFrequency() {
        return JPC_DistanceConstraintSettings_GetFrequency(handle);
    }

    public void setFrequency(float frequency) {
        JPC_DistanceConstraintSettings_SetFrequency(handle, frequency);
    }

    public float getDamping() {
        return JPC_DistanceConstraintSettings_GetDamping(handle);
    }

    public void setDamping(float damping) {
        JPC_DistanceConstraintSettings_SetDamping(handle, damping);
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

    static final class D extends DistanceConstraintSettings {
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
