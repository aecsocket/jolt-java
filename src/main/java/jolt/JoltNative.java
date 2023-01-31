package jolt;

public class JoltNative implements Destroyable {
    protected long address;

    protected JoltNative() {}
    protected JoltNative(long address) {
        this.address = address;
    }

    public long getAddress() { return address; }

    @Override
    public void destroy() {
        _destroy(address);
        address = 0;
    }
    private static native void _destroy(long address);

    public static RuntimeException unimplemented() {
        return new UnimplementedMethodException();
    }
}
