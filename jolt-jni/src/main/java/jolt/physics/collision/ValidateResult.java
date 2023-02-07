package jolt.physics.collision;

import io.github.aecsocket.jniglue.JniInclude;

@JniInclude("<Jolt/Physics/Collision/ContactListener.h>")
public enum ValidateResult {
    ACCEPT_ALL_CONTACTS_FOR_THIS_BODY_PAIR,
    ACCEPT_CONTACT,
    REJECT_CONTACT,
    REJECT_ALL_CONTACTS_FOR_THIS_BODY_PAIR
}
