plugins {
    id("natives-conventions")
}

extensions.getByType<NativesExtension>().apply {
    platformPredicate.set { it.isWindows }
    generator.set("MinGW Makefiles")
    sourceLibraryName.set("libJoltC.dll")
}
