package org.venexer.serviceregistryserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableEurekaServer
class ServiceRegistryServerApplication

fun main(args: Array<String>) {
	runApplication<ServiceRegistryServerApplication>(*args)
}
