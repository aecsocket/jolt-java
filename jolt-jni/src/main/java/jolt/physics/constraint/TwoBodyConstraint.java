package jolt.physics.constraint;

import io.github.aecsocket.jniglue.JniInclude;
import io.github.aecsocket.jniglue.JniTypeMapping;
import jolt.JoltNativeImpl;

@JniInclude("<Jolt/Physics/Constraints/TwoBodyConstraint.h>")
@JniTypeMapping("TwoBodyConstraint")
public class TwoBodyConstraint extends Constraint {
    protected TwoBodyConstraint(long address) { super(address); }
    public static TwoBodyConstraint ref(long address) { return address == 0 ? null : new TwoBodyConstraint(address); }


}

