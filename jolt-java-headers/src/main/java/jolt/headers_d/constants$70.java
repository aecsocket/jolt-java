// Generated by jextract

package jolt.headers_d;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$70 {

    static final FunctionDescriptor JPC_Body_GetMotionType$FUNC = FunctionDescriptor.of(Constants$root.C_CHAR$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_Body_GetMotionType$MH = RuntimeHelper.downcallHandle(
        "JPC_Body_GetMotionType",
        constants$70.JPC_Body_GetMotionType$FUNC
    );
    static final FunctionDescriptor JPC_Body_SetMotionType$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_CHAR$LAYOUT
    );
    static final MethodHandle JPC_Body_SetMotionType$MH = RuntimeHelper.downcallHandle(
        "JPC_Body_SetMotionType",
        constants$70.JPC_Body_SetMotionType$FUNC
    );
    static final FunctionDescriptor JPC_Body_GetBroadPhaseLayer$FUNC = FunctionDescriptor.of(Constants$root.C_CHAR$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_Body_GetBroadPhaseLayer$MH = RuntimeHelper.downcallHandle(
        "JPC_Body_GetBroadPhaseLayer",
        constants$70.JPC_Body_GetBroadPhaseLayer$FUNC
    );
    static final FunctionDescriptor JPC_Body_GetObjectLayer$FUNC = FunctionDescriptor.of(Constants$root.C_SHORT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_Body_GetObjectLayer$MH = RuntimeHelper.downcallHandle(
        "JPC_Body_GetObjectLayer",
        constants$70.JPC_Body_GetObjectLayer$FUNC
    );
    static final FunctionDescriptor JPC_Body_GetCollisionGroup$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_Body_GetCollisionGroup$MH = RuntimeHelper.downcallHandle(
        "JPC_Body_GetCollisionGroup",
        constants$70.JPC_Body_GetCollisionGroup$FUNC
    );
    static final FunctionDescriptor JPC_Body_SetCollisionGroup$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_Body_SetCollisionGroup$MH = RuntimeHelper.downcallHandle(
        "JPC_Body_SetCollisionGroup",
        constants$70.JPC_Body_SetCollisionGroup$FUNC
    );
}


