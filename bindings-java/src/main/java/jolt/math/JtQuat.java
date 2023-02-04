package jolt.math;

public class JtQuat {
    public static final JtQuat IDENTITY = new JtQuat(0f, 0f, 0f, 1f);

    public float x;
    public float y;
    public float z;
    public float w;

    public JtQuat() {}
    public JtQuat(float x, float y, float z, float w) { set(x, y, z, w); }

    public void set(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    @Override
    public String toString() { return String.format("(%f + %fi + %fj + %fk)", w, x, y, z); }
}
