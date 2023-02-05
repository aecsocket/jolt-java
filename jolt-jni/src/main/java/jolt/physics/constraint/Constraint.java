package jolt.physics.constraint;

import jolt.JoltNative;
import jolt.jni.JniInclude;
import jolt.jni.JniType;

@JniInclude("<Jolt/Physics/Constraints/Constraint.h>")
@JniType("Constraint")
public class Constraint extends JoltNative {
    protected Constraint(long address) { super(address); }
    public static Constraint ref(long address) { return address == 0 ? null : new Constraint(address); }


}

