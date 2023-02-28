package jolt.math;

public record Quat(float x, float y, float z, float w) {
    public static final Quat IDENTITY = new Quat(0f, 0f, 0f, 0f);

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ", " + w + ")";
    }
}
