package jolt;

public class JoltNative implements AutoCloseable {
    protected long address;

    protected JoltNative(long address) { this.address = address; }
    // although this *can* return null, we leave the nullability ambiguous
    public static JoltNative ref(long address) { return address == 0 ? null : new JoltNative(address); }

    public JoltNative() {}

    public long getAddress() { return address; }

    public void delete() {
        if (address == 0L)
            throw new IllegalStateException("Native object is already deleted");
        _delete(address);
        address = 0;
    }
    private static native void _delete(long address);
    @Override public void close() { delete(); }

    public static RuntimeException unimplemented() {
        return new UnimplementedMethodException();
    }
}
