import com.android.build.gradle.LibraryExtension
import com.jfrog.bintray.gradle.tasks.BintrayUploadTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.net.URI

buildscript {
    repositories {
        google()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.5.2")
    }
}

plugins {
    kotlin("multiplatform") version "1.3.60"
    id("kotlinx-serialization") version "1.3.50"
    id("maven-publish")
    id("com.jfrog.bintray") version "1.8.4"
    id("com.github.kukuhyoniatmoko.buildconfigkotlin") version "1.0.5"
}

repositories {
    jcenter()
    google()
    mavenCentral()
    maven { url = URI("https://dl.bintray.com/kotlin/ktor") }
    maven { url = URI("https://kotlin.bintray.com/kotlinx") }
}

group = Library.group
version = Library.version

buildConfigKotlin {
    sourceSet("metadata") {
        buildConfig(name = "version", value = Library.version)
    }
}

kotlin {
    /* Targets configuration omitted. 
    *  To find out how to configure the targets, please follow the link:
    *  https://kotlinlang.org/docs/reference/building-mpp-with-gradle.html#setting-up-targets */
    jvm {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    sourceSets {
        all {
            languageSettings.progressiveMode = true
        }
        val commonMain by getting {
            kotlin.srcDirs("build/generated")
            dependencies {
                api(kotlin("stdlib-common"))
                api(Ktor("client"))
                api(Ktor("client-logging"))
                api(Ktor("client-core"))
                api(Ktor("client-json"))
                api(Ktor("client-serialization"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
                api(Ktor("client-mock"))
            }
        }
        val jvmMain by getting {
            dependencies {
                api(kotlin("stdlib-jdk8"))
                api(Ktor("client-core-jvm"))
                api(Ktor("client-json-jvm"))
                api(Ktor("client-logging-jvm"))
                api(Ktor("client-serialization-jvm"))
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
                implementation(Ktor("client-mock-jvm"))
                implementation(Ktor("client-okhttp"))
                implementation(Ktor("client-apache"))
                implementation(Ktor("client-android"))
            }
        }
    }
}

val javadocJar by tasks.creating(Jar::class) {
    archiveClassifier.value("javadoc")
}

publishing {
    repositories {
        maven {
            url = uri("https://dl.bintray.com/clinia/maven")
        }
    }
    publications.withType<MavenPublication>().all {
        groupId = Library.group
        version = Library.version
        artifactId = when (name) {
            "kotlinMultiplatform" -> Library.artifact
            "metadata" -> "${Library.artifact}-common"
            else -> "${Library.artifact}-$name"
        }

        pom.withXml {
            asNode().apply {
                appendNode("description", "Kotlin client for Clinia Search API.")
                appendNode("url", "https://github.com/clinia/cliniasearch-client-kotlin")
                appendNode("licenses").appendNode("license").apply {
                    appendNode("name", "MIT")
                    appendNode("url", "http://www.opensource.org/licenses/mit-license.php")
                    appendNode("distribution", "repo")
                }
                appendNode("developers").appendNode("developer").apply {
                    appendNode("id", "clinia")
                    appendNode("name", "The Clinia Team")
                    appendNode("email", "hey@clinia.ca")
                }
                appendNode("scm").apply {
                    appendNode("url", "https://github.com/clinia/cliniasearch-client-kotlin.git")
                }
            }
        }
    }

    kotlin.targets.forEach { target ->
        val targetPublication = publications.withType<MavenPublication>().findByName(target.name)

        targetPublication?.artifact(javadocJar)
    }
}

bintray {
    user = System.getenv("BINTRAY_USER")
    key = System.getenv("BINTRAY_KEY")
    publish = true

    pkg.apply {
        desc = "Clinia API Client for Kotlin lets you easily use the Clinia Search" +
                "REST API from your JVM project, such as Android or backend implementations."
        repo = "maven"
        name = Library.artifact
        websiteUrl = "https://www.clinia.ca/"
        issueTrackerUrl =  "https://github.com/clinia/cliniasearch-client-kotlin/issues"
        setLicenses("MIT")
        setLabels("Kotlin", "Clinia")
        vcsUrl = "https://github.com/clinia/cliniasearch-client-kotlin.git"
        version.apply {
            name = Library.version
            vcsTag = Library.version
        }
    }
}

tasks {
    val bintrayUpload by getting(BintrayUploadTask::class) {
        doFirst {
            setPublications("jvm", "metadata", "androidRelease")
        }
    }
    withType<KotlinCompile> {
        dependsOn("generateMetadataBuildConfigKotlin")
    }
}

tasks.withType<Test> {
    maxParallelForks = Runtime.getRuntime().availableProcessors().minus(1).coerceAtLeast(1)
}