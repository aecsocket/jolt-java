package jolt.physics.collision.shape;

import jolt.Jolt;
import jolt.math.FVec3;
import jolt.physics.collision.PhysicsMaterial;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySession;
import java.util.Collection;

import static jolt.headers.JoltPhysicsC.*;

public final class MeshShapeSettings extends ShapeSettings {
    private static final int VERTEX_SIZE = (int) (C_FLOAT.byteSize() * 3);

    //region Jolt-Pointer
    private MeshShapeSettings(MemoryAddress handle) {
        super(handle);
    }

    public static MeshShapeSettings at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new MeshShapeSettings(addr);
    }
    //endregion Jolt-Pointer

    public static MeshShapeSettings of(FVec3[] vertices, int[] indices, PhysicsMaterial[] materials) {
        if (indices.length % 4 != 0)
            throw new IllegalArgumentException("Length of indices must be a multiple of 4");
        try (var arena = MemorySession.openConfined()) {
            var nVertices = FVec3.ofArray(arena, vertices); // float[] (size % 3 == 0) [ x, y, z ]
            var nIndices = arena.allocateArray(C_INT, indices); // int[] (size % 4 == 0) [ t1, t2, t3, materialIndex ]
            var nMaterials = Jolt.ofArray(arena, materials); // JPC_PhysicsMaterial**
            return new MeshShapeSettings(JPC_MeshShapeSettings_Create(
                    nVertices.address(),
                    vertices.length,
                    VERTEX_SIZE,
                    nIndices.address(),
                    indices.length,
                    nMaterials.address(),
                    materials.length
            ));
        }
    }

    public static MeshShapeSettings of(Collection<FVec3> vertices, Collection<Integer> indices, Collection<PhysicsMaterial> materials) {
        return of(vertices.toArray(new FVec3[0]), Jolt.arrayOf(indices), materials.toArray(new PhysicsMaterial[0]));
    }

    public int getMaxTrianglesPerLeaf() {
        return JPC_MeshShapeSettings_GetMaxTrianglesPerLeaf(handle);
    }

    public void setMaxTrianglesPerLeaf(int maxTrianglesPerLeaf) {
        JPC_MeshShapeSettings_SetMaxTrianglesPerLeaf(handle, maxTrianglesPerLeaf);
    }

    public void sanitize() {
        JPC_MeshShapeSettings_Sanitize(handle);
    }
}
