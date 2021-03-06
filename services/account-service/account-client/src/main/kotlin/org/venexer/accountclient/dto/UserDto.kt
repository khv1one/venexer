package org.venexer.accountclient.dto

data class UserDto(
    val id: Long,
    val username: String
) {
    companion object {
        private const val serialVersionUID: Long = 1L
    }
}