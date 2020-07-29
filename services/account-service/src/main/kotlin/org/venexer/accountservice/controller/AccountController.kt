package org.venexer.accountservice.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.venexer.accountservice.dto.UserDto
import org.venexer.accountservice.dto.UserRegistrationDto
import org.venexer.accountservice.services.AccountService

@RestController
class AccountController(val accountService: AccountService) {

    @PostMapping
    fun createNewAccount(@RequestBody user: UserRegistrationDto): UserDto {
        val res = accountService.create(user)
        println("[BUG] accountService.res = $res")
        return res
    }
}