// Generated by jextract

package jolt.headers_f;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$19 {

    static final FunctionDescriptor JPC_FreeFunction$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_FreeFunction$MH = RuntimeHelper.downcallHandle(
        constants$19.JPC_FreeFunction$FUNC
    );
    static final FunctionDescriptor JPC_AlignedAllocateFunction$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT
    );
    static final MethodHandle JPC_AlignedAllocateFunction$MH = RuntimeHelper.downcallHandle(
        constants$19.JPC_AlignedAllocateFunction$FUNC
    );
    static final FunctionDescriptor JPC_AlignedFreeFunction$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_AlignedFreeFunction$MH = RuntimeHelper.downcallHandle(
        constants$19.JPC_AlignedFreeFunction$FUNC
    );
    static final FunctionDescriptor JPC_BodyIDVector_Destroy$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_BodyIDVector_Destroy$MH = RuntimeHelper.downcallHandle(
        "JPC_BodyIDVector_Destroy",
        constants$19.JPC_BodyIDVector_Destroy$FUNC
    );
}


