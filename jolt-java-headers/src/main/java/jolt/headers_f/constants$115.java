// Generated by jextract

package jolt.headers_f;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$115 {

    static final FunctionDescriptor JPC_PointConstraintSettings_Create$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT);
    static final MethodHandle JPC_PointConstraintSettings_Create$MH = RuntimeHelper.downcallHandleVariadic(
        "JPC_PointConstraintSettings_Create",
        constants$115.JPC_PointConstraintSettings_Create$FUNC
    );
    static final FunctionDescriptor JPC_PointConstraintSettings_GetSpace$FUNC = FunctionDescriptor.of(Constants$root.C_CHAR$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_PointConstraintSettings_GetSpace$MH = RuntimeHelper.downcallHandle(
        "JPC_PointConstraintSettings_GetSpace",
        constants$115.JPC_PointConstraintSettings_GetSpace$FUNC
    );
    static final FunctionDescriptor JPC_PointConstraintSettings_SetSpace$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_CHAR$LAYOUT
    );
    static final MethodHandle JPC_PointConstraintSettings_SetSpace$MH = RuntimeHelper.downcallHandle(
        "JPC_PointConstraintSettings_SetSpace",
        constants$115.JPC_PointConstraintSettings_SetSpace$FUNC
    );
    static final FunctionDescriptor JPC_PointConstraintSettings_GetPoint1$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_PointConstraintSettings_GetPoint1$MH = RuntimeHelper.downcallHandle(
        "JPC_PointConstraintSettings_GetPoint1",
        constants$115.JPC_PointConstraintSettings_GetPoint1$FUNC
    );
    static final FunctionDescriptor JPC_PointConstraintSettings_SetPoint1$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_PointConstraintSettings_SetPoint1$MH = RuntimeHelper.downcallHandle(
        "JPC_PointConstraintSettings_SetPoint1",
        constants$115.JPC_PointConstraintSettings_SetPoint1$FUNC
    );
    static final FunctionDescriptor JPC_PointConstraintSettings_GetPoint2$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_PointConstraintSettings_GetPoint2$MH = RuntimeHelper.downcallHandle(
        "JPC_PointConstraintSettings_GetPoint2",
        constants$115.JPC_PointConstraintSettings_GetPoint2$FUNC
    );
}

