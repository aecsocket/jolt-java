// Generated by jextract

package jolt.headers_d;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$39 {

    static final FunctionDescriptor JPC_CylinderShapeSettings_SetRadius$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_FLOAT$LAYOUT
    );
    static final MethodHandle JPC_CylinderShapeSettings_SetRadius$MH = RuntimeHelper.downcallHandle(
        "JPC_CylinderShapeSettings_SetRadius",
        constants$39.JPC_CylinderShapeSettings_SetRadius$FUNC
    );
    static final FunctionDescriptor JPC_ConvexHullShapeSettings_Create$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle JPC_ConvexHullShapeSettings_Create$MH = RuntimeHelper.downcallHandle(
        "JPC_ConvexHullShapeSettings_Create",
        constants$39.JPC_ConvexHullShapeSettings_Create$FUNC
    );
    static final FunctionDescriptor JPC_ConvexHullShapeSettings_GetMaxConvexRadius$FUNC = FunctionDescriptor.of(Constants$root.C_FLOAT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_ConvexHullShapeSettings_GetMaxConvexRadius$MH = RuntimeHelper.downcallHandle(
        "JPC_ConvexHullShapeSettings_GetMaxConvexRadius",
        constants$39.JPC_ConvexHullShapeSettings_GetMaxConvexRadius$FUNC
    );
    static final FunctionDescriptor JPC_ConvexHullShapeSettings_SetMaxConvexRadius$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_FLOAT$LAYOUT
    );
    static final MethodHandle JPC_ConvexHullShapeSettings_SetMaxConvexRadius$MH = RuntimeHelper.downcallHandle(
        "JPC_ConvexHullShapeSettings_SetMaxConvexRadius",
        constants$39.JPC_ConvexHullShapeSettings_SetMaxConvexRadius$FUNC
    );
    static final FunctionDescriptor JPC_ConvexHullShapeSettings_GetMaxErrorConvexRadius$FUNC = FunctionDescriptor.of(Constants$root.C_FLOAT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_ConvexHullShapeSettings_GetMaxErrorConvexRadius$MH = RuntimeHelper.downcallHandle(
        "JPC_ConvexHullShapeSettings_GetMaxErrorConvexRadius",
        constants$39.JPC_ConvexHullShapeSettings_GetMaxErrorConvexRadius$FUNC
    );
    static final FunctionDescriptor JPC_ConvexHullShapeSettings_SetMaxErrorConvexRadius$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_FLOAT$LAYOUT
    );
    static final MethodHandle JPC_ConvexHullShapeSettings_SetMaxErrorConvexRadius$MH = RuntimeHelper.downcallHandle(
        "JPC_ConvexHullShapeSettings_SetMaxErrorConvexRadius",
        constants$39.JPC_ConvexHullShapeSettings_SetMaxErrorConvexRadius$FUNC
    );
}


