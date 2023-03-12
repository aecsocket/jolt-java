package jolt.physics.collision.shape;

import jolt.AddressedJoltNative;
import jolt.DestroyableJoltNative;
import jolt.Jolt;
import jolt.SegmentedJoltNative;
import jolt.math.FVec3;
import jolt.physics.PhysicsSystem;
import jolt.physics.collision.PhysicsMaterial;
import jolt.physics.collision.RayCastSettings;

import javax.annotation.Nullable;
import java.lang.foreign.*;

import static jolt.headers.JPC_RayCastSettings.allocate;
import static jolt.headers.JPC_RayCastSettings.ofAddress;
import static jolt.headers.JoltPhysicsC.*;

public sealed class ConvexShape extends Shape
        permits SphereShape, BoxShape, TriangleShape, CapsuleShape,
        TaperedCapsuleShape, CylinderShape, ConvexHullShape {
    public enum SupportMode {
        EXCLUDE_CONVEX_RADIUS,
        INCLUDE_CONVEX_RADIUS
    }

    public static final class SupportBuffer extends SegmentedJoltNative {
        //region Jolt-Value
        private SupportBuffer(MemorySegment handle) {
            super(handle);
        }

        public static SupportBuffer at(MemorySegment segment) {
            return new SupportBuffer(segment);
        }

        public static SupportBuffer at(MemorySession alloc, MemoryAddress addr) {
            return addr == MemoryAddress.NULL ? null : new SupportBuffer(ofAddress(addr, alloc));
        }

        public static SupportBuffer of(SegmentAllocator alloc) {
            return new SupportBuffer(allocate(alloc));
        }
        //endregion Jolt-Value
    }

    public static final class Support extends DestroyableJoltNative {
        //region Jolt-Pointer
        private Support(MemoryAddress handle) {
            super(handle);
        }

        public static Support at(MemoryAddress addr) {
            return addr == MemoryAddress.NULL ? null : new Support(addr);
        }
        //endregion Jolt-Pointer

        @Override
        protected void destroyInternal() {
            JPC_ConvexShapeSupport_Destroy(handle);
        }
    }

    //region Jolt-Pointer-Protected
    protected ConvexShape(MemoryAddress handle) {
        super(handle);
    }

    public static ConvexShape at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new ConvexShape(addr);
    }
    //endregion Jolt-Pointer-Protected

    public Support getSupportFunction(SupportMode mode, SupportBuffer buffer, FVec3 scale) {
        return Support.at(JPC_ConvexShape_GetSupportFunction(handle, (byte) mode.ordinal(), buffer.address(), scale.address()));
    }

    public void setMaterial(@Nullable PhysicsMaterial material) {
        JPC_ConvexShape_SetMaterial(handle, Jolt.ptr(material));
    }

    public @Nullable PhysicsMaterial getMaterial() {
        return PhysicsMaterial.at(JPC_ConvexShape_GetMaterial(handle));
    }

    public void setDensity(float density) {
        JPC_ConvexShape_SetDensity(handle, density);
    }

    public float getDensity() {
        return JPC_ConvexShape_GetDensity(handle);
    }
}
