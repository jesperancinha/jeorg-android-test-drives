// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
        google()
    }
    dependencies {
        classpath("com.google.guava:guava") {
            version {
                strictly("28.1-jre")
            }
        }
    }
}

plugins {
    alias(libs.plugins.org.jesperancinha.plugins.omni)
    id("jacoco")
}

allprojects {
    version = "0.0.0-SNAPSHOT"
}

jacoco {
    toolVersion = "0.8.7"
}

tasks.withType<Test>().configureEach {
    reports {
        html.required.set(false)
    }
    useJUnitPlatform()
    finalizedBy(tasks.named("jacocoTestReport"))
}

tasks.register<JacocoReport>("jacocoTestReport") {
    dependsOn(tasks.withType<Test>())
    reports {
        xml.required.set(true)
        csv.required.set(false)
        html.required.set(false)
    }
}
