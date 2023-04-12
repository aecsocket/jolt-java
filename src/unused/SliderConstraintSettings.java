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

public abstract sealed class SliderConstraintSettings extends TwoBodyConstraintSettings
        permits SliderConstraintSettings.F, SliderConstraintSettings.D {
    //region Jolt-Pointer
    private SliderConstraintSettings(MemoryAddress handle) {
        super(handle);
    }

    public static SliderConstraintSettings at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : Jolt.tryingDoublePrecision()
                ? new D(addr)
                : new F(addr);
    }
    //endregion Jolt-Pointer

    public static SliderConstraintSettings of() {
        return new SliderConstraintSettings(JPC_SliderConstraintSettings_Create());
    }

    public ConstraintSpace getSpace() {
        return ConstraintSpace.values()[JPC_SliderConstraintSettings_GetSpace(handle)];
    }

    public void setSpace(ConstraintSpace space) {
        JPC_SliderConstraintSettings_SetSpace(handle, space.ordinal());
    }

    public boolean getAutoDetectPoint() {
        return JPC_SliderConstraintSettings_GetAutoDetectPoint(handle);
    }

    public void setAutoDetectPoint(boolean autoDetectPoint) {
        JPC_SliderConstraintSettings_SetAutoDetectPoint(handle, autoDetectPoint);
    }

    public abstract void getPoint1(FVec3 out);

    public abstract void getPoint1(DVec3 out);

    public abstract void setPoint1(FVec3 point1);

    public abstract void setPoint1(DVec3 point1);

    public void getSliderAxis1(FVec3 out) {
        JPC_SliderConstraintSettings_GetSliderAxis1(handle, out.address());
    }

    public void setSliderAxis1(FVec3 sliderAxis1) {
        JPC_SliderConstraintSettings_SetSliderAxis1(handle, sliderAxis1.address());
    }

    public void getNormalAxis1(FVec3 out) {
        JPC_SliderConstraintSettings_GetNormalAxis1(handle, out.address());
    }

    public void setNormalAxis1(FVec3 normalAxis1) {
        JPC_SliderConstraintSettings_SetNormalAxis1(handle, normalAxis1.address());
    }

    public abstract void getPoint2(FVec3 out);

    public abstract void getPoint2(DVec3 out);

    public abstract void setPoint2(FVec3 point2);

    public abstract void setPoint2(DVec3 point2);

    public void getSliderAxis2(FVec3 out) {
        JPC_SliderConstraintSettings_GetSliderAxis2(handle, out.address());
    }

    public void setSliderAxis2(FVec3 sliderAxis2) {
        JPC_SliderConstraintSettings_SetSliderAxis2(handle, sliderAxis2.address());
    }

    public void getNormalAxis2(FVec3 out) {
        JPC_SliderConstraintSettings_GetNormalAxis2(handle, out.address());
    }

    public void setNormalAxis2(FVec3 normalAxis2) {
        JPC_SliderConstraintSettings_SetNormalAxis2(handle, normalAxis2.address());
    }

    public float getLimitsMin() {
        return JPC_SliderConstraintSettings_GetLimitsMin(handle);
    }

    public void setLimitsMin(float limitsMin) {
        JPC_SliderConstraintSettings_SetLimitsMin(handle, limitsMin);
    }

    public float getLimitsMax() {
        return JPC_SliderConstraintSettings_GetLimitsMax(handle);
    }

    public void setLimitsMax(float limitsMax) {
        JPC_SliderConstraintSettings_SetLimitsMax(handle, limitsMax);
    }

    public float getFrequency() {
        return JPC_SliderConstraintSettings_GetFrequency(handle);
    }

    public void setFrequency(float frqeuency) {
        JPC_SliderConstraintSettings_SetFrequency(handle, frequency);
    }

    public float getDamping() {
        return JPC_SliderConstraintSettings_GetDamping(handle);
    }

    public void setDamping(float damping) {
        JPC_SliderConstraintSettings_SetDamping(handle, damping);
    }

    public float getMaxFrictionForce() {
        return JPC_SliderConstraintSettings_GetMaxFrictionForce(handle);
    }

    public void setMaxFrictionForce(float maxFrictionForce) {
        JPC_SliderConstraintSettings_SetMaxFrictionForce(handle, maxFrictionForce);
    }

    public MotorSettings getMotorSettings(SegmentAllocator alloc) {
        return MotorSettings.at(JPC_SliderConstraintSettings_GetMotorSettings(alloc, handle));
    }

    public void setMotorSettings(MotorSettings motorSettings) {
        JPC_SliderConstraintSettings_SetMotorSettings(motorSettings.address());
    }

    static final class F extends SliderConstraintSettings {
        private F(MemoryAddress handle) {
            super(handle);
        }
        
        @Override
        public void getPoint1(FVec3 out) {
            jolt.headers_f.JoltPhysicsC.JPC_SliderConstraintSettings_GetPoint1(handle, out.address());
        }

        @Override
        public void getPoint1(DVec3 out) {
            throw Jolt.tryingDoublePrecision();
        }
    
        @Override
        public void setPoint1(FVec3 point1) {
            jolt.headers_f.JoltPhysicsC.JPC_SliderConstraintSettings_SetPoint1(handle, point1.address());
        }
    
        @Override
        public void setPoint1(DVec3 point1) {
            throw Jolt.tryingDoublePrecision();
        }
    
        @Override
        public void getPoint2(FVec3 out) {
            jolt.headers_f.JoltPhysicsC.JPC_SliderConstraintSettings_GetPoint2(handle, out.address());
        }
    
        @Override
        public void getPoint2(DVec3 out) {
            throw Jolt.tryingDoublePrecision();
        }
    
        @Override
        public void setPoint2(FVec3 point2) {
            jolt.headers_f.JoltPhysicsC.JPC_SliderConstraintSettings_SetPoint2(handle, point2.address());
        }
    
        @Override
        public void setPoint2(DVec3 point2) {
            throw Jolt.tryingDoublePrecision();
        }
    }

    static final class D extends SliderConstraintSettings {
        private D(MemoryAddress handle) {
            super(handle);
        }

        @Override
        public void getPoint1(FVec3 out) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public void getPoint1(DVec3 out) {
            jolt.headers_d.JoltPhysicsC.JPC_SliderConstraintSettings_GetPoint1(handle, out.address());
        }
    
        @Override
        public void setPoint1(FVec3 point1) {
            throw Jolt.tryingSinglePrecision();
        }
    
        @Override
        public void setPoint1(DVec3 point1) {
            jolt.headers_d.JoltPhysicsC.JPC_SliderConstraintSettings_SetPoint1(handle, point1.address());
        }
    
        @Override
        public void getPoint2(FVec3 out) {
            throw Jolt.tryingSinglePrecision();
        }
    
        @Override
        public void getPoint2(DVec3 out) {
            jolt.headers_d.JoltPhysicsC.JPC_SliderConstraintSettings_GetPoint2(handle, out.address());
        }
    
        @Override
        public void setPoint2(FVec3 point2) {
            throw Jolt.tryingSinglePrecision();
        }
    
        @Override
        public void setPoint2(DVec3 point2) {
            jolt.headers_d.JoltPhysicsC.JPC_SliderConstraintSettings_SetPoint2(handle, point2.address());
        }
    }
}
