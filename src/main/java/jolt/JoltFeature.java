package jolt;

import java.util.function.Predicate;

public enum JoltFeature {
    DOUBLE_PRECISION    (JoltFeatures::doublePrecision),
    USE_SSE4_1          (JoltFeatures::useSSE4_1),
    USE_SSE4_2          (JoltFeatures::useSSE4_2),
    USE_AVX             (JoltFeatures::useAVX),
    USE_AVX2            (JoltFeatures::useAVX2),
    USE_AVX512          (JoltFeatures::useAVX512),
    USE_LZCNT           (JoltFeatures::useLZCNT),
    USE_TZCNT           (JoltFeatures::useTZCNT),
    USE_F16C            (JoltFeatures::useF16C),
    USE_FMADD           (JoltFeatures::useFMADD);

    final Predicate<JoltFeatures> test;

    JoltFeature(Predicate<JoltFeatures> test) {
        this.test = test;
    }
}
