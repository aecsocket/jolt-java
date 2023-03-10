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

public final class FixedConstraint extends TwoBodyConstraint {
    //region Jolt-Pointer
    private FixedConstraint(MemoryAddress handle) {
        super(handle);
    }

    public static FixedConstraint at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new FixedConstraint(addr);
    }
    //endregion Jolt-Pointer

    public void getTotalLambdaPosition(FVec3 out) {
        JPC_FixedConstraint_GetTotalLambdaPosition(handle, out.address());
    }

    public void getTotalLambdaRotation(FVec3 out) {
        JPC_FixedConstraint_GetTotalLambdaRotation(handle, out.address());
    }
}
