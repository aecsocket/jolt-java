// Generated by jextract

package jolt.headers_d;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$131 {

    static final FunctionDescriptor JPC_SwingTwistConstraintSettings_GetSwingMotorSettings$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_SwingTwistConstraintSettings_GetSwingMotorSettings$MH = RuntimeHelper.downcallHandle(
        "JPC_SwingTwistConstraintSettings_GetSwingMotorSettings",
        constants$131.JPC_SwingTwistConstraintSettings_GetSwingMotorSettings$FUNC
    );
    static final FunctionDescriptor JPC_SwingTwistConstraintSettings_GetTwistMotorSettings$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_SwingTwistConstraintSettings_GetTwistMotorSettings$MH = RuntimeHelper.downcallHandle(
        "JPC_SwingTwistConstraintSettings_GetTwistMotorSettings",
        constants$131.JPC_SwingTwistConstraintSettings_GetTwistMotorSettings$FUNC
    );
    static final FunctionDescriptor JPC_SixDOFConstraintSettings_Create$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT);
    static final MethodHandle JPC_SixDOFConstraintSettings_Create$MH = RuntimeHelper.downcallHandleVariadic(
        "JPC_SixDOFConstraintSettings_Create",
        constants$131.JPC_SixDOFConstraintSettings_Create$FUNC
    );
    static final FunctionDescriptor JPC_SixDOFConstraintSettings_GetSpace$FUNC = FunctionDescriptor.of(Constants$root.C_CHAR$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_SixDOFConstraintSettings_GetSpace$MH = RuntimeHelper.downcallHandle(
        "JPC_SixDOFConstraintSettings_GetSpace",
        constants$131.JPC_SixDOFConstraintSettings_GetSpace$FUNC
    );
    static final FunctionDescriptor JPC_SixDOFConstraintSettings_SetSpace$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_CHAR$LAYOUT
    );
    static final MethodHandle JPC_SixDOFConstraintSettings_SetSpace$MH = RuntimeHelper.downcallHandle(
        "JPC_SixDOFConstraintSettings_SetSpace",
        constants$131.JPC_SixDOFConstraintSettings_SetSpace$FUNC
    );
    static final FunctionDescriptor JPC_SixDOFConstraintSettings_GetPosition1$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_SixDOFConstraintSettings_GetPosition1$MH = RuntimeHelper.downcallHandle(
        "JPC_SixDOFConstraintSettings_GetPosition1",
        constants$131.JPC_SixDOFConstraintSettings_GetPosition1$FUNC
    );
}


