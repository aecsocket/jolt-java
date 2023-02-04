package jolt.math;

public record JtQuat(float x, float y, float z, float w) {
    public static final JtQuat IDENTITY = new JtQuat(0f, 0f, 0f, 1f);

    @Override
    public String toString() { return String.format("(%f + %fi + %fj + %fk)", w, x, y, z); }
}
