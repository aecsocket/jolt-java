package jolt;

public record JoltFeatures(
        boolean doublePrecision,
        boolean useSSE4_1,
        boolean useSSE4_2,
        boolean useAVX,
        boolean useAVX2,
        boolean useAVX512,
        boolean useLZCNT,
        boolean useTZCNT,
        boolean useF16C,
        boolean useFMADD
) {
}
