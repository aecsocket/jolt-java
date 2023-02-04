package jolt.math;

public class JtVec3f {
    public static final JtVec3f ZERO = new JtVec3f(0f, 0f, 0f);

    public float x;
    public float y;
    public float z;

    public JtVec3f() {}
    public JtVec3f(float x, float y, float z) { set(x, y, z); }

    public void set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() { return String.format("(%f, %f, %f)", x, y, z); }
}
