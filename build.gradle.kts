
plugins {
	id("io.spring.dependency-management") version "1.0.9.RELEASE"
	id("org.springframework.boot") version "2.3.0.RELEASE"
}

subprojects {
	group = "org.venexer"

	apply(plugin = "io.spring.dependency-management")
	apply(plugin = "org.springframework.boot")

	repositories {
		mavenCentral()
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}

	extra["springVersion"] = "2.3.0.RELEASE"
	extra["springCloudVersion"] = "Hoxton.SR4"
}
