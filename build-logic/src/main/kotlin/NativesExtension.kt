import org.gradle.api.provider.Property
import org.gradle.internal.os.OperatingSystem

abstract class NativesExtension {
    abstract val platformPredicate: Property<(OperatingSystem) -> Boolean>
    abstract val sourceLibraryName: Property<String>
    abstract val targetLibraryName: Property<String>
}
