package org.venexer.authservice.repos

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.venexer.authservice.models.AuthClientDetails

@Repository
interface AuthClientRepo : CrudRepository<AuthClientDetails, String> {

    fun findByClientId(clientId: String): AuthClientDetails?
}