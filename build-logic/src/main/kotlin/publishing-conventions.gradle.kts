plugins {
    id("java-conventions")
    id("net.kyori.indra.publishing")
}

signing {
    val signingKey: String? by project
    val signingPassword: String? by project
    if (signingKey != null) {
        useInMemoryPgpKeys(signingKey, signingPassword)
    }
}

indra {
    github("aecsocket", "jolt-jni")
    mitLicense()

    configurePublications {
        pom {
            developers {
                developer {
                    name.set("aecsocket")
                    email.set("aecsocket@tutanota.com")
                    url.set("https://github.com/aecsocket")
                }
            }
        }
    }
}
