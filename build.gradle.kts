plugins {
    kotlin("jvm") version "1.8.0"
}

group = "com.koanly"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}

subprojects {
    apply(plugin = "kotlin")
    repositories {
        mavenCentral()
    }
}