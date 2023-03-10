#include "JoltPhysicsC.h"

#include <assert.h>
#include <stddef.h>

#include <Jolt/Jolt.h>
#include <Jolt/RegisterTypes.h>
#include <Jolt/Core/Factory.h>
#include <Jolt/Core/TempAllocator.h>
#include <Jolt/Core/Memory.h>
#include <Jolt/Core/JobSystemThreadPool.h>
#include <Jolt/Geometry/OrientedBox.h>
#include <Jolt/Physics/PhysicsSettings.h>
#include <Jolt/Physics/PhysicsSystem.h>
#include <Jolt/Physics/Collision/BroadPhase/BroadPhaseQuery.h>
#include <Jolt/Physics/Collision/NarrowPhaseQuery.h>
#include <Jolt/Physics/Collision/CollideShape.h>
#include <Jolt/Physics/Collision/RayCast.h>
#include <Jolt/Physics/Collision/ShapeCast.h>
#include <Jolt/Physics/Collision/AABoxCast.h>
#include <Jolt/Physics/Collision/CastResult.h>
#include <Jolt/Physics/Collision/PhysicsMaterial.h>
#include <Jolt/Physics/Collision/Shape/BoxShape.h>
#include <Jolt/Physics/Collision/Shape/SphereShape.h>
#include <Jolt/Physics/Collision/Shape/TriangleShape.h>
#include <Jolt/Physics/Collision/Shape/CapsuleShape.h>
#include <Jolt/Physics/Collision/Shape/TaperedCapsuleShape.h>
#include <Jolt/Physics/Collision/Shape/CylinderShape.h>
#include <Jolt/Physics/Collision/Shape/ConvexHullShape.h>
#include <Jolt/Physics/Collision/Shape/HeightFieldShape.h>
#include <Jolt/Physics/Collision/Shape/MeshShape.h>
#include <Jolt/Physics/Collision/Shape/StaticCompoundShape.h>
#include <Jolt/Physics/Collision/Shape/MutableCompoundShape.h>
#include <Jolt/Physics/Collision/Shape/DecoratedShape.h>
#include <Jolt/Physics/Collision/Shape/ScaledShape.h>
#include <Jolt/Physics/Collision/Shape/RotatedTranslatedShape.h>
#include <Jolt/Physics/Collision/Shape/OffsetCenterOfMassShape.h>
#include <Jolt/Physics/Constraints/Constraint.h>
#include <Jolt/Physics/Constraints/TwoBodyConstraint.h>
#include <Jolt/Physics/Constraints/FixedConstraint.h>
#include <Jolt/Physics/Constraints/DistanceConstraint.h>
#include <Jolt/Physics/Constraints/PointConstraint.h>
#include <Jolt/Physics/Constraints/HingeConstraint.h>
#include <Jolt/Physics/Constraints/ConeConstraint.h>
#include <Jolt/Physics/Constraints/SliderConstraint.h>
#include <Jolt/Physics/Constraints/SwingTwistConstraint.h>
#include <Jolt/Physics/Constraints/SixDOFConstraint.h>
#include <Jolt/Physics/Body/BodyCreationSettings.h>
#include <Jolt/Physics/Body/BodyActivationListener.h>
#include <Jolt/Physics/Body/BodyLock.h>

JPH_SUPPRESS_WARNINGS

#if defined(JPH_EXTERNAL_PROFILE) || defined(JPH_PROFILE_ENABLED)
#error Currently JoltPhysicsC does not support profiling. Please undef JPH_EXTERNAL_PROFILE and JPH_PROFILE_ENABLED.
#endif

#if defined(JPH_TRACK_BROADPHASE_STATS)
#error JPH_TRACK_BROADPHASE_STATS is not supported.
#endif

#define ENSURE_TYPE(o, t) \
    assert(o != nullptr); \
    assert(reinterpret_cast<const JPH::SerializableObject *>(o)->CastTo(JPH_RTTI(t)) != nullptr)

#define FN(name) static auto name

FN(toJpc)(JPH::BodyID in) { return in.GetIndexAndSequenceNumber(); }
FN(toJph)(JPC_BodyID in) { return JPH::BodyID(in); }

FN(subShapeIDJph)(JPC_SubShapeID in) { auto r = JPH::SubShapeID(); r.SetValue(in); return r; }
FN(subShapeIDJpc)(JPH::SubShapeID in) { return in.GetValue(); }

FN(toJpc)(const JPH::BodyID *in) { assert(in); return reinterpret_cast<const JPC_BodyID *>(in); }
FN(toJph)(const JPC_BodyID *in) { assert(in); return reinterpret_cast<const JPH::BodyID *>(in); }
FN(toJpc)(JPH::BodyID *in) { assert(in); return reinterpret_cast<JPC_BodyID *>(in); }
FN(toJph)(JPC_BodyID *in) { assert(in); return reinterpret_cast<JPH::BodyID *>(in); }

FN(toJpc)(const JPH::Body *in) { assert(in); return reinterpret_cast<const JPC_Body *>(in); }
FN(toJph)(const JPC_Body *in) { assert(in); return reinterpret_cast<const JPH::Body *>(in); }
FN(toJpc)(JPH::Body *in) { assert(in); return reinterpret_cast<JPC_Body *>(in); }
FN(toJph)(JPC_Body *in) { assert(in); return reinterpret_cast<JPH::Body *>(in); }

FN(toJph)(const JPC_PhysicsMaterial *in) { return reinterpret_cast<const JPH::PhysicsMaterial *>(in); }
FN(toJpc)(const JPH::PhysicsMaterial *in) { return reinterpret_cast<const JPC_PhysicsMaterial *>(in); }

FN(toJph)(JPC_TempAllocator *in) { return reinterpret_cast<JPH::TempAllocator *>(in); }
FN(toJpc)(JPH::TempAllocator *in) { return reinterpret_cast<JPC_TempAllocator *>(in); }

FN(toJph)(const JPC_ShapeSettings *in) {
    ENSURE_TYPE(in, JPH::ShapeSettings);
    return reinterpret_cast<const JPH::ShapeSettings *>(in);
}
FN(toJph)(JPC_ShapeSettings *in) {
    ENSURE_TYPE(in, JPH::ShapeSettings);
    return reinterpret_cast<JPH::ShapeSettings *>(in);
}
FN(toJpc)(const JPH::ShapeSettings *in) { assert(in); return reinterpret_cast<const JPC_ShapeSettings *>(in); }
FN(toJpc)(JPH::ShapeSettings *in) { assert(in); return reinterpret_cast<JPC_ShapeSettings *>(in); }

FN(toJph)(const JPC_BoxShapeSettings *in) {
    ENSURE_TYPE(in, JPH::BoxShapeSettings);
    return reinterpret_cast<const JPH::BoxShapeSettings *>(in);
}
FN(toJph)(JPC_BoxShapeSettings *in) {
    ENSURE_TYPE(in, JPH::BoxShapeSettings);
    return reinterpret_cast<JPH::BoxShapeSettings *>(in);
}
FN(toJpc)(JPH::BoxShapeSettings *in) { assert(in); return reinterpret_cast<JPC_BoxShapeSettings *>(in); }

FN(toJph)(const JPC_SphereShapeSettings *in) {
    ENSURE_TYPE(in, JPH::SphereShapeSettings);
    return reinterpret_cast<const JPH::SphereShapeSettings *>(in);
}
FN(toJph)(JPC_SphereShapeSettings *in) {
    ENSURE_TYPE(in, JPH::SphereShapeSettings);
    return reinterpret_cast<JPH::SphereShapeSettings *>(in);
}
FN(toJpc)(JPH::SphereShapeSettings *in) { assert(in); return reinterpret_cast<JPC_SphereShapeSettings *>(in); }

FN(toJph)(const JPC_TriangleShapeSettings *in) {
    ENSURE_TYPE(in, JPH::TriangleShapeSettings);
    return reinterpret_cast<const JPH::TriangleShapeSettings *>(in);
}
FN(toJph)(JPC_TriangleShapeSettings *in) {
    ENSURE_TYPE(in, JPH::TriangleShapeSettings);
    return reinterpret_cast<JPH::TriangleShapeSettings *>(in);
}
FN(toJpc)(JPH::TriangleShapeSettings *in) { assert(in); return reinterpret_cast<JPC_TriangleShapeSettings *>(in); }

FN(toJph)(const JPC_CapsuleShapeSettings *in) {
    ENSURE_TYPE(in, JPH::CapsuleShapeSettings);
    return reinterpret_cast<const JPH::CapsuleShapeSettings *>(in);
}
FN(toJph)(JPC_CapsuleShapeSettings *in) {
    ENSURE_TYPE(in, JPH::CapsuleShapeSettings);
    return reinterpret_cast<JPH::CapsuleShapeSettings *>(in);
}
FN(toJpc)(JPH::CapsuleShapeSettings *in) { assert(in); return reinterpret_cast<JPC_CapsuleShapeSettings *>(in); }

FN(toJph)(const JPC_TaperedCapsuleShapeSettings *in) {
    ENSURE_TYPE(in, JPH::TaperedCapsuleShapeSettings);
    return reinterpret_cast<const JPH::TaperedCapsuleShapeSettings *>(in);
}
FN(toJph)(JPC_TaperedCapsuleShapeSettings *in) {
    ENSURE_TYPE(in, JPH::TaperedCapsuleShapeSettings);
    return reinterpret_cast<JPH::TaperedCapsuleShapeSettings *>(in);
}
FN(toJpc)(JPH::TaperedCapsuleShapeSettings *in) {
    assert(in); return reinterpret_cast<JPC_TaperedCapsuleShapeSettings *>(in);
}

FN(toJph)(const JPC_CylinderShapeSettings *in) {
    ENSURE_TYPE(in, JPH::CylinderShapeSettings);
    return reinterpret_cast<const JPH::CylinderShapeSettings *>(in);
}
FN(toJph)(JPC_CylinderShapeSettings *in) {
    ENSURE_TYPE(in, JPH::CylinderShapeSettings);
    return reinterpret_cast<JPH::CylinderShapeSettings *>(in);
}
FN(toJpc)(JPH::CylinderShapeSettings *in) { assert(in); return reinterpret_cast<JPC_CylinderShapeSettings *>(in); }

FN(toJph)(const JPC_ConvexHullShapeSettings *in) {
    ENSURE_TYPE(in, JPH::ConvexHullShapeSettings);
    return reinterpret_cast<const JPH::ConvexHullShapeSettings *>(in);
}
FN(toJph)(JPC_ConvexHullShapeSettings *in) {
    ENSURE_TYPE(in, JPH::ConvexHullShapeSettings);
    return reinterpret_cast<JPH::ConvexHullShapeSettings *>(in);
}
FN(toJpc)(JPH::ConvexHullShapeSettings *in) {
    assert(in);
    return reinterpret_cast<JPC_ConvexHullShapeSettings *>(in);
}

FN(toJph)(const JPC_HeightFieldShapeSettings *in) {
    ENSURE_TYPE(in, JPH::HeightFieldShapeSettings);
    return reinterpret_cast<const JPH::HeightFieldShapeSettings *>(in);
}
FN(toJph)(JPC_HeightFieldShapeSettings *in) {
    ENSURE_TYPE(in, JPH::HeightFieldShapeSettings);
    return reinterpret_cast<JPH::HeightFieldShapeSettings *>(in);
}
FN(toJpc)(JPH::HeightFieldShapeSettings *in) {
    assert(in);
    return reinterpret_cast<JPC_HeightFieldShapeSettings *>(in);
}

FN(toJph)(const JPC_MeshShapeSettings *in) {
    ENSURE_TYPE(in, JPH::MeshShapeSettings);
    return reinterpret_cast<const JPH::MeshShapeSettings *>(in);
}
FN(toJph)(JPC_MeshShapeSettings *in) {
    ENSURE_TYPE(in, JPH::MeshShapeSettings);
    return reinterpret_cast<JPH::MeshShapeSettings *>(in);
}
FN(toJpc)(JPH::MeshShapeSettings *in) {
    assert(in);
    return reinterpret_cast<JPC_MeshShapeSettings *>(in);
}

FN(toJpc)(const JPH::CompoundShapeSettings *in) { assert(in); return reinterpret_cast<const JPC_CompoundShapeSettings *>(in); }
FN(toJph)(const JPC_CompoundShapeSettings *in) { assert(in); return reinterpret_cast<const JPH::CompoundShapeSettings *>(in); }
FN(toJpc)(JPH::CompoundShapeSettings *in) { assert(in); return reinterpret_cast<JPC_CompoundShapeSettings *>(in); }
FN(toJph)(JPC_CompoundShapeSettings *in) { assert(in); return reinterpret_cast<JPH::CompoundShapeSettings *>(in); }

FN(toJpc)(const JPH::StaticCompoundShapeSettings *in) { assert(in); return reinterpret_cast<const JPC_StaticCompoundShapeSettings *>(in); }
FN(toJph)(const JPC_StaticCompoundShapeSettings *in) { assert(in); return reinterpret_cast<const JPH::StaticCompoundShapeSettings *>(in); }
FN(toJpc)(JPH::StaticCompoundShapeSettings *in) { assert(in); return reinterpret_cast<JPC_StaticCompoundShapeSettings *>(in); }
FN(toJph)(JPC_StaticCompoundShapeSettings *in) { assert(in); return reinterpret_cast<JPH::StaticCompoundShapeSettings *>(in); }

FN(toJpc)(const JPH::MutableCompoundShapeSettings *in) { assert(in); return reinterpret_cast<const JPC_MutableCompoundShapeSettings *>(in); }
FN(toJph)(const JPC_MutableCompoundShapeSettings *in) { assert(in); return reinterpret_cast<const JPH::MutableCompoundShapeSettings *>(in); }
FN(toJpc)(JPH::MutableCompoundShapeSettings *in) { assert(in); return reinterpret_cast<JPC_MutableCompoundShapeSettings *>(in); }
FN(toJph)(JPC_MutableCompoundShapeSettings *in) { assert(in); return reinterpret_cast<JPH::MutableCompoundShapeSettings *>(in); }

FN(toJpc)(const JPH::DecoratedShapeSettings *in) { assert(in); return reinterpret_cast<const JPC_DecoratedShapeSettings *>(in); }
FN(toJph)(const JPC_DecoratedShapeSettings *in) { assert(in); return reinterpret_cast<const JPH::DecoratedShapeSettings *>(in); }
FN(toJpc)(JPH::DecoratedShapeSettings *in) { assert(in); return reinterpret_cast<JPC_DecoratedShapeSettings *>(in); }
FN(toJph)(JPC_DecoratedShapeSettings *in) { assert(in); return reinterpret_cast<JPH::DecoratedShapeSettings *>(in); }

FN(toJpc)(const JPH::ScaledShapeSettings *in) { assert(in); return reinterpret_cast<const JPC_ScaledShapeSettings *>(in); }
FN(toJph)(const JPC_ScaledShapeSettings *in) { assert(in); return reinterpret_cast<const JPH::ScaledShapeSettings *>(in); }
FN(toJpc)(JPH::ScaledShapeSettings *in) { assert(in); return reinterpret_cast<JPC_ScaledShapeSettings *>(in); }
FN(toJph)(JPC_ScaledShapeSettings *in) { assert(in); return reinterpret_cast<JPH::ScaledShapeSettings *>(in); }

FN(toJpc)(const JPH::RotatedTranslatedShapeSettings *in) { assert(in); return reinterpret_cast<const JPC_RotatedTranslatedShapeSettings *>(in); }
FN(toJph)(const JPC_RotatedTranslatedShapeSettings *in) { assert(in); return reinterpret_cast<const JPH::RotatedTranslatedShapeSettings *>(in); }
FN(toJpc)(JPH::RotatedTranslatedShapeSettings *in) { assert(in); return reinterpret_cast<JPC_RotatedTranslatedShapeSettings *>(in); }
FN(toJph)(JPC_RotatedTranslatedShapeSettings *in) { assert(in); return reinterpret_cast<JPH::RotatedTranslatedShapeSettings *>(in); }

FN(toJpc)(const JPH::OffsetCenterOfMassShapeSettings *in) { assert(in); return reinterpret_cast<const JPC_OffsetCenterOfMassShapeSettings *>(in); }
FN(toJph)(const JPC_OffsetCenterOfMassShapeSettings *in) { assert(in); return reinterpret_cast<const JPH::OffsetCenterOfMassShapeSettings *>(in); }
FN(toJpc)(JPH::OffsetCenterOfMassShapeSettings *in) { assert(in); return reinterpret_cast<JPC_OffsetCenterOfMassShapeSettings *>(in); }
FN(toJph)(JPC_OffsetCenterOfMassShapeSettings *in) { assert(in); return reinterpret_cast<JPH::OffsetCenterOfMassShapeSettings *>(in); }

FN(toJph)(const JPC_ConvexShapeSettings *in) {
    ENSURE_TYPE(in, JPH::ConvexShapeSettings);
    return reinterpret_cast<const JPH::ConvexShapeSettings *>(in);
}
FN(toJph)(JPC_ConvexShapeSettings *in) {
    ENSURE_TYPE(in, JPH::ConvexShapeSettings);
    return reinterpret_cast<JPH::ConvexShapeSettings *>(in);
}

FN(toJph)(const JPC_CollisionGroup *in) { assert(in); return reinterpret_cast<const JPH::CollisionGroup *>(in); }
FN(toJpc)(const JPH::CollisionGroup *in) { assert(in); return reinterpret_cast<const JPC_CollisionGroup *>(in); }
FN(toJpc)(JPH::CollisionGroup *in) { assert(in); return reinterpret_cast<JPC_CollisionGroup *>(in); }

FN(toJph)(const JPC_BodyLockInterface *in) {
    assert(in); return reinterpret_cast<const JPH::BodyLockInterface *>(in);
}
FN(toJpc)(const JPH::BodyLockInterface *in) {
    assert(in); return reinterpret_cast<const JPC_BodyLockInterface *>(in);
}

FN(toJph)(const JPC_PhysicsSystem *in) { assert(in); return reinterpret_cast<const JPH::PhysicsSystem *>(in); }
FN(toJph)(JPC_PhysicsSystem *in) { assert(in); return reinterpret_cast<JPH::PhysicsSystem *>(in); }
FN(toJpc)(JPH::PhysicsSystem *in) { assert(in); return reinterpret_cast<JPC_PhysicsSystem *>(in); }

FN(toJpc)(const JPH::Shape *in) { assert(in); return reinterpret_cast<const JPC_Shape *>(in); }
FN(toJph)(const JPC_Shape *in) { assert(in); return reinterpret_cast<const JPH::Shape *>(in); }
FN(toJpc)(JPH::Shape *in) { assert(in); return reinterpret_cast<JPC_Shape *>(in); }
FN(toJph)(JPC_Shape *in) { assert(in); return reinterpret_cast<JPH::Shape *>(in); }

FN(toJpc)(const JPH::ConvexShape *in) { assert(in); return reinterpret_cast<const JPC_ConvexShape *>(in); }
FN(toJph)(const JPC_ConvexShape *in) { assert(in); return reinterpret_cast<const JPH::ConvexShape *>(in); }
FN(toJpc)(JPH::ConvexShape *in) { assert(in); return reinterpret_cast<JPC_ConvexShape *>(in); }
FN(toJph)(JPC_ConvexShape *in) { assert(in); return reinterpret_cast<JPH::ConvexShape *>(in); }

FN(toJpc)(const JPH::BodyInterface *in) { assert(in); return reinterpret_cast<const JPC_BodyInterface *>(in); }
FN(toJph)(const JPC_BodyInterface *in) { assert(in); return reinterpret_cast<const JPH::BodyInterface *>(in); }
FN(toJpc)(JPH::BodyInterface *in) { assert(in); return reinterpret_cast<JPC_BodyInterface *>(in); }
FN(toJph)(JPC_BodyInterface *in) { assert(in); return reinterpret_cast<JPH::BodyInterface *>(in); }

FN(toJpc)(const JPH::MassProperties *in) { assert(in); return reinterpret_cast<const JPC_MassProperties *>(in); }
FN(toJph)(const JPC_MassProperties *in) { assert(in); return reinterpret_cast<const JPH::MassProperties *>(in); }
FN(toJpc)(JPH::MassProperties *in) { assert(in); return reinterpret_cast<JPC_MassProperties *>(in); }
FN(toJph)(JPC_MassProperties *in) { assert(in); return reinterpret_cast<JPH::MassProperties *>(in); }

FN(toJph)(JPC_BodyLockRead *in) { assert(in); return reinterpret_cast<const JPH::BodyLockRead *>(in); }
FN(toJph)(JPC_BodyLockWrite *in) { assert(in); return reinterpret_cast<const JPH::BodyLockWrite *>(in); }

FN(toJph)(const JPC_SphereShape *in) { assert(in); return reinterpret_cast<const JPH::SphereShape *>(in); }
FN(toJpc)(const JPH::SphereShape *in) { assert(in); return reinterpret_cast<const JPC_SphereShape *>(in); }
FN(toJph)(JPC_SphereShape *in) { assert(in); return reinterpret_cast<JPH::SphereShape *>(in); }
FN(toJpc)(JPH::SphereShape *in) { assert(in); return reinterpret_cast<JPC_SphereShape *>(in); }

FN(toJph)(const JPC_BoxShape *in) { assert(in); return reinterpret_cast<const JPH::BoxShape *>(in); }
FN(toJpc)(const JPH::BoxShape *in) { assert(in); return reinterpret_cast<const JPC_BoxShape *>(in); }
FN(toJph)(JPC_BoxShape *in) { assert(in); return reinterpret_cast<JPH::BoxShape *>(in); }
FN(toJpc)(JPH::BoxShape *in) { assert(in); return reinterpret_cast<JPC_BoxShape *>(in); }

FN(toJph)(const JPC_TriangleShape *in) { assert(in); return reinterpret_cast<const JPH::TriangleShape *>(in); }
FN(toJpc)(const JPH::TriangleShape *in) { assert(in); return reinterpret_cast<const JPC_TriangleShape *>(in); }
FN(toJph)(JPC_TriangleShape *in) { assert(in); return reinterpret_cast<JPH::TriangleShape *>(in); }
FN(toJpc)(JPH::TriangleShape *in) { assert(in); return reinterpret_cast<JPC_TriangleShape *>(in); }

FN(toJph)(const JPC_CapsuleShape *in) { assert(in); return reinterpret_cast<const JPH::CapsuleShape *>(in); }
FN(toJpc)(const JPH::CapsuleShape *in) { assert(in); return reinterpret_cast<const JPC_CapsuleShape *>(in); }
FN(toJph)(JPC_CapsuleShape *in) { assert(in); return reinterpret_cast<JPH::CapsuleShape *>(in); }
FN(toJpc)(JPH::CapsuleShape *in) { assert(in); return reinterpret_cast<JPC_CapsuleShape *>(in); }

FN(toJph)(const JPC_TaperedCapsuleShape *in) { assert(in); return reinterpret_cast<const JPH::TaperedCapsuleShape *>(in); }
FN(toJpc)(const JPH::TaperedCapsuleShape *in) { assert(in); return reinterpret_cast<const JPC_TaperedCapsuleShape *>(in); }
FN(toJph)(JPC_TaperedCapsuleShape *in) { assert(in); return reinterpret_cast<JPH::TaperedCapsuleShape *>(in); }
FN(toJpc)(JPH::TaperedCapsuleShape *in) { assert(in); return reinterpret_cast<JPC_TaperedCapsuleShape *>(in); }

FN(toJph)(const JPC_CylinderShape *in) { assert(in); return reinterpret_cast<const JPH::CylinderShape *>(in); }
FN(toJpc)(const JPH::CylinderShape *in) { assert(in); return reinterpret_cast<const JPC_CylinderShape *>(in); }
FN(toJph)(JPC_CylinderShape *in) { assert(in); return reinterpret_cast<JPH::CylinderShape *>(in); }
FN(toJpc)(JPH::CylinderShape *in) { assert(in); return reinterpret_cast<JPC_CylinderShape *>(in); }

FN(toJph)(const JPC_ConvexHullShape *in) { assert(in); return reinterpret_cast<const JPH::ConvexHullShape *>(in); }
FN(toJpc)(const JPH::ConvexHullShape *in) { assert(in); return reinterpret_cast<const JPC_ConvexHullShape *>(in); }
FN(toJph)(JPC_ConvexHullShape *in) { assert(in); return reinterpret_cast<JPH::ConvexHullShape *>(in); }
FN(toJpc)(JPH::ConvexHullShape *in) { assert(in); return reinterpret_cast<JPC_ConvexHullShape *>(in); }

FN(toJpc)(const JPH::DecoratedShape *in) { assert(in); return reinterpret_cast<const JPC_DecoratedShape *>(in); }
FN(toJph)(const JPC_DecoratedShape *in) { assert(in); return reinterpret_cast<const JPH::DecoratedShape *>(in); }
FN(toJpc)(JPH::DecoratedShape *in) { assert(in); return reinterpret_cast<JPC_DecoratedShape *>(in); }
FN(toJph)(JPC_DecoratedShape *in) { assert(in); return reinterpret_cast<JPH::DecoratedShape *>(in); }

FN(toJpc)(const JPH::ScaledShape *in) { assert(in); return reinterpret_cast<const JPC_ScaledShape *>(in); }
FN(toJph)(const JPC_ScaledShape *in) { assert(in); return reinterpret_cast<const JPH::ScaledShape *>(in); }
FN(toJpc)(JPH::ScaledShape *in) { assert(in); return reinterpret_cast<JPC_ScaledShape *>(in); }
FN(toJph)(JPC_ScaledShape *in) { assert(in); return reinterpret_cast<JPH::ScaledShape *>(in); }

FN(toJpc)(const JPH::RotatedTranslatedShape *in) { assert(in); return reinterpret_cast<const JPC_RotatedTranslatedShape *>(in); }
FN(toJph)(const JPC_RotatedTranslatedShape *in) { assert(in); return reinterpret_cast<const JPH::RotatedTranslatedShape *>(in); }
FN(toJpc)(JPH::RotatedTranslatedShape *in) { assert(in); return reinterpret_cast<JPC_RotatedTranslatedShape *>(in); }
FN(toJph)(JPC_RotatedTranslatedShape *in) { assert(in); return reinterpret_cast<JPH::RotatedTranslatedShape *>(in); }

FN(toJpc)(const JPH::OffsetCenterOfMassShape *in) { assert(in); return reinterpret_cast<const JPC_OffsetCenterOfMassShape *>(in); }
FN(toJph)(const JPC_OffsetCenterOfMassShape *in) { assert(in); return reinterpret_cast<const JPH::OffsetCenterOfMassShape *>(in); }
FN(toJpc)(JPH::OffsetCenterOfMassShape *in) { assert(in); return reinterpret_cast<JPC_OffsetCenterOfMassShape *>(in); }
FN(toJph)(JPC_OffsetCenterOfMassShape *in) { assert(in); return reinterpret_cast<JPH::OffsetCenterOfMassShape *>(in); }


FN(toJph)(const JPC_Constraint *in) { assert(in); return reinterpret_cast<const JPH::Constraint *>(in); }
FN(toJpc)(const JPH::Constraint *in) { assert(in); return reinterpret_cast<const JPC_Constraint *>(in); }
FN(toJph)(JPC_Constraint *in) { assert(in); return reinterpret_cast<JPH::Constraint *>(in); }
FN(toJpc)(JPH::Constraint *in) { assert(in); return reinterpret_cast<JPC_Constraint *>(in); }

FN(toJph)(const JPC_TwoBodyConstraint *in) { assert(in); return reinterpret_cast<const JPH::TwoBodyConstraint *>(in); }
FN(toJpc)(const JPH::TwoBodyConstraint *in) { assert(in); return reinterpret_cast<const JPC_TwoBodyConstraint *>(in); }
FN(toJph)(JPC_TwoBodyConstraint *in) { assert(in); return reinterpret_cast<JPH::TwoBodyConstraint *>(in); }
FN(toJpc)(JPH::TwoBodyConstraint *in) { assert(in); return reinterpret_cast<JPC_TwoBodyConstraint *>(in); }

FN(toJph)(const JPC_FixedConstraint *in) { assert(in); return reinterpret_cast<const JPH::FixedConstraint *>(in); }
FN(toJpc)(const JPH::FixedConstraint *in) { assert(in); return reinterpret_cast<const JPC_FixedConstraint *>(in); }
FN(toJph)(JPC_FixedConstraint *in) { assert(in); return reinterpret_cast<JPH::FixedConstraint *>(in); }
FN(toJpc)(JPH::FixedConstraint *in) { assert(in); return reinterpret_cast<JPC_FixedConstraint *>(in); }

FN(toJph)(const JPC_DistanceConstraint *in) { assert(in); return reinterpret_cast<const JPH::DistanceConstraint *>(in); }
FN(toJpc)(const JPH::DistanceConstraint *in) { assert(in); return reinterpret_cast<const JPC_DistanceConstraint *>(in); }
FN(toJph)(JPC_DistanceConstraint *in) { assert(in); return reinterpret_cast<JPH::DistanceConstraint *>(in); }
FN(toJpc)(JPH::DistanceConstraint *in) { assert(in); return reinterpret_cast<JPC_DistanceConstraint *>(in); }

FN(toJph)(const JPC_PointConstraint *in) { assert(in); return reinterpret_cast<const JPH::PointConstraint *>(in); }
FN(toJpc)(const JPH::PointConstraint *in) { assert(in); return reinterpret_cast<const JPC_PointConstraint *>(in); }
FN(toJph)(JPC_PointConstraint *in) { assert(in); return reinterpret_cast<JPH::PointConstraint *>(in); }
FN(toJpc)(JPH::PointConstraint *in) { assert(in); return reinterpret_cast<JPC_PointConstraint *>(in); }

FN(toJph)(const JPC_HingeConstraint *in) { assert(in); return reinterpret_cast<const JPH::HingeConstraint *>(in); }
FN(toJpc)(const JPH::HingeConstraint *in) { assert(in); return reinterpret_cast<const JPC_HingeConstraint *>(in); }
FN(toJph)(JPC_HingeConstraint *in) { assert(in); return reinterpret_cast<JPH::HingeConstraint *>(in); }
FN(toJpc)(JPH::HingeConstraint *in) { assert(in); return reinterpret_cast<JPC_HingeConstraint *>(in); }

FN(toJph)(const JPC_ConeConstraint *in) { assert(in); return reinterpret_cast<const JPH::ConeConstraint *>(in); }
FN(toJpc)(const JPH::ConeConstraint *in) { assert(in); return reinterpret_cast<const JPC_ConeConstraint *>(in); }
FN(toJph)(JPC_ConeConstraint *in) { assert(in); return reinterpret_cast<JPH::ConeConstraint *>(in); }
FN(toJpc)(JPH::ConeConstraint *in) { assert(in); return reinterpret_cast<JPC_ConeConstraint *>(in); }

FN(toJph)(const JPC_SliderConstraint *in) { assert(in); return reinterpret_cast<const JPH::SliderConstraint *>(in); }
FN(toJpc)(const JPH::SliderConstraint *in) { assert(in); return reinterpret_cast<const JPC_SliderConstraint *>(in); }
FN(toJph)(JPC_SliderConstraint *in) { assert(in); return reinterpret_cast<JPH::SliderConstraint *>(in); }
FN(toJpc)(JPH::SliderConstraint *in) { assert(in); return reinterpret_cast<JPC_SliderConstraint *>(in); }

FN(toJph)(const JPC_SwingTwistConstraint *in) { assert(in); return reinterpret_cast<const JPH::SwingTwistConstraint *>(in); }
FN(toJpc)(const JPH::SwingTwistConstraint *in) { assert(in); return reinterpret_cast<const JPC_SwingTwistConstraint *>(in); }
FN(toJph)(JPC_SwingTwistConstraint *in) { assert(in); return reinterpret_cast<JPH::SwingTwistConstraint *>(in); }
FN(toJpc)(JPH::SwingTwistConstraint *in) { assert(in); return reinterpret_cast<JPC_SwingTwistConstraint *>(in); }

FN(toJph)(const JPC_SixDOFConstraint *in) { assert(in); return reinterpret_cast<const JPH::SixDOFConstraint *>(in); }
FN(toJpc)(const JPH::SixDOFConstraint *in) { assert(in); return reinterpret_cast<const JPC_SixDOFConstraint *>(in); }
FN(toJph)(JPC_SixDOFConstraint *in) { assert(in); return reinterpret_cast<JPH::SixDOFConstraint *>(in); }
FN(toJpc)(JPH::SixDOFConstraint *in) { assert(in); return reinterpret_cast<JPC_SixDOFConstraint *>(in); }



FN(toJph)(const JPC_ConstraintSettings *in) { assert(in); return reinterpret_cast<const JPH::ConstraintSettings *>(in); }
FN(toJpc)(const JPH::ConstraintSettings *in) { assert(in); return reinterpret_cast<const JPC_ConstraintSettings *>(in); }
FN(toJph)(JPC_ConstraintSettings *in) { assert(in); return reinterpret_cast<JPH::ConstraintSettings *>(in); }
FN(toJpc)(JPH::ConstraintSettings *in) { assert(in); return reinterpret_cast<JPC_ConstraintSettings *>(in); }

FN(toJph)(const JPC_TwoBodyConstraintSettings *in) { assert(in); return reinterpret_cast<const JPH::TwoBodyConstraintSettings *>(in); }
FN(toJpc)(const JPH::TwoBodyConstraintSettings *in) { assert(in); return reinterpret_cast<const JPC_TwoBodyConstraintSettings *>(in); }
FN(toJph)(JPC_TwoBodyConstraintSettings *in) { assert(in); return reinterpret_cast<JPH::TwoBodyConstraintSettings *>(in); }
FN(toJpc)(JPH::TwoBodyConstraintSettings *in) { assert(in); return reinterpret_cast<JPC_TwoBodyConstraintSettings *>(in); }

FN(toJph)(const JPC_FixedConstraintSettings *in) { assert(in); return reinterpret_cast<const JPH::FixedConstraintSettings *>(in); }
FN(toJpc)(const JPH::FixedConstraintSettings *in) { assert(in); return reinterpret_cast<const JPC_FixedConstraintSettings *>(in); }
FN(toJph)(JPC_FixedConstraintSettings *in) { assert(in); return reinterpret_cast<JPH::FixedConstraintSettings *>(in); }
FN(toJpc)(JPH::FixedConstraintSettings *in) { assert(in); return reinterpret_cast<JPC_FixedConstraintSettings *>(in); }

FN(toJph)(const JPC_DistanceConstraintSettings *in) { assert(in); return reinterpret_cast<const JPH::DistanceConstraintSettings *>(in); }
FN(toJpc)(const JPH::DistanceConstraintSettings *in) { assert(in); return reinterpret_cast<const JPC_DistanceConstraintSettings *>(in); }
FN(toJph)(JPC_DistanceConstraintSettings *in) { assert(in); return reinterpret_cast<JPH::DistanceConstraintSettings *>(in); }
FN(toJpc)(JPH::DistanceConstraintSettings *in) { assert(in); return reinterpret_cast<JPC_DistanceConstraintSettings *>(in); }

FN(toJph)(const JPC_PointConstraintSettings *in) { assert(in); return reinterpret_cast<const JPH::PointConstraintSettings *>(in); }
FN(toJpc)(const JPH::PointConstraintSettings *in) { assert(in); return reinterpret_cast<const JPC_PointConstraintSettings *>(in); }
FN(toJph)(JPC_PointConstraintSettings *in) { assert(in); return reinterpret_cast<JPH::PointConstraintSettings *>(in); }
FN(toJpc)(JPH::PointConstraintSettings *in) { assert(in); return reinterpret_cast<JPC_PointConstraintSettings *>(in); }

FN(toJph)(const JPC_HingeConstraintSettings *in) { assert(in); return reinterpret_cast<const JPH::HingeConstraintSettings *>(in); }
FN(toJpc)(const JPH::HingeConstraintSettings *in) { assert(in); return reinterpret_cast<const JPC_HingeConstraintSettings *>(in); }
FN(toJph)(JPC_HingeConstraintSettings *in) { assert(in); return reinterpret_cast<JPH::HingeConstraintSettings *>(in); }
FN(toJpc)(JPH::HingeConstraintSettings *in) { assert(in); return reinterpret_cast<JPC_HingeConstraintSettings *>(in); }

FN(toJph)(const JPC_ConeConstraintSettings *in) { assert(in); return reinterpret_cast<const JPH::ConeConstraintSettings *>(in); }
FN(toJpc)(const JPH::ConeConstraintSettings *in) { assert(in); return reinterpret_cast<const JPC_ConeConstraintSettings *>(in); }
FN(toJph)(JPC_ConeConstraintSettings *in) { assert(in); return reinterpret_cast<JPH::ConeConstraintSettings *>(in); }
FN(toJpc)(JPH::ConeConstraintSettings *in) { assert(in); return reinterpret_cast<JPC_ConeConstraintSettings *>(in); }

FN(toJph)(const JPC_SliderConstraintSettings *in) { assert(in); return reinterpret_cast<const JPH::SliderConstraintSettings *>(in); }
FN(toJpc)(const JPH::SliderConstraintSettings *in) { assert(in); return reinterpret_cast<const JPC_SliderConstraintSettings *>(in); }
FN(toJph)(JPC_SliderConstraintSettings *in) { assert(in); return reinterpret_cast<JPH::SliderConstraintSettings *>(in); }
FN(toJpc)(JPH::SliderConstraintSettings *in) { assert(in); return reinterpret_cast<JPC_SliderConstraintSettings *>(in); }

FN(toJph)(const JPC_SwingTwistConstraintSettings *in) { assert(in); return reinterpret_cast<const JPH::SwingTwistConstraintSettings *>(in); }
FN(toJpc)(const JPH::SwingTwistConstraintSettings *in) { assert(in); return reinterpret_cast<const JPC_SwingTwistConstraintSettings *>(in); }
FN(toJph)(JPC_SwingTwistConstraintSettings *in) { assert(in); return reinterpret_cast<JPH::SwingTwistConstraintSettings *>(in); }
FN(toJpc)(JPH::SwingTwistConstraintSettings *in) { assert(in); return reinterpret_cast<JPC_SwingTwistConstraintSettings *>(in); }

FN(toJph)(const JPC_SixDOFConstraintSettings *in) { assert(in); return reinterpret_cast<const JPH::SixDOFConstraintSettings *>(in); }
FN(toJpc)(const JPH::SixDOFConstraintSettings *in) { assert(in); return reinterpret_cast<const JPC_SixDOFConstraintSettings *>(in); }
FN(toJph)(JPC_SixDOFConstraintSettings *in) { assert(in); return reinterpret_cast<JPH::SixDOFConstraintSettings *>(in); }
FN(toJpc)(JPH::SixDOFConstraintSettings *in) { assert(in); return reinterpret_cast<JPC_SixDOFConstraintSettings *>(in); }


FN(toJph)(const JPC_MotorSettings *in) { assert(in); return reinterpret_cast<const JPH::MotorSettings *>(in); }
FN(toJpc)(const JPH::MotorSettings *in) { assert(in); return reinterpret_cast<const JPC_MotorSettings *>(in); }
FN(toJph)(JPC_MotorSettings *in) { assert(in); return reinterpret_cast<JPH::MotorSettings *>(in); }
FN(toJpc)(JPH::MotorSettings *in) { assert(in); return reinterpret_cast<JPC_MotorSettings *>(in); }


FN(toJph)(const JPC_BroadPhaseCastResult *in) { assert(in); return reinterpret_cast<const JPH::BroadPhaseCastResult *>(in); }
FN(toJpc)(const JPH::BroadPhaseCastResult *in) { assert(in); return reinterpret_cast<const JPC_BroadPhaseCastResult *>(in); }
FN(toJph)(JPC_BroadPhaseCastResult *in) { assert(in); return reinterpret_cast<JPH::BroadPhaseCastResult *>(in); }
FN(toJpc)(JPH::BroadPhaseCastResult *in) { assert(in); return reinterpret_cast<JPC_BroadPhaseCastResult *>(in); }

FN(toJph)(const JPC_RayCastResult *in) { assert(in); return reinterpret_cast<const JPH::RayCastResult *>(in); }
FN(toJpc)(const JPH::RayCastResult *in) { assert(in); return reinterpret_cast<const JPC_RayCastResult *>(in); }
FN(toJph)(JPC_RayCastResult *in) { assert(in); return reinterpret_cast<JPH::RayCastResult *>(in); }
FN(toJpc)(JPH::RayCastResult *in) { assert(in); return reinterpret_cast<JPC_RayCastResult *>(in); }

FN(toJph)(const JPC_RayCastSettings *in) { assert(in); return reinterpret_cast<const JPH::RayCastSettings *>(in); }
FN(toJpc)(const JPH::RayCastSettings *in) { assert(in); return reinterpret_cast<const JPC_RayCastSettings *>(in); }
FN(toJph)(JPC_RayCastSettings *in) { assert(in); return reinterpret_cast<JPH::RayCastSettings *>(in); }
FN(toJpc)(JPH::RayCastSettings *in) { assert(in); return reinterpret_cast<JPC_RayCastSettings *>(in); }

FN(toJph)(const JPC_CollideShapeSettings *in) { assert(in); return reinterpret_cast<const JPH::CollideShapeSettings *>(in); }
FN(toJpc)(const JPH::CollideShapeSettings *in) { assert(in); return reinterpret_cast<const JPC_CollideShapeSettings *>(in); }
FN(toJph)(JPC_CollideShapeSettings *in) { assert(in); return reinterpret_cast<JPH::CollideShapeSettings *>(in); }
FN(toJpc)(JPH::CollideShapeSettings *in) { assert(in); return reinterpret_cast<JPC_CollideShapeSettings *>(in); }

FN(toJph)(const JPC_ShapeCastSettings *in) { assert(in); return reinterpret_cast<const JPH::ShapeCastSettings *>(in); }
FN(toJpc)(const JPH::ShapeCastSettings *in) { assert(in); return reinterpret_cast<const JPC_ShapeCastSettings *>(in); }
FN(toJph)(JPC_ShapeCastSettings *in) { assert(in); return reinterpret_cast<JPH::ShapeCastSettings *>(in); }
FN(toJpc)(JPH::ShapeCastSettings *in) { assert(in); return reinterpret_cast<JPC_ShapeCastSettings *>(in); }

FN(toJph)(const JPC_ShapeResult *in) { assert(in); return reinterpret_cast<const JPH::Shape::ShapeResult *>(in); }
FN(toJpc)(const JPH::Shape::ShapeResult *in) { assert(in); return reinterpret_cast<const JPC_ShapeResult *>(in); }
FN(toJph)(JPC_ShapeResult *in) { assert(in); return reinterpret_cast<JPH::Shape::ShapeResult *>(in); }
FN(toJpc)(JPH::Shape::ShapeResult *in) { assert(in); return reinterpret_cast<JPC_ShapeResult *>(in); }

FN(toJpc)(const JPH::TransformedShape *in) { assert(in); return reinterpret_cast<const JPC_TransformedShape *>(in); }
FN(toJph)(const JPC_TransformedShape *in) { assert(in); return reinterpret_cast<const JPH::TransformedShape *>(in); }
FN(toJpc)(JPH::TransformedShape *in) { assert(in); return reinterpret_cast<JPC_TransformedShape *>(in); }
FN(toJph)(JPC_TransformedShape *in) { assert(in); return reinterpret_cast<JPH::TransformedShape *>(in); }

FN(toJpc)(const JPH::BodyCreationSettings *in) { assert(in); return reinterpret_cast<const JPC_BodyCreationSettings *>(in); }
FN(toJph)(const JPC_BodyCreationSettings *in) { assert(in); return reinterpret_cast<const JPH::BodyCreationSettings *>(in); }
FN(toJpc)(JPH::BodyCreationSettings *in) { assert(in); return reinterpret_cast<JPC_BodyCreationSettings *>(in); }
FN(toJph)(JPC_BodyCreationSettings *in) { assert(in); return reinterpret_cast<JPH::BodyCreationSettings *>(in); }

FN(toJpc)(const JPH::PhysicsSettings *in) { assert(in); return reinterpret_cast<const JPC_PhysicsSettings *>(in); }
FN(toJph)(const JPC_PhysicsSettings *in) { assert(in); return reinterpret_cast<const JPH::PhysicsSettings *>(in); }
FN(toJpc)(JPH::PhysicsSettings *in) { assert(in); return reinterpret_cast<JPC_PhysicsSettings *>(in); }
FN(toJph)(JPC_PhysicsSettings *in) { assert(in); return reinterpret_cast<JPH::PhysicsSettings *>(in); }

FN(toJpc)(const JPH::CollideShapeResult *in) { assert(in); return reinterpret_cast<const JPC_CollideShapeResult *>(in); }
FN(toJph)(const JPC_CollideShapeResult *in) { assert(in); return reinterpret_cast<const JPH::CollideShapeResult *>(in); }
FN(toJpc)(JPH::CollideShapeResult *in) { assert(in); return reinterpret_cast<JPC_CollideShapeResult *>(in); }
FN(toJph)(JPC_CollideShapeResult *in) { assert(in); return reinterpret_cast<JPH::CollideShapeResult *>(in); }

FN(toJpc)(const JPH::MotionProperties *in) { assert(in); return reinterpret_cast<const JPC_MotionProperties *>(in); }
FN(toJph)(const JPC_MotionProperties *in) { assert(in); return reinterpret_cast<const JPH::MotionProperties *>(in); }
FN(toJpc)(JPH::MotionProperties *in) { assert(in); return reinterpret_cast<JPC_MotionProperties *>(in); }
FN(toJph)(JPC_MotionProperties *in) { assert(in); return reinterpret_cast<JPH::MotionProperties *>(in); }

FN(toJpc)(const JPH::BroadPhaseQuery *in) { assert(in); return reinterpret_cast<const JPC_BroadPhaseQuery *>(in); }
FN(toJph)(const JPC_BroadPhaseQuery *in) { assert(in); return reinterpret_cast<const JPH::BroadPhaseQuery *>(in); }
FN(toJpc)(JPH::BroadPhaseQuery *in) { assert(in); return reinterpret_cast<JPC_BroadPhaseQuery *>(in); }
FN(toJph)(JPC_BroadPhaseQuery *in) { assert(in); return reinterpret_cast<JPH::BroadPhaseQuery *>(in); }

FN(toJpc)(const JPH::NarrowPhaseQuery *in) { assert(in); return reinterpret_cast<const JPC_NarrowPhaseQuery *>(in); }
FN(toJph)(const JPC_NarrowPhaseQuery *in) { assert(in); return reinterpret_cast<const JPH::NarrowPhaseQuery *>(in); }
FN(toJpc)(JPH::NarrowPhaseQuery *in) { assert(in); return reinterpret_cast<JPC_NarrowPhaseQuery *>(in); }
FN(toJph)(JPC_NarrowPhaseQuery *in) { assert(in); return reinterpret_cast<JPH::NarrowPhaseQuery *>(in); }

FN(toJpc)(const JPH::RayCast *in) { assert(in); return reinterpret_cast<const JPC_RayCast *>(in); }
FN(toJph)(const JPC_RayCast *in) { assert(in); return reinterpret_cast<const JPH::RayCast *>(in); }
FN(toJpc)(JPH::RayCast *in) { assert(in); return reinterpret_cast<JPC_RayCast *>(in); }
FN(toJph)(JPC_RayCast *in) { assert(in); return reinterpret_cast<JPH::RayCast *>(in); }

FN(toJpc)(const JPH::RRayCast *in) { assert(in); return reinterpret_cast<const JPC_RRayCast *>(in); }
FN(toJph)(const JPC_RRayCast *in) { assert(in); return reinterpret_cast<const JPH::RRayCast *>(in); }
FN(toJpc)(JPH::RRayCast *in) { assert(in); return reinterpret_cast<JPC_RRayCast *>(in); }
FN(toJph)(JPC_RRayCast *in) { assert(in); return reinterpret_cast<JPH::RRayCast *>(in); }

FN(toJpc)(const JPH::AABox *in) { assert(in); return reinterpret_cast<const JPC_AABox *>(in); }
FN(toJph)(const JPC_AABox *in) { assert(in); return reinterpret_cast<const JPH::AABox *>(in); }
FN(toJpc)(JPH::AABox *in) { assert(in); return reinterpret_cast<JPC_AABox *>(in); }
FN(toJph)(JPC_AABox *in) { assert(in); return reinterpret_cast<JPH::AABox *>(in); }

FN(toJpc)(const JPH::OrientedBox *in) { assert(in); return reinterpret_cast<const JPC_OrientedBox *>(in); }
FN(toJph)(const JPC_OrientedBox *in) { assert(in); return reinterpret_cast<const JPH::OrientedBox *>(in); }
FN(toJpc)(JPH::OrientedBox *in) { assert(in); return reinterpret_cast<JPC_OrientedBox *>(in); }
FN(toJph)(JPC_OrientedBox *in) { assert(in); return reinterpret_cast<JPH::OrientedBox *>(in); }

FN(toJpc)(const JPH::AABoxCast *in) { assert(in); return reinterpret_cast<const JPC_AABoxCast *>(in); }
FN(toJph)(const JPC_AABoxCast *in) { assert(in); return reinterpret_cast<const JPH::AABoxCast *>(in); }
FN(toJpc)(JPH::AABoxCast *in) { assert(in); return reinterpret_cast<JPC_AABoxCast *>(in); }
FN(toJph)(JPC_AABoxCast *in) { assert(in); return reinterpret_cast<JPH::AABoxCast *>(in); }

FN(toJpc)(const JPH::SubShapeIDPair *in) {
    assert(in); return reinterpret_cast<const JPC_SubShapeIDPair *>(in);
}

FN(toJpc)(const JPH::ContactManifold *in) {
    assert(in); return reinterpret_cast<const JPC_ContactManifold *>(in);
}

FN(toJpc)(JPH::ContactSettings *in) {
    assert(in); return reinterpret_cast<JPC_ContactSettings *>(in);
}

FN(toJpc)(JPH::BroadPhaseLayer in) { return static_cast<JPC_BroadPhaseLayer>(in); }
FN(broadPhaseLayerToJph)(JPC_BroadPhaseLayer in) { return static_cast<JPH::BroadPhaseLayer>(in); }

FN(toJpc)(JPH::ObjectLayer in) { return static_cast<JPC_ObjectLayer>(in); }
FN(objectLayerToJph)(JPC_ObjectLayer in) { return static_cast<JPH::ObjectLayer>(in); }

FN(toJpc)(JPH::EShapeType in) { return static_cast<JPC_ShapeType>(in); }
FN(shapeTypeToJph)(JPC_ShapeType in) { return static_cast<JPH::EShapeType>(in); }

FN(toJpc)(JPH::EShapeSubType in) { return static_cast<JPC_ShapeSubType>(in); }
FN(shapeSubTypeToJph)(JPC_ShapeSubType in) { return static_cast<JPH::EShapeSubType>(in); }

FN(toJpc)(JPH::EShapeSubType in) { return static_cast<JPC_ShapeSubType>(in); }
FN(shapeSubTypeToJph)(JPC_ShapeSubType in) { return static_cast<JPH::EShapeSubType>(in); }

FN(toJpc)(JPH::EMotionType in) { return static_cast<JPC_MotionType>(in); }
FN(motionTypeToJph)(JPC_MotionType in) { return static_cast<JPH::EMotionType>(in); }

FN(toJpc)(JPH::EActivation in) { return static_cast<JPC_Activation>(in); }
FN(activationToJph)(JPC_Activation in) { return static_cast<JPH::EActivation>(in); }

FN(toJpc)(JPH::EMotionQuality in) { return static_cast<JPC_MotionQuality>(in); }
FN(motionQualityToJph)(JPC_MotionQuality in) { return static_cast<JPH::EMotionQuality>(in); }

FN(toJpc)(JPH::EConstraintType in) { return static_cast<JPC_ConstraintType>(in); }
FN(constraintTypeToJph)(JPC_ConstraintType in) { return static_cast<JPH::EConstraintType>(in); }

FN(toJpc)(JPH::EConstraintSubType in) { return static_cast<JPC_ConstraintSubType>(in); }
FN(constraintSubTypeToJph)(JPC_ConstraintSubType in) { return static_cast<JPH::EConstraintSubType>(in); }

FN(toJpc)(JPH::EConstraintSpace in) { return static_cast<JPC_ConstraintSpace>(in); }
FN(constraintSpaceToJph)(JPC_ConstraintSpace in) { return static_cast<JPH::EConstraintSpace>(in); }

FN(toJpc)(JPH::EMotorState in) { return static_cast<JPC_MotorState>(in); }
FN(motorStateToJph)(JPC_MotorState in) { return static_cast<JPH::EMotorState>(in); }

FN(toJpc)(const JPH::Shape::ShapeResult& in) {
    auto result = JPC_ShapeResult {
        .result = in.IsValid() ? toJpc(in.Get().GetPtr()) : nullptr,
    };
    if (in.HasError()) {
        in.GetError().copy(result.error, 256);
    } else {
        const_cast<JPH::Shape *>(in.Get().GetPtr())->AddRef();
    }
    return result;
}

#undef FN

static inline JPH::Vec3 loadVec3(const float in[3]) {
    assert(in != nullptr);
    return JPH::Vec3(*reinterpret_cast<const JPH::Float3 *>(in));
}

static inline JPH::DVec3 loadDVec3(const double in[3]) {
    assert(in != nullptr);
    return JPH::DVec3(*reinterpret_cast<const JPH::Double3 *>(in));
}

static inline JPH::RVec3 loadRVec3(const JPC_Real in[3]) {
    assert(in != nullptr);
#if JPC_DOUBLE_PRECISION == 0
    return loadVec3(in);
#else
    return loadDVec3(in);
#endif
}

static inline JPH::Vec4 loadVec4(const float in[4]) {
    assert(in != nullptr);
    return JPH::Vec4::sLoadFloat4(reinterpret_cast<const JPH::Float4 *>(in));
}

static inline JPH::Quat loadQuat(const float in[4]) {
    assert(in != nullptr);
    return {in[0], in[1], in[2], in[3]};
}

static inline JPH::Mat44 loadMat44(const float in[16]) {
    assert(in != nullptr);
    return JPH::Mat44::sLoadFloat4x4(reinterpret_cast<const JPH::Float4 *>(in));
}

static inline JPH::Mat44 loadMat44(const float in_rotation[9], const float in_translation[3]) {
    assert(in_rotation != nullptr && in_translation != nullptr);
    JPH::Mat44 result{};
    result.SetColumn3(0, loadVec3(&in_rotation[0]));
    result.SetColumn3(1, loadVec3(&in_rotation[3]));
    result.SetColumn3(2, loadVec3(&in_rotation[6]));
    result.SetTranslation(loadVec3(in_translation));
    return result;
}

static inline JPH::DMat44 loadDMat44(const float in_rotation[9], const double in_translation[3]) {
    assert(in_rotation != nullptr && in_translation != nullptr);
    JPH::DMat44 result{};
    result.SetColumn3(0, loadVec3(&in_rotation[0]));
    result.SetColumn3(1, loadVec3(&in_rotation[3]));
    result.SetColumn3(2, loadVec3(&in_rotation[6]));
    result.SetTranslation(loadDVec3(in_translation));
    return result;
}

static inline JPH::RMat44 loadRMat44(const float in_rotation[9], const JPC_Real in_translation[3]) {
#if JPC_DOUBLE_PRECISION == 0
    return loadMat44(in_rotation, in_translation);
#else
    return loadDMat44(in_rotation, in_translation);
#endif
}

static inline void storeVec3(float out[3], JPH::Vec3Arg in) {
    assert(out != nullptr);
    in.StoreFloat3(reinterpret_cast<JPH::Float3 *>(out));
}

static inline void storeDVec3(double out[3], JPH::DVec3Arg in) {
    assert(out != nullptr);
    in.StoreDouble3(reinterpret_cast<JPH::Double3 *>(out));
}

static inline void storeRVec3(JPC_Real out[3], JPH::RVec3Arg in) {
#if JPC_DOUBLE_PRECISION == 0
    storeVec3(out, in);
#else
    storeDVec3(out, in);
#endif
}

static inline void storeQuat(float out[4], JPH::QuatArg in) {
    assert(out != nullptr);
    out[0] = in.GetX();
    out[1] = in.GetY();
    out[2] = in.GetZ();
    out[3] = in.GetW();
}

static inline void storeVec4(float out[4], JPH::Vec4Arg in) {
    assert(out != nullptr);
    in.StoreFloat4(reinterpret_cast<JPH::Float4 *>(out));
}

static inline void storeMat44(float out[16], JPH::Mat44Arg in) {
    assert(out != nullptr);
    in.StoreFloat4x4(reinterpret_cast<JPH::Float4 *>(out));
}

static inline void storeMat44(float out_rotation[9], float out_translation[3], JPH::Mat44Arg in) {
    assert(out_rotation != nullptr && out_translation != nullptr);
    storeVec3(&out_rotation[0], in.GetColumn3(0));
    storeVec3(&out_rotation[3], in.GetColumn3(1));
    storeVec3(&out_rotation[6], in.GetColumn3(2));
    storeVec3(out_translation, in.GetTranslation());
}

static inline void storeDMat44(float out_rotation[9], double out_translation[3], JPH::DMat44Arg in) {
    assert(out_rotation != nullptr && out_translation != nullptr);
    storeVec3(&out_rotation[0], in.GetColumn3(0));
    storeVec3(&out_rotation[3], in.GetColumn3(1));
    storeVec3(&out_rotation[6], in.GetColumn3(2));
    storeDVec3(&out_translation[0], in.GetTranslation());
}

static inline void storeRMat44(float out_rotation[9], JPC_Real out_translation[3], JPH::RMat44Arg in) {
#if JPC_DOUBLE_PRECISION == 0
    storeMat44(out_rotation, out_translation, in);
#else
    storeDMat44(out_rotation, out_translation, in);
#endif
}

#ifdef JPH_ENABLE_ASSERTS

static bool
AssertFailedImpl(const char *in_expression,
                 const char *in_message,
                 const char *in_file,
                 uint32_t in_line)
{
	return true;
}

#endif
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyIDVector_Destroy(JPC_BodyIDVector *in_vector)
{
    delete reinterpret_cast<JPH::BodyIDVector *>(in_vector);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_RegisterDefaultAllocator(void)
{
    JPH::RegisterDefaultAllocator();
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_RegisterCustomAllocator(JPC_AllocateFunction in_alloc,
                            JPC_FreeFunction in_free,
                            JPC_AlignedAllocateFunction in_aligned_alloc,
                            JPC_AlignedFreeFunction in_aligned_free)
{
#ifndef JPH_DISABLE_CUSTOM_ALLOCATOR
    JPH::Allocate = in_alloc;
    JPH::Free = in_free;
    JPH::AlignedAllocate = in_aligned_alloc;
    JPH::AlignedFree = in_aligned_free;
#endif
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_CreateFactory(void)
{
    assert(JPH::Factory::sInstance == nullptr);
    JPH::Factory::sInstance = new JPH::Factory();
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_DestroyFactory(void)
{
    assert(JPH::Factory::sInstance != nullptr);
    JPH::PhysicsMaterial::sDefault = nullptr;
    delete JPH::Factory::sInstance;
    JPH::Factory::sInstance = nullptr;
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_RegisterTypes(void)
{
    JPH::RegisterTypes();
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyCreationSettings_SetDefault(JPC_BodyCreationSettings *out_settings)
{
    assert(out_settings != nullptr);
    const JPH::BodyCreationSettings settings;
    *out_settings = *toJpc(&settings);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyCreationSettings_Set(JPC_BodyCreationSettings *out_settings,
                             const JPC_Shape *in_shape,
                             const JPC_Real in_position[3],
                             const float in_rotation[4],
                             JPC_MotionType in_motion_type,
                             JPC_ObjectLayer in_layer)
{
    assert(out_settings != nullptr && in_shape != nullptr && in_position != nullptr && in_rotation != nullptr);

    JPC_BodyCreationSettings settings;
    JPC_BodyCreationSettings_SetDefault(&settings);

    settings.position[0] = in_position[0];
    settings.position[1] = in_position[1];
    settings.position[2] = in_position[2];
    settings.rotation[0] = in_rotation[0];
    settings.rotation[1] = in_rotation[1];
    settings.rotation[2] = in_rotation[2];
    settings.rotation[3] = in_rotation[3];
    settings.object_layer = in_layer;
    settings.motion_type = in_motion_type;
    settings.shape = in_shape;

    *out_settings = settings;
}
//--------------------------------------------------------------------------------------------------
JPC_API const JPC_ShapeSettings *
JPC_BodyCreationSettings_GetShapeSettings(const JPC_BodyCreationSettings *in_settings)
{
    return toJpc(toJph(in_settings)->GetShapeSettings());
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyCreationSettings_SetShapeSettings(JPC_BodyCreationSettings *in_settings,
                                          const JPC_ShapeSettings *in_shape)
{
    toJph(in_settings)->SetShapeSettings(toJph(in_shape));
}
//--------------------------------------------------------------------------------------------------
JPC_API JPC_ShapeResult
JPC_BodyCreationSettings_ConvertShapeSettings(JPC_BodyCreationSettings *in_settings)
{
    auto result = toJph(in_settings)->ConvertShapeSettings();
    return toJpc(result);
}
//--------------------------------------------------------------------------------------------------
JPC_API const JPC_Shape *
JPC_BodyCreationSettings_GetShape(const JPC_BodyCreationSettings *in_settings)
{
    return toJpc(toJph(in_settings)->GetShape());
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyCreationSettings_SetShape(JPC_BodyCreationSettings *in_settings,
                                  const JPC_Shape *in_shape)
{
    toJph(in_settings)->SetShape(toJph(in_shape));
}
//--------------------------------------------------------------------------------------------------
JPC_API bool
JPC_BodyCreationSettings_HasMassProperties(const JPC_BodyCreationSettings *in_settings)
{
    return toJph(in_settings)->HasMassProperties();
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyCreationSettings_GetMassProperties(const JPC_BodyCreationSettings *in_settings,
                                           JPC_MassProperties *out_properties)
{
    auto out = toJph(in_settings)->GetMassProperties();
    *out_properties = *toJpc(&out);
}
//--------------------------------------------------------------------------------------------------
JPC_API bool
JPC_CollisionGroup_CanCollide(const JPC_CollisionGroup *in_group, const JPC_CollisionGroup *in_other)
{
    return toJph(in_group)->CanCollide(*toJph(in_other));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_PhysicsSettings_SetDefault(JPC_PhysicsSettings *out_settings)
{
    const JPH::PhysicsSettings settings;
    *out_settings = *toJpc(&settings);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BroadPhaseCastResult_SetDefault(JPC_BroadPhaseCastResult *out_result)
{
    const JPH::BroadPhaseCastResult result;
    *out_result = *toJpc(&result);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_RayCastResult_SetDefault(JPC_RayCastResult *out_result)
{
    const JPH::RayCastResult result;
    *out_result = *toJpc(&result);
}
//--------------------------------------------------------------------------------------------------
JPC_API float
JPC_CollideShapeResult_GetEarlyOutFraction(JPC_CollideShapeResult *in_settings)
{
    return toJph(in_settings)->GetEarlyOutFraction();
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_CollideShapeResult_Reversed(JPC_CollideShapeResult *in_settings, JPC_CollideShapeResult *out_settings)
{
    const auto out = toJph(in_settings)->Reversed();
    *out_settings = *toJpc(&out);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_RayCastSettings_SetDefault(JPC_RayCastSettings *out_settings)
{
    const JPH::RayCastSettings settings;
    *out_settings = *toJpc(&settings);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_CollideShapeSettings_SetDefault(JPC_CollideShapeSettings *out_settings)
{
    const JPH::CollideShapeSettings settings;
    *out_settings = *toJpc(&settings);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_ShapeCastSettings_SetDefault(JPC_ShapeCastSettings *out_settings)
{
    const JPH::ShapeCastSettings settings;
    *out_settings = *toJpc(&settings);
}
//--------------------------------------------------------------------------------------------------
//
// JPC_TempAllocator
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_TempAllocator *
JPC_TempAllocator_Create(uint32_t in_size)
{
    auto impl = new JPH::TempAllocatorImpl(in_size);
    return reinterpret_cast<JPC_TempAllocator *>(impl);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_TempAllocator_Destroy(JPC_TempAllocator *in_allocator)
{
    assert(in_allocator != nullptr);
    delete reinterpret_cast<JPH::TempAllocator *>(in_allocator);
}
//--------------------------------------------------------------------------------------------------
//
// JPC_JobSystem
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_JobSystem *
JPC_JobSystem_Create(uint32_t in_max_jobs, uint32_t in_max_barriers, int in_num_threads)
{
    auto job_system = new JPH::JobSystemThreadPool(in_max_jobs, in_max_barriers, in_num_threads);
    return reinterpret_cast<JPC_JobSystem *>(job_system);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_JobSystem_Destroy(JPC_JobSystem *in_job_system)
{
    assert(in_job_system != nullptr);
    delete reinterpret_cast<JPH::JobSystemThreadPool *>(in_job_system);
}
//--------------------------------------------------------------------------------------------------
//
// JPC_PhysicsSystem
//
//--------------------------------------------------------------------------------------------------
class ContactListener : public JPH::ContactListener
{
public:
    JPH::ValidateResult OnContactValidate(
        const JPH::Body &inBody1,
        const JPH::Body &inBody2,
        JPH::RVec3Arg inBaseOffset,
        const JPH::CollideShapeResult &inCollisionResult) override
    {
        if (c_listener->vtbl->OnContactValidate)
        {
            JPC_Real base_offset[3];
            storeRVec3(base_offset, inBaseOffset);

            const JPC_ValidateResult res = c_listener->vtbl->OnContactValidate(
                    c_listener, toJpc(&inBody1), toJpc(&inBody2), base_offset, toJpc(&inCollisionResult));

            return static_cast<JPH::ValidateResult>(res);
        }
        return JPH::ContactListener::OnContactValidate(inBody1, inBody2, inBaseOffset, inCollisionResult);
    }

    void OnContactAdded(
        const JPH::Body &inBody1,
        const JPH::Body &inBody2,
        const JPH::ContactManifold &inManifold,
        JPH::ContactSettings &ioSettings) override
    {
        if (c_listener->vtbl->OnContactAdded)
        {
            c_listener->vtbl->OnContactAdded(
                c_listener, toJpc(&inBody1), toJpc(&inBody2), toJpc(&inManifold), toJpc(&ioSettings));
        }
    }

    void OnContactPersisted(
        const JPH::Body &inBody1,
        const JPH::Body &inBody2,
        const JPH::ContactManifold &inManifold,
        JPH::ContactSettings &ioSettings) override
    {
        if (c_listener->vtbl->OnContactPersisted)
        {
            c_listener->vtbl->OnContactPersisted(
                c_listener, toJpc(&inBody1), toJpc(&inBody2), toJpc(&inManifold), toJpc(&ioSettings));
        }
    }

    void OnContactRemoved(const JPH::SubShapeIDPair &inSubShapePair) override
    {
        if (c_listener->vtbl->OnContactRemoved)
            c_listener->vtbl->OnContactRemoved(c_listener, toJpc(&inSubShapePair));
    }

    struct CListener
    {
        JPC_ContactListenerVTable *vtbl;
    };
    CListener *c_listener;
};

struct PhysicsSystemData
{
    uint64_t safety_token = 0xC0DEC0DEC0DEC0DE;
    ContactListener *contact_listener = nullptr;
};

JPC_API JPC_PhysicsSystem *
JPC_PhysicsSystem_Create(uint32_t in_max_bodies,
                         uint32_t in_num_body_mutexes,
                         uint32_t in_max_body_pairs,
                         uint32_t in_max_contact_constraints,
                         const void *in_broad_phase_layer_interface,
                         const void *in_object_vs_broad_phase_layer_filter,
                         const void *in_object_layer_pair_filter)
{
    assert(in_broad_phase_layer_interface != nullptr);
    assert(in_object_vs_broad_phase_layer_filter != nullptr);
    assert(in_object_layer_pair_filter != nullptr);

    auto physics_system =
        static_cast<JPH::PhysicsSystem *>(
            JPH::Allocate(sizeof(JPH::PhysicsSystem) + sizeof(PhysicsSystemData)));
    ::new (physics_system) JPH::PhysicsSystem();

    PhysicsSystemData* data =
        ::new (reinterpret_cast<uint8_t *>(physics_system) + sizeof(JPH::PhysicsSystem)) PhysicsSystemData();
    assert(data->safety_token == 0xC0DEC0DEC0DEC0DE);

    physics_system->Init(
        in_max_bodies,
        in_num_body_mutexes,
        in_max_body_pairs,
        in_max_contact_constraints,
        *static_cast<const JPH::BroadPhaseLayerInterface *>(in_broad_phase_layer_interface),
        *static_cast<const JPH::ObjectVsBroadPhaseLayerFilter *>(in_object_vs_broad_phase_layer_filter),
        *static_cast<const JPH::ObjectLayerPairFilter *>(in_object_layer_pair_filter));

    return toJpc(physics_system);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_PhysicsSystem_Destroy(JPC_PhysicsSystem *in_physics_system)
{
    auto data = reinterpret_cast<PhysicsSystemData *>(
        reinterpret_cast<uint8_t *>(in_physics_system) + sizeof(JPH::PhysicsSystem));
    assert(data->safety_token == 0xC0DEC0DEC0DEC0DE);

    if (data->contact_listener)
    {
        data->contact_listener->~ContactListener();
        JPH::Free(data->contact_listener);
    }

    toJph(in_physics_system)->~PhysicsSystem();
    JPH::Free(in_physics_system);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_PhysicsSystem_SetBodyActivationListener(JPC_PhysicsSystem *in_physics_system, void *in_listener)
{
    toJph(in_physics_system)->SetBodyActivationListener(static_cast<JPH::BodyActivationListener *>(in_listener));
}
//--------------------------------------------------------------------------------------------------
JPC_API void *
JPC_PhysicsSystem_GetBodyActivationListener(const JPC_PhysicsSystem *in_physics_system)
{
    return toJph(in_physics_system)->GetBodyActivationListener();
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_PhysicsSystem_SetContactListener(JPC_PhysicsSystem *in_physics_system, void *in_listener)
{
    if (in_listener == nullptr)
    {
        toJph(in_physics_system)->SetContactListener(nullptr);
        return;
    }

    auto data = reinterpret_cast<PhysicsSystemData *>(
        reinterpret_cast<uint8_t *>(in_physics_system) + sizeof(JPH::PhysicsSystem));
    assert(data->safety_token == 0xC0DEC0DEC0DEC0DE);

    if (data->contact_listener == nullptr)
    {
        data->contact_listener = static_cast<ContactListener *>(JPH::Allocate(sizeof(ContactListener)));
        ::new (data->contact_listener) ContactListener();
    }

    toJph(in_physics_system)->SetContactListener(data->contact_listener);

    data->contact_listener->c_listener = static_cast<ContactListener::CListener *>(in_listener);
}
//--------------------------------------------------------------------------------------------------
JPC_API void *
JPC_PhysicsSystem_GetContactListener(const JPC_PhysicsSystem *in_physics_system)
{
    auto listener = static_cast<ContactListener *>(toJph(in_physics_system)->GetContactListener());
    if (listener == nullptr)
        return nullptr;
    return listener->c_listener;
}
//--------------------------------------------------------------------------------------------------
// TODO this is way too complicated for my limited C++ knowledge
//class ContactCombineFunctor {
//public:
//    explicit ContactCombineFunctor(float (*fn)(
//            const JPC_Body *,
//            const JPC_SubShapeID,
//            const JPC_Body *,
//            const JPC_SubShapeID)) : mFn(fn) {}
//
//    float operator () (
//            const JPH::Body &inBody1,
//            const JPH::SubShapeID &inSubShapeID1,
//            const JPH::Body &inBody2,
//            const JPH::SubShapeID &inSubShapeID2
//    ) {
//        return mFn(toJpc(&inBody1), toJpc(inSubShapeID1), toJpc(&inBody2), toJpc(inSubShapeID2));
//    }
//
//    JPH::ContactConstraintManager::CombineFunction thing() {
//        return [this](
//                const JPH::Body &inBody1,
//                const JPH::SubShapeID &inSubShapeID1,
//                const JPH::Body &inBody2,
//                const JPH::SubShapeID &inSubShapeID2
//        ) -> float {
//            return mFn(toJpc(&inBody1), toJpc(inSubShapeID1), toJpc(&inBody2), toJpc(inSubShapeID2));
//        }
//    }
//
//private:
//    float (*mFn)(const JPC_Body *, const JPC_SubShapeID, const JPC_Body *, const JPC_SubShapeID);
//};
//
//JPC_API void
//JPC_PhysicsSystem_SetCombineFriction(JPC_PhysicsSystem *in_physics_system,
//                                     const JPC_CombineFunctor *in_combine)
//{
//    ContactCombineFunctor functor(in_combine->fn);
//    toJph(in_physics_system)->SetCombineFriction(functor);
//}
//class CombineFunctor {
//public:
//    explicit CombineFunctor(float (*mCombine)(const JPC_Body *in_body_1,
//                                     const JPC_SubShapeID in_sub_shape_id_1,
//                                     const JPC_Body *in_body_2,
//                                     const JPC_SubShapeID in_sub_shape_id_2)) : mCombine(mCombine) {}
//
//    float operator() (const JPH::Body &inBody1,
//                      const JPH::SubShapeID &inSubShapeID1,
//                      const JPH::Body &inBody2,
//                      const JPH::SubShapeID &inSubShapeID2) const {
//        return mCombine(toJpc(&inBody1), toJpc(inSubShapeID1), toJpc(&inBody2), toJpc(inSubShapeID2));
//    }
//private:
//    float
//    (*mCombine)(const JPC_Body *in_body_1,
//                const JPC_SubShapeID in_sub_shape_id_1,
//                const JPC_Body *in_body_2,
//                const JPC_SubShapeID in_sub_shape_id_2);
//};
//
//JPC_API void
//JPC_PhysicsSystem_SetCombineFriction(JPC_PhysicsSystem *in_physics_system,
//                                     float
//                                     (*in_combine)(const JPC_Body *in_body_1,
//                                                   JPC_SubShapeID in_sub_shape_id_1,
//                                                   const JPC_Body *in_body_2,
//                                                   JPC_SubShapeID in_sub_shape_id_2))
//{
//    CombineFunctor fn(in_combine);
//
//    auto function = reinterpret_cast<JPH::ContactConstraintManager::CombineFunction>(&CombineFunctor::operator());
//    toJph(in_physics_system)->SetCombineFriction(function);
//}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_PhysicsSystem_SetPhysicsSettings(JPC_PhysicsSystem *in_physics_system,
                                     const JPC_PhysicsSettings *in_settings)
{
    toJph(in_physics_system)->SetPhysicsSettings(*toJph(in_settings));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_PhysicsSystem_GetPhysicsSettings(const JPC_PhysicsSystem *in_physics_system,
                                     JPC_PhysicsSettings *out_settings)
{
    const auto out = toJph(in_physics_system)->GetPhysicsSettings();
    *out_settings = *toJpc(&out);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_PhysicsSystem_SetCombineRestitution(JPC_PhysicsSystem *in_physics_system,
                                        float (*in_combine)(const JPC_Body *in_body_1, JPC_SubShapeID in_sub_shape_id_1, JPC_Body *in_body_2, JPC_SubShapeID in_sub_shape_id_2));
//--------------------------------------------------------------------------------------------------
JPC_API uint32_t
JPC_PhysicsSystem_GetNumBodies(const JPC_PhysicsSystem *in_physics_system)
{
    return toJph(in_physics_system)->GetNumBodies();
}
//--------------------------------------------------------------------------------------------------
JPC_API uint32_t
JPC_PhysicsSystem_GetNumActiveBodies(const JPC_PhysicsSystem *in_physics_system)
{
    return toJph(in_physics_system)->GetNumActiveBodies();
}
//--------------------------------------------------------------------------------------------------
JPC_API uint32_t
JPC_PhysicsSystem_GetMaxBodies(const JPC_PhysicsSystem *in_physics_system)
{
    return toJph(in_physics_system)->GetMaxBodies();
}
//--------------------------------------------------------------------------------------------------
JPC_API JPC_BodyIDVector *
JPC_PhysicsSystem_GetBodyIDs(const JPC_PhysicsSystem *in_physics_system,
                             uint32_t *out_num_body_ids,
                             JPC_BodyID **out_body_ids)
{
    auto out = new JPH::BodyIDVector();
    toJph(in_physics_system)->GetBodies(*out);
    *out_num_body_ids = out->size();
    *out_body_ids = toJpc(out->data());
    return reinterpret_cast<JPC_BodyIDVector *>(out);
}
//--------------------------------------------------------------------------------------------------
JPC_API JPC_BodyIDVector *
JPC_PhysicsSystem_GetActiveBodyIDs(const JPC_PhysicsSystem *in_physics_system,
                                   uint32_t *out_num_body_ids,
                                   JPC_BodyID **out_body_ids)
{
    auto out = new JPH::BodyIDVector();
    toJph(in_physics_system)->GetActiveBodies(*out);
    *out_num_body_ids = out->size();
    *out_body_ids = toJpc(out->data());
    return reinterpret_cast<JPC_BodyIDVector *>(out);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_PhysicsSystem_GetGravity(const JPC_PhysicsSystem *in_physics_system, float out_gravity[3])
{
    storeVec3(out_gravity, toJph(in_physics_system)->GetGravity());
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_PhysicsSystem_SetGravity(JPC_PhysicsSystem *in_physics_system, const float in_gravity[3])
{
    toJph(in_physics_system)->SetGravity(loadVec3(in_gravity));
}
//--------------------------------------------------------------------------------------------------
JPC_API JPC_BodyInterface *
JPC_PhysicsSystem_GetBodyInterface(JPC_PhysicsSystem *in_physics_system)
{
    return toJpc(&toJph(in_physics_system)->GetBodyInterface());
}
JPC_API JPC_BodyInterface *
JPC_PhysicsSystem_GetBodyInterfaceNoLock(JPC_PhysicsSystem *in_physics_system)
{
    return toJpc(&toJph(in_physics_system)->GetBodyInterfaceNoLock());
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_PhysicsSystem_Update(JPC_PhysicsSystem *in_physics_system,
                         float in_delta_time,
                         int in_collision_steps,
                         int in_integration_sub_steps,
                         JPC_TempAllocator *in_temp_allocator,
                         JPC_JobSystem *in_job_system)
{
    assert(in_temp_allocator != nullptr && in_job_system != nullptr);
    toJph(in_physics_system)->Update(
        in_delta_time,
        in_collision_steps,
        in_integration_sub_steps,
        reinterpret_cast<JPH::TempAllocator *>(in_temp_allocator),
        reinterpret_cast<JPH::JobSystem *>(in_job_system));
}
//--------------------------------------------------------------------------------------------------
JPC_API const JPC_BodyLockInterface *
JPC_PhysicsSystem_GetBodyLockInterface(const JPC_PhysicsSystem *in_physics_system)
{
    return toJpc(&toJph(in_physics_system)->GetBodyLockInterface());
}
JPC_API const JPC_BodyLockInterface *
JPC_PhysicsSystem_GetBodyLockInterfaceNoLock(const JPC_PhysicsSystem *in_physics_system)
{
    return toJpc(&toJph(in_physics_system)->GetBodyLockInterfaceNoLock());
}
//--------------------------------------------------------------------------------------------------
JPC_API const JPC_BroadPhaseQuery *
JPC_PhysicsSystem_GetBroadPhaseQuery(const JPC_PhysicsSystem *in_physics_system)
{
    return toJpc(&toJph(in_physics_system)->GetBroadPhaseQuery());
}
//--------------------------------------------------------------------------------------------------
JPC_API const JPC_NarrowPhaseQuery *
JPC_PhysicsSystem_GetNarrowPhaseQuery(const JPC_PhysicsSystem *in_physics_system)
{
    return toJpc(&toJph(in_physics_system)->GetNarrowPhaseQuery());
}
//--------------------------------------------------------------------------------------------------
JPC_API const JPC_NarrowPhaseQuery *
JPC_PhysicsSystem_GetNarrowPhaseQueryNoLock(const JPC_PhysicsSystem *in_physics_system)
{
    return toJpc(&toJph(in_physics_system)->GetNarrowPhaseQueryNoLock());
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_PhysicsSystem_OptimizeBroadPhase(JPC_PhysicsSystem *in_physics_system)
{
    toJph(in_physics_system)->OptimizeBroadPhase();
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_PhysicsSystem_AddStepListener(JPC_PhysicsSystem *in_physics_system, void *in_listener)
{
    toJph(in_physics_system)->AddStepListener(static_cast<JPH::PhysicsStepListener *>(in_listener));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_PhysicsSystem_RemoveStepListener(JPC_PhysicsSystem *in_physics_system, void *in_listener)
{
    toJph(in_physics_system)->RemoveStepListener(static_cast<JPH::PhysicsStepListener *>(in_listener));
}
//--------------------------------------------------------------------------------------------------
JPC_API bool
JPC_PhysicsSystem_WereBodiesInContact(const JPC_PhysicsSystem *in_physics_system,
                                      JPC_BodyID in_body_id1,
                                      JPC_BodyID in_body_id2)
{
    return toJph(in_physics_system)->WereBodiesInContact(toJph(in_body_id1), toJph(in_body_id2));
}
//--------------------------------------------------------------------------------------------------
//
// JPC_BodyLockInterface
//
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyLockInterface_LockRead(const JPC_BodyLockInterface *in_lock_interface,
                               JPC_BodyID in_body_id,
                               JPC_BodyLockRead *out_lock)
{
    assert(out_lock != nullptr);
    ::new (out_lock) JPH::BodyLockRead(*toJph(in_lock_interface), toJph(in_body_id));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyLockInterface_UnlockRead(const JPC_BodyLockInterface *in_lock_interface,
                                 JPC_BodyLockRead *io_lock)
{
    assert(io_lock != nullptr);
    assert(in_lock_interface != nullptr && in_lock_interface == io_lock->lock_interface);
    toJph(io_lock)->~BodyLockRead();
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyLockInterface_LockWrite(const JPC_BodyLockInterface *in_lock_interface,
                                JPC_BodyID in_body_id,
                                JPC_BodyLockWrite *out_lock)
{
    assert(out_lock != nullptr);
    ::new (out_lock) JPH::BodyLockWrite(*toJph(in_lock_interface), toJph(in_body_id));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyLockInterface_UnlockWrite(const JPC_BodyLockInterface *in_lock_interface,
                                  JPC_BodyLockWrite *io_lock)
{
    assert(io_lock != nullptr);
    assert(in_lock_interface != nullptr && in_lock_interface == io_lock->lock_interface);
    toJph(io_lock)->~BodyLockWrite();
}
//--------------------------------------------------------------------------------------------------
//
// (collector structs)
//
//--------------------------------------------------------------------------------------------------
// note: it doesn't matter what type we cast to, as long as it's a template of JPH::CollisionCollector
JPC_API void
JPC_CollisionCollector_Reset(void *in_collector)
{
    static_cast<JPH::RayCastBodyCollector *>(in_collector)->Reset();
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_CollisionCollector_UpdateEarlyOutFraction(void *in_collector, float in_fraction)
{
    static_cast<JPH::RayCastBodyCollector *>(in_collector)->UpdateEarlyOutFraction(in_fraction);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_CollisionCollector_ResetEarlyOutFraction(void *in_collector, float in_fraction)
{
    static_cast<JPH::RayCastBodyCollector *>(in_collector)->ResetEarlyOutFraction(in_fraction);
}
//--------------------------------------------------------------------------------------------------
//
// (RayCastBodyCollector)
//
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_RayCastBodyCollector_ForceEarlyOut(void *in_collector)
{
    static_cast<JPH::RayCastBodyCollector *>(in_collector)->ForceEarlyOut();
}

JPC_API bool
JPC_RayCastBodyCollector_ShouldEarlyOut(void *in_collector)
{
    return static_cast<JPH::RayCastBodyCollector *>(in_collector)->ShouldEarlyOut();
}
//--------------------------------------------------------------------------------------------------
//
// (CollideShapeBodyCollector)
//
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_CollideShapeBodyCollector_ForceEarlyOut(void *in_collector)
{
    static_cast<JPH::CollideShapeBodyCollector *>(in_collector)->ForceEarlyOut();
}

JPC_API bool
JPC_CollideShapeBodyCollector_ShouldEarlyOut(void *in_collector)
{
    return static_cast<JPH::CollideShapeBodyCollector *>(in_collector)->ShouldEarlyOut();
}
//--------------------------------------------------------------------------------------------------
//
// (CastShapeBodyCollector)
//
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_CastShapeBodyCollector_ForceEarlyOut(void *in_collector)
{
    static_cast<JPH::CastShapeBodyCollector *>(in_collector)->ForceEarlyOut();
}

JPC_API bool
JPC_CastShapeBodyCollector_ShouldEarlyOut(void *in_collector)
{
    return static_cast<JPH::CastShapeBodyCollector *>(in_collector)->ShouldEarlyOut();
}
//--------------------------------------------------------------------------------------------------
//
// (CastRayCollector)
//
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_CastRayCollector_ForceEarlyOut(void *in_collector)
{
    static_cast<JPH::CastRayCollector *>(in_collector)->ForceEarlyOut();
}

JPC_API bool
JPC_CastRayCollector_ShouldEarlyOut(void *in_collector)
{
    return static_cast<JPH::CastRayCollector *>(in_collector)->ShouldEarlyOut();
}
//--------------------------------------------------------------------------------------------------
//
// (CastShapeCollector)
//
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_CastShapeCollector_ForceEarlyOut(void *in_collector)
{
    static_cast<JPH::CastShapeCollector *>(in_collector)->ForceEarlyOut();
}

JPC_API bool
JPC_CastShapeCollector_ShouldEarlyOut(void *in_collector)
{
    return static_cast<JPH::CastShapeCollector *>(in_collector)->ShouldEarlyOut();
}
//--------------------------------------------------------------------------------------------------
//
// (CollidePointCollector)
//
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_CollidePointCollector_ForceEarlyOut(void *in_collector)
{
    static_cast<JPH::CollidePointCollector *>(in_collector)->ForceEarlyOut();
}

JPC_API bool
JPC_CollidePointCollector_ShouldEarlyOut(void *in_collector)
{
    return static_cast<JPH::CollidePointCollector *>(in_collector)->ShouldEarlyOut();
}
//--------------------------------------------------------------------------------------------------
//
// (CollideShapeCollector)
//
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_CollideShapeCollector_ForceEarlyOut(void *in_collector)
{
    static_cast<JPH::CollideShapeCollector *>(in_collector)->ForceEarlyOut();
}

JPC_API bool
JPC_CollideShapeCollector_ShouldEarlyOut(void *in_collector)
{
    return static_cast<JPH::CollideShapeCollector *>(in_collector)->ShouldEarlyOut();
}
//--------------------------------------------------------------------------------------------------
//
// (TransformedShapeCollector)
//
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_TransformedShapeCollector_ForceEarlyOut(void *in_collector)
{
    static_cast<JPH::TransformedShapeCollector *>(in_collector)->ForceEarlyOut();
}

JPC_API bool
JPC_TransformedShapeCollector_ShouldEarlyOut(void *in_collector)
{
    return static_cast<JPH::TransformedShapeCollector *>(in_collector)->ShouldEarlyOut();
}
//--------------------------------------------------------------------------------------------------
//
// JPC_BroadPhaseQuery
//
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BroadPhaseQuery_CastRay(const JPC_BroadPhaseQuery *in_query,
                            const JPC_RayCast *in_ray,
                            void *io_collector,
                            const void *in_broad_phase_layer_filter,
                            const void *in_object_layer_filter)
{
    auto collector = static_cast<JPH::RayCastBodyCollector *>(io_collector);
    collector->Reset();
    const JPH::BroadPhaseLayerFilter broad_phase_layer_filter{};
    const JPH::ObjectLayerFilter object_layer_filter{};

    toJph(in_query)->CastRay(
        *toJph(in_ray),
        *collector,
        in_broad_phase_layer_filter
            ? *static_cast<const JPH::BroadPhaseLayerFilter *>(in_broad_phase_layer_filter)
            : broad_phase_layer_filter,
        in_object_layer_filter
            ? *static_cast<const JPH::ObjectLayerFilter *>(in_object_layer_filter)
            : object_layer_filter);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BroadPhaseQuery_CollideAABox(const JPC_BroadPhaseQuery *in_query,
                                 const JPC_AABox *in_box,
                                 void *io_collector,
                                 const void *in_broad_phase_layer_filter,
                                 const void *in_object_layer_filter)
{
    auto collector = static_cast<JPH::CollideShapeBodyCollector *>(io_collector);
    collector->Reset();
    const JPH::BroadPhaseLayerFilter broad_phase_layer_filter{};
    const JPH::ObjectLayerFilter object_layer_filter{};

    toJph(in_query)->CollideAABox(
        *toJph(in_box),
        *collector,
        in_broad_phase_layer_filter
            ? *static_cast<const JPH::BroadPhaseLayerFilter *>(in_broad_phase_layer_filter)
            : broad_phase_layer_filter,
        in_object_layer_filter
            ? *static_cast<const JPH::ObjectLayerFilter *>(in_object_layer_filter)
            : object_layer_filter);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BroadPhaseQuery_CollideSphere(const JPC_BroadPhaseQuery *in_query,
                                  const float in_center[3],
                                  float in_radius,
                                  void *io_collector,
                                  const void *in_broad_phase_layer_filter,
                                  const void *in_object_layer_filter)
{
    auto collector = static_cast<JPH::CollideShapeBodyCollector *>(io_collector);
    collector->Reset();
    const JPH::BroadPhaseLayerFilter broad_phase_layer_filter{};
    const JPH::ObjectLayerFilter object_layer_filter{};

    toJph(in_query)->CollideSphere(
        loadVec3(in_center),
        in_radius,
        *collector,
        in_broad_phase_layer_filter
            ? *static_cast<const JPH::BroadPhaseLayerFilter *>(in_broad_phase_layer_filter)
            : broad_phase_layer_filter,
        in_object_layer_filter
            ? *static_cast<const JPH::ObjectLayerFilter *>(in_object_layer_filter)
            : object_layer_filter);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BroadPhaseQuery_CollidePoint(const JPC_BroadPhaseQuery *in_query,
                                 const float in_point[3],
                                 void *io_collector,
                                 const void *in_broad_phase_layer_filter,
                                 const void *in_object_layer_filter)
{
    auto collector = static_cast<JPH::CollideShapeBodyCollector *>(io_collector);
    collector->Reset();
    const JPH::BroadPhaseLayerFilter broad_phase_layer_filter{};
    const JPH::ObjectLayerFilter object_layer_filter{};

    toJph(in_query)->CollidePoint(
        loadVec3(in_point),
        *collector,
        in_broad_phase_layer_filter
            ? *static_cast<const JPH::BroadPhaseLayerFilter *>(in_broad_phase_layer_filter)
            : broad_phase_layer_filter,
        in_object_layer_filter
            ? *static_cast<const JPH::ObjectLayerFilter *>(in_object_layer_filter)
            : object_layer_filter);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BroadPhaseQuery_CollideOrientedBox(const JPC_BroadPhaseQuery *in_query,
                                       const JPC_OrientedBox *in_box,
                                       void *io_collector,
                                       const void *in_broad_phase_layer_filter,
                                       const void *in_object_layer_filter)
{
    auto collector = static_cast<JPH::CollideShapeBodyCollector *>(io_collector);
    collector->Reset();
    const JPH::BroadPhaseLayerFilter broad_phase_layer_filter{};
    const JPH::ObjectLayerFilter object_layer_filter{};

    toJph(in_query)->CollideOrientedBox(
        *toJph(in_box),
        *collector,
        in_broad_phase_layer_filter
            ? *static_cast<const JPH::BroadPhaseLayerFilter *>(in_broad_phase_layer_filter)
            : broad_phase_layer_filter,
        in_object_layer_filter
            ? *static_cast<const JPH::ObjectLayerFilter *>(in_object_layer_filter)
            : object_layer_filter);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BroadPhaseQuery_CastAABox(const JPC_BroadPhaseQuery *in_query,
                              const JPC_AABoxCast *in_box,
                              void *io_collector,
                              const void *in_broad_phase_layer_filter,
                              const void *in_object_layer_filter)
{
    auto collector = static_cast<JPH::CastShapeBodyCollector *>(io_collector);
    collector->Reset();
    const JPH::BroadPhaseLayerFilter broad_phase_layer_filter{};
    const JPH::ObjectLayerFilter object_layer_filter{};

    toJph(in_query)->CastAABox(
        *toJph(in_box),
        *collector,
        in_broad_phase_layer_filter
            ? *static_cast<const JPH::BroadPhaseLayerFilter *>(in_broad_phase_layer_filter)
            : broad_phase_layer_filter,
        in_object_layer_filter
            ? *static_cast<const JPH::ObjectLayerFilter *>(in_object_layer_filter)
            : object_layer_filter);
}
//--------------------------------------------------------------------------------------------------
//
// JPC_NarrowPhaseQuery
//
//--------------------------------------------------------------------------------------------------
JPC_API bool
JPC_NarrowPhaseQuery_GetCastRay(const JPC_NarrowPhaseQuery *in_query,
                                const JPC_RRayCast *in_ray,
                                JPC_RayCastResult *io_hit,
                                const void *in_broad_phase_layer_filter,
                                const void *in_object_layer_filter,
                                const void *in_body_filter)
{
    const JPH::BroadPhaseLayerFilter broad_phase_layer_filter{};
    const JPH::ObjectLayerFilter object_layer_filter{};
    const JPH::BodyFilter body_filter{};

    return toJph(in_query)->CastRay(
        *toJph(in_ray),
        *toJph(io_hit),
        in_broad_phase_layer_filter
            ? *static_cast<const JPH::BroadPhaseLayerFilter *>(in_broad_phase_layer_filter)
            : broad_phase_layer_filter,
        in_object_layer_filter
            ? *static_cast<const JPH::ObjectLayerFilter *>(in_object_layer_filter)
            : object_layer_filter,
        in_body_filter
            ? *static_cast<const JPH::BodyFilter *>(in_body_filter)
            : body_filter);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_NarrowPhaseQuery_CollectCastRay(const JPC_NarrowPhaseQuery *in_query,
                                    const JPC_RRayCast *in_ray,
                                    const JPC_RayCastSettings *in_settings,
                                    void *io_collector,
                                    const void *in_broad_phase_layer_filter,
                                    const void *in_object_layer_filter,
                                    const void *in_body_filter,
                                    const void *in_shape_filter)
{
    auto collector = static_cast<JPH::CastRayCollector *>(io_collector);
    collector->Reset();
    const JPH::BroadPhaseLayerFilter broad_phase_layer_filter{};
    const JPH::ObjectLayerFilter object_layer_filter{};
    const JPH::BodyFilter body_filter{};
    const JPH::ShapeFilter shape_filter{};

    toJph(in_query)->CastRay(
            *toJph(in_ray),
            *toJph(in_settings),
            *collector,
            in_broad_phase_layer_filter
                ? *static_cast<const JPH::BroadPhaseLayerFilter *>(in_broad_phase_layer_filter)
                : broad_phase_layer_filter,
            in_object_layer_filter
                ? *static_cast<const JPH::ObjectLayerFilter *>(in_object_layer_filter)
                : object_layer_filter,
            in_body_filter
                ? *static_cast<const JPH::BodyFilter *>(in_body_filter)
                : body_filter);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_NarrowPhaseQuery_CollidePoint(const JPC_NarrowPhaseQuery *in_query,
                                  const JPC_Real in_point[3],
                                  void *io_collector,
                                  const void *in_broad_phase_layer_filter,
                                  const void *in_object_layer_filter,
                                  const void *in_body_filter,
                                  const void *in_shape_filter)
{
    auto collector = static_cast<JPH::CollidePointCollector *>(io_collector);
    collector->Reset();
    const JPH::BroadPhaseLayerFilter broad_phase_layer_filter{};
    const JPH::ObjectLayerFilter object_layer_filter{};
    const JPH::BodyFilter body_filter{};
    const JPH::ShapeFilter shape_filter{};

    toJph(in_query)->CollidePoint(
            loadRVec3(in_point),
            *collector,
            in_broad_phase_layer_filter
                ? *static_cast<const JPH::BroadPhaseLayerFilter *>(in_broad_phase_layer_filter)
                : broad_phase_layer_filter,
            in_object_layer_filter
                ? *static_cast<const JPH::ObjectLayerFilter *>(in_object_layer_filter)
                : object_layer_filter,
            in_body_filter
                ? *static_cast<const JPH::BodyFilter *>(in_body_filter)
                : body_filter);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_NarrowPhaseQuery_CollideShape(const JPC_NarrowPhaseQuery *in_query,
                                  const JPC_Shape *in_shape,
                                  const float in_shape_scale[3],
                                  const float in_com_rotation[9],
                                  const JPC_Real in_com_translation[3],
                                  const JPC_CollideShapeSettings *in_settings,
                                  const JPC_Real in_base_offset[3],
                                  void *io_collector,
                                  const void *in_broad_phase_layer_filter,
                                  const void *in_object_layer_filter,
                                  const void *in_body_filter,
                                  const void *in_shape_filter)
{
    auto collector = static_cast<JPH::CollideShapeCollector *>(io_collector);
    collector->Reset();
    const JPH::BroadPhaseLayerFilter broad_phase_layer_filter{};
    const JPH::ObjectLayerFilter object_layer_filter{};
    const JPH::BodyFilter body_filter{};
    const JPH::ShapeFilter shape_filter{};

    toJph(in_query)->CollideShape(
            toJph(in_shape),
            loadVec3(in_shape_scale),
            loadRMat44(in_com_rotation, in_com_translation),
            *toJph(in_settings),
            loadRVec3(in_base_offset),
            *collector,
            in_broad_phase_layer_filter
                ? *static_cast<const JPH::BroadPhaseLayerFilter *>(in_broad_phase_layer_filter)
                : broad_phase_layer_filter,
            in_object_layer_filter
                ? *static_cast<const JPH::ObjectLayerFilter *>(in_object_layer_filter)
                : object_layer_filter,
            in_body_filter
                ? *static_cast<const JPH::BodyFilter *>(in_body_filter)
                : body_filter);
}
//--------------------------------------------------------------------------------------------------
// TODO
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_NarrowPhaseQuery_CollectTransformedShapes(const JPC_NarrowPhaseQuery *in_query,
                                              const JPC_AABox *in_box,
                                              void *io_collector,
                                              const void *in_broad_phase_layer_filter,
                                              const void *in_object_layer_filter,
                                              const void *in_body_filter,
                                              const void *in_shape_filter)
{
    auto collector = static_cast<JPH::TransformedShapeCollector *>(io_collector);
    collector->Reset();
    const JPH::BroadPhaseLayerFilter broad_phase_layer_filter{};
    const JPH::ObjectLayerFilter object_layer_filter{};
    const JPH::BodyFilter body_filter{};
    const JPH::ShapeFilter shape_filter{};

    toJph(in_query)->CollectTransformedShapes(
            *toJph(in_box),
            *collector,
            in_broad_phase_layer_filter
                ? *static_cast<const JPH::BroadPhaseLayerFilter *>(in_broad_phase_layer_filter)
                : broad_phase_layer_filter,
            in_object_layer_filter
                ? *static_cast<const JPH::ObjectLayerFilter *>(in_object_layer_filter)
                : object_layer_filter,
            in_body_filter
                ? *static_cast<const JPH::BodyFilter *>(in_body_filter)
                : body_filter);
}
//--------------------------------------------------------------------------------------------------
//
// JPC_SphereShape (-> JPC_ConvexShape -> JPC_Shape)
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_SphereShape *
JPC_SphereShape_Create(float in_radius, const JPC_PhysicsMaterial *in_material)
{
    auto result = new JPH::SphereShape(in_radius, toJph(in_material));
    result->AddRef();
    return toJpc(result);
}

JPC_API float
JPC_SphereShape_GetRadius(const JPC_SphereShape *in_shape)
{
    return toJph(in_shape)->GetRadius();
}
//--------------------------------------------------------------------------------------------------
//
// JPC_BoxShape (-> JPC_ConvexShape -> JPC_Shape)
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_BoxShape *
JPC_BoxShape_Create(const float in_half_extent[3], float in_convex_radius, const JPC_PhysicsMaterial *in_material)
{
    auto result = new JPH::BoxShape(loadVec3(in_half_extent), in_convex_radius, toJph(in_material));
    result->AddRef();
    return toJpc(result);
}

JPC_API void
JPC_BoxShape_GetHalfExtent(const JPC_BoxShape *in_shape, float out_half_extent[3])
{
    storeVec3(out_half_extent, toJph(in_shape)->GetHalfExtent());
}
//--------------------------------------------------------------------------------------------------
//
// JPC_TriangleShape (-> JPC_ConvexShape -> JPC_Shape)
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_TriangleShape *
JPC_TriangleShape_Create(const float in_v1[3],
                         const float in_v2[3],
                         const float in_v3[3],
                         float in_convex_radius,
                         const JPC_PhysicsMaterial *in_material)
{
    auto result = new JPH::TriangleShape(loadVec3(in_v1), loadVec3(in_v2), loadVec3(in_v3), in_convex_radius, toJph(in_material));
    result->AddRef();
    return toJpc(result);
}

JPC_API float
JPC_TriangleShape_GetConvexRadius(const JPC_TriangleShape *in_shape)
{
    return toJph(in_shape)->GetConvexRadius();
}
//--------------------------------------------------------------------------------------------------
//
// JPC_CapsuleShape (-> JPC_ConvexShape -> JPC_Shape)
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_CapsuleShape *
JPC_CapsuleShape_Create(float in_half_height_of_cylinder,
                        float in_radius,
                        const JPC_PhysicsMaterial *in_material)
{
    auto result = new JPH::CapsuleShape(in_half_height_of_cylinder, in_radius, toJph(in_material));
    result->AddRef();
    return toJpc(result);
}

JPC_API float
JPC_CapsuleShape_GetRadius(const JPC_CapsuleShape *in_shape)
{
    return toJph(in_shape)->GetRadius();
}

JPC_API float
JPC_CapsuleShape_GetHalfHeightOfCylinder(const JPC_CapsuleShape *in_shape)
{
    return toJph(in_shape)->GetHalfHeightOfCylinder();
}
//--------------------------------------------------------------------------------------------------
//
// JPC_CylinderShape (-> JPC_ConvexShape -> JPC_Shape)
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_CylinderShape *
JPC_CylinderShape_Create(float in_half_height,
                         float in_radius,
                         float in_convex_radius,
                         const JPC_PhysicsMaterial *in_material)
{
    auto result = new JPH::CylinderShape(in_half_height, in_radius, in_convex_radius, toJph(in_material));
    result->AddRef();
    return toJpc(result);
}

JPC_API float
JPC_CylinderShape_GetHalfHeight(const JPC_CylinderShape *in_shape)
{
    return toJph(in_shape)->GetHalfHeight();
}

JPC_API float
JPC_CylinderShape_GetRadius(const JPC_CylinderShape *in_shape)
{
    return toJph(in_shape)->GetRadius();

}
//--------------------------------------------------------------------------------------------------
//
// JPC_ShapeSettings
//
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_ShapeSettings_AddRef(JPC_ShapeSettings *in_settings)
{
    toJph(in_settings)->AddRef();
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_ShapeSettings_Release(JPC_ShapeSettings *in_settings)
{
    toJph(in_settings)->Release();
}
//--------------------------------------------------------------------------------------------------
JPC_API uint32_t
JPC_ShapeSettings_GetRefCount(const JPC_ShapeSettings *in_settings)
{
    return toJph(in_settings)->GetRefCount();
}
//--------------------------------------------------------------------------------------------------
JPC_API JPC_ShapeResult
JPC_ShapeSettings_CreateShape(const JPC_ShapeSettings *in_settings)
{
    const auto result = toJph(in_settings)->Create();
    return toJpc(result);
}
//--------------------------------------------------------------------------------------------------
JPC_API uint64_t
JPC_ShapeSettings_GetUserData(const JPC_ShapeSettings *in_settings)
{
    return toJph(in_settings)->mUserData;
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_ShapeSettings_SetUserData(JPC_ShapeSettings *in_settings, uint64_t in_user_data)
{
    toJph(in_settings)->mUserData = in_user_data;
}
//--------------------------------------------------------------------------------------------------
//
// JPC_ConvexShapeSettings (-> JPC_ShapeSettings)
//
//--------------------------------------------------------------------------------------------------
JPC_API const JPC_PhysicsMaterial *
JPC_ConvexShapeSettings_GetMaterial(const JPC_ConvexShapeSettings *in_settings)
{
    // TODO: Increment ref count?
    return toJpc(toJph(in_settings)->mMaterial.GetPtr());
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_ConvexShapeSettings_SetMaterial(JPC_ConvexShapeSettings *in_settings,
                                    const JPC_PhysicsMaterial *in_material)
{
    toJph(in_settings)->mMaterial = toJph(in_material);
}
//--------------------------------------------------------------------------------------------------
JPC_API float
JPC_ConvexShapeSettings_GetDensity(const JPC_ConvexShapeSettings *in_settings)
{
    return toJph(in_settings)->mDensity;
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_ConvexShapeSettings_SetDensity(JPC_ConvexShapeSettings *in_settings, float in_density)
{
    toJph(in_settings)->SetDensity(in_density);
}
//--------------------------------------------------------------------------------------------------
//
// JPC_BoxShapeSettings (-> JPC_ConvexShapeSettings -> JPC_ShapeSettings)
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_BoxShapeSettings *
JPC_BoxShapeSettings_Create(const float in_half_extent[3], float in_convex_radius, const JPC_PhysicsMaterial *in_material)
{
    auto settings = new JPH::BoxShapeSettings(loadVec3(in_half_extent), in_convex_radius, toJph(in_material));
    settings->AddRef();
    return toJpc(settings);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BoxShapeSettings_GetHalfExtent(const JPC_BoxShapeSettings *in_settings, float out_half_extent[3])
{
    storeVec3(out_half_extent, toJph(in_settings)->mHalfExtent);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BoxShapeSettings_SetHalfExtent(JPC_BoxShapeSettings *in_settings, const float in_half_extent[3])
{
    toJph(in_settings)->mHalfExtent = loadVec3(in_half_extent);
}
//--------------------------------------------------------------------------------------------------
JPC_API float
JPC_BoxShapeSettings_GetConvexRadius(const JPC_BoxShapeSettings *in_settings)
{
    return toJph(in_settings)->mConvexRadius;
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BoxShapeSettings_SetConvexRadius(JPC_BoxShapeSettings *in_settings, float in_convex_radius)
{
    toJph(in_settings)->mConvexRadius = in_convex_radius;
}
//--------------------------------------------------------------------------------------------------
//
// JPC_SphereShapeSettings (-> JPC_ConvexShapeSettings -> JPC_ShapeSettings)
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_SphereShapeSettings *
JPC_SphereShapeSettings_Create(float in_radius, const JPC_PhysicsMaterial *in_material)
{
    auto settings = new JPH::SphereShapeSettings(in_radius, toJph(in_material));
    settings->AddRef();
    return toJpc(settings);
}
//--------------------------------------------------------------------------------------------------
JPC_API float
JPC_SphereShapeSettings_GetRadius(const JPC_SphereShapeSettings *in_settings)
{
    return toJph(in_settings)->mRadius;
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_SphereShapeSettings_SetRadius(JPC_SphereShapeSettings *in_settings, float in_radius)
{
    toJph(in_settings)->mRadius = in_radius;
}
//--------------------------------------------------------------------------------------------------
//
// JPC_TriangleShapeSettings (-> JPC_ConvexShapeSettings -> JPC_ShapeSettings)
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_TriangleShapeSettings *
JPC_TriangleShapeSettings_Create(const float in_v1[3],
                                 const float in_v2[3],
                                 const float in_v3[3],
                                 float in_convex_radius,
                                 const JPC_PhysicsMaterial *in_material)
{
    auto settings = new JPH::TriangleShapeSettings(loadVec3(in_v1), loadVec3(in_v2), loadVec3(in_v3), in_convex_radius, toJph(in_material));
    settings->AddRef();
    return toJpc(settings);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_TriangleShapeSettings_SetVertices(JPC_TriangleShapeSettings *in_settings,
                                      const float in_v1[3],
                                      const float in_v2[3],
                                      const float in_v3[3])
{
    JPH::TriangleShapeSettings *settings = toJph(in_settings);
    settings->mV1 = loadVec3(in_v1);
    settings->mV2 = loadVec3(in_v2);
    settings->mV3 = loadVec3(in_v3);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_TriangleShapeSettings_GetVertices(const JPC_TriangleShapeSettings *in_settings,
                                      float out_v1[3],
                                      float out_v2[3],
                                      float out_v3[3])
{
    const JPH::TriangleShapeSettings *settings = toJph(in_settings);
    storeVec3(out_v1, settings->mV1);
    storeVec3(out_v2, settings->mV2);
    storeVec3(out_v3, settings->mV3);
}
//--------------------------------------------------------------------------------------------------
JPC_API float
JPC_TriangleShapeSettings_GetConvexRadius(const JPC_TriangleShapeSettings *in_settings)
{
    return toJph(in_settings)->mConvexRadius;
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_TriangleShapeSettings_SetConvexRadius(JPC_TriangleShapeSettings *in_settings, float in_convex_radius)
{
    toJph(in_settings)->mConvexRadius = in_convex_radius;
}
//--------------------------------------------------------------------------------------------------
//
// JPC_CapsuleShapeSettings (-> JPC_ConvexShapeSettings -> JPC_ShapeSettings)
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_CapsuleShapeSettings *
JPC_CapsuleShapeSettings_Create(float in_half_height_of_cylinder,
                                float in_radius,
                                const JPC_PhysicsMaterial *in_material)
{
    auto settings = new JPH::CapsuleShapeSettings(in_half_height_of_cylinder, in_radius, toJph(in_material));
    settings->AddRef();
    return toJpc(settings);
}
//--------------------------------------------------------------------------------------------------
JPC_API float
JPC_CapsuleShapeSettings_GetHalfHeight(const JPC_CapsuleShapeSettings *in_settings)
{
    return toJph(in_settings)->mHalfHeightOfCylinder;
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_CapsuleShapeSettings_SetHalfHeight(JPC_CapsuleShapeSettings *in_settings,
                                       float in_half_height_of_cylinder)
{
    toJph(in_settings)->mHalfHeightOfCylinder = in_half_height_of_cylinder;
}
//--------------------------------------------------------------------------------------------------
JPC_API float
JPC_CapsuleShapeSettings_GetRadius(const JPC_CapsuleShapeSettings *in_settings)
{
    return toJph(in_settings)->mRadius;
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_CapsuleShapeSettings_SetRadius(JPC_CapsuleShapeSettings *in_settings, float in_radius)
{
    toJph(in_settings)->mRadius = in_radius;
}
//--------------------------------------------------------------------------------------------------
//
// JPC_TaperedCapsuleShapeSettings (-> JPC_ConvexShapeSettings -> JPC_ShapeSettings)
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_TaperedCapsuleShapeSettings *
JPC_TaperedCapsuleShapeSettings_Create(float in_half_height,
                                       float in_top_radius,
                                       float in_bottom_radius,
                                       const JPC_PhysicsMaterial *in_material)
{
    auto settings = new JPH::TaperedCapsuleShapeSettings(in_half_height, in_top_radius, in_bottom_radius, toJph(in_material));
    settings->AddRef();
    return toJpc(settings);
}
//--------------------------------------------------------------------------------------------------
JPC_API float
JPC_TaperedCapsuleShapeSettings_GetHalfHeight(const JPC_TaperedCapsuleShapeSettings *in_settings)
{
    return toJph(in_settings)->mHalfHeightOfTaperedCylinder;
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_TaperedCapsuleShapeSettings_SetHalfHeight(JPC_TaperedCapsuleShapeSettings *in_settings,
                                              float in_half_height)
{
    toJph(in_settings)->mHalfHeightOfTaperedCylinder = in_half_height;
}
//--------------------------------------------------------------------------------------------------
JPC_API float
JPC_TaperedCapsuleShapeSettings_GetTopRadius(const JPC_TaperedCapsuleShapeSettings *in_settings)
{
    return toJph(in_settings)->mTopRadius;
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_TaperedCapsuleShapeSettings_SetTopRadius(JPC_TaperedCapsuleShapeSettings *in_settings, float in_top_radius)
{
    toJph(in_settings)->mTopRadius = in_top_radius;
}
//--------------------------------------------------------------------------------------------------
JPC_API float
JPC_TaperedCapsuleShapeSettings_GetBottomRadius(const JPC_TaperedCapsuleShapeSettings *in_settings)
{
    return toJph(in_settings)->mBottomRadius;
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_TaperedCapsuleShapeSettings_SetBottomRadius(JPC_TaperedCapsuleShapeSettings *in_settings,
                                                float in_bottom_radius)
{
    toJph(in_settings)->mBottomRadius = in_bottom_radius;
}
//--------------------------------------------------------------------------------------------------
//
// JPC_CylinderShapeSettings (-> JPC_ConvexShapeSettings -> JPC_ShapeSettings)
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_CylinderShapeSettings *
JPC_CylinderShapeSettings_Create(float in_half_height,
                                 float in_radius,
                                 float in_convex_radius,
                                 const JPC_PhysicsMaterial *in_material)
{
    auto settings = new JPH::CylinderShapeSettings(in_half_height, in_radius, in_convex_radius, toJph(in_material));
    settings->AddRef();
    return toJpc(settings);
}
//--------------------------------------------------------------------------------------------------
JPC_API float
JPC_CylinderShapeSettings_GetConvexRadius(const JPC_CylinderShapeSettings *in_settings)
{
    return toJph(in_settings)->mConvexRadius;
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_CylinderShapeSettings_SetConvexRadius(JPC_CylinderShapeSettings *in_settings, float in_convex_radius)
{
    toJph(in_settings)->mConvexRadius = in_convex_radius;
}
//--------------------------------------------------------------------------------------------------
JPC_API float
JPC_CylinderShapeSettings_GetHalfHeight(const JPC_CylinderShapeSettings *in_settings)
{
    return toJph(in_settings)->mHalfHeight;
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_CylinderShapeSettings_SetHalfHeight(JPC_CylinderShapeSettings *in_settings, float in_half_height)
{
    toJph(in_settings)->mHalfHeight = in_half_height;
}
//--------------------------------------------------------------------------------------------------
JPC_API float
JPC_CylinderShapeSettings_GetRadius(const JPC_CylinderShapeSettings *in_settings)
{
    return toJph(in_settings)->mRadius;
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_CylinderShapeSettings_SetRadius(JPC_CylinderShapeSettings *in_settings, float in_radius)
{
    toJph(in_settings)->mRadius = in_radius;
}
//--------------------------------------------------------------------------------------------------
//
// JPC_ConvexHullShapeSettings (-> JPC_ConvexShapeSettings -> JPC_ShapeSettings)
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_ConvexHullShapeSettings *
JPC_ConvexHullShapeSettings_Create(const void *in_vertices,
                                   uint32_t in_num_vertices,
                                   uint32_t in_vertex_size,
                                   float in_convex_radius,
                                   const JPC_PhysicsMaterial *in_material)
{
    assert(in_vertices && in_num_vertices >= 3);
    assert(in_vertex_size >= 3 * sizeof(float));

    JPH::Array<JPH::Vec3> points;
    points.reserve(in_num_vertices);

    for (uint32_t i = 0; i < in_num_vertices; ++i)
    {
        const uint8_t *base = static_cast<const uint8_t *>(in_vertices) + i * in_vertex_size;
        points.push_back(loadVec3(reinterpret_cast<const float *>(base)));
    }

    auto settings = new JPH::ConvexHullShapeSettings(points, in_convex_radius, toJph(in_material));
    settings->AddRef();
    return toJpc(settings);
}
//--------------------------------------------------------------------------------------------------
JPC_API float
JPC_ConvexHullShapeSettings_GetMaxConvexRadius(const JPC_ConvexHullShapeSettings *in_settings)
{
    return toJph(in_settings)->mMaxConvexRadius;
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_ConvexHullShapeSettings_SetMaxConvexRadius(JPC_ConvexHullShapeSettings *in_settings,
                                               float in_max_convex_radius)
{
    toJph(in_settings)->mMaxConvexRadius = in_max_convex_radius;
}
//--------------------------------------------------------------------------------------------------
JPC_API float
JPC_ConvexHullShapeSettings_GetMaxErrorConvexRadius(const JPC_ConvexHullShapeSettings *in_settings)
{
    return toJph(in_settings)->mMaxErrorConvexRadius;
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_ConvexHullShapeSettings_SetMaxErrorConvexRadius(JPC_ConvexHullShapeSettings *in_settings,
                                                    float in_max_err_convex_radius)
{
    toJph(in_settings)->mMaxErrorConvexRadius = in_max_err_convex_radius;
}
//--------------------------------------------------------------------------------------------------
JPC_API float
JPC_ConvexHullShapeSettings_GetHullTolerance(const JPC_ConvexHullShapeSettings *in_settings)
{
    return toJph(in_settings)->mHullTolerance;
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_ConvexHullShapeSettings_SetHullTolerance(JPC_ConvexHullShapeSettings *in_settings,
                                             float in_hull_tolerance)
{
    toJph(in_settings)->mHullTolerance = in_hull_tolerance;
}
//--------------------------------------------------------------------------------------------------
//
// JPC_HeightFieldShapeSettings (-> JPC_ShapeSettings)
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_HeightFieldShapeSettings *
JPC_HeightFieldShapeSettings_Create(const float *in_samples,
                                    uint32_t in_height_field_size,
                                    float in_offset[3],
                                    float in_scale[3],
                                    const uint8_t *in_material_indices,
                                    const JPC_PhysicsMaterial **in_materials,
                                    uint32_t in_num_materials)
{
    assert(in_samples != nullptr && in_height_field_size >= 2);

    JPH::PhysicsMaterialList materials;
    materials.reserve(in_num_materials);

    for (uint32_t i = 0; i < in_num_materials; ++i)
    {
        materials.emplace_back(toJph(in_materials[i]));
    }

    auto settings = new JPH::HeightFieldShapeSettings(
        in_samples,
        loadVec3(in_offset),
        loadVec3(in_scale),
        in_height_field_size,
        in_material_indices,
        materials
    );
    settings->AddRef();
    return toJpc(settings);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_HeightFieldShapeSettings_GetOffset(const JPC_HeightFieldShapeSettings *in_settings, float out_offset[3])
{
    storeVec3(out_offset, toJph(in_settings)->mOffset);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_HeightFieldShapeSettings_SetOffset(JPC_HeightFieldShapeSettings *in_settings, const float in_offset[3])
{
    toJph(in_settings)->mOffset = loadVec3(in_offset);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_HeightFieldShapeSettings_GetScale(const JPC_HeightFieldShapeSettings *in_settings, float out_scale[3])
{
    storeVec3(out_scale, toJph(in_settings)->mScale);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_HeightFieldShapeSettings_SetScale(JPC_HeightFieldShapeSettings *in_settings, const float in_scale[3])
{
    toJph(in_settings)->mScale = loadVec3(in_scale);
}
//--------------------------------------------------------------------------------------------------
JPC_API uint32_t
JPC_HeightFieldShapeSettings_GetBlockSize(const JPC_HeightFieldShapeSettings *in_settings)
{
    return toJph(in_settings)->mBlockSize;
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_HeightFieldShapeSettings_SetBlockSize(JPC_HeightFieldShapeSettings *in_settings, uint32_t in_block_size)
{
    toJph(in_settings)->mBlockSize = in_block_size;
}
//--------------------------------------------------------------------------------------------------
JPC_API uint32_t
JPC_HeightFieldShapeSettings_GetBitsPerSample(const JPC_HeightFieldShapeSettings *in_settings)
{
    return toJph(in_settings)->mBitsPerSample;
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_HeightFieldShapeSettings_SetBitsPerSample(JPC_HeightFieldShapeSettings *in_settings, uint32_t in_num_bits)
{
    toJph(in_settings)->mBitsPerSample = in_num_bits;
}
//--------------------------------------------------------------------------------------------------
//
// JPC_MeshShapeSettings (-> JPC_ShapeSettings)
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_MeshShapeSettings *
JPC_MeshShapeSettings_Create(const void *in_vertices,
                             uint32_t in_num_vertices,
                             uint32_t in_vertex_size,
                             const uint32_t *in_indices,
                             uint32_t in_num_indices,
                             const JPC_PhysicsMaterial **in_materials,
                             uint32_t in_num_materials)
{
    assert(in_vertices && in_indices);
    assert(in_num_vertices >= 3);
    assert(in_vertex_size >= 3 * sizeof(float));
    assert(in_num_indices >= 4 && in_num_indices % 4 == 0);

    JPH::VertexList vertices;
    vertices.reserve(in_num_vertices);

    for (uint32_t i = 0; i < in_num_vertices; ++i)
    {
        const auto *base = reinterpret_cast<const float *>(
            static_cast<const uint8_t *>(in_vertices) + i * in_vertex_size);
        vertices.emplace_back(base[0], base[1], base[2]);
    }

    JPH::IndexedTriangleList triangles;
    triangles.reserve(in_num_indices / 3);

    for (uint32_t i = 0; i < in_num_indices / 3; ++i)
    {
        triangles.emplace_back(
            in_indices[i * 3], in_indices[i * 3 + 1], in_indices[i * 3 + 2], in_indices[i * 3 + 3]);
    }

    JPH::PhysicsMaterialList materials;
    materials.reserve(in_num_materials);

    for (uint32_t i = 0; i < in_num_materials; ++i)
    {
        materials.emplace_back(toJph(in_materials[i]));
    }

    auto settings = new JPH::MeshShapeSettings(vertices, triangles, materials);
    settings->AddRef();
    return toJpc(settings);
}
//--------------------------------------------------------------------------------------------------
JPC_API uint32_t
JPC_MeshShapeSettings_GetMaxTrianglesPerLeaf(const JPC_MeshShapeSettings *in_settings)
{
    return toJph(in_settings)->mMaxTrianglesPerLeaf;
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_MeshShapeSettings_SetMaxTrianglesPerLeaf(JPC_MeshShapeSettings *in_settings, uint32_t in_max_triangles)
{
    toJph(in_settings)->mMaxTrianglesPerLeaf = in_max_triangles;
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_MeshShapeSettings_Sanitize(JPC_MeshShapeSettings *in_settings)
{
    toJph(in_settings)->Sanitize();
}
//--------------------------------------------------------------------------------------------------
//
// JPC_CompoundShapeSettings (-> JPC_ShapeSettings)
//
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_CompoundShapeSettings_AddShapeSettings(JPC_CompoundShapeSettings *in_settings,
                                           const float in_position[3],
                                           const float in_rotation[4],
                                           const JPC_ShapeSettings *in_shape,
                                           uint32_t in_user_data)
{
    toJph(in_settings)->AddShape(loadVec3(in_position), loadQuat(in_rotation), toJph(in_shape), in_user_data);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_CompoundShapeSettings_AddShape(JPC_CompoundShapeSettings *in_settings,
                                   const float in_position[3],
                                   const float in_rotation[4],
                                   const JPC_Shape *in_shape,
                                   uint32_t in_user_data)
{
    toJph(in_settings)->AddShape(loadVec3(in_position), loadQuat(in_rotation), toJph(in_shape), in_user_data);
}
//--------------------------------------------------------------------------------------------------
//
// JPC_StaticCompoundShapeSettings (-> JPC_CompoundShapeSettings -> JPC_ShapeSettings)
//

//--------------------------------------------------------------------------------------------------
JPC_API JPC_StaticCompoundShapeSettings *
JPC_StaticCompoundShapeSettings_Create()
{
    auto settings = new JPH::StaticCompoundShapeSettings();
    settings->AddRef();
    return toJpc(settings);
}

JPC_API JPC_ShapeResult
JPC_StaticCompoundShapeSettings_CreateShape(const JPC_StaticCompoundShapeSettings *in_settings,
                                            JPC_TempAllocator *in_temp_allocator)
{
    auto result = toJph(in_settings)->Create(*toJph(in_temp_allocator));
    return toJpc(result);
}
//--------------------------------------------------------------------------------------------------
//
// JPC_MutableCompoundShapeSettings (-> JPC_CompoundShapeSettings -> JPC_ShapeSettings)
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_MutableCompoundShapeSettings *
JPC_MutableCompoundShapeSettings_Create()
{
    auto settings = new JPH::MutableCompoundShapeSettings();
    settings->AddRef();
    return toJpc(settings);
}
//--------------------------------------------------------------------------------------------------
//
// JPC_DecoratedShapeSettings (-> JPC_ShapeSettings)
//
//--------------------------------------------------------------------------------------------------
JPC_API const JPC_ShapeSettings *
JPC_DecoratedShapeSettings_GetInnerShape(const JPC_DecoratedShapeSettings *in_settings)
{
    return toJpc(toJph(in_settings)->mInnerShape);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_DecoratedShapeSettings_SetInnerShape(JPC_DecoratedShapeSettings *in_settings, const JPC_ShapeSettings *in_shape)
{
    toJph(in_settings)->mInnerShape = toJph(in_shape);
}
//--------------------------------------------------------------------------------------------------
JPC_API const JPC_Shape *
JPC_DecoratedShapeSettings_GetInnerShapePtr(const JPC_DecoratedShapeSettings *in_settings)
{
    return toJpc(toJph(in_settings)->mInnerShapePtr);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_DecoratedShapeSettings_SetInnerShapePtr(JPC_DecoratedShapeSettings *in_settings, const JPC_Shape *in_shape)
{
    toJph(in_settings)->mInnerShapePtr = toJph(in_shape);
}
//--------------------------------------------------------------------------------------------------
//
// JPC_ScaledShapeSettings (-> JPC_DecoratedShapeSettings -> JPC_ShapeSettings)
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_ScaledShapeSettings *
JPC_ScaledShapeSettings_CreateFromShape(const JPC_Shape *in_shape, const float in_scale[3])
{
    auto settings = new JPH::ScaledShapeSettings(toJph(in_shape), loadVec3(in_scale));
    settings->AddRef();
    return toJpc(settings);
}
//--------------------------------------------------------------------------------------------------
JPC_API JPC_ScaledShapeSettings *
JPC_ScaledShapeSettings_CreateFromSettings(const JPC_ShapeSettings *in_shape, const float in_scale[3])
{
    auto settings = new JPH::ScaledShapeSettings(toJph(in_shape), loadVec3(in_scale));
    settings->AddRef();
    return toJpc(settings);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_ScaledShapeSettings_GetScale(const JPC_ScaledShapeSettings *in_settings, float out_scale[3])
{
    storeVec3(out_scale, toJph(in_settings)->mScale);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_ScaledShapeSettings_SetScale(JPC_ScaledShapeSettings *in_settings, const float in_scale[3])
{
    toJph(in_settings)->mScale = loadVec3(in_scale);
}
//--------------------------------------------------------------------------------------------------
//
// JPC_RotatedTranslatedShapeSettings (-> JPC_DecoratedShapeSettings -> JPC_ShapeSettings)
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_RotatedTranslatedShapeSettings *
JPC_RotatedTranslatedShapeSettings_CreateFromShape(const JPC_Shape *in_shape,
                                                   const float in_position[3],
                                                   const float in_rotation[4])
{
    auto settings = new JPH::RotatedTranslatedShapeSettings(loadVec3(in_position), loadQuat(in_rotation), toJph(in_shape));
    settings->AddRef();
    return toJpc(settings);
}
//--------------------------------------------------------------------------------------------------
JPC_API JPC_RotatedTranslatedShapeSettings *
JPC_RotatedTranslatedShapeSettings_CreateFromSettings(const JPC_ShapeSettings *in_shape,
                                                      const float in_position[3],
                                                      const float in_rotation[4])
{
    auto settings = new JPH::RotatedTranslatedShapeSettings(loadVec3(in_position), loadQuat(in_rotation), toJph(in_shape));
    settings->AddRef();
    return toJpc(settings);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_RotatedTranslatedShapeSettings_GetPosition(const JPC_RotatedTranslatedShapeSettings *in_settings, float out_position[3])
{
    storeVec3(out_position, toJph(in_settings)->mPosition);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_RotatedTranslatedShapeSettings_SetPosition(JPC_RotatedTranslatedShapeSettings *in_settings, const float in_position[3])
{
    toJph(in_settings)->mPosition = loadVec3(in_position);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_RotatedTranslatedShapeSettings_GetRotation(const JPC_RotatedTranslatedShapeSettings *in_settings, float out_rotation[4])
{
    storeQuat(out_rotation, toJph(in_settings)->mRotation);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_RotatedTranslatedShapeSettings_SetRotation(JPC_RotatedTranslatedShapeSettings *in_settings, const float in_rotation[4])
{
    toJph(in_settings)->mRotation = loadQuat(in_rotation);
}
//--------------------------------------------------------------------------------------------------
//
// JPC_OffsetCenterOfMassShapeSettings (-> JPC_DecoratedShapeSettings -> JPC_ShapeSettings)
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_OffsetCenterOfMassShapeSettings *
JPC_OffsetCenterOfMassShapeSettings_CreateFromShape(const JPC_Shape *in_shape, const float in_offset[3])
{
    auto settings = new JPH::OffsetCenterOfMassShapeSettings(loadVec3(in_offset), toJph(in_shape));
    settings->AddRef();
    return toJpc(settings);
}
//--------------------------------------------------------------------------------------------------
JPC_API JPC_OffsetCenterOfMassShapeSettings *
JPC_OffsetCenterOfMassShapeSettings_CreateFromSettings(const JPC_ShapeSettings *in_shape, const float in_offset[3])
{
    auto settings = new JPH::OffsetCenterOfMassShapeSettings(loadVec3(in_offset), toJph(in_shape));
    settings->AddRef();
    return toJpc(settings);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_OffsetCenterOfMassShapeSettings_GetOffset(const JPC_OffsetCenterOfMassShapeSettings *in_settings, float out_offset[3])
{
    storeVec3(out_offset, toJph(in_settings)->mOffset);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_OffsetCenterOfMassShapeSettings_SetOffset(JPC_OffsetCenterOfMassShapeSettings *in_settings, const float in_offset[3])
{
    toJph(in_settings)->mOffset = loadVec3(in_offset);
}
//--------------------------------------------------------------------------------------------------
//
// JPC_Shape
//
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_Shape_AddRef(JPC_Shape *in_shape)
{
    toJph(in_shape)->AddRef();
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_Shape_Release(JPC_Shape *in_shape)
{
    toJph(in_shape)->Release();
}
//--------------------------------------------------------------------------------------------------
JPC_API uint32_t
JPC_Shape_GetRefCount(const JPC_Shape *in_shape)
{
    return toJph(in_shape)->GetRefCount();
}
//--------------------------------------------------------------------------------------------------
JPC_API JPC_ShapeType
JPC_Shape_GetType(const JPC_Shape *in_shape)
{
    return toJpc(toJph(in_shape)->GetType());
}
//--------------------------------------------------------------------------------------------------
JPC_API JPC_ShapeSubType
JPC_Shape_GetSubType(const JPC_Shape *in_shape)
{
    return toJpc(toJph(in_shape)->GetSubType());
}
//--------------------------------------------------------------------------------------------------
JPC_API uint64_t
JPC_Shape_GetUserData(const JPC_Shape *in_shape)
{
    return toJph(in_shape)->GetUserData();
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_Shape_SetUserData(JPC_Shape *in_shape, uint64_t in_user_data)
{
    return toJph(in_shape)->SetUserData(in_user_data);
}
//--------------------------------------------------------------------------------------------------
JPC_API bool
JPC_Shape_MustBeStatic(const JPC_Shape *in_shape)
{
    return toJph(in_shape)->MustBeStatic();
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_Shape_GetCenterOfMass(const JPC_Shape *in_shape, float out_center_of_mass[3])
{
    storeVec3(out_center_of_mass, toJph(in_shape)->GetCenterOfMass());
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_Shape_GetLocalBounds(const JPC_Shape *in_shape, JPC_AABox *out_local_bounds)
{
    const auto out = toJph(in_shape)->GetLocalBounds();
    *out_local_bounds = *toJpc(&out);
}
//--------------------------------------------------------------------------------------------------
JPC_API uint32_t
JPC_Shape_GetSubShapeIDBitsRecursive(const JPC_Shape *in_shape)
{
    return toJph(in_shape)->GetSubShapeIDBitsRecursive();
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_Shape_GetWorldSpaceBounds(const JPC_Shape *in_shape,
                              const float in_center_of_mass_transform[16],
                              const float in_scale[3],
                              JPC_AABox *out_world_space_bounds)
{
    const auto out = toJph(in_shape)->GetWorldSpaceBounds(
            loadMat44(in_center_of_mass_transform),
            loadVec3(in_scale)
    );
    *out_world_space_bounds = *toJpc(&out);
}
//--------------------------------------------------------------------------------------------------
JPC_API float
JPC_Shape_GetInnerRadius(const JPC_Shape *in_shape)
{
    return toJph(in_shape)->GetInnerRadius();
}
//--------------------------------------------------------------------------------------------------
JPC_API const JPC_PhysicsMaterial *
JPC_Shape_GetMaterial(const JPC_Shape *in_shape, JPC_SubShapeID in_sub_shape_id)
{
    return toJpc(toJph(in_shape)->GetMaterial(subShapeIDJph(in_sub_shape_id)));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_Shape_GetSurfaceNormal(const JPC_Shape *in_shape,
                           JPC_SubShapeID in_sub_shape_id,
                           const float in_local_surface_position[3],
                           float out_surface_normal[3])
{
    storeVec3(out_surface_normal, toJph(in_shape)->GetSurfaceNormal(subShapeIDJph(in_sub_shape_id), loadVec3(in_local_surface_position)));
}
//--------------------------------------------------------------------------------------------------

// TODO GetSupportingFace

//--------------------------------------------------------------------------------------------------
JPC_API uint64_t
JPC_Shape_GetSubShapeUserData(const JPC_Shape *in_shape, JPC_SubShapeID in_sub_shape_id)
{
    return toJph(in_shape)->GetSubShapeUserData(subShapeIDJph(in_sub_shape_id));
}
//--------------------------------------------------------------------------------------------------
JPC_API JPC_TransformedShape
JPC_Shape_GetSubShapeTransformedShape(const JPC_Shape *in_shape,
                                      JPC_SubShapeID in_sub_shape_id,
                                      const float in_position_com[3],
                                      const float in_rotation[4],
                                      const float in_scale[3])
{
    JPH::SubShapeID outRemainder;
    auto result = toJph(in_shape)->GetSubShapeTransformedShape(subShapeIDJph(in_sub_shape_id), loadVec3(in_position_com), loadQuat(in_rotation), loadVec3(in_scale), outRemainder);
    return *toJpc(&result);
}
//--------------------------------------------------------------------------------------------------
JPC_API float
JPC_Shape_GetVolume(const JPC_Shape *in_shape)
{
    return toJph(in_shape)->GetVolume();
}
//--------------------------------------------------------------------------------------------------
JPC_API bool
JPC_Shape_IsValidScale(const JPC_Shape *in_shape, const float in_scale[3])
{
    return toJph(in_shape)->IsValidScale(loadVec3(in_scale));
}
//--------------------------------------------------------------------------------------------------
//
// JPC_ConvexShape (-> JPC_Shape)
//
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_ConvexShape_SetMaterial(JPC_ConvexShape *in_shape, const JPC_PhysicsMaterial *in_material)
{
    toJph(in_shape)->SetMaterial(toJph(in_material));
}
//--------------------------------------------------------------------------------------------------
JPC_API const JPC_PhysicsMaterial *
JPC_ConvexShape_GetMaterial(const JPC_ConvexShape *in_shape)
{
    return toJpc(toJph(in_shape)->GetMaterial());
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_ConvexShape_SetDensity(JPC_ConvexShape *in_shape, float in_density)
{
    return toJph(in_shape)->SetDensity(in_density);
}
//--------------------------------------------------------------------------------------------------
JPC_API float
JPC_ConvexShape_GetDensity(const JPC_ConvexShape *in_shape)
{
    return toJph(in_shape)->GetDensity();
}
//--------------------------------------------------------------------------------------------------
//
// JPC_DecoratedShape (-> JPC_Shape)
//
//--------------------------------------------------------------------------------------------------
JPC_API const JPC_Shape *
JPC_DecoratedShape_GetInnerShape(const JPC_DecoratedShape *in_shape)
{
    return toJpc(toJph(in_shape)->GetInnerShape());
}
//--------------------------------------------------------------------------------------------------
//
// JPC_ScaledShape (-> JPC_DecoratedShape -> JPC_Shape)
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_ScaledShape *
JPC_ScaledShape_Create(const JPC_Shape *in_shape, const float in_scale[3]) {
    auto result = new JPH::ScaledShape(toJph(in_shape), loadVec3(in_scale));
    result->AddRef();
    return toJpc(result);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_ScaledShape_GetScale(const JPC_ScaledShape *in_shape, float out_scale[3])
{
    storeVec3(out_scale, toJph(in_shape)->GetScale());
}
//--------------------------------------------------------------------------------------------------
//
// JPC_RotatedTranslatedShape (-> JPC_DecoratedShape -> JPC_Shape)
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_RotatedTranslatedShape *
JPC_RotatedTranslatedShape_Create(const JPC_Shape *in_shape, const float in_position[3], const float in_rotation[4])
{
    auto result = new JPH::RotatedTranslatedShape(loadVec3(in_position), loadQuat(in_rotation), toJph(in_shape));
    result->AddRef();
    return toJpc(result);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_RotatedTranslatedShape_GetPosition(const JPC_RotatedTranslatedShape *in_shape, float out_position[3])
{
    storeVec3(out_position, toJph(in_shape)->GetPosition());
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_RotatedTranslatedShape_GetRotation(const JPC_RotatedTranslatedShape *in_shape, float out_rotation[4])
{
    storeQuat(out_rotation, toJph(in_shape)->GetRotation());
}
//--------------------------------------------------------------------------------------------------
//
// JPC_OffsetCenterOfMassShape (-> JPC_DecoratedShape -> JPC_Shape)
//
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_OffsetCenterOfMassShape_GetOffset(const JPC_OffsetCenterOfMassShape *in_shape, float out_offset[3])
{
    storeVec3(out_offset, toJph(in_shape)->GetOffset());
}
//--------------------------------------------------------------------------------------------------
//
// JPC_BodyInterface
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_Body *
JPC_BodyInterface_CreateBody(JPC_BodyInterface *in_iface, const JPC_BodyCreationSettings *in_settings)
{
    return toJpc(toJph(in_iface)->CreateBody(*toJph(in_settings)));
}
//--------------------------------------------------------------------------------------------------
JPC_API JPC_Body *
JPC_BodyInterface_CreateBodyWithID(JPC_BodyInterface *in_iface,
                                   JPC_BodyID in_body_id,
                                   const JPC_BodyCreationSettings *in_settings)
{
    return toJpc(toJph(in_iface)->CreateBodyWithID(toJph(in_body_id), *toJph(in_settings)));
}
//--------------------------------------------------------------------------------------------------
JPC_API JPC_Body *
JPC_BodyInterface_CreateBodyWithoutID(JPC_BodyInterface *in_iface,
                                      const JPC_BodyCreationSettings *in_settings)
{
    return toJpc(toJph(in_iface)->CreateBodyWithoutID(*toJph(in_settings)));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_DestroyBodyWithoutID(JPC_BodyInterface *in_iface, JPC_Body *in_body)
{
    toJph(in_iface)->DestroyBodyWithoutID(toJph(in_body));
}
//--------------------------------------------------------------------------------------------------
JPC_API bool
JPC_BodyInterface_AssignNextBodyID(JPC_BodyInterface *in_iface, JPC_Body *in_body)
{
    return toJph(in_iface)->AssignBodyID(toJph(in_body));
}
//--------------------------------------------------------------------------------------------------
JPC_API bool
JPC_BodyInterface_AssignBodyID(JPC_BodyInterface *in_iface, JPC_Body *in_body, JPC_BodyID in_body_id)
{
    return toJph(in_iface)->AssignBodyID(toJph(in_body), toJph(in_body_id));
}
//--------------------------------------------------------------------------------------------------
JPC_API JPC_Body *
JPC_BodyInterface_UnassignBodyID(JPC_BodyInterface *in_iface, JPC_BodyID in_body_id)
{
    return toJpc(toJph(in_iface)->UnassignBodyID(toJph(in_body_id)));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_UnassignBodyIDs(JPC_BodyInterface *in_iface,
                                  const JPC_BodyID *in_body_ids,
                                  int in_number,
                                  JPC_Body **out_bodies)
{
    toJph(in_iface)->UnassignBodyIDs(
            toJph(in_body_ids),
            in_number,
            reinterpret_cast<JPH::Body **>(out_bodies)
    );
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_DestroyBody(JPC_BodyInterface *in_iface, JPC_BodyID in_body_id)
{
    toJph(in_iface)->DestroyBody(toJph(in_body_id));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_DestroyBodies(JPC_BodyInterface *in_iface, const JPC_BodyID *in_body_ids, int in_number)
{
    toJph(in_iface)->DestroyBodies(toJph(in_body_ids), in_number);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_AddBody(JPC_BodyInterface *in_iface, JPC_BodyID in_body_id, JPC_Activation in_mode)
{
    toJph(in_iface)->AddBody(toJph(in_body_id), activationToJph(in_mode));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_RemoveBody(JPC_BodyInterface *in_iface, JPC_BodyID in_body_id)
{
    toJph(in_iface)->RemoveBody(toJph(in_body_id));
}
//--------------------------------------------------------------------------------------------------
JPC_API bool
JPC_BodyInterface_IsAdded(const JPC_BodyInterface *in_iface, JPC_BodyID in_body_id)
{
    return toJph(in_iface)->IsAdded(toJph(in_body_id));
}
//--------------------------------------------------------------------------------------------------
JPC_API JPC_BodyID
JPC_BodyInterface_CreateAndAddBody(JPC_BodyInterface *in_iface,
                                   const JPC_BodyCreationSettings *in_settings,
                                   JPC_Activation in_mode)
{
    return toJpc(toJph(in_iface)->CreateAndAddBody(*toJph(in_settings),
        static_cast<JPH::EActivation>(in_mode)));
}
//--------------------------------------------------------------------------------------------------
JPC_API JPC_BodyInterfaceAddState
JPC_BodyInterface_AddBodiesPrepare(JPC_BodyInterface *in_iface, JPC_BodyID *io_bodies, int in_number)
{
    return toJph(in_iface)->AddBodiesPrepare(toJph(io_bodies), in_number);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_AddBodiesFinalize(JPC_BodyInterface *in_iface,
                                    JPC_BodyID *io_bodies,
                                    int in_number,
                                    JPC_BodyInterfaceAddState *in_add_state,
                                    JPC_Activation in_activation_mode)
{
    toJph(in_iface)->AddBodiesFinalize(
            toJph(io_bodies),
            in_number,
            in_add_state,
            activationToJph(in_activation_mode)
    );
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_AddBodiesAbort(JPC_BodyInterface *in_iface,
                                 JPC_BodyID *io_bodies,
                                 int in_number,
                                 JPC_BodyInterfaceAddState *in_add_state)
{
    toJph(in_iface)->AddBodiesAbort(toJph(io_bodies), in_number, in_add_state);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_RemoveBodies(JPC_BodyInterface *in_iface,
                               JPC_BodyID *io_bodies,
                               int in_number)
{
    toJph(in_iface)->RemoveBodies(toJph(io_bodies), in_number);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_ActivateBody(JPC_BodyInterface *in_iface, JPC_BodyID in_body_id)
{
    toJph(in_iface)->ActivateBody(toJph(in_body_id));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_ActivateBodies(JPC_BodyInterface *in_iface,
                                 const JPC_BodyID *in_body_ids,
                                 int in_number)
{
    toJph(in_iface)->ActivateBodies(toJph(in_body_ids), in_number);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_DeactivateBody(JPC_BodyInterface *in_iface, JPC_BodyID in_body_id)
{
    toJph(in_iface)->DeactivateBody(toJph(in_body_id));
}
//--------------------------------------------------------------------------------------------------
JPC_API bool
JPC_BodyInterface_IsActive(const JPC_BodyInterface *in_iface, JPC_BodyID in_body_id)
{
    return toJph(in_iface)->IsActive(toJph(in_body_id));
}
//--------------------------------------------------------------------------------------------------
JPC_API const JPC_Shape *
JPC_BodyInterface_GetShape(const JPC_BodyInterface *in_iface, JPC_BodyID in_body_id)
{
    auto result = toJph(in_iface)->GetShape(toJph(in_body_id));
    result->AddRef();
    return toJpc(result);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_SetShape(const JPC_BodyInterface *in_iface,
                           JPC_BodyID in_body_id,
                           const JPC_Shape *in_shape,
                           bool in_update_mass_properties,
                           JPC_Activation in_activation_mode)
{
    toJph(in_iface)->SetShape(
            toJph(in_body_id),
            toJph(in_shape),
            in_update_mass_properties,
            activationToJph(in_activation_mode)
    );
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_NotifyShapeChanged(const JPC_BodyInterface *in_iface,
                                     JPC_BodyID in_body_id,
                                     const float in_previous_center_of_mass[3],
                                     bool in_update_mass_properties,
                                     JPC_Activation in_activation_mode)
{
    toJph(in_iface)->NotifyShapeChanged(
            toJph(in_body_id),
            loadVec3(in_previous_center_of_mass),
            in_update_mass_properties,
            activationToJph(in_activation_mode)
    );
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_SetObjectLayer(JPC_BodyInterface *in_iface, JPC_BodyID in_body_id, JPC_ObjectLayer in_layer)
{
    toJph(in_iface)->SetObjectLayer(toJph(in_body_id), objectLayerToJph(in_layer));
}
//--------------------------------------------------------------------------------------------------
JPC_API JPC_ObjectLayer
JPC_BodyInterface_GetObjectLayer(const JPC_BodyInterface *in_iface, JPC_BodyID in_body_id)
{
    return toJph(in_iface)->GetObjectLayer(toJph(in_body_id));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_SetPositionAndRotation(JPC_BodyInterface *in_iface,
                                         JPC_BodyID in_body_id,
                                         const JPC_Real in_position[3],
                                         const float in_rotation[4],
                                         JPC_Activation in_activation_mode)
{
    toJph(in_iface)->SetPositionAndRotation(
            toJph(in_body_id),
            loadRVec3(in_position),
            loadQuat(in_rotation),
            activationToJph(in_activation_mode)
    );
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_SetPositionAndRotationWhenChanged(JPC_BodyInterface *in_iface,
                                                    JPC_BodyID in_body_id,
                                                    const JPC_Real in_position[3],
                                                    const float in_rotation[4],
                                                    JPC_Activation in_activation_mode)
{
    toJph(in_iface)->SetPositionAndRotationWhenChanged(
            toJph(in_body_id),
            loadRVec3(in_position),
            loadQuat(in_rotation),
            activationToJph(in_activation_mode)
    );
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_GetPositionAndRotation(const JPC_BodyInterface *in_iface,
                                         JPC_BodyID in_body_id,
                                         JPC_Real out_position[3],
                                         float out_rotation[4])
{
    JPH::RVec3 position;
    JPH::Quat rotation;
    toJph(in_iface)->GetPositionAndRotation(toJph(in_body_id), position, rotation);
    storeRVec3(out_position, position);
    storeQuat(out_rotation, rotation);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_SetPosition(JPC_BodyInterface *in_iface,
                              JPC_BodyID in_body_id,
                              const JPC_Real in_position[3],
                              JPC_Activation in_activation_mode)
{
    toJph(in_iface)->SetPosition(toJph(in_body_id), loadRVec3(in_position), activationToJph(in_activation_mode));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_GetPosition(const JPC_BodyInterface *in_iface,
                              JPC_BodyID in_body_id,
                              JPC_Real out_position[3])
{
    auto result = toJph(in_iface)->GetPosition(toJph(in_body_id));
    storeRVec3(out_position, result);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_GetCenterOfMassPosition(const JPC_BodyInterface *in_iface,
                                          JPC_BodyID in_body_id,
                                          JPC_Real out_position[3])
{
    auto result = toJph(in_iface)->GetCenterOfMassPosition(toJph(in_body_id));
    storeRVec3(out_position, result);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_SetRotation(JPC_BodyInterface *in_iface,
                              JPC_BodyID in_body_id,
                              const float in_rotation[4],
                              JPC_Activation in_activation_mode)
{
    toJph(in_iface)->SetRotation(toJph(in_body_id), loadQuat(in_rotation), activationToJph(in_activation_mode));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_GetRotation(const JPC_BodyInterface *in_iface,
                              JPC_BodyID in_body_id,
                              float out_rotation[4])
{
    auto result = toJph(in_iface)->GetRotation(toJph(in_body_id));
    storeQuat(out_rotation, result);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_GetWorldTransform(const JPC_BodyInterface *in_iface,
                                    JPC_BodyID in_body_id,
                                    float out_rotation[9],
                                    JPC_Real out_translation[3])
{
    auto result = toJph(in_iface)->GetWorldTransform(toJph(in_body_id));
    storeRMat44(out_rotation, out_translation, result);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_GetCenterOfMassTransform(const JPC_BodyInterface *in_iface,
                                           JPC_BodyID in_body_id,
                                           float out_rotation[9],
                                           JPC_Real out_translation[3])
{
    auto result = toJph(in_iface)->GetCenterOfMassTransform(toJph(in_body_id));
    storeRMat44(out_rotation, out_translation, result);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_MoveKinematic(JPC_BodyInterface *in_iface,
                                JPC_BodyID in_body_id,
                                const JPC_Real in_target_position[3],
                                const float in_target_rotation[4],
                                float in_delta_time)
{
    toJph(in_iface)->MoveKinematic(
            toJph(in_body_id),
            loadRVec3(in_target_position),
            loadQuat(in_target_rotation),
            in_delta_time
    );
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_SetLinearAndAngularVelocity(JPC_BodyInterface *in_iface,
                                              JPC_BodyID in_body_id,
                                              const float in_linear_velocity[3],
                                              const float in_angular_velocity[3])
{
    toJph(in_iface)->SetLinearAndAngularVelocity(
        toJph(in_body_id), loadVec3(in_linear_velocity), loadVec3(in_angular_velocity));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_GetLinearAndAngularVelocity(const JPC_BodyInterface *in_iface,
                                              JPC_BodyID in_body_id,
                                              float out_linear_velocity[3],
                                              float out_angular_velocity[3])
{
    JPH::Vec3 linear, angular;
    toJph(in_iface)->GetLinearAndAngularVelocity(toJph(in_body_id), linear, angular);
    storeVec3(out_linear_velocity, linear);
    storeVec3(out_angular_velocity, angular);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_SetLinearVelocity(JPC_BodyInterface *in_iface,
                                    JPC_BodyID in_body_id,
                                    const float in_velocity[3])
{
    toJph(in_iface)->SetLinearVelocity(toJph(in_body_id), loadVec3(in_velocity));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_GetLinearVelocity(const JPC_BodyInterface *in_iface,
                                    JPC_BodyID in_body_id,
                                    float out_velocity[3])
{
    storeVec3(out_velocity, toJph(in_iface)->GetLinearVelocity(toJph(in_body_id)));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_AddLinearVelocity(JPC_BodyInterface *in_iface,
                                    JPC_BodyID in_body_id,
                                    const float in_velocity[3])
{
    toJph(in_iface)->AddLinearVelocity(toJph(in_body_id), loadVec3(in_velocity));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_AddLinearAndAngularVelocity(JPC_BodyInterface *in_iface,
                                              JPC_BodyID in_body_id,
                                              const float in_linear_velocity[3],
                                              const float in_angular_velocity[3])
{
    toJph(in_iface)->AddLinearAndAngularVelocity(
        toJph(in_body_id), loadVec3(in_linear_velocity), loadVec3(in_angular_velocity));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_SetAngularVelocity(JPC_BodyInterface *in_iface,
                                     JPC_BodyID in_body_id,
                                     const float in_velocity[3])
{
    toJph(in_iface)->SetAngularVelocity(toJph(in_body_id), loadVec3(in_velocity));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_GetAngularVelocity(const JPC_BodyInterface *in_iface,
                                     JPC_BodyID in_body_id,
                                     float out_velocity[3])
{
    storeVec3(out_velocity, toJph(in_iface)->GetAngularVelocity(toJph(in_body_id)));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_GetPointVelocity(const JPC_BodyInterface *in_iface,
                                   JPC_BodyID in_body_id,
                                   const JPC_Real in_point[3],
                                   float out_velocity[3])
{
    storeVec3(out_velocity, toJph(in_iface)->GetPointVelocity(toJph(in_body_id), loadRVec3(in_point)));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_SetPositionRotationAndVelocity(JPC_BodyInterface *in_iface,
                                                 JPC_BodyID in_body_id,
                                                 const JPC_Real in_position[3],
                                                 const float in_rotation[4],
                                                 const float in_linear_velocity[3],
                                                 const float in_angular_velocity[3])
{
    toJph(in_iface)->SetPositionRotationAndVelocity(
        toJph(in_body_id),
        loadRVec3(in_position),
        JPH::Quat(loadVec4(in_rotation)),
        loadVec3(in_linear_velocity),
        loadVec3(in_angular_velocity));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_AddForce(JPC_BodyInterface *in_iface, JPC_BodyID in_body_id, const float in_force[3])
{
    toJph(in_iface)->AddForce(toJph(in_body_id), loadVec3(in_force));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_AddForceAtPosition(JPC_BodyInterface *in_iface,
                                     JPC_BodyID in_body_id,
                                     const float in_force[3],
                                     const JPC_Real in_position[3])
{
    toJph(in_iface)->AddForce(toJph(in_body_id), loadVec3(in_force), loadRVec3(in_position));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_AddTorque(JPC_BodyInterface *in_iface, JPC_BodyID in_body_id, const float in_torque[3])
{
    toJph(in_iface)->AddTorque(toJph(in_body_id), loadVec3(in_torque));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_AddForceAndTorque(JPC_BodyInterface *in_iface,
                                    JPC_BodyID in_body_id,
                                    const float in_force[3],
                                    const float in_torque[3])
{
    toJph(in_iface)->AddForceAndTorque(toJph(in_body_id), loadVec3(in_force), loadVec3(in_torque));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_AddImpulse(JPC_BodyInterface *in_iface, JPC_BodyID in_body_id, const float in_impulse[3])
{
    toJph(in_iface)->AddImpulse(toJph(in_body_id), loadVec3(in_impulse));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_AddImpulseAtPosition(JPC_BodyInterface *in_iface,
                                       JPC_BodyID in_body_id,
                                       const float in_impulse[3],
                                       const JPC_Real in_position[3])
{
    toJph(in_iface)->AddImpulse(toJph(in_body_id), loadVec3(in_impulse), loadRVec3(in_position));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_AddAngularImpulse(JPC_BodyInterface *in_iface, JPC_BodyID in_body_id, const float in_impulse[3])
{
    toJph(in_iface)->AddAngularImpulse(toJph(in_body_id), loadVec3(in_impulse));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_SetMotionType(JPC_BodyInterface *in_iface,
                                JPC_BodyID in_body_id,
                                JPC_MotionType in_motion_type,
                                JPC_Activation in_activation_mode)
{
    toJph(in_iface)->SetMotionType(
            toJph(in_body_id), motionTypeToJph(in_motion_type), activationToJph(in_activation_mode));
}
//--------------------------------------------------------------------------------------------------
JPC_API JPC_MotionType
JPC_BodyInterface_GetMotionType(const JPC_BodyInterface *in_iface,
                                JPC_BodyID in_body_id)
{
    return toJpc(toJph(in_iface)->GetMotionType(toJph(in_body_id)));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_SetMotionQuality(JPC_BodyInterface *in_iface,
                                   JPC_BodyID in_body_id,
                                   JPC_MotionQuality in_motion_quality)
{
    toJph(in_iface)->SetMotionQuality(toJph(in_body_id), motionQualityToJph(in_motion_quality));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_GetInverseInertia(const JPC_BodyInterface *in_iface,
                                    JPC_BodyID in_body_id,
                                    float out_inverse_inertia[16])
{
    auto result = toJph(in_iface)->GetInverseInertia(toJph(in_body_id));
    storeMat44(out_inverse_inertia, result);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_SetRestitution(JPC_BodyInterface *in_iface,
                                 JPC_BodyID in_body_id,
                                 float in_restitution)
{
    toJph(in_iface)->SetRestitution(toJph(in_body_id), in_restitution);
}
//--------------------------------------------------------------------------------------------------
JPC_API float
JPC_BodyInterface_GetRestitution(const JPC_BodyInterface *in_iface,
                                 JPC_BodyID in_body_id)
{
    return toJph(in_iface)->GetRestitution(toJph(in_body_id));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_SetFriction(JPC_BodyInterface *in_iface,
                              JPC_BodyID in_body_id,
                              float in_friction)
{
    toJph(in_iface)->SetFriction(toJph(in_body_id), in_friction);
}
//--------------------------------------------------------------------------------------------------
JPC_API float
JPC_BodyInterface_GetFriction(const JPC_BodyInterface *in_iface,
                              JPC_BodyID in_body_id)
{
    return toJph(in_iface)->GetFriction(toJph(in_body_id));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_SetGravityFactor(JPC_BodyInterface *in_iface,
                                   JPC_BodyID in_body_id,
                                   float in_gravity_factor)
{
    toJph(in_iface)->SetGravityFactor(toJph(in_body_id), in_gravity_factor);
}
//--------------------------------------------------------------------------------------------------
JPC_API float
JPC_BodyInterface_GetGravityFactor(const JPC_BodyInterface *in_iface,
                                   JPC_BodyID in_body_id)
{
    return toJph(in_iface)->GetGravityFactor(toJph(in_body_id));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_GetTransformedShape(const JPC_BodyInterface *in_iface,
                                      JPC_BodyID in_body_id,
                                      JPC_TransformedShape *out_shape)
{
    auto out = toJph(in_iface)->GetTransformedShape(toJph(in_body_id));
    *out_shape = *toJpc(&out);
}
//--------------------------------------------------------------------------------------------------
JPC_API uint64_t
JPC_BodyInterface_GetUserData(const JPC_BodyInterface *in_iface,
                              JPC_BodyID in_body_id)
{
    return toJph(in_iface)->GetUserData(toJph(in_body_id));
}
//--------------------------------------------------------------------------------------------------
JPC_API const JPC_PhysicsMaterial *
JPC_BodyInterface_GetMaterial(const JPC_BodyInterface *in_iface,
                              JPC_BodyID in_body_id,
                              JPC_SubShapeID in_sub_shape_id)
{
    return toJpc(toJph(in_iface)->GetMaterial(toJph(in_body_id), subShapeIDJph(in_sub_shape_id)));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyInterface_InvalidateContactCache(JPC_BodyInterface *in_iface,
                                         JPC_BodyID in_body_id)
{
    toJph(in_iface)->InvalidateContactCache(toJph(in_body_id));
}
//--------------------------------------------------------------------------------------------------
//
// JPC_Body
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_BodyID
JPC_Body_GetID(const JPC_Body *in_body)
{
    return toJph(in_body)->GetID().GetIndexAndSequenceNumber();
}
//--------------------------------------------------------------------------------------------------
JPC_API bool
JPC_Body_IsActive(const JPC_Body *in_body)
{
    return toJph(in_body)->IsActive();
}
//--------------------------------------------------------------------------------------------------
JPC_API bool
JPC_Body_IsStatic(const JPC_Body *in_body)
{
    return toJph(in_body)->IsStatic();
}
//--------------------------------------------------------------------------------------------------
JPC_API bool
JPC_Body_IsKinematic(const JPC_Body *in_body)
{
    return toJph(in_body)->IsKinematic();
}
//--------------------------------------------------------------------------------------------------
JPC_API bool
JPC_Body_IsDynamic(const JPC_Body *in_body)
{
    return toJph(in_body)->IsDynamic();
}
//--------------------------------------------------------------------------------------------------
JPC_API bool
JPC_Body_CanBeKinematicOrDynamic(const JPC_Body *in_body)
{
    return toJph(in_body)->CanBeKinematicOrDynamic();
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_Body_SetIsSensor(JPC_Body *in_body, bool in_is_sensor)
{
    toJph(in_body)->SetIsSensor(in_is_sensor);
}
//--------------------------------------------------------------------------------------------------
JPC_API bool
JPC_Body_IsSensor(const JPC_Body *in_body)
{
    return toJph(in_body)->IsSensor();
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_Body_SetUseManifoldReduction(JPC_Body *in_body, bool in_use_reduction)
{
    toJph(in_body)->SetUseManifoldReduction(in_use_reduction);
}
//--------------------------------------------------------------------------------------------------
JPC_API bool
JPC_Body_GetUseManifoldReduction(const JPC_Body *in_body)
{
    return toJph(in_body)->GetUseManifoldReduction();
}
//--------------------------------------------------------------------------------------------------
JPC_API bool
JPC_Body_GetUseManifoldReductionWithBody(const JPC_Body *in_body, const JPC_Body *in_other)
{
    return toJph(in_body)->GetUseManifoldReductionWithBody(*toJph(in_other));
}
//--------------------------------------------------------------------------------------------------
JPC_API JPC_MotionType
JPC_Body_GetMotionType(const JPC_Body *in_body)
{
    return toJpc(toJph(in_body)->GetMotionType());
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_Body_SetMotionType(JPC_Body *in_body, JPC_MotionType in_motion_type)
{
    toJph(in_body)->SetMotionType(static_cast<JPH::EMotionType>(in_motion_type));
}
//--------------------------------------------------------------------------------------------------
JPC_API JPC_BroadPhaseLayer
JPC_Body_GetBroadPhaseLayer(const JPC_Body *in_body)
{
    return toJpc(toJph(in_body)->GetBroadPhaseLayer());
}
//--------------------------------------------------------------------------------------------------
JPC_API JPC_ObjectLayer
JPC_Body_GetObjectLayer(const JPC_Body *in_body)
{
    return toJpc(toJph(in_body)->GetObjectLayer());
}
//--------------------------------------------------------------------------------------------------
JPC_API JPC_CollisionGroup *
JPC_Body_GetCollisionGroup(JPC_Body *in_body)
{
    return toJpc(&toJph(in_body)->GetCollisionGroup());
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_Body_SetCollisionGroup(JPC_Body *in_body, const JPC_CollisionGroup *in_group)
{
    toJph(in_body)->SetCollisionGroup(*toJph(in_group));
}
//--------------------------------------------------------------------------------------------------
JPC_API bool
JPC_Body_GetAllowSleeping(const JPC_Body *in_body)
{
    return toJph(in_body)->GetAllowSleeping();
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_Body_SetAllowSleeping(JPC_Body *in_body, bool in_allow)
{
    toJph(in_body)->SetAllowSleeping(in_allow);
}
//--------------------------------------------------------------------------------------------------
JPC_API float
JPC_Body_GetFriction(const JPC_Body *in_body)
{
    return toJph(in_body)->GetFriction();
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_Body_SetFriction(JPC_Body *in_body, float in_friction)
{
    toJph(in_body)->SetFriction(in_friction);
}
//--------------------------------------------------------------------------------------------------
JPC_API float
JPC_Body_GetRestitution(const JPC_Body *in_body)
{
    return toJph(in_body)->GetRestitution();
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_Body_SetRestitution(JPC_Body *in_body, float in_restitution)
{
    toJph(in_body)->SetRestitution(in_restitution);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_Body_GetLinearVelocity(const JPC_Body *in_body, float out_linear_velocity[3])
{
    storeVec3(out_linear_velocity, toJph(in_body)->GetLinearVelocity());
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_Body_SetLinearVelocity(JPC_Body *in_body, const float in_linear_velocity[3])
{
    toJph(in_body)->SetLinearVelocity(loadVec3(in_linear_velocity));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_Body_SetLinearVelocityClamped(JPC_Body *in_body, const float in_linear_velocity[3])
{
    toJph(in_body)->SetLinearVelocityClamped(loadVec3(in_linear_velocity));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_Body_GetAngularVelocity(const JPC_Body *in_body, float out_angular_velocity[3])
{
    storeVec3(out_angular_velocity, toJph(in_body)->GetAngularVelocity());
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_Body_SetAnglularVelocity(JPC_Body *in_body, const float in_angular_velocity[3])
{
    toJph(in_body)->SetAngularVelocity(loadVec3(in_angular_velocity));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_Body_SetAnglularVelocityClamped(JPC_Body *in_body, const float in_angular_velocity[3])
{
    toJph(in_body)->SetAngularVelocityClamped(loadVec3(in_angular_velocity));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_Body_GetPointVelocityCOM(const JPC_Body *in_body,
                             const float in_point_relative_to_com[3],
                             float out_velocity[3])
{
    storeVec3(out_velocity, toJph(in_body)->GetPointVelocityCOM(loadVec3(in_point_relative_to_com)));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_Body_GetPointVelocity(const JPC_Body *in_body, const JPC_Real in_point[3], float out_velocity[3])
{
    storeVec3(out_velocity, toJph(in_body)->GetPointVelocity(loadRVec3(in_point)));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_Body_AddForce(JPC_Body *in_body, const float in_force[3])
{
    toJph(in_body)->AddForce(loadVec3(in_force));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_Body_AddForceAtPosition(JPC_Body *in_body, const float in_force[3], const JPC_Real in_position[3])
{
    toJph(in_body)->AddForce(loadVec3(in_force), loadRVec3(in_position));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_Body_AddTorque(JPC_Body *in_body, const float in_torque[3])
{
    toJph(in_body)->AddTorque(loadVec3(in_torque));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_Body_GetAccumulatedForce(JPC_Body *in_body, float out_force[3])
{
    storeVec3(out_force, toJph(in_body)->GetAccumulatedForce());
}

JPC_API void
JPC_Body_GetAccumulatedTorque(JPC_Body *in_body, float out_torque[3])
{
    storeVec3(out_torque, toJph(in_body)->GetAccumulatedTorque());
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_Body_GetInverseInertia(const JPC_Body *in_body, float out_inverse_inertia[16])
{
    storeMat44(out_inverse_inertia, toJph(in_body)->GetInverseInertia());
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_Body_AddImpulse(JPC_Body *in_body, const float in_impulse[3])
{
    toJph(in_body)->AddImpulse(loadVec3(in_impulse));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_Body_AddImpulseAtPosition(JPC_Body *in_body, const float in_impulse[3], const JPC_Real in_position[3])
{
    toJph(in_body)->AddImpulse(loadVec3(in_impulse), loadRVec3(in_position));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_Body_AddAngularImpulse(JPC_Body *in_body, const float in_angular_impulse[3])
{
    toJph(in_body)->AddAngularImpulse(loadVec3(in_angular_impulse));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_Body_MoveKinematic(JPC_Body *in_body,
                       const JPC_Real in_target_position[3],
                       const float in_target_rotation[4],
                       float in_delta_time)
{
    toJph(in_body)->MoveKinematic(
        loadRVec3(in_target_position), JPH::Quat(loadVec4(in_target_rotation)), in_delta_time);
}
//--------------------------------------------------------------------------------------------------
JPC_API bool
JPC_Body_ApplyBuoyancyImpulse(JPC_Body *in_body,
                              const JPC_Real in_surface_position[3],
                              const float in_surface_normal[3],
                              float in_buoyancy,
                              float in_linear_drag,
                              float in_angular_drag,
                              const float in_fluid_velocity[3],
                              const float in_gravity[3],
                              float in_delta_time)
{
    return toJph(in_body)->ApplyBuoyancyImpulse(
        loadRVec3(in_surface_position),
        loadVec3(in_surface_normal),
        in_buoyancy,
        in_linear_drag,
        in_angular_drag,
        loadVec3(in_fluid_velocity),
        loadVec3(in_gravity),
        in_delta_time);
}
//--------------------------------------------------------------------------------------------------
JPC_API bool
JPC_Body_IsInBroadPhase(const JPC_Body *in_body)
{
    return toJph(in_body)->IsInBroadPhase();
}
//--------------------------------------------------------------------------------------------------
JPC_API bool
JPC_Body_IsCollisionCacheInvalid(const JPC_Body *in_body)
{
    return toJph(in_body)->IsCollisionCacheInvalid();
}
//--------------------------------------------------------------------------------------------------
JPC_API const JPC_Shape *
JPC_Body_GetShape(const JPC_Body *in_body)
{
    return toJpc(toJph(in_body)->GetShape());
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_Body_GetPosition(const JPC_Body *in_body, JPC_Real out_position[3])
{
    storeRVec3(out_position, toJph(in_body)->GetPosition());
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_Body_GetRotation(const JPC_Body *in_body, float out_rotation[4])
{
    storeVec4(out_rotation, toJph(in_body)->GetRotation().GetXYZW());
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_Body_GetWorldTransform(const JPC_Body *in_body, float out_rotation[9], JPC_Real out_translation[3])
{
    const JPH::RMat44 m = toJph(in_body)->GetWorldTransform();
    storeVec3(&out_rotation[0], m.GetColumn3(0));
    storeVec3(&out_rotation[3], m.GetColumn3(1));
    storeVec3(&out_rotation[6], m.GetColumn3(2));
    storeRVec3(&out_translation[0], m.GetTranslation());
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_Body_GetCenterOfMassPosition(const JPC_Body *in_body, JPC_Real out_position[3])
{
    storeRVec3(out_position, toJph(in_body)->GetCenterOfMassPosition());
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_Body_GetCenterOfMassTransform(const JPC_Body *in_body,
                                  float out_rotation[9],
                                  JPC_Real out_translation[3])
{
    const JPH::RMat44 m = toJph(in_body)->GetCenterOfMassTransform();
    storeVec3(&out_rotation[0], m.GetColumn3(0));
    storeVec3(&out_rotation[3], m.GetColumn3(1));
    storeVec3(&out_rotation[6], m.GetColumn3(2));
    storeRVec3(&out_translation[0], m.GetTranslation());
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_Body_GetInverseCenterOfMassTransform(const JPC_Body *in_body,
                                         float out_rotation[9],
                                         JPC_Real out_translation[3])
{
    const JPH::RMat44 m = toJph(in_body)->GetInverseCenterOfMassTransform();
    storeVec3(&out_rotation[0], m.GetColumn3(0));
    storeVec3(&out_rotation[3], m.GetColumn3(1));
    storeVec3(&out_rotation[6], m.GetColumn3(2));
    storeRVec3(&out_translation[0], m.GetTranslation());
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_Body_GetWorldSpaceBounds(const JPC_Body *in_body, float out_min[3], float out_max[3])
{
    const JPH::AABox& aabb = toJph(in_body)->GetWorldSpaceBounds();
    storeVec3(out_min, aabb.mMin);
    storeVec3(out_min, aabb.mMax);
}
//--------------------------------------------------------------------------------------------------
JPC_API JPC_MotionProperties *
JPC_Body_GetMotionProperties(JPC_Body *in_body)
{
    return toJpc(toJph(in_body)->GetMotionProperties());
}
//--------------------------------------------------------------------------------------------------
JPC_API uint64_t
JPC_Body_GetUserData(const JPC_Body *in_body)
{
    return toJph(in_body)->GetUserData();
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_Body_SetUserData(JPC_Body *in_body, uint64_t in_user_data)
{
    toJph(in_body)->SetUserData(in_user_data);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_Body_GetTransformedShape(JPC_Body *in_body, JPC_TransformedShape *out_shape)
{
    auto out = toJph(in_body)->GetTransformedShape();
    *out_shape = *toJpc(&out);
}

JPC_API void
JPC_Body_GetBodyCreationSettings(JPC_Body *in_body, JPC_BodyCreationSettings *out_settings)
{
    auto out = toJph(in_body)->GetBodyCreationSettings();
    *out_settings = *toJpc(&out);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_Body_GetWorldSpaceSurfaceNormal(const JPC_Body *in_body,
                                    JPC_SubShapeID in_sub_shape_id,
                                    const JPC_Real in_position[3],
                                    float out_normal_vector[3])
{
    const JPH::Vec3 v = toJph(in_body)->GetWorldSpaceSurfaceNormal(
            subShapeIDJph(in_sub_shape_id), loadRVec3(in_position));
    storeVec3(out_normal_vector, v);
}
//--------------------------------------------------------------------------------------------------
//
// JPC_MotionProperties
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_MotionQuality
JPC_MotionProperties_GetMotionQuality(const JPC_MotionProperties *in_properties)
{
    return toJpc(toJph(in_properties)->GetMotionQuality());
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_MotionProperties_GetLinearVelocity(const JPC_MotionProperties *in_properties,
                                       float out_linear_velocity[3])
{
    storeVec3(out_linear_velocity, toJph(in_properties)->GetLinearVelocity());
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_MotionProperties_SetLinearVelocity(JPC_MotionProperties *in_properties,
                                       const float in_linear_velocity[3])
{
    toJph(in_properties)->SetLinearVelocity(loadVec3(in_linear_velocity));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_MotionProperties_SetLinearVelocityClamped(JPC_MotionProperties *in_properties,
                                              const float in_linear_velocity[3])
{
    toJph(in_properties)->SetLinearVelocityClamped(loadVec3(in_linear_velocity));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_MotionProperties_GetAngularVelocity(const JPC_MotionProperties *in_properties,
                                        float out_angular_velocity[3])
{
    storeVec3(out_angular_velocity, toJph(in_properties)->GetAngularVelocity());
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_MotionProperties_SetAngularVelocity(JPC_MotionProperties *in_properties,
                                        const float in_angular_velocity[3])
{
    toJph(in_properties)->SetAngularVelocity(loadVec3(in_angular_velocity));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_MotionProperties_SetAngularVelocityClamped(JPC_MotionProperties *in_properties,
                                               const float in_angular_velocity[3])
{
    toJph(in_properties)->SetAngularVelocityClamped(loadVec3(in_angular_velocity));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_MotionProperties_MoveKinematic(JPC_MotionProperties *in_properties,
                                   const float in_delta_position[3],
                                   const float in_delta_rotation[4],
                                   float in_delta_time)
{
    toJph(in_properties)->MoveKinematic(
        loadVec3(in_delta_position), JPH::Quat(loadVec4(in_delta_rotation)), in_delta_time);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_MotionProperties_ClampLinearVelocity(JPC_MotionProperties *in_properties)
{
    toJph(in_properties)->ClampLinearVelocity();
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_MotionProperties_ClampAngularVelocity(JPC_MotionProperties *in_properties)
{
    toJph(in_properties)->ClampAngularVelocity();
}
//--------------------------------------------------------------------------------------------------
JPC_API float
JPC_MotionProperties_GetLinearDamping(const JPC_MotionProperties *in_properties)
{
    return toJph(in_properties)->GetLinearDamping();
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_MotionProperties_SetLinearDamping(JPC_MotionProperties *in_properties,
                                      float in_linear_damping)
{
    toJph(in_properties)->SetLinearDamping(in_linear_damping);
}
//--------------------------------------------------------------------------------------------------
JPC_API float
JPC_MotionProperties_GetAngularDamping(const JPC_MotionProperties *in_properties)
{
    return toJph(in_properties)->GetAngularDamping();
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_MotionProperties_SetAngularDamping(JPC_MotionProperties *in_properties,
                                       float in_angular_damping)
{
    toJph(in_properties)->SetAngularDamping(in_angular_damping);
}
//--------------------------------------------------------------------------------------------------
JPC_API float
JPC_MotionProperties_GetGravityFactor(const JPC_MotionProperties *in_properties)
{
    return toJph(in_properties)->GetGravityFactor();
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_MotionProperties_SetGravityFactor(JPC_MotionProperties *in_properties,
                                      float in_gravity_factor)
{
    toJph(in_properties)->SetGravityFactor(in_gravity_factor);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_MotionProperties_SetMassProperties(JPC_MotionProperties *in_properties,
                                       const JPC_MassProperties *in_mass_properties)
{
    toJph(in_properties)->SetMassProperties(*toJph(in_mass_properties));
}
//--------------------------------------------------------------------------------------------------
JPC_API float
JPC_MotionProperties_GetInverseMass(const JPC_MotionProperties *in_properties)
{
    return toJph(in_properties)->GetInverseMass();
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_MotionProperties_SetInverseMass(JPC_MotionProperties *in_properties, float in_inv_mass)
{
    toJph(in_properties)->SetInverseMass(in_inv_mass);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_MotionProperties_GetInverseInertiaDiagonal(const JPC_MotionProperties *in_properties,
                                               float out_inverse_inertia_diagonal[3])
{
    storeVec3(out_inverse_inertia_diagonal, toJph(in_properties)->GetInverseInertiaDiagonal());
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_MotionProperties_GetInertiaRotation(const JPC_MotionProperties *in_properties,
                                        float out_inertia_rotation[4])
{
    storeVec4(out_inertia_rotation, toJph(in_properties)->GetInertiaRotation().GetXYZW());
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_MotionProperties_SetInverseInertia(JPC_MotionProperties *in_properties,
                                       const float in_diagonal[3],
                                       const float in_rotation[4])
{
    toJph(in_properties)->SetInverseInertia(
        loadVec3(in_diagonal),
        JPH::Quat(loadVec4(in_rotation)));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_MotionProperties_GetLocalSpaceInverseInertia(const JPC_MotionProperties *in_properties,
                                                 float out_matrix[16])
{
    storeMat44(out_matrix, toJph(in_properties)->GetLocalSpaceInverseInertia());
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_MotionProperties_GetInverseInertiaForRotation(const JPC_MotionProperties *in_properties,
                                                  const float in_rotation_matrix[16],
                                                  float out_matrix[16])
{
    storeMat44(out_matrix, toJph(in_properties)->GetInverseInertiaForRotation(loadMat44(in_rotation_matrix)));
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_MotionProperties_MultiplyWorldSpaceInverseInertiaByVector(const JPC_MotionProperties *in_properties,
                                                              const float in_body_rotation[4],
                                                              const float in_vector[3],
                                                              float out_vector[3])
{
    const JPH::Vec3 v = toJph(in_properties)->MultiplyWorldSpaceInverseInertiaByVector(
        JPH::Quat(loadVec4(in_body_rotation)), loadVec3(in_vector));
    storeVec3(out_vector, v);
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_MotionProperties_GetPointVelocityCOM(const JPC_MotionProperties *in_properties,
                                         const float in_point_relative_to_com[3],
                                         float out_point[3])
{
    storeVec3(out_point, toJph(in_properties)->GetPointVelocityCOM(loadVec3(in_point_relative_to_com)));
}
//--------------------------------------------------------------------------------------------------
JPC_API float
JPC_MotionProperties_GetMaxLinearVelocity(const JPC_MotionProperties *in_properties)
{
    return toJph(in_properties)->GetMaxLinearVelocity();
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_MotionProperties_SetMaxLinearVelocity(JPC_MotionProperties *in_properties,
                                          float in_max_linear_velocity)
{
    toJph(in_properties)->SetMaxLinearVelocity(in_max_linear_velocity);
}
//--------------------------------------------------------------------------------------------------
JPC_API float
JPC_MotionProperties_GetMaxAngularVelocity(const JPC_MotionProperties *in_properties)
{
    return toJph(in_properties)->GetMaxAngularVelocity();
}
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_MotionProperties_SetMaxAngularVelocity(JPC_MotionProperties *in_properties,
                                           float in_max_angular_velocity)
{
    toJph(in_properties)->SetMaxAngularVelocity(in_max_angular_velocity);
}
//--------------------------------------------------------------------------------------------------
//
// JPC_BodyID
//
//--------------------------------------------------------------------------------------------------
JPC_API uint32_t
JPC_BodyID_GetIndex(JPC_BodyID in_body_id)
{
    return JPH::BodyID(in_body_id).GetIndex();
}
//--------------------------------------------------------------------------------------------------
JPC_API uint8_t
JPC_BodyID_GetSequenceNumber(JPC_BodyID in_body_id)
{
    return JPH::BodyID(in_body_id).GetSequenceNumber();
}
//--------------------------------------------------------------------------------------------------
JPC_API bool
JPC_BodyID_IsInvalid(JPC_BodyID in_body_id)
{
    return JPH::BodyID(in_body_id).IsInvalid();
}
//--------------------------------------------------------------------------------------------------
//
// JPC_Constraint
//
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_Constraint_Release(JPC_Constraint *in_constraint)
{
    toJph(in_constraint)->Release();
}

JPC_API JPC_ConstraintType
JPC_Constraint_GetType(const JPC_Constraint *in_constraint)
{
    return toJpc(toJph(in_constraint)->GetType());
}

JPC_API JPC_ConstraintSubType
JPC_Constraint_GetSubType(const JPC_Constraint *in_constraint)
{
    return toJpc(toJph(in_constraint)->GetSubType());
}

JPC_API void
JPC_Constraint_SetNumVelocityStepsOverride(JPC_Constraint *in_constraint, int in_num_velocity_steps_override)
{
    toJph(in_constraint)->SetNumVelocityStepsOverride(in_num_velocity_steps_override);
}

JPC_API int
JPC_Constraint_GetNumVelocityStepsOverride(const JPC_Constraint *in_constraint)
{
    return toJph(in_constraint)->GetNumVelocityStepsOverride();
}

JPC_API void
JPC_Constraint_SetNumPositionStepsOverride(JPC_Constraint *in_constraint, int in_num_position_steps_override)
{
    toJph(in_constraint)->SetNumPositionStepsOverride(in_num_position_steps_override);
}

JPC_API int
JPC_Constraint_GetNumPositionStepsOverride(const JPC_Constraint *in_constraint)
{
    return toJph(in_constraint)->GetNumPositionStepsOverride();
}

JPC_API void
JPC_Constraint_SetEnabled(JPC_Constraint *in_constraint, bool in_enabled)
{
    toJph(in_constraint)->SetEnabled(in_enabled);
}

JPC_API bool
JPC_Constraint_GetEnabled(const JPC_Constraint *in_constraint)
{
    return toJph(in_constraint)->GetEnabled();
}
//--------------------------------------------------------------------------------------------------
//
// JPC_TwoBodyConstraint
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_Body *
JPC_TwoBodyConstraint_GetBody1(const JPC_TwoBodyConstraint *in_constraint)
{
    return toJpc(toJph(in_constraint)->GetBody1());
}

JPC_API JPC_Body *
JPC_TwoBodyConstraint_GetBody2(const JPC_TwoBodyConstraint *in_constraint)
{
    return toJpc(toJph(in_constraint)->GetBody2());
}

JPC_API void
JPC_TwoBodyConstraint_GetConstraintToBody1Matrix(const JPC_TwoBodyConstraint *in_constraint, float out_matrix[16])
{
    storeMat44(out_matrix, toJph(in_constraint)->GetConstraintToBody1Matrix());
}

JPC_API void
JPC_TwoBodyConstraint_GetConstraintToBody2Matrix(const JPC_TwoBodyConstraint *in_constraint, float out_matrix[16])
{
    storeMat44(out_matrix, toJph(in_constraint)->GetConstraintToBody2Matrix());
}
//--------------------------------------------------------------------------------------------------
//
// JPC_FixedConstraint
//
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_FixedConstraint_GetTotalLambdaPosition(const JPC_FixedConstraint *in_constraint, float out_lambda[3])
{
    storeVec3(out_lambda, toJph(in_constraint)->GetTotalLambdaPosition());
}

JPC_API void
JPC_FixedConstraint_GetTotalLambdaRotation(const JPC_FixedConstraint *in_constraint, float out_lambda[3])
{
    storeVec3(out_lambda, toJph(in_constraint)->GetTotalLambdaRotation());
}
//--------------------------------------------------------------------------------------------------
//
// JPC_DistanceConstraint
//
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_DistanceConstraint_SetDistance(JPC_DistanceConstraint *in_constraint, float in_min_distance, float in_max_distance)
{
    toJph(in_constraint)->SetDistance(in_min_distance, in_max_distance);
}

JPC_API float
JPC_DistanceConstraint_GetMinDistance(const JPC_DistanceConstraint *in_constraint)
{
    return toJph(in_constraint)->GetMinDistance();
}

JPC_API float
JPC_DistanceConstraint_GetMaxDistance(const JPC_DistanceConstraint *in_constraint)
{
    return toJph(in_constraint)->GetMaxDistance();
}

JPC_API void
JPC_DistanceConstraint_SetFrequency(JPC_DistanceConstraint *in_constraint, float in_frequency)
{
    toJph(in_constraint)->SetFrequency(in_frequency);
}

JPC_API float
JPC_DistanceConstraint_GetFrequency(const JPC_DistanceConstraint *in_constraint)
{
    return toJph(in_constraint)->GetFrequency();
}

JPC_API void
JPC_DistanceConstraint_SetDamping(JPC_DistanceConstraint *in_constraint, float in_damping)
{
    toJph(in_constraint)->SetDamping(in_damping);
}

JPC_API float
JPC_DistanceConstraint_GetDamping(const JPC_DistanceConstraint *in_constraint)
{
    return toJph(in_constraint)->GetDamping();
}

JPC_API float
JPC_DistanceConstraint_GetTotalLambdaPosition(const JPC_DistanceConstraint *in_constraint)
{
    return toJph(in_constraint)->GetTotalLambdaPosition();
}
//--------------------------------------------------------------------------------------------------
//
// JPC_PointConstraint
//
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_PointConstraint_SetPoint1(JPC_PointConstraint *in_constraint, const JPC_ConstraintSpace in_space, const JPC_Real in_point1[3])
{
    toJph(in_constraint)->SetPoint1(constraintSpaceToJph(in_space), loadRVec3(in_point1));
}

JPC_API void
JPC_PointConstraint_SetPoint2(JPC_PointConstraint *in_constraint, const JPC_ConstraintSpace in_space, const JPC_Real in_point2[3])
{
    toJph(in_constraint)->SetPoint2(constraintSpaceToJph(in_space), loadRVec3(in_point2));
}

JPC_API void
JPC_PointConstraint_GetLocalSpacePoint1(const JPC_PointConstraint *in_constraint, float out_point[3])
{
    storeVec3(out_point, toJph(in_constraint)->GetLocalSpacePoint1());
}

JPC_API void
JPC_PointConstraint_GetLocalSpacePoint2(const JPC_PointConstraint *in_constraint, float out_point[3])
{
    storeVec3(out_point, toJph(in_constraint)->GetLocalSpacePoint2());
}

JPC_API void
JPC_PointConstraint_GetTotalLambdaPosition(const JPC_PointConstraint *in_constraint, float out_lambda[3])
{
    storeVec3(out_lambda, toJph(in_constraint)->GetTotalLambdaPosition());
}
//--------------------------------------------------------------------------------------------------
//
// JPC_HingeConstraint
//
//--------------------------------------------------------------------------------------------------
JPC_API float
JPC_HingeConstraint_GetCurrentAngle(const JPC_HingeConstraint *in_constraint)
{
    return toJph(in_constraint)->GetCurrentAngle();
}

JPC_API void
JPC_HingeConstraint_SetMaxFrictionTorque(JPC_HingeConstraint *in_constraint, float in_friction_torque)
{
    toJph(in_constraint)->SetMaxFrictionTorque(in_friction_torque);
}

JPC_API float
JPC_HingeConstraint_GetMaxFrictionTorque(const JPC_HingeConstraint *in_constraint)
{
    return toJph(in_constraint)->GetMaxFrictionTorque();
}

JPC_API JPC_MotorSettings *
JPC_HingeConstraint_GetMotorSettings(JPC_HingeConstraint *in_constraint)
{
    return toJpc(toJph(in_constraint)->GetMotorSettings());
}

JPC_API void
JPC_HingeConstraint_SetMotorState(JPC_HingeConstraint *in_constraint, JPC_MotorState in_state)
{
    toJph(in_constraint)->SetMotorState(motorStateToJph(in_state));
}

JPC_API JPC_MotorState
JPC_HingeConstraint_GetMotorState(const JPC_HingeConstraint *in_constraint)
{
    return toJpc(toJph(in_constraint)->GetMotorState());
}

JPC_API void
JPC_HingeConstraint_SetTargetAngularVelocity(JPC_HingeConstraint *in_constraint, float in_angular_velocity)
{
    toJph(in_constraint)->SetTargetAngularVelocity(in_angular_velocity);
}

JPC_API float
JPC_HingeConstraint_GetTargetAngularVelocity(const JPC_HingeConstraint *in_constraint)
{
    return toJph(in_constraint)->GetTargetAngularVelocity();
}

JPC_API void
JPC_HingeConstraint_SetTargetAngle(JPC_HingeConstraint *in_constraint, float in_angle)
{
    toJph(in_constraint)->SetTargetAngle(in_angle);
}

JPC_API float
JPC_HingeConstraint_GetTargetAngle(const JPC_HingeConstraint *in_constraint)
{
    return toJph(in_constraint)->GetTargetAngle();
}

JPC_API void
JPC_HingeConstraint_SetLimits(JPC_HingeConstraint *in_constraint, float in_limits_min, float in_limits_max)
{
    toJph(in_constraint)->SetLimits(in_limits_min, in_limits_max);
}

JPC_API float
JPC_HingeConstraint_GetLimitsMin(const JPC_HingeConstraint *in_constraint)
{
    return toJph(in_constraint)->GetLimitsMin();
}

JPC_API float
JPC_HingeConstraint_GetLimitsMax(const JPC_HingeConstraint *in_constraint)
{
    return toJph(in_constraint)->GetLimitsMax();
}

JPC_API bool
JPC_HingeConstraint_HasLimits(const JPC_HingeConstraint *in_constraint)
{
    return toJph(in_constraint)->HasLimits();
}

JPC_API void
JPC_HingeConstraint_GetTotalLambdaPosition(const JPC_HingeConstraint *in_constraint, float out_lambda[3])
{
    storeVec3(out_lambda, toJph(in_constraint)->GetTotalLambdaPosition());
}

JPC_API void
JPC_HingeConstraint_GetTotalLambdaRotation(const JPC_HingeConstraint *in_constraint, float *out_x, float *out_y)
{
    auto out = toJph(in_constraint)->GetTotalLambdaRotation();
    *out_x = out[0];
    *out_y = out[1];
}

JPC_API float
JPC_HingeConstraint_GetTotalLambdaRotationLimits(const JPC_HingeConstraint *in_constraint)
{
    return toJph(in_constraint)->GetTotalLambdaRotationLimits();
}

JPC_API float
JPC_HingeConstraint_GetTotalLambdaMotor(const JPC_HingeConstraint *in_constraint)
{
    return toJph(in_constraint)->GetTotalLambdaMotor();
}
//--------------------------------------------------------------------------------------------------
//
// JPC_ConeConstraint
//
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_ConeConstraint_SetHalfConeAngle(JPC_ConeConstraint *in_self, float in_half_cone_angle)
{
    toJph(in_self)->SetHalfConeAngle(in_half_cone_angle);
}

JPC_API float
JPC_ConeConstraint_GetCosHalfConeAngle(const JPC_ConeConstraint *in_self)
{
    return toJph(in_self)->GetCosHalfConeAngle();
}

JPC_API void
JPC_ConeConstraint_GetTotalLambdaPosition(const JPC_ConeConstraint *in_self, float out_lambda[3])
{
    storeVec3(out_lambda, toJph(in_self)->GetTotalLambdaPosition());
}

JPC_API float
JPC_ConeConstraint_GetTotalLambdaRotation(const JPC_ConeConstraint *in_self)
{
    return toJph(in_self)->GetTotalLambdaRotation();
}
//--------------------------------------------------------------------------------------------------
//
// JPC_SliderConstraint
//
//--------------------------------------------------------------------------------------------------
JPC_API float
JPC_SliderConstraint_GetCurrentPosition(const JPC_SliderConstraint *in_self)
{
    return toJph(in_self)->GetCurrentPosition();
}

JPC_API void
JPC_SliderConstraint_SetMaxFrictionForce(JPC_SliderConstraint *in_self, float in_friction)
{
    toJph(in_self)->SetMaxFrictionForce(in_friction);
}

JPC_API JPC_MotorSettings *
JPC_SliderConstraint_GetMotorSettings(JPC_SliderConstraint *in_self)
{
    return toJpc(toJph(in_self)->GetMotorSettings());
}

JPC_API void
JPC_SliderConstraint_SetMotorState(JPC_SliderConstraint *in_self, JPC_MotorState in_state)
{
    toJph(in_self)->SetMotorState(motorStateToJph(in_state));
}

JPC_API JPC_MotorState
JPC_SliderConstraint_GetMotorState(const JPC_SliderConstraint *in_self)
{
    return toJpc(toJph(in_self)->GetMotorState());
}

JPC_API void
JPC_SliderConstraint_SetTargetVelocity(JPC_SliderConstraint *in_self, float in_velocity)
{
    toJph(in_self)->SetTargetVelocity(in_velocity);
}

JPC_API float
JPC_SliderConstraint_GetTargetVelocity(const JPC_SliderConstraint *in_self)
{
    return toJph(in_self)->GetTargetVelocity();
}

JPC_API void
JPC_SliderConstraint_SetTargetPosition(JPC_SliderConstraint *in_self, float in_position)
{
    toJph(in_self)->SetTargetPosition(in_position);
}

JPC_API float
JPC_SliderConstraint_GetTargetPosition(const JPC_SliderConstraint *in_self)
{
    return toJph(in_self)->GetTargetPosition();
}

JPC_API void
JPC_SliderConstraint_SetLimits(JPC_SliderConstraint *in_self, float in_limits_min, float in_limits_max)
{
    toJph(in_self)->SetLimits(in_limits_min, in_limits_max);
}

JPC_API float
JPC_SliderConstraint_GetLimitsMin(const JPC_SliderConstraint *in_self)
{
    return toJph(in_self)->GetLimitsMin();
}

JPC_API float
JPC_SliderConstraint_GetLimitsMax(const JPC_SliderConstraint *in_self)
{
    return toJph(in_self)->GetLimitsMax();
}

JPC_API bool
JPC_SliderConstraint_HasLimits(const JPC_SliderConstraint *in_self)
{
    return toJph(in_self)->HasLimits();
}

JPC_API void
JPC_SliderConstraint_SetFrequency(JPC_SliderConstraint *in_self, float in_frequency)
{
    toJph(in_self)->SetFrequency(in_frequency);
}

JPC_API float
JPC_SliderConstraint_GetFrequency(const JPC_SliderConstraint *in_self)
{
    return toJph(in_self)->GetFrequency();
}
//--------------------------------------------------------------------------------------------------
//
// JPC_MotorSettings
//
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_MotorSettings_SetForceLimits(JPC_MotorSettings *in_settings, float in_min, float in_max)
{
    toJph(in_settings)->SetForceLimits(in_min, in_max);
}

JPC_API void
JPC_MotorSettings_SetTorqueLimits(JPC_MotorSettings *in_settings, float in_min, float in_max)
{
    toJph(in_settings)->SetTorqueLimits(in_min, in_max);
}

JPC_API void
JPC_MotorSettings_SetForceLimit(JPC_MotorSettings *in_settings, float in_limit)
{
    toJph(in_settings)->SetForceLimit(in_limit);
}

JPC_API void
JPC_MotorSettings_SetTorqueLimit(JPC_MotorSettings *in_settings, float in_limit)
{
    toJph(in_settings)->SetTorqueLimit(in_limit);
}

JPC_API bool
JPC_MotorSettings_IsValid(const JPC_MotorSettings *in_settings)
{
    return toJph(in_settings)->IsValid();
}
//--------------------------------------------------------------------------------------------------
// JoltJava: Java support
JPC_API uint32_t
JPJ_GetFeatures()
{
    uint32_t features = 0;
#if JPC_DOUBLE_PRECISION == 1
    features |= 0x1;
#endif
#if JPH_USE_SSE4_1
    features |= 0x2;
#endif
#if JPH_USE_SSE4_2
    features |= 0x4;
#endif
#if JPH_USE_AVX
    features |= 0x8;
#endif
#if JPH_USE_AVX2
    features |= 0x10;
#endif
#if JPH_USE_AVX512
    features |= 0x20;
#endif
#if JPH_USE_LZCNT
    features |= 0x40;
#endif
#if JPH_USE_TZCNT
    features |= 0x80;
#endif
#if JPH_USE_F16C
    features |= 0x100;
#endif
#if JPH_USE_FMADD
    features |= 0x200;
#endif
    return features;
}
// END JoltJava
