// Generated by jextract

package jolt.headers;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$92 {

    static final FunctionDescriptor JPC_HingeConstraint_GetMotorState$FUNC = FunctionDescriptor.of(Constants$root.C_CHAR$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_HingeConstraint_GetMotorState$MH = RuntimeHelper.downcallHandle(
        "JPC_HingeConstraint_GetMotorState",
        constants$92.JPC_HingeConstraint_GetMotorState$FUNC
    );
    static final FunctionDescriptor JPC_HingeConstraint_SetTargetAngularVelocity$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_FLOAT$LAYOUT
    );
    static final MethodHandle JPC_HingeConstraint_SetTargetAngularVelocity$MH = RuntimeHelper.downcallHandle(
        "JPC_HingeConstraint_SetTargetAngularVelocity",
        constants$92.JPC_HingeConstraint_SetTargetAngularVelocity$FUNC
    );
    static final FunctionDescriptor JPC_HingeConstraint_GetTargetAngularVelocity$FUNC = FunctionDescriptor.of(Constants$root.C_FLOAT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_HingeConstraint_GetTargetAngularVelocity$MH = RuntimeHelper.downcallHandle(
        "JPC_HingeConstraint_GetTargetAngularVelocity",
        constants$92.JPC_HingeConstraint_GetTargetAngularVelocity$FUNC
    );
    static final FunctionDescriptor JPC_HingeConstraint_SetTargetAngle$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_FLOAT$LAYOUT
    );
    static final MethodHandle JPC_HingeConstraint_SetTargetAngle$MH = RuntimeHelper.downcallHandle(
        "JPC_HingeConstraint_SetTargetAngle",
        constants$92.JPC_HingeConstraint_SetTargetAngle$FUNC
    );
    static final FunctionDescriptor JPC_HingeConstraint_GetTargetAngle$FUNC = FunctionDescriptor.of(Constants$root.C_FLOAT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_HingeConstraint_GetTargetAngle$MH = RuntimeHelper.downcallHandle(
        "JPC_HingeConstraint_GetTargetAngle",
        constants$92.JPC_HingeConstraint_GetTargetAngle$FUNC
    );
    static final FunctionDescriptor JPC_HingeConstraint_SetLimits$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_FLOAT$LAYOUT,
        Constants$root.C_FLOAT$LAYOUT
    );
    static final MethodHandle JPC_HingeConstraint_SetLimits$MH = RuntimeHelper.downcallHandle(
        "JPC_HingeConstraint_SetLimits",
        constants$92.JPC_HingeConstraint_SetLimits$FUNC
    );
}


