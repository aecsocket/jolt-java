// Generated by jextract

package jolt.headers;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$31 {

    static final FunctionDescriptor JPC_PhysicsSystem_GetNarrowPhaseQuery$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_PhysicsSystem_GetNarrowPhaseQuery$MH = RuntimeHelper.downcallHandle(
        "JPC_PhysicsSystem_GetNarrowPhaseQuery",
        constants$31.JPC_PhysicsSystem_GetNarrowPhaseQuery$FUNC
    );
    static final FunctionDescriptor JPC_PhysicsSystem_GetNarrowPhaseQueryNoLock$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_PhysicsSystem_GetNarrowPhaseQueryNoLock$MH = RuntimeHelper.downcallHandle(
        "JPC_PhysicsSystem_GetNarrowPhaseQueryNoLock",
        constants$31.JPC_PhysicsSystem_GetNarrowPhaseQueryNoLock$FUNC
    );
    static final FunctionDescriptor JPC_PhysicsSystem_OptimizeBroadPhase$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_PhysicsSystem_OptimizeBroadPhase$MH = RuntimeHelper.downcallHandle(
        "JPC_PhysicsSystem_OptimizeBroadPhase",
        constants$31.JPC_PhysicsSystem_OptimizeBroadPhase$FUNC
    );
    static final FunctionDescriptor JPC_PhysicsSystem_AddStepListener$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_PhysicsSystem_AddStepListener$MH = RuntimeHelper.downcallHandle(
        "JPC_PhysicsSystem_AddStepListener",
        constants$31.JPC_PhysicsSystem_AddStepListener$FUNC
    );
    static final FunctionDescriptor JPC_PhysicsSystem_RemoveStepListener$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_PhysicsSystem_RemoveStepListener$MH = RuntimeHelper.downcallHandle(
        "JPC_PhysicsSystem_RemoveStepListener",
        constants$31.JPC_PhysicsSystem_RemoveStepListener$FUNC
    );
    static final FunctionDescriptor JPC_PhysicsSystem_Update$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_FLOAT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_PhysicsSystem_Update$MH = RuntimeHelper.downcallHandle(
        "JPC_PhysicsSystem_Update",
        constants$31.JPC_PhysicsSystem_Update$FUNC
    );
}


