// Generated by jextract

package jolt.headers;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$104 {

    static final FunctionDescriptor JPC_SixDOFConstraint_GetLimitsMax$FUNC = FunctionDescriptor.of(Constants$root.C_FLOAT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_CHAR$LAYOUT
    );
    static final MethodHandle JPC_SixDOFConstraint_GetLimitsMax$MH = RuntimeHelper.downcallHandle(
        "JPC_SixDOFConstraint_GetLimitsMax",
        constants$104.JPC_SixDOFConstraint_GetLimitsMax$FUNC
    );
    static final FunctionDescriptor JPC_SixDOFConstraint_IsFixedAxis$FUNC = FunctionDescriptor.of(Constants$root.C_BOOL$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_CHAR$LAYOUT
    );
    static final MethodHandle JPC_SixDOFConstraint_IsFixedAxis$MH = RuntimeHelper.downcallHandle(
        "JPC_SixDOFConstraint_IsFixedAxis",
        constants$104.JPC_SixDOFConstraint_IsFixedAxis$FUNC
    );
    static final FunctionDescriptor JPC_SixDOFConstraint_IsFreeAxis$FUNC = FunctionDescriptor.of(Constants$root.C_BOOL$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_CHAR$LAYOUT
    );
    static final MethodHandle JPC_SixDOFConstraint_IsFreeAxis$MH = RuntimeHelper.downcallHandle(
        "JPC_SixDOFConstraint_IsFreeAxis",
        constants$104.JPC_SixDOFConstraint_IsFreeAxis$FUNC
    );
    static final FunctionDescriptor JPC_SixDOFConstraint_SetMaxFriction$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_CHAR$LAYOUT,
        Constants$root.C_FLOAT$LAYOUT
    );
    static final MethodHandle JPC_SixDOFConstraint_SetMaxFriction$MH = RuntimeHelper.downcallHandle(
        "JPC_SixDOFConstraint_SetMaxFriction",
        constants$104.JPC_SixDOFConstraint_SetMaxFriction$FUNC
    );
    static final FunctionDescriptor JPC_SixDOFConstraint_GetMaxFriction$FUNC = FunctionDescriptor.of(Constants$root.C_FLOAT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_CHAR$LAYOUT
    );
    static final MethodHandle JPC_SixDOFConstraint_GetMaxFriction$MH = RuntimeHelper.downcallHandle(
        "JPC_SixDOFConstraint_GetMaxFriction",
        constants$104.JPC_SixDOFConstraint_GetMaxFriction$FUNC
    );
    static final FunctionDescriptor JPC_SixDOFConstraint_GetMotorSettings$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_CHAR$LAYOUT
    );
    static final MethodHandle JPC_SixDOFConstraint_GetMotorSettings$MH = RuntimeHelper.downcallHandle(
        "JPC_SixDOFConstraint_GetMotorSettings",
        constants$104.JPC_SixDOFConstraint_GetMotorSettings$FUNC
    );
}


