plugins {
    id("parent-conventions")
}

group = "io.github.aecsocket"
version = "0.1.0-SNAPSHOT"
description = "Java bindings for JoltPhysics"

tasks.register("printVersionType") {
    doFirst {
        println(if (net.kyori.indra.util.Versioning.isRelease(project)) "release" else "snapshot")
    }
}
