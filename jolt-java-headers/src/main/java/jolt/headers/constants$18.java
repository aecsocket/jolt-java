// Generated by jextract

package jolt.headers;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$18 {

    static final FunctionDescriptor rpmatch$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle rpmatch$MH = RuntimeHelper.downcallHandle(
        "rpmatch",
        constants$18.rpmatch$FUNC
    );
    static final FunctionDescriptor getsubopt$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle getsubopt$MH = RuntimeHelper.downcallHandle(
        "getsubopt",
        constants$18.getsubopt$FUNC
    );
    static final FunctionDescriptor getloadavg$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle getloadavg$MH = RuntimeHelper.downcallHandle(
        "getloadavg",
        constants$18.getloadavg$FUNC
    );
    static final FunctionDescriptor JPC_AllocateFunction$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT
    );
    static final MethodHandle JPC_AllocateFunction$MH = RuntimeHelper.downcallHandle(
        constants$18.JPC_AllocateFunction$FUNC
    );
    static final FunctionDescriptor JPC_FreeFunction$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT
    );
}


