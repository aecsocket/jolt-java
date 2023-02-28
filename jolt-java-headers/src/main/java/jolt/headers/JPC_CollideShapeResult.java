// Generated by jextract

package jolt.headers;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
public class JPC_CollideShapeResult {

    static final  GroupLayout $struct$LAYOUT = MemoryLayout.structLayout(
        MemoryLayout.sequenceLayout(4, Constants$root.C_FLOAT$LAYOUT).withName("shape1_contact_point"),
        MemoryLayout.sequenceLayout(4, Constants$root.C_FLOAT$LAYOUT).withName("shape2_contact_point"),
        MemoryLayout.sequenceLayout(4, Constants$root.C_FLOAT$LAYOUT).withName("penetration_axis"),
        Constants$root.C_FLOAT$LAYOUT.withName("penetration_depth"),
        Constants$root.C_INT$LAYOUT.withName("shape1_sub_shape_id"),
        Constants$root.C_INT$LAYOUT.withName("shape2_sub_shape_id"),
        Constants$root.C_INT$LAYOUT.withName("body2_id"),
        MemoryLayout.structLayout(
            Constants$root.C_INT$LAYOUT.withName("num_points"),
            MemoryLayout.paddingLayout(96),
            MemoryLayout.sequenceLayout(32, MemoryLayout.sequenceLayout(4, Constants$root.C_FLOAT$LAYOUT)).withName("points")
        ).withName("shape1_face"),
        MemoryLayout.structLayout(
            Constants$root.C_INT$LAYOUT.withName("num_points"),
            MemoryLayout.paddingLayout(96),
            MemoryLayout.sequenceLayout(32, MemoryLayout.sequenceLayout(4, Constants$root.C_FLOAT$LAYOUT)).withName("points")
        ).withName("shape2_face")
    ).withName("JPC_CollideShapeResult");
    public static MemoryLayout $LAYOUT() {
        return JPC_CollideShapeResult.$struct$LAYOUT;
    }
    public static MemorySegment shape1_contact_point$slice(MemorySegment seg) {
        return seg.asSlice(0, 16);
    }
    public static MemorySegment shape2_contact_point$slice(MemorySegment seg) {
        return seg.asSlice(16, 16);
    }
    public static MemorySegment penetration_axis$slice(MemorySegment seg) {
        return seg.asSlice(32, 16);
    }
    static final VarHandle penetration_depth$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("penetration_depth"));
    public static VarHandle penetration_depth$VH() {
        return JPC_CollideShapeResult.penetration_depth$VH;
    }
    public static float penetration_depth$get(MemorySegment seg) {
        return (float)JPC_CollideShapeResult.penetration_depth$VH.get(seg);
    }
    public static void penetration_depth$set( MemorySegment seg, float x) {
        JPC_CollideShapeResult.penetration_depth$VH.set(seg, x);
    }
    public static float penetration_depth$get(MemorySegment seg, long index) {
        return (float)JPC_CollideShapeResult.penetration_depth$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void penetration_depth$set(MemorySegment seg, long index, float x) {
        JPC_CollideShapeResult.penetration_depth$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle shape1_sub_shape_id$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("shape1_sub_shape_id"));
    public static VarHandle shape1_sub_shape_id$VH() {
        return JPC_CollideShapeResult.shape1_sub_shape_id$VH;
    }
    public static int shape1_sub_shape_id$get(MemorySegment seg) {
        return (int)JPC_CollideShapeResult.shape1_sub_shape_id$VH.get(seg);
    }
    public static void shape1_sub_shape_id$set( MemorySegment seg, int x) {
        JPC_CollideShapeResult.shape1_sub_shape_id$VH.set(seg, x);
    }
    public static int shape1_sub_shape_id$get(MemorySegment seg, long index) {
        return (int)JPC_CollideShapeResult.shape1_sub_shape_id$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void shape1_sub_shape_id$set(MemorySegment seg, long index, int x) {
        JPC_CollideShapeResult.shape1_sub_shape_id$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle shape2_sub_shape_id$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("shape2_sub_shape_id"));
    public static VarHandle shape2_sub_shape_id$VH() {
        return JPC_CollideShapeResult.shape2_sub_shape_id$VH;
    }
    public static int shape2_sub_shape_id$get(MemorySegment seg) {
        return (int)JPC_CollideShapeResult.shape2_sub_shape_id$VH.get(seg);
    }
    public static void shape2_sub_shape_id$set( MemorySegment seg, int x) {
        JPC_CollideShapeResult.shape2_sub_shape_id$VH.set(seg, x);
    }
    public static int shape2_sub_shape_id$get(MemorySegment seg, long index) {
        return (int)JPC_CollideShapeResult.shape2_sub_shape_id$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void shape2_sub_shape_id$set(MemorySegment seg, long index, int x) {
        JPC_CollideShapeResult.shape2_sub_shape_id$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle body2_id$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("body2_id"));
    public static VarHandle body2_id$VH() {
        return JPC_CollideShapeResult.body2_id$VH;
    }
    public static int body2_id$get(MemorySegment seg) {
        return (int)JPC_CollideShapeResult.body2_id$VH.get(seg);
    }
    public static void body2_id$set( MemorySegment seg, int x) {
        JPC_CollideShapeResult.body2_id$VH.set(seg, x);
    }
    public static int body2_id$get(MemorySegment seg, long index) {
        return (int)JPC_CollideShapeResult.body2_id$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void body2_id$set(MemorySegment seg, long index, int x) {
        JPC_CollideShapeResult.body2_id$VH.set(seg.asSlice(index*sizeof()), x);
    }
    public static class shape1_face {

        static final  GroupLayout shape1_face$struct$LAYOUT = MemoryLayout.structLayout(
            Constants$root.C_INT$LAYOUT.withName("num_points"),
            MemoryLayout.paddingLayout(96),
            MemoryLayout.sequenceLayout(32, MemoryLayout.sequenceLayout(4, Constants$root.C_FLOAT$LAYOUT)).withName("points")
        );
        public static MemoryLayout $LAYOUT() {
            return shape1_face.shape1_face$struct$LAYOUT;
        }
        static final VarHandle num_points$VH = shape1_face$struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("num_points"));
        public static VarHandle num_points$VH() {
            return shape1_face.num_points$VH;
        }
        public static int num_points$get(MemorySegment seg) {
            return (int)shape1_face.num_points$VH.get(seg);
        }
        public static void num_points$set( MemorySegment seg, int x) {
            shape1_face.num_points$VH.set(seg, x);
        }
        public static int num_points$get(MemorySegment seg, long index) {
            return (int)shape1_face.num_points$VH.get(seg.asSlice(index*sizeof()));
        }
        public static void num_points$set(MemorySegment seg, long index, int x) {
            shape1_face.num_points$VH.set(seg.asSlice(index*sizeof()), x);
        }
        public static MemorySegment points$slice(MemorySegment seg) {
            return seg.asSlice(16, 512);
        }
        public static long sizeof() { return $LAYOUT().byteSize(); }
        public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate($LAYOUT()); }
        public static MemorySegment allocateArray(int len, SegmentAllocator allocator) {
            return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
        }
        public static MemorySegment ofAddress(MemoryAddress addr, MemorySession session) { return RuntimeHelper.asArray(addr, $LAYOUT(), 1, session); }
    }

    public static MemorySegment shape1_face$slice(MemorySegment seg) {
        return seg.asSlice(64, 528);
    }
    public static class shape2_face {

        static final  GroupLayout shape2_face$struct$LAYOUT = MemoryLayout.structLayout(
            Constants$root.C_INT$LAYOUT.withName("num_points"),
            MemoryLayout.paddingLayout(96),
            MemoryLayout.sequenceLayout(32, MemoryLayout.sequenceLayout(4, Constants$root.C_FLOAT$LAYOUT)).withName("points")
        );
        public static MemoryLayout $LAYOUT() {
            return shape2_face.shape2_face$struct$LAYOUT;
        }
        static final VarHandle num_points$VH = shape2_face$struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("num_points"));
        public static VarHandle num_points$VH() {
            return shape2_face.num_points$VH;
        }
        public static int num_points$get(MemorySegment seg) {
            return (int)shape2_face.num_points$VH.get(seg);
        }
        public static void num_points$set( MemorySegment seg, int x) {
            shape2_face.num_points$VH.set(seg, x);
        }
        public static int num_points$get(MemorySegment seg, long index) {
            return (int)shape2_face.num_points$VH.get(seg.asSlice(index*sizeof()));
        }
        public static void num_points$set(MemorySegment seg, long index, int x) {
            shape2_face.num_points$VH.set(seg.asSlice(index*sizeof()), x);
        }
        public static MemorySegment points$slice(MemorySegment seg) {
            return seg.asSlice(16, 512);
        }
        public static long sizeof() { return $LAYOUT().byteSize(); }
        public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate($LAYOUT()); }
        public static MemorySegment allocateArray(int len, SegmentAllocator allocator) {
            return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
        }
        public static MemorySegment ofAddress(MemoryAddress addr, MemorySession session) { return RuntimeHelper.asArray(addr, $LAYOUT(), 1, session); }
    }

    public static MemorySegment shape2_face$slice(MemorySegment seg) {
        return seg.asSlice(592, 528);
    }
    public static long sizeof() { return $LAYOUT().byteSize(); }
    public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate($LAYOUT()); }
    public static MemorySegment allocateArray(int len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }
    public static MemorySegment ofAddress(MemoryAddress addr, MemorySession session) { return RuntimeHelper.asArray(addr, $LAYOUT(), 1, session); }
}

