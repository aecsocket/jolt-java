// Generated by jextract

package jolt.headers_d;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$35 {

    static final FunctionDescriptor JPC_SphereShapeSettings_SetRadius$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_FLOAT$LAYOUT
    );
    static final MethodHandle JPC_SphereShapeSettings_SetRadius$MH = RuntimeHelper.downcallHandle(
        "JPC_SphereShapeSettings_SetRadius",
        constants$35.JPC_SphereShapeSettings_SetRadius$FUNC
    );
    static final FunctionDescriptor JPC_TriangleShapeSettings_Create$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_TriangleShapeSettings_Create$MH = RuntimeHelper.downcallHandle(
        "JPC_TriangleShapeSettings_Create",
        constants$35.JPC_TriangleShapeSettings_Create$FUNC
    );
    static final FunctionDescriptor JPC_TriangleShapeSettings_SetVertices$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_TriangleShapeSettings_SetVertices$MH = RuntimeHelper.downcallHandle(
        "JPC_TriangleShapeSettings_SetVertices",
        constants$35.JPC_TriangleShapeSettings_SetVertices$FUNC
    );
    static final FunctionDescriptor JPC_TriangleShapeSettings_GetVertices$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_TriangleShapeSettings_GetVertices$MH = RuntimeHelper.downcallHandle(
        "JPC_TriangleShapeSettings_GetVertices",
        constants$35.JPC_TriangleShapeSettings_GetVertices$FUNC
    );
    static final FunctionDescriptor JPC_TriangleShapeSettings_GetConvexRadius$FUNC = FunctionDescriptor.of(Constants$root.C_FLOAT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_TriangleShapeSettings_GetConvexRadius$MH = RuntimeHelper.downcallHandle(
        "JPC_TriangleShapeSettings_GetConvexRadius",
        constants$35.JPC_TriangleShapeSettings_GetConvexRadius$FUNC
    );
    static final FunctionDescriptor JPC_TriangleShapeSettings_SetConvexRadius$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_FLOAT$LAYOUT
    );
    static final MethodHandle JPC_TriangleShapeSettings_SetConvexRadius$MH = RuntimeHelper.downcallHandle(
        "JPC_TriangleShapeSettings_SetConvexRadius",
        constants$35.JPC_TriangleShapeSettings_SetConvexRadius$FUNC
    );
}


