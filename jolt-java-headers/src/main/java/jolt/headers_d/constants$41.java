// Generated by jextract

package jolt.headers_d;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$41 {

    static final FunctionDescriptor JPC_NarrowPhaseQuery_CollidePoint$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_NarrowPhaseQuery_CollidePoint$MH = RuntimeHelper.downcallHandle(
        "JPC_NarrowPhaseQuery_CollidePoint",
        constants$41.JPC_NarrowPhaseQuery_CollidePoint$FUNC
    );
    static final FunctionDescriptor JPC_NarrowPhaseQuery_CollideShape$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_NarrowPhaseQuery_CollideShape$MH = RuntimeHelper.downcallHandle(
        "JPC_NarrowPhaseQuery_CollideShape",
        constants$41.JPC_NarrowPhaseQuery_CollideShape$FUNC
    );
    static final FunctionDescriptor JPC_NarrowPhaseQuery_CollectTransformedShapes$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_NarrowPhaseQuery_CollectTransformedShapes$MH = RuntimeHelper.downcallHandle(
        "JPC_NarrowPhaseQuery_CollectTransformedShapes",
        constants$41.JPC_NarrowPhaseQuery_CollectTransformedShapes$FUNC
    );
    static final FunctionDescriptor JPC_SphereShape_Create$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_FLOAT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_SphereShape_Create$MH = RuntimeHelper.downcallHandle(
        "JPC_SphereShape_Create",
        constants$41.JPC_SphereShape_Create$FUNC
    );
    static final FunctionDescriptor JPC_SphereShape_GetRadius$FUNC = FunctionDescriptor.of(Constants$root.C_FLOAT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_SphereShape_GetRadius$MH = RuntimeHelper.downcallHandle(
        "JPC_SphereShape_GetRadius",
        constants$41.JPC_SphereShape_GetRadius$FUNC
    );
    static final FunctionDescriptor JPC_BoxShape_Create$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_FLOAT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_BoxShape_Create$MH = RuntimeHelper.downcallHandle(
        "JPC_BoxShape_Create",
        constants$41.JPC_BoxShape_Create$FUNC
    );
}


