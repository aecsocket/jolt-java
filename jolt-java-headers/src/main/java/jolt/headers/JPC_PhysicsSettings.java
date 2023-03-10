// Generated by jextract

package jolt.headers;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
public class JPC_PhysicsSettings {

    static final  GroupLayout $struct$LAYOUT = MemoryLayout.structLayout(
        Constants$root.C_INT$LAYOUT.withName("max_in_flight_body_pairs"),
        Constants$root.C_INT$LAYOUT.withName("step_listeners_batch_size"),
        Constants$root.C_INT$LAYOUT.withName("step_listener_batches_per_job"),
        Constants$root.C_FLOAT$LAYOUT.withName("baumgarte"),
        Constants$root.C_FLOAT$LAYOUT.withName("speculative_contact_distance"),
        Constants$root.C_FLOAT$LAYOUT.withName("penetration_slop"),
        Constants$root.C_FLOAT$LAYOUT.withName("linear_cast_threshold"),
        Constants$root.C_FLOAT$LAYOUT.withName("linear_cast_max_penetration"),
        Constants$root.C_FLOAT$LAYOUT.withName("manifold_tolerance_sq"),
        Constants$root.C_FLOAT$LAYOUT.withName("max_penetration_distance"),
        Constants$root.C_FLOAT$LAYOUT.withName("body_pair_cache_max_delta_position_sq"),
        Constants$root.C_FLOAT$LAYOUT.withName("body_pair_cache_cos_max_delta_rotation_div_2"),
        Constants$root.C_FLOAT$LAYOUT.withName("contact_normal_cos_max_delta_rotation"),
        Constants$root.C_FLOAT$LAYOUT.withName("contact_point_preserve_lambda_max_dist_sq"),
        Constants$root.C_INT$LAYOUT.withName("num_velocity_steps"),
        Constants$root.C_INT$LAYOUT.withName("num_position_steps"),
        Constants$root.C_FLOAT$LAYOUT.withName("min_velocity_for_restitution"),
        Constants$root.C_FLOAT$LAYOUT.withName("time_before_sleep"),
        Constants$root.C_FLOAT$LAYOUT.withName("point_velocity_sleep_threshold"),
        Constants$root.C_BOOL$LAYOUT.withName("constraint_warm_start"),
        Constants$root.C_BOOL$LAYOUT.withName("use_body_pair_contact_cache"),
        Constants$root.C_BOOL$LAYOUT.withName("use_manifold_reduction"),
        Constants$root.C_BOOL$LAYOUT.withName("allow_sleeping"),
        Constants$root.C_BOOL$LAYOUT.withName("check_active_edges"),
        MemoryLayout.paddingLayout(24)
    ).withName("JPC_PhysicsSettings");
    public static MemoryLayout $LAYOUT() {
        return JPC_PhysicsSettings.$struct$LAYOUT;
    }
    static final VarHandle max_in_flight_body_pairs$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("max_in_flight_body_pairs"));
    public static VarHandle max_in_flight_body_pairs$VH() {
        return JPC_PhysicsSettings.max_in_flight_body_pairs$VH;
    }
    public static int max_in_flight_body_pairs$get(MemorySegment seg) {
        return (int)JPC_PhysicsSettings.max_in_flight_body_pairs$VH.get(seg);
    }
    public static void max_in_flight_body_pairs$set( MemorySegment seg, int x) {
        JPC_PhysicsSettings.max_in_flight_body_pairs$VH.set(seg, x);
    }
    public static int max_in_flight_body_pairs$get(MemorySegment seg, long index) {
        return (int)JPC_PhysicsSettings.max_in_flight_body_pairs$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void max_in_flight_body_pairs$set(MemorySegment seg, long index, int x) {
        JPC_PhysicsSettings.max_in_flight_body_pairs$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle step_listeners_batch_size$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("step_listeners_batch_size"));
    public static VarHandle step_listeners_batch_size$VH() {
        return JPC_PhysicsSettings.step_listeners_batch_size$VH;
    }
    public static int step_listeners_batch_size$get(MemorySegment seg) {
        return (int)JPC_PhysicsSettings.step_listeners_batch_size$VH.get(seg);
    }
    public static void step_listeners_batch_size$set( MemorySegment seg, int x) {
        JPC_PhysicsSettings.step_listeners_batch_size$VH.set(seg, x);
    }
    public static int step_listeners_batch_size$get(MemorySegment seg, long index) {
        return (int)JPC_PhysicsSettings.step_listeners_batch_size$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void step_listeners_batch_size$set(MemorySegment seg, long index, int x) {
        JPC_PhysicsSettings.step_listeners_batch_size$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle step_listener_batches_per_job$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("step_listener_batches_per_job"));
    public static VarHandle step_listener_batches_per_job$VH() {
        return JPC_PhysicsSettings.step_listener_batches_per_job$VH;
    }
    public static int step_listener_batches_per_job$get(MemorySegment seg) {
        return (int)JPC_PhysicsSettings.step_listener_batches_per_job$VH.get(seg);
    }
    public static void step_listener_batches_per_job$set( MemorySegment seg, int x) {
        JPC_PhysicsSettings.step_listener_batches_per_job$VH.set(seg, x);
    }
    public static int step_listener_batches_per_job$get(MemorySegment seg, long index) {
        return (int)JPC_PhysicsSettings.step_listener_batches_per_job$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void step_listener_batches_per_job$set(MemorySegment seg, long index, int x) {
        JPC_PhysicsSettings.step_listener_batches_per_job$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle baumgarte$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("baumgarte"));
    public static VarHandle baumgarte$VH() {
        return JPC_PhysicsSettings.baumgarte$VH;
    }
    public static float baumgarte$get(MemorySegment seg) {
        return (float)JPC_PhysicsSettings.baumgarte$VH.get(seg);
    }
    public static void baumgarte$set( MemorySegment seg, float x) {
        JPC_PhysicsSettings.baumgarte$VH.set(seg, x);
    }
    public static float baumgarte$get(MemorySegment seg, long index) {
        return (float)JPC_PhysicsSettings.baumgarte$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void baumgarte$set(MemorySegment seg, long index, float x) {
        JPC_PhysicsSettings.baumgarte$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle speculative_contact_distance$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("speculative_contact_distance"));
    public static VarHandle speculative_contact_distance$VH() {
        return JPC_PhysicsSettings.speculative_contact_distance$VH;
    }
    public static float speculative_contact_distance$get(MemorySegment seg) {
        return (float)JPC_PhysicsSettings.speculative_contact_distance$VH.get(seg);
    }
    public static void speculative_contact_distance$set( MemorySegment seg, float x) {
        JPC_PhysicsSettings.speculative_contact_distance$VH.set(seg, x);
    }
    public static float speculative_contact_distance$get(MemorySegment seg, long index) {
        return (float)JPC_PhysicsSettings.speculative_contact_distance$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void speculative_contact_distance$set(MemorySegment seg, long index, float x) {
        JPC_PhysicsSettings.speculative_contact_distance$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle penetration_slop$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("penetration_slop"));
    public static VarHandle penetration_slop$VH() {
        return JPC_PhysicsSettings.penetration_slop$VH;
    }
    public static float penetration_slop$get(MemorySegment seg) {
        return (float)JPC_PhysicsSettings.penetration_slop$VH.get(seg);
    }
    public static void penetration_slop$set( MemorySegment seg, float x) {
        JPC_PhysicsSettings.penetration_slop$VH.set(seg, x);
    }
    public static float penetration_slop$get(MemorySegment seg, long index) {
        return (float)JPC_PhysicsSettings.penetration_slop$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void penetration_slop$set(MemorySegment seg, long index, float x) {
        JPC_PhysicsSettings.penetration_slop$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle linear_cast_threshold$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("linear_cast_threshold"));
    public static VarHandle linear_cast_threshold$VH() {
        return JPC_PhysicsSettings.linear_cast_threshold$VH;
    }
    public static float linear_cast_threshold$get(MemorySegment seg) {
        return (float)JPC_PhysicsSettings.linear_cast_threshold$VH.get(seg);
    }
    public static void linear_cast_threshold$set( MemorySegment seg, float x) {
        JPC_PhysicsSettings.linear_cast_threshold$VH.set(seg, x);
    }
    public static float linear_cast_threshold$get(MemorySegment seg, long index) {
        return (float)JPC_PhysicsSettings.linear_cast_threshold$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void linear_cast_threshold$set(MemorySegment seg, long index, float x) {
        JPC_PhysicsSettings.linear_cast_threshold$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle linear_cast_max_penetration$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("linear_cast_max_penetration"));
    public static VarHandle linear_cast_max_penetration$VH() {
        return JPC_PhysicsSettings.linear_cast_max_penetration$VH;
    }
    public static float linear_cast_max_penetration$get(MemorySegment seg) {
        return (float)JPC_PhysicsSettings.linear_cast_max_penetration$VH.get(seg);
    }
    public static void linear_cast_max_penetration$set( MemorySegment seg, float x) {
        JPC_PhysicsSettings.linear_cast_max_penetration$VH.set(seg, x);
    }
    public static float linear_cast_max_penetration$get(MemorySegment seg, long index) {
        return (float)JPC_PhysicsSettings.linear_cast_max_penetration$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void linear_cast_max_penetration$set(MemorySegment seg, long index, float x) {
        JPC_PhysicsSettings.linear_cast_max_penetration$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle manifold_tolerance_sq$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("manifold_tolerance_sq"));
    public static VarHandle manifold_tolerance_sq$VH() {
        return JPC_PhysicsSettings.manifold_tolerance_sq$VH;
    }
    public static float manifold_tolerance_sq$get(MemorySegment seg) {
        return (float)JPC_PhysicsSettings.manifold_tolerance_sq$VH.get(seg);
    }
    public static void manifold_tolerance_sq$set( MemorySegment seg, float x) {
        JPC_PhysicsSettings.manifold_tolerance_sq$VH.set(seg, x);
    }
    public static float manifold_tolerance_sq$get(MemorySegment seg, long index) {
        return (float)JPC_PhysicsSettings.manifold_tolerance_sq$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void manifold_tolerance_sq$set(MemorySegment seg, long index, float x) {
        JPC_PhysicsSettings.manifold_tolerance_sq$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle max_penetration_distance$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("max_penetration_distance"));
    public static VarHandle max_penetration_distance$VH() {
        return JPC_PhysicsSettings.max_penetration_distance$VH;
    }
    public static float max_penetration_distance$get(MemorySegment seg) {
        return (float)JPC_PhysicsSettings.max_penetration_distance$VH.get(seg);
    }
    public static void max_penetration_distance$set( MemorySegment seg, float x) {
        JPC_PhysicsSettings.max_penetration_distance$VH.set(seg, x);
    }
    public static float max_penetration_distance$get(MemorySegment seg, long index) {
        return (float)JPC_PhysicsSettings.max_penetration_distance$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void max_penetration_distance$set(MemorySegment seg, long index, float x) {
        JPC_PhysicsSettings.max_penetration_distance$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle body_pair_cache_max_delta_position_sq$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("body_pair_cache_max_delta_position_sq"));
    public static VarHandle body_pair_cache_max_delta_position_sq$VH() {
        return JPC_PhysicsSettings.body_pair_cache_max_delta_position_sq$VH;
    }
    public static float body_pair_cache_max_delta_position_sq$get(MemorySegment seg) {
        return (float)JPC_PhysicsSettings.body_pair_cache_max_delta_position_sq$VH.get(seg);
    }
    public static void body_pair_cache_max_delta_position_sq$set( MemorySegment seg, float x) {
        JPC_PhysicsSettings.body_pair_cache_max_delta_position_sq$VH.set(seg, x);
    }
    public static float body_pair_cache_max_delta_position_sq$get(MemorySegment seg, long index) {
        return (float)JPC_PhysicsSettings.body_pair_cache_max_delta_position_sq$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void body_pair_cache_max_delta_position_sq$set(MemorySegment seg, long index, float x) {
        JPC_PhysicsSettings.body_pair_cache_max_delta_position_sq$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle body_pair_cache_cos_max_delta_rotation_div_2$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("body_pair_cache_cos_max_delta_rotation_div_2"));
    public static VarHandle body_pair_cache_cos_max_delta_rotation_div_2$VH() {
        return JPC_PhysicsSettings.body_pair_cache_cos_max_delta_rotation_div_2$VH;
    }
    public static float body_pair_cache_cos_max_delta_rotation_div_2$get(MemorySegment seg) {
        return (float)JPC_PhysicsSettings.body_pair_cache_cos_max_delta_rotation_div_2$VH.get(seg);
    }
    public static void body_pair_cache_cos_max_delta_rotation_div_2$set( MemorySegment seg, float x) {
        JPC_PhysicsSettings.body_pair_cache_cos_max_delta_rotation_div_2$VH.set(seg, x);
    }
    public static float body_pair_cache_cos_max_delta_rotation_div_2$get(MemorySegment seg, long index) {
        return (float)JPC_PhysicsSettings.body_pair_cache_cos_max_delta_rotation_div_2$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void body_pair_cache_cos_max_delta_rotation_div_2$set(MemorySegment seg, long index, float x) {
        JPC_PhysicsSettings.body_pair_cache_cos_max_delta_rotation_div_2$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle contact_normal_cos_max_delta_rotation$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("contact_normal_cos_max_delta_rotation"));
    public static VarHandle contact_normal_cos_max_delta_rotation$VH() {
        return JPC_PhysicsSettings.contact_normal_cos_max_delta_rotation$VH;
    }
    public static float contact_normal_cos_max_delta_rotation$get(MemorySegment seg) {
        return (float)JPC_PhysicsSettings.contact_normal_cos_max_delta_rotation$VH.get(seg);
    }
    public static void contact_normal_cos_max_delta_rotation$set( MemorySegment seg, float x) {
        JPC_PhysicsSettings.contact_normal_cos_max_delta_rotation$VH.set(seg, x);
    }
    public static float contact_normal_cos_max_delta_rotation$get(MemorySegment seg, long index) {
        return (float)JPC_PhysicsSettings.contact_normal_cos_max_delta_rotation$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void contact_normal_cos_max_delta_rotation$set(MemorySegment seg, long index, float x) {
        JPC_PhysicsSettings.contact_normal_cos_max_delta_rotation$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle contact_point_preserve_lambda_max_dist_sq$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("contact_point_preserve_lambda_max_dist_sq"));
    public static VarHandle contact_point_preserve_lambda_max_dist_sq$VH() {
        return JPC_PhysicsSettings.contact_point_preserve_lambda_max_dist_sq$VH;
    }
    public static float contact_point_preserve_lambda_max_dist_sq$get(MemorySegment seg) {
        return (float)JPC_PhysicsSettings.contact_point_preserve_lambda_max_dist_sq$VH.get(seg);
    }
    public static void contact_point_preserve_lambda_max_dist_sq$set( MemorySegment seg, float x) {
        JPC_PhysicsSettings.contact_point_preserve_lambda_max_dist_sq$VH.set(seg, x);
    }
    public static float contact_point_preserve_lambda_max_dist_sq$get(MemorySegment seg, long index) {
        return (float)JPC_PhysicsSettings.contact_point_preserve_lambda_max_dist_sq$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void contact_point_preserve_lambda_max_dist_sq$set(MemorySegment seg, long index, float x) {
        JPC_PhysicsSettings.contact_point_preserve_lambda_max_dist_sq$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle num_velocity_steps$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("num_velocity_steps"));
    public static VarHandle num_velocity_steps$VH() {
        return JPC_PhysicsSettings.num_velocity_steps$VH;
    }
    public static int num_velocity_steps$get(MemorySegment seg) {
        return (int)JPC_PhysicsSettings.num_velocity_steps$VH.get(seg);
    }
    public static void num_velocity_steps$set( MemorySegment seg, int x) {
        JPC_PhysicsSettings.num_velocity_steps$VH.set(seg, x);
    }
    public static int num_velocity_steps$get(MemorySegment seg, long index) {
        return (int)JPC_PhysicsSettings.num_velocity_steps$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void num_velocity_steps$set(MemorySegment seg, long index, int x) {
        JPC_PhysicsSettings.num_velocity_steps$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle num_position_steps$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("num_position_steps"));
    public static VarHandle num_position_steps$VH() {
        return JPC_PhysicsSettings.num_position_steps$VH;
    }
    public static int num_position_steps$get(MemorySegment seg) {
        return (int)JPC_PhysicsSettings.num_position_steps$VH.get(seg);
    }
    public static void num_position_steps$set( MemorySegment seg, int x) {
        JPC_PhysicsSettings.num_position_steps$VH.set(seg, x);
    }
    public static int num_position_steps$get(MemorySegment seg, long index) {
        return (int)JPC_PhysicsSettings.num_position_steps$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void num_position_steps$set(MemorySegment seg, long index, int x) {
        JPC_PhysicsSettings.num_position_steps$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle min_velocity_for_restitution$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("min_velocity_for_restitution"));
    public static VarHandle min_velocity_for_restitution$VH() {
        return JPC_PhysicsSettings.min_velocity_for_restitution$VH;
    }
    public static float min_velocity_for_restitution$get(MemorySegment seg) {
        return (float)JPC_PhysicsSettings.min_velocity_for_restitution$VH.get(seg);
    }
    public static void min_velocity_for_restitution$set( MemorySegment seg, float x) {
        JPC_PhysicsSettings.min_velocity_for_restitution$VH.set(seg, x);
    }
    public static float min_velocity_for_restitution$get(MemorySegment seg, long index) {
        return (float)JPC_PhysicsSettings.min_velocity_for_restitution$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void min_velocity_for_restitution$set(MemorySegment seg, long index, float x) {
        JPC_PhysicsSettings.min_velocity_for_restitution$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle time_before_sleep$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("time_before_sleep"));
    public static VarHandle time_before_sleep$VH() {
        return JPC_PhysicsSettings.time_before_sleep$VH;
    }
    public static float time_before_sleep$get(MemorySegment seg) {
        return (float)JPC_PhysicsSettings.time_before_sleep$VH.get(seg);
    }
    public static void time_before_sleep$set( MemorySegment seg, float x) {
        JPC_PhysicsSettings.time_before_sleep$VH.set(seg, x);
    }
    public static float time_before_sleep$get(MemorySegment seg, long index) {
        return (float)JPC_PhysicsSettings.time_before_sleep$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void time_before_sleep$set(MemorySegment seg, long index, float x) {
        JPC_PhysicsSettings.time_before_sleep$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle point_velocity_sleep_threshold$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("point_velocity_sleep_threshold"));
    public static VarHandle point_velocity_sleep_threshold$VH() {
        return JPC_PhysicsSettings.point_velocity_sleep_threshold$VH;
    }
    public static float point_velocity_sleep_threshold$get(MemorySegment seg) {
        return (float)JPC_PhysicsSettings.point_velocity_sleep_threshold$VH.get(seg);
    }
    public static void point_velocity_sleep_threshold$set( MemorySegment seg, float x) {
        JPC_PhysicsSettings.point_velocity_sleep_threshold$VH.set(seg, x);
    }
    public static float point_velocity_sleep_threshold$get(MemorySegment seg, long index) {
        return (float)JPC_PhysicsSettings.point_velocity_sleep_threshold$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void point_velocity_sleep_threshold$set(MemorySegment seg, long index, float x) {
        JPC_PhysicsSettings.point_velocity_sleep_threshold$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle constraint_warm_start$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("constraint_warm_start"));
    public static VarHandle constraint_warm_start$VH() {
        return JPC_PhysicsSettings.constraint_warm_start$VH;
    }
    public static boolean constraint_warm_start$get(MemorySegment seg) {
        return (boolean)JPC_PhysicsSettings.constraint_warm_start$VH.get(seg);
    }
    public static void constraint_warm_start$set( MemorySegment seg, boolean x) {
        JPC_PhysicsSettings.constraint_warm_start$VH.set(seg, x);
    }
    public static boolean constraint_warm_start$get(MemorySegment seg, long index) {
        return (boolean)JPC_PhysicsSettings.constraint_warm_start$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void constraint_warm_start$set(MemorySegment seg, long index, boolean x) {
        JPC_PhysicsSettings.constraint_warm_start$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle use_body_pair_contact_cache$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("use_body_pair_contact_cache"));
    public static VarHandle use_body_pair_contact_cache$VH() {
        return JPC_PhysicsSettings.use_body_pair_contact_cache$VH;
    }
    public static boolean use_body_pair_contact_cache$get(MemorySegment seg) {
        return (boolean)JPC_PhysicsSettings.use_body_pair_contact_cache$VH.get(seg);
    }
    public static void use_body_pair_contact_cache$set( MemorySegment seg, boolean x) {
        JPC_PhysicsSettings.use_body_pair_contact_cache$VH.set(seg, x);
    }
    public static boolean use_body_pair_contact_cache$get(MemorySegment seg, long index) {
        return (boolean)JPC_PhysicsSettings.use_body_pair_contact_cache$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void use_body_pair_contact_cache$set(MemorySegment seg, long index, boolean x) {
        JPC_PhysicsSettings.use_body_pair_contact_cache$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle use_manifold_reduction$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("use_manifold_reduction"));
    public static VarHandle use_manifold_reduction$VH() {
        return JPC_PhysicsSettings.use_manifold_reduction$VH;
    }
    public static boolean use_manifold_reduction$get(MemorySegment seg) {
        return (boolean)JPC_PhysicsSettings.use_manifold_reduction$VH.get(seg);
    }
    public static void use_manifold_reduction$set( MemorySegment seg, boolean x) {
        JPC_PhysicsSettings.use_manifold_reduction$VH.set(seg, x);
    }
    public static boolean use_manifold_reduction$get(MemorySegment seg, long index) {
        return (boolean)JPC_PhysicsSettings.use_manifold_reduction$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void use_manifold_reduction$set(MemorySegment seg, long index, boolean x) {
        JPC_PhysicsSettings.use_manifold_reduction$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle allow_sleeping$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("allow_sleeping"));
    public static VarHandle allow_sleeping$VH() {
        return JPC_PhysicsSettings.allow_sleeping$VH;
    }
    public static boolean allow_sleeping$get(MemorySegment seg) {
        return (boolean)JPC_PhysicsSettings.allow_sleeping$VH.get(seg);
    }
    public static void allow_sleeping$set( MemorySegment seg, boolean x) {
        JPC_PhysicsSettings.allow_sleeping$VH.set(seg, x);
    }
    public static boolean allow_sleeping$get(MemorySegment seg, long index) {
        return (boolean)JPC_PhysicsSettings.allow_sleeping$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void allow_sleeping$set(MemorySegment seg, long index, boolean x) {
        JPC_PhysicsSettings.allow_sleeping$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle check_active_edges$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("check_active_edges"));
    public static VarHandle check_active_edges$VH() {
        return JPC_PhysicsSettings.check_active_edges$VH;
    }
    public static boolean check_active_edges$get(MemorySegment seg) {
        return (boolean)JPC_PhysicsSettings.check_active_edges$VH.get(seg);
    }
    public static void check_active_edges$set( MemorySegment seg, boolean x) {
        JPC_PhysicsSettings.check_active_edges$VH.set(seg, x);
    }
    public static boolean check_active_edges$get(MemorySegment seg, long index) {
        return (boolean)JPC_PhysicsSettings.check_active_edges$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void check_active_edges$set(MemorySegment seg, long index, boolean x) {
        JPC_PhysicsSettings.check_active_edges$VH.set(seg.asSlice(index*sizeof()), x);
    }
    public static long sizeof() { return $LAYOUT().byteSize(); }
    public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate($LAYOUT()); }
    public static MemorySegment allocateArray(int len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }
    public static MemorySegment ofAddress(MemoryAddress addr, MemorySession session) { return RuntimeHelper.asArray(addr, $LAYOUT(), 1, session); }
}


