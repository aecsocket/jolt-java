const val BUILD_TYPE = "buildType"
const val DOUBLE_PRECISION = "doublePrecision"
const val JVM_VERSION = "jvmVersion"

enum class JoltBuildType(val key: String) {
    DEBUG               ("Debug"),
    RELEASE             ("Release"),
    // RELEASE_ASAN        ("ReleaseASAN"),
    // RELEASE_UBSAN       ("ReleaseUBSAN"),
    // RELEASE_COVERAGE    ("ReleaseCoverage"),
    DISTRIBUTION        ("Distribution")
}

enum class JoltFeature {
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

    fun macro() = "JPH_$name"

    fun cmakeFlag() = "JOLT_JNI_$name"
}
