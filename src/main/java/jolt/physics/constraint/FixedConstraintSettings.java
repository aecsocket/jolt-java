package jolt.physics.constraint;

import jolt.Jolt;
import jolt.math.DVec3;
import jolt.math.FVec3;

import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public abstract sealed class FixedConstraintSettings extends TwoBodyConstraintSettings
        permits FixedConstraintSettings.F, FixedConstraintSettings.D {
    //region Jolt-Pointer
    private FixedConstraintSettings(MemoryAddress handle) {
        super(handle);
    }

    public static FixedConstraintSettings at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : Jolt.doublePrecision()
                ? new D(addr)
                : new F(addr);
    }
    //endregion Jolt-Pointer

    public static FixedConstraintSettings of() {
        return Jolt.doublePrecision() ? new D(JPC_FixedConstraintSettings_Create()) : new F(JPC_FixedConstraintSettings_Create());
    }

    public ConstraintSpace getSpace() {
        return ConstraintSpace.values()[JPC_FixedConstraintSettings_GetSpace(handle)];
    }

    public void setSpace(ConstraintSpace space) {
        JPC_FixedConstraintSettings_SetSpace(handle, (byte) space.ordinal());
    }

    public boolean getAutoDetectPoint() {
        return JPC_FixedConstraintSettings_GetAutoDetectPoint(handle);
    }

    public void setAutoDetectPoint(boolean autoDetectPoint) {
        JPC_FixedConstraintSettings_SetAutoDetectPoint(handle, autoDetectPoint);
    }

    public abstract void getPoint1(FVec3 out);

    public abstract void getPoint1(DVec3 out);

    public abstract void setPoint1(FVec3 point1);

    public abstract void setPoint1(DVec3 point1);

    public void getAxisX1(FVec3 out) {
        JPC_FixedConstraintSettings_GetAxisX1(handle, out.address());
    }

    public void setAxisX1(FVec3 axisX1) {
        JPC_FixedConstraintSettings_SetAxisX1(handle, axisX1.address());
    }

    public void getAxisY1(FVec3 out) {
        JPC_FixedConstraintSettings_GetAxisY1(handle, out.address());
    }

    public void setAxisY1(FVec3 axisY1) {
        JPC_FixedConstraintSettings_SetAxisY1(handle, axisY1.address());
    }

    public abstract void getPoint2(FVec3 out);

    public abstract void getPoint2(DVec3 out);

    public abstract void setPoint2(FVec3 point2);

    public abstract void setPoint2(DVec3 point2);

    public void getAxisX2(FVec3 out) {
        JPC_FixedConstraintSettings_GetAxisX2(handle, out.address());
    }

    public void setAxisX2(FVec3 axisX2) {
        JPC_FixedConstraintSettings_SetAxisX2(handle, axisX2.address());
    }

    public void getAxisY2(FVec3 out) {
        JPC_FixedConstraintSettings_GetAxisY2(handle, out.address());
    }

    public void setAxisY2(FVec3 axisY2) {
        JPC_FixedConstraintSettings_SetAxisY2(handle, axisY2.address());
    }

    static final class F extends FixedConstraintSettings {
        private F(MemoryAddress handle) {
            super(handle);
        }

        @Override
        public void getPoint1(FVec3 out) {
            jolt.headers_f.JoltPhysicsC.JPC_FixedConstraintSettings_GetPoint1(handle, out.address());
        }

        @Override
        public void getPoint1(DVec3 out) {
            throw Jolt.tryingDoublePrecision();
        }

        @Override
        public void setPoint1(FVec3 point1) {
            jolt.headers_f.JoltPhysicsC.JPC_FixedConstraintSettings_SetPoint1(handle, point1.address());
        }

        @Override
        public void setPoint1(DVec3 point1) {
            throw Jolt.tryingDoublePrecision();
        }

        @Override
        public void getPoint2(FVec3 out) {
            jolt.headers_f.JoltPhysicsC.JPC_FixedConstraintSettings_GetPoint2(handle, out.address());
        }

        @Override
        public void getPoint2(DVec3 point2) {
            throw Jolt.tryingDoublePrecision();
        }

        @Override
        public void setPoint2(FVec3 point2) {
            jolt.headers_f.JoltPhysicsC.JPC_FixedConstraintSettings_SetPoint2(handle, point2.address());
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
            jolt.headers_d.JoltPhysicsC.JPC_FixedConstraintSettings_GetPoint1(handle, out.address());
        }

        @Override
        public void setPoint1(FVec3 point1) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public void setPoint1(DVec3 point1) {
            jolt.headers_d.JoltPhysicsC.JPC_FixedConstraintSettings_SetPoint1(handle, point1.address());
        }

        @Override
        public void getPoint2(FVec3 out) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public void getPoint2(DVec3 point2) {
            jolt.headers_d.JoltPhysicsC.JPC_FixedConstraintSettings_GetPoint2(handle, point2.address());
        }

        @Override
        public void setPoint2(FVec3 point2) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public void setPoint2(DVec3 point2) {
            jolt.headers_d.JoltPhysicsC.JPC_FixedConstraintSettings_SetPoint2(handle, point2.address());
        }
    }
}
