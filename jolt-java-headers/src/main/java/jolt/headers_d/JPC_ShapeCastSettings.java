// Generated by jextract

package jolt.headers_d;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
public class JPC_ShapeCastSettings {

    static final  GroupLayout $struct$LAYOUT = MemoryLayout.structLayout(
        MemoryLayout.structLayout(
            Constants$root.C_INT$LAYOUT.withName("active_edge_mode"),
            Constants$root.C_INT$LAYOUT.withName("collect_faces_mode"),
            Constants$root.C_FLOAT$LAYOUT.withName("collision_tolerance"),
            Constants$root.C_FLOAT$LAYOUT.withName("penetration_tolerance"),
            MemoryLayout.sequenceLayout(3, Constants$root.C_FLOAT$LAYOUT).withName("active_edge_movement_direction"),
            MemoryLayout.paddingLayout(32)
        ).withName("base"),
        Constants$root.C_CHAR$LAYOUT.withName("back_face_mode_triangles"),
        Constants$root.C_CHAR$LAYOUT.withName("back_face_mode_convex"),
        Constants$root.C_BOOL$LAYOUT.withName("use_shrunken_shape_and_convex_radius"),
        Constants$root.C_BOOL$LAYOUT.withName("return_deepest_point"),
        MemoryLayout.paddingLayout(96)
    ).withName("JPC_ShapeCastSettings");
    public static MemoryLayout $LAYOUT() {
        return JPC_ShapeCastSettings.$struct$LAYOUT;
    }
    public static MemorySegment base$slice(MemorySegment seg) {
        return seg.asSlice(0, 32);
    }
    static final VarHandle back_face_mode_triangles$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("back_face_mode_triangles"));
    public static VarHandle back_face_mode_triangles$VH() {
        return JPC_ShapeCastSettings.back_face_mode_triangles$VH;
    }
    public static byte back_face_mode_triangles$get(MemorySegment seg) {
        return (byte)JPC_ShapeCastSettings.back_face_mode_triangles$VH.get(seg);
    }
    public static void back_face_mode_triangles$set( MemorySegment seg, byte x) {
        JPC_ShapeCastSettings.back_face_mode_triangles$VH.set(seg, x);
    }
    public static byte back_face_mode_triangles$get(MemorySegment seg, long index) {
        return (byte)JPC_ShapeCastSettings.back_face_mode_triangles$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void back_face_mode_triangles$set(MemorySegment seg, long index, byte x) {
        JPC_ShapeCastSettings.back_face_mode_triangles$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle back_face_mode_convex$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("back_face_mode_convex"));
    public static VarHandle back_face_mode_convex$VH() {
        return JPC_ShapeCastSettings.back_face_mode_convex$VH;
    }
    public static byte back_face_mode_convex$get(MemorySegment seg) {
        return (byte)JPC_ShapeCastSettings.back_face_mode_convex$VH.get(seg);
    }
    public static void back_face_mode_convex$set( MemorySegment seg, byte x) {
        JPC_ShapeCastSettings.back_face_mode_convex$VH.set(seg, x);
    }
    public static byte back_face_mode_convex$get(MemorySegment seg, long index) {
        return (byte)JPC_ShapeCastSettings.back_face_mode_convex$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void back_face_mode_convex$set(MemorySegment seg, long index, byte x) {
        JPC_ShapeCastSettings.back_face_mode_convex$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle use_shrunken_shape_and_convex_radius$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("use_shrunken_shape_and_convex_radius"));
    public static VarHandle use_shrunken_shape_and_convex_radius$VH() {
        return JPC_ShapeCastSettings.use_shrunken_shape_and_convex_radius$VH;
    }
    public static boolean use_shrunken_shape_and_convex_radius$get(MemorySegment seg) {
        return (boolean)JPC_ShapeCastSettings.use_shrunken_shape_and_convex_radius$VH.get(seg);
    }
    public static void use_shrunken_shape_and_convex_radius$set( MemorySegment seg, boolean x) {
        JPC_ShapeCastSettings.use_shrunken_shape_and_convex_radius$VH.set(seg, x);
    }
    public static boolean use_shrunken_shape_and_convex_radius$get(MemorySegment seg, long index) {
        return (boolean)JPC_ShapeCastSettings.use_shrunken_shape_and_convex_radius$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void use_shrunken_shape_and_convex_radius$set(MemorySegment seg, long index, boolean x) {
        JPC_ShapeCastSettings.use_shrunken_shape_and_convex_radius$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle return_deepest_point$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("return_deepest_point"));
    public static VarHandle return_deepest_point$VH() {
        return JPC_ShapeCastSettings.return_deepest_point$VH;
    }
    public static boolean return_deepest_point$get(MemorySegment seg) {
        return (boolean)JPC_ShapeCastSettings.return_deepest_point$VH.get(seg);
    }
    public static void return_deepest_point$set( MemorySegment seg, boolean x) {
        JPC_ShapeCastSettings.return_deepest_point$VH.set(seg, x);
    }
    public static boolean return_deepest_point$get(MemorySegment seg, long index) {
        return (boolean)JPC_ShapeCastSettings.return_deepest_point$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void return_deepest_point$set(MemorySegment seg, long index, boolean x) {
        JPC_ShapeCastSettings.return_deepest_point$VH.set(seg.asSlice(index*sizeof()), x);
    }
    public static long sizeof() { return $LAYOUT().byteSize(); }
    public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate($LAYOUT()); }
    public static MemorySegment allocateArray(int len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }
    public static MemorySegment ofAddress(MemoryAddress addr, MemorySession session) { return RuntimeHelper.asArray(addr, $LAYOUT(), 1, session); }
}


