package org.venexer.authservice.services

import org.venexer.authservice.models.User

interface UserService {
    fun create(user: User): User
}