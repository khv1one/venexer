package org.venexer.accountservice.services

import org.springframework.stereotype.Service
import org.venexer.accountservice.client.AuthServiceFeignClient
import org.venexer.accountservice.dto.UserDto
import org.venexer.accountservice.dto.UserRegistrationDto

@Service
class AccountServiceImpl(
    val authServiceFeignClient: AuthServiceFeignClient
) : AccountService {

    override fun create(user: UserRegistrationDto): UserDto {
        println("[BUG] user: $user")
        return authServiceFeignClient.createUser(user)
    }
}