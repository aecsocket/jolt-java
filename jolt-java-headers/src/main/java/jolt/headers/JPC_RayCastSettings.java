// Generated by jextract

package jolt.headers;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
public class JPC_RayCastSettings {

    static final  GroupLayout $struct$LAYOUT = MemoryLayout.structLayout(
        Constants$root.C_CHAR$LAYOUT.withName("back_face_mode"),
        Constants$root.C_BOOL$LAYOUT.withName("treat_convex_as_solid")
    ).withName("JPC_RayCastSettings");
    public static MemoryLayout $LAYOUT() {
        return JPC_RayCastSettings.$struct$LAYOUT;
    }
    static final VarHandle back_face_mode$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("back_face_mode"));
    public static VarHandle back_face_mode$VH() {
        return JPC_RayCastSettings.back_face_mode$VH;
    }
    public static byte back_face_mode$get(MemorySegment seg) {
        return (byte)JPC_RayCastSettings.back_face_mode$VH.get(seg);
    }
    public static void back_face_mode$set( MemorySegment seg, byte x) {
        JPC_RayCastSettings.back_face_mode$VH.set(seg, x);
    }
    public static byte back_face_mode$get(MemorySegment seg, long index) {
        return (byte)JPC_RayCastSettings.back_face_mode$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void back_face_mode$set(MemorySegment seg, long index, byte x) {
        JPC_RayCastSettings.back_face_mode$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle treat_convex_as_solid$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("treat_convex_as_solid"));
    public static VarHandle treat_convex_as_solid$VH() {
        return JPC_RayCastSettings.treat_convex_as_solid$VH;
    }
    public static boolean treat_convex_as_solid$get(MemorySegment seg) {
        return (boolean)JPC_RayCastSettings.treat_convex_as_solid$VH.get(seg);
    }
    public static void treat_convex_as_solid$set( MemorySegment seg, boolean x) {
        JPC_RayCastSettings.treat_convex_as_solid$VH.set(seg, x);
    }
    public static boolean treat_convex_as_solid$get(MemorySegment seg, long index) {
        return (boolean)JPC_RayCastSettings.treat_convex_as_solid$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void treat_convex_as_solid$set(MemorySegment seg, long index, boolean x) {
        JPC_RayCastSettings.treat_convex_as_solid$VH.set(seg.asSlice(index*sizeof()), x);
    }
    public static long sizeof() { return $LAYOUT().byteSize(); }
    public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate($LAYOUT()); }
    public static MemorySegment allocateArray(int len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }
    public static MemorySegment ofAddress(MemoryAddress addr, MemorySession session) { return RuntimeHelper.asArray(addr, $LAYOUT(), 1, session); }
}

