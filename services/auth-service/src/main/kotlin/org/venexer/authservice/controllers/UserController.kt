package org.venexer.authservice.controllers

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import org.venexer.authservice.dto.UserDto
import org.venexer.authservice.dto.UserRegistrationDto
import org.venexer.authservice.models.User
import org.venexer.authservice.services.UserService
import java.security.Principal

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService
) {

    @GetMapping("/current")
    fun getUser(principal: Principal): Principal = principal //TODO: nice

    @PostMapping
    @PreAuthorize("#oauth2.hasScope('server')")
    fun createUser(@RequestBody userRegistration: UserRegistrationDto): UserDto {
        println("[BUG] userRegistration: $userRegistration")
        val savedUser = userService.create(fromDto(userRegistration))
        return toDto(savedUser)
    }

    private fun fromDto(userRegistration: UserRegistrationDto): User {
        return User(
            username = userRegistration.username,
            password = userRegistration.password
        )
    }

    private fun toDto(user: User): UserDto {
        return UserDto(
            id = user.id(),
            username = user.username
        )
    }
}