// Generated by jextract

package jolt.headers;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$93 {

    static final FunctionDescriptor JPC_HingeConstraint_GetLimitsMin$FUNC = FunctionDescriptor.of(Constants$root.C_FLOAT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_HingeConstraint_GetLimitsMin$MH = RuntimeHelper.downcallHandle(
        "JPC_HingeConstraint_GetLimitsMin",
        constants$93.JPC_HingeConstraint_GetLimitsMin$FUNC
    );
    static final FunctionDescriptor JPC_HingeConstraint_GetLimitsMax$FUNC = FunctionDescriptor.of(Constants$root.C_FLOAT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_HingeConstraint_GetLimitsMax$MH = RuntimeHelper.downcallHandle(
        "JPC_HingeConstraint_GetLimitsMax",
        constants$93.JPC_HingeConstraint_GetLimitsMax$FUNC
    );
    static final FunctionDescriptor JPC_HingeConstraint_HasLimits$FUNC = FunctionDescriptor.of(Constants$root.C_BOOL$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_HingeConstraint_HasLimits$MH = RuntimeHelper.downcallHandle(
        "JPC_HingeConstraint_HasLimits",
        constants$93.JPC_HingeConstraint_HasLimits$FUNC
    );
    static final FunctionDescriptor JPC_HingeConstraint_GetTotalLambdaPosition$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_HingeConstraint_GetTotalLambdaPosition$MH = RuntimeHelper.downcallHandle(
        "JPC_HingeConstraint_GetTotalLambdaPosition",
        constants$93.JPC_HingeConstraint_GetTotalLambdaPosition$FUNC
    );
    static final FunctionDescriptor JPC_HingeConstraint_GetTotalLambdaRotation$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_HingeConstraint_GetTotalLambdaRotation$MH = RuntimeHelper.downcallHandle(
        "JPC_HingeConstraint_GetTotalLambdaRotation",
        constants$93.JPC_HingeConstraint_GetTotalLambdaRotation$FUNC
    );
    static final FunctionDescriptor JPC_HingeConstraint_GetTotalLambdaRotationLimits$FUNC = FunctionDescriptor.of(Constants$root.C_FLOAT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_HingeConstraint_GetTotalLambdaRotationLimits$MH = RuntimeHelper.downcallHandle(
        "JPC_HingeConstraint_GetTotalLambdaRotationLimits",
        constants$93.JPC_HingeConstraint_GetTotalLambdaRotationLimits$FUNC
    );
}


