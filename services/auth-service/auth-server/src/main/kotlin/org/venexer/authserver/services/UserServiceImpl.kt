package org.venexer.authserver.services

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.venexer.authclient.enums.Roles
import org.venexer.authserver.models.User
import org.venexer.authserver.repos.UserRepo

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
        userRepo.findByUsername(username) ?.let { throw IllegalArgumentException("Incorrect username") }
    }
}