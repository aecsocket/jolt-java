enum class JoltBuildType(val key: String) {
    DEBUG               ("Debug"),
    RELEASE             ("Release"),
    // RELEASE_ASAN        ("ReleaseASAN"),
    // RELEASE_UBSAN       ("ReleaseUBSAN"),
    // RELEASE_COVERAGE    ("ReleaseCoverage"),
    DISTRIBUTION        ("Distribution")
}

enum class JoltFlavor(val key: String) {
    SP  ("Sp"),
    DP  ("Dp")
}

const val BUILD_TYPE = "buildType"
const val FLAVOR = "flavor"
