plugins {
    id("natives-conventions")
}

extensions.getByType<NativesExtension>().apply {
    platformPredicate.set { it.isWindows }
    generator.set("MinGW Makefiles")
    generatorBinary.set("make")
    sourceLibraryName.set("libJoltC.dll")
}
