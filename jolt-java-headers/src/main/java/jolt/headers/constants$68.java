// Generated by jextract

package jolt.headers;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$68 {

    static final FunctionDescriptor JPC_Body_SetCollisionGroup$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_Body_SetCollisionGroup$MH = RuntimeHelper.downcallHandle(
        "JPC_Body_SetCollisionGroup",
        constants$68.JPC_Body_SetCollisionGroup$FUNC
    );
    static final FunctionDescriptor JPC_Body_GetAllowSleeping$FUNC = FunctionDescriptor.of(Constants$root.C_BOOL$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_Body_GetAllowSleeping$MH = RuntimeHelper.downcallHandle(
        "JPC_Body_GetAllowSleeping",
        constants$68.JPC_Body_GetAllowSleeping$FUNC
    );
    static final FunctionDescriptor JPC_Body_SetAllowSleeping$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_BOOL$LAYOUT
    );
    static final MethodHandle JPC_Body_SetAllowSleeping$MH = RuntimeHelper.downcallHandle(
        "JPC_Body_SetAllowSleeping",
        constants$68.JPC_Body_SetAllowSleeping$FUNC
    );
    static final FunctionDescriptor JPC_Body_GetFriction$FUNC = FunctionDescriptor.of(Constants$root.C_FLOAT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_Body_GetFriction$MH = RuntimeHelper.downcallHandle(
        "JPC_Body_GetFriction",
        constants$68.JPC_Body_GetFriction$FUNC
    );
    static final FunctionDescriptor JPC_Body_SetFriction$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_FLOAT$LAYOUT
    );
    static final MethodHandle JPC_Body_SetFriction$MH = RuntimeHelper.downcallHandle(
        "JPC_Body_SetFriction",
        constants$68.JPC_Body_SetFriction$FUNC
    );
    static final FunctionDescriptor JPC_Body_GetRestitution$FUNC = FunctionDescriptor.of(Constants$root.C_FLOAT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_Body_GetRestitution$MH = RuntimeHelper.downcallHandle(
        "JPC_Body_GetRestitution",
        constants$68.JPC_Body_GetRestitution$FUNC
    );
}

