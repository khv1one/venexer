package org.venexer.accountclient.dto

data class UserRegistrationDto(
    val username: String,
    val password: String
) {
    companion object {
        private const val serialVersionUID: Long = 1L
    }
}