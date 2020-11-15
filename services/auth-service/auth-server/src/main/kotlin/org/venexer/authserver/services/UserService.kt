package org.venexer.authserver.services

import org.venexer.authserver.models.User

interface UserService {
    fun create(user: User): User

}