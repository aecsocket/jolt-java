import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import org.gradle.internal.os.OperatingSystem
import org.gradle.kotlin.dsl.listProperty
import org.gradle.kotlin.dsl.property

open class NativesExtension @javax.inject.Inject constructor(
    objects: ObjectFactory
) {
    val platformPredicate = objects.property<(OperatingSystem) -> Boolean>()
    val buildScriptBase = objects.listProperty<String>()
    val sourceLibraryName = objects.property<String>()
    val targetLibraryName = objects.property<String>()
}
