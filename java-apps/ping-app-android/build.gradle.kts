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
}
