package org.venexer.accountserver.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.venexer.accountclient.dto.UserDto
import org.venexer.accountclient.dto.UserRegistrationDto
import org.venexer.accountserver.services.AccountService


@RestController
class AccountController(val accountService: AccountService) {

    @PostMapping
    fun createNewAccount(@RequestBody user: UserRegistrationDto): UserDto {
        return accountService.create(user)
    }
}