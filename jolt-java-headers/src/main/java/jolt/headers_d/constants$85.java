// Generated by jextract

package jolt.headers_d;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$85 {

    static final FunctionDescriptor JPC_TwoBodyConstraint_GetBody2$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_TwoBodyConstraint_GetBody2$MH = RuntimeHelper.downcallHandle(
        "JPC_TwoBodyConstraint_GetBody2",
        constants$85.JPC_TwoBodyConstraint_GetBody2$FUNC
    );
    static final FunctionDescriptor JPJ_GetFeatures$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT);
    static final MethodHandle JPJ_GetFeatures$MH = RuntimeHelper.downcallHandleVariadic(
        "JPJ_GetFeatures",
        constants$85.JPJ_GetFeatures$FUNC
    );
    static final MemoryAddress NULL$ADDR = MemoryAddress.ofLong(0L);
}

