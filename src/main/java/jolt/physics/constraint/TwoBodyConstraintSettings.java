package jolt.physics.constraint;

import jolt.DestroyableJoltNative;
import jolt.geometry.AABox;
import jolt.math.FMat44;
import jolt.math.FVec3;
import jolt.physics.body.MutableBody;
import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;
import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public sealed class TwoBodyConstraintSettings extends ConstraintSettings
        permits FixedConstraintSettings, DistanceConstraintSettings, PointConstraintSettings, HingeConstraintSettings, ConeConstraintSettings, SliderConstraintSettings,
        SwingTwistConstraintSettings, SixDOFConstraintSettings, PathConstraintSettings, GearConstraintSettings, RackAndPinionConstraintSettings, PulleyConstraintSettings {
    //region Jolt-Pointer-Protected
    protected TwoBodyConstraintSettings(MemoryAddress handle) {
        super(handle);
    }

    public static TwoBodyConstraintSettings at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new TwoBodyConstraintSettings(addr);
    }
    //endregion Jolt-Pointer-Protected

    public TwoBodyConstraint create(MutableBody body1, MutableBody body2) {
        return TwoBodyConstraint.at(JPC_TwoBodyConstraintSettings_Create(handle, body1.address(), body2.address()));
    }
}
