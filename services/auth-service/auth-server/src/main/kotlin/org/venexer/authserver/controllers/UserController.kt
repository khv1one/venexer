package org.venexer.authserver.controllers

import com.google.gson.Gson
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import org.venexer.accountclient.dto.UserDto
import org.venexer.accountclient.dto.UserRegistrationDto
import org.venexer.authclient.dto.AuthUserInfo
import org.venexer.authserver.models.User
import org.venexer.authserver.repos.UserRepo
import org.venexer.authserver.services.UserService

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService,
    private val userRepo: UserRepo
) {

    @GetMapping("/current")
    fun getUser(@AuthenticationPrincipal user: User, authentication: Authentication): Map<String, Any>? {
        val authUserInfo = AuthUserInfo(user.id, user.username, user.authorities)
        return hashMapOf("auth_user" to Gson().toJson(authUserInfo), "principal" to authentication)
    }

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
            id = user.id,
            username = user.username
        )
    }
}