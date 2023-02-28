package jolt.math;

public record FVec3(float x, float y, float z) {
    public static final FVec3 ZERO = new FVec3(0.0f, 0.0f, 0.0f);

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}
