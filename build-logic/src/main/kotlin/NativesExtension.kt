import org.gradle.api.provider.Property
import org.gradle.internal.os.OperatingSystem
import java.util.function.Predicate

abstract class NativesExtension {
    abstract val platformPredicate: Property<Predicate<OperatingSystem>>
    abstract val generator: Property<String>
    abstract val sourceLibraryName: Property<String>
    abstract val destDir: Property<String>
}
