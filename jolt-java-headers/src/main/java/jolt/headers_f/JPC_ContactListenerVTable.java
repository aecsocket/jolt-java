// Generated by jextract

package jolt.headers_f;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
public class JPC_ContactListenerVTable {

    static final  GroupLayout $struct$LAYOUT = MemoryLayout.structLayout(
        Constants$root.C_POINTER$LAYOUT.withName("__unused0"),
        Constants$root.C_POINTER$LAYOUT.withName("__unused1"),
        Constants$root.C_POINTER$LAYOUT.withName("OnContactValidate"),
        Constants$root.C_POINTER$LAYOUT.withName("OnContactAdded"),
        Constants$root.C_POINTER$LAYOUT.withName("OnContactPersisted"),
        Constants$root.C_POINTER$LAYOUT.withName("OnContactRemoved")
    ).withName("JPC_ContactListenerVTable");
    public static MemoryLayout $LAYOUT() {
        return JPC_ContactListenerVTable.$struct$LAYOUT;
    }
    static final VarHandle __unused0$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("__unused0"));
    public static VarHandle __unused0$VH() {
        return JPC_ContactListenerVTable.__unused0$VH;
    }
    public static MemoryAddress __unused0$get(MemorySegment seg) {
        return (java.lang.foreign.MemoryAddress)JPC_ContactListenerVTable.__unused0$VH.get(seg);
    }
    public static void __unused0$set( MemorySegment seg, MemoryAddress x) {
        JPC_ContactListenerVTable.__unused0$VH.set(seg, x);
    }
    public static MemoryAddress __unused0$get(MemorySegment seg, long index) {
        return (java.lang.foreign.MemoryAddress)JPC_ContactListenerVTable.__unused0$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void __unused0$set(MemorySegment seg, long index, MemoryAddress x) {
        JPC_ContactListenerVTable.__unused0$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle __unused1$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("__unused1"));
    public static VarHandle __unused1$VH() {
        return JPC_ContactListenerVTable.__unused1$VH;
    }
    public static MemoryAddress __unused1$get(MemorySegment seg) {
        return (java.lang.foreign.MemoryAddress)JPC_ContactListenerVTable.__unused1$VH.get(seg);
    }
    public static void __unused1$set( MemorySegment seg, MemoryAddress x) {
        JPC_ContactListenerVTable.__unused1$VH.set(seg, x);
    }
    public static MemoryAddress __unused1$get(MemorySegment seg, long index) {
        return (java.lang.foreign.MemoryAddress)JPC_ContactListenerVTable.__unused1$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void __unused1$set(MemorySegment seg, long index, MemoryAddress x) {
        JPC_ContactListenerVTable.__unused1$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final FunctionDescriptor OnContactValidate$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle OnContactValidate$MH = RuntimeHelper.downcallHandle(
        JPC_ContactListenerVTable.OnContactValidate$FUNC
    );
    public interface OnContactValidate {

        int apply(java.lang.foreign.MemoryAddress _x0, java.lang.foreign.MemoryAddress _x1, java.lang.foreign.MemoryAddress _x2, java.lang.foreign.MemoryAddress _x3, java.lang.foreign.MemoryAddress _x4);
        static MemorySegment allocate(OnContactValidate fi, MemorySession session) {
            return RuntimeHelper.upcallStub(OnContactValidate.class, fi, JPC_ContactListenerVTable.OnContactValidate$FUNC, session);
        }
        static OnContactValidate ofAddress(MemoryAddress addr, MemorySession session) {
            MemorySegment symbol = MemorySegment.ofAddress(addr, 0, session);
            return (java.lang.foreign.MemoryAddress __x0, java.lang.foreign.MemoryAddress __x1, java.lang.foreign.MemoryAddress __x2, java.lang.foreign.MemoryAddress __x3, java.lang.foreign.MemoryAddress __x4) -> {
                try {
                    return (int)JPC_ContactListenerVTable.OnContactValidate$MH.invokeExact((Addressable)symbol, (java.lang.foreign.Addressable)__x0, (java.lang.foreign.Addressable)__x1, (java.lang.foreign.Addressable)__x2, (java.lang.foreign.Addressable)__x3, (java.lang.foreign.Addressable)__x4);
                } catch (Throwable ex$) {
                    throw new AssertionError("should not reach here", ex$);
                }
            };
        }
    }

    static final VarHandle OnContactValidate$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("OnContactValidate"));
    public static VarHandle OnContactValidate$VH() {
        return JPC_ContactListenerVTable.OnContactValidate$VH;
    }
    public static MemoryAddress OnContactValidate$get(MemorySegment seg) {
        return (java.lang.foreign.MemoryAddress)JPC_ContactListenerVTable.OnContactValidate$VH.get(seg);
    }
    public static void OnContactValidate$set( MemorySegment seg, MemoryAddress x) {
        JPC_ContactListenerVTable.OnContactValidate$VH.set(seg, x);
    }
    public static MemoryAddress OnContactValidate$get(MemorySegment seg, long index) {
        return (java.lang.foreign.MemoryAddress)JPC_ContactListenerVTable.OnContactValidate$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void OnContactValidate$set(MemorySegment seg, long index, MemoryAddress x) {
        JPC_ContactListenerVTable.OnContactValidate$VH.set(seg.asSlice(index*sizeof()), x);
    }
    public static OnContactValidate OnContactValidate (MemorySegment segment, MemorySession session) {
        return OnContactValidate.ofAddress(OnContactValidate$get(segment), session);
    }
    static final FunctionDescriptor OnContactAdded$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle OnContactAdded$MH = RuntimeHelper.downcallHandle(
        JPC_ContactListenerVTable.OnContactAdded$FUNC
    );
    public interface OnContactAdded {

        void apply(java.lang.foreign.MemoryAddress _x0, java.lang.foreign.MemoryAddress _x1, java.lang.foreign.MemoryAddress _x2, java.lang.foreign.MemoryAddress _x3, java.lang.foreign.MemoryAddress _x4);
        static MemorySegment allocate(OnContactAdded fi, MemorySession session) {
            return RuntimeHelper.upcallStub(OnContactAdded.class, fi, JPC_ContactListenerVTable.OnContactAdded$FUNC, session);
        }
        static OnContactAdded ofAddress(MemoryAddress addr, MemorySession session) {
            MemorySegment symbol = MemorySegment.ofAddress(addr, 0, session);
            return (java.lang.foreign.MemoryAddress __x0, java.lang.foreign.MemoryAddress __x1, java.lang.foreign.MemoryAddress __x2, java.lang.foreign.MemoryAddress __x3, java.lang.foreign.MemoryAddress __x4) -> {
                try {
                    JPC_ContactListenerVTable.OnContactAdded$MH.invokeExact((Addressable)symbol, (java.lang.foreign.Addressable)__x0, (java.lang.foreign.Addressable)__x1, (java.lang.foreign.Addressable)__x2, (java.lang.foreign.Addressable)__x3, (java.lang.foreign.Addressable)__x4);
                } catch (Throwable ex$) {
                    throw new AssertionError("should not reach here", ex$);
                }
            };
        }
    }

    static final VarHandle OnContactAdded$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("OnContactAdded"));
    public static VarHandle OnContactAdded$VH() {
        return JPC_ContactListenerVTable.OnContactAdded$VH;
    }
    public static MemoryAddress OnContactAdded$get(MemorySegment seg) {
        return (java.lang.foreign.MemoryAddress)JPC_ContactListenerVTable.OnContactAdded$VH.get(seg);
    }
    public static void OnContactAdded$set( MemorySegment seg, MemoryAddress x) {
        JPC_ContactListenerVTable.OnContactAdded$VH.set(seg, x);
    }
    public static MemoryAddress OnContactAdded$get(MemorySegment seg, long index) {
        return (java.lang.foreign.MemoryAddress)JPC_ContactListenerVTable.OnContactAdded$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void OnContactAdded$set(MemorySegment seg, long index, MemoryAddress x) {
        JPC_ContactListenerVTable.OnContactAdded$VH.set(seg.asSlice(index*sizeof()), x);
    }
    public static OnContactAdded OnContactAdded (MemorySegment segment, MemorySession session) {
        return OnContactAdded.ofAddress(OnContactAdded$get(segment), session);
    }
    static final FunctionDescriptor OnContactPersisted$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle OnContactPersisted$MH = RuntimeHelper.downcallHandle(
        JPC_ContactListenerVTable.OnContactPersisted$FUNC
    );
    public interface OnContactPersisted {

        void apply(java.lang.foreign.MemoryAddress _x0, java.lang.foreign.MemoryAddress _x1, java.lang.foreign.MemoryAddress _x2, java.lang.foreign.MemoryAddress _x3, java.lang.foreign.MemoryAddress _x4);
        static MemorySegment allocate(OnContactPersisted fi, MemorySession session) {
            return RuntimeHelper.upcallStub(OnContactPersisted.class, fi, JPC_ContactListenerVTable.OnContactPersisted$FUNC, session);
        }
        static OnContactPersisted ofAddress(MemoryAddress addr, MemorySession session) {
            MemorySegment symbol = MemorySegment.ofAddress(addr, 0, session);
            return (java.lang.foreign.MemoryAddress __x0, java.lang.foreign.MemoryAddress __x1, java.lang.foreign.MemoryAddress __x2, java.lang.foreign.MemoryAddress __x3, java.lang.foreign.MemoryAddress __x4) -> {
                try {
                    JPC_ContactListenerVTable.OnContactPersisted$MH.invokeExact((Addressable)symbol, (java.lang.foreign.Addressable)__x0, (java.lang.foreign.Addressable)__x1, (java.lang.foreign.Addressable)__x2, (java.lang.foreign.Addressable)__x3, (java.lang.foreign.Addressable)__x4);
                } catch (Throwable ex$) {
                    throw new AssertionError("should not reach here", ex$);
                }
            };
        }
    }

    static final VarHandle OnContactPersisted$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("OnContactPersisted"));
    public static VarHandle OnContactPersisted$VH() {
        return JPC_ContactListenerVTable.OnContactPersisted$VH;
    }
    public static MemoryAddress OnContactPersisted$get(MemorySegment seg) {
        return (java.lang.foreign.MemoryAddress)JPC_ContactListenerVTable.OnContactPersisted$VH.get(seg);
    }
    public static void OnContactPersisted$set( MemorySegment seg, MemoryAddress x) {
        JPC_ContactListenerVTable.OnContactPersisted$VH.set(seg, x);
    }
    public static MemoryAddress OnContactPersisted$get(MemorySegment seg, long index) {
        return (java.lang.foreign.MemoryAddress)JPC_ContactListenerVTable.OnContactPersisted$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void OnContactPersisted$set(MemorySegment seg, long index, MemoryAddress x) {
        JPC_ContactListenerVTable.OnContactPersisted$VH.set(seg.asSlice(index*sizeof()), x);
    }
    public static OnContactPersisted OnContactPersisted (MemorySegment segment, MemorySession session) {
        return OnContactPersisted.ofAddress(OnContactPersisted$get(segment), session);
    }
    static final FunctionDescriptor OnContactRemoved$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle OnContactRemoved$MH = RuntimeHelper.downcallHandle(
        JPC_ContactListenerVTable.OnContactRemoved$FUNC
    );
    public interface OnContactRemoved {

        void apply(java.lang.foreign.MemoryAddress _x0, java.lang.foreign.MemoryAddress _x1);
        static MemorySegment allocate(OnContactRemoved fi, MemorySession session) {
            return RuntimeHelper.upcallStub(OnContactRemoved.class, fi, JPC_ContactListenerVTable.OnContactRemoved$FUNC, session);
        }
        static OnContactRemoved ofAddress(MemoryAddress addr, MemorySession session) {
            MemorySegment symbol = MemorySegment.ofAddress(addr, 0, session);
            return (java.lang.foreign.MemoryAddress __x0, java.lang.foreign.MemoryAddress __x1) -> {
                try {
                    JPC_ContactListenerVTable.OnContactRemoved$MH.invokeExact((Addressable)symbol, (java.lang.foreign.Addressable)__x0, (java.lang.foreign.Addressable)__x1);
                } catch (Throwable ex$) {
                    throw new AssertionError("should not reach here", ex$);
                }
            };
        }
    }

    static final VarHandle OnContactRemoved$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("OnContactRemoved"));
    public static VarHandle OnContactRemoved$VH() {
        return JPC_ContactListenerVTable.OnContactRemoved$VH;
    }
    public static MemoryAddress OnContactRemoved$get(MemorySegment seg) {
        return (java.lang.foreign.MemoryAddress)JPC_ContactListenerVTable.OnContactRemoved$VH.get(seg);
    }
    public static void OnContactRemoved$set( MemorySegment seg, MemoryAddress x) {
        JPC_ContactListenerVTable.OnContactRemoved$VH.set(seg, x);
    }
    public static MemoryAddress OnContactRemoved$get(MemorySegment seg, long index) {
        return (java.lang.foreign.MemoryAddress)JPC_ContactListenerVTable.OnContactRemoved$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void OnContactRemoved$set(MemorySegment seg, long index, MemoryAddress x) {
        JPC_ContactListenerVTable.OnContactRemoved$VH.set(seg.asSlice(index*sizeof()), x);
    }
    public static OnContactRemoved OnContactRemoved (MemorySegment segment, MemorySession session) {
        return OnContactRemoved.ofAddress(OnContactRemoved$get(segment), session);
    }
    public static long sizeof() { return $LAYOUT().byteSize(); }
    public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate($LAYOUT()); }
    public static MemorySegment allocateArray(int len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }
    public static MemorySegment ofAddress(MemoryAddress addr, MemorySession session) { return RuntimeHelper.asArray(addr, $LAYOUT(), 1, session); }
}


