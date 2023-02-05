package jolt.physics.collision;

import jolt.JoltNative;
import jolt.jni.JniInclude;
import jolt.jni.JniType;

@JniInclude("<Jolt/Physics/Collision/NarrowPhaseQuery.h>")
@JniType("NarrowPhaseQuery")
public class NarrowPhaseQuery extends JoltNative {
    protected NarrowPhaseQuery(long address) { super(address); }
    public static NarrowPhaseQuery ref(long address) { return address == 0 ? null : new NarrowPhaseQuery(address); }


}

