// Generated by jextract

package jolt.headers_f;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$44 {

    static final FunctionDescriptor JPC_BoxShapeSettings_GetConvexRadius$FUNC = FunctionDescriptor.of(Constants$root.C_FLOAT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_BoxShapeSettings_GetConvexRadius$MH = RuntimeHelper.downcallHandle(
        "JPC_BoxShapeSettings_GetConvexRadius",
        constants$44.JPC_BoxShapeSettings_GetConvexRadius$FUNC
    );
    static final FunctionDescriptor JPC_BoxShapeSettings_SetConvexRadius$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_FLOAT$LAYOUT
    );
    static final MethodHandle JPC_BoxShapeSettings_SetConvexRadius$MH = RuntimeHelper.downcallHandle(
        "JPC_BoxShapeSettings_SetConvexRadius",
        constants$44.JPC_BoxShapeSettings_SetConvexRadius$FUNC
    );
    static final FunctionDescriptor JPC_SphereShapeSettings_Create$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_FLOAT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_SphereShapeSettings_Create$MH = RuntimeHelper.downcallHandle(
        "JPC_SphereShapeSettings_Create",
        constants$44.JPC_SphereShapeSettings_Create$FUNC
    );
    static final FunctionDescriptor JPC_SphereShapeSettings_GetRadius$FUNC = FunctionDescriptor.of(Constants$root.C_FLOAT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_SphereShapeSettings_GetRadius$MH = RuntimeHelper.downcallHandle(
        "JPC_SphereShapeSettings_GetRadius",
        constants$44.JPC_SphereShapeSettings_GetRadius$FUNC
    );
    static final FunctionDescriptor JPC_SphereShapeSettings_SetRadius$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_FLOAT$LAYOUT
    );
    static final MethodHandle JPC_SphereShapeSettings_SetRadius$MH = RuntimeHelper.downcallHandle(
        "JPC_SphereShapeSettings_SetRadius",
        constants$44.JPC_SphereShapeSettings_SetRadius$FUNC
    );
    static final FunctionDescriptor JPC_TriangleShapeSettings_Create$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_FLOAT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_TriangleShapeSettings_Create$MH = RuntimeHelper.downcallHandle(
        "JPC_TriangleShapeSettings_Create",
        constants$44.JPC_TriangleShapeSettings_Create$FUNC
    );
}


