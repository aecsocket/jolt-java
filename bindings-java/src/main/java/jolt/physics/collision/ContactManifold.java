package jolt.physics.collision;

import jolt.JoltNative;
import jolt.jni.JniInclude;
import jolt.jni.JniType;

@JniInclude("<Jolt/Physics/Collision/ContactListener.h>")
@JniType("ContactManifold")
public final class ContactManifold extends JoltNative {
    private ContactManifold(long address) { super(address); }
    public static ContactManifold ref(long address) { return address == 0 ? null : new ContactManifold(address); }

}

