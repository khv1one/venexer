package org.venexer.authservice.services

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.venexer.authservice.repos.UserRepo

@Service
class CustomUserDetailsService(
        private val userRepo: UserRepo
) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        TODO("Not yet implemented")
    }
}