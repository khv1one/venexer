package org.venexer.authserver.repos

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.venexer.authserver.models.User

@Repository
interface UserRepo : CrudRepository<User, Long> {

    fun findByUsername(username: String): User?
}