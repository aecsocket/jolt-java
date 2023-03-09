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

public abstract sealed class HingeConstraintSettings extends TwoBodyConstraintSettings
        permits HingeConstraintSettings.F, HingeConstraintSettings.D {
    //region Jolt-Pointer
    private HingeConstraintSettings(MemoryAddress handle) {
        super(handle);
    }

    public static HingeConstraintSettings at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : Jolt.tryingDoublePrecision()
                ? new D(addr)
                : new F(addr);
    }
    //endregion Jolt-Pointer

    public ConstraintSpace getSpace() {
        return ConstraintSpace.values()[JPC_HingeConstraintSettings_GetSpace(handle)];
    }

    public void setSpace(ConstraintSpace space) {
        JPC_HingeConstraintSettings_SetSpace(handle, space.ordinal());
    }

    public abstract void getPoint1(FVec3 out);

    public abstract void getPoint1(DVec3 out);

    public abstract void setPoint1(FVec3 point1);

    public abstract void setPoint1(DVec3 point1);

    public void getHingeAxis1(FVec3 out) {
        JPC_HingeConstraintSettings_GetHingeAxis1(handle, out.address());
    }

    public void setHingeAxis1(FVec3 hingeAxis1) {
        JPC_HingeConstraintSettings_SetHingeAxis1(handle, hingeAxis1.address());
    }

    public void getNormalAxis1(FVec3 out) {
        JPC_HingeConstraintSettings_GetNormalAxis1(handle, out.address());
    }

    public void setNormalAxis1(FVec3 normalAxis1) {
        JPC_HingeConstraintSettings_SetNormalAxis1(handle, normalAxis1.address());
    }

    public abstract void getPoint2(FVec3 out);

    public abstract void getPoint2(DVec3 out);

    public abstract void setPoint2(FVec3 point2);

    public abstract void setPoint2(DVec3 point2);

    public void getHingeAxis2(FVec3 out) {
        JPC_HingeConstraintSettings_GetHingeAxis2(handle, out.address());
    }

    public void setHingeAxis2(FVec3 hingeAxis2) {
        JPC_HingeConstraintSettings_SetHingeAxis2(handle, hingeAxis2.address());
    }

    public void getNormalAxis2(FVec3 out) {
        JPC_HingeConstraintSettings_GetNormalAxis2(handle, out.address());
    }

    public void setNormalAxis2(FVec3 normalAxis2) {
        JPC_HingeConstraintSettings_SetNormalAxis2(handle, normalAxis2.address());
    }

    public float getLimitsMin() {
        return JPC_HingeConstraintSettings_GetLimitsMin(handle);
    }

    public void setLimitsMin(float limitsMin) {
        JPC_HingeConstraintSettings_SetLimitsMin(handle, limitsMin);
    }

    public float getLimitsMax() {
        return JPC_HingeConstraintSettings_GetLimitsMax(handle);
    }

    public void setLimitsMax(float limitsMax) {
        JPC_HingeConstraintSettings_SetLimitsMax(handle, limitsMax);
    }

    public float getMaxFrictionTorque() {
        return JPC_HingeConstraintSettings_GetMaxFrictionTorque(handle);
    }

    public void setMaxFrictionTorque(float maxFrictionTorque) {
        JPC_HingeConstraintSettings_SetMaxFrictionTorque(handle, maxFrictionTorque);
    }

    public MotorSettings getMotorSettings(SegmentAllocator alloc) {
        return MotorSettings.at(JPC_HingeConstraintSettings_GetMotorSettings(alloc, handle));
    }

    public void setMotorSettings(MotorSettings motorSettings) {
        JPC_HingeConstraintSettings_SetMotorSettings(handle, motorSettings.address());
    }

    static final class F extends HingeConstraintSettings {
        private F(MemoryAddress handle) {
            super(handle);
        }

        @Override
        public void getPoint1(FVec3 out) {
            jolt.headers_f.JoltPhysicsC.JPC_HingeConstraintSettings_GetPoint1(handle, out.address());
        }

        @Override
        public void getPoint1(DVec3 out) {
            throw Jolt.tryingDoublePrecision();
        }

        @Override
        public void setPoint1(FVec3 point1) {
            jolt.headers_f.JoltPhysicsC.JPC_HingeConstraintSettings_SetPoint1(handle, point1.address());
        }

        @Override
        public void setPoint1(DVec3 point1) {
            throw Jolt.tryingDoublePrecision();
        }

        @Override
        public void getPoint2(FVec3 out) {
            jolt.headers_f.JoltPhysicsC.JPC_HingeConstraintSettings_GetPoint2(handle, out.address());
        }

        @Override
        public void getPoint2(DVec3 point2) {
            throw Jolt.tryingDoublePrecision();
        }

        @Override
        public void setPoint2(FVec3 point2) {
            jolt.headers_f.JoltPhysicsC.JPC_HingeConstraintSettings_SetPoint2(handle, out.address());
        }

        @Override
        public void setPoint2(DVec3 point2) {
            throw Jolt.tryingDoublePrecision();
        }
    }

    static final class D extends HingeConstraintSettings {
        private D(MemoryAddress handle) {
            super(handle);
        }

        @Override
        public void getPoint1(FVec3 out) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public void getPoint1(DVec3 out) {
            jolt.headers_d.JoltPhysicsC.JPC_HingeConstraintSettings_GetPoint1(handle, out.address());
        }

        @Override
        public void setPoint1(FVec3 point1) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public void setPoint1(DVec3 point1) {
            jolt.headers_d.JoltPhysicsC.JPC_HingeConstraintSettings_SetPoint1(handle, point1.address());
        }

        @Override
        public void getPoint2(FVec3 out) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public void getPoint2(DVec3 point2) {
            jolt.headers_d.JoltPhysicsC.JPC_HingeConstraintSettings_GetPoint2(handle, out.address());
        }

        @Override
        public void setPoint2(FVec3 point2) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public void setPoint2(DVec3 point2) {
            jolt.headers_d.JoltPhysicsC.JPC_HingeConstraintSettings_SetPoint2(handle, out.address());
        }
    }
}
