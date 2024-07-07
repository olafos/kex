plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.spotless)
    alias(libs.plugins.sonatype.publisher)
}

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

spotless {
    kotlin {
        ktlint("1.3.1")
    }
}

object Meta {
    val COMPONENT_TYPE = "java"
    val GROUP = "pl.codeplay"
    val ARTIFACT_ID = "kex"
    val VERSION = "0.1.0"
    val PUBLISHING_TYPE = "USER_MANAGED"
    val SHA_ALGORITHMS = listOf("SHA-512")
    val DESC = "Kotlin exception utilities"
    val LICENSE = "MIT"
    val LICENSE_URL = "https://opensource.org/license/mit"
    val GITHUB_REPO = "olafos/kex.git"
    val DEVELOPER_ID = "olafos"
    val DEVELOPER_NAME = "Olaf Tomczak"
    val DEVELOPER_ORGANIZATION = "codeplay.pl"
    val DEVELOPER_ORGANIZATION_URL = "https://github.com/olafos"
}

val sonatypeUsername: String? by project
val sonatypePassword: String? by project

sonatypeCentralPublishExtension {
    groupId.set(Meta.GROUP)
    artifactId.set(Meta.ARTIFACT_ID)
    version.set(Meta.VERSION)
    componentType.set(Meta.COMPONENT_TYPE)
    publishingType.set(Meta.PUBLISHING_TYPE)
    shaAlgorithms.set(Meta.SHA_ALGORITHMS)
    username.set(System.getenv("SONATYPE_USERNAME") ?: sonatypeUsername)
    password.set(System.getenv("SONATYPE_PASSWORD") ?: sonatypePassword)

    pom {
        name.set(Meta.ARTIFACT_ID)
        description.set(Meta.DESC)
        url.set("https://github.com/${Meta.GITHUB_REPO}")
        licenses {
            license {
                name.set(Meta.LICENSE)
                url.set(Meta.LICENSE_URL)
            }
        }
        developers {
            developer {
                id.set(Meta.DEVELOPER_ID)
                name.set(Meta.DEVELOPER_NAME)
                organization.set(Meta.DEVELOPER_ORGANIZATION)
                organizationUrl.set(Meta.DEVELOPER_ORGANIZATION_URL)
            }
        }
        scm {
            url.set("https://github.com/${Meta.GITHUB_REPO}")
            connection.set("scm:git:https://github.com/${Meta.GITHUB_REPO}")
            developerConnection.set("scm:git:https://github.com/${Meta.GITHUB_REPO}")
        }
        issueManagement {
            system.set("GitHub")
            url.set("https://github.com/${Meta.GITHUB_REPO}/issues")
        }
    }
}