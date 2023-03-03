// Generated by jextract

package jolt.headers;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
public class JPC_CastShapeBodyCollectorVTable {

    static final  GroupLayout $struct$LAYOUT = MemoryLayout.structLayout(
        Constants$root.C_POINTER$LAYOUT.withName("__unused0"),
        Constants$root.C_POINTER$LAYOUT.withName("__unused1"),
        Constants$root.C_POINTER$LAYOUT.withName("Reset"),
        Constants$root.C_POINTER$LAYOUT.withName("OnBody"),
        Constants$root.C_POINTER$LAYOUT.withName("AddHit")
    ).withName("JPC_CastShapeBodyCollectorVTable");
    public static MemoryLayout $LAYOUT() {
        return JPC_CastShapeBodyCollectorVTable.$struct$LAYOUT;
    }
    static final VarHandle __unused0$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("__unused0"));
    public static VarHandle __unused0$VH() {
        return JPC_CastShapeBodyCollectorVTable.__unused0$VH;
    }
    public static MemoryAddress __unused0$get(MemorySegment seg) {
        return (java.lang.foreign.MemoryAddress)JPC_CastShapeBodyCollectorVTable.__unused0$VH.get(seg);
    }
    public static void __unused0$set( MemorySegment seg, MemoryAddress x) {
        JPC_CastShapeBodyCollectorVTable.__unused0$VH.set(seg, x);
    }
    public static MemoryAddress __unused0$get(MemorySegment seg, long index) {
        return (java.lang.foreign.MemoryAddress)JPC_CastShapeBodyCollectorVTable.__unused0$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void __unused0$set(MemorySegment seg, long index, MemoryAddress x) {
        JPC_CastShapeBodyCollectorVTable.__unused0$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle __unused1$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("__unused1"));
    public static VarHandle __unused1$VH() {
        return JPC_CastShapeBodyCollectorVTable.__unused1$VH;
    }
    public static MemoryAddress __unused1$get(MemorySegment seg) {
        return (java.lang.foreign.MemoryAddress)JPC_CastShapeBodyCollectorVTable.__unused1$VH.get(seg);
    }
    public static void __unused1$set( MemorySegment seg, MemoryAddress x) {
        JPC_CastShapeBodyCollectorVTable.__unused1$VH.set(seg, x);
    }
    public static MemoryAddress __unused1$get(MemorySegment seg, long index) {
        return (java.lang.foreign.MemoryAddress)JPC_CastShapeBodyCollectorVTable.__unused1$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void __unused1$set(MemorySegment seg, long index, MemoryAddress x) {
        JPC_CastShapeBodyCollectorVTable.__unused1$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final FunctionDescriptor Reset$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle Reset$MH = RuntimeHelper.downcallHandle(
        JPC_CastShapeBodyCollectorVTable.Reset$FUNC
    );
    public interface Reset {

        void apply(java.lang.foreign.MemoryAddress _x0);
        static MemorySegment allocate(Reset fi, MemorySession session) {
            return RuntimeHelper.upcallStub(Reset.class, fi, JPC_CastShapeBodyCollectorVTable.Reset$FUNC, session);
        }
        static Reset ofAddress(MemoryAddress addr, MemorySession session) {
            MemorySegment symbol = MemorySegment.ofAddress(addr, 0, session);
            return (java.lang.foreign.MemoryAddress __x0) -> {
                try {
                    JPC_CastShapeBodyCollectorVTable.Reset$MH.invokeExact((Addressable)symbol, (java.lang.foreign.Addressable)__x0);
                } catch (Throwable ex$) {
                    throw new AssertionError("should not reach here", ex$);
                }
            };
        }
    }

    static final VarHandle Reset$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("Reset"));
    public static VarHandle Reset$VH() {
        return JPC_CastShapeBodyCollectorVTable.Reset$VH;
    }
    public static MemoryAddress Reset$get(MemorySegment seg) {
        return (java.lang.foreign.MemoryAddress)JPC_CastShapeBodyCollectorVTable.Reset$VH.get(seg);
    }
    public static void Reset$set( MemorySegment seg, MemoryAddress x) {
        JPC_CastShapeBodyCollectorVTable.Reset$VH.set(seg, x);
    }
    public static MemoryAddress Reset$get(MemorySegment seg, long index) {
        return (java.lang.foreign.MemoryAddress)JPC_CastShapeBodyCollectorVTable.Reset$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void Reset$set(MemorySegment seg, long index, MemoryAddress x) {
        JPC_CastShapeBodyCollectorVTable.Reset$VH.set(seg.asSlice(index*sizeof()), x);
    }
    public static Reset Reset (MemorySegment segment, MemorySession session) {
        return Reset.ofAddress(Reset$get(segment), session);
    }
    static final FunctionDescriptor OnBody$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        MemoryLayout.structLayout(
            MemoryLayout.sequenceLayout(4, Constants$root.C_FLOAT$LAYOUT).withName("position"),
            MemoryLayout.sequenceLayout(4, Constants$root.C_FLOAT$LAYOUT).withName("rotation"),
            MemoryLayout.sequenceLayout(4, Constants$root.C_FLOAT$LAYOUT).withName("bounds_min"),
            MemoryLayout.sequenceLayout(4, Constants$root.C_FLOAT$LAYOUT).withName("bounds_max"),
            Constants$root.C_POINTER$LAYOUT.withName("shape"),
            Constants$root.C_POINTER$LAYOUT.withName("motion_properties"),
            Constants$root.C_LONG_LONG$LAYOUT.withName("user_data"),
            MemoryLayout.structLayout(
                Constants$root.C_POINTER$LAYOUT.withName("filter"),
                Constants$root.C_INT$LAYOUT.withName("group_id"),
                Constants$root.C_INT$LAYOUT.withName("sub_group_id")
            ).withName("collision_group"),
            Constants$root.C_FLOAT$LAYOUT.withName("friction"),
            Constants$root.C_FLOAT$LAYOUT.withName("restitution"),
            Constants$root.C_INT$LAYOUT.withName("id"),
            Constants$root.C_SHORT$LAYOUT.withName("object_layer"),
            Constants$root.C_CHAR$LAYOUT.withName("broad_phase_layer"),
            Constants$root.C_CHAR$LAYOUT.withName("motion_type"),
            Constants$root.C_CHAR$LAYOUT.withName("flags"),
            MemoryLayout.paddingLayout(56)
        ).withName("JPC_Body")
    );
    static final MethodHandle OnBody$MH = RuntimeHelper.downcallHandle(
        JPC_CastShapeBodyCollectorVTable.OnBody$FUNC
    );
    public interface OnBody {

        void apply(java.lang.foreign.MemoryAddress _x0, java.lang.foreign.MemorySegment _x1);
        static MemorySegment allocate(OnBody fi, MemorySession session) {
            return RuntimeHelper.upcallStub(OnBody.class, fi, JPC_CastShapeBodyCollectorVTable.OnBody$FUNC, session);
        }
        static OnBody ofAddress(MemoryAddress addr, MemorySession session) {
            MemorySegment symbol = MemorySegment.ofAddress(addr, 0, session);
            return (java.lang.foreign.MemoryAddress __x0, java.lang.foreign.MemorySegment __x1) -> {
                try {
                    JPC_CastShapeBodyCollectorVTable.OnBody$MH.invokeExact((Addressable)symbol, (java.lang.foreign.Addressable)__x0, __x1);
                } catch (Throwable ex$) {
                    throw new AssertionError("should not reach here", ex$);
                }
            };
        }
    }

    static final VarHandle OnBody$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("OnBody"));
    public static VarHandle OnBody$VH() {
        return JPC_CastShapeBodyCollectorVTable.OnBody$VH;
    }
    public static MemoryAddress OnBody$get(MemorySegment seg) {
        return (java.lang.foreign.MemoryAddress)JPC_CastShapeBodyCollectorVTable.OnBody$VH.get(seg);
    }
    public static void OnBody$set( MemorySegment seg, MemoryAddress x) {
        JPC_CastShapeBodyCollectorVTable.OnBody$VH.set(seg, x);
    }
    public static MemoryAddress OnBody$get(MemorySegment seg, long index) {
        return (java.lang.foreign.MemoryAddress)JPC_CastShapeBodyCollectorVTable.OnBody$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void OnBody$set(MemorySegment seg, long index, MemoryAddress x) {
        JPC_CastShapeBodyCollectorVTable.OnBody$VH.set(seg.asSlice(index*sizeof()), x);
    }
    public static OnBody OnBody (MemorySegment segment, MemorySession session) {
        return OnBody.ofAddress(OnBody$get(segment), session);
    }
    static final FunctionDescriptor AddHit$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        MemoryLayout.structLayout(
            Constants$root.C_INT$LAYOUT.withName("body_id"),
            Constants$root.C_FLOAT$LAYOUT.withName("fraction")
        ).withName("JPC_BroadPhaseCastResult")
    );
    static final MethodHandle AddHit$MH = RuntimeHelper.downcallHandle(
        JPC_CastShapeBodyCollectorVTable.AddHit$FUNC
    );
    public interface AddHit {

        void apply(java.lang.foreign.MemoryAddress _x0, java.lang.foreign.MemorySegment _x1);
        static MemorySegment allocate(AddHit fi, MemorySession session) {
            return RuntimeHelper.upcallStub(AddHit.class, fi, JPC_CastShapeBodyCollectorVTable.AddHit$FUNC, session);
        }
        static AddHit ofAddress(MemoryAddress addr, MemorySession session) {
            MemorySegment symbol = MemorySegment.ofAddress(addr, 0, session);
            return (java.lang.foreign.MemoryAddress __x0, java.lang.foreign.MemorySegment __x1) -> {
                try {
                    JPC_CastShapeBodyCollectorVTable.AddHit$MH.invokeExact((Addressable)symbol, (java.lang.foreign.Addressable)__x0, __x1);
                } catch (Throwable ex$) {
                    throw new AssertionError("should not reach here", ex$);
                }
            };
        }
    }

    static final VarHandle AddHit$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("AddHit"));
    public static VarHandle AddHit$VH() {
        return JPC_CastShapeBodyCollectorVTable.AddHit$VH;
    }
    public static MemoryAddress AddHit$get(MemorySegment seg) {
        return (java.lang.foreign.MemoryAddress)JPC_CastShapeBodyCollectorVTable.AddHit$VH.get(seg);
    }
    public static void AddHit$set( MemorySegment seg, MemoryAddress x) {
        JPC_CastShapeBodyCollectorVTable.AddHit$VH.set(seg, x);
    }
    public static MemoryAddress AddHit$get(MemorySegment seg, long index) {
        return (java.lang.foreign.MemoryAddress)JPC_CastShapeBodyCollectorVTable.AddHit$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void AddHit$set(MemorySegment seg, long index, MemoryAddress x) {
        JPC_CastShapeBodyCollectorVTable.AddHit$VH.set(seg.asSlice(index*sizeof()), x);
    }
    public static AddHit AddHit (MemorySegment segment, MemorySession session) {
        return AddHit.ofAddress(AddHit$get(segment), session);
    }
    public static long sizeof() { return $LAYOUT().byteSize(); }
    public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate($LAYOUT()); }
    public static MemorySegment allocateArray(int len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }
    public static MemorySegment ofAddress(MemoryAddress addr, MemorySession session) { return RuntimeHelper.asArray(addr, $LAYOUT(), 1, session); }
}


