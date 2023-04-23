plugins {
    id("natives-conventions")
}

extensions.getByType<NativesExtension>().apply {
    platformPredicate.set { it.isMacOsX }
    generator.set("Unix Makefiles")
    sourceLibraryName.set("libJoltC.dylib")
}
