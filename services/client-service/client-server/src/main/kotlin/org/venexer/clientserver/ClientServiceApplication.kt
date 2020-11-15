package org.venexer.clientserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity


@SpringBootApplication
@EnableDiscoveryClient
@EnableGlobalMethodSecurity(prePostEnabled = true)
class ClientServiceApplication

fun main(args: Array<String>) {
    runApplication<ClientServiceApplication>(*args)
}