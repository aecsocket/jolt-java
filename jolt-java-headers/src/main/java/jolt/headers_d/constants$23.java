// Generated by jextract

package jolt.headers_d;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$23 {

    static final FunctionDescriptor JPC_MotionProperties_ClampAngularVelocity$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_MotionProperties_ClampAngularVelocity$MH = RuntimeHelper.downcallHandle(
        "JPC_MotionProperties_ClampAngularVelocity",
        constants$23.JPC_MotionProperties_ClampAngularVelocity$FUNC
    );
    static final FunctionDescriptor JPC_MotionProperties_GetLinearDamping$FUNC = FunctionDescriptor.of(Constants$root.C_FLOAT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_MotionProperties_GetLinearDamping$MH = RuntimeHelper.downcallHandle(
        "JPC_MotionProperties_GetLinearDamping",
        constants$23.JPC_MotionProperties_GetLinearDamping$FUNC
    );
    static final FunctionDescriptor JPC_MotionProperties_SetLinearDamping$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_FLOAT$LAYOUT
    );
    static final MethodHandle JPC_MotionProperties_SetLinearDamping$MH = RuntimeHelper.downcallHandle(
        "JPC_MotionProperties_SetLinearDamping",
        constants$23.JPC_MotionProperties_SetLinearDamping$FUNC
    );
    static final FunctionDescriptor JPC_MotionProperties_GetAngularDamping$FUNC = FunctionDescriptor.of(Constants$root.C_FLOAT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_MotionProperties_GetAngularDamping$MH = RuntimeHelper.downcallHandle(
        "JPC_MotionProperties_GetAngularDamping",
        constants$23.JPC_MotionProperties_GetAngularDamping$FUNC
    );
    static final FunctionDescriptor JPC_MotionProperties_SetAngularDamping$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_FLOAT$LAYOUT
    );
    static final MethodHandle JPC_MotionProperties_SetAngularDamping$MH = RuntimeHelper.downcallHandle(
        "JPC_MotionProperties_SetAngularDamping",
        constants$23.JPC_MotionProperties_SetAngularDamping$FUNC
    );
    static final FunctionDescriptor JPC_MotionProperties_GetGravityFactor$FUNC = FunctionDescriptor.of(Constants$root.C_FLOAT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_MotionProperties_GetGravityFactor$MH = RuntimeHelper.downcallHandle(
        "JPC_MotionProperties_GetGravityFactor",
        constants$23.JPC_MotionProperties_GetGravityFactor$FUNC
    );
}


