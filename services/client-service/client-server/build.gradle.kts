import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.72"
    kotlin("plugin.spring") version "1.3.72"
    kotlin("plugin.jpa") version "1.3.72"
    id("org.flywaydb.flyway") version "6.5.3"
}

version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

dependencies {
    compile(project(path = ":services:auth-service:auth-client"))
    compile(project(path = ":services:question-service:question-client"))
    compile(project(path = ":services:client-service:client-client"))

    implementation("com.google.code.gson:gson:2.8.5")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.cloud:spring-cloud-starter-config")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("org.springframework.cloud:spring-cloud-starter-oauth2")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    implementation(project(":services:account-service:account-client"))

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("org.springframework.security:spring-security-test")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
        mavenBom("org.springframework.boot:spring-boot-dependencies:${property("springVersion")}") {
            bomProperty("kotlin.version", "1.3.72")
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}
