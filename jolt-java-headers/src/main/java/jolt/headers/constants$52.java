// Generated by jextract

package jolt.headers;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$52 {

    static final FunctionDescriptor JPC_BodyInterface_CreateBodyWithID$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_BodyInterface_CreateBodyWithID$MH = RuntimeHelper.downcallHandle(
        "JPC_BodyInterface_CreateBodyWithID",
        constants$52.JPC_BodyInterface_CreateBodyWithID$FUNC
    );
    static final FunctionDescriptor JPC_BodyInterface_CreateBodyWithoutID$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_BodyInterface_CreateBodyWithoutID$MH = RuntimeHelper.downcallHandle(
        "JPC_BodyInterface_CreateBodyWithoutID",
        constants$52.JPC_BodyInterface_CreateBodyWithoutID$FUNC
    );
    static final FunctionDescriptor JPC_BodyInterface_DestroyBodyWithoutID$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_BodyInterface_DestroyBodyWithoutID$MH = RuntimeHelper.downcallHandle(
        "JPC_BodyInterface_DestroyBodyWithoutID",
        constants$52.JPC_BodyInterface_DestroyBodyWithoutID$FUNC
    );
    static final FunctionDescriptor JPC_BodyInterface_AssignNextBodyID$FUNC = FunctionDescriptor.of(Constants$root.C_BOOL$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_BodyInterface_AssignNextBodyID$MH = RuntimeHelper.downcallHandle(
        "JPC_BodyInterface_AssignNextBodyID",
        constants$52.JPC_BodyInterface_AssignNextBodyID$FUNC
    );
    static final FunctionDescriptor JPC_BodyInterface_AssignBodyID$FUNC = FunctionDescriptor.of(Constants$root.C_BOOL$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle JPC_BodyInterface_AssignBodyID$MH = RuntimeHelper.downcallHandle(
        "JPC_BodyInterface_AssignBodyID",
        constants$52.JPC_BodyInterface_AssignBodyID$FUNC
    );
    static final FunctionDescriptor JPC_BodyInterface_UnassignBodyID$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle JPC_BodyInterface_UnassignBodyID$MH = RuntimeHelper.downcallHandle(
        "JPC_BodyInterface_UnassignBodyID",
        constants$52.JPC_BodyInterface_UnassignBodyID$FUNC
    );
}


