plugins {
	id("io.spring.dependency-management") version "1.0.9.RELEASE"
	id("org.springframework.boot").version("2.3.2.RELEASE")
}

subprojects {
	extra["springVersion"] = "2.3.2.RELEASE"
	extra["springCloudVersion"] = "Hoxton.SR6"

	group = "org.venexer"

	apply(plugin = "io.spring.dependency-management")
	apply(plugin = "org.springframework.boot")

	repositories {
		mavenCentral()
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}
}
