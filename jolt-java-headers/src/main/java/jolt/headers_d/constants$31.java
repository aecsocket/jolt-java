// Generated by jextract

package jolt.headers_d;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$31 {

    static final FunctionDescriptor JPC_BodyLockInterface_UnlockWrite$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_BodyLockInterface_UnlockWrite$MH = RuntimeHelper.downcallHandle(
        "JPC_BodyLockInterface_UnlockWrite",
        constants$31.JPC_BodyLockInterface_UnlockWrite$FUNC
    );
    static final FunctionDescriptor JPC_RayCastBodyCollector_Reset$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_RayCastBodyCollector_Reset$MH = RuntimeHelper.downcallHandle(
        "JPC_RayCastBodyCollector_Reset",
        constants$31.JPC_RayCastBodyCollector_Reset$FUNC
    );
    static final FunctionDescriptor JPC_RayCastBodyCollector_UpdateEarlyOutFraction$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_FLOAT$LAYOUT
    );
    static final MethodHandle JPC_RayCastBodyCollector_UpdateEarlyOutFraction$MH = RuntimeHelper.downcallHandle(
        "JPC_RayCastBodyCollector_UpdateEarlyOutFraction",
        constants$31.JPC_RayCastBodyCollector_UpdateEarlyOutFraction$FUNC
    );
    static final FunctionDescriptor JPC_RayCastBodyCollector_ResetEarlyOutFraction$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_FLOAT$LAYOUT
    );
    static final MethodHandle JPC_RayCastBodyCollector_ResetEarlyOutFraction$MH = RuntimeHelper.downcallHandle(
        "JPC_RayCastBodyCollector_ResetEarlyOutFraction",
        constants$31.JPC_RayCastBodyCollector_ResetEarlyOutFraction$FUNC
    );
    static final FunctionDescriptor JPC_RayCastBodyCollector_ForceEarlyOut$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_RayCastBodyCollector_ForceEarlyOut$MH = RuntimeHelper.downcallHandle(
        "JPC_RayCastBodyCollector_ForceEarlyOut",
        constants$31.JPC_RayCastBodyCollector_ForceEarlyOut$FUNC
    );
    static final FunctionDescriptor JPC_RayCastBodyCollector_ShouldEarlyOut$FUNC = FunctionDescriptor.of(Constants$root.C_BOOL$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_RayCastBodyCollector_ShouldEarlyOut$MH = RuntimeHelper.downcallHandle(
        "JPC_RayCastBodyCollector_ShouldEarlyOut",
        constants$31.JPC_RayCastBodyCollector_ShouldEarlyOut$FUNC
    );
}


