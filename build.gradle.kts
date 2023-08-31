plugins {
    kotlin("jvm") version "1.8.0"
    `maven-publish`
}

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

allprojects {
    version = "1.0-SNAPSHOT"
    group = "com.github.gokoan"
}

subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "maven-publish")
    repositories {
        mavenCentral()
    }


    publishing {
        publications {
            create<MavenPublication>("maven") {
                groupId = project.group.toString()
                artifactId = project.name
                version = project.version.toString()
                from(components["kotlin"])
            }
        }
    }
}