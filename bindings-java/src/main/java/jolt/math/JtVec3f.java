package jolt.math;

public record JtVec3f(float x, float y, float z) {
    public static final JtVec3f ZERO = new JtVec3f(0f, 0f, 0f);

    @Override
    public String toString() { return String.format("(%f, %f, %f)", x, y, z); }
}
