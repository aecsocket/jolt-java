import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import org.gradle.jvm.toolchain.JavaToolchainService
import org.gradle.jvm.toolchain.JavaToolchainSpec
import javax.inject.Inject

abstract class WriteToolchainPath : DefaultTask() {
    @get:Inject
    abstract val javaToolchainService: JavaToolchainService

    @get:OutputFile
    abstract val outputFile: RegularFileProperty

    @get:Internal
    abstract val toolchainSpec: Property<JavaToolchainSpec>

    @TaskAction
    fun run() {
        val compiler = javaToolchainService.compilerFor(toolchainSpec.get())
        val javaHome = compiler.get().executablePath.asFile.toPath().toAbsolutePath() // javahome/bin/javac
            .parent // javahome/bin
            .parent // javahome
        outputFile.get().asFile.writeText(javaHome.toAbsolutePath().toString())
    }
}
