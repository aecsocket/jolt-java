// Generated by jextract

package jolt.headers_d;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$76 {

    static final FunctionDescriptor JPC_Body_GetUserData$FUNC = FunctionDescriptor.of(Constants$root.C_LONG_LONG$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_Body_GetUserData$MH = RuntimeHelper.downcallHandle(
        "JPC_Body_GetUserData",
        constants$76.JPC_Body_GetUserData$FUNC
    );
    static final FunctionDescriptor JPC_Body_SetUserData$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT
    );
    static final MethodHandle JPC_Body_SetUserData$MH = RuntimeHelper.downcallHandle(
        "JPC_Body_SetUserData",
        constants$76.JPC_Body_SetUserData$FUNC
    );
    static final FunctionDescriptor JPC_Body_GetWorldSpaceSurfaceNormal$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_Body_GetWorldSpaceSurfaceNormal$MH = RuntimeHelper.downcallHandle(
        "JPC_Body_GetWorldSpaceSurfaceNormal",
        constants$76.JPC_Body_GetWorldSpaceSurfaceNormal$FUNC
    );
    static final FunctionDescriptor JPC_BodyID_GetIndex$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle JPC_BodyID_GetIndex$MH = RuntimeHelper.downcallHandle(
        "JPC_BodyID_GetIndex",
        constants$76.JPC_BodyID_GetIndex$FUNC
    );
    static final FunctionDescriptor JPC_BodyID_GetSequenceNumber$FUNC = FunctionDescriptor.of(Constants$root.C_CHAR$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle JPC_BodyID_GetSequenceNumber$MH = RuntimeHelper.downcallHandle(
        "JPC_BodyID_GetSequenceNumber",
        constants$76.JPC_BodyID_GetSequenceNumber$FUNC
    );
    static final FunctionDescriptor JPC_BodyID_IsInvalid$FUNC = FunctionDescriptor.of(Constants$root.C_BOOL$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle JPC_BodyID_IsInvalid$MH = RuntimeHelper.downcallHandle(
        "JPC_BodyID_IsInvalid",
        constants$76.JPC_BodyID_IsInvalid$FUNC
    );
}

