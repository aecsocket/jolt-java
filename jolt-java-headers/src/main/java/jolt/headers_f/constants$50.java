// Generated by jextract

package jolt.headers_f;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$50 {

    static final FunctionDescriptor JPC_CylinderShapeSettings_GetHalfHeight$FUNC = FunctionDescriptor.of(Constants$root.C_FLOAT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_CylinderShapeSettings_GetHalfHeight$MH = RuntimeHelper.downcallHandle(
        "JPC_CylinderShapeSettings_GetHalfHeight",
        constants$50.JPC_CylinderShapeSettings_GetHalfHeight$FUNC
    );
    static final FunctionDescriptor JPC_CylinderShapeSettings_SetHalfHeight$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_FLOAT$LAYOUT
    );
    static final MethodHandle JPC_CylinderShapeSettings_SetHalfHeight$MH = RuntimeHelper.downcallHandle(
        "JPC_CylinderShapeSettings_SetHalfHeight",
        constants$50.JPC_CylinderShapeSettings_SetHalfHeight$FUNC
    );
    static final FunctionDescriptor JPC_CylinderShapeSettings_GetRadius$FUNC = FunctionDescriptor.of(Constants$root.C_FLOAT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_CylinderShapeSettings_GetRadius$MH = RuntimeHelper.downcallHandle(
        "JPC_CylinderShapeSettings_GetRadius",
        constants$50.JPC_CylinderShapeSettings_GetRadius$FUNC
    );
    static final FunctionDescriptor JPC_CylinderShapeSettings_SetRadius$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_FLOAT$LAYOUT
    );
    static final MethodHandle JPC_CylinderShapeSettings_SetRadius$MH = RuntimeHelper.downcallHandle(
        "JPC_CylinderShapeSettings_SetRadius",
        constants$50.JPC_CylinderShapeSettings_SetRadius$FUNC
    );
    static final FunctionDescriptor JPC_ConvexHullShapeSettings_Create$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_FLOAT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_ConvexHullShapeSettings_Create$MH = RuntimeHelper.downcallHandle(
        "JPC_ConvexHullShapeSettings_Create",
        constants$50.JPC_ConvexHullShapeSettings_Create$FUNC
    );
    static final FunctionDescriptor JPC_ConvexHullShapeSettings_GetMaxConvexRadius$FUNC = FunctionDescriptor.of(Constants$root.C_FLOAT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_ConvexHullShapeSettings_GetMaxConvexRadius$MH = RuntimeHelper.downcallHandle(
        "JPC_ConvexHullShapeSettings_GetMaxConvexRadius",
        constants$50.JPC_ConvexHullShapeSettings_GetMaxConvexRadius$FUNC
    );
}


