
plugins {
	id("io.spring.dependency-management") version "1.0.9.RELEASE"
	id("org.springframework.boot") version "2.1.13.RELEASE"
}

subprojects {
	group = "org.venexer"
	version = "0.0.1"

	apply(plugin = "io.spring.dependency-management")
	apply(plugin = "org.springframework.boot")

	repositories {
		mavenCentral()
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}
}
