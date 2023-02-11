import org.gradle.api.provider.Property
import org.gradle.api.tasks.Optional
import org.gradle.internal.os.OperatingSystem

abstract class NativesExtension {
    abstract val platformPredicate: Property<(OperatingSystem) -> Boolean>
    abstract val bindingsFileName: Property<String>
    abstract val destInnerDir: Property<String>
}
