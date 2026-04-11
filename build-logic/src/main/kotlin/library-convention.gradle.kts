plugins {
    id("kotlin-convention")
    `maven-publish`
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            pom {
                name.set("kex")
                description.set("Kotlin exception utilities - a lightweight library providing extension functions for better exception handling in Kotlin")
                url.set("https://github.com/olafos/kex")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                developers {
                    developer {
                        id.set("olafos")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/olafos/kex.git")
                    developerConnection.set("scm:git:ssh://github.com/olafos/kex.git")
                    url.set("https://github.com/olafos/kex")
                }
            }
        }
    }
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/olafos/kex")
            credentials {
                username = providers.gradleProperty("gpr.user").getOrElse(System.getenv("GITHUB_ACTOR"))
                password = providers.gradleProperty("gpr.key").getOrElse(System.getenv("GITHUB_TOKEN"))
            }
        }
    }
}
