package jolt.physics.collision;

public enum ValidateResult {
    ACCEPT_ALL_CONTACTS_FOR_THIS_BODY_PAIR  (_acceptAllContactsForThisBodyPair()),
    ACCEPT_CONTACT                          (_acceptContact()),
    REJECT_CONTACT                          (_rejectContact()),
    REJECT_ALL_CONTACTS_FOR_THIS_BODY_PAIR  (_rejectAllContactsForThisBodyPair());

    public final int value;

    ValidateResult(int value) { this.value = value; }

    private static native int _acceptAllContactsForThisBodyPair();
    private static native int _acceptContact();
    private static native int _rejectContact();
    private static native int _rejectAllContactsForThisBodyPair();
}
