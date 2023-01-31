package jolt;

public final class SubShapeID extends JoltNative {
    private SubShapeID(long address) { super(address); }
    public static SubShapeID ofPointer(long address) { return new SubShapeID(address); }
}
