package org.venexer.authservice.services

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.venexer.authservice.enums.Roles
import org.venexer.authservice.models.User
import org.venexer.authservice.repos.UserRepo

@Service
class UserServiceImpl(
    private val userRepo: UserRepo,
    private val passEncoder: PasswordEncoder
) : UserService {

    override fun create(user: User): User {
        throwIfUsernameExist(user.username)

        val userToSave = user.copy(
            password = passEncoder.encode(user.password),
            activated = true, // TODO: sms
            authorities = hashSetOf(Roles.USER_ROLE)
        )

        return userRepo.save(userToSave)
    }

    private fun throwIfUsernameExist(username: String) {
        userRepo.findByUsername(username) ?: throw IllegalArgumentException("Incorrect username")
    }
}