// Generated by jextract

package jolt.headers_f;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$11 {

    static final FunctionDescriptor at_quick_exit$__func$FUNC = FunctionDescriptor.ofVoid();
    static final MethodHandle at_quick_exit$__func$MH = RuntimeHelper.downcallHandle(
        constants$11.at_quick_exit$__func$FUNC
    );
    static final FunctionDescriptor at_quick_exit$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle at_quick_exit$MH = RuntimeHelper.downcallHandle(
        "at_quick_exit",
        constants$11.at_quick_exit$FUNC
    );
    static final FunctionDescriptor on_exit$__func$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle on_exit$__func$MH = RuntimeHelper.downcallHandle(
        constants$11.on_exit$__func$FUNC
    );
    static final FunctionDescriptor on_exit$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle on_exit$MH = RuntimeHelper.downcallHandle(
        "on_exit",
        constants$11.on_exit$FUNC
    );
    static final FunctionDescriptor exit$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle exit$MH = RuntimeHelper.downcallHandle(
        "exit",
        constants$11.exit$FUNC
    );
}

