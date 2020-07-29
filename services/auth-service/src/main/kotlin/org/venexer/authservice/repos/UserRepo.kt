package org.venexer.authservice.repos

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.venexer.authservice.models.User

@Repository
interface UserRepo : CrudRepository<User, Long> {

    fun findByUsername(username: String): User?
}