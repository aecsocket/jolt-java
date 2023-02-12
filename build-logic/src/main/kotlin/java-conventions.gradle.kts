plugins {
    id("base-conventions")
    id("java-library")
    id("net.kyori.indra")
}

indra {
    javaVersions {
        target(17)
    }
}

repositories {
    mavenCentral()
}
