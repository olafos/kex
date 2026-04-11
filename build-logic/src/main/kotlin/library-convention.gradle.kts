@file:Suppress("UnstableApiUsage")

import org.gradle.api.publish.maven.MavenPublication

plugins {
    id("kotlin-convention")
    `maven-publish`
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            pom {
                groupId = project.group.toString()
                artifactId = project.name
                version = project.version.toString()
                name = project.name
                description = project.description ?: ""
                url = "https://github.com/olafos/kex"
                licenses {
                    license {
                        name = "MIT License"
                        url = "https://opensource.org/licenses/MIT"
                    }
                }
                developers {
                    developer {
                        id = "olafos"
                    }
                }
                scm {
                    connection = "scm:git:git://github.com/olafos/kex.git"
                    developerConnection = "scm:git:ssh://github.com/olafos/kex.git"
                    url = "https://github.com/olafos/kex"
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
