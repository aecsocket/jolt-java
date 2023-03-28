// Generated by jextract

package jolt.headers_f;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
public class JPC_FixedConstraintSettings {

    static final  GroupLayout $struct$LAYOUT = MemoryLayout.structLayout(
        Constants$root.C_POINTER$LAYOUT.withName("base"),
        Constants$root.C_CHAR$LAYOUT.withName("space"),
        Constants$root.C_BOOL$LAYOUT.withName("auto_detect_point"),
        MemoryLayout.paddingLayout(48),
        MemoryLayout.sequenceLayout(4, Constants$root.C_FLOAT$LAYOUT).withName("point1"),
        MemoryLayout.sequenceLayout(4, Constants$root.C_FLOAT$LAYOUT).withName("axis_x1"),
        MemoryLayout.sequenceLayout(4, Constants$root.C_FLOAT$LAYOUT).withName("axis_y1"),
        MemoryLayout.sequenceLayout(4, Constants$root.C_FLOAT$LAYOUT).withName("point2"),
        MemoryLayout.sequenceLayout(4, Constants$root.C_FLOAT$LAYOUT).withName("axis_x2"),
        MemoryLayout.sequenceLayout(4, Constants$root.C_FLOAT$LAYOUT).withName("axis_y2")
    ).withName("JPC_FixedConstraintSettings");
    public static MemoryLayout $LAYOUT() {
        return JPC_FixedConstraintSettings.$struct$LAYOUT;
    }
    static final VarHandle base$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("base"));
    public static VarHandle base$VH() {
        return JPC_FixedConstraintSettings.base$VH;
    }
    public static MemoryAddress base$get(MemorySegment seg) {
        return (java.lang.foreign.MemoryAddress)JPC_FixedConstraintSettings.base$VH.get(seg);
    }
    public static void base$set( MemorySegment seg, MemoryAddress x) {
        JPC_FixedConstraintSettings.base$VH.set(seg, x);
    }
    public static MemoryAddress base$get(MemorySegment seg, long index) {
        return (java.lang.foreign.MemoryAddress)JPC_FixedConstraintSettings.base$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void base$set(MemorySegment seg, long index, MemoryAddress x) {
        JPC_FixedConstraintSettings.base$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle space$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("space"));
    public static VarHandle space$VH() {
        return JPC_FixedConstraintSettings.space$VH;
    }
    public static byte space$get(MemorySegment seg) {
        return (byte)JPC_FixedConstraintSettings.space$VH.get(seg);
    }
    public static void space$set( MemorySegment seg, byte x) {
        JPC_FixedConstraintSettings.space$VH.set(seg, x);
    }
    public static byte space$get(MemorySegment seg, long index) {
        return (byte)JPC_FixedConstraintSettings.space$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void space$set(MemorySegment seg, long index, byte x) {
        JPC_FixedConstraintSettings.space$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle auto_detect_point$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("auto_detect_point"));
    public static VarHandle auto_detect_point$VH() {
        return JPC_FixedConstraintSettings.auto_detect_point$VH;
    }
    public static boolean auto_detect_point$get(MemorySegment seg) {
        return (boolean)JPC_FixedConstraintSettings.auto_detect_point$VH.get(seg);
    }
    public static void auto_detect_point$set( MemorySegment seg, boolean x) {
        JPC_FixedConstraintSettings.auto_detect_point$VH.set(seg, x);
    }
    public static boolean auto_detect_point$get(MemorySegment seg, long index) {
        return (boolean)JPC_FixedConstraintSettings.auto_detect_point$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void auto_detect_point$set(MemorySegment seg, long index, boolean x) {
        JPC_FixedConstraintSettings.auto_detect_point$VH.set(seg.asSlice(index*sizeof()), x);
    }
    public static MemorySegment point1$slice(MemorySegment seg) {
        return seg.asSlice(16, 16);
    }
    public static MemorySegment axis_x1$slice(MemorySegment seg) {
        return seg.asSlice(32, 16);
    }
    public static MemorySegment axis_y1$slice(MemorySegment seg) {
        return seg.asSlice(48, 16);
    }
    public static MemorySegment point2$slice(MemorySegment seg) {
        return seg.asSlice(64, 16);
    }
    public static MemorySegment axis_x2$slice(MemorySegment seg) {
        return seg.asSlice(80, 16);
    }
    public static MemorySegment axis_y2$slice(MemorySegment seg) {
        return seg.asSlice(96, 16);
    }
    public static long sizeof() { return $LAYOUT().byteSize(); }
    public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate($LAYOUT()); }
    public static MemorySegment allocateArray(int len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }
    public static MemorySegment ofAddress(MemoryAddress addr, MemorySession session) { return RuntimeHelper.asArray(addr, $LAYOUT(), 1, session); }
}

