package org.venexer.authservice.enums

import org.springframework.security.core.GrantedAuthority

enum class Roles : GrantedAuthority {
    USER_ROLE,
    ADMIN_ROLE;

    override fun getAuthority(): String {
        return name
    }
}