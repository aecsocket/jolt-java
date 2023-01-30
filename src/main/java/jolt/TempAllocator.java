package jolt;

public class TempAllocator extends JoltNative {
    protected TempAllocator(long address) { super(address); }
    public static TempAllocator ofPointer(long address) { return new TempAllocator(address); }
    protected TempAllocator() {}
}
