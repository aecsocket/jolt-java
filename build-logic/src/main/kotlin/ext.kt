import org.gradle.api.Project
import org.gradle.api.provider.Provider

val Project.ci: Provider<Boolean>
    get() = providers.environmentVariable("CI").map { it.toBoolean() }.orElse(false)

val Project.ciPublishApi: Provider<Boolean>
    get() = providers.environmentVariable("CI_PUBLISH_API").map { it.toBoolean() }.orElse(false)

val Project.buildType: JoltBuildType
    get() = (findProperty("buildType")?.toString())?.toUpperCase()?.let { JoltBuildType.valueOf(it) } ?: JoltBuildType.DEBUG

val Project.flavor: JoltFlavor
    get() = (findProperty("flavor")?.toString())?.toUpperCase()?.let { JoltFlavor.valueOf(it) } ?: JoltFlavor.SP

val Project.features: List<JoltFeature>
    get() = listOfNotNull(
        if (flavor == JoltFlavor.DP) JoltFeature.DOUBLE_PRECISION else null,
        JoltFeature.USE_SSE4_1,
        JoltFeature.USE_SSE4_2,
        JoltFeature.USE_AVX,
        JoltFeature.USE_AVX2,
        // JoltFeature.USE_AVX512,
        JoltFeature.USE_LZCNT,
        JoltFeature.USE_TZCNT,
        JoltFeature.USE_F16C,
        JoltFeature.USE_FMADD
    )
