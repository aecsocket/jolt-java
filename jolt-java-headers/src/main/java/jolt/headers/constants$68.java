// Generated by jextract

package jolt.headers;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$68 {

    static final FunctionDescriptor JPC_BodyInterface_GetTransformedShape$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_BodyInterface_GetTransformedShape$MH = RuntimeHelper.downcallHandle(
        "JPC_BodyInterface_GetTransformedShape",
        constants$68.JPC_BodyInterface_GetTransformedShape$FUNC
    );
    static final FunctionDescriptor JPC_BodyInterface_GetUserData$FUNC = FunctionDescriptor.of(Constants$root.C_LONG_LONG$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle JPC_BodyInterface_GetUserData$MH = RuntimeHelper.downcallHandle(
        "JPC_BodyInterface_GetUserData",
        constants$68.JPC_BodyInterface_GetUserData$FUNC
    );
    static final FunctionDescriptor JPC_BodyInterface_GetMaterial$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle JPC_BodyInterface_GetMaterial$MH = RuntimeHelper.downcallHandle(
        "JPC_BodyInterface_GetMaterial",
        constants$68.JPC_BodyInterface_GetMaterial$FUNC
    );
    static final FunctionDescriptor JPC_BodyInterface_InvalidateContactCache$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle JPC_BodyInterface_InvalidateContactCache$MH = RuntimeHelper.downcallHandle(
        "JPC_BodyInterface_InvalidateContactCache",
        constants$68.JPC_BodyInterface_InvalidateContactCache$FUNC
    );
    static final FunctionDescriptor JPC_Body_GetID$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_Body_GetID$MH = RuntimeHelper.downcallHandle(
        "JPC_Body_GetID",
        constants$68.JPC_Body_GetID$FUNC
    );
    static final FunctionDescriptor JPC_Body_IsActive$FUNC = FunctionDescriptor.of(Constants$root.C_BOOL$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_Body_IsActive$MH = RuntimeHelper.downcallHandle(
        "JPC_Body_IsActive",
        constants$68.JPC_Body_IsActive$FUNC
    );
}


