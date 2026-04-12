@file:Suppress("UnstableApiUsage")

import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("kotlin-convention")
    `maven-publish`
}

tasks {
    withType<KotlinCompile>().configureEach {
        compilerOptions {
            apiVersion = KotlinVersion.KOTLIN_1_9
            languageVersion = KotlinVersion.KOTLIN_1_9
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            configureEach {
                from(components["java"])
                pom {
                    groupId = project.group.toString()
                    artifactId = project.name
                    version = project.version.toString()
                    name = project.name
                    description = project.description
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
