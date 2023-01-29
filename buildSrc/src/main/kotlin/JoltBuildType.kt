enum class JoltBuildType(val key: String) {
    DEBUG               ("Debug"),
    RELEASE             ("Release"),
    RELEASE_ASAN        ("ReleaseASAN"),
    RELEASE_UBSAN       ("ReleaseUBSAN"),
    RELEASE_COVERAGE    ("ReleaseCoverage"),
    DISTRIBUTION        ("Distribution");

    companion object {
        val Default = JoltBuildType.DEBUG
    }
}
