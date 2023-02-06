package jolt;

import java.util.ArrayList;
import java.util.List;

public enum JoltFeature {
    DOUBLE_PRECISION,
    SSE4_1,
    SSE4_2,
    AVX,
    AVX2,
    AVX512,
    LZCNT,
    TZCNT,
    F16C,
    FMADD;

    public boolean isSet(int bits) {
        return (bits & (1 << ordinal())) != 0;
    }

    public static List<JoltFeature> flagsOf(int bits) {
        var result = new ArrayList<JoltFeature>();
        for (var feature : values()) {
            if (feature.isSet(bits)) {
                result.add(feature);
            }
        }
        return result;
    }
}
