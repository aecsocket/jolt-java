// Generated by jextract

package jolt.headers_d;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
public interface JPC_AllocateFunction {

    java.lang.foreign.Addressable apply(long in_size);
    static MemorySegment allocate(JPC_AllocateFunction fi, MemorySession session) {
        return RuntimeHelper.upcallStub(JPC_AllocateFunction.class, fi, constants$18.JPC_AllocateFunction$FUNC, session);
    }
    static JPC_AllocateFunction ofAddress(MemoryAddress addr, MemorySession session) {
        MemorySegment symbol = MemorySegment.ofAddress(addr, 0, session);
        return (long _in_size) -> {
            try {
                return (java.lang.foreign.Addressable)(java.lang.foreign.MemoryAddress)constants$18.JPC_AllocateFunction$MH.invokeExact((Addressable)symbol, _in_size);
            } catch (Throwable ex$) {
                throw new AssertionError("should not reach here", ex$);
            }
        };
    }
}


