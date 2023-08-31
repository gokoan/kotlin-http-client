plugins {
    kotlin("jvm") version "1.8.0"
}
val ktor_version = "2.3.3"

dependencies {
    implementation(project(":kotlin-http-client"))
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-apache:$ktor_version")
}
