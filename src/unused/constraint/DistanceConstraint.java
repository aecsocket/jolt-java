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

public final class DistanceConstraint extends TwoBodyConstraint {
    //region Jolt-Pointer
    private DistanceConstraint(MemoryAddress handle) {
        super(handle);
    }

    public static DistanceConstraint at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new DistanceConstraint(addr);
    }
    //endregion Jolt-Pointer

    public void setDistance(float minDistance, float maxDistance) {
        JPC_DistanceConstraint_SetDistance(handle, minDistance, maxDistance);
    }

    public float getMinDistance() {
        return JPC_DistanceConstraint_GetMinDistance(handle);
    }

    public float getMaxDistance() {
        return JPC_DistanceConstraint_GetMaxDistance(handle);
    }

    public void setFrequency(float frequency) {
        JPC_DistanceConstraint_SetFrequency(handle, frequency);
    }

    public float getFrequency() {
        return JPC_DistanceConstraint_GetFrequency(handle);
    }

    public void setDamping(float damping) {
        JPC_DistanceConstraint_SetDamping(handle, damping);
    }

    public float getDamping() {
        return JPC_DistanceConstraint_GetDamping(handle);
    }

    public float getTotalLambdaPosition() {
        return JPC_DistanceConstraint_GetTotalLambdaConstraint(handle);
    }
}
