package jolt.physics.constraint;

import jolt.JoltNative;
import io.github.aecsocket.jniglue.JniInclude;
import io.github.aecsocket.jniglue.JniType;

@JniInclude("<Jolt/Physics/Constraints/Constraint.h>")
@JniType("Constraint")
public class Constraint extends JoltNative {
    protected Constraint(long address) { super(address); }
    public static Constraint ref(long address) { return address == 0 ? null : new Constraint(address); }


}

