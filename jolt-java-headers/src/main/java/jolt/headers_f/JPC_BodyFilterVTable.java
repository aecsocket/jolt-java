// Generated by jextract

package jolt.headers_f;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
public class JPC_BodyFilterVTable {

    static final  GroupLayout $struct$LAYOUT = MemoryLayout.structLayout(
        Constants$root.C_POINTER$LAYOUT.withName("__unused0"),
        Constants$root.C_POINTER$LAYOUT.withName("__unused1"),
        Constants$root.C_POINTER$LAYOUT.withName("ShouldCollide"),
        Constants$root.C_POINTER$LAYOUT.withName("ShouldCollideLocked")
    ).withName("JPC_BodyFilterVTable");
    public static MemoryLayout $LAYOUT() {
        return JPC_BodyFilterVTable.$struct$LAYOUT;
    }
    static final VarHandle __unused0$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("__unused0"));
    public static VarHandle __unused0$VH() {
        return JPC_BodyFilterVTable.__unused0$VH;
    }
    public static MemoryAddress __unused0$get(MemorySegment seg) {
        return (java.lang.foreign.MemoryAddress)JPC_BodyFilterVTable.__unused0$VH.get(seg);
    }
    public static void __unused0$set( MemorySegment seg, MemoryAddress x) {
        JPC_BodyFilterVTable.__unused0$VH.set(seg, x);
    }
    public static MemoryAddress __unused0$get(MemorySegment seg, long index) {
        return (java.lang.foreign.MemoryAddress)JPC_BodyFilterVTable.__unused0$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void __unused0$set(MemorySegment seg, long index, MemoryAddress x) {
        JPC_BodyFilterVTable.__unused0$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle __unused1$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("__unused1"));
    public static VarHandle __unused1$VH() {
        return JPC_BodyFilterVTable.__unused1$VH;
    }
    public static MemoryAddress __unused1$get(MemorySegment seg) {
        return (java.lang.foreign.MemoryAddress)JPC_BodyFilterVTable.__unused1$VH.get(seg);
    }
    public static void __unused1$set( MemorySegment seg, MemoryAddress x) {
        JPC_BodyFilterVTable.__unused1$VH.set(seg, x);
    }
    public static MemoryAddress __unused1$get(MemorySegment seg, long index) {
        return (java.lang.foreign.MemoryAddress)JPC_BodyFilterVTable.__unused1$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void __unused1$set(MemorySegment seg, long index, MemoryAddress x) {
        JPC_BodyFilterVTable.__unused1$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final FunctionDescriptor ShouldCollide$FUNC = FunctionDescriptor.of(Constants$root.C_BOOL$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle ShouldCollide$MH = RuntimeHelper.downcallHandle(
        JPC_BodyFilterVTable.ShouldCollide$FUNC
    );
    public interface ShouldCollide {

        boolean apply(java.lang.foreign.MemoryAddress _x0, int _x1);
        static MemorySegment allocate(ShouldCollide fi, MemorySession session) {
            return RuntimeHelper.upcallStub(ShouldCollide.class, fi, JPC_BodyFilterVTable.ShouldCollide$FUNC, session);
        }
        static ShouldCollide ofAddress(MemoryAddress addr, MemorySession session) {
            MemorySegment symbol = MemorySegment.ofAddress(addr, 0, session);
            return (java.lang.foreign.MemoryAddress __x0, int __x1) -> {
                try {
                    return (boolean)JPC_BodyFilterVTable.ShouldCollide$MH.invokeExact((Addressable)symbol, (java.lang.foreign.Addressable)__x0, __x1);
                } catch (Throwable ex$) {
                    throw new AssertionError("should not reach here", ex$);
                }
            };
        }
    }

    static final VarHandle ShouldCollide$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("ShouldCollide"));
    public static VarHandle ShouldCollide$VH() {
        return JPC_BodyFilterVTable.ShouldCollide$VH;
    }
    public static MemoryAddress ShouldCollide$get(MemorySegment seg) {
        return (java.lang.foreign.MemoryAddress)JPC_BodyFilterVTable.ShouldCollide$VH.get(seg);
    }
    public static void ShouldCollide$set( MemorySegment seg, MemoryAddress x) {
        JPC_BodyFilterVTable.ShouldCollide$VH.set(seg, x);
    }
    public static MemoryAddress ShouldCollide$get(MemorySegment seg, long index) {
        return (java.lang.foreign.MemoryAddress)JPC_BodyFilterVTable.ShouldCollide$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void ShouldCollide$set(MemorySegment seg, long index, MemoryAddress x) {
        JPC_BodyFilterVTable.ShouldCollide$VH.set(seg.asSlice(index*sizeof()), x);
    }
    public static ShouldCollide ShouldCollide (MemorySegment segment, MemorySession session) {
        return ShouldCollide.ofAddress(ShouldCollide$get(segment), session);
    }
    static final FunctionDescriptor ShouldCollideLocked$FUNC = FunctionDescriptor.of(Constants$root.C_BOOL$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle ShouldCollideLocked$MH = RuntimeHelper.downcallHandle(
        JPC_BodyFilterVTable.ShouldCollideLocked$FUNC
    );
    public interface ShouldCollideLocked {

        boolean apply(java.lang.foreign.MemoryAddress _x0, java.lang.foreign.MemoryAddress _x1);
        static MemorySegment allocate(ShouldCollideLocked fi, MemorySession session) {
            return RuntimeHelper.upcallStub(ShouldCollideLocked.class, fi, JPC_BodyFilterVTable.ShouldCollideLocked$FUNC, session);
        }
        static ShouldCollideLocked ofAddress(MemoryAddress addr, MemorySession session) {
            MemorySegment symbol = MemorySegment.ofAddress(addr, 0, session);
            return (java.lang.foreign.MemoryAddress __x0, java.lang.foreign.MemoryAddress __x1) -> {
                try {
                    return (boolean)JPC_BodyFilterVTable.ShouldCollideLocked$MH.invokeExact((Addressable)symbol, (java.lang.foreign.Addressable)__x0, (java.lang.foreign.Addressable)__x1);
                } catch (Throwable ex$) {
                    throw new AssertionError("should not reach here", ex$);
                }
            };
        }
    }

    static final VarHandle ShouldCollideLocked$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("ShouldCollideLocked"));
    public static VarHandle ShouldCollideLocked$VH() {
        return JPC_BodyFilterVTable.ShouldCollideLocked$VH;
    }
    public static MemoryAddress ShouldCollideLocked$get(MemorySegment seg) {
        return (java.lang.foreign.MemoryAddress)JPC_BodyFilterVTable.ShouldCollideLocked$VH.get(seg);
    }
    public static void ShouldCollideLocked$set( MemorySegment seg, MemoryAddress x) {
        JPC_BodyFilterVTable.ShouldCollideLocked$VH.set(seg, x);
    }
    public static MemoryAddress ShouldCollideLocked$get(MemorySegment seg, long index) {
        return (java.lang.foreign.MemoryAddress)JPC_BodyFilterVTable.ShouldCollideLocked$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void ShouldCollideLocked$set(MemorySegment seg, long index, MemoryAddress x) {
        JPC_BodyFilterVTable.ShouldCollideLocked$VH.set(seg.asSlice(index*sizeof()), x);
    }
    public static ShouldCollideLocked ShouldCollideLocked (MemorySegment segment, MemorySession session) {
        return ShouldCollideLocked.ofAddress(ShouldCollideLocked$get(segment), session);
    }
    public static long sizeof() { return $LAYOUT().byteSize(); }
    public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate($LAYOUT()); }
    public static MemorySegment allocateArray(int len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }
    public static MemorySegment ofAddress(MemoryAddress addr, MemorySession session) { return RuntimeHelper.asArray(addr, $LAYOUT(), 1, session); }
}


