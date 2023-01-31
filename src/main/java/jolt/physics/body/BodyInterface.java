package jolt;

public final class BodyInterface extends JoltNative {
    private BodyInterface(long address) { super(address); }
    public static BodyInterface ofPointer(long address) { return new BodyInterface(address); }

}

