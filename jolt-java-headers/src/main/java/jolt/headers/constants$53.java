// Generated by jextract

package jolt.headers;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$53 {

    static final FunctionDescriptor JPC_BodyInterface_AddForceAndTorque$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_BodyInterface_AddForceAndTorque$MH = RuntimeHelper.downcallHandle(
        "JPC_BodyInterface_AddForceAndTorque",
        constants$53.JPC_BodyInterface_AddForceAndTorque$FUNC
    );
    static final FunctionDescriptor JPC_BodyInterface_AddImpulse$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_BodyInterface_AddImpulse$MH = RuntimeHelper.downcallHandle(
        "JPC_BodyInterface_AddImpulse",
        constants$53.JPC_BodyInterface_AddImpulse$FUNC
    );
    static final FunctionDescriptor JPC_BodyInterface_AddImpulseAtPosition$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_BodyInterface_AddImpulseAtPosition$MH = RuntimeHelper.downcallHandle(
        "JPC_BodyInterface_AddImpulseAtPosition",
        constants$53.JPC_BodyInterface_AddImpulseAtPosition$FUNC
    );
    static final FunctionDescriptor JPC_BodyInterface_AddAngularImpulse$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_BodyInterface_AddAngularImpulse$MH = RuntimeHelper.downcallHandle(
        "JPC_BodyInterface_AddAngularImpulse",
        constants$53.JPC_BodyInterface_AddAngularImpulse$FUNC
    );
    static final FunctionDescriptor JPC_Body_GetID$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_Body_GetID$MH = RuntimeHelper.downcallHandle(
        "JPC_Body_GetID",
        constants$53.JPC_Body_GetID$FUNC
    );
    static final FunctionDescriptor JPC_Body_IsActive$FUNC = FunctionDescriptor.of(Constants$root.C_BOOL$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_Body_IsActive$MH = RuntimeHelper.downcallHandle(
        "JPC_Body_IsActive",
        constants$53.JPC_Body_IsActive$FUNC
    );
}


