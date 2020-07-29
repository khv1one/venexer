package org.venexer.accountservice.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.venexer.accountservice.dto.UserDto
import org.venexer.accountservice.dto.UserRegistrationDto

@FeignClient(name = "auth-service")
interface AuthServiceFeignClient {

    @PostMapping("/uaa/user")
    fun createUser(user: UserRegistrationDto): UserDto
}