// Generated by jextract

package jolt.headers_f;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$88 {

    static final FunctionDescriptor JPC_GJKClosestPoint_GetClosestPointsConvexPoint$FUNC = FunctionDescriptor.of(Constants$root.C_FLOAT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_FLOAT$LAYOUT,
        Constants$root.C_FLOAT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_GJKClosestPoint_GetClosestPointsConvexPoint$MH = RuntimeHelper.downcallHandle(
        "JPC_GJKClosestPoint_GetClosestPointsConvexPoint",
        constants$88.JPC_GJKClosestPoint_GetClosestPointsConvexPoint$FUNC
    );
    static final FunctionDescriptor JPJ_GetFeatures$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT);
    static final MethodHandle JPJ_GetFeatures$MH = RuntimeHelper.downcallHandleVariadic(
        "JPJ_GetFeatures",
        constants$88.JPJ_GetFeatures$FUNC
    );
    static final MemoryAddress NULL$ADDR = MemoryAddress.ofLong(0L);
}


