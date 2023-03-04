package jolt.physics.collision;

import jolt.AddressedJoltNative;
import jolt.Jolt;
import jolt.geometry.AABox;
import jolt.math.DMat44;
import jolt.math.DVec3;
import jolt.math.FMat44;
import jolt.math.FVec3;
import jolt.physics.collision.broadphase.BroadPhaseLayerFilter;
import jolt.physics.collision.shape.Shape;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySession;

import static jolt.headers.JoltPhysicsC.*;

public sealed abstract class NarrowPhaseQuery extends AddressedJoltNative
        permits NarrowPhaseQuery.F, NarrowPhaseQuery.D {
    //region Jolt-Pointer-FD
    private NarrowPhaseQuery(MemoryAddress handle) {
        super(handle);
    }

    public static NarrowPhaseQuery at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : Jolt.doublePrecision() ? new D(addr) : new F(addr);
    }
    //endregion Jolt-Pointer

    public abstract boolean castRay(
            FRayCast ray,
            RayCastResult hit,
            BroadPhaseLayerFilter broadPhaseLayerFilter,
            ObjectLayerFilter objectLayerFilter,
            BodyFilter bodyFilter
    );

    public abstract boolean castRay(
            DRayCast ray,
            RayCastResult hit,
            BroadPhaseLayerFilter broadPhaseLayerFilter,
            ObjectLayerFilter objectLayerFilter,
            BodyFilter bodyFilter
    );

    public abstract void castRay(
            FRayCast ray,
            RayCastSettings settings,
            CastRayCollector collector,
            BroadPhaseLayerFilter broadPhaseLayerFilter,
            ObjectLayerFilter objectLayerFilter,
            BodyFilter bodyFilter,
            ShapeFilter shapeFilter
    );

    public abstract void castRay(
            DRayCast ray,
            RayCastSettings settings,
            CastRayCollector collector,
            BroadPhaseLayerFilter broadPhaseLayerFilter,
            ObjectLayerFilter objectLayerFilter,
            BodyFilter bodyFilter,
            ShapeFilter shapeFilter
    );

    public abstract void collidePoint(
            FVec3 point,
            CollidePointCollector collector,
            BroadPhaseLayerFilter broadPhaseLayerFilter,
            ObjectLayerFilter objectLayerFilter,
            BodyFilter bodyFilter,
            ShapeFilter shapeFilter
    );

    public abstract void collidePoint(
            DVec3 point,
            CollidePointCollector collector,
            BroadPhaseLayerFilter broadPhaseLayerFilter,
            ObjectLayerFilter objectLayerFilter,
            BodyFilter bodyFilter,
            ShapeFilter shapeFilter
    );

    public abstract void collideShape(
            Shape shape,
            FVec3 shapeScale,
            FMat44 comTransform,
            CollideShapeSettings settings,
            FVec3 baseOffset,
            CollideShapeCollector collector,
            BroadPhaseLayerFilter broadPhaseLayerFilter,
            ObjectLayerFilter objectLayerFilter,
            BodyFilter bodyFilter,
            ShapeFilter shapeFilter
    );

    public abstract void collideShape(
            Shape shape,
            FVec3 shapeScale,
            DMat44 comTransform,
            CollideShapeSettings settings,
            DVec3 baseOffset,
            CollideShapeCollector collector,
            BroadPhaseLayerFilter broadPhaseLayerFilter,
            ObjectLayerFilter objectLayerFilter,
            BodyFilter bodyFilter,
            ShapeFilter shapeFilter
    );

    // TODO CastShape

    public void collectTransformedShapes(
            AABox box,
            TransformedShapeCollector collector,
            BroadPhaseLayerFilter broadPhaseLayerFilter,
            ObjectLayerFilter objectLayerFilter,
            BodyFilter bodyFilter,
            ShapeFilter shapeFilter
    ) {
        JPC_NarrowPhaseQuery_CollectTransformedShapes(handle,
                box.address(),
                collector.address(),
                broadPhaseLayerFilter.address(),
                objectLayerFilter.address(),
                bodyFilter.address(),
                shapeFilter.address()
        );
    }

    protected static final class F extends NarrowPhaseQuery {
        private F(MemoryAddress handle) {
            super(handle);
        }

        @Override
        public boolean castRay(
                FRayCast ray,
                RayCastResult hit,
                BroadPhaseLayerFilter broadPhaseLayerFilter,
                ObjectLayerFilter objectLayerFilter,
                BodyFilter bodyFilter
        ) {
            JPC_RayCastResult_SetDefault(hit.address());
            return jolt.headers_f.JoltPhysicsC.JPC_NarrowPhaseQuery_GetCastRay(handle,
                    ray.address(),
                    hit.address(),
                    broadPhaseLayerFilter.address(),
                    objectLayerFilter.address(),
                    bodyFilter.address()
            );
        }

        @Override
        public boolean castRay(
                DRayCast ray,
                RayCastResult hit,
                BroadPhaseLayerFilter broadPhaseLayerFilter,
                ObjectLayerFilter objectLayerFilter,
                BodyFilter bodyFilter
        ) {
            throw Jolt.tryingDoublePrecision();
        }

        @Override
        public void castRay(
                FRayCast ray,
                RayCastSettings settings,
                CastRayCollector collector,
                BroadPhaseLayerFilter broadPhaseLayerFilter,
                ObjectLayerFilter objectLayerFilter,
                BodyFilter bodyFilter,
                ShapeFilter shapeFilter
        ) {
            jolt.headers_f.JoltPhysicsC.JPC_NarrowPhaseQuery_CollectCastRay(handle,
                    ray.address(),
                    settings.address(),
                    collector.address(),
                    broadPhaseLayerFilter.address(),
                    objectLayerFilter.address(),
                    bodyFilter.address(),
                    shapeFilter.address()
            );
        }

        @Override
        public void castRay(
                DRayCast ray,
                RayCastSettings settings,
                CastRayCollector collector,
                BroadPhaseLayerFilter broadPhaseLayerFilter,
                ObjectLayerFilter objectLayerFilter,
                BodyFilter bodyFilter,
                ShapeFilter shapeFilter
        ) {
            throw Jolt.tryingDoublePrecision();
        }

        @Override
        public void collidePoint(
                FVec3 point,
                CollidePointCollector collector,
                BroadPhaseLayerFilter broadPhaseLayerFilter,
                ObjectLayerFilter objectLayerFilter,
                BodyFilter bodyFilter,
                ShapeFilter shapeFilter
        ) {
            jolt.headers_f.JoltPhysicsC.JPC_NarrowPhaseQuery_CollidePoint(handle,
                    point.address(),
                    collector.address(),
                    broadPhaseLayerFilter.address(),
                    objectLayerFilter.address(),
                    bodyFilter.address(),
                    shapeFilter.address()
            );
        }

        @Override
        public void collidePoint(
                DVec3 point,
                CollidePointCollector collector,
                BroadPhaseLayerFilter broadPhaseLayerFilter,
                ObjectLayerFilter objectLayerFilter,
                BodyFilter bodyFilter,
                ShapeFilter shapeFilter
        ) {
            throw Jolt.tryingDoublePrecision();
        }

        @Override
        public void collideShape(
                Shape shape,
                FVec3 shapeScale,
                FMat44 comTransform,
                CollideShapeSettings settings,
                FVec3 baseOffset,
                CollideShapeCollector collector,
                BroadPhaseLayerFilter broadPhaseLayerFilter,
                ObjectLayerFilter objectLayerFilter,
                BodyFilter bodyFilter,
                ShapeFilter shapeFilter
        ) {
            try (var arena = MemorySession.openConfined()) {
                var comRotation = arena.allocateArray(C_FLOAT, new float[9]);
                var comTranslation = arena.allocateArray(C_FLOAT, new float[3]);
                comTransform.write(comRotation, comTranslation);
                jolt.headers_f.JoltPhysicsC.JPC_NarrowPhaseQuery_CollideShape(handle,
                        shape.address(),
                        shapeScale.address(),
                        comRotation.address(),
                        comTranslation.address(),
                        settings.address(),
                        baseOffset.address(),
                        collector.address(),
                        broadPhaseLayerFilter.address(),
                        objectLayerFilter.address(),
                        bodyFilter.address(),
                        shapeFilter.address()
                );
            }
        }

        @Override
        public void collideShape(
                Shape shape,
                FVec3 shapeScale,
                DMat44 comTransform,
                CollideShapeSettings settings,
                DVec3 baseOffset,
                CollideShapeCollector collector,
                BroadPhaseLayerFilter broadPhaseLayerFilter,
                ObjectLayerFilter objectLayerFilter,
                BodyFilter bodyFilter,
                ShapeFilter shapeFilter
        ) {
            throw Jolt.tryingDoublePrecision();
        }
    }

    protected static final class D extends NarrowPhaseQuery {
        private D(MemoryAddress handle) {
            super(handle);
        }

        @Override
        public boolean castRay(
                FRayCast ray,
                RayCastResult hit,
                BroadPhaseLayerFilter broadPhaseLayerFilter,
                ObjectLayerFilter objectLayerFilter,
                BodyFilter bodyFilter
        ) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public boolean castRay(
                DRayCast ray,
                RayCastResult hit,
                BroadPhaseLayerFilter broadPhaseLayerFilter,
                ObjectLayerFilter objectLayerFilter,
                BodyFilter bodyFilter
        ) {
            JPC_RayCastResult_SetDefault(hit.address());
            return jolt.headers_d.JoltPhysicsC.JPC_NarrowPhaseQuery_GetCastRay(handle,
                    ray.address(),
                    hit.address(),
                    broadPhaseLayerFilter.address(),
                    objectLayerFilter.address(),
                    bodyFilter.address()
            );
        }

        @Override
        public void castRay(
                FRayCast ray,
                RayCastSettings settings,
                CastRayCollector collector,
                BroadPhaseLayerFilter broadPhaseLayerFilter,
                ObjectLayerFilter objectLayerFilter,
                BodyFilter bodyFilter,
                ShapeFilter shapeFilter
        ) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public void castRay(
                DRayCast ray,
                RayCastSettings settings,
                CastRayCollector collector,
                BroadPhaseLayerFilter broadPhaseLayerFilter,
                ObjectLayerFilter objectLayerFilter,
                BodyFilter bodyFilter,
                ShapeFilter shapeFilter
        ) {
            jolt.headers_d.JoltPhysicsC.JPC_NarrowPhaseQuery_CollectCastRay(handle,
                    ray.address(),
                    settings.address(),
                    collector.address(),
                    broadPhaseLayerFilter.address(),
                    objectLayerFilter.address(),
                    bodyFilter.address(),
                    shapeFilter.address()
            );
        }

        @Override
        public void collidePoint(
                FVec3 point,
                CollidePointCollector collector,
                BroadPhaseLayerFilter broadPhaseLayerFilter,
                ObjectLayerFilter objectLayerFilter,
                BodyFilter bodyFilter,
                ShapeFilter shapeFilter
        ) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public void collidePoint(
                DVec3 point,
                CollidePointCollector collector,
                BroadPhaseLayerFilter broadPhaseLayerFilter,
                ObjectLayerFilter objectLayerFilter,
                BodyFilter bodyFilter,
                ShapeFilter shapeFilter
        ) {
            jolt.headers_d.JoltPhysicsC.JPC_NarrowPhaseQuery_CollidePoint(handle,
                    point.address(),
                    collector.address(),
                    broadPhaseLayerFilter.address(),
                    objectLayerFilter.address(),
                    bodyFilter.address(),
                    shapeFilter.address()
            );
        }

        @Override
        public void collideShape(
                Shape shape,
                FVec3 shapeScale,
                FMat44 comTransform,
                CollideShapeSettings settings,
                FVec3 baseOffset,
                CollideShapeCollector collector,
                BroadPhaseLayerFilter broadPhaseLayerFilter,
                ObjectLayerFilter objectLayerFilter,
                BodyFilter bodyFilter,
                ShapeFilter shapeFilter
        ) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public void collideShape(
                Shape shape,
                FVec3 shapeScale,
                DMat44 comTransform,
                CollideShapeSettings settings,
                DVec3 baseOffset,
                CollideShapeCollector collector,
                BroadPhaseLayerFilter broadPhaseLayerFilter,
                ObjectLayerFilter objectLayerFilter,
                BodyFilter bodyFilter,
                ShapeFilter shapeFilter
        ) {
            try (var arena = MemorySession.openConfined()) {
                var comRotation = arena.allocateArray(C_FLOAT, new float[9]);
                var comTranslation = arena.allocateArray(C_DOUBLE, new double[3]);
                comTransform.write(comRotation, comTranslation);
                jolt.headers_d.JoltPhysicsC.JPC_NarrowPhaseQuery_CollideShape(handle,
                        shape.address(),
                        shapeScale.address(),
                        comRotation.address(),
                        comTranslation.address(),
                        settings.address(),
                        baseOffset.address(),
                        collector.address(),
                        broadPhaseLayerFilter.address(),
                        objectLayerFilter.address(),
                        bodyFilter.address(),
                        shapeFilter.address()
                );
            }
        }
    }
}
