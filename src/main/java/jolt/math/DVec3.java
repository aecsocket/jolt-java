package jolt.math;

public record DVec3(double x, double y, double z) {
    public static final DVec3 ZERO = new DVec3(0.0, 0.0, 0.0);

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}
