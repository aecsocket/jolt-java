// Generated by jextract

package jolt.headers;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$42 {

    static final FunctionDescriptor JPC_ShapeSettings_Release$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_ShapeSettings_Release$MH = RuntimeHelper.downcallHandle(
        "JPC_ShapeSettings_Release",
        constants$42.JPC_ShapeSettings_Release$FUNC
    );
    static final FunctionDescriptor JPC_ShapeSettings_GetRefCount$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_ShapeSettings_GetRefCount$MH = RuntimeHelper.downcallHandle(
        "JPC_ShapeSettings_GetRefCount",
        constants$42.JPC_ShapeSettings_GetRefCount$FUNC
    );
    static final FunctionDescriptor JPC_ShapeSettings_CreateShape$FUNC = FunctionDescriptor.of(MemoryLayout.structLayout(
        Constants$root.C_POINTER$LAYOUT.withName("result"),
        Constants$root.C_POINTER$LAYOUT.withName("error")
    ).withName("JPC_ShapeResult"),
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_ShapeSettings_CreateShape$MH = RuntimeHelper.downcallHandle(
        "JPC_ShapeSettings_CreateShape",
        constants$42.JPC_ShapeSettings_CreateShape$FUNC
    );
    static final FunctionDescriptor JPC_ShapeSettings_GetUserData$FUNC = FunctionDescriptor.of(Constants$root.C_LONG_LONG$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_ShapeSettings_GetUserData$MH = RuntimeHelper.downcallHandle(
        "JPC_ShapeSettings_GetUserData",
        constants$42.JPC_ShapeSettings_GetUserData$FUNC
    );
    static final FunctionDescriptor JPC_ShapeSettings_SetUserData$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT
    );
    static final MethodHandle JPC_ShapeSettings_SetUserData$MH = RuntimeHelper.downcallHandle(
        "JPC_ShapeSettings_SetUserData",
        constants$42.JPC_ShapeSettings_SetUserData$FUNC
    );
    static final FunctionDescriptor JPC_ConvexShapeSettings_GetMaterial$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_ConvexShapeSettings_GetMaterial$MH = RuntimeHelper.downcallHandle(
        "JPC_ConvexShapeSettings_GetMaterial",
        constants$42.JPC_ConvexShapeSettings_GetMaterial$FUNC
    );
}


