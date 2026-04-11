plugins {
    id("kotlin-convention")
    `maven-publish`
    signing
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            pom {
                name.set("JVM Gradle Multi-Module Template Library")
                description.set("A sample library from the JVM Gradle multi-module template")
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

signing {
    sign(publishing.publications["maven"])
}
