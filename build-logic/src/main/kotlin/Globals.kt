enum class JoltBuildType(val key: String) {
    DEBUG           ("Debug"),
    RELEASE         ("Release"),
    DISTRIBUTION    ("Distribution")
}

enum class JoltFlavor {
    SP,
    DP
}

enum class JoltFeature {
    DOUBLE_PRECISION,
    IPO,
    USE_SSE4_1,
    USE_SSE4_2,
    USE_AVX,
    USE_AVX2,
    USE_AVX512,
    USE_LZCNT,
    USE_TZCNT,
    USE_F16C,
    USE_FMADD;

    fun macro() = "JPH_$name"

    fun cmakeFlag() = "JOLT_JNI_$name"
}

val buildType = JoltBuildType.DISTRIBUTION
val flavor = JoltFlavor.DP
val features = listOfNotNull(
    if (flavor == JoltFlavor.DP) JoltFeature.DOUBLE_PRECISION else null
)
