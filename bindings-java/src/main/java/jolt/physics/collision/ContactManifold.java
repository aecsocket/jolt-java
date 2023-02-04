package jolt.physics.collision;

import jolt.JoltNative;
import jolt.jni.JniHeader;
import jolt.jni.JniInclude;

@JniInclude("<Jolt/Physics/Collision/ContactListener.h>")
public final class ContactManifold extends JoltNative {
    private ContactManifold(long address) { super(address); }
    public static ContactManifold ref(long address) { return new ContactManifold(address); }

}

