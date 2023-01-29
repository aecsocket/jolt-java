package jolt

class TestNatives {
    external fun addTen(x: Int): Int

    init {
        System.loadLibrary("jolt-jni-natives")
    }
}
