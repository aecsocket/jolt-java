plugins {
    id("natives-conventions")
}

extensions.getByType<NativesExtension>().apply {
    platformPredicate.set { it.isLinux }
    generator.set("Unix Makefiles")
    generatorBinary.set("make")
    sourceLibraryName.set("libJoltJava.so")
}
