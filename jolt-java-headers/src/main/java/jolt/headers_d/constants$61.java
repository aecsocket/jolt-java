// Generated by jextract

package jolt.headers_d;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$61 {

    static final FunctionDescriptor JPC_Shape_GetCastRay$FUNC = FunctionDescriptor.of(Constants$root.C_BOOL$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_Shape_GetCastRay$MH = RuntimeHelper.downcallHandle(
        "JPC_Shape_GetCastRay",
        constants$61.JPC_Shape_GetCastRay$FUNC
    );
    static final FunctionDescriptor JPC_Shape_CollectCastRay$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_Shape_CollectCastRay$MH = RuntimeHelper.downcallHandle(
        "JPC_Shape_CollectCastRay",
        constants$61.JPC_Shape_CollectCastRay$FUNC
    );
    static final FunctionDescriptor JPC_Shape_CollidePoint$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_Shape_CollidePoint$MH = RuntimeHelper.downcallHandle(
        "JPC_Shape_CollidePoint",
        constants$61.JPC_Shape_CollidePoint$FUNC
    );
    static final FunctionDescriptor JPC_Shape_CollectTransformedShapes$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_Shape_CollectTransformedShapes$MH = RuntimeHelper.downcallHandle(
        "JPC_Shape_CollectTransformedShapes",
        constants$61.JPC_Shape_CollectTransformedShapes$FUNC
    );
    static final FunctionDescriptor JPC_Shape_TransformShape$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_Shape_TransformShape$MH = RuntimeHelper.downcallHandle(
        "JPC_Shape_TransformShape",
        constants$61.JPC_Shape_TransformShape$FUNC
    );
    static final FunctionDescriptor JPC_Shape_ScaleShape$FUNC = FunctionDescriptor.of(MemoryLayout.structLayout(
        Constants$root.C_POINTER$LAYOUT.withName("result"),
        MemoryLayout.sequenceLayout(256, Constants$root.C_CHAR$LAYOUT).withName("error")
    ).withName("JPC_ShapeResult"),
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_Shape_ScaleShape$MH = RuntimeHelper.downcallHandle(
        "JPC_Shape_ScaleShape",
        constants$61.JPC_Shape_ScaleShape$FUNC
    );
}


