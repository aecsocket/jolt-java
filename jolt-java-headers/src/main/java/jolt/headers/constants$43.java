// Generated by jextract

package jolt.headers;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$43 {

    static final FunctionDescriptor JPC_ConvexShapeSettings_SetMaterial$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_ConvexShapeSettings_SetMaterial$MH = RuntimeHelper.downcallHandle(
        "JPC_ConvexShapeSettings_SetMaterial",
        constants$43.JPC_ConvexShapeSettings_SetMaterial$FUNC
    );
    static final FunctionDescriptor JPC_ConvexShapeSettings_GetDensity$FUNC = FunctionDescriptor.of(Constants$root.C_FLOAT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_ConvexShapeSettings_GetDensity$MH = RuntimeHelper.downcallHandle(
        "JPC_ConvexShapeSettings_GetDensity",
        constants$43.JPC_ConvexShapeSettings_GetDensity$FUNC
    );
    static final FunctionDescriptor JPC_ConvexShapeSettings_SetDensity$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_FLOAT$LAYOUT
    );
    static final MethodHandle JPC_ConvexShapeSettings_SetDensity$MH = RuntimeHelper.downcallHandle(
        "JPC_ConvexShapeSettings_SetDensity",
        constants$43.JPC_ConvexShapeSettings_SetDensity$FUNC
    );
    static final FunctionDescriptor JPC_BoxShapeSettings_Create$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_FLOAT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_BoxShapeSettings_Create$MH = RuntimeHelper.downcallHandle(
        "JPC_BoxShapeSettings_Create",
        constants$43.JPC_BoxShapeSettings_Create$FUNC
    );
    static final FunctionDescriptor JPC_BoxShapeSettings_GetHalfExtent$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_BoxShapeSettings_GetHalfExtent$MH = RuntimeHelper.downcallHandle(
        "JPC_BoxShapeSettings_GetHalfExtent",
        constants$43.JPC_BoxShapeSettings_GetHalfExtent$FUNC
    );
    static final FunctionDescriptor JPC_BoxShapeSettings_SetHalfExtent$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_BoxShapeSettings_SetHalfExtent$MH = RuntimeHelper.downcallHandle(
        "JPC_BoxShapeSettings_SetHalfExtent",
        constants$43.JPC_BoxShapeSettings_SetHalfExtent$FUNC
    );
}


