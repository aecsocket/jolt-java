package jolt;

public abstract sealed class BaseJoltNative implements JoltNative
        permits AddressedJoltNative, SegmentedJoltNative {
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof JoltNative nativeObj) {
            return address().equals(nativeObj.address());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return address().hashCode();
    }
}
