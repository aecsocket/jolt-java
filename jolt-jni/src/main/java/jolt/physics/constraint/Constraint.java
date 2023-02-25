package jolt.physics.constraint;

import io.github.aecsocket.jniglue.JniTypeMapping;
import jolt.JoltNativeImpl;
import io.github.aecsocket.jniglue.JniInclude;

@JniInclude("<Jolt/Physics/Constraints/Constraint.h>")
@JniTypeMapping("Constraint")
public class Constraint extends JoltNativeImpl {
    protected Constraint(long address) { super(address); }
    public static Constraint ref(long address) { return address == 0 ? null : new Constraint(address); }


}

