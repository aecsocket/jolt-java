// Generated by jextract

package jolt.headers_f;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$48 {

    static final FunctionDescriptor JPC_Body_IsActive$FUNC = FunctionDescriptor.of(Constants$root.C_BOOL$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_Body_IsActive$MH = RuntimeHelper.downcallHandle(
        "JPC_Body_IsActive",
        constants$48.JPC_Body_IsActive$FUNC
    );
    static final FunctionDescriptor JPC_Body_IsStatic$FUNC = FunctionDescriptor.of(Constants$root.C_BOOL$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_Body_IsStatic$MH = RuntimeHelper.downcallHandle(
        "JPC_Body_IsStatic",
        constants$48.JPC_Body_IsStatic$FUNC
    );
    static final FunctionDescriptor JPC_Body_IsKinematic$FUNC = FunctionDescriptor.of(Constants$root.C_BOOL$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_Body_IsKinematic$MH = RuntimeHelper.downcallHandle(
        "JPC_Body_IsKinematic",
        constants$48.JPC_Body_IsKinematic$FUNC
    );
    static final FunctionDescriptor JPC_Body_IsDynamic$FUNC = FunctionDescriptor.of(Constants$root.C_BOOL$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_Body_IsDynamic$MH = RuntimeHelper.downcallHandle(
        "JPC_Body_IsDynamic",
        constants$48.JPC_Body_IsDynamic$FUNC
    );
    static final FunctionDescriptor JPC_Body_CanBeKinematicOrDynamic$FUNC = FunctionDescriptor.of(Constants$root.C_BOOL$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_Body_CanBeKinematicOrDynamic$MH = RuntimeHelper.downcallHandle(
        "JPC_Body_CanBeKinematicOrDynamic",
        constants$48.JPC_Body_CanBeKinematicOrDynamic$FUNC
    );
    static final FunctionDescriptor JPC_Body_SetIsSensor$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_BOOL$LAYOUT
    );
    static final MethodHandle JPC_Body_SetIsSensor$MH = RuntimeHelper.downcallHandle(
        "JPC_Body_SetIsSensor",
        constants$48.JPC_Body_SetIsSensor$FUNC
    );
}


