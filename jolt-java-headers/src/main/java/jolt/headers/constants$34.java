// Generated by jextract

package jolt.headers;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$34 {

    static final FunctionDescriptor JPC_PhysicsSystem_WereBodiesInContact$FUNC = FunctionDescriptor.of(Constants$root.C_BOOL$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle JPC_PhysicsSystem_WereBodiesInContact$MH = RuntimeHelper.downcallHandle(
        "JPC_PhysicsSystem_WereBodiesInContact",
        constants$34.JPC_PhysicsSystem_WereBodiesInContact$FUNC
    );
    static final FunctionDescriptor JPC_BodyLockInterface_LockRead$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_BodyLockInterface_LockRead$MH = RuntimeHelper.downcallHandle(
        "JPC_BodyLockInterface_LockRead",
        constants$34.JPC_BodyLockInterface_LockRead$FUNC
    );
    static final FunctionDescriptor JPC_BodyLockInterface_UnlockRead$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_BodyLockInterface_UnlockRead$MH = RuntimeHelper.downcallHandle(
        "JPC_BodyLockInterface_UnlockRead",
        constants$34.JPC_BodyLockInterface_UnlockRead$FUNC
    );
    static final FunctionDescriptor JPC_BodyLockInterface_LockWrite$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_BodyLockInterface_LockWrite$MH = RuntimeHelper.downcallHandle(
        "JPC_BodyLockInterface_LockWrite",
        constants$34.JPC_BodyLockInterface_LockWrite$FUNC
    );
    static final FunctionDescriptor JPC_BodyLockInterface_UnlockWrite$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_BodyLockInterface_UnlockWrite$MH = RuntimeHelper.downcallHandle(
        "JPC_BodyLockInterface_UnlockWrite",
        constants$34.JPC_BodyLockInterface_UnlockWrite$FUNC
    );
    static final FunctionDescriptor JPC_CollisionCollector_Reset$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_CollisionCollector_Reset$MH = RuntimeHelper.downcallHandle(
        "JPC_CollisionCollector_Reset",
        constants$34.JPC_CollisionCollector_Reset$FUNC
    );
}


