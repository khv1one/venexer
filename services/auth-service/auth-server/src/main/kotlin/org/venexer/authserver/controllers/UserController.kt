package org.venexer.authserver.controllers

import com.google.gson.Gson
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import org.venexer.accountclient.dto.UserDto
import org.venexer.accountclient.dto.UserRegistrationDto
import org.venexer.authclient.constants.Constants
import org.venexer.authclient.dto.AuthUserInfo
import org.venexer.authserver.models.User
import org.venexer.authserver.services.UserService

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService
) {

    @GetMapping("/current")
    fun getUser(@AuthenticationPrincipal user: User, authentication: Authentication): Map<String, Any>? {
        val authUserInfo = AuthUserInfo(user.id(), user.username, user.authorities)

        return hashMapOf(
            Constants.AUTH_USER_DTO to Gson().toJson(authUserInfo),
            Constants.PRINCIPAL to authentication
        )
    }

    @PostMapping
    @PreAuthorize("#oauth2.hasScope('server')")
    fun createUser(@RequestBody userRegistration: UserRegistrationDto): UserDto {
        val savedUser = userService.create(userRegistration.toUser())

        return savedUser.toQuestionDto()
    }

    private fun UserRegistrationDto.toUser() = User (
        username = username,
        password = password
    )
}