enum class JoltBuildType(val key: String) {
    DEBUG           ("Debug"),
    RELEASE         ("Release"),
    DISTRIBUTION    ("Distribution")
}

enum class JoltBuildFlavor {
    SP,
    DP
}

enum class JoltBuildFeature {
    DOUBLE_PRECISION,
    USE_SSE4_1,
    USE_SSE4_2,
    USE_AVX,
    USE_AVX2,
    USE_AVX512,
    USE_LZCNT,
    USE_TZCNT,
    USE_F16C,
    USE_FMADD;

    fun cmakeFlag() = "JOLT_JAVA_$name"
}
