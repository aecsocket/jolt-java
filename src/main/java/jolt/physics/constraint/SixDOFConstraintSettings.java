package jolt.physics.constraint;

import jolt.Jolt;
import jolt.math.DVec3;
import jolt.math.FVec3;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySession;

import static jolt.headers.JoltPhysicsC.*;

public abstract sealed class SixDOFConstraintSettings extends TwoBodyConstraintSettings
        permits SixDOFConstraintSettings.F, SixDOFConstraintSettings.D {
    //region Jolt-Pointer
    private SixDOFConstraintSettings(MemoryAddress handle) {
        super(handle);
    }

    public static SixDOFConstraintSettings at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : Jolt.doublePrecision()
                ? new D(addr)
                : new F(addr);
    }
    //endregion Jolt-Pointer

    public static SixDOFConstraintSettings of() {
        return Jolt.doublePrecision() ? new D(JPC_SixDOFConstraintSettings_Create()) : new F(JPC_SixDOFConstraintSettings_Create());
    }

    public ConstraintSpace getSpace() {
        return ConstraintSpace.values()[JPC_SixDOFConstraintSettings_GetSpace(handle)];
    }

    public void setSpace(ConstraintSpace space) {
        JPC_SixDOFConstraintSettings_SetSpace(handle, (byte) space.ordinal());
    }

    public abstract void getPosition1(FVec3 out);

    public abstract void getPosition1(DVec3 out);

    public abstract void setPosition1(FVec3 position1);

    public abstract void setPosition1(DVec3 position1);

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

    public abstract void getPosition2(FVec3 out);

    public abstract void getPosition2(DVec3 out);

    public abstract void setPosition2(FVec3 position2);

    public abstract void setPosition2(DVec3 position2);

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
        var values = JPC_SixDOFConstraintSettings_GetMaxFriction(handle);
        var result = new float[6];
        for (int i = 0; i < 6; i++) {
            result[i] = values.getAtIndex(C_FLOAT, i);
        }
        return result;
    }

    public void setMaxFriction(float... maxFriction) {
        var values = JPC_SixDOFConstraintSettings_GetMaxFriction(handle);
        for (int i = 0; i < 6; i++) {
            values.setAtIndex(C_FLOAT, i, maxFriction[i]);
        }
    }

    public float[] getLimitMin() {
        var values = JPC_SixDOFConstraintSettings_GetLimitMin(handle);
        var result = new float[6];
        for (int i = 0; i < 6; i++) {
            result[i] = values.getAtIndex(C_FLOAT, i);
        }
        return result;
    }

    public void setLimitMin(float... limitMin) {
        var values = JPC_SixDOFConstraintSettings_GetLimitMin(handle);
        for (int i = 0; i < 6; i++) {
            values.setAtIndex(C_FLOAT, i, limitMin[i]);
        }
    }

    public float[] getLimitMax() {
        var values = JPC_SixDOFConstraintSettings_GetLimitMax(handle);
        var result = new float[6];
        for (int i = 0; i < 6; i++) {
            result[i] = values.getAtIndex(C_FLOAT, i);
        }
        return result;
    }

    public void setLimitMax(float... limitMax) {
        var values = JPC_SixDOFConstraintSettings_GetLimitMax(handle);
        for (int i = 0; i < 6; i++) {
            values.setAtIndex(C_FLOAT, i, limitMax[i]);
        }
    }

    public void makeFreeAxis(Axis axis) {
        JPC_SixDOFConstraintSettings_MakeFreeAxis(handle, (byte) axis.ordinal());
    }

    public boolean isFreeAxis(Axis axis) {
        return JPC_SixDOFConstraintSettings_IsFreeAxis(handle, (byte) axis.ordinal());
    }

    public void makeFixedAxis(Axis axis) {
        JPC_SixDOFConstraintSettings_MakeFixedAxis(handle, (byte) axis.ordinal());
    }

    public boolean isFixedAxis(Axis axis) {
        return JPC_SixDOFConstraintSettings_IsFixedAxis(handle, (byte) axis.ordinal());
    }

    public void setLimitedAxis(Axis axis, float min, float max) {
        JPC_SixDOFConstraintSettings_SetLimitedAxis(handle, (byte) axis.ordinal(), min, max);
    }

    public MotorSettings[] getMotorSettings(MemorySession alloc) {
        var values = JPC_SixDOFConstraintSettings_GetMotorSettings(handle);
        var result = new MotorSettings[6];
        for (int i = 0; i < 6; i++) {
            result[i] = MotorSettings.at(alloc, values.getAtIndex(C_POINTER, i));
        }
        return result;
    }

    static final class F extends SixDOFConstraintSettings {
        private F(MemoryAddress handle) {
            super(handle);
        }
        
        @Override
        public void getPosition1(FVec3 out) {
            jolt.headers_f.JoltPhysicsC.JPC_SixDOFConstraintSettings_GetPosition1(handle, out.address());
        }

        @Override
        public void getPosition1(DVec3 out) {
            throw Jolt.tryingDoublePrecision();
        }
    
        @Override
        public void setPosition1(FVec3 position1) {
            jolt.headers_f.JoltPhysicsC.JPC_SixDOFConstraintSettings_SetPosition1(handle, position1.address());
        }
    
        @Override
        public void setPosition1(DVec3 position1) {
            throw Jolt.tryingDoublePrecision();
        }
    
        @Override
        public void getPosition2(FVec3 out) {
            jolt.headers_f.JoltPhysicsC.JPC_SixDOFConstraintSettings_GetPosition2(handle, out.address());
        }
    
        @Override
        public void getPosition2(DVec3 out) {
            throw Jolt.tryingDoublePrecision();
        }
    
        @Override
        public void setPosition2(FVec3 position2) {
            jolt.headers_f.JoltPhysicsC.JPC_SixDOFConstraintSettings_SetPosition2(handle, position2.address());
        }
    
        @Override
        public void setPosition2(DVec3 position2) {
            throw Jolt.tryingDoublePrecision();
        }
    }

    static final class D extends SixDOFConstraintSettings {
        private D(MemoryAddress handle) {
            super(handle);
        }

        @Override
        public void getPosition1(FVec3 out) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public void getPosition1(DVec3 out) {
            jolt.headers_d.JoltPhysicsC.JPC_SixDOFConstraintSettings_GetPosition1(handle, out.address());
        }
    
        @Override
        public void setPosition1(FVec3 position1) {
            throw Jolt.tryingSinglePrecision();
        }
    
        @Override
        public void setPosition1(DVec3 position1) {
            jolt.headers_d.JoltPhysicsC.JPC_SixDOFConstraintSettings_SetPosition1(handle, position1.address());
        }
    
        @Override
        public void getPosition2(FVec3 out) {
            throw Jolt.tryingSinglePrecision();
        }
    
        @Override
        public void getPosition2(DVec3 out) {
            jolt.headers_d.JoltPhysicsC.JPC_SixDOFConstraintSettings_GetPosition2(handle, out.address());
        }
    
        @Override
        public void setPosition2(FVec3 position2) {
            throw Jolt.tryingSinglePrecision();
        }
    
        @Override
        public void setPosition2(DVec3 position2) {
            jolt.headers_d.JoltPhysicsC.JPC_SixDOFConstraintSettings_SetPosition2(handle, position2.address());
        }
    }
}
