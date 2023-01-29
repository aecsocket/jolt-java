package jolt;

public class ContactListener extends JoltNative {
    protected ContactListener(long address) { super(address); }
    public static ContactListener ofPointer(long address) { return new ContactListener(address); }

    public ContactListener() {
        super(_create());
    }
    private static native long _create();

    //public ValidateResult onContactValidate(BodyID body1, BodyID body2, ) { throw unsupported(); }

    //public void onContactAdded(BodyID body1, BodyID body2, ) {}
}
