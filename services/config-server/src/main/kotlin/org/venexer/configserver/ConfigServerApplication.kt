package org.venexer.configserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.config.server.EnableConfigServer
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigServer
class ConfigServerApplication

fun main(args: Array<String>) {
	runApplication<ConfigServerApplication>(*args)
}
