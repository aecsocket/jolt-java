package jolt.physics.collision;

import io.github.aecsocket.jniglue.JniTypeMapping;
import jolt.JoltNative;
import io.github.aecsocket.jniglue.JniInclude;

@JniInclude("<Jolt/Physics/Collision/ContactListener.h>")
@JniTypeMapping("ContactManifold")
public final class ContactManifold extends JoltNative {
    private ContactManifold(long address) { super(address); }
    public static ContactManifold ref(long address) { return address == 0 ? null : new ContactManifold(address); }

}

