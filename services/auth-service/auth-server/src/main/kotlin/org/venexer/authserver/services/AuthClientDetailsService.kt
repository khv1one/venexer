package org.venexer.authserver.services

import org.springframework.security.oauth2.provider.ClientDetails
import org.springframework.security.oauth2.provider.ClientDetailsService
import org.springframework.stereotype.Service
import org.venexer.authserver.repos.AuthClientRepo

@Service
class AuthClientDetailsService (
    private val authClientRepo: AuthClientRepo
) : ClientDetailsService {

    override fun loadClientByClientId(clientId: String): ClientDetails {
        return authClientRepo.findByClientId(clientId) ?: throw IllegalArgumentException("Client not found")
    }
}