package jolt.physics.constraint;

import jolt.Jolt;
import jolt.math.DVec3;
import jolt.math.FVec3;

import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public abstract sealed class ConeConstraintSettings extends TwoBodyConstraintSettings
        permits ConeConstraintSettings.F, ConeConstraintSettings.D {
    //region Jolt-Pointer
    private ConeConstraintSettings(MemoryAddress handle) {
        super(handle);
    }

    public static ConeConstraintSettings at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : Jolt.doublePrecision()
                ? new D(addr)
                : new F(addr);
    }
    //endregion Jolt-Pointer

    public static ConeConstraintSettings of() {
        return Jolt.doublePrecision() ? new D(JPC_ConeConstraintSettings_Create()) : new F(JPC_ConeConstraintSettings_Create());
    }

    public ConstraintSpace getSpace() {
        return ConstraintSpace.values()[JPC_ConeConstraintSettings_GetSpace(handle)];
    }

    public void setSpace(ConstraintSpace space) {
        JPC_ConeConstraintSettings_SetSpace(handle, (byte) space.ordinal());
    }

    public abstract void getPoint1(FVec3 out);

    public abstract void getPoint1(DVec3 out);

    public abstract void setPoint1(FVec3 point1);

    public abstract void setPoint1(DVec3 point1);

    public void getTwistAxis1(FVec3 out) {
        JPC_ConeConstraintSettings_GetTwistAxis1(handle, out.address());
    }

    public void setTwistAxis1(FVec3 twistAxis1) {
        JPC_ConeConstraintSettings_SetTwistAxis1(handle, twistAxis1.address());
    }

    public abstract void getPoint2(FVec3 out);

    public abstract void getPoint2(DVec3 out);

    public abstract void setPoint2(FVec3 point2);

    public abstract void setPoint2(DVec3 point2);

    public void getTwistAxis2(FVec3 out) {
        JPC_ConeConstraintSettings_GetTwistAxis2(handle, out.address());
    }

    public void setTwistAxis2(FVec3 twistAxis2) {
        JPC_ConeConstraintSettings_SetTwistAxis2(handle, twistAxis2.address());
    }

    public float getHalfConeAngle() {
        return JPC_ConeConstraintSettings_GetHalfConeAngle(handle);
    }

    public void setHalfConeAngle(float halfConeAngle) {
        JPC_ConeConstraintSettings_SetHalfConeAngle(handle, halfConeAngle);
    }

    static final class F extends ConeConstraintSettings {
        private F(MemoryAddress handle) {
            super(handle);
        }

        @Override
        public void getPoint1(FVec3 out) {
            jolt.headers_f.JoltPhysicsC.JPC_ConeConstraintSettings_GetPoint1(handle, out.address());
        }

        @Override
        public void getPoint1(DVec3 out) {
            throw Jolt.tryingDoublePrecision();
        }

        @Override
        public void setPoint1(FVec3 point1) {
            jolt.headers_f.JoltPhysicsC.JPC_ConeConstraintSettings_SetPoint1(handle, point1.address());
        }

        @Override
        public void setPoint1(DVec3 point1) {
            throw Jolt.tryingDoublePrecision();
        }

        @Override
        public void getPoint2(FVec3 out) {
            jolt.headers_f.JoltPhysicsC.JPC_ConeConstraintSettings_GetPoint2(handle, out.address());
        }

        @Override
        public void getPoint2(DVec3 point2) {
            throw Jolt.tryingDoublePrecision();
        }

        @Override
        public void setPoint2(FVec3 point2) {
            jolt.headers_f.JoltPhysicsC.JPC_ConeConstraintSettings_SetPoint2(handle, point2.address());
        }

        @Override
        public void setPoint2(DVec3 point2) {
            throw Jolt.tryingDoublePrecision();
        }
    }

    static final class D extends ConeConstraintSettings {
        private D(MemoryAddress handle) {
            super(handle);
        }

        @Override
        public void getPoint1(FVec3 out) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public void getPoint1(DVec3 out) {
            jolt.headers_d.JoltPhysicsC.JPC_ConeConstraintSettings_GetPoint1(handle, out.address());
        }

        @Override
        public void setPoint1(FVec3 point1) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public void setPoint1(DVec3 point1) {
            jolt.headers_d.JoltPhysicsC.JPC_ConeConstraintSettings_SetPoint1(handle, point1.address());
        }

        @Override
        public void getPoint2(FVec3 out) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public void getPoint2(DVec3 point2) {
            jolt.headers_d.JoltPhysicsC.JPC_ConeConstraintSettings_GetPoint2(handle, point2.address());
        }

        @Override
        public void setPoint2(FVec3 point2) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public void setPoint2(DVec3 point2) {
            jolt.headers_d.JoltPhysicsC.JPC_ConeConstraintSettings_SetPoint2(handle, point2.address());
        }
    }
}
