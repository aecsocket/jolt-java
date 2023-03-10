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

public abstract sealed class SixDOFConstraintSettings extends TwoBodyConstraintSettings
        permits SixDOFConstraintSettings.F, SixDOFConstraintSettings.D {
    //region Jolt-Pointer
    private SixDOFConstraintSettings(MemoryAddress handle) {
        super(handle);
    }

    public static SixDOFConstraintSettings at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : Jolt.tryingDoublePrecision()
                ? new D(addr)
                : new F(addr);
    }
    //endregion Jolt-Pointer

    public static SixDOFConstraintSettings of() {
        return new SixDOFConstraintSettings(JPC_SixDOFConstraintSettings_Create());
    }

    public ConstraintSpace getSpace() {
        return ConstraintSpace.values()[JPC_SixDOFConstraintSettings_GetSpace(handle)];
    }

    public void setSpace(ConstraintSpace space) {
        JPC_SixDOFConstraintSettings_SetSpace(handle, space.ordinal());
    }

    public abstract void getPoint1(FVec3 out);

    public abstract void getPoint1(DVec3 out);

    public abstract void setPoint1(FVec3 point1);

    public abstract void setPoint1(DVec3 point1);

    public void getAxisX1(FVec3 out) {
        JPC_SixDOFConstraintSettings_GetAxisX1(handle, out.address());
    }

    public void setAxisX1(FVec3 axisX1) {
        JPC_SixDOFConstraintSettings_SetAxisX1(handle, axisX1.address());
    }

    public void getAxisY1(FVec3 out) {
        JPC_SixDOFConstraintSettings_GetAxisY1(handle, out.address());
    }

    public void setAxisY1(FVec3 axisY1) {
        JPC_SixDOFConstraintSettings_SetAxisY1(handle, axisY1.address());
    }

    public abstract void getPoint2(FVec3 out);

    public abstract void getPoint2(DVec3 out);

    public abstract void setPoint2(FVec3 point2);

    public abstract void setPoint2(DVec3 point2);

    public void getAxisX2(FVec3 out) {
        JPC_SixDOFConstraintSettings_GetAxisX2(handle, out.address());
    }

    public void setAxisX2(FVec3 axisX2) {
        JPC_SixDOFConstraintSettings_SetAxisX2(handle, axisX2.address());
    }

    public void getAxisY2(FVec3 out) {
        JPC_SixDOFConstraintSettings_GetAxisY2(handle, out.address());
    }

    public void setAxisY2(FVec3 axisY2) {
        JPC_SixDOFConstraintSettings_SetAxisY2(handle, axisY2.address());
    }

    public float[] getMaxFriction() {
        // TODO
    }

    public void setMaxFriction(float... maxFriction) {
        // TODO
    }

    public float[] getLimitMin() {
        // TODO
    }

    public void setLimitMin(float... limitMin) {
        // TODO
    }

    public float[] getLimitMax() {
        // TODO
    }

    public void setLimitMax(float... limitMax) {
        // TODO
    }

    public void makeFreeAxis(Axis axis) {
        JPC_SixDOFConstraintSettings_MakeFreeAxis(handle, axis.ordinal());
    }

    public boolean isFreeAxis(Axis axis) {
        return JPC_SixDOFConstraintSettings_IsFreeAxis(handle, axis.ordinal());
    }

    public void makeFixedAxis(Axis axis) {
        JPC_SixDOFConstraintSettings_MakeFixedAxis(handle, axis.ordinal());
    }

    public boolean isFixedAxis(Axis axis) {
        return JPC_SixDOFConstraintSettings_IsFixedAxis(handle, axis.ordinal());
    }

    public void setLimitedAxis(Axis axis, float min, float max) {
        JPC_SixDOFConstraintSettings_SetLimitedAxis(handle, axis.ordinal(), min, max);
    }

    public MotorSettings[] getMotorSettings() {
        // TODO
    }

    static final class F extends SixDOFConstraintSettings {
        private F(MemoryAddress handle) {
            super(handle);
        }
        
        @Override
        public void getPoint1(FVec3 out) {
            jolt.headers_f.JoltPhysicsC.JPC_SixDOFConstraintSettings_GetPoint1(handle, out.address());
        }

        @Override
        public void getPoint1(DVec3 out) {
            throw Jolt.tryingDoublePrecision();
        }
    
        @Override
        public void setPoint1(FVec3 point1) {
            jolt.headers_f.JoltPhysicsC.JPC_SixDOFConstraintSettings_SetPoint1(handle, point1.address());
        }
    
        @Override
        public void setPoint1(DVec3 point1) {
            throw Jolt.tryingDoublePrecision();
        }
    
        @Override
        public void getPoint2(FVec3 out) {
            jolt.headers_f.JoltPhysicsC.JPC_SixDOFConstraintSettings_GetPoint2(handle, out.address());
        }
    
        @Override
        public void getPoint2(DVec3 out) {
            throw Jolt.tryingDoublePrecision();
        }
    
        @Override
        public void setPoint2(FVec3 point2) {
            jolt.headers_f.JoltPhysicsC.JPC_SixDOFConstraintSettings_SetPoint2(handle, point2.address());
        }
    
        @Override
        public void setPoint2(DVec3 point2) {
            throw Jolt.tryingDoublePrecision();
        }
    }

    static final class D extends SixDOFConstraintSettings {
        private D(MemoryAddress handle) {
            super(handle);
        }

        @Override
        public void getPoint1(FVec3 out) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public void getPoint1(DVec3 out) {
            jolt.headers_d.JoltPhysicsC.JPC_SixDOFConstraintSettings_GetPoint1(handle, out.address());
        }
    
        @Override
        public void setPoint1(FVec3 point1) {
            throw Jolt.tryingSinglePrecision();
        }
    
        @Override
        public void setPoint1(DVec3 point1) {
            jolt.headers_d.JoltPhysicsC.JPC_SixDOFConstraintSettings_SetPoint1(handle, point1.address());
        }
    
        @Override
        public void getPoint2(FVec3 out) {
            throw Jolt.tryingSinglePrecision();
        }
    
        @Override
        public void getPoint2(DVec3 out) {
            jolt.headers_d.JoltPhysicsC.JPC_SixDOFConstraintSettings_GetPoint2(handle, out.address());
        }
    
        @Override
        public void setPoint2(FVec3 point2) {
            throw Jolt.tryingSinglePrecision();
        }
    
        @Override
        public void setPoint2(DVec3 point2) {
            jolt.headers_d.JoltPhysicsC.JPC_SixDOFConstraintSettings_SetPoint2(handle, point2.address());
        }
    }
}
