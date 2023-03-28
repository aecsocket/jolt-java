import org.gradle.api.Project
import org.gradle.api.provider.Provider
import java.io.File

val Project.ci: Provider<Boolean>
    get() = providers.environmentVariable("CI").map { it.toBoolean() }.orElse(false)

val Project.ciPublishCore: Provider<Boolean>
    get() = providers.environmentVariable("CI_PUBLISH_CORE").map { it.toBoolean() }.orElse(false)

val Project.joltDir: File
    get() = rootDir.resolve("JoltPhysicsC")

val Project.buildType: JoltBuildType
    get() = (findProperty("buildType")?.toString())?.uppercase()?.let { JoltBuildType.valueOf(it) } ?: JoltBuildType.DEBUG

val Project.buildFlavor: JoltBuildFlavor
    get() = (findProperty("buildFlavor")?.toString())?.uppercase()?.let { JoltBuildFlavor.valueOf(it) } ?: JoltBuildFlavor.SP

val Project.buildFeatures: List<JoltBuildFeature>
    get() = listOfNotNull(
        if (buildFlavor == JoltBuildFlavor.DP) JoltBuildFeature.DOUBLE_PRECISION else null,
        JoltBuildFeature.USE_SSE4_1,
        JoltBuildFeature.USE_SSE4_2, // defines USE_SSE4_1
        JoltBuildFeature.USE_AVX,
        JoltBuildFeature.USE_AVX2, // defines USE_AVX
        // JoltFeature.USE_AVX512, // defines USE_AVX, USE_AVX2
        JoltBuildFeature.USE_LZCNT,
        JoltBuildFeature.USE_TZCNT,
        JoltBuildFeature.USE_F16C, // defines USE_SSE4_1, USE_SSE4_2, USE_AVX
        JoltBuildFeature.USE_FMADD // defines USE_SSE4_1, USE_SSE4_2, USE_AVX
    )

fun Project.publishCore(): Boolean {
    if (ci.get() && !ciPublishCore.get())
        return false

    plugins.apply("publishing-conventions")
    return true
}
