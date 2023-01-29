package jolt;

public class JoltNative implements Destroyable {
    protected final long address;

    protected JoltNative(long address) {
        this.address = address;
    }

    public long getAddress() { return address; }

    @Override
    public void destroy() {
        _destroy(address);
    }
    private static native void _destroy(long address);

    static RuntimeException unsupported() {
        return new UnsupportedOperationException();
    }
}
