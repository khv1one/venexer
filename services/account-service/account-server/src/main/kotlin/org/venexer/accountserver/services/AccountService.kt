package org.venexer.accountserver.services

import org.venexer.accountclient.dto.UserDto
import org.venexer.accountclient.dto.UserRegistrationDto

interface AccountService {

    fun create(user: UserRegistrationDto): UserDto
}