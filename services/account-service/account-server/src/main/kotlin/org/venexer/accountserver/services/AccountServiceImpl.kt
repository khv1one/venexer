package org.venexer.accountserver.services

import org.springframework.stereotype.Service
import org.venexer.accountclient.dto.UserDto
import org.venexer.accountclient.dto.UserRegistrationDto
import org.venexer.accountserver.rpc.AuthServiceFeignClient

@Service
class AccountServiceImpl(
    val authServiceFeignClient: AuthServiceFeignClient
) : AccountService {

    override fun create(user: UserRegistrationDto): UserDto {
        return authServiceFeignClient.createUser(user)
    }
}