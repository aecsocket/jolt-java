// Generated by jextract

package jolt.headers;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$69 {

    static final FunctionDescriptor JPC_Body_IsStatic$FUNC = FunctionDescriptor.of(Constants$root.C_BOOL$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_Body_IsStatic$MH = RuntimeHelper.downcallHandle(
        "JPC_Body_IsStatic",
        constants$69.JPC_Body_IsStatic$FUNC
    );
    static final FunctionDescriptor JPC_Body_IsKinematic$FUNC = FunctionDescriptor.of(Constants$root.C_BOOL$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_Body_IsKinematic$MH = RuntimeHelper.downcallHandle(
        "JPC_Body_IsKinematic",
        constants$69.JPC_Body_IsKinematic$FUNC
    );
    static final FunctionDescriptor JPC_Body_IsDynamic$FUNC = FunctionDescriptor.of(Constants$root.C_BOOL$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_Body_IsDynamic$MH = RuntimeHelper.downcallHandle(
        "JPC_Body_IsDynamic",
        constants$69.JPC_Body_IsDynamic$FUNC
    );
    static final FunctionDescriptor JPC_Body_CanBeKinematicOrDynamic$FUNC = FunctionDescriptor.of(Constants$root.C_BOOL$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_Body_CanBeKinematicOrDynamic$MH = RuntimeHelper.downcallHandle(
        "JPC_Body_CanBeKinematicOrDynamic",
        constants$69.JPC_Body_CanBeKinematicOrDynamic$FUNC
    );
    static final FunctionDescriptor JPC_Body_SetIsSensor$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_BOOL$LAYOUT
    );
    static final MethodHandle JPC_Body_SetIsSensor$MH = RuntimeHelper.downcallHandle(
        "JPC_Body_SetIsSensor",
        constants$69.JPC_Body_SetIsSensor$FUNC
    );
    static final FunctionDescriptor JPC_Body_IsSensor$FUNC = FunctionDescriptor.of(Constants$root.C_BOOL$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_Body_IsSensor$MH = RuntimeHelper.downcallHandle(
        "JPC_Body_IsSensor",
        constants$69.JPC_Body_IsSensor$FUNC
    );
}


