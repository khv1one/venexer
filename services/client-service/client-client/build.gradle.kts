plugins {
    id("java-library")
    kotlin("jvm") version "1.3.72"
}

java.sourceCompatibility = JavaVersion.VERSION_11

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")

    implementation("org.springframework.boot:spring-boot-starter-security")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}