// Generated by jextract

package jolt.headers_f;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
public class JPC_BroadPhaseCastResult {

    static final  GroupLayout $struct$LAYOUT = MemoryLayout.structLayout(
        Constants$root.C_INT$LAYOUT.withName("body_id"),
        Constants$root.C_FLOAT$LAYOUT.withName("fraction")
    ).withName("JPC_BroadPhaseCastResult");
    public static MemoryLayout $LAYOUT() {
        return JPC_BroadPhaseCastResult.$struct$LAYOUT;
    }
    static final VarHandle body_id$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("body_id"));
    public static VarHandle body_id$VH() {
        return JPC_BroadPhaseCastResult.body_id$VH;
    }
    public static int body_id$get(MemorySegment seg) {
        return (int)JPC_BroadPhaseCastResult.body_id$VH.get(seg);
    }
    public static void body_id$set( MemorySegment seg, int x) {
        JPC_BroadPhaseCastResult.body_id$VH.set(seg, x);
    }
    public static int body_id$get(MemorySegment seg, long index) {
        return (int)JPC_BroadPhaseCastResult.body_id$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void body_id$set(MemorySegment seg, long index, int x) {
        JPC_BroadPhaseCastResult.body_id$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle fraction$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("fraction"));
    public static VarHandle fraction$VH() {
        return JPC_BroadPhaseCastResult.fraction$VH;
    }
    public static float fraction$get(MemorySegment seg) {
        return (float)JPC_BroadPhaseCastResult.fraction$VH.get(seg);
    }
    public static void fraction$set( MemorySegment seg, float x) {
        JPC_BroadPhaseCastResult.fraction$VH.set(seg, x);
    }
    public static float fraction$get(MemorySegment seg, long index) {
        return (float)JPC_BroadPhaseCastResult.fraction$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void fraction$set(MemorySegment seg, long index, float x) {
        JPC_BroadPhaseCastResult.fraction$VH.set(seg.asSlice(index*sizeof()), x);
    }
    public static long sizeof() { return $LAYOUT().byteSize(); }
    public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate($LAYOUT()); }
    public static MemorySegment allocateArray(int len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }
    public static MemorySegment ofAddress(MemoryAddress addr, MemorySession session) { return RuntimeHelper.asArray(addr, $LAYOUT(), 1, session); }
}

