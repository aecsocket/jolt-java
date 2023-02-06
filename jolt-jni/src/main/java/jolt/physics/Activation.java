package jolt.physics;

public enum Activation {
    ACTIVATE,
    DONT_ACTIVATE;

    public static Activation ofValue(boolean value) {
        return value ? ACTIVATE : DONT_ACTIVATE;
    }
}
