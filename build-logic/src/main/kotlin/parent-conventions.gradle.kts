plugins {
    id("net.kyori.indra.publishing.sonatype")
    id("publishing-conventions")
}

indraSonatype {
    useAlternateSonatypeOSSHost("s01")
}

tasks.register("printVersionType") {
    doFirst {
        println(if (net.kyori.indra.util.Versioning.isSnapshot(project)) "snapshot" else "release")
    }
}
