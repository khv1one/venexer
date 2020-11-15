package org.venexer.accountserver.rpc

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.venexer.accountclient.dto.UserDto
import org.venexer.accountclient.dto.UserRegistrationDto


@FeignClient(name = "auth-service")
interface AuthServiceFeignClient {

    @PostMapping("/uaa/user")
    fun createUser(user: UserRegistrationDto): UserDto
}