package org.venexer.accountservice.services

import org.venexer.accountservice.dto.UserDto
import org.venexer.accountservice.dto.UserRegistrationDto

interface AccountService {

    fun create(user: UserRegistrationDto): UserDto
}