// Generated by jextract

package jolt.headers_d;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
public class JPC_CollidePointResult {

    static final  GroupLayout $struct$LAYOUT = MemoryLayout.structLayout(
        Constants$root.C_INT$LAYOUT.withName("body_id"),
        Constants$root.C_INT$LAYOUT.withName("sub_shape_id")
    ).withName("JPC_CollidePointResult");
    public static MemoryLayout $LAYOUT() {
        return JPC_CollidePointResult.$struct$LAYOUT;
    }
    static final VarHandle body_id$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("body_id"));
    public static VarHandle body_id$VH() {
        return JPC_CollidePointResult.body_id$VH;
    }
    public static int body_id$get(MemorySegment seg) {
        return (int)JPC_CollidePointResult.body_id$VH.get(seg);
    }
    public static void body_id$set( MemorySegment seg, int x) {
        JPC_CollidePointResult.body_id$VH.set(seg, x);
    }
    public static int body_id$get(MemorySegment seg, long index) {
        return (int)JPC_CollidePointResult.body_id$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void body_id$set(MemorySegment seg, long index, int x) {
        JPC_CollidePointResult.body_id$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle sub_shape_id$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("sub_shape_id"));
    public static VarHandle sub_shape_id$VH() {
        return JPC_CollidePointResult.sub_shape_id$VH;
    }
    public static int sub_shape_id$get(MemorySegment seg) {
        return (int)JPC_CollidePointResult.sub_shape_id$VH.get(seg);
    }
    public static void sub_shape_id$set( MemorySegment seg, int x) {
        JPC_CollidePointResult.sub_shape_id$VH.set(seg, x);
    }
    public static int sub_shape_id$get(MemorySegment seg, long index) {
        return (int)JPC_CollidePointResult.sub_shape_id$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void sub_shape_id$set(MemorySegment seg, long index, int x) {
        JPC_CollidePointResult.sub_shape_id$VH.set(seg.asSlice(index*sizeof()), x);
    }
    public static long sizeof() { return $LAYOUT().byteSize(); }
    public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate($LAYOUT()); }
    public static MemorySegment allocateArray(int len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }
    public static MemorySegment ofAddress(MemoryAddress addr, MemorySession session) { return RuntimeHelper.asArray(addr, $LAYOUT(), 1, session); }
}


