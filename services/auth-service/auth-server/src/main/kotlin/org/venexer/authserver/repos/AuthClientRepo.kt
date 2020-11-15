package org.venexer.authserver.repos

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.venexer.authserver.models.AuthClientDetails

@Repository
interface AuthClientRepo : CrudRepository<AuthClientDetails, String> {

    fun findByClientId(clientId: String): AuthClientDetails?
}