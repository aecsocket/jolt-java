package jolt.core;

public final class TempAllocatorImpl extends TempAllocator {
    private TempAllocatorImpl(long address) { super(address); }
    public static TempAllocatorImpl ofPointer(long address) { return new TempAllocatorImpl(address); }

    public static TempAllocatorImpl ofSize(long size) { return new TempAllocatorImpl(_create(size)); }
    private static native long _create(long size);
}
