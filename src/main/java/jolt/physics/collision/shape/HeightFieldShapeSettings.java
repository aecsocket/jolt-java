package jolt.physics.collision.shape;

import jolt.Jolt;
import jolt.math.FVec3;
import jolt.physics.collision.PhysicsMaterial;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySession;
import java.util.Collection;

import static jolt.headers.JoltPhysicsC.*;

public final class HeightFieldShapeSettings extends ShapeSettings {
    //region Jolt-Pointer
    private HeightFieldShapeSettings(MemoryAddress handle) {
        super(handle);
    }

    public static HeightFieldShapeSettings at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new HeightFieldShapeSettings(addr);
    }
    //endregion Jolt-Pointer

    public static HeightFieldShapeSettings of(
            HeightFieldSamples samples,
            int size,
            FVec3 offset,
            FVec3 scale,
            HeightFieldMaterialIndices indices,
            PhysicsMaterial[] materials
    ) {
        try (var arena = MemorySession.openConfined()) {
            var nMaterials = Jolt.ofArray(arena, materials); // JPC_PhysicsMaterial**
            return new HeightFieldShapeSettings(JPC_HeightFieldShapeSettings_Create(
                    samples.address(),
                    size,
                    offset.address(),
                    scale.address(),
                    indices.address(),
                    nMaterials.address(),
                    materials.length
            ));
        }
    }

    public void getOffset(FVec3 out) {
        JPC_HeightFieldShapeSettings_GetOffset(handle, out.address());
    }

    public void setOffset(FVec3 offset) {
        JPC_HeightFieldShapeSettings_SetOffset(handle, offset.address());
    }

    public void getScale(FVec3 out) {
        JPC_HeightFieldShapeSettings_GetScale(handle, out.address());
    }

    public void setScale(FVec3 scale) {
        JPC_HeightFieldShapeSettings_SetScale(handle, scale.address());
    }

    public int getBlockSize() {
        return JPC_HeightFieldShapeSettings_GetBlockSize(handle);
    }

    public void setBlockSize(int blockSize) {
        JPC_HeightFieldShapeSettings_SetBlockSize(handle, blockSize);
    }

    public int getBitsPerSample() {
        return JPC_HeightFieldShapeSettings_GetBitsPerSample(handle);
    }

    public void setBitsPerSample(int bitsPerSample) {
        JPC_HeightFieldShapeSettings_SetBitsPerSample(handle, bitsPerSample);
    }
}
