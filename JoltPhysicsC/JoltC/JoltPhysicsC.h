// JoltPhysicsC v0.0.4 - C API for Jolt Physics C++ library

#pragma once
#include <stdlib.h>
#include <stdbool.h>
#include <stdint.h>
#include <stdalign.h>
#include <float.h>
//--------------------------------------------------------------------------------------------------
//
// Const
//
//--------------------------------------------------------------------------------------------------
#define JPC_API // TODO: Define this properly

// Always turn on asserts in Debug mode
#if defined(_DEBUG) || defined(JPH_ENABLE_ASSERTS)
    #define JPC_ENABLE_ASSERTS 1
#else
    #define JPC_ENABLE_ASSERTS 0
#endif

#if defined(JPH_DOUBLE_PRECISION)
    #define JPC_DOUBLE_PRECISION 1
#else
    #define JPC_DOUBLE_PRECISION 0
#endif

#if JPC_DOUBLE_PRECISION == 1
typedef double JPC_Real;
#define JPC_RVEC_ALIGN alignas(32)
#else
typedef float JPC_Real;
#define JPC_RVEC_ALIGN alignas(16)
#endif

#define JPC_COLLISION_GROUP_INVALID_GROUP 0xffffffff
#define JPC_COLLISION_GROUP_INVALID_SUB_GROUP 0xffffffff

#define JPC_BODY_ID_INVALID 0xffffffff
#define JPC_BODY_ID_INDEX_BITS 0x007fffff
#define JPC_BODY_ID_SEQUENCE_BITS 0xff000000
#define JPC_BODY_ID_SEQUENCE_SHIFT 24

#define JPC_SUB_SHAPE_ID_EMPTY 0xffffffff

#define JPC_FLT_EPSILON FLT_EPSILON

#ifdef __cplusplus
extern "C" {
#endif

// JPC_JobSystem_Create()
enum
{
    JPC_MAX_PHYSICS_JOBS     = 2048,
    JPC_MAX_PHYSICS_BARRIERS = 8
};

typedef uint8_t JPC_ShapeType;
enum
{
    JPC_SHAPE_TYPE_CONVEX       = 0,
    JPC_SHAPE_TYPE_COMPOUND     = 1,
    JPC_SHAPE_TYPE_DECORATED    = 2,
    JPC_SHAPE_TYPE_MESH         = 3,
    JPC_SHAPE_TYPE_HEIGHT_FIELD = 4,
    JPC_SHAPE_TYPE_USER1        = 5,
    JPC_SHAPE_TYPE_USER2        = 6,
    JPC_SHAPE_TYPE_USER3        = 7,
    JPC_SHAPE_TYPE_USER4        = 8
};

typedef uint8_t JPC_ShapeSubType;
enum
{
    JPC_SHAPE_SUB_TYPE_SPHERE                = 0,
    JPC_SHAPE_SUB_TYPE_BOX                   = 1,
    JPC_SHAPE_SUB_TYPE_TRIANGLE              = 2,
    JPC_SHAPE_SUB_TYPE_CAPSULE               = 3,
    JPC_SHAPE_SUB_TYPE_TAPERED_CAPSULE       = 4,
    JPC_SHAPE_SUB_TYPE_CYLINDER              = 5,
    JPC_SHAPE_SUB_TYPE_CONVEX_HULL           = 6,
    JPC_SHAPE_SUB_TYPE_STATIC_COMPOUND       = 7,
    JPC_SHAPE_SUB_TYPE_MUTABLE_COMPOUND      = 8,
    JPC_SHAPE_SUB_TYPE_ROTATED_TRANSLATED    = 9,
    JPC_SHAPE_SUB_TYPE_SCALED                = 10,
    JPC_SHAPE_SUB_TYPE_OFFSET_CENTER_OF_MASS = 11,
    JPC_SHAPE_SUB_TYPE_MESH                  = 12,
    JPC_SHAPE_SUB_TYPE_HEIGHT_FIELD          = 13,
    JPC_SHAPE_SUB_TYPE_USER1                 = 14,
    JPC_SHAPE_SUB_TYPE_USER2                 = 15,
    JPC_SHAPE_SUB_TYPE_USER3                 = 16,
    JPC_SHAPE_SUB_TYPE_USER4                 = 17,
    JPC_SHAPE_SUB_TYPE_USER5                 = 18,
    JPC_SHAPE_SUB_TYPE_USER6                 = 19,
    JPC_SHAPE_SUB_TYPE_USER7                 = 20,
    JPC_SHAPE_SUB_TYPE_USER8                 = 21
};

typedef uint8_t JPC_ConstraintType;
enum {
    JPC_CONSTRAINT_TYPE_CONSTRAINT          = 0,
    JPC_CONSTRAINT_TYPE_TWO_BODY_CONSTRAINT = 1
};

typedef uint8_t JPC_ConstraintSubType;
enum {
    JPC_CONSTRAINT_SUB_TYPE_FIXED           = 0,
    JPC_CONSTRAINT_SUB_TYPE_POINT           = 1,
    JPC_CONSTRAINT_SUB_TYPE_HINGE           = 2,
    JPC_CONSTRAINT_SUB_TYPE_SLIDER          = 3,
    JPC_CONSTRAINT_SUB_TYPE_DISTANCE        = 4,
    JPC_CONSTRAINT_SUB_TYPE_CONE            = 5,
    JPC_CONSTRAINT_SUB_TYPE_SWING_TWIST     = 6,
    JPC_CONSTRAINT_SUB_TYPE_SIX_DOF         = 7,
    JPC_CONSTRAINT_SUB_TYPE_PATH            = 8,
    JPC_CONSTRAINT_SUB_TYPE_VEHICLE         = 9,
    JPC_CONSTRAINT_SUB_TYPE_RACK_AND_PINION = 10,
    JPC_CONSTRAINT_SUB_TYPE_GEAR            = 11,
    JPC_CONSTRAINT_SUB_TYPE_PULLEY          = 12,
    JPC_CONSTRAINT_SUB_TYPE_USER1           = 13,
    JPC_CONSTRAINT_SUB_TYPE_USER2           = 14,
    JPC_CONSTRAINT_SUB_TYPE_USER3           = 15,
    JPC_CONSTRAINT_SUB_TYPE_USER4           = 16
};

typedef uint8_t JPC_MotionType;
enum
{
    JPC_MOTION_TYPE_STATIC    = 0,
    JPC_MOTION_TYPE_KINEMATIC = 1,
    JPC_MOTION_TYPE_DYNAMIC   = 2
};

typedef uint8_t JPC_MotionQuality;
enum
{
    JPC_MOTION_QUALITY_DISCRETE    = 0,
    JPC_MOTION_QUALITY_LINEAR_CAST = 1
};

typedef uint8_t JPC_OverrideMassProperties;
enum
{
    JPC_OVERRIDE_MASS_PROPS_CALC_MASS_INERTIA     = 0,
    JPC_OVERRIDE_MASS_PROPS_CALC_INERTIA          = 1,
    JPC_OVERRIDE_MASS_PROPS_MASS_INERTIA_PROVIDED = 2
};

typedef enum JPC_Activation
{
    JPC_ACTIVATION_ACTIVATE      = 0,
    JPC_ACTIVATION_DONT_ACTIVATE = 1,
    _JPC_ACTIVATION_FORCEU32     = 0x7fffffff
} JPC_Activation;

typedef enum JPC_ActiveEdgeMode
{
    JPC_COLLIDE_ONLY_WITH_ACTIVE = 0,
    JPC_COLLIDE_WITH_ALL         = 1
} JPC_ActiveEdgeMode;

typedef enum JPC_CollectFacesMode
{
    JPC_COLLECT_FACES = 0,
    JPC_NO_FACES      = 1
} JPC_CollectFacesMode;

typedef enum JPC_ValidateResult
{
    JPC_VALIDATE_RESULT_ACCEPT_ALL_CONTACTS = 0,
    JPC_VALIDATE_RESULT_ACCEPT_CONTACT      = 1,
    JPC_VALIDATE_RESULT_REJECT_CONTACT      = 2,
    JPC_VALIDATE_RESULT_REJECT_ALL_CONTACTS = 3,
    _JPC_VALIDATE_RESULT_FORCEU32           = 0x7fffffff
} JPC_ValidateResult;

typedef uint8_t JPC_BackFaceMode;
enum
{
    JPC_BACK_FACE_IGNORE  = 0,
    JPC_BACK_FACE_COLLIDE = 1
};
//--------------------------------------------------------------------------------------------------
//
// Types
//
//--------------------------------------------------------------------------------------------------
typedef uint16_t JPC_ObjectLayer;
typedef uint8_t  JPC_BroadPhaseLayer;

// TODO: Consider using structures for IDs
typedef uint32_t JPC_BodyID;
typedef uint32_t JPC_SubShapeID;
typedef uint32_t JPC_CollisionGroupID;
typedef uint32_t JPC_CollisionSubGroupID;

typedef void * JPC_BodyInterfaceAddState;

// Must be 16 byte aligned
typedef void *(*JPC_AllocateFunction)(size_t in_size);
typedef void (*JPC_FreeFunction)(void *in_block);

typedef void *(*JPC_AlignedAllocateFunction)(size_t in_size, size_t in_alignment);
typedef void (*JPC_AlignedFreeFunction)(void *in_block);
//--------------------------------------------------------------------------------------------------
//
// Opaque Types
//
//--------------------------------------------------------------------------------------------------
typedef struct JPC_BodyIDVector            JPC_BodyIDVector;
typedef struct JPC_TempAllocator     JPC_TempAllocator;
typedef struct JPC_JobSystem         JPC_JobSystem;
typedef struct JPC_BodyInterface     JPC_BodyInterface;
typedef struct JPC_BodyLockInterface JPC_BodyLockInterface;
typedef struct JPC_BroadPhaseQuery   JPC_BroadPhaseQuery;
typedef struct JPC_NarrowPhaseQuery  JPC_NarrowPhaseQuery;

typedef struct JPC_Shape                   JPC_Shape;
typedef struct JPC_ConvexShape             JPC_ConvexShape;
typedef struct JPC_BoxShape                JPC_BoxShape;
typedef struct JPC_SphereShape             JPC_SphereShape;
typedef struct JPC_TriangleShape           JPC_TriangleShape;
typedef struct JPC_CapsuleShape            JPC_CapsuleShape;
typedef struct JPC_TaperedCapsuleShape     JPC_TaperedCapsuleShape;
typedef struct JPC_CylinderShape           JPC_CylinderShape;
typedef struct JPC_ConvexHullShape         JPC_ConvexHullShape;
typedef struct JPC_HeightFieldShape        JPC_HeightFieldShape;
typedef struct JPC_MeshShape               JPC_MeshShape;
typedef struct JPC_CompoundShape           JPC_CompoundShape;
typedef struct JPC_StaticCompoundShape     JPC_StaticCompoundShape;
typedef struct JPC_MutableCompoundShape    JPC_MutableCompoundShape;
typedef struct JPC_DecoratedShape          JPC_DecoratedShape;
typedef struct JPC_ScaledShape             JPC_ScaledShape;
typedef struct JPC_RotatedTranslatedShape  JPC_RotatedTranslatedShape;
typedef struct JPC_OffsetCenterOfMassShape JPC_OffsetCenterOfMassShape;

typedef struct JPC_ShapeSettings                   JPC_ShapeSettings;
typedef struct JPC_ConvexShapeSettings             JPC_ConvexShapeSettings;
typedef struct JPC_BoxShapeSettings                JPC_BoxShapeSettings;
typedef struct JPC_SphereShapeSettings             JPC_SphereShapeSettings;
typedef struct JPC_TriangleShapeSettings           JPC_TriangleShapeSettings;
typedef struct JPC_CapsuleShapeSettings            JPC_CapsuleShapeSettings;
typedef struct JPC_TaperedCapsuleShapeSettings     JPC_TaperedCapsuleShapeSettings;
typedef struct JPC_CylinderShapeSettings           JPC_CylinderShapeSettings;
typedef struct JPC_ConvexHullShapeSettings         JPC_ConvexHullShapeSettings;
typedef struct JPC_HeightFieldShapeSettings        JPC_HeightFieldShapeSettings;
typedef struct JPC_MeshShapeSettings               JPC_MeshShapeSettings;
typedef struct JPC_CompoundShapeSettings           JPC_CompoundShapeSettings;
typedef struct JPC_StaticCompoundShapeSettings     JPC_StaticCompoundShapeSettings;
typedef struct JPC_MutableCompoundShapeSettings    JPC_MutableCompoundShapeSettings;
typedef struct JPC_DecoratedShapeSettings          JPC_DecoratedShapeSettings;
typedef struct JPC_ScaledShapeSettings             JPC_ScaledShapeSettings;
typedef struct JPC_RotatedTranslatedShapeSettings  JPC_RotatedTranslatedShapeSettings;
typedef struct JPC_OffsetCenterOfMassShapeSettings JPC_OffsetCenterOfMassShapeSettings;

typedef struct JPC_PhysicsSystem JPC_PhysicsSystem;
typedef struct JPC_SharedMutex   JPC_SharedMutex;

typedef struct JPC_PhysicsMaterial JPC_PhysicsMaterial;
typedef struct JPC_GroupFilter     JPC_GroupFilter;

typedef struct JPC_Constraint           JPC_Constraint;
typedef struct JPC_TwoBodyConstraint    JPC_TwoBodyConstraint;
typedef struct JPC_FixedConstraint      JPC_FixedConstraint;
typedef struct JPC_DistanceConstraint   JPC_DistanceConstraint;
typedef struct JPC_PointConstraint      JPC_PointConstraint;
typedef struct JPC_HingeConstraint      JPC_HingeConstraint;
typedef struct JPC_ConeConstraint       JPC_ConeConstraint;
typedef struct JPC_SliderConstraint     JPC_SliderConstraint;
typedef struct JPC_SwingTwistConstraint JPC_SwingTwistConstraint;
typedef struct JPC_SixDOFConstraint     JPC_SixDOFConstraint;

typedef struct JPC_ConstraintSettings           JPC_ConstraintSettings;
typedef struct JPC_TwoBodyConstraintSettings    JPC_TwoBodyConstraintSettings;
typedef struct JPC_FixedConstraintSettings      JPC_FixedConstraintSettings;
typedef struct JPC_DistanceConstraintSettings   JPC_DistanceConstraintSettings;
typedef struct JPC_PointConstraintSettings      JPC_PointConstraintSettings;
typedef struct JPC_HingeConstraintSettings      JPC_HingeConstraintSettings;
typedef struct JPC_ConeConstraintSettings       JPC_ConeConstraintSettings;
typedef struct JPC_SliderConstraintSettings     JPC_SliderConstraintSettings;
typedef struct JPC_SwingTwistConstraintSettings JPC_SwingTwistConstraintSettings;
typedef struct JPC_SixDOFConstraintSettings     JPC_SixDOFConstraintSettings;
//--------------------------------------------------------------------------------------------------
//
// Structures
//
//--------------------------------------------------------------------------------------------------
typedef struct JPC_ShapeResult
{
    const JPC_Shape *result;
    char             error[256];
} JPC_ShapeResult;

// NOTE: Needs to be kept in sync with JPH::MassProperties
typedef struct JPC_MassProperties
{
    float             mass;
    alignas(16) float inertia[16];
} JPC_MassProperties;

// NOTE: Needs to be kept in sync with JPH::MotionProperties
typedef struct JPC_MotionProperties
{
    alignas(16) float  linear_velocity[4]; // 4th element is ignored
    alignas(16) float  angular_velocity[4]; // 4th element is ignored
    alignas(16) float  inv_inertia_diagonal[4]; // 4th element is ignored
    alignas(16) float  inertia_rotation[4];

    float              force[3];
    float              torque[3];
    float              inv_mass;
    float              linear_damping;
    float              angular_damping;
    float              max_linear_velocity;
    float              max_angular_velocity;
    float              gravity_factor;
    uint32_t           index_in_active_bodies;
    uint32_t           island_index;

    JPC_MotionQuality  motion_quality;
    bool               allow_sleeping;

#if JPC_DOUBLE_PRECISION == 1
    alignas(8) uint8_t reserved[76];
#else
    alignas(4) uint8_t reserved[52];
#endif

#if JPC_ENABLE_ASSERTS == 1
    JPC_MotionType    cached_motion_type;
#endif
} JPC_MotionProperties;

// NOTE: Needs to be kept in sync with JPH::CollisionGroup
typedef struct JPC_CollisionGroup
{
    const JPC_GroupFilter * filter;
    JPC_CollisionGroupID    group_id;
    JPC_CollisionSubGroupID sub_group_id;
} JPC_CollisionGroup;

// NOTE: Needs to be kept in sync with JPH::BodyCreationSettings
typedef struct JPC_BodyCreationSettings
{
    JPC_RVEC_ALIGN JPC_Real    position[4]; // 4th element is ignored
    alignas(16) float          rotation[4];
    alignas(16) float          linear_velocity[4]; // 4th element is ignored
    alignas(16) float          angular_velocity[4]; // 4th element is ignored
    uint64_t                   user_data;
    JPC_ObjectLayer            object_layer;
    JPC_CollisionGroup         collision_group;
    JPC_MotionType             motion_type;
    bool                       allow_dynamic_or_kinematic;
    bool                       is_sensor;
    bool                       use_manifold_reduction;
    JPC_MotionQuality          motion_quality;
    bool                       allow_sleeping;
    float                      friction;
    float                      restitution;
    float                      linear_damping;
    float                      angular_damping;
    float                      max_linear_velocity;
    float                      max_angular_velocity;
    float                      gravity_factor;
    JPC_OverrideMassProperties override_mass_properties;
    float                      inertia_multiplier;
    JPC_MassProperties         mass_properties_override;
    const void *               reserved;
    const JPC_Shape *          shape;
} JPC_BodyCreationSettings;

// NOTE: Needs to be kept in sync with JPH::Body
typedef struct JPC_Body
{
    JPC_RVEC_ALIGN JPC_Real position[4]; // 4th element is ignored
    alignas(16) float       rotation[4];
    alignas(16) float       bounds_min[4]; // 4th element is ignored
    alignas(16) float       bounds_max[4]; // 4th element is ignored

    const JPC_Shape *       shape;
    JPC_MotionProperties *  motion_properties; // will be NULL for static bodies
    uint64_t                user_data;
    JPC_CollisionGroup      collision_group;

    float                   friction;
    float                   restitution;
    JPC_BodyID              id;

    JPC_ObjectLayer         object_layer;

    JPC_BroadPhaseLayer     broad_phase_layer;
    JPC_MotionType          motion_type;
    uint8_t                 flags;
} JPC_Body;

// NOTE: Needs to be kept in sync with JPH::SubShapeIDCreator
typedef struct JPC_SubShapeIDCreator
{
    JPC_SubShapeID id;
    uint32_t       current_bit;
} JPC_SubShapeIDCreator;

// NOTE: Needs to be kept in sync with JPH::SubShapeIDPair
typedef struct JPC_SubShapeIDPair
{
    struct {
        JPC_BodyID     body_id;
        JPC_SubShapeID sub_shape_id;
    }                  first;
    struct {
        JPC_BodyID     body_id;
        JPC_SubShapeID sub_shape_id;
    }                  second;
} JPC_SubShapeIDPair;

// NOTE: Needs to be kept in sync with JPH::ContactManifold
typedef struct JPC_ContactManifold
{
    JPC_RVEC_ALIGN JPC_Real  base_offset[4]; // 4th element is ignored
    alignas(16) float        normal[4]; // 4th element is ignored; world space
    float                    penetration_depth;
    JPC_SubShapeID           shape1_sub_shape_id;
    JPC_SubShapeID           shape2_sub_shape_id;
    struct {
        alignas(16) uint32_t num_points;
        alignas(16) float    points[64][4]; // 4th element is ignored; world space
    }                        shape1_relative_contact;
    struct {
        alignas(16) uint32_t num_points;
        alignas(16) float    points[64][4]; // 4th element is ignored; world space
    }                        shape2_relative_contact;
} JPC_ContactManifold;

// NOTE: Needs to be kept in sync with JPH::ContactSettings
typedef struct JPC_ContactSettings
{
    float combined_friction;
    float combined_restitution;
    bool  is_sensor;
} JPC_ContactSettings;

// NOTE: Needs to be kept in sync with JPH::CollideShapeResult
typedef struct JPC_CollideShapeResult
{
    alignas(16) float        shape1_contact_point[4]; // 4th element is ignored; world space
    alignas(16) float        shape2_contact_point[4]; // 4th element is ignored; world space
    alignas(16) float        penetration_axis[4]; // 4th element is ignored; world space
    float                    penetration_depth;
    JPC_SubShapeID           shape1_sub_shape_id;
    JPC_SubShapeID           shape2_sub_shape_id;
    JPC_BodyID               body2_id;
    struct {
        alignas(16) uint32_t num_points;
        alignas(16) float    points[32][4]; // 4th element is ignored; world space
    }                        shape1_face;
    struct {
        alignas(16) uint32_t num_points;
        alignas(16) float    points[32][4]; // 4th element is ignored; world space
    }                        shape2_face;
} JPC_CollideShapeResult;

// NOTE: Needs to be kept in sync with JPH::TransformedShape
typedef struct JPC_TransformedShape
{
    JPC_RVEC_ALIGN JPC_Real shape_position_com[4]; // 4th element is ignored
    alignas(16) float       shape_rotation[4];
    JPC_Shape *             shape;
    float                   shape_scale[3];
    JPC_BodyID              body_id;
    JPC_SubShapeIDCreator   sub_shape_id_creator;
} JPC_TransformedShape;

// NOTE: Needs to be kept in sync with JPH::BodyLockRead
typedef struct JPC_BodyLockRead
{
    const JPC_BodyLockInterface *lock_interface;
    JPC_SharedMutex *            mutex;
    const JPC_Body *             body;
} JPC_BodyLockRead;

// NOTE: Needs to be kept in sync with JPH::BodyLockWrite
typedef struct JPC_BodyLockWrite
{
    const JPC_BodyLockInterface *lock_interface;
    JPC_SharedMutex *            mutex;
    JPC_Body *                   body;
} JPC_BodyLockWrite;

// NOTE: Needs to be kept in sync with JPH::RayCast
typedef struct JPC_RayCast
{
    alignas(16) float       origin[4]; // 4th element is ignored
    alignas(16) float       direction[4]; // length of the vector is important; 4th element is ignored
} JPC_RayCast;

// NOTE: Needs to be kept in sync with JPH::RRayCast
typedef struct JPC_RRayCast
{
    JPC_RVEC_ALIGN JPC_Real origin[4]; // 4th element is ignored
    alignas(16) float       direction[4]; // length of the vector is important; 4th element is ignored
} JPC_RRayCast;

// NOTE: Needs to be kept in sync with JPH::RayCastResultBroadPhaseCastResult
typedef struct JPC_BroadPhaseCastResult
{
    JPC_BodyID     body_id; // JPC_BODY_ID_INVALID
    float          fraction; // 1.0 + JPC_FLT_EPSILON
} JPC_BroadPhaseCastResult;

// NOTE: Needs to be kept in sync with JPH::RayCastResult
typedef struct JPC_RayCastResult
{
    JPC_BroadPhaseCastResult base;
    JPC_SubShapeID           sub_shape_id;
} JPC_RayCastResult;

// NOTE: Needs to be kept in sync with JPH::ShapeCastResult
typedef struct JPC_ShapeCastResult
{
    JPC_CollideShapeResult base;
    float fraction;
    bool is_back_face_hit;
} JPC_ShapeCastResult;

// NOTE: Needs to be kept in sync with JPH::CollidePointResult
typedef struct JPC_CollidePointResult
{
    JPC_BodyID body_id;
    JPC_SubShapeID sub_shape_id;
} JPC_CollidePointResult;

// NOTE: Needs to be kept in sync with JPH::RayCastSettings
typedef struct JPC_RayCastSettings
{
    JPC_BackFaceMode back_face_mode;
    bool             treat_convex_as_solid;
} JPC_RayCastSettings;

// NOTE: Needs to be kept in sync with JPH::CollideSettingsBase
typedef struct JPC_CollideSettingsBase
{
    JPC_ActiveEdgeMode   active_edge_mode;
    JPC_CollectFacesMode collect_faces_mode;
    float                collision_tolerance;
    float                penetration_tolerance;
    alignas(16) float    active_edge_movement_direction[3];
} JPC_CollideSettingsBase;

// NOTE: Needs to be kept in sync with JPH::CollideShapeSettings
typedef struct JPC_CollideShapeSettings
{
    JPC_CollideSettingsBase base;
    float                   max_separation_distance;
    JPC_BackFaceMode        back_face_mode;
} JPC_CollideShapeSettings;

// NOTE: Needs to be kept in sync with JPH::ShapeCastSettings
typedef struct JPC_ShapeCastSettings
{
    JPC_CollideSettingsBase base;
    JPC_BackFaceMode        back_face_mode_triangles;
    JPC_BackFaceMode        back_face_mode_convex;
    bool                    use_shrunken_shape_and_convex_radius;
    bool                    return_deepest_point;
} JPC_ShapeCastSettings;

// NOTE: Needs to be kept in sync with JPH::AABox
typedef struct JPC_AABox
{
    alignas(16) float   min[4]; // 4th element is ignored
    alignas(16) float   max[4]; // 4th element is ignored
} JPC_AABox;

// NOTE: Needs to be kept in sync with JPH::OrientedBox
typedef struct JPC_OrientedBox
{
    alignas(64) float   orientation[16];
    alignas(16) float   half_extent[3];
} JPC_OrientedBox;

// NOTE: Needs to be kept in sync with JPH::AABoxCast
typedef struct JPC_AABoxCast
{
    JPC_AABox           box;
    alignas(16) float   direction[3];
} JPC_AABoxCast;

//// NOTE: Needs to be kept in sync with JPH::AABoxCast
//typedef struct JPC_RShapeCast
//{
//    JPC_Shape *shape;
//    alignas(16) float scale[3];
//    // todo
//
//    JPC_AABox           box;
//    alignas(16) float   direction[3];
//} JPC_RShapeCast;

typedef struct JPC_PhysicsSettings
{
    int   max_in_flight_body_pairs; // 16384
    int   step_listeners_batch_size; // 8
    int   step_listener_batches_per_job; // 1
    float baumgarte; // 0.2f
    float speculative_contact_distance; // 0.02f
    float penetration_slop; // 0.02f
    float linear_cast_threshold; // 0.75f
    float linear_cast_max_penetration; // 0.25f
    float manifold_tolerance_sq; // 1.0e-6f
    float max_penetration_distance; // 0.2f
    float body_pair_cache_max_delta_position_sq; // 0.001f * 0.001f
    float body_pair_cache_cos_max_delta_rotation_div_2; // cos(2 degrees / 2)
    float contact_normal_cos_max_delta_rotation; // cos(5 degrees)
    float contact_point_preserve_lambda_max_dist_sq; // 0.01f * 0.01f
    int   num_velocity_steps; // 10
    int   num_position_steps; // 2
    float min_velocity_for_restitution; // 1.0f
    float time_before_sleep; // 0.5f
    float point_velocity_sleep_threshold; // 0.03f
    bool  constraint_warm_start; // true
    bool  use_body_pair_contact_cache; // true
    bool  use_manifold_reduction; // true
    bool  allow_sleeping; // true
    bool  check_active_edges; // true
} JPC_PhysicsSettings;

typedef struct JPC_CollisionCollector
{
    float                                 early_out_fraction;
    const JPC_TransformedShape *          context;
} JPC_CollisionCollector;

//--------------------------------------------------------------------------------------------------
//
// Interfaces (virtual tables)
//
//--------------------------------------------------------------------------------------------------
typedef struct JPC_BroadPhaseLayerInterfaceVTable
{
    const void *__unused0; // Unused, *must* be NULL.
    const void *__unused1; // Unused, *must* be NULL.

    // Required, *cannot* be NULL.
    uint32_t
    (*GetNumBroadPhaseLayers)(const void *in_self);

    // Required, *cannot* be NULL.
    JPC_BroadPhaseLayer
    (*GetBroadPhaseLayer)(const void *in_self, JPC_ObjectLayer in_layer);
} JPC_BroadPhaseLayerInterfaceVTable;

typedef struct JPC_ObjectVsBroadPhaseLayerFilterVTable
{
    const void *__unused0; // Unused, *must* be NULL.
    const void *__unused1; // Unused, *must* be NULL.

    // Required, *cannot* be NULL.
    bool
    (*ShouldCollide)(const void *in_self, JPC_ObjectLayer in_layer1, JPC_BroadPhaseLayer in_layer2);
} JPC_ObjectVsBroadPhaseLayerFilterVTable;

typedef struct JPC_BroadPhaseLayerFilterVTable
{
    const void *__unused0; // Unused, *must* be NULL.
    const void *__unused1; // Unused, *must* be NULL.

    // Required, *cannot* be NULL.
    bool
    (*ShouldCollide)(const void *in_self, JPC_BroadPhaseLayer in_layer);
} JPC_BroadPhaseLayerFilterVTable;

typedef struct JPC_ObjectLayerPairFilterVTable
{
    const void *__unused0; // Unused, *must* be NULL.
    const void *__unused1; // Unused, *must* be NULL.

    // Required, *cannot* be NULL.
    bool
    (*ShouldCollide)(const void *in_self, JPC_ObjectLayer in_layer1, JPC_ObjectLayer in_layer2);
} JPC_ObjectLayerPairFilterVTable;

typedef struct JPC_ObjectLayerFilterVTable
{
    const void *__unused0; // Unused, *must* be NULL.
    const void *__unused1; // Unused, *must* be NULL.

    // Required, *cannot* be NULL.
    bool
    (*ShouldCollide)(const void *in_self, JPC_ObjectLayer in_layer);
} JPC_ObjectLayerFilterVTable;

typedef struct JPC_BodyActivationListenerVTable
{
    const void *__unused0; // Unused, *must* be NULL.
    const void *__unused1; // Unused, *must* be NULL.

    // Required, *cannot* be NULL.
    void
    (*OnBodyActivated)(void *in_self, JPC_BodyID in_body_id, uint64_t in_user_data);

    // Required, *cannot* be NULL.
    void
    (*OnBodyDeactivated)(void *in_self, JPC_BodyID in_body_id, uint64_t in_user_data);
} JPC_BodyActivationListenerVTable;

typedef struct JPC_BodyFilterVTable
{
    const void *__unused0; // Unused, *must* be NULL.
    const void *__unused1; // Unused, *must* be NULL.

    // Required, *cannot* be NULL.
    bool
    (*ShouldCollide)(const void *in_self, JPC_BodyID in_body_id);

    // Required, *cannot* be NULL.
    bool
    (*ShouldCollideLocked)(const void *in_self, const JPC_Body *in_body);
} JPC_BodyFilterVTable;

typedef struct JPC_ShapeFilterVTable
{
    const void *__unused0; // Unused, *must* be NULL.
    const void *__unused1; // Unused, *must* be NULL.

    // Required, *cannot* be NULL.
    bool
    (*ShouldCollide)(const void *in_self, JPC_SubShapeID in_sub_shape_id);

    // Required, *cannot* be NULL.
    bool
    (*ShouldPairCollide)(const void *in_self, JPC_SubShapeID in_sub_shape_id1, JPC_SubShapeID in_sub_shape_id2);
} JPC_ShapeFilterVTable;

typedef struct JPC_ContactListenerVTable
{
    const void *__unused0; // Unused, *must* be NULL.
    const void *__unused1; // Unused, *must* be NULL.

    // Optional, can be NULL.
    JPC_ValidateResult
    (*OnContactValidate)(void *in_self,
                         const JPC_Body *in_body1,
                         const JPC_Body *in_body2,
                         const JPC_Real in_base_offset[3],
                         const JPC_CollideShapeResult *in_collision_result);

    // Optional, can be NULL.
    void
    (*OnContactAdded)(void *in_self,
                      const JPC_Body *in_body1,
                      const JPC_Body *in_body2,
                      const JPC_ContactManifold *in_manifold,
                      JPC_ContactSettings *io_settings);

    // Optional, can be NULL.
    void
    (*OnContactPersisted)(void *in_self,
                          const JPC_Body *in_body1,
                          const JPC_Body *in_body2,
                          const JPC_ContactManifold *in_manifold,
                          JPC_ContactSettings *io_settings);

    // Optional, can be NULL.
    void
    (*OnContactRemoved)(void *in_self, const JPC_SubShapeIDPair *in_sub_shape_pair);
} JPC_ContactListenerVTable;

typedef struct JPC_PhysicsStepListenerVTable
{
    const void *__unused0; // Unused, *must* be NULL.
    const void *__unused1; // Unused, *must* be NULL.

    // Required, *cannot* be NULL.
    void
    (*OnStep)(void *in_self, float in_delta_time, JPC_PhysicsSystem *in_physics_system);
} JPC_PhysicsStepListenerVTable;

typedef struct JPC_RayCastBodyCollectorVTable
{
    const void *__unused0; // Unused, *must* be NULL.
    const void *__unused1; // Unused, *must* be NULL.

    // Required, *cannot* be NULL.
    void
    (*Reset)(void *in_self);

    // Required, *cannot* be NULL.
    void
    (*OnBody)(void *in_self, JPC_Body in_body);

    // Required, *cannot* be NULL.
    void
    (*AddHit)(void *in_self, const JPC_BroadPhaseCastResult in_result);
} JPC_RayCastBodyCollectorVTable;

typedef struct JPC_CollideShapeBodyCollectorVTable
{
    const void *__unused0; // Unused, *must* be NULL.
    const void *__unused1; // Unused, *must* be NULL.

    // Required, *cannot* be NULL.
    void
    (*Reset)(void *in_self);

    // Required, *cannot* be NULL.
    void
    (*OnBody)(void *in_self, JPC_Body in_body);

    // Required, *cannot* be NULL.
    void
    (*AddHit)(void *in_self, JPC_BodyID in_result);
} JPC_CollideShapeBodyCollectorVTable;

typedef struct JPC_CastShapeBodyCollectorVTable
{
    const void *__unused0; // Unused, *must* be NULL.
    const void *__unused1; // Unused, *must* be NULL.

    // Required, *cannot* be NULL.
    void
    (*Reset)(void *in_self);

    // Required, *cannot* be NULL.
    void
    (*OnBody)(void *in_self, JPC_Body in_body);

    // Required, *cannot* be NULL.
    void
    (*AddHit)(void *in_self, const JPC_BroadPhaseCastResult in_result);
} JPC_CastShapeBodyCollectorVTable;

typedef struct JPC_CastRayCollectorVTable
{
    const void *__unused0; // Unused, *must* be NULL.
    const void *__unused1; // Unused, *must* be NULL.

    // Required, *cannot* be NULL.
    void
    (*Reset)(void *in_self);

    // Required, *cannot* be NULL.
    void
    (*OnBody)(void *in_self, JPC_Body in_body);

    // Required, *cannot* be NULL.
    void
    (*AddHit)(void *in_self, const JPC_RayCastResult in_result);
} JPC_CastRayCollectorVTable;

typedef struct JPC_CastShapeCollectorVTable
{
    const void *__unused0; // Unused, *must* be NULL.
    const void *__unused1; // Unused, *must* be NULL.

    // Required, *cannot* be NULL.
    void
    (*Reset)(void *in_self);

    // Required, *cannot* be NULL.
    void
    (*OnBody)(void *in_self, JPC_Body in_body);

    // Required, *cannot* be NULL.
    void
    (*AddHit)(void *in_self, const JPC_ShapeCastResult in_result);
} JPC_CastShapeCollectorVTable;

typedef struct JPC_CollidePointCollectorVTable
{
    const void *__unused0; // Unused, *must* be NULL.
    const void *__unused1; // Unused, *must* be NULL.

    // Required, *cannot* be NULL.
    void
    (*Reset)(void *in_self);

    // Required, *cannot* be NULL.
    void
    (*OnBody)(void *in_self, JPC_Body in_body);

    // Required, *cannot* be NULL.
    void
    (*AddHit)(void *in_self, const JPC_CollidePointResult in_result);
} JPC_CollidePointCollectorVTable;

typedef struct JPC_CollideShapeCollectorVTable
{
    const void *__unused0; // Unused, *must* be NULL.
    const void *__unused1; // Unused, *must* be NULL.

    // Required, *cannot* be NULL.
    void
    (*Reset)(void *in_self);

    // Required, *cannot* be NULL.
    void
    (*OnBody)(void *in_self, JPC_Body in_body);

    // Required, *cannot* be NULL.
    void
    (*AddHit)(void *in_self, const JPC_CollideShapeResult in_result);
} JPC_CollideShapeCollectorVTable;

typedef struct JPC_TransformedShapeCollectorVTable
{
    const void *__unused0; // Unused, *must* be NULL.
    const void *__unused1; // Unused, *must* be NULL.

    // Required, *cannot* be NULL.
    void
    (*Reset)(void *in_self);

    // Required, *cannot* be NULL.
    void
    (*OnBody)(void *in_self, JPC_Body in_body);

    // Required, *cannot* be NULL.
    void
    (*AddHit)(void *in_self, const JPC_TransformedShape in_result);
} JPC_TransformedShapeCollectorVTable;

// TODO combine
//typedef struct JPC_CombineFunctor
//{
//    // Required, *cannot* be NULL.
//    float
//    (*fn)(const JPC_Body *in_body_1,
//          const JPC_SubShapeID in_sub_shape_id_1,
//          const JPC_Body *in_body_2,
//          const JPC_SubShapeID in_sub_shape_id_2);
//} JPC_CombineFunctor;
//--------------------------------------------------------------------------------------------------
//
// Misc functions
//
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyIDVector_Destroy(JPC_BodyIDVector *in_vector);

JPC_API void
JPC_RegisterDefaultAllocator(void);

JPC_API void
JPC_RegisterCustomAllocator(JPC_AllocateFunction in_alloc,
                            JPC_FreeFunction in_free,
                            JPC_AlignedAllocateFunction in_aligned_alloc,
                            JPC_AlignedFreeFunction in_aligned_free);
JPC_API void
JPC_CreateFactory(void);

JPC_API void
JPC_DestroyFactory(void);

JPC_API void
JPC_RegisterTypes(void);

JPC_API void
JPC_BodyCreationSettings_SetDefault(JPC_BodyCreationSettings *out_settings);

JPC_API void
JPC_BodyCreationSettings_Set(JPC_BodyCreationSettings *out_settings,
                             const JPC_Shape *in_shape,
                             const JPC_Real in_position[3],
                             const float in_rotation[4],
                             JPC_MotionType in_motion_type,
                             JPC_ObjectLayer in_layer);

JPC_API const JPC_ShapeSettings *
JPC_BodyCreationSettings_GetShapeSettings(const JPC_BodyCreationSettings *in_settings);

JPC_API void
JPC_BodyCreationSettings_SetShapeSettings(JPC_BodyCreationSettings *in_settings,
                                          const JPC_ShapeSettings *in_shape);

JPC_API JPC_ShapeResult
JPC_BodyCreationSettings_ConvertShapeSettings(JPC_BodyCreationSettings *in_settings);

JPC_API const JPC_Shape *
JPC_BodyCreationSettings_GetShape(const JPC_BodyCreationSettings *in_settings);

JPC_API void
JPC_BodyCreationSettings_SetShape(JPC_BodyCreationSettings *in_settings,
                                  const JPC_Shape *in_shape);

JPC_API bool
JPC_BodyCreationSettings_HasMassProperties(const JPC_BodyCreationSettings *in_settings);

JPC_API void
JPC_BodyCreationSettings_GetMassProperties(const JPC_BodyCreationSettings *in_settings,
                                           JPC_MassProperties *out_properties);

JPC_API bool
JPC_CollisionGroup_CanCollide(const JPC_CollisionGroup *in_group, const JPC_CollisionCollector *in_other);

JPC_API void
JPC_PhysicsSettings_SetDefault(JPC_PhysicsSettings *out_settings);

JPC_API void
JPC_BroadPhaseCastResult_SetDefault(JPC_BroadPhaseCastResult *out_result);

JPC_API void
JPC_RayCastResult_SetDefault(JPC_RayCastResult *out_result);

JPC_API float
JPC_CollideShapeResult_GetEarlyOutFraction(JPC_CollideShapeResult *in_settings);

JPC_API void
JPC_CollideShapeResult_Reversed(JPC_CollideShapeResult *in_settings, JPC_CollideShapeResult *out_settings);

JPC_API void
JPC_RayCastSettings_SetDefault(JPC_RayCastSettings *out_settings);

JPC_API void
JPC_CollideShapeSettings_SetDefault(JPC_CollideShapeSettings *out_settings);

JPC_API void
JPC_ShapeCastSettings_SetDefault(JPC_ShapeCastSettings *out_settings);
//--------------------------------------------------------------------------------------------------
//
// JPC_MotionProperties
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_MotionQuality
JPC_MotionProperties_GetMotionQuality(const JPC_MotionProperties *in_properties);

JPC_API void
JPC_MotionProperties_GetLinearVelocity(const JPC_MotionProperties *in_properties,
                                       float out_linear_velocity[3]);
JPC_API void
JPC_MotionProperties_SetLinearVelocity(JPC_MotionProperties *in_properties,
                                       const float in_linear_velocity[3]);
JPC_API void
JPC_MotionProperties_SetLinearVelocityClamped(JPC_MotionProperties *in_properties,
                                              const float in_linear_velocity[3]);
JPC_API void
JPC_MotionProperties_GetAngularVelocity(const JPC_MotionProperties *in_properties,
                                        float out_angular_velocity[3]);
JPC_API void
JPC_MotionProperties_SetAngularVelocity(JPC_MotionProperties *in_properties,
                                        const float in_angular_velocity[3]);
JPC_API void
JPC_MotionProperties_SetAngularVelocityClamped(JPC_MotionProperties *in_properties,
                                               const float in_angular_velocity[3]);
JPC_API void
JPC_MotionProperties_MoveKinematic(JPC_MotionProperties *in_properties,
                                   const float in_delta_position[3],
                                   const float in_delta_rotation[4],
                                   float in_delta_time);
JPC_API void
JPC_MotionProperties_ClampLinearVelocity(JPC_MotionProperties *in_properties);

JPC_API void
JPC_MotionProperties_ClampAngularVelocity(JPC_MotionProperties *in_properties);

JPC_API float
JPC_MotionProperties_GetLinearDamping(const JPC_MotionProperties *in_properties);

JPC_API void
JPC_MotionProperties_SetLinearDamping(JPC_MotionProperties *in_properties,
                                      float in_linear_damping);
JPC_API float
JPC_MotionProperties_GetAngularDamping(const JPC_MotionProperties *in_properties);

JPC_API void
JPC_MotionProperties_SetAngularDamping(JPC_MotionProperties *in_properties,
                                       float in_angular_damping);
JPC_API float
JPC_MotionProperties_GetGravityFactor(const JPC_MotionProperties *in_properties);

JPC_API void
JPC_MotionProperties_SetGravityFactor(JPC_MotionProperties *in_properties,
                                      float in_gravity_factor);
JPC_API void
JPC_MotionProperties_SetMassProperties(JPC_MotionProperties *in_properties,
                                       const JPC_MassProperties *in_mass_properties);
JPC_API float
JPC_MotionProperties_GetInverseMass(const JPC_MotionProperties *in_properties);

JPC_API void
JPC_MotionProperties_SetInverseMass(JPC_MotionProperties *in_properties, float in_inv_mass);

JPC_API void
JPC_MotionProperties_GetInverseInertiaDiagonal(const JPC_MotionProperties *in_properties,
                                               float out_inverse_inertia_diagonal[3]);
JPC_API void
JPC_MotionProperties_GetInertiaRotation(const JPC_MotionProperties *in_properties,
                                        float out_inertia_rotation[4]);
JPC_API void
JPC_MotionProperties_SetInverseInertia(JPC_MotionProperties *in_properties,
                                       const float in_diagonal[3],
                                       const float in_rotation[4]);
JPC_API void
JPC_MotionProperties_GetLocalSpaceInverseInertia(const JPC_MotionProperties *in_properties,
                                                 float out_matrix[16]);
JPC_API void
JPC_MotionProperties_GetInverseInertiaForRotation(const JPC_MotionProperties *in_properties,
                                                  const float in_rotation_matrix[16],
                                                  float out_matrix[16]);
JPC_API void
JPC_MotionProperties_MultiplyWorldSpaceInverseInertiaByVector(const JPC_MotionProperties *in_properties,
                                                              const float in_body_rotation[4],
                                                              const float in_vector[3],
                                                              float out_vector[3]);
JPC_API void
JPC_MotionProperties_GetPointVelocityCOM(const JPC_MotionProperties *in_properties,
                                         const float in_point_relative_to_com[3],
                                         float out_point[3]);
JPC_API float
JPC_MotionProperties_GetMaxLinearVelocity(const JPC_MotionProperties *in_properties);

JPC_API void
JPC_MotionProperties_SetMaxLinearVelocity(JPC_MotionProperties *in_properties,
                                          float in_max_linear_velocity);
JPC_API float
JPC_MotionProperties_GetMaxAngularVelocity(const JPC_MotionProperties *in_properties);

JPC_API void
JPC_MotionProperties_SetMaxAngularVelocity(JPC_MotionProperties *in_properties,
                                           float in_max_angular_velocity);
//--------------------------------------------------------------------------------------------------
//
// JPC_TempAllocator
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_TempAllocator *
JPC_TempAllocator_Create(uint32_t in_size);

JPC_API void
JPC_TempAllocator_Destroy(JPC_TempAllocator *in_allocator);
//--------------------------------------------------------------------------------------------------
//
// JPC_JobSystem
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_JobSystem *
JPC_JobSystem_Create(uint32_t in_max_jobs, uint32_t in_max_barriers, int in_num_threads);

JPC_API void
JPC_JobSystem_Destroy(JPC_JobSystem *in_job_system);
//--------------------------------------------------------------------------------------------------
//
// JPC_PhysicsSystem
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_PhysicsSystem *
JPC_PhysicsSystem_Create(uint32_t in_max_bodies,
                         uint32_t in_num_body_mutexes,
                         uint32_t in_max_body_pairs,
                         uint32_t in_max_contact_constraints,
                         const void *in_broad_phase_layer_interface,
                         const void *in_object_vs_broad_phase_layer_filter,
                         const void *in_object_layer_pair_filter);
JPC_API void
JPC_PhysicsSystem_Destroy(JPC_PhysicsSystem *in_physics_system);

JPC_API void
JPC_PhysicsSystem_SetBodyActivationListener(JPC_PhysicsSystem *in_physics_system, void *in_listener);

JPC_API void *
JPC_PhysicsSystem_GetBodyActivationListener(const JPC_PhysicsSystem *in_physics_system);

JPC_API void
JPC_PhysicsSystem_SetContactListener(JPC_PhysicsSystem *in_physics_system, void *in_listener);

JPC_API void *
JPC_PhysicsSystem_GetContactListener(const JPC_PhysicsSystem *in_physics_system);

// TODO combine
//JPC_API void
//JPC_PhysicsSystem_SetCombineFriction(JPC_PhysicsSystem *in_physics_system,
//                                     const JPC_CombineFunctor *in_combine);
//
//JPC_API void
//JPC_PhysicsSystem_SetCombineRestitution(JPC_PhysicsSystem *in_physics_system,
//                                        const JPC_CombineFunctor *in_combine);

JPC_API void
JPC_PhysicsSystem_SetPhysicsSettings(JPC_PhysicsSystem *in_physics_system,
                                     const JPC_PhysicsSettings *in_settings);

JPC_API void
JPC_PhysicsSystem_GetPhysicsSettings(const JPC_PhysicsSystem *in_physics_system,
                                     JPC_PhysicsSettings *out_settings);

JPC_API const JPC_BroadPhaseQuery *
JPC_PhysicsSystem_GetBroadPhaseQuery(const JPC_PhysicsSystem *in_physics_system);

JPC_API const JPC_NarrowPhaseQuery *
JPC_PhysicsSystem_GetNarrowPhaseQuery(const JPC_PhysicsSystem *in_physics_system);

JPC_API const JPC_NarrowPhaseQuery *
JPC_PhysicsSystem_GetNarrowPhaseQueryNoLock(const JPC_PhysicsSystem *in_physics_system);

JPC_API void
JPC_PhysicsSystem_OptimizeBroadPhase(JPC_PhysicsSystem *in_physics_system);

JPC_API void
JPC_PhysicsSystem_AddStepListener(JPC_PhysicsSystem *in_physics_system, void *in_listener);

JPC_API void
JPC_PhysicsSystem_RemoveStepListener(JPC_PhysicsSystem *in_physics_system, void *in_listener);

JPC_API void
JPC_PhysicsSystem_Update(JPC_PhysicsSystem *in_physics_system,
                         float in_delta_time,
                         int in_collision_steps,
                         int in_integration_sub_steps,
                         JPC_TempAllocator *in_temp_allocator,
                         JPC_JobSystem *in_job_system);

JPC_API void
JPC_PhysicsSystem_SetGravity(JPC_PhysicsSystem *in_physics_system, const float in_gravity[3]);

JPC_API void
JPC_PhysicsSystem_GetGravity(const JPC_PhysicsSystem *in_physics_system, float out_gravity[3]);

JPC_API JPC_BodyInterface *
JPC_PhysicsSystem_GetBodyInterfaceNoLock(JPC_PhysicsSystem *in_physics_system);

JPC_API JPC_BodyInterface *
JPC_PhysicsSystem_GetBodyInterface(JPC_PhysicsSystem *in_physics_system);

JPC_API const JPC_BodyLockInterface *
JPC_PhysicsSystem_GetBodyLockInterface(const JPC_PhysicsSystem *in_physics_system);

JPC_API const JPC_BodyLockInterface *
JPC_PhysicsSystem_GetBodyLockInterfaceNoLock(const JPC_PhysicsSystem *in_physics_system);

JPC_API uint32_t
JPC_PhysicsSystem_GetNumBodies(const JPC_PhysicsSystem *in_physics_system);

JPC_API uint32_t
JPC_PhysicsSystem_GetNumActiveBodies(const JPC_PhysicsSystem *in_physics_system);

JPC_API uint32_t
JPC_PhysicsSystem_GetMaxBodies(const JPC_PhysicsSystem *in_physics_system);

/// Get copy of the list of all bodies under protection of a lock.
JPC_API JPC_BodyIDVector *
JPC_PhysicsSystem_GetBodyIDs(const JPC_PhysicsSystem *in_physics_system,
                             uint32_t *out_num_body_ids,
                             JPC_BodyID **out_body_ids);

/// Get copy of the list of active bodies under protection of a lock.
JPC_API JPC_BodyIDVector *
JPC_PhysicsSystem_GetActiveBodyIDs(const JPC_PhysicsSystem *in_physics_system,
                                   uint32_t *out_num_body_ids,
                                   JPC_BodyID **out_body_ids);
///
/// Low-level access for advanced usage and zero CPU overhead (access *not* protected by a lock)
///
/// Check if this is a valid body pointer.
/// When a body is freed the memory that the pointer occupies is reused to store a freelist.
#define _JPC_IS_FREED_BODY_BIT 0x1

#define JPC_IS_VALID_BODY_POINTER(body_ptr) (((uintptr_t)(body_ptr) & _JPC_IS_FREED_BODY_BIT) == 0)

/// Access a body, will return NULL if the body ID is no longer valid.
/// Use `JPC_PhysicsSystem_GetBodiesUnsafe()` to get an array of all body pointers.
#define JPC_TRY_GET_BODY(all_body_ptrs, body_id) \
    JPC_IS_VALID_BODY_POINTER(all_body_ptrs[body_id & JPC_BODY_ID_INDEX_BITS]) && \
    all_body_ptrs[body_id & JPC_BODY_ID_INDEX_BITS]->id == body_id ? \
    all_body_ptrs[body_id & JPC_BODY_ID_INDEX_BITS] : NULL

/// Get direct access to all bodies. Not protected by a lock. Use with great care!
JPC_API JPC_Body **
JPC_PhysicsSystem_GetBodiesUnsafe(JPC_PhysicsSystem *in_physics_system);

JPC_API bool
JPC_PhysicsSystem_WereBodiesInContact(const JPC_PhysicsSystem *in_physics_system,
                                      JPC_BodyID in_body_id1,
                                      JPC_BodyID in_body_id2);
//--------------------------------------------------------------------------------------------------
//
// JPC_BodyLockInterface
//
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BodyLockInterface_LockRead(const JPC_BodyLockInterface *in_lock_interface,
                               JPC_BodyID in_body_id,
                               JPC_BodyLockRead *out_lock);
JPC_API void
JPC_BodyLockInterface_UnlockRead(const JPC_BodyLockInterface *in_lock_interface,
                                 JPC_BodyLockRead *io_lock);
JPC_API void
JPC_BodyLockInterface_LockWrite(const JPC_BodyLockInterface *in_lock_interface,
                                JPC_BodyID in_body_id,
                                JPC_BodyLockWrite *out_lock);
JPC_API void
JPC_BodyLockInterface_UnlockWrite(const JPC_BodyLockInterface *in_lock_interface,
                                  JPC_BodyLockWrite *io_lock);
//--------------------------------------------------------------------------------------------------
//
// (collector structs)
//
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_CollisionCollector_Reset(void *in_collector);

JPC_API void
JPC_CollisionCollector_UpdateEarlyOutFraction(void *in_collector, float in_fraction);

JPC_API void
JPC_CollisionCollector_ResetEarlyOutFraction(void *in_collector, float in_fraction);
//--------------------------------------------------------------------------------------------------
//
// (RayCastBodyCollector)
//
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_RayCastBodyCollector_ForceEarlyOut(void *in_collector);

JPC_API bool
JPC_RayCastBodyCollector_ShouldEarlyOut(void *in_collector);
//--------------------------------------------------------------------------------------------------
//
// (CollideShapeBodyCollector)
//
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_CollideShapeBodyCollector_ForceEarlyOut(void *in_collector);

JPC_API bool
JPC_CollideShapeBodyCollector_ShouldEarlyOut(void *in_collector);
//--------------------------------------------------------------------------------------------------
//
// (CastShapeBodyCollector)
//
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_CastShapeBodyCollector_ForceEarlyOut(void *in_collector);

JPC_API bool
JPC_CastShapeBodyCollector_ShouldEarlyOut(void *in_collector);
//--------------------------------------------------------------------------------------------------
//
// (CastRayCollector)
//
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_CastRayCollector_ForceEarlyOut(void *in_collector);

JPC_API bool
JPC_CastRayCollector_ShouldEarlyOut(void *in_collector);
//--------------------------------------------------------------------------------------------------
//
// (CastShapeCollector)
//
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_CastShapeCollector_ForceEarlyOut(void *in_collector);

JPC_API bool
JPC_CastShapeCollector_ShouldEarlyOut(void *in_collector);
//--------------------------------------------------------------------------------------------------
//
// (CollidePointCollector)
//
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_CollidePointCollector_ForceEarlyOut(void *in_collector);

JPC_API bool
JPC_CollidePointCollector_ShouldEarlyOut(void *in_collector);
//--------------------------------------------------------------------------------------------------
//
// (CollideShapeCollector)
//
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_CollideShapeCollector_ForceEarlyOut(void *in_collector);

JPC_API bool
JPC_CollideShapeCollector_ShouldEarlyOut(void *in_collector);
//--------------------------------------------------------------------------------------------------
//
// (TransformedShapeCollector)
//
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_TransformedShapeCollector_ForceEarlyOut(void *in_collector);

JPC_API bool
JPC_TransformedShapeCollector_ShouldEarlyOut(void *in_collector);
//--------------------------------------------------------------------------------------------------
//
// JPC_BroadPhaseQuery
//
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_BroadPhaseQuery_CastRay(const JPC_BroadPhaseQuery *in_query,
                            const JPC_RayCast *in_ray,
                            void *io_collector,
                            const void *in_broad_phase_layer_filter, // Can be NULL (no filter)
                            const void *in_object_layer_filter); // Can be NULL (no filter)

JPC_API void
JPC_BroadPhaseQuery_CollideAABox(const JPC_BroadPhaseQuery *in_query,
                                 const JPC_AABox *in_box,
                                 void *io_collector,
                                 const void *in_broad_phase_layer_filter, // Can be NULL (no filter)
                                 const void *in_object_layer_filter); // Can be NULL (no filter)

JPC_API void
JPC_BroadPhaseQuery_CollideSphere(const JPC_BroadPhaseQuery *in_query,
                                  const float in_center[3],
                                  float in_radius,
                                  void *io_collector,
                                  const void *in_broad_phase_layer_filter, // Can be NULL (no filter)
                                  const void *in_object_layer_filter); // Can be NULL (no filter)

JPC_API void
JPC_BroadPhaseQuery_CollidePoint(const JPC_BroadPhaseQuery *in_query,
                                 const float in_point[3],
                                 void *io_collector,
                                 const void *in_broad_phase_layer_filter, // Can be NULL (no filter)
                                 const void *in_object_layer_filter); // Can be NULL (no filter)

JPC_API void
JPC_BroadPhaseQuery_CollideOrientedBox(const JPC_BroadPhaseQuery *in_query,
                                       const JPC_OrientedBox *in_box,
                                       void *io_collector,
                                       const void *in_broad_phase_layer_filter, // Can be NULL (no filter)
                                       const void *in_object_layer_filter); // Can be NULL (no filter)

JPC_API void
JPC_BroadPhaseQuery_CastAABox(const JPC_BroadPhaseQuery *in_query,
                              const JPC_AABoxCast *in_box,
                              void *io_collector,
                              const void *in_broad_phase_layer_filter, // Can be NULL (no filter)
                              const void *in_object_layer_filter); // Can be NULL (no filter)
//--------------------------------------------------------------------------------------------------
//
// JPC_NarrowPhaseQuery
//
//--------------------------------------------------------------------------------------------------
JPC_API bool
JPC_NarrowPhaseQuery_GetCastRay(const JPC_NarrowPhaseQuery *in_query,
                                const JPC_RRayCast *in_ray,
                                JPC_RayCastResult *io_hit, // *Must* be default initialized (see JPC_RayCastResult)
                                const void *in_broad_phase_layer_filter, // Can be NULL (no filter)
                                const void *in_object_layer_filter, // Can be NULL (no filter)
                                const void *in_body_filter); // Can be NULL (no filter)

JPC_API void
JPC_NarrowPhaseQuery_CollectCastRay(const JPC_NarrowPhaseQuery *in_query,
                                    const JPC_RRayCast *in_ray,
                                    const JPC_RayCastSettings *in_settings,
                                    void *io_collector,
                                    const void *in_broad_phase_layer_filter, // Can be NULL (no filter)
                                    const void *in_object_layer_filter, // Can be NULL (no filter)
                                    const void *in_body_filter, // Can be NULL (no filter)
                                    const void *in_shape_filter); // Can be NULL (no filter)

JPC_API void
JPC_NarrowPhaseQuery_CollidePoint(const JPC_NarrowPhaseQuery *in_query,
                                  const JPC_Real in_point[3],
                                  void *io_collector,
                                  const void *in_broad_phase_layer_filter, // Can be NULL (no filter)
                                  const void *in_object_layer_filter, // Can be NULL (no filter)
                                  const void *in_body_filter, // Can be NULL (no filter)
                                  const void *in_shape_filter); // Can be NULL (no filter)

JPC_API void
JPC_NarrowPhaseQuery_CollideShape(const JPC_NarrowPhaseQuery *in_query,
                                  const JPC_Shape *in_shape,
                                  const float in_shape_scale[3],
                                  const float in_com_rotation[9],
                                  const JPC_Real in_com_translation[3],
                                  const JPC_CollideShapeSettings *in_settings,
                                  const JPC_Real in_base_offset[3],
                                  void *io_collector,
                                  const void *in_broad_phase_layer_filter, // Can be NULL (no filter)
                                  const void *in_object_layer_filter, // Can be NULL (no filter)
                                  const void *in_body_filter, // Can be NULL (no filter)
                                  const void *in_shape_filter); // Can be NULL (no filter)

// TODO
//JPC_API void
//JPC_NarrowPhaseQuery_CastShape(const JPC_NarrowPhaseQuery *in_query,
//                               const JPC_RShapeCast *in_shape_cast,
//                               const JPC_ShapeCastSettings *in_settings,
//                               const JPC_Real in_base_offset[3],
//                               void *io_collector,
//                               const void *in_broad_phase_layer_filter, // Can be NULL (no filter)
//                               const void *in_object_layer_filter, // Can be NULL (no filter)
//                               const void *in_body_filter, // Can be NULL (no filter)
//                               const void *in_shape_filter); // Can be NULL (no filter)

JPC_API void
JPC_NarrowPhaseQuery_CollectTransformedShapes(const JPC_NarrowPhaseQuery *in_query,
                                              const JPC_AABox *in_box,
                                              void *io_collector,
                                              const void *in_broad_phase_layer_filter, // Can be NULL (no filter)
                                              const void *in_object_layer_filter, // Can be NULL (no filter)
                                              const void *in_body_filter, // Can be NULL (no filter)
                                              const void *in_shape_filter); // Can be NULL (no filter)
//--------------------------------------------------------------------------------------------------
//
// JPC_SphereShape (-> JPC_ConvexShape -> JPC_Shape)
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_SphereShape *
JPC_SphereShape_Create(float in_radius, const JPC_PhysicsMaterial *in_material);

JPC_API float
JPC_SphereShape_GetRadius(const JPC_SphereShape *in_shape);
//--------------------------------------------------------------------------------------------------
//
// JPC_BoxShape (-> JPC_ConvexShape -> JPC_Shape)
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_BoxShape *
JPC_BoxShape_Create(const float in_half_extent[3], float in_convex_radius, const JPC_PhysicsMaterial *in_material);

JPC_API void
JPC_BoxShape_GetHalfExtent(const JPC_BoxShape *in_shape, float out_half_extent[3]);
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
                         const JPC_PhysicsMaterial *in_material);

JPC_API float
JPC_TriangleShape_GetConvexRadius(const JPC_TriangleShape *in_shape);
//--------------------------------------------------------------------------------------------------
//
// JPC_CapsuleShape (-> JPC_ConvexShape -> JPC_Shape)
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_CapsuleShape *
JPC_CapsuleShape_Create(float in_half_height_of_cylinder,
                        float in_radius,
                        const JPC_PhysicsMaterial *in_material);

JPC_API float
JPC_CapsuleShape_GetRadius(const JPC_CapsuleShape *in_shape);

JPC_API float
JPC_CapsuleShape_GetHalfHeightOfCylinder(const JPC_CapsuleShape *in_shape);
//--------------------------------------------------------------------------------------------------
//
// JPC_CylinderShape (-> JPC_ConvexShape -> JPC_Shape)
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_CylinderShape *
JPC_CylinderShape_Create(float in_half_height,
                         float in_radius,
                         float in_convex_radius,
                         const JPC_PhysicsMaterial *in_material);

JPC_API float
JPC_CylinderShape_GetHalfHeight(const JPC_CylinderShape *in_shape);

JPC_API float
JPC_CylinderShape_GetRadius(const JPC_CylinderShape *in_shape);
//--------------------------------------------------------------------------------------------------
//
// JPC_ShapeSettings
//
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_ShapeSettings_AddRef(JPC_ShapeSettings *in_settings);

JPC_API void
JPC_ShapeSettings_Release(JPC_ShapeSettings *in_settings);

JPC_API uint32_t
JPC_ShapeSettings_GetRefCount(const JPC_ShapeSettings *in_settings);

/// First call creates the shape, subsequent calls return the same pointer and increments reference count.
/// Call `JPC_Shape_Release()` when you don't need returned pointer anymore.
JPC_API JPC_ShapeResult
JPC_ShapeSettings_CreateShape(const JPC_ShapeSettings *in_settings);

JPC_API uint64_t
JPC_ShapeSettings_GetUserData(const JPC_ShapeSettings *in_settings);

JPC_API void
JPC_ShapeSettings_SetUserData(JPC_ShapeSettings *in_settings, uint64_t in_user_data);
//--------------------------------------------------------------------------------------------------
//
// JPC_ConvexShapeSettings (-> JPC_ShapeSettings)
//
//--------------------------------------------------------------------------------------------------
JPC_API const JPC_PhysicsMaterial *
JPC_ConvexShapeSettings_GetMaterial(const JPC_ConvexShapeSettings *in_settings);

JPC_API void
JPC_ConvexShapeSettings_SetMaterial(JPC_ConvexShapeSettings *in_settings,
                                    const JPC_PhysicsMaterial *in_material);

JPC_API float
JPC_ConvexShapeSettings_GetDensity(const JPC_ConvexShapeSettings *in_settings);

JPC_API void
JPC_ConvexShapeSettings_SetDensity(JPC_ConvexShapeSettings *in_settings, float in_density);
//--------------------------------------------------------------------------------------------------
//
// JPC_BoxShapeSettings (-> JPC_ConvexShapeSettings -> JPC_ShapeSettings)
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_BoxShapeSettings *
JPC_BoxShapeSettings_Create(const float in_half_extent[3],
                            float in_convex_radius,
                            const JPC_PhysicsMaterial *in_material);

JPC_API void
JPC_BoxShapeSettings_GetHalfExtent(const JPC_BoxShapeSettings *in_settings, float out_half_extent[3]);

JPC_API void
JPC_BoxShapeSettings_SetHalfExtent(JPC_BoxShapeSettings *in_settings, const float in_half_extent[3]);

JPC_API float
JPC_BoxShapeSettings_GetConvexRadius(const JPC_BoxShapeSettings *in_settings);

JPC_API void
JPC_BoxShapeSettings_SetConvexRadius(JPC_BoxShapeSettings *in_settings, float in_convex_radius);
//--------------------------------------------------------------------------------------------------
//
// JPC_SphereShapeSettings (-> JPC_ConvexShapeSettings -> JPC_ShapeSettings)
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_SphereShapeSettings *
JPC_SphereShapeSettings_Create(float in_radius, const JPC_PhysicsMaterial *in_material);

JPC_API float
JPC_SphereShapeSettings_GetRadius(const JPC_SphereShapeSettings *in_settings);

JPC_API void
JPC_SphereShapeSettings_SetRadius(JPC_SphereShapeSettings *in_settings, float in_radius);
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
                                 const JPC_PhysicsMaterial *in_material);

JPC_API void
JPC_TriangleShapeSettings_SetVertices(JPC_TriangleShapeSettings *in_settings,
                                      const float in_v1[3],
                                      const float in_v2[3],
                                      const float in_v3[3]);
JPC_API void
JPC_TriangleShapeSettings_GetVertices(const JPC_TriangleShapeSettings *in_settings,
                                      float out_v1[3],
                                      float out_v2[3],
                                      float out_v3[3]);
JPC_API float
JPC_TriangleShapeSettings_GetConvexRadius(const JPC_TriangleShapeSettings *in_settings);

JPC_API void
JPC_TriangleShapeSettings_SetConvexRadius(JPC_TriangleShapeSettings *in_settings,
                                          float in_convex_radius);
//--------------------------------------------------------------------------------------------------
//
// JPC_CapsuleShapeSettings (-> JPC_ConvexShapeSettings -> JPC_ShapeSettings)
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_CapsuleShapeSettings *
JPC_CapsuleShapeSettings_Create(float in_half_height_of_cylinder,
                                float in_radius,
                                const JPC_PhysicsMaterial *in_material);

JPC_API float
JPC_CapsuleShapeSettings_GetHalfHeight(const JPC_CapsuleShapeSettings *in_settings);

JPC_API void
JPC_CapsuleShapeSettings_SetHalfHeight(JPC_CapsuleShapeSettings *in_settings,
                                       float in_half_height_of_cylinder);
JPC_API float
JPC_CapsuleShapeSettings_GetRadius(const JPC_CapsuleShapeSettings *in_settings);

JPC_API void
JPC_CapsuleShapeSettings_SetRadius(JPC_CapsuleShapeSettings *in_settings, float in_radius);
//--------------------------------------------------------------------------------------------------
//
// JPC_TaperedCapsuleShapeSettings (-> JPC_ConvexShapeSettings -> JPC_ShapeSettings)
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_TaperedCapsuleShapeSettings *
JPC_TaperedCapsuleShapeSettings_Create(float in_half_height,
                                       float in_top_radius,
                                       float in_bottom_radius,
                                       const JPC_PhysicsMaterial *in_material);

JPC_API float
JPC_TaperedCapsuleShapeSettings_GetHalfHeight(const JPC_TaperedCapsuleShapeSettings *in_settings);

JPC_API void
JPC_TaperedCapsuleShapeSettings_SetHalfHeight(JPC_TaperedCapsuleShapeSettings *in_settings,
                                              float in_half_height);
JPC_API float
JPC_TaperedCapsuleShapeSettings_GetTopRadius(const JPC_TaperedCapsuleShapeSettings *in_settings);

JPC_API void
JPC_TaperedCapsuleShapeSettings_SetTopRadius(JPC_TaperedCapsuleShapeSettings *in_settings, float in_top_radius);

JPC_API float
JPC_TaperedCapsuleShapeSettings_GetBottomRadius(const JPC_TaperedCapsuleShapeSettings *in_settings);

JPC_API void
JPC_TaperedCapsuleShapeSettings_SetBottomRadius(JPC_TaperedCapsuleShapeSettings *in_settings,
                                                float in_bottom_radius);
//--------------------------------------------------------------------------------------------------
//
// JPC_CylinderShapeSettings (-> JPC_ConvexShapeSettings -> JPC_ShapeSettings)
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_CylinderShapeSettings *
JPC_CylinderShapeSettings_Create(float in_half_height,
                                 float in_radius,
                                 float in_convex_radius,
                                 const JPC_PhysicsMaterial *in_material);

JPC_API float
JPC_CylinderShapeSettings_GetConvexRadius(const JPC_CylinderShapeSettings *in_settings);

JPC_API void
JPC_CylinderShapeSettings_SetConvexRadius(JPC_CylinderShapeSettings *in_settings, float in_convex_radius);

JPC_API float
JPC_CylinderShapeSettings_GetHalfHeight(const JPC_CylinderShapeSettings *in_settings);

JPC_API void
JPC_CylinderShapeSettings_SetHalfHeight(JPC_CylinderShapeSettings *in_settings, float in_half_height);

JPC_API float
JPC_CylinderShapeSettings_GetRadius(const JPC_CylinderShapeSettings *in_settings);

JPC_API void
JPC_CylinderShapeSettings_SetRadius(JPC_CylinderShapeSettings *in_settings, float in_radius);
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
                                   const JPC_PhysicsMaterial *in_material);

JPC_API float
JPC_ConvexHullShapeSettings_GetMaxConvexRadius(const JPC_ConvexHullShapeSettings *in_settings);

JPC_API void
JPC_ConvexHullShapeSettings_SetMaxConvexRadius(JPC_ConvexHullShapeSettings *in_settings,
                                               float in_max_convex_radius);
JPC_API float
JPC_ConvexHullShapeSettings_GetMaxErrorConvexRadius(const JPC_ConvexHullShapeSettings *in_settings);

JPC_API void
JPC_ConvexHullShapeSettings_SetMaxErrorConvexRadius(JPC_ConvexHullShapeSettings *in_settings,
                                                    float in_max_err_convex_radius);
JPC_API float
JPC_ConvexHullShapeSettings_GetHullTolerance(const JPC_ConvexHullShapeSettings *in_settings);

JPC_API void
JPC_ConvexHullShapeSettings_SetHullTolerance(JPC_ConvexHullShapeSettings *in_settings,
                                             float in_hull_tolerance);
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
                                    uint32_t in_num_materials);

JPC_API void
JPC_HeightFieldShapeSettings_GetOffset(const JPC_HeightFieldShapeSettings *in_settings, float out_offset[3]);

JPC_API void
JPC_HeightFieldShapeSettings_SetOffset(JPC_HeightFieldShapeSettings *in_settings, const float in_offset[3]);

JPC_API void
JPC_HeightFieldShapeSettings_GetScale(const JPC_HeightFieldShapeSettings *in_settings, float out_scale[3]);

JPC_API void
JPC_HeightFieldShapeSettings_SetScale(JPC_HeightFieldShapeSettings *in_settings, const float in_scale[3]);

JPC_API uint32_t
JPC_HeightFieldShapeSettings_GetBlockSize(const JPC_HeightFieldShapeSettings *in_settings);

JPC_API void
JPC_HeightFieldShapeSettings_SetBlockSize(JPC_HeightFieldShapeSettings *in_settings, uint32_t in_block_size);

JPC_API uint32_t
JPC_HeightFieldShapeSettings_GetBitsPerSample(const JPC_HeightFieldShapeSettings *in_settings);

JPC_API void
JPC_HeightFieldShapeSettings_SetBitsPerSample(JPC_HeightFieldShapeSettings *in_settings, uint32_t in_num_bits);
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
                             uint32_t in_num_materials);
JPC_API uint32_t
JPC_MeshShapeSettings_GetMaxTrianglesPerLeaf(const JPC_MeshShapeSettings *in_settings);

JPC_API void
JPC_MeshShapeSettings_SetMaxTrianglesPerLeaf(JPC_MeshShapeSettings *in_settings, uint32_t in_max_triangles);

JPC_API void
JPC_MeshShapeSettings_Sanitize(JPC_MeshShapeSettings *in_settings);
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_CompoundShapeSettings_AddShapeSettings(JPC_CompoundShapeSettings *in_settings,
                                           const float in_position[3],
                                           const float in_rotation[4],
                                           const JPC_ShapeSettings *in_shape,
                                           uint32_t in_user_data);

JPC_API void
JPC_CompoundShapeSettings_AddShape(JPC_CompoundShapeSettings *in_settings,
                                   const float in_position[3],
                                   const float in_rotation[4],
                                   const JPC_Shape *in_shape,
                                   uint32_t in_user_data);
//--------------------------------------------------------------------------------------------------
//
// JPC_StaticCompoundShapeSettings (-> JPC_CompoundShapeSettings -> JPC_ShapeSettings)
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_StaticCompoundShapeSettings *
JPC_StaticCompoundShapeSettings_Create();

JPC_API JPC_ShapeResult
JPC_StaticCompoundShapeSettings_CreateShape(const JPC_StaticCompoundShapeSettings *in_settings,
                                            JPC_TempAllocator *in_temp_allocator);
//--------------------------------------------------------------------------------------------------
//
// JPC_MutableCompoundShapeSettings (-> JPC_CompoundShapeSettings -> JPC_ShapeSettings)
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_MutableCompoundShapeSettings *
JPC_MutableCompoundShapeSettings_Create();
//--------------------------------------------------------------------------------------------------
//
// JPC_DecoratedShapeSettings (-> JPC_ShapeSettings)
//
//--------------------------------------------------------------------------------------------------
JPC_API const JPC_ShapeSettings *
JPC_DecoratedShapeSettings_GetInnerShape(const JPC_DecoratedShapeSettings *in_settings);

JPC_API void
JPC_DecoratedShapeSettings_SetInnerShape(JPC_DecoratedShapeSettings *in_settings, const JPC_ShapeSettings *in_shape);

JPC_API const JPC_Shape *
JPC_DecoratedShapeSettings_GetInnerShapePtr(const JPC_DecoratedShapeSettings *in_settings);

JPC_API void
JPC_DecoratedShapeSettings_SetInnerShapePtr(JPC_DecoratedShapeSettings *in_settings, const JPC_Shape *in_shape);
//--------------------------------------------------------------------------------------------------
//
// JPC_ScaledShapeSettings (-> JPC_DecoratedShapeSettings -> JPC_ShapeSettings)
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_ScaledShapeSettings *
JPC_ScaledShapeSettings_CreateFromShape(const JPC_Shape *in_shape, const float in_scale[3]);

JPC_API JPC_ScaledShapeSettings *
JPC_ScaledShapeSettings_CreateFromSettings(const JPC_ShapeSettings *in_shape, const float in_scale[3]);

JPC_API void
JPC_ScaledShapeSettings_GetScale(const JPC_ScaledShapeSettings *in_settings, float out_scale[3]);

JPC_API void
JPC_ScaledShapeSettings_SetScale(JPC_ScaledShapeSettings *in_settings, const float in_scale[3]);
//--------------------------------------------------------------------------------------------------
//
// JPC_RotatedTranslatedShapeSettings (-> JPC_DecoratedShapeSettings -> JPC_ShapeSettings)
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_RotatedTranslatedShapeSettings *
JPC_RotatedTranslatedShapeSettings_CreateFromShape(const JPC_Shape *in_shape,
                                                   const float in_position[3],
                                                   const float in_rotation[4]);

JPC_API JPC_RotatedTranslatedShapeSettings *
JPC_RotatedTranslatedShapeSettings_CreateFromSettings(const JPC_ShapeSettings *in_shape,
                                                      const float in_position[3],
                                                      const float in_rotation[4]);

JPC_API void
JPC_RotatedTranslatedShapeSettings_GetPosition(const JPC_RotatedTranslatedShapeSettings *in_settings, float out_position[3]);

JPC_API void
JPC_RotatedTranslatedShapeSettings_SetPosition(JPC_RotatedTranslatedShapeSettings *in_settings, const float in_position[3]);

JPC_API void
JPC_RotatedTranslatedShapeSettings_GetRotation(const JPC_RotatedTranslatedShapeSettings *in_settings, float out_rotation[4]);

JPC_API void
JPC_RotatedTranslatedShapeSettings_SetRotation(JPC_RotatedTranslatedShapeSettings *in_settings, const float in_rotation[4]);
//--------------------------------------------------------------------------------------------------
//
// JPC_OffsetCenterOfMassShapeSettings (-> JPC_DecoratedShapeSettings -> JPC_ShapeSettings)
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_OffsetCenterOfMassShapeSettings *
JPC_OffsetCenterOfMassShapeSettings_CreateFromShape(const JPC_Shape *in_shape, const float in_offset[3]);

JPC_API JPC_OffsetCenterOfMassShapeSettings *
JPC_OffsetCenterOfMassShapeSettings_CreateFromSettings(const JPC_ShapeSettings *in_shape, const float in_offset[3]);

JPC_API void
JPC_OffsetCenterOfMassShapeSettings_GetOffset(const JPC_OffsetCenterOfMassShapeSettings *in_settings, float out_offset[3]);

JPC_API void
JPC_OffsetCenterOfMassShapeSettings_SetOffset(JPC_OffsetCenterOfMassShapeSettings *in_settings, const float in_offset[3]);
//--------------------------------------------------------------------------------------------------
//
// JPC_Shape
//
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_Shape_AddRef(JPC_Shape *in_shape);

JPC_API void
JPC_Shape_Release(JPC_Shape *in_shape);

JPC_API uint32_t
JPC_Shape_GetRefCount(const JPC_Shape *in_shape);

JPC_API JPC_ShapeType
JPC_Shape_GetType(const JPC_Shape *in_shape);

JPC_API JPC_ShapeSubType
JPC_Shape_GetSubType(const JPC_Shape *in_shape);

JPC_API uint64_t
JPC_Shape_GetUserData(const JPC_Shape *in_shape);

JPC_API void
JPC_Shape_SetUserData(JPC_Shape *in_shape, uint64_t in_user_data);

JPC_API bool
JPC_Shape_MustBeStatic(const JPC_Shape *in_shape);

JPC_API void
JPC_Shape_GetCenterOfMass(const JPC_Shape *in_shape, float out_center_of_mass[3]);

JPC_API void
JPC_Shape_GetLocalBounds(const JPC_Shape *in_shape, JPC_AABox *out_local_bounds);

JPC_API uint32_t
JPC_Shape_GetSubShapeIDBitsRecursive(const JPC_Shape *in_shape);

JPC_API void
JPC_Shape_GetWorldSpaceBounds(const JPC_Shape *in_shape,
                              const float in_center_of_mass_transform[16],
                              const float in_scale[3],
                              JPC_AABox *out_world_space_bounds);

JPC_API float
JPC_Shape_GetInnerRadius(const JPC_Shape *in_shape);

// TODO
//JPC_API void
//JPC_Shape_GetMassProperties(const JPC_Shape *in_shape, JPC_MassProperties* out_mass_properties);

JPC_API const JPC_PhysicsMaterial *
JPC_Shape_GetMaterial(const JPC_Shape *in_shape, JPC_SubShapeID in_sub_shape_id);

JPC_API void
JPC_Shape_GetSurfaceNormal(const JPC_Shape *in_shape,
                           JPC_SubShapeID in_sub_shape_id,
                           const float in_local_surface_position[3],
                           float out_surface_normal[3]);

// TODO GetSupportingFace

JPC_API uint64_t
JPC_Shape_GetSubShapeUserData(const JPC_Shape *in_shape, JPC_SubShapeID in_sub_shape_id);

JPC_API JPC_TransformedShape
JPC_Shape_GetSubShapeTransformedShape(const JPC_Shape *in_shape,
                                      JPC_SubShapeID in_sub_shape_id,
                                      const float in_position_com[3],
                                      const float in_rotation[4],
                                      const float in_scale[3]);

JPC_API float
JPC_Shape_GetVolume(const JPC_Shape *in_shape);

JPC_API bool
JPC_Shape_IsValidScale(const JPC_Shape *in_shape, const float in_scale[3]);
//--------------------------------------------------------------------------------------------------
//
// JPC_ConvexShape (-> JPC_Shape)
//
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_ConvexShape_SetMaterial(JPC_ConvexShape *in_shape, const JPC_PhysicsMaterial *in_material);

JPC_API const JPC_PhysicsMaterial *
JPC_ConvexShape_GetMaterial(const JPC_ConvexShape *in_shape);

JPC_API void
JPC_ConvexShape_SetDensity(JPC_ConvexShape *in_shape, float in_density);

JPC_API float
JPC_ConvexShape_GetDensity(const JPC_ConvexShape *in_shape);
//--------------------------------------------------------------------------------------------------
//
// JPC_DecoratedShape (-> JPC_Shape)
//
//--------------------------------------------------------------------------------------------------
JPC_API const JPC_Shape *
JPC_DecoratedShape_GetInnerShape(const JPC_DecoratedShape *in_shape);
//--------------------------------------------------------------------------------------------------
//
// JPC_ScaledShape (-> JPC_DecoratedShape -> JPC_Shape)
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_ScaledShape *
JPC_ScaledShape_Create(const JPC_Shape *in_shape, const float in_scale[3]);

JPC_API void
JPC_ScaledShape_GetScale(const JPC_ScaledShape *in_shape, float out_scale[3]);
//--------------------------------------------------------------------------------------------------
//
// JPC_RotatedTranslatedShape (-> JPC_DecoratedShape -> JPC_Shape)
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_RotatedTranslatedShape *
JPC_RotatedTranslatedShape_Create(const JPC_Shape *in_shape, const float in_position[3], const float in_rotation[4]);

JPC_API void
JPC_RotatedTranslatedShape_GetPosition(const JPC_RotatedTranslatedShape *in_shape, float out_position[3]);

JPC_API void
JPC_RotatedTranslatedShape_GetRotation(const JPC_RotatedTranslatedShape *in_shape, float out_rotation[4]);
//--------------------------------------------------------------------------------------------------
//
// JPC_OffsetCenterOfMassShape (-> JPC_DecoratedShape -> JPC_Shape)
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_OffsetCenterOfMassShapeSettings *
JPC_OffsetCenterOfMassShape_Create(const JPC_Shape *in_shape, const float in_offset[3]);

JPC_API void
JPC_OffsetCenterOfMassShape_GetOffset(const JPC_OffsetCenterOfMassShape *in_shape, float out_offset[3]);
//--------------------------------------------------------------------------------------------------
//
// JPC_BodyInterface
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_Body *
JPC_BodyInterface_CreateBody(JPC_BodyInterface *in_iface, const JPC_BodyCreationSettings *in_setting);

JPC_API JPC_Body *
JPC_BodyInterface_CreateBodyWithID(JPC_BodyInterface *in_iface,
                                   JPC_BodyID in_body_id,
                                   const JPC_BodyCreationSettings *in_settings);

JPC_API JPC_Body *
JPC_BodyInterface_CreateBodyWithoutID(JPC_BodyInterface *in_iface,
                                      const JPC_BodyCreationSettings *in_settings);

JPC_API void
JPC_BodyInterface_DestroyBodyWithoutID(JPC_BodyInterface *in_iface, JPC_Body *in_body);

JPC_API bool
JPC_BodyInterface_AssignNextBodyID(JPC_BodyInterface *in_iface, JPC_Body *in_body);

JPC_API bool
JPC_BodyInterface_AssignBodyID(JPC_BodyInterface *in_iface, JPC_Body *in_body, JPC_BodyID in_body_id);

JPC_API JPC_Body *
JPC_BodyInterface_UnassignBodyID(JPC_BodyInterface *in_iface, JPC_BodyID in_body_id);

JPC_API void
JPC_BodyInterface_UnassignBodyIDs(JPC_BodyInterface *in_iface,
                                  const JPC_BodyID *in_body_ids,
                                  int in_number,
                                  JPC_Body **out_bodies);

JPC_API void
JPC_BodyInterface_DestroyBody(JPC_BodyInterface *in_iface, JPC_BodyID in_body_id);

JPC_API void
JPC_BodyInterface_DestroyBodies(JPC_BodyInterface *in_iface, const JPC_BodyID *in_body_ids, int in_number);

JPC_API void
JPC_BodyInterface_AddBody(JPC_BodyInterface *in_iface, JPC_BodyID in_body_id, JPC_Activation in_mode);

JPC_API void
JPC_BodyInterface_RemoveBody(JPC_BodyInterface *in_iface, JPC_BodyID in_body_id);

JPC_API bool
JPC_BodyInterface_IsAdded(const JPC_BodyInterface *in_iface, JPC_BodyID in_body_id);

JPC_API JPC_BodyID
JPC_BodyInterface_CreateAndAddBody(JPC_BodyInterface *in_iface,
                                   const JPC_BodyCreationSettings *in_settings,
                                   JPC_Activation in_mode);

JPC_API JPC_BodyInterfaceAddState
JPC_BodyInterface_AddBodiesPrepare(JPC_BodyInterface *in_iface,
                                   JPC_BodyID *io_bodies,
                                   int in_number);

JPC_API void
JPC_BodyInterface_AddBodiesFinalize(JPC_BodyInterface *in_iface,
                                    JPC_BodyID *io_bodies,
                                    int in_number,
                                    JPC_BodyInterfaceAddState *in_add_state,
                                    JPC_Activation in_activation_mode);

JPC_API void
JPC_BodyInterface_AddBodiesAbort(JPC_BodyInterface *in_iface,
                                 JPC_BodyID *io_bodies,
                                 int in_number,
                                 JPC_BodyInterfaceAddState *in_add_state);

JPC_API void
JPC_BodyInterface_RemoveBodies(JPC_BodyInterface *in_iface,
                               JPC_BodyID *io_bodies,
                               int in_number);

JPC_API void
JPC_BodyInterface_ActivateBody(JPC_BodyInterface *in_iface, JPC_BodyID in_body_id);

JPC_API void
JPC_BodyInterface_ActivateBodies(JPC_BodyInterface *in_iface,
                                 const JPC_BodyID *in_body_ids,
                                 int in_number);

JPC_API void
JPC_BodyInterface_DeactivateBody(JPC_BodyInterface *in_iface, JPC_BodyID in_body_id);

JPC_API void
JPC_BodyInterface_DeactivateBodies(JPC_BodyInterface *in_iface,
                                   const JPC_BodyID *in_body_ids,
                                   int in_number);

JPC_API bool
JPC_BodyInterface_IsActive(const JPC_BodyInterface *in_iface, JPC_BodyID in_body_id);

// TODO constraint
//JPC_API JPC_TwoBodyConstraint *
//JPC_BodyInterface_CreateConstraint(JPC_BodyInterface *in_iface,
//                                   const JPC_TwoBodyConstraintSettings *in_settings,
//                                   JPC_BodyID in_body_id1,
//                                   JPC_BodyID in_body_id2);
//
//JPC_API void
//JPC_BodyInterface_ActivateConstraint(JPC_BodyInterface *in_iface,
//                                     const JPC_TwoBodyConstraint *in_constraint);

JPC_API const JPC_Shape *
JPC_BodyInterface_GetShape(const JPC_BodyInterface *in_iface, JPC_BodyID in_body_id);

JPC_API void
JPC_BodyInterface_SetShape(const JPC_BodyInterface *in_iface,
                           JPC_BodyID in_body_id,
                           const JPC_Shape *in_shape,
                           bool in_update_mass_properties,
                           JPC_Activation in_activation_mode);

JPC_API void
JPC_BodyInterface_NotifyShapeChanged(const JPC_BodyInterface *in_iface,
                                     JPC_BodyID in_body_id,
                                     const float in_previous_center_of_mass[3],
                                     bool in_update_mass_properties,
                                     JPC_Activation in_activation_mode);

JPC_API void
JPC_BodyInterface_SetObjectLayer(JPC_BodyInterface *in_iface, JPC_BodyID in_body_id, JPC_ObjectLayer in_layer);

JPC_API JPC_ObjectLayer
JPC_BodyInterface_GetObjectLayer(const JPC_BodyInterface *in_iface, JPC_BodyID in_body_id);

JPC_API void
JPC_BodyInterface_SetPositionAndRotation(JPC_BodyInterface *in_iface,
                                         JPC_BodyID in_body_id,
                                         const JPC_Real in_position[3],
                                         const float in_rotation[4],
                                         JPC_Activation in_activation_mode);

JPC_API void
JPC_BodyInterface_SetPositionAndRotationWhenChanged(JPC_BodyInterface *in_iface,
                                                    JPC_BodyID in_body_id,
                                                    const JPC_Real in_position[3],
                                                    const float in_rotation[4],
                                                    JPC_Activation in_activation_mode);

JPC_API void
JPC_BodyInterface_GetPositionAndRotation(const JPC_BodyInterface *in_iface,
                                         JPC_BodyID in_body_id,
                                         JPC_Real out_position[3],
                                         float out_rotation[4]);

JPC_API void
JPC_BodyInterface_SetPosition(JPC_BodyInterface *in_iface,
                              JPC_BodyID in_body_id,
                              const JPC_Real in_position[3],
                              JPC_Activation in_activation_mode);

JPC_API void
JPC_BodyInterface_GetPosition(const JPC_BodyInterface *in_iface,
                              JPC_BodyID in_body_id,
                              JPC_Real out_position[3]);

JPC_API void
JPC_BodyInterface_GetCenterOfMassPosition(const JPC_BodyInterface *in_iface,
                                          JPC_BodyID in_body_id,
                                          JPC_Real out_position[3]);

JPC_API void
JPC_BodyInterface_SetRotation(JPC_BodyInterface *in_iface,
                              JPC_BodyID in_body_id,
                              const float in_rotation[4],
                              JPC_Activation in_activation_mode);

JPC_API void
JPC_BodyInterface_GetRotation(const JPC_BodyInterface *in_iface,
                              JPC_BodyID in_body_id,
                              float out_rotation[4]);

JPC_API void
JPC_BodyInterface_GetWorldTransform(const JPC_BodyInterface *in_iface,
                                    JPC_BodyID in_body_id,
                                    float out_rotation[9],
                                    JPC_Real out_translation[3]);

JPC_API void
JPC_BodyInterface_GetCenterOfMassTransform(const JPC_BodyInterface *in_iface,
                                           JPC_BodyID in_body_id,
                                           float out_rotation[9],
                                           JPC_Real out_translation[3]);

JPC_API void
JPC_BodyInterface_MoveKinematic(JPC_BodyInterface *in_iface,
                                JPC_BodyID in_body_id,
                                const JPC_Real in_target_position[3],
                                const float in_target_rotation[4],
                                float in_delta_time);

JPC_API void
JPC_BodyInterface_SetLinearAndAngularVelocity(JPC_BodyInterface *in_iface,
                                              JPC_BodyID in_body_id,
                                              const float in_linear_velocity[3],
                                              const float in_angular_velocity[3]);
JPC_API void
JPC_BodyInterface_GetLinearAndAngularVelocity(const JPC_BodyInterface *in_iface,
                                              JPC_BodyID in_body_id,
                                              float out_linear_velocity[3],
                                              float out_angular_velocity[3]);
JPC_API void
JPC_BodyInterface_SetLinearVelocity(JPC_BodyInterface *in_iface,
                                    JPC_BodyID in_body_id,
                                    const float in_velocity[3]);
JPC_API void
JPC_BodyInterface_GetLinearVelocity(const JPC_BodyInterface *in_iface,
                                    JPC_BodyID in_body_id,
                                    float out_velocity[3]);
JPC_API void
JPC_BodyInterface_AddLinearVelocity(JPC_BodyInterface *in_iface,
                                    JPC_BodyID in_body_id,
                                    const float in_velocity[3]);
JPC_API void
JPC_BodyInterface_AddLinearAndAngularVelocity(JPC_BodyInterface *in_iface,
                                              JPC_BodyID in_body_id,
                                              const float in_linear_velocity[3],
                                              const float in_angular_velocity[3]);
JPC_API void
JPC_BodyInterface_SetAngularVelocity(JPC_BodyInterface *in_iface,
                                     JPC_BodyID in_body_id,
                                     const float in_velocity[3]);
JPC_API void
JPC_BodyInterface_GetAngularVelocity(const JPC_BodyInterface *in_iface,
                                     JPC_BodyID in_body_id,
                                     float out_velocity[3]);
JPC_API void
JPC_BodyInterface_GetPointVelocity(const JPC_BodyInterface *in_iface,
                                   JPC_BodyID in_body_id,
                                   const JPC_Real in_point[3],
                                   float out_velocity[3]);

JPC_API void
JPC_BodyInterface_SetPositionRotationAndVelocity(JPC_BodyInterface *in_iface,
                                                 JPC_BodyID in_body_id,
                                                 const JPC_Real in_position[3],
                                                 const float in_rotation[4],
                                                 const float in_linear_velocity[3],
                                                 const float in_angular_velocity[3]);

JPC_API void
JPC_BodyInterface_AddForce(JPC_BodyInterface *in_iface, JPC_BodyID in_body_id, const float in_force[3]);

JPC_API void
JPC_BodyInterface_AddForceAtPosition(JPC_BodyInterface *in_iface,
                                     JPC_BodyID in_body_id,
                                     const float in_force[3],
                                     const JPC_Real in_position[3]);
JPC_API void
JPC_BodyInterface_AddTorque(JPC_BodyInterface *in_iface, JPC_BodyID in_body_id, const float in_torque[3]);

JPC_API void
JPC_BodyInterface_AddForceAndTorque(JPC_BodyInterface *in_iface,
                                    JPC_BodyID in_body_id,
                                    const float in_force[3],
                                    const float in_torque[3]);
JPC_API void
JPC_BodyInterface_AddImpulse(JPC_BodyInterface *in_iface, JPC_BodyID in_body_id, const float in_impulse[3]);

JPC_API void
JPC_BodyInterface_AddImpulseAtPosition(JPC_BodyInterface *in_iface,
                                       JPC_BodyID in_body_id,
                                       const float in_impulse[3],
                                       const JPC_Real in_position[3]);
JPC_API void
JPC_BodyInterface_AddAngularImpulse(JPC_BodyInterface *in_iface, JPC_BodyID in_body_id, const float in_impulse[3]);

JPC_API void
JPC_BodyInterface_SetMotionType(JPC_BodyInterface *in_iface,
                                JPC_BodyID in_body_id,
                                JPC_MotionType in_motion_type,
                                JPC_Activation in_activation_mode);

JPC_API JPC_MotionType
JPC_BodyInterface_GetMotionType(const JPC_BodyInterface *in_iface,
                                JPC_BodyID in_body_id);

JPC_API void
JPC_BodyInterface_SetMotionQuality(JPC_BodyInterface *in_iface,
                                   JPC_BodyID in_body_id,
                                   JPC_MotionQuality in_motion_quality);

JPC_API JPC_MotionQuality
JPC_BodyInterface_GetMotionQuality(const JPC_BodyInterface *in_iface,
                                   JPC_BodyID in_body_id);

JPC_API void
JPC_BodyInterface_GetInverseInertia(const JPC_BodyInterface *in_iface,
                                    JPC_BodyID in_body_id,
                                    float out_inverse_inertia[16]);

JPC_API void
JPC_BodyInterface_SetRestitution(JPC_BodyInterface *in_iface,
                                 JPC_BodyID in_body_id,
                                 float in_restitution);

JPC_API float
JPC_BodyInterface_GetRestitution(const JPC_BodyInterface *in_iface,
                                 JPC_BodyID in_body_id);

JPC_API void
JPC_BodyInterface_SetFriction(JPC_BodyInterface *in_iface,
                              JPC_BodyID in_body_id,
                              float in_friction);

JPC_API float
JPC_BodyInterface_GetFriction(const JPC_BodyInterface *in_iface,
                              JPC_BodyID in_body_id);

JPC_API void
JPC_BodyInterface_SetGravityFactor(JPC_BodyInterface *in_iface,
                                   JPC_BodyID in_body_id,
                                   float in_gravity_factor);

JPC_API float
JPC_BodyInterface_GetGravityFactor(const JPC_BodyInterface *in_iface,
                                   JPC_BodyID in_body_id);

JPC_API void
JPC_BodyInterface_GetTransformedShape(const JPC_BodyInterface *in_iface,
                                      JPC_BodyID in_body_id,
                                      JPC_TransformedShape *out_shape);

JPC_API uint64_t
JPC_BodyInterface_GetUserData(const JPC_BodyInterface *in_iface,
                              JPC_BodyID in_body_id);

JPC_API const JPC_PhysicsMaterial *
JPC_BodyInterface_GetMaterial(const JPC_BodyInterface *in_iface,
                              JPC_BodyID in_body_id,
                              JPC_SubShapeID in_sub_shape_id);

JPC_API void
JPC_BodyInterface_InvalidateContactCache(JPC_BodyInterface *in_iface,
                                         JPC_BodyID in_body_id);
//--------------------------------------------------------------------------------------------------
//
// JPC_Body
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_BodyID
JPC_Body_GetID(const JPC_Body *in_body);

JPC_API bool
JPC_Body_IsActive(const JPC_Body *in_body);

JPC_API bool
JPC_Body_IsStatic(const JPC_Body *in_body);

JPC_API bool
JPC_Body_IsKinematic(const JPC_Body *in_body);

JPC_API bool
JPC_Body_IsDynamic(const JPC_Body *in_body);

JPC_API bool
JPC_Body_CanBeKinematicOrDynamic(const JPC_Body *in_body);

JPC_API void
JPC_Body_SetIsSensor(JPC_Body *in_body, bool in_is_sensor);

JPC_API bool
JPC_Body_IsSensor(const JPC_Body *in_body);

JPC_API void
JPC_Body_SetUseManifoldReduction(JPC_Body *in_body, bool in_use_reduction);

JPC_API bool
JPC_Body_GetUseManifoldReduction(const JPC_Body *in_body);

JPC_API bool
JPC_Body_GetUseManifoldReductionWithBody(const JPC_Body *in_body, const JPC_Body *in_other);

JPC_API JPC_MotionType
JPC_Body_GetMotionType(const JPC_Body *in_body);

JPC_API void
JPC_Body_SetMotionType(JPC_Body *in_body, JPC_MotionType in_motion_type);

JPC_API JPC_BroadPhaseLayer
JPC_Body_GetBroadPhaseLayer(const JPC_Body *in_body);

JPC_API JPC_ObjectLayer
JPC_Body_GetObjectLayer(const JPC_Body *in_body);

JPC_API JPC_CollisionGroup *
JPC_Body_GetCollisionGroup(JPC_Body *in_body);

JPC_API void
JPC_Body_SetCollisionGroup(JPC_Body *in_body, const JPC_CollisionGroup *in_group);

JPC_API bool
JPC_Body_GetAllowSleeping(const JPC_Body *in_body);

JPC_API void
JPC_Body_SetAllowSleeping(JPC_Body *in_body, bool in_allow_sleeping);

JPC_API float
JPC_Body_GetFriction(const JPC_Body *in_body);

JPC_API void
JPC_Body_SetFriction(JPC_Body *in_body, float in_friction);

JPC_API float
JPC_Body_GetRestitution(const JPC_Body *in_body);

JPC_API void
JPC_Body_SetRestitution(JPC_Body *in_body, float in_restitution);

JPC_API void
JPC_Body_GetLinearVelocity(const JPC_Body *in_body, float out_linear_velocity[3]);

JPC_API void
JPC_Body_SetLinearVelocity(JPC_Body *in_body, const float in_linear_velocity[3]);

JPC_API void
JPC_Body_SetLinearVelocityClamped(JPC_Body *in_body, const float in_linear_velocity[3]);

JPC_API void
JPC_Body_GetAngularVelocity(const JPC_Body *in_body, float out_angular_velocity[3]);

JPC_API void
JPC_Body_SetAngularVelocity(JPC_Body *in_body, const float in_angular_velocity[3]);

JPC_API void
JPC_Body_SetAngularVelocityClamped(JPC_Body *in_body, const float in_angular_velocity[3]);

JPC_API void
JPC_Body_GetPointVelocityCOM(const JPC_Body *in_body,
                             const float in_point_relative_to_com[3],
                             float out_velocity[3]);
JPC_API void
JPC_Body_GetPointVelocity(const JPC_Body *in_body, const JPC_Real in_point[3], float out_velocity[3]);

JPC_API void
JPC_Body_AddForce(JPC_Body *in_body, const float in_force[3]);

JPC_API void
JPC_Body_AddForceAtPosition(JPC_Body *in_body, const float in_force[3], const JPC_Real in_position[3]);

JPC_API void
JPC_Body_AddTorque(JPC_Body *in_body, const float in_torque[3]);

JPC_API void
JPC_Body_GetAccumulatedForce(JPC_Body *in_body, float out_force[3]);

JPC_API void
JPC_Body_GetAccumulatedTorque(JPC_Body *in_body, float out_torque[3]);

JPC_API void
JPC_Body_GetInverseInertia(const JPC_Body *in_body, float out_inverse_inertia[16]);

JPC_API void
JPC_Body_AddImpulse(JPC_Body *in_body, const float in_impulse[3]);

JPC_API void
JPC_Body_AddImpulseAtPosition(JPC_Body *in_body, const float in_impulse[3], const JPC_Real in_position[3]);

JPC_API void
JPC_Body_AddAngularImpulse(JPC_Body *in_body, const float in_angular_impulse[3]);

JPC_API void
JPC_Body_MoveKinematic(JPC_Body *in_body,
                       const JPC_Real in_target_position[3],
                       const float in_target_rotation[4],
                       float in_delta_time);
JPC_API bool
JPC_Body_ApplyBuoyancyImpulse(JPC_Body *in_body,
                              const JPC_Real in_surface_position[3],
                              const float in_surface_normal[3],
                              float in_buoyancy,
                              float in_linear_drag,
                              float in_angular_drag,
                              const float in_fluid_velocity[3],
                              const float in_gravity[3],
                              float in_delta_time);
JPC_API bool
JPC_Body_IsInBroadPhase(const JPC_Body *in_body);

JPC_API bool
JPC_Body_IsCollisionCacheInvalid(const JPC_Body *in_body);

JPC_API const JPC_Shape *
JPC_Body_GetShape(const JPC_Body *in_body);

JPC_API void
JPC_Body_GetPosition(const JPC_Body *in_body, JPC_Real out_position[3]);

JPC_API void
JPC_Body_GetRotation(const JPC_Body *in_body, float out_rotation[4]);

JPC_API void
JPC_Body_GetWorldTransform(const JPC_Body *in_body, float out_rotation[9], JPC_Real out_translation[3]);

JPC_API void
JPC_Body_GetCenterOfMassPosition(const JPC_Body *in_body, JPC_Real out_position[3]);

JPC_API void
JPC_Body_GetCenterOfMassTransform(const JPC_Body *in_body,
                                  float out_rotation[9],
                                  JPC_Real out_translation[3]);
JPC_API void
JPC_Body_GetInverseCenterOfMassTransform(const JPC_Body *in_body,
                                         float out_rotation[9],
                                         JPC_Real out_translation[3]);
JPC_API void
JPC_Body_GetWorldSpaceBounds(const JPC_Body *in_body, float out_min[3], float out_max[3]);

JPC_API JPC_MotionProperties *
JPC_Body_GetMotionProperties(JPC_Body *in_body);

JPC_API uint64_t
JPC_Body_GetUserData(const JPC_Body *in_body);

JPC_API void
JPC_Body_SetUserData(JPC_Body *in_body, uint64_t in_user_data);

JPC_API void
JPC_Body_GetTransformedShape(JPC_Body *in_body, JPC_TransformedShape *out_shape);

JPC_API void
JPC_Body_GetBodyCreationSettings(JPC_Body *in_body, JPC_BodyCreationSettings *out_settings);

JPC_API void
JPC_Body_GetWorldSpaceSurfaceNormal(const JPC_Body *in_body,
                                    JPC_SubShapeID in_sub_shape_id,
                                    const JPC_Real in_position[3], // world space
                                    float out_normal_vector[3]);
//--------------------------------------------------------------------------------------------------
//
// JPC_BodyID
//
//--------------------------------------------------------------------------------------------------
JPC_API uint32_t
JPC_BodyID_GetIndex(JPC_BodyID in_body_id);

JPC_API uint8_t
JPC_BodyID_GetSequenceNumber(JPC_BodyID in_body_id);

JPC_API bool
JPC_BodyID_IsInvalid(JPC_BodyID in_body_id);
//--------------------------------------------------------------------------------------------------
//
// JPC_Constraint
//
//--------------------------------------------------------------------------------------------------
JPC_API void
JPC_Constraint_Release(JPC_Constraint *in_constraint);

JPC_API JPC_ConstraintType
JPC_Constraint_GetType(const JPC_Constraint *in_constraint);

JPC_API JPC_ConstraintSubType
JPC_Constraint_GetSubType(const JPC_Constraint *in_constraint);

JPC_API void
JPC_Constraint_SetNumVelocityStepsOverride(JPC_Constraint *in_constraint, uint32_t in_num_velocity_steps_override);

JPC_API uint32_t
JPC_Constraint_GetNumVelocityStepsOverride(const JPC_Constraint *in_constraint);

JPC_API void
JPC_Constraint_SetNumPositionStepsOverride(JPC_Constraint *in_constraint, uint32_t in_num_position_steps_override);

JPC_API uint32_t
JPC_Constraint_GetNumPositionStepsOverride(const JPC_Constraint *in_constraint);

JPC_API void
JPC_Constraint_SetEnabled(JPC_Constraint *in_constraint, bool in_enabled);

JPC_API bool
JPC_Constraint_GetEnabled(const JPC_Constraint *in_constraint);
//--------------------------------------------------------------------------------------------------
//
// JPC_TwoBodyConstraint
//
//--------------------------------------------------------------------------------------------------
JPC_API JPC_Body *
JPC_TwoBodyConstraint_GetBody1(const JPC_TwoBodyConstraint *in_constraint);

JPC_API JPC_Body *
JPC_TwoBodyConstraint_GetBody2(const JPC_TwoBodyConstraint *in_constraint);
//--------------------------------------------------------------------------------------------------
// JoltJava: Java support
JPC_API uint32_t
JPJ_GetFeatures();

struct JPJ_BroadPhaseLayerInterface {
    const JPC_BroadPhaseLayerInterfaceVTable *vtable;
};

struct JPJ_ObjectVsBroadPhaseLayerFilter {
    const JPC_ObjectVsBroadPhaseLayerFilterVTable *vtable;
};

struct JPJ_BroadPhaseLayerFilter {
    const JPC_BroadPhaseLayerFilterVTable *vtable;
};

struct JPJ_ObjectLayerPairFilter {
    const JPC_ObjectLayerPairFilterVTable *vtable;
};

struct JPJ_ObjectLayerFilter {
    const JPC_ObjectLayerFilterVTable *vtable;
};

struct JPJ_BodyActivationListener {
    const JPC_BodyActivationListenerVTable *vtable;
};

struct JPJ_BodyFilter {
    const JPC_BodyFilterVTable *vtable;
};

struct JPJ_ShapeFilter {
    const JPC_ShapeFilterVTable *vtable;
    JPC_BodyID body_id;
};

struct JPJ_ContactListener {
    const JPC_ContactListenerVTable *vtable;
};

struct JPJ_PhysicsStepListener {
    const JPC_PhysicsStepListenerVTable *vtable;
};

struct JPJ_RayCastBodyCollector {
    const JPC_RayCastBodyCollectorVTable *vtable;
    JPC_CollisionCollector                collector;
};

struct JPJ_CollideShapeBodyCollector {
    const JPC_CollideShapeBodyCollectorVTable *vtable;
    JPC_CollisionCollector                     collector;
};

struct JPJ_CastShapeBodyCollector {
    const JPC_CastShapeBodyCollectorVTable *vtable;
    JPC_CollisionCollector                  collector;
};

struct JPJ_CastRayCollector {
    const JPC_CastRayCollectorVTable *vtable;
    JPC_CollisionCollector            collector;
};

struct JPJ_CastShapeCollector {
    const JPC_CastShapeCollectorVTable *vtable;
    JPC_CollisionCollector              collector;
};

struct JPJ_CollidePointCollector {
    const JPC_CollidePointCollectorVTable *vtable;
    JPC_CollisionCollector                 collector;
};

struct JPJ_CollideShapeCollector {
    const JPC_CollideShapeCollectorVTable *vtable;
    JPC_CollisionCollector                 collector;
};

struct JPJ_TransformedShapeCollector {
    const JPC_TransformedShapeCollectorVTable *vtable;
    JPC_CollisionCollector                     collector;
};
// END JoltJava
#ifdef __cplusplus
}
#endif
