package org.venexer.authservice.repos

import org.springframework.data.repository.CrudRepository
import org.venexer.authservice.models.User

interface UserRepo : CrudRepository<User, Long>