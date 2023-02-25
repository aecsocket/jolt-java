package jolt;

public final class Preconditions {
    public static void ensure(boolean expr, String message) {
        if (!expr)
            throw new IllegalArgumentException(message);
    }

    public static void ensureParam(String param, boolean expr, String message) {
        ensure(expr, param + ": " + message);
    }

    public static float gtOrEq(String param, float value, float threshold) {
        ensureParam(param, value >= threshold, "failed " + value + " >= " + threshold);
        return value;
    }

    public static float between(String param, float value, float min, float max) {
        ensureParam(param, value >= min && value <= max, "failed " + min + " <= " + value + " <= " + max);
        return value;
    }
}
