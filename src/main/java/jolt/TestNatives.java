package jolt;

public class TestNatives {
    public native int addTen(int x);

    static {
        System.loadLibrary("jolt-jni-natives");
    }
}
