package jolt;

public final class BodyID extends JoltNative {
    private BodyID(long address) { super(address); }
    public static BodyID ofPointer(long address) { return new BodyID(address); }

    public static BodyID ofRaw(int value) { return new BodyID(_ofRaw(value)); }
    private static native long _ofRaw(int value);

    public static BodyID of(int index, byte sequence) { return new BodyID(_of(index, sequence)); }
    private static native long _of(int index, byte sequence);

    public int getIndex() { return _getIndex(address); }
    private static native int _getIndex(long address);

    public byte getSequenceNumber() { return _getSequenceNumber(address); }
    private static native byte _getSequenceNumber(long address);

    public boolean isInvalid() { return _isInvalid(address); }
    private static native boolean _isInvalid(long address);

    public boolean isEqual(BodyID other) { return _isEqual(address, other.address); }
    private static native boolean _isEqual(long address, long other);
}
