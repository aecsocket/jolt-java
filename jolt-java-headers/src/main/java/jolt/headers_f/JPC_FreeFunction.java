// Generated by jextract

package jolt.headers_f;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
public interface JPC_FreeFunction {

    void apply(java.lang.foreign.MemoryAddress in_block);
    static MemorySegment allocate(JPC_FreeFunction fi, MemorySession session) {
        return RuntimeHelper.upcallStub(JPC_FreeFunction.class, fi, constants$18.JPC_FreeFunction$FUNC, session);
    }
    static JPC_FreeFunction ofAddress(MemoryAddress addr, MemorySession session) {
        MemorySegment symbol = MemorySegment.ofAddress(addr, 0, session);
        return (java.lang.foreign.MemoryAddress _in_block) -> {
            try {
                constants$18.JPC_FreeFunction$MH.invokeExact((Addressable)symbol, (java.lang.foreign.Addressable)_in_block);
            } catch (Throwable ex$) {
                throw new AssertionError("should not reach here", ex$);
            }
        };
    }
}


