// Generated by jextract

package jolt.headers;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$54 {

    static final FunctionDescriptor JPC_CompoundShapeSettings_AddShapeSettings$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle JPC_CompoundShapeSettings_AddShapeSettings$MH = RuntimeHelper.downcallHandle(
        "JPC_CompoundShapeSettings_AddShapeSettings",
        constants$54.JPC_CompoundShapeSettings_AddShapeSettings$FUNC
    );
    static final FunctionDescriptor JPC_CompoundShapeSettings_AddShape$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle JPC_CompoundShapeSettings_AddShape$MH = RuntimeHelper.downcallHandle(
        "JPC_CompoundShapeSettings_AddShape",
        constants$54.JPC_CompoundShapeSettings_AddShape$FUNC
    );
    static final FunctionDescriptor JPC_StaticCompoundShapeSettings_Create$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT);
    static final MethodHandle JPC_StaticCompoundShapeSettings_Create$MH = RuntimeHelper.downcallHandleVariadic(
        "JPC_StaticCompoundShapeSettings_Create",
        constants$54.JPC_StaticCompoundShapeSettings_Create$FUNC
    );
    static final FunctionDescriptor JPC_StaticCompoundShapeSettings_CreateShape$FUNC = FunctionDescriptor.of(MemoryLayout.structLayout(
        Constants$root.C_POINTER$LAYOUT.withName("result"),
        MemoryLayout.sequenceLayout(256, Constants$root.C_CHAR$LAYOUT).withName("error")
    ).withName("JPC_ShapeResult"),
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_StaticCompoundShapeSettings_CreateShape$MH = RuntimeHelper.downcallHandle(
        "JPC_StaticCompoundShapeSettings_CreateShape",
        constants$54.JPC_StaticCompoundShapeSettings_CreateShape$FUNC
    );
    static final FunctionDescriptor JPC_MutableCompoundShapeSettings_Create$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT);
    static final MethodHandle JPC_MutableCompoundShapeSettings_Create$MH = RuntimeHelper.downcallHandleVariadic(
        "JPC_MutableCompoundShapeSettings_Create",
        constants$54.JPC_MutableCompoundShapeSettings_Create$FUNC
    );
    static final FunctionDescriptor JPC_DecoratedShapeSettings_GetInnerShape$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle JPC_DecoratedShapeSettings_GetInnerShape$MH = RuntimeHelper.downcallHandle(
        "JPC_DecoratedShapeSettings_GetInnerShape",
        constants$54.JPC_DecoratedShapeSettings_GetInnerShape$FUNC
    );
}


