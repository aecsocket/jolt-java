// Generated by jextract

package jolt.headers_f;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$37 {

    static final FunctionDescriptor JPC_CapsuleShape_GetHalfHeightOfCylinder$FUNC = FunctionDescriptor.of(Constants$root.C_FLOAT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_CapsuleShape_GetHalfHeightOfCylinder$MH = RuntimeHelper.downcallHandle(
        "JPC_CapsuleShape_GetHalfHeightOfCylinder",
        constants$37.JPC_CapsuleShape_GetHalfHeightOfCylinder$FUNC
    );
    static final FunctionDescriptor JPC_CylinderShape_Create$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_FLOAT$LAYOUT,
        Constants$root.C_FLOAT$LAYOUT,
        Constants$root.C_FLOAT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_CylinderShape_Create$MH = RuntimeHelper.downcallHandle(
        "JPC_CylinderShape_Create",
        constants$37.JPC_CylinderShape_Create$FUNC
    );
    static final FunctionDescriptor JPC_CylinderShape_GetHalfHeight$FUNC = FunctionDescriptor.of(Constants$root.C_FLOAT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_CylinderShape_GetHalfHeight$MH = RuntimeHelper.downcallHandle(
        "JPC_CylinderShape_GetHalfHeight",
        constants$37.JPC_CylinderShape_GetHalfHeight$FUNC
    );
    static final FunctionDescriptor JPC_CylinderShape_GetRadius$FUNC = FunctionDescriptor.of(Constants$root.C_FLOAT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_CylinderShape_GetRadius$MH = RuntimeHelper.downcallHandle(
        "JPC_CylinderShape_GetRadius",
        constants$37.JPC_CylinderShape_GetRadius$FUNC
    );
    static final FunctionDescriptor JPC_ShapeSettings_AddRef$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_ShapeSettings_AddRef$MH = RuntimeHelper.downcallHandle(
        "JPC_ShapeSettings_AddRef",
        constants$37.JPC_ShapeSettings_AddRef$FUNC
    );
    static final FunctionDescriptor JPC_ShapeSettings_Release$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_ShapeSettings_Release$MH = RuntimeHelper.downcallHandle(
        "JPC_ShapeSettings_Release",
        constants$37.JPC_ShapeSettings_Release$FUNC
    );
}


