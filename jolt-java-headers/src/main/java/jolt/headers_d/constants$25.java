// Generated by jextract

package jolt.headers_d;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$25 {

    static final FunctionDescriptor JPC_MotionProperties_SetInverseInertia$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_MotionProperties_SetInverseInertia$MH = RuntimeHelper.downcallHandle(
        "JPC_MotionProperties_SetInverseInertia",
        constants$25.JPC_MotionProperties_SetInverseInertia$FUNC
    );
    static final FunctionDescriptor JPC_MotionProperties_GetLocalSpaceInverseInertia$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_MotionProperties_GetLocalSpaceInverseInertia$MH = RuntimeHelper.downcallHandle(
        "JPC_MotionProperties_GetLocalSpaceInverseInertia",
        constants$25.JPC_MotionProperties_GetLocalSpaceInverseInertia$FUNC
    );
    static final FunctionDescriptor JPC_MotionProperties_GetInverseInertiaForRotation$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_MotionProperties_GetInverseInertiaForRotation$MH = RuntimeHelper.downcallHandle(
        "JPC_MotionProperties_GetInverseInertiaForRotation",
        constants$25.JPC_MotionProperties_GetInverseInertiaForRotation$FUNC
    );
    static final FunctionDescriptor JPC_MotionProperties_MultiplyWorldSpaceInverseInertiaByVector$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_MotionProperties_MultiplyWorldSpaceInverseInertiaByVector$MH = RuntimeHelper.downcallHandle(
        "JPC_MotionProperties_MultiplyWorldSpaceInverseInertiaByVector",
        constants$25.JPC_MotionProperties_MultiplyWorldSpaceInverseInertiaByVector$FUNC
    );
    static final FunctionDescriptor JPC_MotionProperties_GetPointVelocityCOM$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_MotionProperties_GetPointVelocityCOM$MH = RuntimeHelper.downcallHandle(
        "JPC_MotionProperties_GetPointVelocityCOM",
        constants$25.JPC_MotionProperties_GetPointVelocityCOM$FUNC
    );
    static final FunctionDescriptor JPC_MotionProperties_GetMaxLinearVelocity$FUNC = FunctionDescriptor.of(Constants$root.C_FLOAT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_MotionProperties_GetMaxLinearVelocity$MH = RuntimeHelper.downcallHandle(
        "JPC_MotionProperties_GetMaxLinearVelocity",
        constants$25.JPC_MotionProperties_GetMaxLinearVelocity$FUNC
    );
}


