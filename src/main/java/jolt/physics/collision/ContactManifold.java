package jolt.physics.collision;

import jolt.JoltNative;

public final class ContactManifold extends JoltNative {
    private ContactManifold(long address) { super(address); }
    public static ContactManifold ofPointer(long address) { return new ContactManifold(address); }

}
