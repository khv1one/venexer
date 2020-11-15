package org.venexer.authclient.dto

import org.venexer.authclient.enums.Roles

data class AuthUserInfo(
    val id: Long,
    val username: String,
    val authorities: HashSet<Roles> = HashSet()
) {
    companion object {
        private const val serialVersionUID: Long = 1L
    }
}